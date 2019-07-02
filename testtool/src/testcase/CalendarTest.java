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
import testclass.Calendar;
import testclass.TestClass;

@RunWith(Parameterized.class)
public class CalendarTest implements TestCase{
    @Parameters
    public static Collection<Object[]> initTestData(){
    	return Tool.getTestDataCollection();
    }
	private String result;
    private int year;
    private int month;
    private int day;
    
	public CalendarTest(String result,int year,int month,int day) {
		this.result = result;
		this.year = year;
		this.month = month;
		this.day = day;
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public final void testCalendar() {
		assertEquals(result, Calendar.calendar(year, month, day));
		
	}
	public static Collection<Object[]> getParament(int i) {
		if (i == 1) {
			return Arrays.asList(
			        new Object[][]{
			        	{"1000-5-16",1000, 5, 15},
			        	{"1001-6-16",1001, 6, 15},
			        	{"1800-6-17",1800, 6, 16},
			        	{"2518-6-15",2518, 6, 14},
			        	{"年的范围:1000-2519之间",2520, 5, 16},
			        	{"1800-1-16",1800, 1, 15},
			        	{"1800-2-17",1800, 2, 16},
			        	{"1801-11-15",1801, 11, 14},
			        	{"1801-12-16",1801, 12, 15},
			        	{"1802-5-2",1802, 5, 1},
			        	{"1802-6-3",1802, 6, 2},
			        	{"1801-7-1",1801, 6, 30},
			        	{"1801年6月没有31日",1801, 6, 31},
			        	{"1800年2月没有29日",1800, 2, 29},
			        	{"1600-3-1",1600, 2, 29},
			        	{"2000-1-1",1999, 12, 31}
			        	}
			        );
		} else {
			return Arrays.asList(
			        new Object[][]{
			        	}
			        );
		}

	}

	public static int getTestCaseNumber() {
		return 1;
	}

}
