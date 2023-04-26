package servlet;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabasePersist.DerbyDatabase;
import UserModel.PopularLocations;
import UserModel.SavedLocations;
//needs database import
import DatabasePersist.DerbyDatabase;

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
		System.out.println("SavePage Servlet: doGet");
		
		//needs database attribute
		String username = (String) req.getSession().getAttribute("user");
        List<String> SavedLocations = null;
		try {
			System.out.print("USERNAME"+username);
			SavedLocations = database.ViewSavedLocations(username);
			
			System.out.print("SAVEDLOCS: " + SavedLocations.get(0));
			  
		     req.setAttribute("SavedLocations", SavedLocations);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

		
        

    
	
	
	
	
	
		
		
		req.getRequestDispatcher("/_view/SavePage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Savepage Servlet: doPost");
		if(req.getParameter("submithome") != null) {
			resp.sendRedirect(req.getContextPath() + "/index");
			return;
		}

		   
		   
		}
}
