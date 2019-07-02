package testcase;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import application.Tool;
import testclass.Triangle;

@RunWith(Parameterized.class)
public class TriangleTest implements TestCase{
	@Parameters
    public static Collection<Object[]> initTestData(){
    	return getParament(0);
    }
	private String result;
    private float a;
    private float b;
    private float c;
    
	public TriangleTest(String result, float a, float b, float c) {
		this.result = result;
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public final void testAdd() {
		assertEquals(result, Triangle.triangle(a, b, c));
		
	}
	public static Collection<Object[]> getParament(int i) {
		return Arrays.asList(
		        new Object[][]{
			        {"a、b、c必须大于0",0, 500, 499},
			        {"等腰三角形",2, 500, 500},
			        {"等边三角形",500, 500, 500},
			        {"一般三角形",999, 499, 501},
			        {"一般三角形",1000, 500, 501},
			        {"等腰三角形",500, 1, 500},
			        {"非三角形",500, 2, 502},
			        {"一般三角形",502, 999, 501},
			        {"非三角形",500, 1000, 500},
			        {"a、b、c必须大于0",500, 500, -1},
			        {"一般三角形",500, 501, 2},
			        {"一般三角形",500, 502, 999},
			        {"a、b、c应该小于等于1000",502, 502, 1001}
			        }
		        );
	}

	public static int getTestCaseNumber() {
		return 1;
	}
}
