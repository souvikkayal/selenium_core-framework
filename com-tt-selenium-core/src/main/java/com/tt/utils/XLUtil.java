package com.tt.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;;
public class XLUtil {

	public String filePath = "";
	public String currSheet = "Data";
	public int rowCount = 0;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	InputStream is;
	
	HashMap<String, Integer> columns;
	
	public XLUtil(String filePath) {
		this(filePath,"Data");
	}
	public XLUtil(String filePath,String currSheet) {
		this.filePath = filePath;
		this.currSheet = currSheet;
		load();
	}
	
	public void load() {
		try {
			 is = new FileInputStream(this.filePath);
			wb = new XSSFWorkbook(is);
			loadSheet(this.currSheet);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadSheet(String sheetName) {
		sheet = wb.getSheet(currSheet);
		getRowCount();
		loadColumns();
	}
	
	public void close() {
		try {
			if(is!=null)
				is.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadColumns() {
		columns = new HashMap();
		Row header = sheet.getRow(0);
		int currCellIndex = 0;
		Iterator<Cell> cells = header.iterator();
		while(cells.hasNext()) {
			Cell cell = cells.next();
			String colName = getCellValue(cell);
			columns.put(colName, currCellIndex);
			currCellIndex++;
		}
	}
	
	public void printRow(int rowNum) {
		Row r = sheet.getRow(rowNum);
		Iterator<Cell> cells = r.iterator();
		int cellNum = 0;
		while(cells.hasNext()) {
			Cell cell = cells.next();
			System.out.println("Value at ["+rowNum+","+cellNum+"]:"+getCellValue(cell));
			cellNum++;
		}
		
	}
	
	public String getCellValue(String columnName,int rowNum) {
		String ret = "";
		Row r = sheet.getRow(rowNum);
		Iterator<Cell> cells = r.iterator();
		int colIndex = columns.get(columnName);
		int cellNum = 0;
		while(cells.hasNext()) {
			Cell cell = cells.next();
			if(colIndex == cellNum) {
				ret = getCellValue(cell);
				System.out.println("Value at ["+rowNum+","+cellNum+"]:"+getCellValue(cell));
				break;
			}
			cellNum++;
		}
		return ret;
	}
	
	public String getCellValue(Cell cell) {
		String ret = "";
		if(cell.getCellTypeEnum() == CellType.STRING)
			ret = cell.getStringCellValue();
		else if(cell.getCellTypeEnum() == CellType.NUMERIC)
			ret = ""+cell.getNumericCellValue();
		else if(cell.getCellTypeEnum() == CellType.BOOLEAN)
			ret = ""+cell.getBooleanCellValue();
		return ret;
	}
	
	public int getRowCount() {
		rowCount = sheet.getLastRowNum();
		return rowCount;
	}
	
	public static void main(String args[]) {
		XLUtil xl = new XLUtil("D:\\Selenium File Generate\\Resource\\TestData.xlsx");
		int rowCount = xl.getRowCount();
		System.out.println("Number of rows present in exel file are: "+rowCount);
		
		String firstName = xl.getCellValue("FirstName", 1);
		String lastName = xl.getCellValue("LastName", 1);
		String postalCode = xl.getCellValue("PostalCode", 1);
		
		xl.close();
		
	}
}
