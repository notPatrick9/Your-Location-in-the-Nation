package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FakeDatabase.FakeData;
import LocationModel.Location;
import ThingsToDo.AboutTheArea;
import DatabasePersist.DerbyDatabase;



public class OutputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String Zipcode;
	
	@Override
    public void init() throws ServletException {
        super.init();
        
        
    }
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Output Servlet: doGet");	
		AboutTheArea about;
		Location bestLoc = null;
		String FunThingsToDo = null;
		System.out.println("Output Servlet: doPost");
		

		// holds the error message text, if there is any
		String errorMessage = null;

		// result of calculation goes here
		Double result = null;
		
		// decode POSTed form parameters and dispatch to controller
		try {
			about = new AboutTheArea();
			bestLoc = (Location) req.getSession().getAttribute("bestLocation");
			System.out.print("BEST LOC: " + bestLoc.getAvgSalaryPerHouse());
			System.out.print("ZIPCODE: " +bestLoc.getZipcode());
			FunThingsToDo = about.getThingsTodo(bestLoc.getZipcode());
			Zipcode = bestLoc.getZipcode();
			
		} catch (Exception e) {
			errorMessage = "Error getting information";
		}
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		
		
		
		//fixes null pointer exception that was being thrown before
		Zipcode = bestLoc.getZipcode();
		
		
		req.setAttribute("CrimeRate", bestLoc.getCrimeRate());
		req.setAttribute("AvgSalary", bestLoc.getAvgSalaryPerHouse());
		//req.setAttribute("CostOfLiving", bestLoc.getCostOfLivingRent());
		req.setAttribute("County", bestLoc.getCounty());
		req.setAttribute("FunThingsToDo", FunThingsToDo);
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/output.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.print("Output Servlet doPost");
		DerbyDatabase database = new DerbyDatabase();
		String username = (String) req.getSession().getAttribute("user");
		// holds the error message text, if there is any
		String errorMessage = null;
		boolean saved = false;
		
		if(req.getParameter("submitquestions") != null) {
			resp.sendRedirect(req.getContextPath() + "/questions");
			return;
		}
		else if(req.getParameter("SaveLocation") != null) {
			
			try {
				saved = database.SaveLocation(username, Zipcode);
				System.out.print(username);
				System.out.print(Zipcode);
			} catch (SQLException e) {
				errorMessage = "Database error";
				
			}
			
			if(saved == false) {
				errorMessage = "Failed to save location. It may already be saved in your account";
			}
			else {
				String success = "Saved!";
				req.setAttribute("success", success);
			}
			req.setAttribute("errorMessage", errorMessage);
			
			
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/output.jsp").forward(req, resp);
		}
		
		
		
	}

}

