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
		System.out.println("SearchByZipcode Servlet: doPost");
		//needs database attribute
		String errorMessage;
    	String Zipcode = req.getParameter("Zipcode");
        Location Location = null;
		//if user clicks submit
        if(req.getParameter("submit") != null) {
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
    			System.out.print(errorMessage);
    			req.setAttribute("errorMessage", errorMessage);
    		}
    		else {
    			req.setAttribute("Location", Location);
    			req.setAttribute("avgsal", Location.getAvgSalaryPerHouse());
    		}
    	       
    	    
    		req.getRequestDispatcher("/_view/ViewZipInfo.jsp").forward(req, resp);
        	
        	
        }
        
        //if the user clicks to go back to the index
        else if(req.getParameter("index") != null) {
        	resp.sendRedirect(req.getContextPath() + "/index");
        	return;
        }
        
        
        
	    
	}
}
