package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabasePersist.DerbyDatabase;

public class ViewZipsForAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DerbyDatabase database;

	@Override
	public void init() throws ServletException {
		super.init();
		database = new DerbyDatabase();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ViewZipsForArea Servlet: doGet");
	        

	    
		
		
		
		
		req.getRequestDispatcher("/_view/ZipsForArea.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ZipsForArea Servlet: doPost");
		//needs database attribute
		String errorMessage;
    	String AreaName = req.getParameter("AreaName");
        List<String> Zips = null;
        
        if(req.getParameter("submit") != null) {
        	try {
   			 Zips = database.getZipcodesForAreaName(AreaName);
   			 //System.out.print("PopLoc" + PopularLocations.get(0).getNumberOfSaves());
   			  
   		     
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
           

   		if(Zips.isEmpty()) {
   			errorMessage = "There is no zipcode in our database that matched that area";
   			req.setAttribute("errorMessage", errorMessage);
   			System.out.print(errorMessage);
   		}
   		else {
   			req.setAttribute("Zips", Zips);
   		}
   	       
   	    
   		req.getRequestDispatcher("/_view/ZipsForArea.jsp").forward(req, resp);
        }
        
        else if(req.getParameter("index")!= null) {
        	resp.sendRedirect(req.getContextPath() + "/index");
        	return;
        }
        
        
		
	    
	}
}
