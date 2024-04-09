package onlyforothertest;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.testng.annotations.DataProvider;

public class DataProviders {
	


	@DataProvider(name = "Data")
	public Object[][] getAllData() throws IOException {

		String path = System.getProperty("user.dir") + "\\testData\\Userdata.xlsx";
		System.out.println(path);
		XLUtility s = new XLUtility(path);
		int row = s.getRowCount("Sheet1");
		  int col = s.getCellCount("Sheet1", row);
		 
        Object apidata[][] = new Object[row][1];
		for (int i = 1; i <= row; i++) {
			Hashtable<Object, Object> taHashtable = new Hashtable<Object, Object>();
			for (int j = 0; j < col; j++) {
			taHashtable.put(s.getCellData("Sheet1", 0, j),  s.getCellData("Sheet1", i, j));
			
			}
			apidata[i - 1][0]=taHashtable ;
		}

		return apidata;
	}

	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException {
		String path = System.getProperty("user.dir") + "\\testData\\Userdata.xlsx";
		XLUtility s = new XLUtility(path);
		int row = s.getRowCount("Sheet1");
		String apidata[] = new String[row];
		for (int i = 1; i <= row; i++) {
			apidata[i - 1] = s.getCellData("Sheet1", i, 1);
		}
		return apidata;
	}

/**
 * @param arg
 * @throws IOException
 */
public static void main(String arg[]) throws IOException {
  
 /* Object arr[][]= new DataProviders().getAllData();
  for (int i = 0; i <  arr.length; i++) { 
	  for (int j = 0; j < arr[i].length; j++) { 
	 
  } System.out.println(); // Move to the   next line after each row }
  }*/
/*	String as []= new DataProviders().getUserNames();
	for(int i = 0;i<as.length;i++)
	{
		System.out.println(as[i]);
	}*/
  
/*  for (String key : taHashtable.keySet()) {
      System.out.println("Key: " + key);
      System.out.println("Value :  " + taHashtable.get(key));
  }*/
  
 /* Map<String, String> taHashtable1 = new Hashtable<String, String>();
  taHashtable1.put("abc1", "xyz");
  Hashtable<String, String> taHashtable2 = new Hashtable<String, String>();
  taHashtable2.put("abc2", "xyz");

  Hashtable<String, String> taHashtable3 = new Hashtable<String, String>();
  taHashtable2.put("ab2", "xyz");
  
  Object ab[] = {taHashtable1,taHashtable2,taHashtable3};
  
  for(int i = 0 ;i<ab.length;i++) {
	  Hashtable<String, String> tesmp = (Hashtable)ab[i];
 	  for (String key :tesmp.keySet()) {
	      System.out.println("Key: " + key);
	      System.out.println("Value :  " + tesmp.get(key));
	  }
  }*/
	
Object ab[][] = new DataProviders().getAllData();
System.out.println("Array length "+ab.length);
	for(int i = 0 ;i<ab.length;i++) {
		  Hashtable<String, String> tesmp = (Hashtable)ab[i][0];
	 	  for (String key :tesmp.keySet()) {
		      System.out.print("Key: " + key +" "+tesmp.get(key));
		      System.out.println();
		  }
	  }
	
}


}
