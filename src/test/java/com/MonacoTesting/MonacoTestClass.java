package com.MonacoTesting;

import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.json.JSONObject;
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

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.baseclass.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class MonacoTestClass extends BaseClass{
	public ExtentReports extent;
	public ExtentTest test;
	
	public String jwtToken;
	public String baseurl="https://5x8cmx277h.execute-api.ap-south-2.amazonaws.com/testing";
	
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
		ReportName = "MWC_Client-Negative_Test" + formattedDate + "&" + formattedTime + ".html";
		//System.out.println("reportLocation: " + RafflouxReportLoginPath);
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(MWCReportPath+ ReportName);
		System.out.println("reportLocation ReportName: " + MWCReportPath+ ReportName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}
			
	
 
//	@BeforeMethod
//	public void beforeMethod() {
//		test = extent.createTest("Monaco Client  APIs", "API Testing both Positive and negative");
//	}
//	
	
	
	@Test(priority = 1)
	public void RestApiLoginValidations()

	{
		int ExpectedStatusCode = 200;
		String Payload = "{\r\n"
				+ "    \"email\": \"channabasavaraju.suresh@rhibhus.com\",\r\n"
				+ "    \"password\": \"Raju@123\"\r\n"
				+ "}";
	//	String EndPoint = "/v1/signIn";
		String Expectedjwt = "eyJ1c2VyX2lkIjoxNTEwNjMsInVzZXJfZW1haWwiOiJ2ZWVyYS5yYWdoYXZhbkByaGliaHVzLmNvbSIsInVzZXJfZmlyc3RfbmFtZSI6InZlZXJhIiwidXNlcl9sYXN0X25hbWUiOiJyYWdoYXZhbiIsImlhdCI6MTY4NTMzMjEwM30";
        String Method="post";
        String APIFunctionName="Login";
        		String EndPoint="/v1/userLogin";
		Response response = RestAssured

				.given()

				//.baseUri("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing")
				.contentType(ContentType.JSON).body(Payload)

				.when()

				.post("https://v46z8c6kn5.execute-api.ap-south-1.amazonaws.com/testing")

				.then()

				.extract()

				.response();
		// To ActualStatusCode from response body and To Print
		int ActualStatusCode = response.getStatusCode();
		String ActualResponseBody= response.getBody().asString();
		// To ActualResponseBody from response body and To prin
		 System.out.println("actual response "+ActualResponseBody );

		//String subString = str.substring(startIndex, endIndex);
		// jwtToken = ActualResponseBody.substring(30, ActualResponseBody.lastIndexOf("}")-3);
		//String ActualResponseBody= response.getBody().asString();
		JSONObject jsonObject = new JSONObject(ActualResponseBody);
		jwtToken = jsonObject.getJSONObject("status").getString("jwt");
		 //System.out.println( ActualResponseBody+ " ActualResponseBody is");
         System.out.println("actual response "+ActualResponseBody );


		// To print and To validate both (exp and act status code)
		System.out.println("Actual status code is " + "=>" + ActualStatusCode + "=>" + "-AND-" + "expected status code" + "=>"
				+ ExpectedStatusCode);
		Assert.assertEquals(ActualStatusCode, ExpectedStatusCode, "Expected status code is  Incorrect");

		// To print response body
		Reporter.log(
				"Actual Response body" + " =>" + jwtToken + "=>" + "Expected Response Body is" + "=>" + Expectedjwt);
	//	Assert.assertEquals(jwtToken.contains(Expectedjwt), true, "Incorrect Expeted body");
		test.log(Status.INFO,"Method is:" +Method);
		test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
		test.log(Status.INFO,"EndPoint:"+ EndPoint);
	    test.log(Status.INFO,"Payload :"+Payload);
		System.out.println(" status code is " + ActualStatusCode);
       System.out.println("jwtToken:" +jwtToken);
	}
	


	@Test(priority=2,  dataProvider = "MWCData", dataProviderClass = DataProviderExcelReader.class)

	public void MWCRestApiFuntValidations(String Rownum ,String APIFunctionName, String EndPoint, String Method, String Payload,
			double ExpectedStatusCodeDouble,String ExpectedResponseBody)


	{
		test = extent.createTest(EndPoint + "API Testing both Positive and negative");
		jwtToken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyZXF1ZXN0Ijp7InVzZXJfaWQiOiJVc2VyIzU3OTU0MWU1LWJiY2EtNGFlNS1hNjNlLTBiNDc4MzkzNDg4MiIsInVzZXJfZW1haWwiOiJndWVzdDEyQGdtYWlsLmNvbSIsInVzZXJfZmlyc3RfbmFtZSI6Imd1ZXN0IiwidXNlcl9sYXN0X25hbWUiOiIxMiIsImlzU29jaWFsTWVkaWEiOmZhbHNlfSwiaWF0IjoxNzE2MzU2MDExfQ.aJVvCC6Q6cQPirFq8ppwlyl2H4CHnL_3gUBoRK4xKjQ";
		
		if(Method.equals("POST"))

		{
	     String jwtToken1=jwtToken;
	    // System.out.println(jwtToken1);
		 test.log(Status.INFO, "Starting the APItest");
		 Response response = RestAssured

				.given() 
				
				.header("Authorization", jwtToken1)

				.baseUri(baseurl)
			
				.contentType(ContentType.JSON).body(Payload)

				.when()
 
				.post(EndPoint)

				.then()

				.extract()

				.response();
		 test.log(Status.INFO,"RowNum is :"+ Rownum+  "and "  +"Method is:" +Method);
		test.log(Status.INFO,"Testcase >: "+APIFunctionName);
		test.log(Status.INFO,"EndPoint:"+ EndPoint);
	    test.log(Status.INFO,"Payload :"+Payload);
		
		// To ActualStatusCode from response body and To Print
		int ActualStatusCode = response.getStatusCode();
		System.out.println(" status code is " + ActualStatusCode);
		
		test.log(Status.INFO, "ActualStatusCode is :" + ActualStatusCode);

		// To ActualResponseBody from response body and To print
		String ActualResponseBody = response.getBody().asString();

        if (ActualResponseBody.length() <= 400) {

		test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
        }
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

	}
	
	
	
	else if(Method.equals("GET"))

	{ 
		String jwtToken1=jwtToken;
    // System.out.println(jwtToken1);
		test.log(Status.INFO, "Starting the APItest");
		 Response response = RestAssured

				.given() 
				
				.header("Authorization", jwtToken1)

				.baseUri(baseurl)
			
				.contentType(ContentType.JSON)
				.when()

				.get(EndPoint)

				.then()

				.extract()

				.response();
		 test.log(Status.INFO,"RowNum is :"+ Rownum+  "and "  +"Method is:" +Method);
		test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
		test.log(Status.INFO,"EndPoint:"+ EndPoint);
	    test.log(Status.INFO,"Payload :"+Payload);
		
		// To ActualStatusCode from response body and To Print
		int ActualStatusCode = response.getStatusCode();
		System.out.println(" status code is " + ActualStatusCode);
		
		test.log(Status.INFO, "ActualStatusCode is :" + ActualStatusCode);

		// To ActualResponseBody from response body and To print
		String ActualResponseBody = response.getBody().asString();
if(ActualResponseBody.length()<=400) {
		test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
}

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

	}
	
		
		
	else if(Method.equals("DELETE"))

	{ 
		String jwtToken1=jwtToken;
    // System.out.println(jwtToken1);
		test.log(Status.INFO, "Starting the APItest");
		 Response response = RestAssured

				.given() 
				
				.header("Authorization", jwtToken1)

				.baseUri(baseurl)
			
				.contentType(ContentType.JSON).body(ExpectedResponseBody)
				.when()

				.delete(EndPoint)

				.then()

				.extract()

				.response();
		 test.log(Status.INFO,"RowNum is :"+ Rownum+  " and "  +"Method is:" +Method);
		test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
		test.log(Status.INFO,"EndPoint:"+ EndPoint);
	    test.log(Status.INFO,"Payload :"+Payload);
		
		// To ActualStatusCode from response body and To Print
		int ActualStatusCode = response.getStatusCode();
		System.out.println(" status code is " + ActualStatusCode);
		
		test.log(Status.INFO, "ActualStatusCode is :" + ActualStatusCode);

		// To ActualResponseBody from response body and To print
		String ActualResponseBody = response.getBody().asString();
if(ActualResponseBody.length()<=400) {
		test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
}

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

	}
	
		
		
		
	
	else if(Method.equals("PUT"))

	{ 
		String jwtToken1=jwtToken;
    // System.out.println(jwtToken1);
		test.log(Status.INFO, "Starting the APItest");
		 Response response = RestAssured

				.given() 
				
				.header("Authorization", jwtToken1)

				.baseUri(baseurl)
			
				.contentType(ContentType.JSON).body(ExpectedResponseBody)
				.when()

				.put(EndPoint)

				.then()

				.extract()

				.response();
		 test.log(Status.INFO,"RowNum is :"+ Rownum+  "and "  +"Method is:" +Method);
		test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
		test.log(Status.INFO,"EndPoint:"+ EndPoint);
	    test.log(Status.INFO,"Payload :"+Payload);
		
		// To ActualStatusCode from response body and To Print
		int ActualStatusCode = response.getStatusCode();
		System.out.println(" status code is " + ActualStatusCode);
		
		test.log(Status.INFO, "ActualStatusCode is :" + ActualStatusCode);

		// To ActualResponseBody from response body and To print
		String ActualResponseBody = response.getBody().asString();
if(ActualResponseBody.length()<=400) {
		test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
           }

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

	}
	
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
