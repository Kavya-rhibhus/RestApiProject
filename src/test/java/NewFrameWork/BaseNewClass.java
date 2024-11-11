package NewFrameWork;

import java.text.SimpleDateFormat;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseNewClass {
	
	public static String ExcelFolder = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\ExcelData\\";
    public static String ExtentReport = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\Reports\\";
	
    
    //  MWC -client portal
    public String mwcClientJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyZXF1ZXN0Ijp7InVzZXJfaWQiOiJVc2VyI2IyYmQ3YTA4LWE2ZTctNGVjMC1hODcwLTkyNzIyNTY3ZjlkYiIsInVzZXJfZW1haWwiOiJ0ZXN0MTIzNEBnbWFpbC5jb20iLCJ1c2VyX2ZpcnN0X25hbWUiOiJzYWQiLCJ1c2VyX2xhc3RfbmFtZSI6ImFzYXNkIiwiaXNTb2NpYWxNZWRpYSI6ZmFsc2V9LCJpYXQiOjE3MjAxNTkyNDl9.OTvthpZvQKYV5PpgE2uREuiHff18CLr5qgz_WQcXCCU";
    public String mwcClientBaseUrl = "https://5x8cmx277h.execute-api.ap-south-2.amazonaws.com/testing";
//	public static String F_NAME = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\ExcelData\\Monaco_Client_Api's_online.xlsx";
//    public static String S_name="Negative";
//    public String ReportName = "MWC-Client-Negative-Testcases-";
//	public String ReportLoc = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\Reports\\MWC-ClientPortal-Report\\";
	
    
    //	Raffle Tech project admin portal
	
	 public String rtAdminJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyZXF1ZXN0Ijp7InVzZXJfaWQiOiJVc2VyI3ByMjU1NGk2LXkxYTEtNDFuay1hNzIzLTJqNjc1ODMyNDAxMCIsInVzZXJfZW1haWwiOiJwcml5YW5rYS5qYXlha3VtYXJhbkByaGliaHVzLmNvbSIsInVzZXJfZmlyc3RfbmFtZSI6InByaXlhbmthIiwidXNlcl9sYXN0X25hbWUiOiJqIiwiaXNTb2NpYWxNZWRpYSI6dHJ1ZX0sImlhdCI6MTcyODg4MDczOCwiZXhwIjoxNzI4OTIzOTM4fQ.XVa_EL6tUP5w7BmNcQIc5Le9g8LjExSKD6tZuGBETC4";
     public String rtadminBaseUrl = "https://kd9j8uwoek.execute-api.ap-south-2.amazonaws.com/testing";
  /*  public String ReportName = "RaffleTech-Admin-Negative-Testcases-";
  	 public String ReportLoc = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\Reports\\Raffle-Tech-AdminPortal-Report\\";
     public static String F_NAME ="C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\ExcelData\\Raise_Hero APIs.xlsx";
	 public static String S_name="Negative-Admin";*/
////	x
//	   
      
     
     //raffle tech client portal
     
	 public String rtClientJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyZXF1ZXN0Ijp7InVzZXJfaWQiOiJVc2VyIzQ3NTdhYWZiLTQ2NjMtNDk5Zi05MTIwLTQ2MTYwZDE0OWI1MyIsInVzZXJfZW1haWwiOiJrYXZ5YWNsaWVudEBnbWFpbC5jb20iLCJ1c2VyX2ZpcnN0X25hbWUiOiJrYXZ5YWNsaWVudHdld2UiLCJ1c2VyX2xhc3RfbmFtZSI6IkNsaWVudHd3ZXdldyIsImlzU29jaWFsTWVkaWEiOnRydWV9LCJpYXQiOjE3Mjg4ODExMzgsImV4cCI6MTcyODg4NDczOH0.J7_NLY3iyNyPfpY2X2IBe1foMJmS9sj0thnJjGXe6Js";
     public String rtClientBaseUrl = "https://kd9j8uwoek.execute-api.ap-south-2.amazonaws.com/testing";
    /* public String ReportName = "RaiseHero-Client-Negative-Testcases-";
  	 public String ReportLoc = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\Reports\\Raffle-Tech-Client-Report\\";
     public static String F_NAME ="C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\ExcelData\\Raise_Hero APIs.xlsx";
     public static String S_name="RT_client_negative";*/
     
     
     
     
     
     
     
     //Cross-selling
     
    public String crossSellingJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoyMDY4NTAsInVzZXJfZW1haWwiOiJra2tAZ21haWwudWsiLCJ1c2VyX2ZpcnN0X25hbWUiOiJoaCIsInVzZXJfbGFzdF9uYW1lIjoiaGgiLCJpc1NvY2lhbE1lZGlhIjpmYWxzZSwiaWF0IjoxNzI3MDg4NDkwfQ.Svj2gqzIkWT5Crdp6rpCxCv7rX7aAPs1PRl3hLKm28Y";
 	public String CrossSellingBaseUrl = "https://eywn06n2u9.execute-api.eu-west-2.amazonaws.com/testing";
 //	public String ReportName = "CrossSelling-Negative-Testcases-";
 //	public String ReportLoc = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\Reports\\Redev-Report\\";
// 	public static String F_NAME = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\ExcelData\\Monaco_Client_Api's_online.xlsx";
 //	public static String S_name="Cross-selling";
//     
     
     
     
     
     
     
	//Raffle tech Project user portal  
	 
	 public String domainname = "https://testing.d2sqce4gy9zdlb.amplifyapp.com";
     public String rtuserBaseUrl = "https://kd9j8uwoek.execute-api.ap-south-2.amazonaws.com/testing";
    public String ReportName = "RaffleTech-user-Negative-Testcases-";
	 public String ReportLoc = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\Reports\\Raffle-Tech-userportal-Report\\";
    public static String F_NAME = ExcelFolder+ "Raise_Hero APIs.xlsx";
	 public static String S_name="Negative-UserRT";
//	  
	 
	 
	 
	 //Redev raffolux api's
	 
	  public String RedevJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxODM0ODIsInVzZXJfZW1haWwiOiJna3Jpc2huYW11cnRoeTQ0M0BnbWFpbC5jb20iLCJ1c2VyX2ZpcnN0X25hbWUiOiJrYXZ5YXByZSIsInVzZXJfbGFzdF9uYW1lIjoicHJlIiwiaXNTb2NpYWxNZWRpYSI6ZmFsc2UsImlhdCI6MTcyODYyNzYwM30.piFsPnV9iGSNJ2GWTU0L3glmwSR0vcTXZFsfWo4vK_Q";
	  public String RedevBaseUrl = "https://eywn06n2u9.execute-api.eu-west-2.amazonaws.com/testing";
		//public String ReportName = "Redev-Negative-Testcases-";
	//	public String ReportLoc = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\Reports\\Redev_Raffolux\\";
     //  public static String F_NAME = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\ExcelData\\RD-Raffloux-final-ApiList.xlsx";
	//	public static String S_name="Api-final-pn";
	  
	 
	 
	 
	 
	//MWC Project Admin portal 
	
	public String mwcadminJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyZXF1ZXN0Ijp7InVzZXJfaWQiOiJVc2VyI2IzOWM0MzhmLWE4NjItNGMzOS04NTAwLTJkYTEwN2VkYzhmMyIsInVzZXJfZW1haWwiOiJkZXZAcmhpYmh1cy5jb20iLCJ1c2VyX2ZpcnN0X25hbWUiOiJEZXYiLCJ1c2VyX2xhc3RfbmFtZSI6IlJoaWJodXMiLCJpc1NvY2lhbE1lZGlhIjp0cnVlfSwiaWF0IjoxNzE5OTAyMjAwLCJleHAiOjE3MTk5ODg2MDB9.B6tdXloYsjU8bE7mvqj3676_mO0d8LQxSbmiwR53bcA";
	public String mwcadminBaseUrl = "https://g669q1e8se.execute-api.ap-south-2.amazonaws.com/testing";
//	public String ReportName = "MWCAdmin-Negative-Testcases-";
//	public String ReportLoc = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\Reports\\MWC-AdminPortal-Report\\";
//	public static String F_NAME = "C:\\Users\\Rhibhus\\git\\newapitestingautomation-89\\ExcelData\\AdminPortalFinalApiListMWCxlsx.xlsx";
//	public static String S_name="AllNegativeTestCase";
//	
	 
	
	
	//Report variable 
	public static ExtentReports extent;
	public static ExtentTest test;



	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void setUp() {

		// For date and time 

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = dateFormat.format(currentDate);
		// Get the current time
		Date currentTime = new Date();
		// Create a SimpleDateFormat object with the desired format
		SimpleDateFormat sdf = new SimpleDateFormat("hh'.'mm'.'ss");
		// Format the current time using the SimpleDateFormat object
		String formattedTime = sdf.format(currentTime);
		System.out.println("Current Date: " + formattedDate);
		System.out.println("Current Time: " + formattedTime);
		String ReportName1 =  ReportName + formattedDate + "&" + formattedTime + ".html";
		// System.out.println("reportLocation: " + RafflouxReportLoginP/ath);
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(ReportLoc + ReportName1);
		System.out.println("reportLocation ReportName: " + ReportLoc + ReportName1);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	} 
 
 
	@AfterMethod
	public void afterMethod() {
		
		extent.flush();
	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
	}

}
