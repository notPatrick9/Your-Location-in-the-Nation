package Controller;
import java.sql.SQLException;

import DatabasePersist.DerbyDatabase;
public class LoginController {
	public boolean LoginToDatabase(String Username, String Password) throws SQLException {
		boolean validLogin = false;
		DerbyDatabase data = new DerbyDatabase();
		
		validLogin = data.Login(Username, Password);
		
		
		return validLogin;
		
	}
}
