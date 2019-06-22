package application;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelReader {

	public static Collection<Object[]> parseInt(Collection<Object[]> collection,int[] columnIndex) {	
		List<Object[]> result = (List<Object[]>) collection;
		for (int i = 0; i < result.size(); i++) {
			Object[] row = result.get(i);
			for (int j = 0; j < columnIndex.length; j++) {
				row[j] = (int)row[j];
			}
			result.set(i, row);
		}
		return result;
	}
	
	public static Collection<Object[]> getSingleTestCase(String filename,int sheetIndex,int rowIndex) 
			throws FileNotFoundException, IOException {
		File file = new File(filename);
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		HSSFSheet st = wb.getSheetAt(sheetIndex);
		HSSFRow row = st.getRow(rowIndex);
		if (row == null) {
			return null;
		}
		rowSize = row.getLastCellNum();
		List<Object[]> result = new ArrayList<Object[]>();
		Object[] values = new Object[rowSize];
		Arrays.fill(values, "");
		boolean hasValue = false;
		for (short columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
			Object value = null;
			cell = row.getCell(columnIndex);
			if (cell != null) {
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						Date date = cell.getDateCellValue();
						if (date != null) {
							value = new SimpleDateFormat("yyyy-MM-dd")
									.format(date);
						} else {
							value = "";
						}
					} else {
						value = cell.getNumericCellValue();
					}
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					if (!cell.getStringCellValue().equals("")) {
						value = cell.getStringCellValue();
					} else {
						value = ""+cell.getNumericCellValue();
					}
					break;
				case HSSFCell.CELL_TYPE_BLANK:
					value = "";
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					value = "";
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				default:
					value = "";
					break;
				}
			}
			if (columnIndex == 0 && value.equals("")) {
				break;
			}
			values[columnIndex] = value;
			hasValue = true;
		}
		if (hasValue) {
			result.add(values);
			return result;
		}	
		return null;
	}
	
	public static Collection<Object[]> getTestCases(String filename,int sheetIndex,int ignoreRow) 
			throws FileNotFoundException, IOException {
		File file = new File(filename);
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		List<Object[]> result = new ArrayList<Object[]>();
		HSSFSheet st = wb.getSheetAt(sheetIndex);
		for (int rowIndex = ignoreRow; rowIndex <= st.getLastRowNum() ; rowIndex++) {
			HSSFRow row = st.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			int tempRowSize = row.getLastCellNum();
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
			Object[] values = new Object[rowSize];
			Arrays.fill(values, "");
			boolean hasValue = false;
			for (short columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
				Object value = null;
				
				cell = row.getCell(columnIndex);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				if (cell != null) {
//					cell.setEncoding(HSSFCell.ENCODING_UTF_16)
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							if (date != null) {
								value = new SimpleDateFormat("yyyy-MM-dd")
										.format(date);
							} else {
								value = "";
							}
						} else {
							value = new DecimalFormat("0").format(cell
									.getNumericCellValue());
						}
						break;
					case HSSFCell.CELL_TYPE_FORMULA:
						if (!cell.getStringCellValue().equals("")) {
							value = cell.getStringCellValue();
						} else {
							value = ""+cell.getNumericCellValue();
						}
						break;
					case HSSFCell.CELL_TYPE_BLANK:
						value = "";
						break;
					case HSSFCell.CELL_TYPE_ERROR:
						value = "";
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						value = cell.getBooleanCellValue();
						break;
					default:
						value = "";
						break;
					}
				}
				if (columnIndex == 0 && value.equals("")) {
					break;
				}
				values[columnIndex] = value;
				hasValue = true;
			}
			if (hasValue) {
				result.add(values);
			}		
		}
		
		return null;
	}
	
	public static Collection<Object[]> getData(String filename,int ignoreRow)
			throws FileNotFoundException, IOException {
		File file = new File(filename);
		List<Object[]> result = new ArrayList<Object[]>();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			for (int rowIndex = ignoreRow; rowIndex <= st.getLastRowNum() ; rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				int tempRowSize = row.getLastCellNum();
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				Object[] values = new Object[rowSize];
				Arrays.fill(values, "");
				boolean hasValue = false;
				for (short columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
					Object value = null;
					cell = row.getCell(columnIndex);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					if (cell != null) {
//						cell.setEncoding(HSSFCell.ENCODING_UTF_16)
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									value = new SimpleDateFormat("yyyy-MM-dd")
											.format(date);
								} else {
									value = "";
								}
							} else {
								value = new DecimalFormat("0").format(cell
										.getNumericCellValue());
							}
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = ""+cell.getNumericCellValue();
							}
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = cell.getBooleanCellValue();
							break;
						default:
							value = "";
							break;
						}
					}
					if (columnIndex == 0 && value.equals("")) {
						break;
					}
					values[columnIndex] = value;
					hasValue = true;
				}
				if (hasValue) {
					result.add(values);
				}		
			}
			
		}
		in.close();
		return result;
	}

}
