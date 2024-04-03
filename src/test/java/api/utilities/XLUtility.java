package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	public String path;
	
	public XLUtility(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		int rowcount;
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		rowcount =sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetName, int rownum) throws IOException{
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
		
	}
	
	public String getCellData(String sheetName, int rownum,int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		}catch(Exception  e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String shetName, int rownum,int colnum,String data)throws IOException  {
		File xlfile = new File(path);
		
		if(!xlfile.exists()) {  // If file does not exists then create new  
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		fi =  new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheet)==-1)  // If sheet does not exist create new one 
			workbook.createSheet(shetName);
		   sheet = workbook.getSheet(shetName);
		
		   
		   if(sheet.getRow(rownum)==null)  // If row does not exist create new one
			   sheet.createRow(rownum);
		   row = sheet.getRow(rownum);
		   
		   cell = row.createCell(colnum);
		   cell.setCellValue(data);
		   fo = new FileOutputStream(path);
		   workbook.write(fo);
		   workbook.close();
		   fi.close();
		   fo.close();
	}
	
	public void fillGreenColor(String sheetName, int rownum,int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		workbook.write(fo);
		 workbook.close();
		   fi.close();
		   fo.close();
	}
	
	public void fillRedColor(String sheetName, int rownum,int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		workbook.write(fo);
		 workbook.close();
		   fi.close();
		   fo.close();
	}
	
	
	/*
	 * public static void main(String arg[]) throws IOException { String path
	 * ="C:\\Users\\balaji.d\\eclipse-workspace\\PetStoreAutomation\\testData\\Userdata.xlsx";
	 * XLUtility s = new XLUtility(path); int row = s.getRowCount("Sheet1");
	 * System.out.println(row+","); int col = s.getCellCount("Sheet1", row);
	 * System.out.println(col);
	 * 
	 * for(int i = 0;i<=row;i++ ) { for(int j=0;j<col;j++) {
	 * System.out.print(s.getCellData("Sheet1", i, j) + " "); }
	 * System.out.println(); }
	 * 
	 * }
	 */

}
