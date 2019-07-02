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
		}else if (i == 2) { 
			return Arrays.asList(
			        new Object[][]{
			        	{"2019-1-16",2019, 1, 15},{"2019-3-29",2019, 3, 28},{"2019-5-30",2019, 5, 29},
			        	{"2019-7-31",2019, 7, 30},{"2019-9-1",2019, 8, 31},{"2019-4-17",2019, 4, 16},
			        	{"2019-6-29",2019, 6, 28},{"2019-9-30",2019, 9, 29},{"2019-12-1",2019, 11, 30},
			        	{"2019年6月没有31日",2019, 6, 31},{"2019-2-16",2019, 2, 15},{"2019-3-1",2019, 2, 28},
			        	{"2019年2月没有29日",2019, 2, 29},{"2019年2月没有30日",2019, 2, 30},{"2019年2月没有31日",2019, 2, 31},
			        	{"2019-12-15",2019, 12, 14},{"2019-12-29",2019, 12, 28},{"2019-12-30",2019, 12, 29},
			        	{"2019-12-31",2019, 12, 30},{"2020-1-1",2019, 12, 31},{"2020-1-16",2020, 1, 15},
			        	{"2020-3-29",2020, 3, 28},{"2020-5-30",2020, 5, 29},{"2020-7-31",2020, 7, 30},
			        	{"2020-9-1",2020, 8, 31},{"2020-4-14",2020, 4, 13},{"2020-6-29",2020, 6,28},
			        	{"2020-9-30",2020, 9, 29},{"2020-12-1",2020, 11, 30},{"2020年9月没有31日",2020, 9, 31},
			        	{"2020-2-12",2020, 2, 11},{"2020-2-29",2020, 2, 28},{"2020-3-1",2020, 2, 29},
			        	{"2020年2月没有30日",2020, 2, 30},{"2020年2月没有31日",2020, 2, 31},{"2020-12-20",2020, 12, 19},
			        	{"2020-12-29",2020, 12, 28},{"2020-12-30",2020, 12, 29},{"2020-12-31",2020, 12, 30},
			        	{"2021-1-1",2020, 12, 31}    
			        	}
			        );			
		} else if (i == 3) {
			return Arrays.asList(
			        new Object[][]{
			        	{"2019-6-16",2019, 6, 15},
			        	{"2020-2-29",2020, 2, 28},
			        	{"2019-3-1",2019, 2, 28},
			        	{"2019-4-29",2019, 4, 28},
			        	{"2020-3-1",2020, 2, 29},
			        	{"2020-7-30",2020, 7, 29},
			        	{"2019年2月没有29日",2019, 2, 29},
			        	{"2019-5-30",2019, 5, 29},
			        	{"2019年2月没有30日",2019, 2, 30},
			        	{"2019-5-1",2019, 4, 30},
			        	{"2019-5-31",2019, 5, 30},
			        	{"2019年2月没有31日",2019, 2, 31},
			        	{"2019-2-1",2019, 1, 31},
			        	{"2020-1-1",2019, 12, 31}
			        	}
			        );			
		}else {
			return Arrays.asList(
			        new Object[][]{
			        	}
			        );			
		}
	}

	public static int getTestCaseNumber() {
		return 3;
	}

}
