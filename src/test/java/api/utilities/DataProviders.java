package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
		
		
		String path
		  =System.getProperty("user.dir")+"\\testData\\Userdata.xlsx";
		System.out.println(path);
		  XLUtility s = new XLUtility(path); 
		  int row = s.getRowCount("Sheet1");
		 
		  int col = s.getCellCount("Sheet1", row);
		 
		  String apidata[][]= new String [row][col];
		  for(int i = 1;i<=row;i++ ) { 
			  for(int j=0;j<col;j++) {
				  apidata[i-1][j] = s.getCellData("Sheet1", i, j);
		  }
	}
		  
		return apidata;  
	}
	
@DataProvider(name="UserNames")
public String[] getUserNames() throws IOException{
	String path
	  =System.getProperty("user.dir")+"\\testData\\Userdata.xlsx";
	XLUtility s = new XLUtility(path); 
	 int row = s.getRowCount("Sheet1");
	 String apidata[] = new String[row];
	 for (int i = 1; i <=row; i++) {
		 apidata[i-1] = s.getCellData("Sheet1", i, 1);
	 }
	 return apidata;
}
	
	
/*
 * public static void main(String arg[]) throws IOException {
 * 
 * String arr[][]= new DataProviders().getAllData(); for (int i = 0; i <
 * arr.length; i++) { for (int j = 0; j < arr[i].length; j++) { }
 * System.out.print(arr[i][j] + " "); } System.out.println(); // Move to the
 * next line after each row }
 * 
 * 
 * String as []= new DataProviders().getUserNames(); for(int
 * i=0;i<as.length;i++) { System.out.println(as[i]); } }
 */
	
}
