package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FakeDatabase.FakeData;
import FindLocation.GetLocation;
import FindLocation.UserScales;
import LocationModel.Location;
import SQLData.FactorGetter;
import DatabasePersist.DerbyDatabase;



public class QuestionsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    
    
  
    @Override
    public void init() throws ServletException {
        super.init();
        
        DerbyDatabase database = new DerbyDatabase();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Questions Servlet: doGet");

   
        req.getRequestDispatcher("/_view/questions.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Question Servlet: doPost");
        System.out.print("\n" + req.getParameter("COLTypes"));
        // holds the error message text, if there is any
        String errorMessage = null;
        String COLType = null;
        // result of calculation goes here
        Location bestLocation = null;
        
        int CostOfLivingType = 0;
        int mostImportantUserFact;
        
        
        DerbyDatabase database = new DerbyDatabase();
        FactorGetter FactorRetriver = new FactorGetter();
        
        
        
        
        

        // decode POSTed form parameters and dispatch to controller
//<<<<<<< HEAD
        
        
        if(req.getParameter("Backtoindex") != null) {
			resp.sendRedirect(req.getContextPath() + "/index");
			return;
//>>>>>>> refs/remotes/Ryan/master
		}
        else {
        	try {
                int crimeRateFactor = getIntFromParameter(req.getParameter("crimeRate"));
                int averageSalaryFactor = getIntFromParameter(req.getParameter("averageSalary"));
                int costOfLivingFactor = getIntFromParameter(req.getParameter("costOfLiving"));
                COLType = req.getParameter("COLTypes");
                
                System.out.print(crimeRateFactor);
                System.out.print(averageSalaryFactor);
                System.out.print(costOfLivingFactor);
                
                
                
                if (crimeRateFactor + averageSalaryFactor + costOfLivingFactor != 10) {
                    errorMessage = "Please answer all the questions and make them equal to 10.";
                } 
                else {
                    //get COL Type
                	if(COLType == "Rent") {
                		CostOfLivingType = 0;
                	}
                	else if(COLType == "Mortgage") {
                		CostOfLivingType = 1;
                	}
                	else if(COLType == "NoMortgage") {
                		CostOfLivingType = 2;
                	}
                	
                	
                	
                	//need to get scales first
                	int CrimeFactor = FactorRetriver.Get_Crime_Factor(crimeRateFactor);
            		int AvgSalaryPerHouseFactor = FactorRetriver.Get_AvgSalary_Factor(averageSalaryFactor);
            		float CostOfLivingFactor = FactorRetriver.Get_CostofLiving_Factor(costOfLivingFactor, CostOfLivingType);
                	
                	//most important user factor
            		
            		if(crimeRateFactor >= averageSalaryFactor) {
            			//crime rate
        		        if(crimeRateFactor >= costOfLivingFactor) mostImportantUserFact = 2;
        		        //cost of living
        		        else mostImportantUserFact = 1;
        		    }
        		    else {
        		    	  //avg sal
        		          if(averageSalaryFactor>=costOfLivingFactor) mostImportantUserFact = 0;
        		          
        		          //cost of living
        		          else mostImportantUserFact = 1;
        		       }
            		
            		
                	
                	
                	
                	
                	bestLocation = database.getLocation(averageSalaryFactor, CostOfLivingFactor, CrimeFactor, CostOfLivingType, mostImportantUserFact);
                    
                    if(bestLocation != null) {
                    	 // store user object in session
            			req.getSession().setAttribute("bestLocation", bestLocation);

            			// redirect to /index page
            			resp.sendRedirect(req.getContextPath() + "/output");

            			return;
                    }
                    
                }
            
            } catch (ClassNotFoundException r) {
    		
    			r.printStackTrace();
    		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		// Add parameters as request attributes
    		// this creates attributes named "first" and "second for the response, and grabs the
    		// values that were originally assigned to the request attributes, also named "first" and "second"
    		// they don't have to be named the same, but in this case, since we are passing them back
    		// and forth, it's a good idea
       
            req.setAttribute("crimeRate", req.getParameter("crimeRate"));
            req.setAttribute("averageSalary", req.getParameter("averageSalary"));
            req.setAttribute("costOfLiving", req.getParameter("costOfLiving"));
           
        
            req.setAttribute("errorMessage", errorMessage);


    		// Forward to view to render the result HTML document
            req.getRequestDispatcher("/_view/questions.jsp").forward(req, resp);
        }
      }    
    
    
 // gets double from the request with attribute named s
 	private int getIntFromParameter(String s) {
 		if (s == null || s.equals("")) {
 			return 0;
 		} else {
 			return Integer.parseInt(s);
 		}
 	}

}


