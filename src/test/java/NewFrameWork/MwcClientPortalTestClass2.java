package NewFrameWork;

import org.testng.annotations.Test;

public class MwcClientPortalTestClass2 extends BaseNewClass {
	
	
		

	@Test(priority = 2, dataProvider = "Data", dataProviderClass = DataProviderExcelReader.class)

	public void MWCRestApiFuntValidations(String Rownum, String APIFunctionName, String endPoint, String Method,
			String payload, double ExpectedStatusCodeDouble, String ExpectedResponseBody) {

		CommonMethod methods = new CommonMethod();
         methods.Method(Rownum, APIFunctionName, endPoint, Method, payload, ExpectedStatusCodeDouble,
				ExpectedResponseBody,mwcClientJWT, mwcClientBaseUrl);
 
	}

} 
 


	