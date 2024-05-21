package com.SpryntzApiTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.baseclass.BaseClass;

public class DataProviderExcelReader extends BaseClass{
	
static String filepath="C:\\Users\\HP\\eclipse\\newapitestingautomation\\src\\test\\java\\com\\SpryntzApiTesting\\Api-List-Spryntz\\Spryntz API functional testing format.xlsx";
//static String restSheet="Restaurant_Onboarding";
	static String chefSheet="Chef_Onboarding"; 
	static String fleetSheet="Fleet_Provider_Onboarding";
static String custSheet="End_User_Registration"; 
 //static String custSheet="Customer_B.O";
			static String restSheet="Restaurant_B.O";
					//static String restSheet="Rest_Fleet_Driver";
	  
	//static String sheetnameCust="End_User_Registration";
		@DataProvider(name = "RestaurantOnboarding")
	    public static Object[][] readExcelData() throws IOException {
	        List<Object[]> data = new ArrayList<>();
	        File file = new File(filepath);
	          FileInputStream inputStream = new FileInputStream(file);
	        Workbook workbook = WorkbookFactory.create(inputStream);
	        Sheet sheet = workbook.getSheet(restSheet);
	        int rowCount = sheet.getLastRowNum();
	         for (int i = 1; i <= rowCount; i++) { // start from second row  
	            Row row = sheet.getRow(i);
	             int  rownumber=  row.getRowNum();
	             System.out.println("row number is :"+ rownumber);
	            Object[] rowValues = new Object[row.getLastCellNum()];
	            for (int j = 0; j < row.getLastCellNum(); j++) 
	            
	            {
	                Cell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
	              //  System.out.println("cell============"+cell);
	                
	                if (cell != null) {
	                    rowValues[j] = getCellValue(cell);
	                } else {
	                    rowValues[j] = null;
	                }
	            }
	            
	         data.add(rowValues);
	        }
	        
	        
	        
	        return data.toArray(new Object[0][0]);
	    }

	    private static Object getCellValue(Cell cell) {
	        CellType cellType = cell.getCellType();
	        switch (cellType) {
	            case BOOLEAN:
	                return cell.getBooleanCellValue();
	            case NUMERIC:
	                return cell.getNumericCellValue();
	            case STRING:
	                return cell.getStringCellValue();
	            default:
	                return null;
	        }
	    }
	    

	   
	    @DataProvider(name = "chef")
	    public static Object[][] readExcelData13() throws IOException {
	        List<Object[]> data = new ArrayList<>();
	        File file = new File(filepath);
	          FileInputStream inputStream = new FileInputStream(file);
	        Workbook workbook = WorkbookFactory.create(inputStream);
	        Sheet sheet = workbook.getSheet(chefSheet);
	        int rowCount = sheet.getLastRowNum();
	         for (int i = 1; i <= rowCount; i++) { // start from second row  
	            Row row = sheet.getRow(i);
	             int  rownumber=  row.getRowNum();
	             System.out.println("row number is :"+ rownumber);
	            Object[] rowValues = new Object[row.getLastCellNum()];
	            for (int j = 0; j < row.getLastCellNum(); j++) 
	            
	            {
	                Cell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
	              //  System.out.println("cell============"+cell);
	                
	                if (cell != null) {
	                    rowValues[j] = getCellValue(cell);
	                } else {
	                    rowValues[j] = null;
	                }
	            }
	            
	         data.add(rowValues);
	        }
	        
	        
	        
	        return data.toArray(new Object[0][0]);
	    }
	    @DataProvider(name = "cust")
	    public static Object[][] readExcelData1() throws IOException {
	        List<Object[]> data = new ArrayList<>();
	        File file = new File(filepath);
	          FileInputStream inputStream = new FileInputStream(file);
	        Workbook workbook = WorkbookFactory.create(inputStream);
	        Sheet sheet = workbook.getSheet(custSheet);
	        int rowCount = sheet.getLastRowNum();
	         for (int i = 1; i <= rowCount; i++) { // start from second row  
	            Row row = sheet.getRow(i);
	             int  rownumber=  row.getRowNum();
	             System.out.println("row number is :"+ rownumber);
	            Object[] rowValues = new Object[row.getLastCellNum()];
	            for (int j = 0; j < row.getLastCellNum(); j++) 
	            
	            {
	                Cell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
	              //  System.out.println("cell============"+cell);
	                
	                if (cell != null) {
	                    rowValues[j] = getCellValue(cell);
	                } else {
	                    rowValues[j] = null;
	                }
	            }
	            
	         data.add(rowValues);
	        }
	        
	        
	        
	        return data.toArray(new Object[0][0]);
	    }
	    @DataProvider(name = "fleet")
	    public static Object[][] readExcelData11() throws IOException {
	        List<Object[]> data = new ArrayList<>();
	        File file = new File(filepath);
	          FileInputStream inputStream = new FileInputStream(file);
	        Workbook workbook = WorkbookFactory.create(inputStream);
	        Sheet sheet = workbook.getSheet(fleetSheet);
	        int rowCount = sheet.getLastRowNum();
	         for (int i = 1; i <= rowCount; i++) { // start from second row  
	            Row row = sheet.getRow(i);
	             int  rownumber=  row.getRowNum();
	             System.out.println("row number is :"+ rownumber);
	            Object[] rowValues = new Object[row.getLastCellNum()];
	            for (int j = 0; j < row.getLastCellNum(); j++) 
	            
	            {
	                Cell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
	              //  System.out.println("cell============"+cell);
	                
	                if (cell != null) {
	                    rowValues[j] = getCellValue(cell);
	                } else {
	                    rowValues[j] = null;
	                }
	            }
	            
	         data.add(rowValues);
	        }
	        
	        
	        
	        return data.toArray(new Object[0][0]);
	    }


}
