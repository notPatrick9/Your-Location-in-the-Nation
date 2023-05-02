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
	int CostOfLivingType;					//0 for rent, 1 for a mortgage, 2+ for own no mortgage
	int AvgSalaryPerHouseFactor;
	public GetLocation(int UserCrimeScale, int UserAvgSalaryScale, int UserCostOfLivingScale, int COLType, List<Location> Locations) throws ClassNotFoundException, IOException {
		
		//User inputed scales;
		this.UserCrimeScale = UserCrimeScale;
		this.UserAvgSalaryScale = UserAvgSalaryScale;
		this.UserCostOfLivingScale = UserCostOfLivingScale;
		this.CostOfLivingType = COLType;
		
		//Factors from database
		FactorGetter FactorRetriver = new FactorGetter();
		
		CrimeFactor = FactorRetriver.Get_Crime_Factor(UserCrimeScale);
		CostOfLivingFactor = FactorRetriver.Get_AvgSalary_Factor(UserAvgSalaryScale);
		AvgSalaryPerHouseFactor = FactorRetriver.Get_CostofLiving_Factor(UserCostOfLivingScale, COLType);
		this.Locations = Locations;
		TopLocations = new ArrayList<Location>();
	}
	//need to add # of factors satisfied for each location
	public Location FindBestLocation() {
		UserScales MostImportantUserFact;
		
		
		
		//first find all locations that satisfy factors
		for (Location CurrentLocation : Locations) {
			//check if Crime rate factor is satisfied
			int FactorsSatisfied = 0;
			//Also including a range to look for so it does not select too many locations - implement for next milestone
			if(CurrentLocation.getCrimeRate() <= CrimeFactor) {
				/*if(CurrentLocation.getCrimeRate() >= (CrimeFactor - 500)) {
					FactorsSatisfied = FactorsSatisfied + 1;
				}*/
				FactorsSatisfied = FactorsSatisfied + 1;
			}
			//Check to see if average salary per house is satisfied
			if(CurrentLocation.getAvgSalaryPerHouse() >= AvgSalaryPerHouseFactor) {
				/*if(CurrentLocation.getAvgSalaryPerHouse() <= (AvgSalaryPerHouseFactor + 10000)) {
					FactorsSatisfied = FactorsSatisfied + 1;
				}*/
				FactorsSatisfied = FactorsSatisfied + 1;
			}
			//check to see if cost of living factor is satisfied
			if((CostOfLivingType == 0 && CurrentLocation.getCostOfLivingRent() < CostOfLivingFactor) ||
					(CostOfLivingType == 1 && CurrentLocation.getCostOfLivingOwnWithMortgage() < CostOfLivingFactor) ||
					(CostOfLivingType > 1 && CurrentLocation.getCostOfLivingOwnNoMortgage() < CostOfLivingFactor)) {
				/*if(CurrentLocation.getCostOfLiving() >= (CostOfLivingFactor - 30)) {
					FactorsSatisfied = FactorsSatisfied + 1;
				}*/
				FactorsSatisfied = FactorsSatisfied + 1;
			}
			//System.out.print(FactorsSatisfied);
			//only add locations where all factors are satisfied
			if(FactorsSatisfied == 3) {
				TopLocations.add(CurrentLocation);
			}
			
			
		}
		
		//sort TopLocations based on most important user factor
		//find most important user factor
		if(TopLocations.isEmpty()) {
			return null;
		}
		else {
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
		    	if (CostOfLivingType == 0) TopLocations.sort(new CostOfLivingRentComparator());
		    	if (CostOfLivingType == 1) TopLocations.sort(new CostOfLivingOwnWithMortgageComparator());
		    	if (CostOfLivingType > 1) TopLocations.sort(new CostOfLivingOwnNoMortgageComparator());
		    }
		    
		    //return the location that has all factors satisfied and is the best in terms of the users most important factor
		    return TopLocations.get(TopLocations.size() - 1);
		}
	    
		
		
		
	}
	
	
	
	
}
