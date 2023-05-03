package FindLocation;
import java.util.Comparator;

import LocationModel.Location;
public class CostOfLivingRentComparator implements Comparator<Location> {
	@Override
	//descending order
	public float compare(Location L1, Location L2) {
		return L2.getCostOfLivingRent() - L1.getCostOfLivingRent();
	}
}
