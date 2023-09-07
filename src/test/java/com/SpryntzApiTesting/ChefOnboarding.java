package com.SpryntzApiTesting;

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

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class ChefOnboarding extends BaseClass{
	public ExtentReports extent;
	public ExtentTest test;
	public String ChefOnboarding;
	public String RestaurantOnboardingJwt;
	public String FleetOnboardingJwt;
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
		ReportName = "Spryntz-" + formattedDate + "&" + formattedTime + ".html";
		//System.out.println("reportLocation: " + RafflouxReportLoginPath);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(SpryntzReportPath+"Chef"+ReportName);
		//System.out.println("reportLocation ReportName: " + RafflouxReportLoginPath+ ReportName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}
			
 
	@BeforeMethod
	public void beforeMethod() {
		test = extent.createTest("Spryntz ChefOnboarding API", "API Testing both Positive and negative");
	}
	
	
//-------------------------------LoginValidationsChefOnboarding--------------------------------------//

@Test(priority = 1)
public void SpryntzLoginValidationsChefOnboarding()  

{
	int ExpectedStatusCode = 200;
	String Payload = "{\r\n"
			+ "\r\n"
			+ "    \"email\" : \"rajesh1234@spryntz.com\",\r\n"
			+ "\r\n"
			+ "    \"password\" : \"MyStrongPassword@123\",\r\n"
			+ "\r\n"
			+ "    \"device_type\": \"ANDRIOD\",\r\n"
			+ "\r\n"
			+ "    \"device_token\": \"mobile\"\r\n"
			+ "\r\n"
			+ "}";
//	String EndPoint = "/v1/signIn";
	String Expectedjwt = "eyJ1c2VyX2lkIjoxNTEwNjMsInVzZXJfZW1haWwiOiJ2ZWVyYS5yYWdoYXZhbkByaGliaHVzLmNvbSIsInVzZXJfZmlyc3RfbmFtZSI6InZlZXJhIiwidXNlcl9sYXN0X25hbWUiOiJyYWdoYXZhbiIsImlhdCI6MTY4NTMzMjEwM30";
    String Method="post";
    String APIFunctionName="Login";
    		String EndPoint="/v1/login";
	Response response = RestAssured

			.given()
			.header("code", "d0a063")
			.header("language", "1")
			
			//.baseUri("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing")
			.contentType(ContentType.JSON).body(Payload)

			.when()

			.post("https://spryntzqa.com/api/v1/auth/login")

			.then()

			.extract()

			.response();
	// To ActualStatusCode from response body and To Print
	int ActualStatusCode = response.getStatusCode();
	// To ActualResponseBody from response body and To print
	String ActualResponseBody= response.getBody().asString();
	JSONObject jsonObject = new JSONObject(ActualResponseBody);
     ChefOnboarding = jsonObject.getJSONObject("data").getString("auth_token");
    // Print the extracted auth_token
    System.out.println("Auth Token:--------> " + ChefOnboarding);
     System.out.println("actual response "+ActualResponseBody );
	// To print and To validate both (exp and act status code)
	System.out.println("Actual status code is " + "=>" + ActualStatusCode + "=>" + "-AND-" + "expected status code" + "=>"		+ ExpectedStatusCode);
	Assert.assertEquals(ActualStatusCode, ExpectedStatusCode, "Expected status code is  Incorrect");
	// To print response body
	Reporter.log("Actual Response body" + " =>" + ChefOnboarding + "=>" + "Expected Response Body is" + "=>" + RestaurantOnboardingJwt);
//	Assert.assertEquals(jwtToken.contains(Expectedjwt), true, "Incorrect Expeted body");
	test.log(Status.INFO,"Method is:" +Method);
	test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
	test.log(Status.INFO,"EndPoint:"+ EndPoint);
    test.log(Status.INFO,"Payload :"+Payload);
	System.out.println(" status code is " + ActualStatusCode);
    
}



//----------------------------------ChefOnboarding---------------------------------------------//



@Test(priority=2,  dataProvider = "ChefOnboarding", dataProviderClass = DataProviderExcelReader.class)

public void SpryntzRestApiAfterLoginChefOnboarding(String Rownum ,String APIFunctionName, String Method, String EndPoint, String Payload,
		double ExpectedStatusCodeDouble,String ExpectedResponseBody)
{
if(Method.equals("POST"))

{
     
	 test.log(Status.INFO, "Starting the APItest");
	 Response response = RestAssured

			.given().header("Authorization", ChefOnboarding)
			.header("code", "d0a063").header("language", "1").header("currency", "AED")
			.contentType(ContentType.JSON).body(Payload).when().post(EndPoint).then().extract()	.response();

	 test.log(Status.INFO,"RowNum is :"+ Rownum+  "and "  +"Method is:" +Method);
	 test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
	test.log(Status.INFO,"EndPoint:"+ EndPoint);
    test.log(Status.INFO,"Payload :"+Payload);

	 
    // test.log(Status.INFO,"ExpectedResponseBody is"+ ExpectedResponseBody );
	// To ActualStatusCode from response body and To Print
	int ActualStatusCode = response.getStatusCode();
	System.out.println(" status code is :------" + ActualStatusCode);
	
	test.log(Status.INFO, "ActualStatusCode is :" + ActualStatusCode);

	// To ActualResponseBody from response body and To print
	String ActualResponseBody = response.getBody().asString();

	test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
	
	 System.out.println("actual response body is:------"+ ActualResponseBody);
	 
	// To ActualHeaderContentType from response body and To print
	String ActualHeaderContentType = response.header("Content-Type");

//test.log(Status.INFO, "ActualHeaderContentType is  :" + ActualHeaderContentType);
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


    ///if (ActualResponseBody.length() <= 200) {

	//test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
   // }

/*try {
	 Assert.assertTrue(ActualResponseBody.contains(ExpectedResponseBody));
	test.log(Status.PASS, "Assertion passed");
    
} catch (AssertionError e) {  

	test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
	System.err.println("Test failed: Assertion failed");
}*/

}


else if(Method.equals("GET"))

{
 
 test.log(Status.INFO, "Starting the APItest");
 Response response = RestAssured

		.given()
		
		.header("Authorization",ChefOnboarding)
	
		.header("code", "d0a063").header("language", "1").header("currency", "AED")
		.contentType(ContentType.JSON).when().get(EndPoint).then().extract().response();

 test.log(Status.INFO,"RowNum is :"+ Rownum+  "and "  +"Method is:" +Method);
 test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
test.log(Status.INFO,"EndPoint:"+ EndPoint);
test.log(Status.INFO,"Payload :"+Payload);

//   test.log(Status.INFO,"ExpectedResponseBody is"+ ExpectedResponseBody );
//To ActualStatusCode from response body and To Print
int ActualStatusCode = response.getStatusCode();
System.out.println(" status code is " + ActualStatusCode);

test.log(Status.INFO, "ActualStatusCode is :" + ActualStatusCode);

// To ActualResponseBody from response body and To print
String ActualResponseBody = response.getBody().asString();

test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);

// To ActualHeaderContentType from response body and To print
String ActualHeaderContentType = response.header("Content-Type");

//test.log(Status.INFO, "ActualHeaderContentType is  :" + ActualHeaderContentType);
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


// if (ActualResponseBody.length() <= 300) {

//test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
// }

/*try {
 Assert.assertTrue(ActualResponseBody.contains(ExpectedResponseBody));
test.log(Status.PASS, "Assertion passed");

} catch (AssertionError e) {  

test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
System.err.println("Test failed: Assertion failed");
}*/

}
}





//---------------------------------ChefOnboardinginvalidJwt---------------------------------------

//@Test(priority=3,  dataProvider = "ChefOnboarding", dataProviderClass = DataProviderExcelReader.class)

public void SpryntzRestApiAfterLoginChefOnboardinginvalidJwt(String Rownum ,String APIFunctionName, String Method, String EndPoint, String Payload,
		double ExpectedStatusCodeDouble,String ExpectedResponseBody)
{
	
	
	String ChefOnboardingJwt="eyJ1c2VyX2lkIjoxNTEwNjMsInVzZXJfZW1haWwiOiJ2ZWVyYS5yYWdoYXZhbkByaGliaHVzLmNvbSIsInVzZXJfZmlyc3RfbmFtZSI6InZlZXJhIiwidXNlcl9sYXN0X25hbWUiOiJyYWdoYXZhbiIsImlhdCI6MTY4NTMzMjEwM30";
   int ExpectedStatusCodeinvalid=401;
    
    
	if(Method.equals("POST"))

{
   
     
	 test.log(Status.INFO, "Starting the APItest");
	 Response response = RestAssured

			.given()
			
			.header("Authorization", ChefOnboardingJwt)
			.header("code", "d0a063").header("language", "1").header("currency", "AED")
			.contentType(ContentType.JSON).body(Payload).when().post(EndPoint).then().extract()	.response();
	
	 test.log(Status.INFO,"RowNum is :"+ Rownum+  "and "  +"Method is:" +Method);
	 test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
	test.log(Status.INFO,"EndPoint:"+ EndPoint);
    test.log(Status.INFO,"Payload :"+Payload);
    // test.log(Status.INFO,"ExpectedResponseBody is"+ ExpectedResponseBody );
	// To ActualStatusCode from response body and To Print
	int ActualStatusCode = response.getStatusCode();
	System.out.println(" status code is :------" + ActualStatusCode);
	
	test.log(Status.INFO, "ActualStatusCode is :" + ActualStatusCode);

	// To ActualResponseBody from response body and To print
	String ActualResponseBody = response.getBody().asString();

	test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
	
	 System.out.println("actual response body is:------"+ ActualResponseBody);
	 
	// To ActualHeaderContentType from response body and To print
	String ActualHeaderContentType = response.header("Content-Type");

//test.log(Status.INFO, "ActualHeaderContentType is  :" + ActualHeaderContentType);
	// To convert from double to integer (while fetching will get integer as double)
	// and to print
	int ExpectedStatusCode = (int) ExpectedStatusCodeDouble;

	try {
		//Assert.assertEquals(ActualStatusCode, ExpectedStatusCode, "Expected status code is  Incorrect");
		//test.log(Status.PASS, "Assertion passed");
		Assert.assertEquals(ActualStatusCode, ExpectedStatusCodeinvalid, "Expected status code is  Incorrect");
		test.log(Status.PASS, "Assertion passed");


	} catch (AssertionError e) {  

		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
		System.err.println("Test failed: Assertion failed");
	}


	test.log(Status.PASS, Rownum+ "row datas are executed successfully");


    //if (ActualResponseBody.length() <= 200) {

	//test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
   // }

/*try {
	 Assert.assertTrue(ActualResponseBody.contains(ExpectedResponseBody));
	test.log(Status.PASS, "Assertion passed");
    
} catch (AssertionError e) {  

	test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
	System.err.println("Test failed: Assertion failed");
}*/

}
else if(Method.equals("GET"))

{
 
 test.log(Status.INFO, "Starting the APItest");
 Response response = RestAssured

		.given()
		
		.header("Authorization",ChefOnboardingJwt)
		.header("code", "d0a063").header("language", "1").header("currency", "AED")
		.contentType(ContentType.JSON).when().get(EndPoint).then().extract().response();
 test.log(Status.INFO,"RowNum is :"+ Rownum+  "and "  +"Method is:" +Method);
 test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
 test.log(Status.INFO,"EndPoint:"+ EndPoint);
test.log(Status.INFO,"Payload :"+Payload);
//   test.log(Status.INFO,"ExpectedResponseBody is"+ ExpectedResponseBody );
//To ActualStatusCode from response body and To Print
int ActualStatusCode = response.getStatusCode();
System.out.println(" status code is " + ActualStatusCode);

test.log(Status.INFO, "ActualStatusCode is :" + ActualStatusCode);

// To ActualResponseBody from response body and To print
String ActualResponseBody = response.getBody().asString();

test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);

// To ActualHeaderContentType from response body and To print
String ActualHeaderContentType = response.header("Content-Type");

//test.log(Status.INFO, "ActualHeaderContentType is  :" + ActualHeaderContentType);
// To convert from double to integer (while fetching will get integer as double)
// and to print
int ExpectedStatusCode = (int) ExpectedStatusCodeDouble;

try {
	//Assert.assertEquals(ActualStatusCode, ExpectedStatusCode, "Expected status code is  Incorrect");
	//test.log(Status.PASS, "Assertion passed");
	Assert.assertEquals(ActualStatusCode, ExpectedStatusCodeinvalid, "Expected status code is  Incorrect");
	test.log(Status.PASS, "Assertion passed");

} catch (AssertionError e) {  

	test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
	System.err.println("Test failed: Assertion failed");
}


test.log(Status.PASS, Rownum+ "row datas are executed successfully");


// if (ActualResponseBody.length() <= 300) {

//test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
// }

/*try {
 Assert.assertTrue(ActualResponseBody.contains(ExpectedResponseBody));
test.log(Status.PASS, "Assertion passed");

} catch (AssertionError e) {  

test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
System.err.println("Test failed: Assertion failed");
}*/

}
}





	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//@Test(priority=3,  dataProvider = "SpryntzBeforeLogin", dataProviderClass = DataProviderExcelReader.class)

	public void SpryntzRestApiBeforeLoginValidations(String Rownum ,String APIFunctionName, String Method, String EndPoint, String Payload,
			double ExpectedStatusCodeDouble,String ExpectedResponseBody)
	{
 if(Method.equals("POST"))

	{
	  // jwtToken1=jwtTokenFacto;
	  //   System.out.println(jwtToken1);
	     
		 test.log(Status.INFO, "Starting the APItest");
		 Response response = RestAssured

				.given()
				.header("code", "d0a063")
				.header("language", "1")
				//.header("Authorization", jwtToken1)
			
				.header("code", "d0a063").header("language", "1").header("currency", "AED")
				.contentType(ContentType.JSON).body(Payload).when().post(EndPoint).then().extract()	.response();
		
		 test.log(Status.INFO,"RowNum is :"+ Rownum+  "and "  +"Method is:" +Method);
		 test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
		 test.log(Status.INFO,"EndPoint:"+ EndPoint);
	     test.log(Status.INFO,"Payload :"+Payload);
	     test.log(Status.INFO,"ExpectedResponseBody is"+ ExpectedResponseBody );
		// To ActualStatusCode from response body and To Print
		int ActualStatusCode = response.getStatusCode();
		System.out.println(" status code is :------" + ActualStatusCode);
		
		test.log(Status.INFO, "ActualStatusCode is :" + ActualStatusCode);

		// To ActualResponseBody from response body and To print
		String ActualResponseBody = response.getBody().asString();

		test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
		
		 System.out.println("actual response body is:------"+ ActualResponseBody);
		 
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

	

/*	try {
		 Assert.assertTrue(ActualResponseBody.contains(ExpectedResponseBody));
		test.log(Status.PASS, "Assertion passed");
        
	} catch (AssertionError e) {  

		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
		System.err.println("Test failed: Assertion failed");
	}*/

	}
else if(Method.equals("GET"))

{
    // String jwtToken1=jwtTokenFacto;
  //   System.out.println(jwtToken1);
     
	 test.log(Status.INFO, "Starting the APItest");
	 Response response = RestAssured

			.given()
			
			//.header("Authorization", jwtToken1)
		
			.contentType(ContentType.JSON).when().get(EndPoint).then().extract().response();
	 test.log(Status.INFO,"RowNum is :"+ Rownum+  "and "  +"Method is:" +Method);
	 test.log(Status.INFO,"APIFunctionName:"+APIFunctionName);
	 test.log(Status.INFO,"EndPoint:"+ EndPoint);
  //   test.log(Status.INFO,"Payload :"+Payload);
     test.log(Status.INFO,"ExpectedResponseBody is"+ ExpectedResponseBody );
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



/*try {
	 Assert.assertTrue(ActualResponseBody.contains(ExpectedResponseBody));
	test.log(Status.PASS, "Assertion passed");
    
} catch (AssertionError e) {  

	test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
	System.err.println("Test failed: Assertion failed");
}*/

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
