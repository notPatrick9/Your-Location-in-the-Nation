import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import DatabasePersist.DerbyDatabase;


public class testLogin {
	DerbyDatabase data = new DerbyDatabase();
	
	@Before
	public void setUp() {
		//DerbyDatabase data = new DerbyDatabase();
	}
	
	@Test
	public void testWithRealUser() throws SQLException {
		
		
		boolean User = data.Login("BillyBob", "1234");
		
		assertTrue(User);
	}
	/*
	@Test
	public void testWithRealUserSpelledWrong() throws SQLException {
		
		
		boolean User = data.Login("billybob", "1234");
		
		assertFalse(User);
	}
	@Test
	public void testWithFakeUser() throws SQLException {
		
		
		boolean User = data.Login("Ryan", "Curry");
		
		assertFalse(User);
	}
	*/
	
}
