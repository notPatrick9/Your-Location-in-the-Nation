import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import DatabasePersist.DerbyDatabase;

public class testCreateUser {
	DerbyDatabase data = new DerbyDatabase();
	
	@Before
	public void setUp() {
		//DerbyDatabase data = new DerbyDatabase();
	}
	//for these tests, false means it did create a new user, true means that it did not
	@Test
	public void CreateNewUser() throws SQLException {
		
		
		boolean Created = data.CreateUser("Bill", "Nye");
		
		assertFalse(Created);
	}
	//for these tests, false means it did create a new user, true means that it did not
		@Test
		public void AttemptToCreateUserThatExists() throws SQLException {
			
			
			boolean Created = data.CreateUser("Micheal", "Jordan");
			
			assertTrue(Created);
		}
	
	
}
