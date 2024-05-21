package com.SpaceBand;

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
public class SpaceBandTest extends BaseClass{
	public ExtentReports extent;
	public ExtentTest test;
	String Baseurl ="https://testing-spacebands.azurewebsites.net";
String 	jwtTokenSpaceband;
	
	
	
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
		ReportName = "Spacebands-" + formattedDate + "&" + formattedTime + ".html";
		//System.out.println("reportLocation: " + RafflouxReportLoginPath);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter( SpaceBandReoprt+ ReportName);
		System.out.println("reportLocation ReportName: " +  SpaceBandReoprt + ReportName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}
			
 
	@BeforeMethod
	public void beforeMethod() {
		test = extent.createTest("SpaceBands Rest API", "API Testing");
	}
	
	
	//@Test(priority = 1)
		public void SpacebandsLoginValidations()

		{
			int ExpectedStatusCode = 200;
			String Payload = "{\r\n"
					+ "  \"email\": \"deepak.sivakumar@rhibhus.com\",\r\n"
					+ "  \"origin\": \"123Pa$$word!\"\r\n"
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

					.post("https://testing-spacebands.azurewebsites.net/api/identity/token")

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
			jwtTokenSpaceband = jsonObject.getJSONObject("status").getString("jwt");
			 //System.out.println( ActualResponseBody+ " ActualResponseBody is");
	         System.out.println("actual response "+ActualResponseBody );


			// To print and To validate both (exp and act status code)
			System.out.println("Actual status code is " + "=>" + ActualStatusCode + "=>" + "-AND-" + "expected status code" + "=>"
					+ ExpectedStatusCode);
			Assert.assertEquals(ActualStatusCode, ExpectedStatusCode, "Expected status code is  Incorrect");

			// To print response body
			Reporter.log(
					"Actual Response body" + " =>" + jwtTokenSpaceband + "=>" + "Expected Response Body is" + "=>" + Expectedjwt);
		//	Assert.assertEquals(jwtToken.contains(Expectedjwt), true, "Incorrect Expeted body");
			test.log(Status.INFO,"Method is:" +Method);
			test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
			test.log(Status.INFO,"EndPoint:"+ EndPoint);
		    test.log(Status.INFO,"Payload :"+Payload);
			System.out.println(" status code is " + ActualStatusCode);
	       System.out.println("jwtToken:" +jwtTokenSpaceband);
		}
		


		@Test(priority=2,  dataProvider = "spaceband", dataProviderClass = SpaceBandDataProviderExcelReader.class)

		public void RafflouxRestApiFuntValidations(String Rownum ,String APIFunctionName, String Method, String EndPoint, String Payload,
				double ExpectedStatusCodeDouble,String ExpectedResponseBody)


		{
			
			jwtTokenSpaceband="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6Ijk3NzRmZGU2LWEyNDUtNDM0MS1iOWZkLTA4MzBlZmU3MmMzZCIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL2VtYWlsYWRkcmVzcyI6ImRlZXBhay5zaXZha3VtYXJAcmhpYmh1cy5jb20iLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiRGVlcGFrIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvc3VybmFtZSI6IlNpdmFrdW1hciIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL21vYmlsZXBob25lIjoiIiwiSXNDbGllbnQiOiJUcnVlIiwiQ29udGFjdElkIjoiMSIsIlN0cmlwZUN1c3RvbWVySWQiOiJjdXNfT3hLUlBxdm1iVEgyNTkiLCJUaW1lWm9uZSI6IkdNVCIsIkN1cnJlbmN5IjoiR0JQIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiQ2xpZW50QWRtaW4iLCJQZXJtaXNzaW9uIjpbIlBlcm1pc3Npb25zLlVzZXJzLlZpZXciLCJQZXJtaXNzaW9ucy5Vc2Vycy5TZWFyY2giLCJQZXJtaXNzaW9ucy5TdXJ2ZXkuVmlldyIsIlBlcm1pc3Npb25zLlN1cnZleS5TZWFyY2giLCJQZXJtaXNzaW9ucy5TdXJ2ZXlBbnN3ZXIuVmlldyIsIlBlcm1pc3Npb25zLlN1cnZleUFuc3dlci5TZWFyY2giLCJQZXJtaXNzaW9ucy5GZWF0dXJlLlZpZXciLCJQZXJtaXNzaW9ucy5GZWF0dXJlLlNlYXJjaCIsIlBlcm1pc3Npb25zLkRhc2hib2FyZHMuVmlldyIsIlBlcm1pc3Npb25zLlByb2ZpbGUuRWRpdCIsIlBlcm1pc3Npb25zLlVzZXJzLkNyZWF0ZSIsIlBlcm1pc3Npb25zLlVzZXJzLkVkaXQiLCJQZXJtaXNzaW9ucy5Vc2Vycy5EZWxldGUiLCJQZXJtaXNzaW9ucy5Vc2Vycy5FeHBvcnQiLCJQZXJtaXNzaW9ucy5Vc2Vycy5JbXBvcnQiLCJQZXJtaXNzaW9ucy5TdXJ2ZXkuQ3JlYXRlIiwiUGVybWlzc2lvbnMuU3VydmV5LkVkaXQiLCJQZXJtaXNzaW9ucy5TdXJ2ZXkuRGVsZXRlIiwiUGVybWlzc2lvbnMuRmVhdHVyZS5DcmVhdGUiLCJQZXJtaXNzaW9ucy5GZWF0dXJlLkVkaXQiLCJQZXJtaXNzaW9ucy5GZWF0dXJlLkRlbGV0ZSIsIlBlcm1pc3Npb25zLkJpbGxpbmcuVmlldyIsIlBlcm1pc3Npb25zLkJpbGxpbmcuQ3JlYXRlIiwiUGVybWlzc2lvbnMuQmlsbGluZy5FZGl0IiwiUGVybWlzc2lvbnMuQmlsbGluZy5EZWxldGUiLCJQZXJtaXNzaW9ucy5CaWxsaW5nLlNlYXJjaCIsIlBlcm1pc3Npb25zLkhhbmdmaXJlLlZpZXciXSwiZXhwIjoxNjk5NTIwODM0fQ.1NOBcgQANNl1-tmxVc6hZ_2AL_655CovV7kIc3Onbjc";
			
			if(Method.equals("POST"))

			{
		     String jwtToken1=jwtTokenSpaceband;
		    // System.out.println(jwtToken1);
			 test.log(Status.INFO, "Starting the APItest");
			 Response response = RestAssured

					.given()
					.header("Authorization", jwtToken1)
					.baseUri(Baseurl)
					.contentType(ContentType.JSON).body(Payload)
					.when().post(EndPoint).then().extract().response();
			 
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
		
		
			
			else if(Method.equals("PUT"))

				{
			     String jwtToken1=jwtTokenSpaceband;
			    // System.out.println(jwtToken1);
				 test.log(Status.INFO, "Starting the APItest");
				 Response response = RestAssured

						.given()
						.header("Authorization", jwtToken1)
						.baseUri(Baseurl)
						.contentType(ContentType.JSON).body(Payload)
						.when().put(EndPoint)
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
			String jwtToken1=jwtTokenSpaceband;
	    // System.out.println(jwtToken1);
			test.log(Status.INFO, "Starting the APItest");
			 Response response = RestAssured

					.given() 
					
					.header("Authorization", jwtToken1)

					.baseUri(Baseurl)
				
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

		}
		
			
		else if (Method.equals("DELETE"))

		{ 
			String jwtToken1=jwtTokenSpaceband;
	    // System.out.println(jwtToken1);
			test.log(Status.INFO, "Starting the APItest");
			 Response response = RestAssured

					.given() 
					
					.header("Authorization", jwtToken1)

					.baseUri(Baseurl)
				
					.contentType(ContentType.JSON)
					.when().delete(EndPoint).then().extract().response();
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
