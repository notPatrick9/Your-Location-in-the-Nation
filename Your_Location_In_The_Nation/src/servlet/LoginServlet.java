package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabasePersist.DerbyDatabase;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DerbyDatabase database;

	@Override
	public void init() throws ServletException {
		super.init();
		database = new DerbyDatabase();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Login Servlet: doGet");
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Login Servlet: doPost");

		// retrieve the values of the "Username" and "Password" parameters
		String username = req.getParameter("Username");
		String password = req.getParameter("Password");

		// holds the error message text, if there is any
		String errorMessage = null;

		// TODO: check if the username and password are valid
		
		try {
			if (database.Login(username, password)) {
				// redirect the user to the home page after successful login
				resp.sendRedirect(req.getContextPath() + "/welcomeG");
				return;
			} else {
				// display an error message to the user
				errorMessage = "Invalid username or password";
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		// Add parameters as request attributes
		req.setAttribute("Username", username);
		req.setAttribute("Password", password);

		// this adds the errorMessage text to the response
		req.setAttribute("errorMessage", errorMessage);

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
}

