import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DatabasePersist.DerbyDatabase;
import UserModel.PopularLocations;

public class testViewSavedLocations {
	DerbyDatabase data = new DerbyDatabase();
	
	@Before
	public void setUp() {
		//DerbyDatabase data = new DerbyDatabase();
	}
	//for these tests, false means it did create a new user, true means that it did not
	@Test
	public void DisplaySavedLocations() throws SQLException {
		
		List<String> SavedLocs = data.ViewSavedLocations("BarneyD");
		
		assertNotNull(SavedLocs);
		
		for(int i = 0; i < SavedLocs.size(); i++) {
			System.out.print(SavedLocs.get(i) + "\n");
			
	}
		
		
		
	
	}
}
