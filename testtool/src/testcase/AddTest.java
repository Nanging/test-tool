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

@RunWith(Parameterized.class)
public class AddTest implements TestCase{

    @Parameters
    public static Collection<Object[]> initTestData(){
    	return Tool.getTestDataCollection();
    }
	
    private int a;
    private int b;
    private int sum;
    
	public AddTest(int sum, int a, int b) {
		this.a = a;
		this.b = b;
		this.sum = sum;
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public final void testAdd() {
		assertEquals(sum, new TestClass().add(a, b));
	}

	private static List<Object[]> paramentCollection = Arrays.asList(
	        new Object[][]{
		        {3,1,2},
		        {10,5,5},
		        {6,4,2},
		        {7,3,4},
		        {3,1,2},
		        {7,5,5},
		        {9,4,2},
		        {7,3,4}}
	        );
	private static List<String> paramentName = Arrays.asList(
	        new String[] {
	        		"a","b"
	        });

	public static Collection<Object[]> getParament(int i) {
		return Arrays.asList(
		        new Object[][]{
		        	paramentCollection.get(i)
		        });
	}

	public static Collection<Object[]> getParamentWithName(int i) {
		Object[][] parament = new Object[paramentName.size()][2];
		for (int j = 1; j < paramentCollection.get(i).length; j++) {
			parament[j-1][0] = paramentName.get(j-1);
			parament[j-1][1] = paramentCollection.get(i)[j];
		}
		return Arrays.asList(parament);
	}

	public static Collection<Object[]> getAll() {
		return paramentCollection;
	}

	public static int getTestCaseNumber() {
		return paramentCollection.size();
	}

}
