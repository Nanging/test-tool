package testcase;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import application.Tool;
import testclass.Calendar;
import testclass.Sale;
import testclass.SaleSystem;
import testclass.TestClass;

@RunWith(Parameterized.class)
public class SaleSystemTest  implements TestCase {
	 @Parameters
	    public static Collection<Object[]> initTestData(){
	    	return Tool.getTestDataCollection();
	    }
		
	    private ArrayList<Sale> sales = new ArrayList<>();
	    private double result;
		public SaleSystemTest(double result,ArrayList<Sale> sales) {
			this.result = result;
			this.sales = sales;
		}

		@Before
		public void setUp() throws Exception {
			
		}

		@Test
		public final void testSaleSystem() {
			assertEquals(result, SaleSystem.sell(sales),0.01);
		}
		public static Collection<Object[]> getParament(int i) {
			return Arrays.asList(
			        new Object[][]{
				        {
				        650.0f,
				        new ArrayList<Sale>() {{
				    		add(new Sale(1, 40, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        995.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(70, 40, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        820.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 40, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        	670.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(5, 40, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        	970.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(65, 40, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        	0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(0, 45, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(71, 45, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        
				        
				        
				        {
				        	586.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 1, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        	1060.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 80, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        	610.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 5, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        	1030.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 75, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 0, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 81, 45));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        
				        
				        
				        {
				        	424.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 40, 1));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        	1225.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 40, 90));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        	460.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 40, 5));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        	1180.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 40, 85));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 40, 0));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(35, 40, 91));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        
				        {
				        	50.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(5, 5, 5));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        	100.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(10, 10, 10));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        
				        {
				        	210.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(14, 14, 14));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        	270.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(18, 18, 18));
				    		add(new Sale(-1, 0, 0));
				        }}},
				        {
				        	400.0,
				        new ArrayList<Sale>() {{
				    		add(new Sale(20, 20, 20));
				    		add(new Sale(-1, 0, 0));
				        }}},
			        }
			        );
		}

		public static int getTestCaseNumber() {
			return 1;
		}
}
