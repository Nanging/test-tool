package testcase;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import application.Tool;
import testclass.Tel;
import testclass.TestClass;


@RunWith(Parameterized.class)
public class TelTest implements TestCase {
	 @Parameters
	    public static Collection<Object[]> initTestData(){
	    	return Tool.getTestDataCollection();
	    }
		private String result;
	    private int min;
	    private int time;
	    private double delay;
	    
		public TelTest(String result,int min,int time,double delay) {
			this.result = result;
			this.min = min;
			this.time = time;
			this.delay = delay;
		}

		@Before
		public void setUp() throws Exception {
			
		}

		@Test
		public final void testTel() {
			assertEquals(result, Tel.calculate(min, time, delay));
			
		}
		public static Collection<Object[]> getParament(int i) {
			if (i == 1) {
			return Arrays.asList(
	    		new Object[][]{
			        {"30.00",0,6,100.0},{"30.15",1,6,100.0},{"34.50",30,6,100.0},{"38.85",59,6,100.0},{"39.00",60,6,100.0},
			        {"39.15",61,6,100.0},{"43.50",90,6,100.0},{"47.85",119,6,100.0},{"48.00",120,6,100.0},{"48.15",121,6,100.0},
			        {"52.50",150,6,100.0},{"56.85",179,6,100.0},{"57.00",180,6,100.0},{"57.15",181,6,100.0},{"66.00",240,6,100.0},
			        {"74.85",299,6,100.0},{"75.00",300,6,100.0},{"31.35",301,6,100.0},{"30.45",150,0,100.0},{"30.45",150,1,100.0},
			        {"52.50",150,6,100.0},{"52.50",150,11,100.0},{"52.50",150,12,100.0},{"47.50",150,6,0.0},{"47.55",150,6,1.0},
			        {"52.50",150,6,100.0},{"输入错误",-1,6,100.0},{"输入错误",150,-1,100.0},{"输入错误",150,13,100.0},{"输入错误",150,6,-1.0}}
		        );
			}else if (i == 2) {
				return Arrays.asList(
					new Object[][]{
				        {"30.05",30,1,100.0},{"34.50",30,2,100.0},{"34.50",30,3,100.0},{"34.50",30,5,100.0},{"34.50",30,9,100.0},
				        {"30.20",90,1,100.0},{"30.20",90,2,100.0},{"43.50",90,3,100.0},{"43.50",90,5,100.0},{"43.50",90,9,100.0},
				        {"30.45",150,1,100.0},{"30.45",150,2,100.0},{"30.45",150,3,100.0},{"52.50",150,5,100.0},{"52.50",150,9,100.0},
				        {"30.90",240,1,100.0},{"30.90",240,2,100.0},{"30.90",240,3,100.0},{"66.00",240,5,100.0},{"66.00",240,9,100.0},
				        {"31.62",360,1,100.0},{"31.62",360,2,100.0},{"31.62",360,3,100.0},{"31.62",360,5,100.0},{"84.00",360,9,100.0},
				        {"输入错误",-1,6,100.0},{"输入错误",150,-1,100.0},{"输入错误",150,13,100.0},{"输入错误",150,6,-1.0}}
			        );
			}else if (i == 3) {
				return Arrays.asList(
			        new Object[][]{
				        {"30.05",30,1,100.0},{"34.50",30,7,100.0},{"30.20",90,2,100.0},{"43.50",90,7,100.0},{"30.45",150,3,100.0},
				        {"52.50",150,7,100.0},{"30.90",240,3,100.0},{"66.00",240,7,100.0},{"31.62",360,6,100.0},{"84.00",360,9,100.0}}
			        );	
			}
		
			return Arrays.asList(
		    		new Object[][]{}
			        );
		}

		public static int getTestCaseNumber() {
			return 3;
		}
}
