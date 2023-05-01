import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import DatabasePersist.DerbyDatabase;

public class testSaveLocation {
	DerbyDatabase data = new DerbyDatabase();
	
	@Before
	public void setUp() {
		//DerbyDatabase data = new DerbyDatabase();
	}
	
	@Test
	public void SaveNewLocation() throws SQLException {
		
		
		boolean Saved = data.SaveLocation("BillyBob", "17347");
		
		assertTrue(Saved);
	}
	
	@Test
	public void SaveOldLocation() throws SQLException {
		
		
		boolean Saved = data.SaveLocation("BarneyD", "17302");
		
		assertFalse(Saved);
	}
	
	
}
