package servlet;

import java.io.IOException;


import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabasePersist.DerbyDatabase;
//needs popular location import
import UserModel.PopularLocations;

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
		
		
		
			
	    	//needs database attribute
	    	
	        List<PopularLocations> PopularLocations = null;
			try {
				PopularLocations = database.ViewPopularLocatons();
				 System.out.print("PopLoc" + PopularLocations.get(0).getNumberOfSaves());
				  
			     req.setAttribute("PopularLocations", PopularLocations);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        

			req.setAttribute("PopularLocations", PopularLocations);
	        

	    
		
		
		
		
		req.getRequestDispatcher("/_view/Popularlocations.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Popularlocations Servlet: doPost");
		if(req.getParameter("submithome") != null) {
			resp.sendRedirect(req.getContextPath() + "/index");
			return;
		}
	       
	    
		
	    
	}
}

