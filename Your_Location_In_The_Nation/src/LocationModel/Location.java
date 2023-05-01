package LocationModel;

public class Location {
	
	private String Zipcode;
	private String LocationName;
	private String County;
	private String City;
	private String State;
	private String Region;
	private int CostOfLivingRent;
	private int CostOfLivingOwnWithMortgage;
	private int CostOfLivingOwnNoMortgage;
	private int AvgSalary;
	private int CrimeRate;
	private int Population;
	
	public Location() {
		
	}
	
	public int getCrimeRate() {
		return CrimeRate;
	}

	public void setCrimeRate(int crimeRate) {
		CrimeRate = crimeRate;
	}
	
	public int getAvgSalaryPerHouse() {
		return AvgSalary;
	}
	
	public void setAvgSalaryPerHouse(int avgSalary) {
		AvgSalary = avgSalary;
	}
	
	public int getCostOfLivingRent() {
		return CostOfLivingRent;
	}
	
	public void setCostOfLivingRent(int costOfLiving) {
		CostOfLivingRent = costOfLiving;
	}
	
	public int getCostOfLivingOwnWithMortgage() {
		return CostOfLivingOwnWithMortgage;
	}
	
	public void setCostOfLivingOwnWithMortgage(int costOfLiving) {
		CostOfLivingOwnWithMortgage = costOfLiving;
	}
	
	public int getCostOfLivingOwnNoMortgage() {
		return CostOfLivingOwnNoMortgage;
	}
	
	public void setCostOfLivingOwnNoMortgage(int costOfLiving) {
		CostOfLivingOwnNoMortgage = costOfLiving;
	}
	
	public String getCounty() {
		return County;
	}
	
	public void setCounty(String county) {
		County = county;
	}
	
	public String getLocationName() {
		return LocationName;
	}
	
	public void setLocationName(String locationName) {
		LocationName = locationName;
	}
	
	public String getZipcode() {
		return Zipcode;
	}
	
	public void setZipcode(String zipcode) {
		Zipcode = zipcode;
	}
	
	public String getCity() {
		return City;
	}
	
	public void setCity(String city) {
		City = city;
	}
	
	public String getState() {
		return State;
	}
	
	public void setState(String state) {
		State = state;
	}
	
	public int getPopulation() {
		return Population;
	}
	
	public void setPopulation(int popNum) {
		Population = popNum;
	}
	
	public String getRegion() {
		return Region;
	}
	
	public void setRegion(String regionName) {
		Region = regionName;
	}
}