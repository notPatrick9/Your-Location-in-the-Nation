package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LocationModel.Location;
import ThingsToDo.AboutTheArea;


public class OutputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			FunThingsToDo = about.getThingsTodo(bestLoc.getZipcode());
			
		} catch (Exception e) {
			errorMessage = "Error getting information";
		}
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("CrimeRate", bestLoc.getCrimeRate());
		req.setAttribute("AvgSalary", bestLoc.getAvgSalaryPerHouse());
		req.setAttribute("CostOfLiving", bestLoc.getCostOfLiving());
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
		
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/output.jsp").forward(req, resp);
	}

}

