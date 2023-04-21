import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DatabasePersist.DerbyDatabase;
import UserModel.PopularLocations;

public class testViewPopularLocation {
DerbyDatabase data = new DerbyDatabase();
	
	@Before
	public void setUp() {
		//DerbyDatabase data = new DerbyDatabase();
	}
	//for these tests, false means it did create a new user, true means that it did not
	@Test
	public void DisplayPopularLocations() throws SQLException {
		
		List<PopularLocations> PopLocs = data.ViewPopularLocatons();
		
		assertNotNull(PopLocs);
		
		for(PopularLocations Loc: PopLocs) {
			System.out.print(Loc.getZipcode() +"\n");
			System.out.print(Loc.getNumberOfSaves() + "\n");
		}
		
		
		
	
	}
	
}
