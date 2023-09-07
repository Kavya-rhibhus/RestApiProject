package com.FactoApiTesting;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.baseclass.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class FactoFunctApiTest extends BaseClass{
	public ExtentReports extent;
	public ExtentTest test;
	
	public String jwtTokenFacto;
	
	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void setUp() {

		// For date and time

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formattedDate = dateFormat.format(currentDate);
		// Get the current time
		Date currentTime = new Date();
		// Create a SimpleDateFormat object with the desired format
		SimpleDateFormat sdf = new SimpleDateFormat("hh'.'mm'.'ss");
		// Format the current time using the SimpleDateFormat object
		String formattedTime = sdf.format(currentTime);
		System.out.println("Current Date: " + formattedDate);
		System.out.println("Current Time: " + formattedTime);
		ReportName = "rafflox-" + formattedDate + "&" + formattedTime + ".html";
		//System.out.println("reportLocation: " + RafflouxReportLoginPath);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(FactoReportPath+ ReportName);
		System.out.println("reportLocation ReportName: " +  FactoReportPath+ ReportName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}
			
 
	@BeforeMethod
	public void beforeMethod() {
		test = extent.createTest("Raffloux-ReDev Rest API", "API Testing both Positive and negative");
	}
	
	
	@Test(priority = 1)
	public void FactoRestApiLoginValidations()  

	{
		int ExpectedStatusCode = 200;
		String Payload = "{\r\n"
				+ "    \"data\": {\r\n"
				+ "        \"user_email\": \"channabasavaraju.suresh@rhibhus.com\",\r\n"
				+ "        \"user_password\": \"Raju@123\"\r\n"
				+ "    }\r\n"
				+ "}";
	//	String EndPoint = "/v1/signIn";
		String Expectedjwt = "eyJ1c2VyX2lkIjoxNTEwNjMsInVzZXJfZW1haWwiOiJ2ZWVyYS5yYWdoYXZhbkByaGliaHVzLmNvbSIsInVzZXJfZmlyc3RfbmFtZSI6InZlZXJhIiwidXNlcl9sYXN0X25hbWUiOiJyYWdoYXZhbiIsImlhdCI6MTY4NTMzMjEwM30";
        String Method="post";
        String APIFunctionName="Login";
        		String EndPoint="/v1/login";
		Response response = RestAssured

				.given()

				//.baseUri("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing")
				.contentType(ContentType.JSON).body(Payload)

				.when()

				.post("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing/v1/login")

				.then()

				.extract()

				.response();
		// To ActualStatusCode from response body and To Print
		int ActualStatusCode = response.getStatusCode();

		// To ActualResponseBody from response body and To print
		String ActualResponseBody= response.getBody().asString();
		
		
	
		//String subString = str.substring(startIndex, endIndex);
		 jwtTokenFacto = ActualResponseBody.substring(9, ActualResponseBody.lastIndexOf("]")-2);
		 //System.out.println( ActualResponseBody+ " ActualResponseBody is");
         System.out.println("actual response "+ActualResponseBody );


		// To print and To validate both (exp and act status code)
		System.out.println("Actual status code is " + "=>" + ActualStatusCode + "=>" + "-AND-" + "expected status code" + "=>"
				+ ExpectedStatusCode);
		Assert.assertEquals(ActualStatusCode, ExpectedStatusCode, "Expected status code is  Incorrect");

		// To print response body
		Reporter.log(
				"Actual Response body" + " =>" + jwtTokenFacto + "=>" + "Expected Response Body is" + "=>" + Expectedjwt);
	//	Assert.assertEquals(jwtToken.contains(Expectedjwt), true, "Incorrect Expeted body");
		test.log(Status.INFO,"Method is:" +Method);
		test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
		test.log(Status.INFO,"EndPoint:"+ EndPoint);
	    test.log(Status.INFO,"Payload :"+Payload);
		System.out.println(" status code is " + ActualStatusCode);
        System.out.println("json Token "+ jwtTokenFacto);
	}
	


	@Test(priority=2,  dataProvider = "Facto", dataProviderClass = DataProviderExcelReader.class)

	public void FactoRestApiLoginValidations(String Rownum ,String APIFunctionName, String Method, String EndPoint, String Payload,
			double ExpectedStatusCodeDouble,String ExpectedResponseBody)


	{
	     String jwtToken1=jwtTokenFacto;
	  //   System.out.println(jwtToken1);
	     
		 test.log(Status.INFO, "Starting the APItest");
		 Response response = RestAssured

				.given()
				
				.header("Authorization", jwtToken1)

				.baseUri("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing")
			
				.contentType(ContentType.JSON).body(Payload)

				.when()
 
				.post(EndPoint)

				.then()

				.extract()

				.response();
		 test.log(Status.INFO,"RowNum is :"+ Rownum+  "and "  +"Method is:" +Method);
		 test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
		 test.log(Status.INFO,"EndPoint:"+ EndPoint);
	     test.log(Status.INFO,"Payload :"+Payload);
	     test.log(Status.INFO,"ExpectedResponseBody is"+ ExpectedResponseBody );
		// To ActualStatusCode from response body and To Print
		int ActualStatusCode = response.getStatusCode();
		System.out.println(" status code is " + ActualStatusCode);
		
		test.log(Status.INFO, "ActualStatusCode is :" + ActualStatusCode);

		// To ActualResponseBody from response body and To print
		String ActualResponseBody = response.getBody().asString();
//		System.

		test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);

		// To ActualHeaderContentType from response body and To print
		String ActualHeaderContentType = response.header("Content-Type");

	//	test.log(Status.INFO, "ActualHeaderContentType is  :" + ActualHeaderContentType);
		// To convert from double to integer (while fetching will get integer as double)
		// and to print
		int ExpectedStatusCode = (int) ExpectedStatusCodeDouble;

		try {
			Assert.assertEquals(ActualStatusCode, ExpectedStatusCode, "Expected status code is  Incorrect");
			test.log(Status.PASS, "Assertion passed");

		} catch (AssertionError e) {  

			test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
			System.err.println("Test failed: Assertion failed");
		}


		test.log(Status.PASS, Rownum+ "row datas are executed successfully");

//	if(ExpectedResponseBody.contains(ActualResponseBody)) {

try {
		 Assert.assertTrue(ActualResponseBody.contains(ExpectedResponseBody));
		test.log(Status.PASS, "Assertion passed");
        
	} catch (AssertionError e) {  

		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
		System.err.println("Test failed: Assertion failed");
	}
	}
	/*else {
		
	test.log(Status.FAIL, "Expected body is not matching actual response body");
	}*/

	
//}



	@AfterMethod
	public void afterMethod() {
		extent.flush();
	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
	}
}
