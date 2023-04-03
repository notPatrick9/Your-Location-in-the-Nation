package FindLocation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import LocationModel.Location;


import SQLData.FactorGetter;


public class GetLocation {
	
	int UserCrimeScale;
	int UserAvgSalaryScale;
	int UserCostOfLivingScale;
	List<Location> Locations;
	List<Location> TopLocations;
	int CrimeFactor;
	int CostOfLivingFactor;
	int AvgSalaryPerHouseFactor;
	public GetLocation(int UserCrimeScale, int UserAvgSalaryScale, int UserCostOfLivingScale, List<Location> Locations) throws ClassNotFoundException, IOException {
		
		//User inputed scales;
		this.UserCrimeScale = UserCrimeScale;
		this.UserAvgSalaryScale = UserAvgSalaryScale;
		this.UserCostOfLivingScale = UserCostOfLivingScale;
		
		//Factors from database
		FactorGetter FactorRetriver = new FactorGetter();
				
		CrimeFactor = FactorRetriver.Get_Crime_Factor(UserCrimeScale);;
		CostOfLivingFactor = FactorRetriver.Get_AvgSalary_Factor(UserAvgSalaryScale);
		AvgSalaryPerHouseFactor = FactorRetriver.Get_CostofLiving_Factor(UserCostOfLivingScale);
		Locations = new ArrayList<Location>();
		TopLocations = new ArrayList<Location>();
	}
	//need to add # of factors satisfied for each location
	public Location FindBestLocation() {
		UserScales MostImportantUserFact;
		//first find all locations that satisfy factors
		for (Location CurrentLocation : Locations) {
			//check if Crime rate factor is satisfied
			int FactorsSatisfied = 0;
			//Also including a range to look for so it does not select too many locations
			if(CurrentLocation.getCrimeRate() <= CrimeFactor && CurrentLocation.getCrimeRate() >= (CrimeFactor - 200)) {
				FactorsSatisfied ++;
			}
			//Check to see if average salary per house is satisfied
			if(CurrentLocation.getAvgSalaryPerHouse() >= AvgSalaryPerHouseFactor && CurrentLocation.getAvgSalaryPerHouse() <= (AvgSalaryPerHouseFactor + 5000)) {
				FactorsSatisfied ++;
			}
			//check to see if cost of living factor is satisfied
			if(CurrentLocation.getCostOfLiving() <= CostOfLivingFactor && CurrentLocation.getCostOfLiving() >= (CrimeFactor - 15)) {
				FactorsSatisfied ++;
			}
			
			//only add locations where all factors are satisfied
			if(FactorsSatisfied == 3) {
				TopLocations.add(CurrentLocation);
			}
			
		}
		
		//sort TopLocations based on most important user factor
		//find most important user factor
	
	    if(UserCrimeScale >= UserAvgSalaryScale) {
	        if(UserCrimeScale >= UserCostOfLivingScale) MostImportantUserFact = UserScales.CrimeRate;
	        else MostImportantUserFact = UserScales.CostOfLiving;
	    }
	    else {
	          if(UserAvgSalaryScale>=UserCostOfLivingScale) MostImportantUserFact = UserScales.AvgSal;
	          else MostImportantUserFact = UserScales.CostOfLiving;
	       }
	    
	    
	    //figure out which was most important, then sort TopLocations by this factor
	    if(MostImportantUserFact == UserScales.CrimeRate) {
	    	TopLocations.sort(new CrimeRateComparator());
	    }
	    else if(MostImportantUserFact == UserScales.AvgSal) {
	    	TopLocations.sort(new AvgSalaryComparator());
	    }
	    else {
	    	TopLocations.sort(new CostOfLivingComparator());
	    }
	    
	    //return the location that has all factors satisfied and is the best in terms of the users most important factor
	    return TopLocations.get(TopLocations.size() - 1);
		
		
		
		
	}
	
	
	
	
}
