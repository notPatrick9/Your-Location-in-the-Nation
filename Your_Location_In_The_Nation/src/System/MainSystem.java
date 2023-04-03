package System;
import FakeDatabase.FakeData;
import LocationModel.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import FindLocation.GetLocation;
public class MainSystem {
	
	public static void main(String args[]) throws ClassNotFoundException, IOException {
		//instantiate database
		FakeData Database  = new FakeData();
		List<Location> LocationList = new ArrayList<Location>();
		//get list of locations
		LocationList = Database.getLocationList();
		System.out.print(LocationList.get(0).getAvgSalaryPerHouse());
		//get user input
		//(for now) create a new scanner to get user input
		/*
		Scanner keyboard = new Scanner(System.in);
		//get crime rate factor 
		System.out.print("On a scale from 1-10, how important is the Crime Rate of an area?");
		int CrimeRateFactor = Integer.parseInt(keyboard.next());
		//get average salary per house hold factor
		System.out.print("On a scale from 1-10, how important is the average salary per household of an area?");
		int AveragesalaryFactor = Integer.parseInt(keyboard.next());
		//get cost of living factor 
		System.out.print("On a scale from 1-10, how important is the cost of living of an area?");
		int CostOfLivingFactor = Integer.parseInt(keyboard.next());
		
		
		//call constructor for GetLocation, giving it user input, and location database list
		GetLocation Locationgetter = new GetLocation(CrimeRateFactor, AveragesalaryFactor, CostOfLivingFactor, LocationList);
		
		Location bestlocation = Locationgetter.FindBestLocation();
		System.out.print(bestlocation.getCity());
		*/
		
	}
	
	
}
