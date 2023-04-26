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

import FakeDatabase.InitialData;
import LocationModel.AverageSalary;
import LocationModel.CrimeRate;
import LocationModel.Location;
import UserModel.PopularLocations;
import UserModel.SavedLocations;
import UserModel.Users;




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
	
	
	//validate a user login
	@Override
	public boolean Login(String Username, String Password) throws SQLException {
		
		boolean UserExists;
		
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

				
					
					
				try {
					// connect to the database
					conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
				}
				catch(SQLException e) {
					System.err.println("Bad Connection");
				}
		
		try {
		
			stmt = conn.prepareStatement(
					"select Username, Password" +
							" from UserDatabase"+
							" where Username = ? and Password = ?"
			);
			stmt.setString(1, Username);
			stmt.setString(2, Password);
			
			
			resultSet = stmt.executeQuery();
			//if it has a result
			if(resultSet.next()) {
				UserExists = true;
			}
			else {
				UserExists = false;
			}
			
			
			
			return UserExists;
		} finally {
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
		}
	}
	
	@Override
	public boolean CreateUser(String Username, String Password) throws SQLException {
		
		boolean UserExists = Login(Username, Password);
		
		//check to see if the user already exists
		if(UserExists == true) {
			return true;
		}
		//User does not exist, so add them to the UserDatabase table
		else {
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

			
				
				
			try {
				// connect to the database
				conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
			}
			catch(SQLException e) {
				System.err.println("Bad Connection");
			}
	
			try {
	
				stmt = conn.prepareStatement(
						"insert into UserDatabase (Username, Password) values (?, ?)"
						);
				stmt.setString(1, Username);
				stmt.setString(2, Password);
		
		
				boolean executed = stmt.execute();
				//if it has a result

				
		
		
				return executed;
	} finally {
		DBUtil.closeQuietly(resultSet);
		DBUtil.closeQuietly(stmt);
	}
		}
		
	}
	//returns list of popular locations and all of their values
	@Override
	public List<PopularLocations> ViewPopularLocatons() throws SQLException {
		
		List<PopularLocations> PopularLocs = new ArrayList<PopularLocations>();
		
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

		
			
			
		try {
			// connect to the database
			conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		}
		catch(SQLException e) {
			System.err.println("Bad Connection");
		}

		try {

			stmt = conn.prepareStatement(
					"select * from PopularLocations"
					);
			
			resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				PopularLocations PopLoc = new PopularLocations();
				
				PopLoc.setZipcode(resultSet.getString(1));
				
				PopLoc.setNumberOfSaves(resultSet.getInt(2));
				
				
				
				
				
				PopularLocs.add(PopLoc);
			}
			//ensures that it is returned with the location with the most saves at the front of the list, and descends down after each index
			PopularLocs.sort(new PopLocComparator());
	
	
		
		} finally {
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
		}
			return PopularLocs;
		}
	
	
	@Override
	public List<String> ViewSavedLocations(String Username) throws SQLException {
		
		
		List<String> SavedLocs = new ArrayList<String>();
		
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

		
			
			
		try {
			// connect to the database
			conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		}
		catch(SQLException e) {
			System.err.println("Bad Connection");
		}

		try {

			stmt = conn.prepareStatement(
					"select * from SavedLocations" +
					" where Username = ?"
					);
			stmt.setString(1, Username);
			
			resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				
				
				SavedLocs.add(resultSet.getString(2));
				
			}

			
	
	
		
		} finally {
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
		}
		
		return SavedLocs;
			
	}
	//need to insert into SavedLocations, and popular Locations
	@Override
	public boolean SaveLocation(String Username, String Zipcode) throws SQLException {
		// load Derby JDBC driver
				
			int NumberOfSaves = 0;
			try {
					Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				} catch (Exception e) {
					System.err.println("Could not load Derby JDBC driver");
					System.err.println(e.getMessage());
					System.exit(1);
				}
				Connection conn = null;
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				ResultSet resultSet = null;
				ResultSet resultSet1 = null;

					
						
						
				try {
					// connect to the database
					conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
				}
				catch(SQLException e) {
					System.err.println("Bad Connection");
				}
			
				try {
					//should check to see if its already there
					stmt1 = conn.prepareStatement("select Zipcode from SavedLocations where Username = ? and Zipcode = ?");
					stmt1.setString(1, Username);
					stmt1.setString(2, Zipcode);
					
					resultSet1 = stmt1.executeQuery();
					
					
					//if the user already has this saved
					if(resultSet1.next() == true) {
						return false;
					}
					
					
					
					//first save into their saved locations
					stmt = conn.prepareStatement(
						"insert into SavedLocations (Username, Zipcode) values (?, ?)"
						);
					stmt.setString(1, Username);
					stmt.setString(2, Zipcode);
					
					
					
					
					
					
					//was boolean executed = 
					stmt.execute();
					/*
					if(executed == false) {
						return false;
					}
					*/
				//	else {
						stmt2 = conn.prepareStatement(
								"select NumberOfSaves from PopularLocations" +
								" where Zipcode = ? "
								);
							stmt2.setString(1, Zipcode);
							
							resultSet = stmt2.executeQuery();
							
							if(resultSet.next()) {
								NumberOfSaves = resultSet.getInt(1);
							}
							NumberOfSaves += 1;
							
							stmt3 = conn.prepareStatement(
									"insert into PopularLocations (Zipcode, NumberOfSaves) values (?, ?) "
									
									);
							
							
							//need to refine this, because doing this will create multiple zipcodes with differn number of saves values
							stmt3.setString(1, Zipcode);
							stmt3.setInt(2, NumberOfSaves);
							
							stmt3.execute();
				//	}

					
					
					return true;
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
			}
		
		
		
		
		
		
		
	
	}
	
	//need to reimplement this function for Locations to load a location
	/*
	private void loadAuthor(Author author, ResultSet resultSet, int index) throws SQLException {
		author.setAuthorId(resultSet.getInt(index++));
		author.setLastname(resultSet.getString(index++));
		author.setFirstname(resultSet.getString(index++));
	}
	*/
	
	//need to reimplement this function for all of our tables 
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				//this will create the table used for storing our users username and password
				try {
					
					stmt1 = conn.prepareStatement(
						"create table UserDatabase (" +
						" Username varchar(40)," +
						" Password varchar(40) "+
						")"
					);	
					stmt1.executeUpdate();
					//this will create a table for all user saved locations
					stmt2 = conn.prepareStatement(
							"create table SavedLocations (" +
							" Username varchar(40)," +
							" Zipcode varchar(20) "
							+")"
					);
					stmt2.executeUpdate();
					//this will create the popular locations table which will save the amount of times each location is saved, for each location
					stmt3 = conn.prepareStatement(
							"create table PopularLocations (" +
							" Zipcode varchar(20), "+
							" NumberOfSaves int " +
							")"
						);
					stmt3.executeUpdate();
					//create AverageSalary
					stmt4 = conn.prepareStatement(
						"create table AverageSalary (" +
						" Scale int, " +
						" AvgSalaryPerHousehold int "
						+")"
					);
					stmt4.executeUpdate();
					//create CrimeRate table
					stmt5 = conn.prepareStatement(
						"create table CrimeRate (" +
							" Scale int, " +
							" RatePerHundredThousand int "
							+")"
						);
					stmt5.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
				}
			}
		});
	}
	
	//need to reimplement this for all of our tables
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Location> LocationList;
				List<Users> UsersList;
				List<SavedLocations> SavedLocationsList;
				List<PopularLocations> PopularLocationsList;
				List<AverageSalary> AverageSalaryList;
				List<CrimeRate> CrimeRateList;
				
				
				
				try {
					LocationList = InitialData.getLocations();
					UsersList = InitialData.getUsers();
					SavedLocationsList = InitialData.getSavedLocations();
					PopularLocationsList = InitialData.getPopularLocations();
					AverageSalaryList = InitialData.getAverageSalary();
					CrimeRateList = InitialData.getCrimeRate();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertUser = null;
				PreparedStatement insertSavedLocation = null;
				PreparedStatement insertPopularLocation  = null;
				PreparedStatement insertAverageSalary = null;
				PreparedStatement insertCrimeRate = null;

				try {
					// populate UserDatabase table
					insertUser = conn.prepareStatement("insert into UserDatabase (Username, Password) values (?, ?)");
					for (Users user : UsersList) {
						
						insertUser.setString(1, user.getUsername());
						insertUser.setString(2, user.getPassword());
						insertUser.addBatch();
					}
					insertUser.executeBatch();
					
					// populate SavedLocations table
					insertSavedLocation = conn.prepareStatement("insert into SavedLocations (Username, Zipcode) values (?, ?)");
					for (SavedLocations SavedLoc : SavedLocationsList) {
//						
						insertSavedLocation.setString(1, SavedLoc.getUsername());
						insertSavedLocation.setString(2, SavedLoc.getZipcode());
						insertSavedLocation.addBatch();
					}
					insertSavedLocation.executeBatch();
					
					
					// populate PopularLocations table
					insertPopularLocation = conn.prepareStatement("insert into PopularLocations (Zipcode, NumberOfSaves) values (?, ?)");
					for (PopularLocations PopularLoc : PopularLocationsList) {
//						
						insertPopularLocation.setString(1, PopularLoc.getZipcode());
						insertPopularLocation.setInt(2, PopularLoc.getNumberOfSaves());
						insertPopularLocation.addBatch();
					}
					insertPopularLocation.executeBatch();
					
					
					
					// populate AveragSalary table
					insertAverageSalary = conn.prepareStatement("insert into AverageSalary (Scale, AvgSalaryPerHousehold) values (?, ?)");
					for (AverageSalary AvgSal : AverageSalaryList) {
//						
						insertAverageSalary.setInt(1, AvgSal.getScale());
						insertAverageSalary.setInt(2, AvgSal.getAvgSalaryPerHousehold());
						insertAverageSalary.addBatch();
					}
					insertAverageSalary.executeBatch();
					
					
					// populate CrimeRate table
					insertCrimeRate = conn.prepareStatement("insert into CrimeRate (Scale, RatePerHundredThousand) values (?, ?)");
					for (CrimeRate CR : CrimeRateList) {
//						
						insertCrimeRate.setInt(1, CR.getScale());
						insertCrimeRate.setInt(2, CR.getRatePerHundredThousand());
						insertCrimeRate.addBatch();
					}
					insertCrimeRate.executeBatch();
					
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertUser);
					DBUtil.closeQuietly(insertSavedLocation);
					DBUtil.closeQuietly(insertPopularLocation);
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
