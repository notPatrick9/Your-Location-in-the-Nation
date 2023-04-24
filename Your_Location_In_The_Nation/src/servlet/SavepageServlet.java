package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabasePersist.DerbyDatabase;

//   make List<Popular locations> and set equal to ViewSavedLocations
//print list of popular locations
//print zip code and number of saves
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
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Login Servlet: doPost");

	
}
}