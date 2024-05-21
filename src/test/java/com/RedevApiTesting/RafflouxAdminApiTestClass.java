package com.RedevApiTesting;

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
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.baseclass.BaseClass;

import ChariotApiTesting.DataProviderExcelReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class RafflouxAdminApiTestClass extends BaseClass{
	public ExtentReports extent;
	public ExtentTest test;
	
	public String jwtToken;
	
	public String baseurl ="https://j1z4ckpexh.execute-api.eu-west-2.amazonaws.com/testing";
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
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(RedevRaffoluxReportPath+ ReportName);
		System.out.println("reportLocation ReportName: " + RedevRaffoluxReportPath+ ReportName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}
			
	
 
	@BeforeMethod
	public void beforeMethod() {
		test = extent.createTest("Raffloux-ReDev Rest API", "API Testing both Positive and negative");
	}
	
	
	
	//@Test(priority = 1)
	public void RestApiLoginValidations()

	{
		int ExpectedStatusCode = 200;
		String Payload = "{\r\n"
				+ "    \"email\":\"kavyaapk@gmail.com\",\r\n"
				+ "    \"password\": \"123456\"\r\n"
				+ "}";
	//	String EndPoint = "/v1/signIn";
		String Expectedjwt = "eyJ1c2VyX2lkIjoxNTEwNjMsInVzZXJfZW1haWwiOiJ2ZWVyYS5yYWdoYXZhbkByaGliaHVzLmNvbSIsInVzZXJfZmlyc3RfbmFtZSI6InZlZXJhIiwidXNlcl9sYXN0X25hbWUiOiJyYWdoYXZhbiIsImlhdCI6MTY4NTMzMjEwM30";
        String Method="post";
        String APIFunctionName="Login";
        		String EndPoint="/v1/signIn";
		Response response = RestAssured

				.given()

				//.baseUri("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing")
				.contentType(ContentType.JSON).body(Payload)

				.when()

				.post("https://3trw0haj02.execute-api.eu-west-2.amazonaws.com/testing/v1/signIn")

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
	


	@Test(priority=2,  dataProvider = "FunctSheet", dataProviderClass = DataProviderExcelReader.class)

	public void RafflouxRestApiFuntValidations(String Rownum ,String APIFunctionName, String Method, String EndPoint, String Payload,
			double ExpectedStatusCodeDouble,String ExpectedResponseBody)


	{
		
		jwtToken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxODM0ODIsInVzZXJfZW1haWwiOiJna3Jpc2huYW11cnRoeTQ0M0BnbWFpbC5jb20iLCJ1c2VyX2ZpcnN0X25hbWUiOiJrYXZ5YXByZSIsInVzZXJfbGFzdF9uYW1lIjoicHJlIiwiaXNTb2NpYWxNZWRpYSI6ZmFsc2UsImlhdCI6MTcwNDY5NzMzM30.gVj_z7wAYhh_By01ctn9s4jvsVNrjTvgGIfl3Te4zCE";
		
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
		test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
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
