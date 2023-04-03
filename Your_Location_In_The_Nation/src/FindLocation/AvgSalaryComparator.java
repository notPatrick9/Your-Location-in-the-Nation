package FindLocation;
import java.util.Comparator;

import LocationModel.Location;
public class AvgSalaryComparator implements Comparator<Location>{
	
	
	@Override
	//ascending order
	public int compare(Location L1, Location L2) {
		return L1.getAvgSalaryPerHouse() - L2.getAvgSalaryPerHouse();
	}

	
}
