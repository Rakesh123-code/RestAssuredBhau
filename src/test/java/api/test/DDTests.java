package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import api.utilities.ApiUtils;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	   User payload;
	   Response response;
	@Test(priority = 1,dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userId,String userName,String fname,String lname,String email,String password,String pNo) {
	    payload = new User();
        payload.setId(Integer.parseInt(userId));
        payload.setUsername(userName);
        payload.setFirstname(fname);
        payload.setLastname(lname);
        payload.setEmail(email);
        payload.setPassword(password);
        payload.setPhone(pNo);
        
          response=UserEndPoints2.CreateUser(payload);
        Assert.assertEquals(response.getStatusCode(), 200);
     
        // Logging test details to make them unique in Extent Report
        System.out.println("Test executed for User: " + userName);
	}
	
	@Test(priority = 2,dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void DeleteUSer(String userName) {
//		 response=UserEndPoints2.DeleteUser(userName);
//	      Assert.assertEquals(response.getStatusCode(), 200);
	
	       Response response1 = ApiUtils.retryApiCall(() -> UserEndPoints2.DeleteUser(userName), 5, 9000);
	       response1.then().log().all();
	       Assert.assertEquals(response1.getStatusCode(), 200, "Deleted user");
	      
	       System.out.println("Test Deleted for User: " + userName);
	}
}
