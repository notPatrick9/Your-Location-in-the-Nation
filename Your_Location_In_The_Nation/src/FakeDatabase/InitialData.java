package FakeDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import LocationModel.Location;


//need to implement later
public class InitialData {
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
}
