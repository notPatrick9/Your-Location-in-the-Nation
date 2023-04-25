package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabasePersist.DerbyDatabase;

//needs database import


public class SavepageServlet extends HttpServlet {
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
		req.getRequestDispatcher("/_view/SavePage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Savepage Servlet: doPost");

		 String username = (String) req.getSession().getAttribute("Username");

		  //needs database attribute
		    List<String> SavedAreas;
		    try {
		        SavedAreas = database.(Username);
		    } catch (SQLException e) {
		        throw new ServletException("Error retrieving saved areas from database", e);
		    }

		    
		    req.setAttribute("savedAreas", SavedAreas);

		   
		    req.getRequestDispatcher("/_view/SavePage.jsp").forward(req, resp);
		}
}
