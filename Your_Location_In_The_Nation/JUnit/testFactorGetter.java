import static org.junit.Assert.assertEquals;


import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import SQLData.FactorGetter;

public class testFactorGetter {
	@Before
	public void setUp() {
		FactorGetter testFactorGetter = new FactorGetter();
		
		
		
	}
	@Test
	public void testCrimeFactor( ) throws ClassNotFoundException, IOException {
		
		FactorGetter testFactorGetter = new FactorGetter();
		assertEquals(3000, testFactorGetter.Get_Crime_Factor(4));
		
	}
	@Test
	public void testAvgSalaryFactor( ) throws ClassNotFoundException, IOException {
		FactorGetter testFactorGetter = new FactorGetter();
		
		assertEquals(65000, testFactorGetter.Get_AvgSalary_Factor(4));
		
	}
	@Test
	public void testCostOfLivingFactor( ) throws ClassNotFoundException, IOException {
		FactorGetter testFactorGetter = new FactorGetter();
		
		assertEquals(180, testFactorGetter.Get_CostofLiving_Factor(4));
		
	}
}
