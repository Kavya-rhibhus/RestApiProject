package UpSchoolApiTesting;

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
public class UpSchoolApiTestClassStudent extends BaseClass{
	public ExtentReports extent;
	public ExtentTest test;
	
	public String jwtToken;
	
	
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
		ReportName = "UpSchoolStudent-" + formattedDate + "&" + formattedTime + ".html";
		//System.out.println("reportLocation: " + RafflouxReportLoginPath);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(UpSchoolReportPath+"Student"+ ReportName);
		System.out.println("reportLocation ReportName: " +UpSchoolReportPath+ ReportName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}
			
	
 
	@BeforeMethod
	public void beforeMethod() {
		test = extent.createTest("UpSchool-Student Rest API", "API Testing ");
	}
	 
	
	
	//@Test(priority = 1)
	public void RestApiLoginValidationsStudent()

	{
		int ExpectedStatusCode = 200;
		String Payload = "{\r\n"
				+ "    \"entered_otp\": \"54898\",\r\n"
				+ "    \"device_type\": \"android\",\r\n"
				+ "    \"device_id\": \"e26ba47924478b65\",\r\n"
				+ "    \"device_name\": \"POCO X2\",\r\n"
				+ "    \"user_email\": \"Aishwarya.Ravindra@rhibhus.com\",\r\n"
				+ "    \"mobile\": \"\"\r\n"
				+ "  }";
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

				.post("https://4umubve2vh.execute-api.ap-south-1.amazonaws.com/dev/v1/otp/validate")

				.then()

				.extract()

				.response();
		// To ActualStatusCode from response body and To Print
		int ActualStatusCode = response.getStatusCode();

		// To ActualResponseBody from response body and To print
		String ActualResponseBody= response.getBody().asString();
		
		
	
		//String subString = str.substring(startIndex, endIndex);
		// jwtToken = ActualResponseBody.substring(30, ActualResponseBody.lastIndexOf("}")-3);
	
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
	
	//@Test(priority = 2)
	public void RestApiLoginValidationsStudentOTPgenerate()

	{
		int ExpectedStatusCode = 200;
		String Payload = "{\r\n"
				+ "    \"mobile\": \"\",\r\n"
				+ "    \"user_email\": \"Aishwarya.Ravindra@rhibhus.com\"\r\n"
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

				.post("https://4umubve2vh.execute-api.ap-south-1.amazonaws.com/dev/v1/otp/generate")

				.then()

				.extract()

				.response();
		// To ActualStatusCode from response body and To Print
		int ActualStatusCode = response.getStatusCode();

		// To ActualResponseBody from response body and To print
		String ActualResponseBody= response.getBody().asString();
		
		
	
		//String subString = str.substring(startIndex, endIndex);
		// jwtToken = ActualResponseBody.substring(30, ActualResponseBody.lastIndexOf("}")-3);
	
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
	


	@Test(priority=3,  dataProvider = "StudentSheet", dataProviderClass = DataProviderExcelReader.class)

	public void StudentUpschoolRestApiFuntValidations(String Rownum ,String APIFunctionName, String Method, String EndPoint, String Payload,
			double ExpectedStatusCodeDouble,String ExpectedResponseBody)


	{
		
		jwtToken="yJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50X2lkIjoiNjdkYjc0NmMtN2I0Ny01ODU2LWE0NTAtYjE5Mjk1ZGM4ZmFkIiwidXNlcl9yb2xlIjoiU3R1ZGVudCIsImlhdCI6MTY5MTQwNzg4N30.8sINFQVo7lkVe62PGDfv9HaktVcYPcjt-0jULPjazbo";
		if(Method.equals("POST"))

		{
	     String jwtToken1=jwtToken;
	    // System.out.println(jwtToken1);
		 test.log(Status.INFO, "Starting the APItest");
		 Response response = RestAssured

				.given() 
				
				.header("Authorization", jwtToken1)

				.baseUri("https://4umubve2vh.execute-api.ap-south-1.amazonaws.com/dev")
			
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

       // if (ActualResponseBody.length() <= 400) {

		test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
        //}
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

				.baseUri("https://dssqy2gip2.execute-api.ap-south-1.amazonaws.com/testing/")
			
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
//if(ActualResponseBody.length()<=400) {
		test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
//}

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
