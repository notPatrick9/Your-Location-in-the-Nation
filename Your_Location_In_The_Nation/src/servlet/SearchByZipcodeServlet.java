package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabasePersist.DerbyDatabase;
import LocationModel.Location;
import UserModel.PopularLocations;

public class SearchByZipcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DerbyDatabase database;

	@Override
	public void init() throws ServletException {
		super.init();
		database = new DerbyDatabase();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SearchByZipcode Servlet: doGet");
	        

	    
		
		
		
		
		req.getRequestDispatcher("/_view/ViewZipInfo.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Popularlocations Servlet: doPost");
		//needs database attribute
		String errorMessage;
    	String Zipcode = req.getParameter("Zipcode");
        Location Location = null;
		try {
			Location = database.viewZipcodeinfo(Zipcode);
			 //System.out.print("PopLoc" + PopularLocations.get(0).getNumberOfSaves());
			  
		     req.setAttribute("Location", Location);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

		if(Location == null) {
			errorMessage = "No Location in our database matched that zipcode";
		}
		else {
			req.setAttribute("Location", Location);
		}
	       
	    
		req.getRequestDispatcher("/_view/ViewZipInfo.jsp").forward(req, resp);
	    
	}
}
