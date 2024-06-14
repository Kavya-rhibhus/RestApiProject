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

	public static String F_NAME = "C:\\Users\\HP\\eclipse\\newapitestingautomation\\src\\test\\java\\com\\MonacoTesting\\Api_Excel_Data\\Monaco_Client_Api's_online.xlsx";
	// public static String S_name="Negative";
	public static String S_name = "Sheet2";
	public String mwcAdminPortalReportLoc = "C:\\Users\\HP\\eclipse\\newapitestingautomation\\src\\test\\java\\NewFrameWork\\Report\\";
	public String MwcAdminPortalReportName = "MWCAdmin-Negative-Testcases-";
	public String adminJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyZXF1ZXN0Ijp7InVzZXJfaWQiOiJVc2VyIzU3OTU0MWU1LWJiY2EtNGFlNS1hNjNlLTBiNDc4MzkzNDg4MiIsInVzZXJfZW1haWwiOiJndWVzdDEyQGdtYWlsLmNvbSIsInVzZXJfZmlyc3RfbmFtZSI6Imd1ZXN0IiwidXNlcl9sYXN0X25hbWUiOiIxMiIsImlzU29jaWFsTWVkaWEiOmZhbHNlfSwiaWF0IjoxNzE2MzU2MDExfQ.aJVvCC6Q6cQPirFq8ppwlyl2H4CHnL_3gUBoRK4xKjQ";
	public String adminBaseUrl = "https://5x8cmx277h.execute-api.ap-south-2.amazonaws.com/testing";

	public static ExtentReports extent;
	public static ExtentTest test;

	public String jwtToken;
	public String baseurl = "https://5x8cmx277h.execute-api.ap-south-2.amazonaws.com/testing";

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
		String ReportName = "MWC_Client-Negative_Test" + formattedDate + "&" + formattedTime + ".html";
		// System.out.println("reportLocation: " + RafflouxReportLoginP/ath);
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(mwcAdminPortalReportLoc + ReportName);
		System.out.println("reportLocation ReportName: " + mwcAdminPortalReportLoc + ReportName);
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
