package LocationModel;

public class Location {
	
	private String Zipcode;
	private String LocationName;
	private String County;
	private String City;
	private String State;
	private int CostOfLiving;
	private int AvgSalary;
	private int CrimeRate;
	
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
	public int getCostOfLiving() {
		return CostOfLiving;
	}
	public void setCostOfLiving(int costOfLiving) {
		CostOfLiving = costOfLiving;
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
	
}
