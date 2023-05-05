import static org.junit.Assert.assertEquals;



import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import SQLData.FactorGetter;

public class testFactorGetter {
	@Before
	public void setUp() {
		//FactorGetter testFactorGetter = new FactorGetter();
		
		
		
	}
	@Test
	public void testCrimeFactor( ) throws ClassNotFoundException, IOException {
		
		FactorGetter testFactorGetter = new FactorGetter();
		assertEquals(6500, testFactorGetter.Get_Crime_Factor(4));
		
	}
	@Test
	public void testAvgSalaryFactor( ) throws ClassNotFoundException, IOException {
		FactorGetter testFactorGetter = new FactorGetter();
		
		assertEquals(50000, testFactorGetter.Get_AvgSalary_Factor(4));
		
	}
	@Test
	public void testCostOfLivingFactor( ) throws ClassNotFoundException, IOException {
		FactorGetter testFactorGetter = new FactorGetter();
		
		assertEquals(33.0, testFactorGetter.Get_CostofLiving_Factor(4, 0), 0.1);
		assertEquals(22.5, testFactorGetter.Get_CostofLiving_Factor(4, 1), 0.1);
		assertEquals(13.3, testFactorGetter.Get_CostofLiving_Factor(4, 2), 0.1);
		
	}
}
