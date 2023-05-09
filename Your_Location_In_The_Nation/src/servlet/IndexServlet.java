package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
	

	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");
			
		req.getRequestDispatcher("/_view/Index.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if(req.getParameter("GotoQuestions") != null) {
			req.getRequestDispatcher("/_view/questions.jsp").forward(req, resp);
		}
		else if(req.getParameter("GotoPopLocs") != null) {
			resp.sendRedirect(req.getContextPath() + "/PopularLocations");
			return;
		}
		else if(req.getParameter("GotoSavedLocs") != null) {
			resp.sendRedirect(req.getContextPath() + "/SavePage");
			return;
		}
		
		else if(req.getParameter("GotoLogin") != null) {
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		//new buttons 
		else if(req.getParameter("GotoSearch") != null) {
			resp.sendRedirect(req.getContextPath() + "/searchzip");
			return;
		}
		else if(req.getParameter("GotoViewZips") != null) {
			resp.sendRedirect(req.getContextPath() + "/viewzips");
			return;
		}
		
		
	}
	
}
