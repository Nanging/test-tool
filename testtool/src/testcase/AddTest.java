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
import testclass.TestClass;

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
	public static Collection<Object[]> getParament(int i) {
		return Arrays.asList(
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
	}

	public static int getTestCaseNumber() {
		return 1;
	}

}
