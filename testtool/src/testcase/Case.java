package testcase;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Case {

	@Before
	public void setUp() throws Exception {
		System.out.println(" setUp ");
	}

	@Test
	public void test() {
		assertEquals(1, 1);
	}

}
