

package System;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.derby.database.Database;

import FakeDatabase.FakeData;
import FindLocation.GetLocation;
import LocationModel.Location;


public class QuestionsServlet extends HttpServlet{
	
	
	FakeData Database = new FakeData();
	List<Location> LocationList = new ArrayList<Location>();
	
	LocationList = Database.getLocationList();
	 
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Questions Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/view/questions.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Question Servlet: doPost");
		
   
		// holds the error message text, if there is any
		String errorMessage = null;

		// result of calculation goes here
		Double result = null;
		
		// decode POSTed form parameters and dispatch to controller
		try {
		int CrimeRateFactor = Integer.parseInt(req.getParameter("CrimeRateFactor"));
		int AveragesalaryFactor = Integer.parseInt(req.getParameter("AveragesalaryFactor"));
		int CostOfLivingFactor = Integer.parseInt(req.getParameter("CostOfLivingFactor"));
		
			// check for errors in the form data before using is in a calculation
		while (CrimeRateFactor + AveragesalaryFactor + CostOfLivingFactor != 10) {
				errorMessage = "Please answer all the questions and make them equal to 10.";
				

			}
		GetLocation Locationgetter = new GetLocation(CrimeRateFactor, AveragesalaryFactor, CostOfLivingFactor, LocationList);
		//get the best Location based on user input
		Location bestlocation = Locationgetter.FindBestLocation();   //copy 2 above
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			
		} catch (NumberFormatException e) {
			errorMessage = "Invalid int";
		}
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("CrimeRateFactor", req.getParameter("CrimeRateFactor"));
		req.setAttribute("AveragesalaryFactor", req.getParameter("AveragesalaryFactor"));
		req.setAttribute("CostOfLivingFactor", req.getParameter("CostOfLivingFactor"));
		
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("result", result);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/view/questions.jsp").forward(req, resp);
	}

	// gets double from the request with attribute named s
	private Double getDoubleFromParameter(String s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			return Double.parseDouble(s);
		}
	}
}

