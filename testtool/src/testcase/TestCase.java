package testcase;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class TestCase {
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
