package DatabasePersist;

import java.util.Comparator;

import UserModel.PopularLocations;

public class PopLocComparator implements Comparator<PopularLocations> {
	//will return by number of saves in descending order
	@Override
	public int compare(PopularLocations P1, PopularLocations P2) {
		return P2.getNumberOfSaves() - P1.getNumberOfSaves();
	}

}
