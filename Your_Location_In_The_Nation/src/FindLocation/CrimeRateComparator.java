package FindLocation;
import java.util.Comparator;

import LocationModel.Location;
public class CrimeRateComparator implements Comparator<Location> {
	@Override
	//descending order
	public int compare(Location L1, Location L2) {
		return L2.getCrimeRate() - L1.getCrimeRate();
	}
}
