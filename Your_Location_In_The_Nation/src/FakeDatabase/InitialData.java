package FakeDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import LocationModel.AverageSalary;
import LocationModel.CostOfLiving;
import LocationModel.CrimeRate;
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
				Loc.setCostOfLivingRent(Float.parseFloat(i.next()));
				Loc.setCostOfLivingOwnWithMortgage(Float.parseFloat(i.next()));
				Loc.setCostOfLivingOwnNoMortgage(Float.parseFloat(i.next()));
				Loc.setCrimeRate(Integer.parseInt(i.next()));
				Loc.setRegion(i.next());
				Loc.setPopulation(i.next());
				
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
	//function for getting all attributes from CrimeRate
public static List<CrimeRate> getCrimeRate() throws IOException {
	
		
		List<CrimeRate> CrimeRateList = new ArrayList<CrimeRate>();
		ReadCSV readCrimeRate = new ReadCSV("CrimeRateScales.csv");
		try {
			
			while (true) {
				List<String> tuple = readCrimeRate.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				CrimeRate CR = new CrimeRate();
				CR.setScale(Integer.parseInt(i.next()));
				CR.setRatePerHundredThousand(Integer.parseInt(i.next()));
				
				
				CrimeRateList.add(CR);
			}
			return CrimeRateList;
		} finally {
			readCrimeRate.close();
		}
		
	}
//function for getting all attributes for AverageSalary table
public static List<AverageSalary> getAverageSalary() throws IOException {
	
	
	List<AverageSalary> AverageSalaryList = new ArrayList<AverageSalary>();
	ReadCSV readAveragSalary = new ReadCSV("AverageSalaryHouseholdScales.csv");
	try {
		
		while (true) {
			List<String> tuple = readAveragSalary.next();
			if (tuple == null) {
				break;
			}
			Iterator<String> i = tuple.iterator();
			AverageSalary AvgSal = new AverageSalary();
			AvgSal.setScale(Integer.parseInt(i.next()));
			AvgSal.setAvgSalaryPerHousehold(Integer.parseInt(i.next()));
			
			
			AverageSalaryList.add(AvgSal);
		}
		return AverageSalaryList;
	} finally {
		readAveragSalary.close();
	}
	
}


public static List<CostOfLiving> getCostOfLiving() throws IOException {
	
	
	List<CostOfLiving> CostOfLivingList = new ArrayList<CostOfLiving>();
	ReadCSV readCostOfLiving = new ReadCSV("CostOfLivingScales.csv");
	try {
		
		while (true) {
			List<String> tuple = readCostOfLiving.next();
			if (tuple == null) {
				break;
			}
			Iterator<String> i = tuple.iterator();
			CostOfLiving COL = new CostOfLiving();
			COL.setScale(Integer.parseInt(i.next()));
			COL.setCostOfLivingIndex(Integer.parseInt(i.next()));
			
			
			CostOfLivingList.add(COL);
		}
		return CostOfLivingList;
	} finally {
		readCostOfLiving.close();
	}
	
}


}
