package System;
import FakeDatabase.FakeData;


import LocationModel.Location;
import ThingsToDo.AboutTheArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import FindLocation.GetLocation;
public class MainSystem {
	//For now this will serve as the System class for the project
	//In future milestones we will need servlets that do all of the interactions in this class, with corresponding jsps as well
	public static void main(String args[]) throws Exception {
		//instantiate database
		FakeData Database  = new FakeData();
		List<Location> LocationList = new ArrayList<Location>();
		//get list of locations
		LocationList = Database.getLocationList();
		
		//System.out.print(LocationList.get(0).getCrimeRate());
		//get user input
		//(for now) create a new scanner to get user input
		Scanner keyboard = new Scanner(System.in);
		//variable that keeps track of how many "points" the user has left to use for the factors
		//the user must use all points for all of the combined factors
		int PointsLeft = 10;
		//get crime rate factor 
		System.out.print("Points Left: "+PointsLeft + "\n");
		System.out.print("On a scale from 1-10, how important is the Crime Rate of an area?: \n");
		
		int CrimeRateFactor = Integer.parseInt(keyboard.next());
		//decrement pointsleft
		PointsLeft -= CrimeRateFactor;
		//get average salary per house hold factor
		System.out.print("Points Left: "+PointsLeft + "\n");
		System.out.print("On a scale from 1-10, how important is the average salary per household of an area?: \n");
		
		int AveragesalaryFactor = Integer.parseInt(keyboard.next());
		//decrement pointsleft
		PointsLeft -= AveragesalaryFactor;
		//get cost of living factor 
		System.out.print("Points Left: "+PointsLeft + "\n");
		System.out.print("On a scale from 1-10, how important is the cost of living of an area?: \n");
		
		int CostOfLivingFactor = Integer.parseInt(keyboard.next());
		//decrement pointsleft
		PointsLeft -= CostOfLivingFactor;
		
		//make sure that user numbers all add up to 10
		//maybe check for other exceptions later
		while(CrimeRateFactor + AveragesalaryFactor + CostOfLivingFactor != 10) {
			//reinstantiate points left 
			PointsLeft = 10;
			//get crime rate factor 
			System.out.print("Needs to add to 10. Retry please.\nPoints Left: "+PointsLeft + "\n");
			System.out.print("On a scale from 1-10, how important is the Crime Rate of an area?: \n");
			
			CrimeRateFactor = Integer.parseInt(keyboard.next());
			//decrement pointsleft
			PointsLeft -= CrimeRateFactor;
			
			//get average salary per house hold factor
			System.out.print("Points Left: "+PointsLeft + "\n");
			System.out.print("On a scale from 1-10, how important is the average salary per household of an area?: \n");
			
			AveragesalaryFactor = Integer.parseInt(keyboard.next());
			//decrement pointsleft
			PointsLeft -= AveragesalaryFactor;
			
			
			//get cost of living factor 
			System.out.print("Points Left: "+PointsLeft + "\n");
			System.out.print("On a scale from 1-10, how important is the cost of living of an area?: \n");
			
			CostOfLivingFactor = Integer.parseInt(keyboard.next());
			//decrement pointsleft
			
		}
		
		
		
		
		keyboard.close();
		
		//call constructor for GetLocation, giving it user input, and location database list
		GetLocation Locationgetter = new GetLocation(CrimeRateFactor, AveragesalaryFactor, CostOfLivingFactor, LocationList);
		//get the best Location based on user input
		Location bestlocation = Locationgetter.FindBestLocation();
		
		//ensure that a location was returned
		if(bestlocation != null) {
			//Give information about the selected location
			System.out.print("The location that best matched your criteria is " + bestlocation.getCounty()+" County located in "+bestlocation.getCity()+"\n");
			System.out.print("The statistics are as follows, Crime Rate: " + bestlocation.getCrimeRate() + " Average Salary Per Household: "+ bestlocation.getAvgSalaryPerHouse()+ " Cost of Living: "+bestlocation.getCostOfLiving()+ "\n");
			//fun things to do
			AboutTheArea getfunThings = new AboutTheArea();
			System.out.print("Here are some fun things to do in " +bestlocation.getCounty()+ " County");
			System.out.print(getfunThings.getThingsTodo(bestlocation.getZipcode()));
		}
		//if not, prompt the user to re-enter their responses
		else {
			System.out.print("We were not able to find a location that that meets these needs, please change some of your factors and try again.");
		}
		
		
		
	}
	
	
}
