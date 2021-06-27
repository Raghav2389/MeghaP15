package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	static Workbook workbk;
	static Sheet sht;
	//public static String path="src\\test\\resources\\searchTestData.xls";
	
	
	public static Object[][] getWatchlistTestData(){
		ConfigFileReader configFileReader= new ConfigFileReader();

		FileInputStream file=null;
	
		try {
			file=new FileInputStream(configFileReader.getWatchlistTestDataPath());
		
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		try {
			workbk=WorkbookFactory.create(file); //open file
			sht=workbk.getSheet("Sheet1");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Object[][] data= new Object[sht.getLastRowNum()][sht.getRow(0).getLastCellNum()];
		for(int i=0;i<sht.getLastRowNum();i++)
		{
			for(int k=0;k<sht.getRow(0).getLastCellNum();k++)
			{
				data[i][k]=sht.getRow(i+1).getCell(k).toString();
				System.out.println(data[i][k]);
			}
		}
		return data;
		
	}
	public static Object[][] getLoginTestData(){
		ConfigFileReader configFileReader= new ConfigFileReader();

		FileInputStream file=null;
	
		try {
			file=new FileInputStream(configFileReader.getLoginTestDataPath());
		
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		try {
			workbk=WorkbookFactory.create(file); //open file
			sht=workbk.getSheet("Sheet1");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		Object[][] data= new Object[sht.getLastRowNum()][sht.getRow(0).getLastCellNum()];
		//System.out.println(sht.getLastRowNum());
		for(int i=0;i<sht.getLastRowNum();i++)
		{
			for(int k=0;k<sht.getRow(0).getLastCellNum();k++)
			{
				data[i][k]=sht.getRow(i+1).getCell(k).toString();
				System.out.println(data[i][k]);
			}
		}
		return data;
		
	}
		
		


}
