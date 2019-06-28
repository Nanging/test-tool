package testcase;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import application.Tool;

@RunWith(Parameterized.class)
public class AddTest {

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

}
