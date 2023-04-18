package FakeDatabase;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import LocationModel.Location;



public class FakeData {
	private List<Location> LocationList;
	
	
	public FakeData() {
		LocationList = new ArrayList<Location>();
		
		
		// Add initial data
		readInitialData();
		
		System.out.println(LocationList.size() + " Locations");
		
	}

	public void readInitialData() {
		try {
			LocationList.addAll(InitialData.getLocations());
			
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
	
	public ArrayList<Location> getLocationList() {
		return (ArrayList<Location>) LocationList;
	}
	
}
