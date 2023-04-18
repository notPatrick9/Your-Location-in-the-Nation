package DatabasePersist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.sqldemo.DBUtil;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	
	
	@SuppressWarnings("resource")
	public boolean InsertNewBookAndAuthor(String firstname, String lastname, String title, String ISBN, int year) throws Exception{
		// load Derby JDBC driver
			try {
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			} catch (Exception e) {
				System.err.println("Could not load Derby JDBC driver");
				System.err.println(e.getMessage());
				System.exit(1);
			}

			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet resultSet = null;

			boolean Insert = true;
			int author_id = 0;
			
			
			try {
				// connect to the database
				conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
			}
			catch(SQLException e) {
				System.err.println("Bad Connection");
			}

			
			
			
			try {
				conn.setAutoCommit(true);
				// a canned query to find book information (including author name) from title
				stmt = conn.prepareStatement(
						"select authors.author_id "
						+ "from authors "
						+ "where authors.lastname = ? and authors.firstname = ?"
				);

				// substitute the title entered by the user for the placeholder in the query
				stmt.setString(1, lastname);
				stmt.setString(2, firstname);
				
				

				// execute the query
				resultSet = stmt.executeQuery();
				
				if(resultSet.next()) {
					author_id = resultSet.getInt(1);
				}
				else {
					try {
						stmt = conn.prepareStatement(
								"insert into authors(lastname, firstname) "
								+ "values (?, ?)"
						);
						stmt.setString(1, lastname);
						stmt.setString(2, firstname);
						stmt.execute();
					
					}
					catch(SQLException e) {
						System.err.println("Error insertint new author");
					}
					finally {
						
					}
					
					try {
						stmt = conn.prepareStatement(
								"select authors.author_id "
								+ "from authors "
								+ "where authors.lastname = ? and authors.firstname = ?"
								);
						// substitute the title entered by the user for the placeholder in the query
						stmt.setString(1, lastname);
						stmt.setString(2, firstname);
						
						

						// execute the query
						resultSet = stmt.executeQuery();
						
						if(resultSet.next()) {
							author_id = resultSet.getInt(1);
						}
					
					}
					catch(SQLException e) {
						System.err.println("Error getting new author_id");
					}
					finally {
						
					}
					
						
				}
				
				
			}
			
			finally {
				// close result set, statement, connection
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(conn);
			}
			
			try {
					
				conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
				conn.setAutoCommit(true);
				// a canned query to find book information (including author name) from title
				stmt = conn.prepareStatement(
						"insert into books(author_id, title, ISBN, published) "
						+ "values (?, ?, ?, ?)"
				);

				// substitute the title entered by the user for the placeholder in the query
				
				stmt.setInt(1, author_id);
				stmt.setString(2, title);
				stmt.setString(3, ISBN);
				stmt.setInt(4, year);
				

				// execute the query
				Insert = stmt.execute();
				
				
				
				
			}
			catch(SQLException e) {
				System.err.println("Error insertint new author into books table");
			}
			finally {
				// close result set, statement, connection
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(conn);
			}

			return Insert;
		
			
		}
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		
		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	private void loadAuthor(Author author, ResultSet resultSet, int index) throws SQLException {
		author.setAuthorId(resultSet.getInt(index++));
		author.setLastname(resultSet.getString(index++));
		author.setFirstname(resultSet.getString(index++));
	}
	
	private void loadBook(Book book, ResultSet resultSet, int index) throws SQLException {
		book.setBookId(resultSet.getInt(index++));
		book.setAuthorId(resultSet.getInt(index++));
		book.setTitle(resultSet.getString(index++));
		book.setIsbn(resultSet.getString(index++));
		book.setPublished(resultSet.getInt(index++));		
	}
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				
				try {
					stmt1 = conn.prepareStatement(
						"create table authors (" +
						"	author_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	lastname varchar(40)," +
						"	firstname varchar(40)" +
						")"
					);	
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"create table books (" +
							"	book_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	author_id integer constraint author_id references authors, " +
							"	title varchar(70)," +
							"	isbn varchar(15)," +
							"   published integer " +
							")"
					);
					stmt2.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Author> authorList;
				List<Book> bookList;
				
				try {
					authorList = InitialData.getAuthors();
					bookList = InitialData.getBooks();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAuthor = null;
				PreparedStatement insertBook   = null;

				try {
					// populate authors table (do authors first, since author_id is foreign key in books table)
					insertAuthor = conn.prepareStatement("insert into authors (lastname, firstname) values (?, ?)");
					for (Author author : authorList) {
//						insertAuthor.setInt(1, author.getAuthorId());	// auto-generated primary key, don't insert this
						insertAuthor.setString(1, author.getLastname());
						insertAuthor.setString(2, author.getFirstname());
						insertAuthor.addBatch();
					}
					insertAuthor.executeBatch();
					
					// populate books table (do this after authors table,
					// since author_id must exist in authors table before inserting book)
					insertBook = conn.prepareStatement("insert into books (author_id, title, isbn, published) values (?, ?, ?, ?)");
					for (Book book : bookList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
						insertBook.setInt(1, book.getAuthorId());
						insertBook.setString(2, book.getTitle());
						insertBook.setString(3, book.getIsbn());
						insertBook.setInt(4,  book.getPublished());
						insertBook.addBatch();
					}
					insertBook.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertBook);
					DBUtil.closeQuietly(insertAuthor);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Success!");
	}
}