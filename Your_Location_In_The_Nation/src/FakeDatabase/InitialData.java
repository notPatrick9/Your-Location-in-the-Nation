package FakeDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import LocationModel.Location;
import UserModel.PopularLocations;
import UserModel.SavedLocations;
import UserModel.Users;

//need to implement later
public class InitialData {
	//Get initial data from Locations.csv
	public static List<Location> getLocations() throws IOException {
		List<Location> LocationList = new ArrayList<Location>();
		ReadCSV readLocations = new ReadCSV("Locations.csv");
		try {
			
			while (true) {
				List<String> tuple = readLocations.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Location Loc = new Location();
				Loc.setCity(i.next());
				Loc.setCounty(i.next());
				Loc.setState(i.next());
				Loc.setZipcode(i.next());
				Loc.setAvgSalaryPerHouse(Integer.parseInt(i.next()));
				Loc.setCostOfLiving(Integer.parseInt(i.next()));
				Loc.setCrimeRate(Integer.parseInt(i.next()));
				
				LocationList.add(Loc);
			}
			return LocationList;
		} finally {
			readLocations.close();
		}
	}
	//This will get intial data from the UserDatabase
	public static List<Users> getUsers() throws IOException {
		List<Users> UserList = new ArrayList<Users>();
		ReadCSV readUsers = new ReadCSV("UserDatabase.csv");
		try {
			
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Users User = new Users();
				User.setUsername(i.next());
				User.setPassword(i.next());
				
				
				UserList.add(User);
			}
			return UserList;
		} finally {
			readUsers.close();
		}
		
	}
	//this function will get all of the initial data from the SavedLocations csv file
	public static List<SavedLocations> getSavedLocations() throws IOException {
		
		List<SavedLocations> SavedLocationsList = new ArrayList<SavedLocations>();
		ReadCSV readSavedLocations = new ReadCSV("SavedLocations.csv");
		try {
			
			while (true) {
				List<String> tuple = readSavedLocations.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				SavedLocations SavedLoc = new SavedLocations();
				SavedLoc.setUsername(i.next());
				SavedLoc.setZipcode(i.next());
				
				
				SavedLocationsList.add(SavedLoc);
			}
			return SavedLocationsList;
		} finally {
			readSavedLocations.close();
		}
		
	}
	
	public static List<PopularLocations> getPopularLocations() throws IOException {
	
		
		List<PopularLocations> PopularLocationsList = new ArrayList<PopularLocations>();
		ReadCSV readPopularLocations = new ReadCSV("PopularLocations.csv");
		try {
			
			while (true) {
				List<String> tuple = readPopularLocations.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				PopularLocations PopularLoc = new PopularLocations();
				PopularLoc.setZipcode(i.next());
				PopularLoc.setNumberOfSaves(Integer.parseInt(i.next()));
				
				
				PopularLocationsList.add(PopularLoc);
			}
			return PopularLocationsList;
		} finally {
			readPopularLocations.close();
		}
		
	}
}
