package UserModel;
//the model for the UserDatabase table
public class Users {
	
	private String Username;
	private String Password;
	
	public Users() {
		
	}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
