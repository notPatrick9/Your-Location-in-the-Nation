package FindLocation;
import java.util.Comparator;

import LocationModel.Location;
public class CostOfLivingOwnWithMortgageComparator implements Comparator<Location> {
	@Override
	//descending order
	public int compare(Location L1, Location L2) {
		return (int)(L2.getCostOfLivingOwnWithMortgage() - L1.getCostOfLivingOwnWithMortgage());
	}
}
