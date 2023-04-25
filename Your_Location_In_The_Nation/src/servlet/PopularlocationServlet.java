package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabasePersist.DerbyDatabase;
//needs popular location import


public class PopularlocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DerbyDatabase database;

	@Override
	public void init() throws ServletException {
		super.init();
		database = new DerbyDatabase();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Popularlocation Servlet: doGet");
		req.getRequestDispatcher("/_view/Popularlocations.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Popularlocations Servlet: doPost");


	    try {
	
	    	//needs database attribute
	    	
	        List<PopularLocation> PopularLocations = database.();

	  
	        req.setAttribute("PopularLocations", PopularLocations);

	    
	        req.getRequestDispatcher("/_view/Popularlocations.jsp").forward(req, resp);

	    } catch (SQLException e) {
	        e.printStackTrace();
	       
	    }
	}
}
