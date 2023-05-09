import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import FakeDatabase.InitialData;
import LocationModel.Location;

public class testInitialData {
	InitialData testInitial;
	List<Location> testLocations;
	
	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		testInitial = new InitialData();
		testLocations = testInitial.getLocations();
	}
	
	@Test
	public void testGetFirstLocationZip() throws ClassNotFoundException, IOException {
		assertEquals("17301", testLocations.get(0).getZipcode());
		assertEquals("17322", testLocations.get(22).getZipcode());
		assertEquals("17407", testLocations.get(115).getZipcode());
	}
	
	@Test
	public void testGetFirstLocationCOLRent() throws ClassNotFoundException, IOException {
		assertEquals(29.2, testLocations.get(0).getCostOfLivingRent(), 0.1);
		assertEquals(29.2, testLocations.get(22).getCostOfLivingRent(), 0.1);
		assertEquals(29.2, testLocations.get(115).getCostOfLivingRent(), 0.1);
	}
	
	@Test
	public void testGetFirstLocationCOLMortgage() throws ClassNotFoundException, IOException {
		assertEquals(20.5, testLocations.get(0).getCostOfLivingOwnWithMortgage(), 0.1);
		assertEquals(20.5, testLocations.get(22).getCostOfLivingOwnWithMortgage(), 0.1);
		assertEquals(20.5, testLocations.get(115).getCostOfLivingOwnWithMortgage(), 0.1);
	}
	
	@Test
	public void testGetFirstLocationCOLNoMortgage() throws ClassNotFoundException, IOException {
		assertEquals(12.5, testLocations.get(0).getCostOfLivingOwnNoMortgage(), 0.1);
		assertEquals(12.5, testLocations.get(22).getCostOfLivingOwnNoMortgage(), 0.1);
		assertEquals(12.5, testLocations.get(115).getCostOfLivingOwnNoMortgage(), 0.1);
	}
	
	@Test
	public void testGetFirstLocationPopulation() throws ClassNotFoundException, IOException {
		assertEquals(4053, testLocations.get(0).getPopulation());
		assertEquals(6012, testLocations.get(22).getPopulation());
		assertEquals(2355, testLocations.get(115).getPopulation());
	}
}
