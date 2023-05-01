package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.LoginController;
import DatabasePersist.DerbyDatabase;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DerbyDatabase database;

	@Override
	public void init() throws ServletException {
		super.init();
		DerbyDatabase database = new DerbyDatabase();
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Login Servlet: doGet");
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Login Servlet: doPost");
		// holds the error message text, if there is any
		String errorMessage = null;
		// retrieve the values of the "Username" and "Password" parameters
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		boolean created = true;
		DerbyDatabase database = new DerbyDatabase();
		
		
		
		if(req.getParameter("login") != null) {
			//boolean varibale for logging in
			boolean validLogin = false;
			LoginController controller = new LoginController();
			
			

			

			// TODO: check if the username and password are valid
			
			try {
				
				System.out.print(username + " " + password);
				
				
				validLogin = controller.LoginToDatabase(username, password);
				if (validLogin == false) {
					
					errorMessage = "Invalid username or password";
					System.out.print("Login failed");
				} 
			} catch (SQLException e) {
			
				e.printStackTrace();
				System.out.print("Failed to validate login cause of database error");
			}

			// Add parameters as request attributes
			req.setAttribute("Username", username);
			req.setAttribute("Password", password);

			// this adds the errorMessage text to the response
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("login", validLogin);
			// if login is valid, start a session
			
			if (validLogin == true) {
				System.out.println("Valid login - starting session, redirecting to /index");

				// store user object in session
				req.getSession().setAttribute("user", username);

				// redirect to /index page
				resp.sendRedirect(req.getContextPath() + "/index");

				return;
			}
		}
		
		
		
		/////////////////////////////////////////////
		
		
		else if(req.getParameter("createuser") != null) {
			
			
			if(username == null || password == null) {
				errorMessage = "Must specify Username and Password";
			}
			
			
			
			else {
				try {
					created = database.CreateUser(username, password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//if not created (cause for this true means not created, false means it did create a new user
				if(created == true) {
					errorMessage = "User already exists";
				}
				
				// Add parameters as request attributes
				req.setAttribute("Username", username);
				req.setAttribute("Password", password);

				// this adds the errorMessage text to the response
				req.setAttribute("errorMessage", errorMessage);
				
				
				
				
				if(created == false) {
					// store user object in session
					req.getSession().setAttribute("user", username);

					// redirect to /index page
					resp.sendRedirect(req.getContextPath() + "/index");

					return;
				}
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
}


