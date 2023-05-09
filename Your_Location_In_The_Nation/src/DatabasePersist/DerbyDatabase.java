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
import LocationModel.CostOfLiving;
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
	
	
	// Getting a failed transaction here
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
			PreparedStatement stmt1 = null;
			ResultSet resultSet = null;

			
				
				
			try {
				// connect to the database
				conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
			}
			catch(SQLException e) {
				System.err.println("Bad Connection");
			}
	
			try {
				
				
				boolean UserExists = false;
				
				//check to see if the user already exists
				
				
				stmt1 = conn.prepareStatement("select Username"
						+ "	from UserDatabase"
						+ "	where Username = ?");
				
				stmt1.setString(1, Username);
				
				resultSet = stmt1.executeQuery();
				
				if(resultSet.next()) {
					UserExists = true;
				}
				
				if(UserExists == true) {
					return true;
				}
				//User does not exist, so add them to the UserDatabase table
				
				
				
				
				
	
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
					"select * from PopularLocations "
					+ " order by NumberOfSaves DESC"
					);
			
			resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				PopularLocations PopLoc = new PopularLocations();
				
				PopLoc.setZipcode(resultSet.getString(1));
				
				PopLoc.setNumberOfSaves(resultSet.getInt(2));
				
				
				
				
				
				PopularLocs.add(PopLoc);
			}
			//ensures that it is returned with the location with the most saves at the front of the list, and descends down after each index
			//PopularLocs.sort(new PopLocComparator());
	
	
		
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
	
	@Override
	public Location viewZipcodeinfo(String Zipcode) throws SQLException {
		
		Location Location = new Location();
		
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
		//for the query there are placeholder names for now where the real database column names will be 
		try {

			stmt = conn.prepareStatement(
					"select Name, County, State, Zip, Income, Rent, Mortgage, NoMortgage, CrimeRate, Region, Population "
					+ " from LocationsDatabase"
					+ " where Zip = ?"
					);
			stmt.setString(1, Zipcode);
			
			resultSet = stmt.executeQuery();
			
			if (resultSet.next()) {
				//these are also placeholders here until the location model class gets updated
				
				//name
				Location.setLocationName(resultSet.getString(1));
				//County
				Location.setCounty(resultSet.getString(2));
				//State
				Location.setState(resultSet.getString(3));
				//Income
				Location.setZipcode(resultSet.getString(4));
				//Rent
				Location.setAvgSalaryPerHouse(resultSet.getInt(5));
				//Mortgage
				Location.setCostOfLivingRent(resultSet.getInt(6));
				//No mortgage
				Location.setCostOfLivingOwnWithMortgage(resultSet.getInt(7));
				//CrimeRate
				Location.setCostOfLivingOwnNoMortgage(resultSet.getInt(8));
				//Region
				Location.setCrimeRate(resultSet.getInt(9));
				//Population
				Location.setRegion(resultSet.getString(10));
				
				Location.setPopulation(resultSet.getInt(11));
			}
<<<<<<< HEAD
=======
			//test for now
			else {
				Location = null;
			}

			
	
	
>>>>>>> refs/remotes/Ryan/master
		
		} finally {
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
		}
		
		return Location;
	}
	
	//for later
	//might implement this as a separate page, or possibly as a drop down on the index page
	@Override
	public List<String> getZipcodesForAreaName(String Name) throws SQLException {
		List<String> Zipcodes = new ArrayList<String>();
		
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
		//for the query there are placeholder names for now where the real database column names will be 
		try {

			stmt = conn.prepareStatement(
					"select Zip "
					+ " from LocationsDatabase "
					+ " where Name = ?"
					);
			stmt.setString(1, Name);
			
			resultSet = stmt.executeQuery();
			
			
			
			
			
			while (resultSet.next()) {
				//should add all zipcodes for the area into the list
				Zipcodes.add(resultSet.getString(1));
			}
			
			

			
	
	
		
		} finally {
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
		}
		
		return Zipcodes;
		
		
		
	}
	
<<<<<<< HEAD
=======
	@SuppressWarnings("resource")
	@Override
	public Location getLocation(int Income, float costOfliving, int CrimeRate, int CostOfLivingType, int mostImportantUserFact) throws SQLException {
		List<Location> Locations = new ArrayList<Location>();
		Location bestLoc = null;
		String costOfLivingQuery = null;
		String UserFactor = null;
		String orderBy = null;
		
		
		
		
		
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
		//for the query there are placeholder names for now where the real database column names will be 
		try {
			String orderby = null;
			//income
			if(mostImportantUserFact == 0) {
				orderby = "order by Income ASC";
				}
			//COL
			else if(mostImportantUserFact == 1) {
				//Rent
				if(CostOfLivingType == 0) {
					orderby = "order by Rent DESC";
				}
				//Mortgage
				else if(CostOfLivingType == 1) {
					orderby = "order by Mortgage DESC";
				}
				else {
					orderby = "order by NoMortgage DESC";
				}
					
				
			}
			//CrimeRate
			else if(mostImportantUserFact == 2) {
				
				orderby = "order by CrimeRate DESC";
						
			}
			
			
			
			
			//query with cost of living types
			//Rent
			if(CostOfLivingType == 0) {
				stmt = conn.prepareStatement(
						"select Name, County, State, Zip, Income, Rent, Mortgage, NoMortgage, CrimeRate, Region, Population "
						+ " from LocationsDatabase "
						+ " where Income > ? and Rent < ? and CrimeRate < ? " +
						orderby
						);
				
				stmt.setInt(1, Income);
				
				stmt.setFloat(2, costOfliving);
				stmt.setInt(3, CrimeRate);
				
				
				
				
			}
			//Mortgage
			else if(CostOfLivingType == 1) {
				stmt = conn.prepareStatement(
						"select Name, County, State, Zip, Income, Rent, Mortgage, NoMortgage, CrimeRate, Region, Population "
						+ " from LocationsDatabase "
						+ " where Income > ? and Mortgage < ? and CrimeRate < ? "
						+ orderby
						);
				
				stmt.setInt(1, Income);
				
				stmt.setFloat(2, costOfliving);
				stmt.setInt(3, CrimeRate);
				
				
				
			}
			//NoMortgage
			else if(CostOfLivingType == 2) {
				stmt = conn.prepareStatement(
						"select Name, County, State, Zip, Income, Rent, Mortgage, NoMortgage, CrimeRate, Region, Population "
						+ " from LocationsDatabase "
						+ " where Income > ? and NoMortgage < ? and CrimeRate < ? " 
						+ orderby
						);
				
				stmt.setInt(1, Income);
				
				stmt.setFloat(2, costOfliving);
				stmt.setInt(3, CrimeRate);
				
				
				
			}
			
			//add orderby statement for most important user factor
			
		
			
			
			
			//execute the query
			resultSet = stmt.executeQuery();
			
			
			
			while (resultSet.next()) {
				//these are also placeholders here until the location model class gets updated
				Location Location = new Location();
				//name
				Location.setLocationName(resultSet.getString(1));
				//County
				Location.setCounty(resultSet.getString(2));
				//State
				Location.setState(resultSet.getString(3));
				//Income
				Location.setZipcode(resultSet.getString(4));
				//Rent
				Location.setAvgSalaryPerHouse(resultSet.getInt(5));
				//Mortgage
				Location.setCostOfLivingRent(resultSet.getInt(6));
				//No mortgage
				Location.setCostOfLivingOwnWithMortgage(resultSet.getInt(7));
				//CrimeRate
				Location.setCostOfLivingOwnNoMortgage(resultSet.getInt(8));
				//Region
				Location.setCrimeRate(resultSet.getInt(9));
				//Population
				Location.setRegion(resultSet.getString(10));
				
				Location.setPopulation(resultSet.getInt(11));
				
				Locations.add(Location);
				
			}
			
			bestLoc = Locations.get(Locations.size() - 1);
			
			
			
			

			
	
	
		
		} finally {
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
		}
		return bestLoc;
		
		
		
		
		
	}
	
	
	
	
	
	
	
>>>>>>> refs/remotes/Ryan/master
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt0 = null;
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;
				PreparedStatement stmt7 = null;
				PreparedStatement stmt8 = null;
				
				// Create Table for list of locations
				try {
					stmt0 = conn.prepareStatement(
							"create table LocationsDatabase (" +
							" Name varchar(40), " +
							" County varchar(40), " +
							" State varchar(2), " +
							" Zip varchar(5), " +
							" Income int, " +
							" Rent float, " +
							" Mortgage float, " +
							" NoMortgage float, " +
							" CrimeRate int, " +
							" Region varchar(40), " +
							" Population int" +
							")"
					);
					stmt0.executeUpdate();
					
					//this will create the table used for storing our users username and password
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
					
					stmt6 = conn.prepareStatement(
							"create table CostOfLivingRent (" +
								" Scale int, " +
								" CostOfLivingIndex float "
								+")"
							);
					stmt6.executeUpdate();
					
					stmt7 = conn.prepareStatement(
							"create table CostOfLivingMortgage (" +
								" Scale int, " +
								" CostOfLivingIndex float "
								+")"
							);
					stmt7.executeUpdate();
					
					stmt8 = conn.prepareStatement(
							"create table CostOfLivingNoMortgage (" +
								" Scale int, " +
								" CostOfLivingIndex float "
								+")"
							);
					stmt8.executeUpdate();
					

					return true;
				} finally {
					DBUtil.closeQuietly(stmt0);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(stmt7);
					DBUtil.closeQuietly(stmt8);
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
				List<CostOfLiving> CostOfLivingListRent;
				List<CostOfLiving> CostOfLivingListMortgage;
				List<CostOfLiving> CostOfLivingListNoMortgage;
				
				
				try {
					LocationList = InitialData.getLocations();
					UsersList = InitialData.getUsers();
					SavedLocationsList = InitialData.getSavedLocations();
					PopularLocationsList = InitialData.getPopularLocations();
					AverageSalaryList = InitialData.getAverageSalary();
					CrimeRateList = InitialData.getCrimeRate();
					CostOfLivingListRent = InitialData.getCostOfLivingRent();
					CostOfLivingListMortgage = InitialData.getCostOfLivingMortgage();
					CostOfLivingListNoMortgage = InitialData.getCostOfLivingMortgage();
					CostOfLivingListRent = InitialData.getCostOfLivingRent();
					CostOfLivingListMortgage = InitialData.getCostOfLivingMortgage();
					CostOfLivingListNoMortgage = InitialData.getCostOfLivingMortgage();
					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertLocation = null;
				PreparedStatement insertUser = null;
				PreparedStatement insertSavedLocation = null;
				PreparedStatement insertPopularLocation  = null;
				PreparedStatement insertAverageSalary = null;
				PreparedStatement insertCrimeRate = null;
				PreparedStatement insertRent = null;
				PreparedStatement insertMortgage = null;
				PreparedStatement insertNoMortgage = null;
				
				try {
					// populate Locations table
					insertLocation = conn.prepareStatement("insert into LocationsDatabase "
							+ "(Name, County, State, Zip, Income, Rent, Mortgage, NoMortgage, CrimeRate, Region, Population)"
							+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Location loc : LocationList) {
						insertLocation.setString(1, loc.getCity());
						insertLocation.setString(2, loc.getCounty());
						insertLocation.setString(3, loc.getState());
						insertLocation.setString(4, loc.getZipcode());
						insertLocation.setInt(5, loc.getAvgSalaryPerHouse());
						insertLocation.setFloat(6, loc.getCostOfLivingRent());
						insertLocation.setFloat(7, loc.getCostOfLivingOwnWithMortgage());
						insertLocation.setFloat(8, loc.getCostOfLivingOwnNoMortgage());
						insertLocation.setInt(9, loc.getCrimeRate());
						insertLocation.setString(10, loc.getRegion());
						insertLocation.setInt(11, loc.getPopulation());
						insertLocation.addBatch();
					}
					insertLocation.executeBatch();					
					
					// populate UserDatabase table
					insertUser = conn.prepareStatement("insert into UserDatabase (Username, Password) values (?, ?)");
					for (Users user : UsersList) {
//						
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
					
				 	// populate Rent table
					insertRent = conn.prepareStatement("insert into CostOfLivingRent (Scale, CostOfLivingIndex) values (?, ?)");
					for (CostOfLiving C : CostOfLivingListRent) {
//						
						insertRent.setInt(1, C.getScale());
						insertRent.setFloat(2, C.getCostOfLivingIndex());
						insertRent.addBatch();
					}
					insertRent.executeBatch();
					
					// populate Mortgage table
					insertMortgage = conn.prepareStatement("insert into CostOfLivingMortgage (Scale, CostOfLivingIndex) values (?, ?)");
					for (CostOfLiving C : CostOfLivingListMortgage) {
//						
						insertMortgage.setInt(1, C.getScale());
						insertMortgage.setFloat(2, C.getCostOfLivingIndex());
						insertMortgage.addBatch();
					}
					insertMortgage.executeBatch();
					
					// populate NoMortgage table
					insertNoMortgage = conn.prepareStatement("insert into CostOfLivingNoMortgage (Scale, CostOfLivingIndex) values (?, ?)");
					for (CostOfLiving C : CostOfLivingListNoMortgage) {
//						
						insertNoMortgage.setInt(1, C.getScale());
						insertNoMortgage.setFloat(2, C.getCostOfLivingIndex());
						insertNoMortgage.addBatch();
					}
					insertNoMortgage.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertUser);
					DBUtil.closeQuietly(insertSavedLocation);
					DBUtil.closeQuietly(insertPopularLocation);
					DBUtil.closeQuietly(insertLocation);
					DBUtil.closeQuietly(insertAverageSalary);
					DBUtil.closeQuietly(insertCrimeRate);
					DBUtil.closeQuietly(insertRent);
					DBUtil.closeQuietly(insertMortgage);
					DBUtil.closeQuietly(insertNoMortgage);
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
