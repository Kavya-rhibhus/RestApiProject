package NewFrameWork;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CommonMethod extends BaseNewClass {
	public  Response response ;
	
	public  String payload;
	public String endPoint;
    public	String Rownum ;
	public String APIFunctionName;
	public String Method;
	public double ExpectedStatusCodeDouble;
	public String ExpectedResponseBody;
	
 
	
	public void Method(String Rownum ,String APIFunctionName, String endPoint , String  Method, String payload,
			double ExpectedStatusCodeDouble,String ExpectedResponseBody ,String jwt,String baseUrl) {
		
		test = BaseNewClass.extent.createTest( endPoint+":"+APIFunctionName,  endPoint);
		
		
		

		  if(Method.equalsIgnoreCase("POST") ) {
		    response = RestAssured

				.given() 
				.header("Authorization", jwt )
				//.header("origin", jwt )
	
				.baseUri(baseUrl)
			
				.contentType(ContentType.JSON).body(payload)
				
				.when()

				.post(endPoint)

				.then()

				.extract()

				.response();
		  
		     
		 
		 
	}
	
		
		
	else if(Method.equalsIgnoreCase("GET")) {
		
		 response = RestAssured

				.given() 
				
				.header("Authorization",jwt )
 
				.baseUri(baseUrl)
			
				.contentType(ContentType.JSON)
				.when()

				.get(endPoint)

				.then()

				.extract()

				.response();
		   

		      
	
	}
	
else if(Method.equalsIgnoreCase("DELETE")) {
		
		response = RestAssured
	             .given() 
				
				.header("Authorization", jwt )

				.baseUri(baseUrl)
			
				.contentType(ContentType.JSON).body(payload)

				.when()

				.delete(endPoint)

				.then()

				.extract().response();
		
			 
	 
	}
	

		  
	else if  (Method.equalsIgnoreCase("PUT") ){
		
	response = RestAssured
	             .given() 
				
			   .header("Authorization", jwt )
	         	//.header("Origin", jwt )
				.baseUri(baseUrl)
			
				.contentType(ContentType.JSON).body(payload)

				.when()

				.put(endPoint)

				.then()

				.extract()
                .response();
		
			 
	
	}
		  
		  
	else {
		System.out.print("There is no matched method or API to execute");
	}
	
		
	System.out.print("double ExpectedStatusCodeDouble"+ExpectedStatusCodeDouble);
	
    BaseNewClass.test.log(Status.INFO,"RowNum is :"+ Rownum+  "and "  +"Method is:" +Method);
    BaseNewClass.test.log(Status.INFO,"Testcase >: "+APIFunctionName);
    BaseNewClass.test.log(Status.INFO,"EndPoint:"+ endPoint);
    BaseNewClass.test.log(Status.INFO,"Payload :"+ payload);
	 int ActualStatusCode = response.getStatusCode();
	 System.out.println(" status code is " + ActualStatusCode);
	 BaseNewClass.test.log(Status.INFO, "ActualStatusCode is :" + ActualStatusCode);
	 String ActualResponseBody = response.getBody().asString();
	// if(ActualResponseBody.length()<=50) {
	 BaseNewClass.test.log(Status.INFO, "ActualResponseBody is :" + ActualResponseBody);
	// }
	
	 
	 int ExpectedStatusCode = (int) ExpectedStatusCodeDouble;
	 System.out.print(ExpectedStatusCode);
	try {
		Assert.assertEquals(ActualStatusCode, ExpectedStatusCode, "Expected status code is  Incorrect");
		 BaseNewClass.test.log(Status.PASS, "Assertion passed");

	} catch (AssertionError e) { 

		 BaseNewClass.test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
		System.err.println("Test failed: Assertion failed");
	}

	 BaseNewClass.test.log(Status.INFO, Rownum+ "row datas are executed successfully");


	}
	
	
}


	

