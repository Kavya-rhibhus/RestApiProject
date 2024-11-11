package NewFrameWork;

import org.testng.annotations.Test;

public class RedevRaffolux extends BaseNewClass {
	
	
		

	@Test(priority = 2, dataProvider = "Data", dataProviderClass = DataProviderExcelReader.class)

	public void RestApiFuntValidations(String Rownum, String APIFunctionName, String endPoint, String Method,
			String payload, double ExpectedStatusCodeDouble, String ExpectedResponseBody) {

		CommonMethod methods = new CommonMethod(); 
         methods.Method(Rownum, APIFunctionName, endPoint, Method, payload, ExpectedStatusCodeDouble,
				ExpectedResponseBody,RedevJWT,RedevBaseUrl);

	} 

}
 
 

	