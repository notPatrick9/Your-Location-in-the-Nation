package DatabasePersist;

import java.sql.SQLException;
import java.util.List;

import LocationModel.Location;
import UserModel.PopularLocations;
import UserModel.SavedLocations;

//This interface will include all of the functions we use for our database operations

public interface IDatabase {
	
	public boolean Login(String Username, String Password) throws SQLException;
	
	public boolean CreateUser(String Username, String Password) throws SQLException;
	
	public List<PopularLocations> ViewPopularLocatons() throws SQLException;
	//function for viewing saved locations for a user
	public List<String> ViewSavedLocations(String Username) throws SQLException;
	
	//for each loop for saved location and popular location
	
	//function for saving a location(will also save into popular location

	public boolean SaveLocation(String Username, String Zipcode) throws SQLException;
	
	
	public Location viewZipcodeinfo(String Zipcode) throws SQLException;
	
	public List<String> getZipcodesForAreaName(String Name) throws SQLException;
	
	
	public Location getLocation(int Income, float costOfliving, int CrimeRate, int CostOfLivingType, int mostImportantUserFact) throws SQLException;
	
	
	
	
}
