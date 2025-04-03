package api.test;
import api.utilities.ApiUtils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.github.javafaker.Faker;


import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;


	
	public class UserTest2 {
		public static  Logger logger;
	    Faker faker;
	    User payload;

	    @BeforeClass
	    public void SetUpData() {
	        faker = new Faker();
	        payload = new User();
	        payload.setId(faker.idNumber().hashCode());
	        payload.setUsername(faker.name().username());
	 //       payload.setUsername("test_" + System.currentTimeMillis());
	        payload.setFirstname(faker.name().firstName());
	        payload.setLastname(faker.name().lastName());
	        payload.setEmail(faker.internet().safeEmailAddress());
	        payload.setPassword(faker.internet().password(6, 12));
	        payload.setPhone(faker.phoneNumber().phoneNumber());
	        PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\Log4j.properties");
	    	logger=Logger.getLogger(UserTest2.class);
	    //	 logger = LogManager.getLogger(UserTest2.class);
	        
	    }

	    @Test(priority = 1)
	    public void TestPostUser() {
	    	logger.info("-------Creating User----------");
	        Response response = UserEndPoints2.CreateUser(payload);
	        response.then().log().all();
	        Assert.assertEquals(response.getStatusCode(), 200);
	        logger.info("-------Created user----------");
	    }

	    @Test(priority = 2)
	    public void TestGetUser() throws InterruptedException {
	    	logger.info("-------Getting User----------");
	        Response response = ApiUtils.retryApiCall(() -> UserEndPoints2.ReadUser(payload.getUsername()), 5, 9000);
	        response.then().log().all();
	        Assert.assertEquals(response.getStatusCode(), 200);
	        logger.info("-------viewed User----------");
	    }

	    @Test(priority = 3)
	    public void TestUpdateUser() throws InterruptedException {
	    
	        String oldUsername =payload.getUsername();
	        payload.setUsername(faker.name().username());
	        payload.setFirstname(faker.name().firstName());
	        payload.setLastname(faker.name().lastName());

//	        Response response = UserEndPoints.UpdateUser(oldUsername, payload);
//	        response.then().log().body().time(Matchers.lessThan(9000L));
//	        Assert.assertEquals(response.getStatusCode(), 200);

	    	logger.info("-------Updating  User----------");
	        Response response = ApiUtils.retryApiCall(() -> UserEndPoints2.UpdateUser(oldUsername,payload), 5, 9000);
	        Assert.assertEquals(response.getStatusCode(), 200);
	        logger.info("-------Updated  User----------");
	        
	        logger.info("-------Getting  User----------");
	        // Verify user is updated with new username
	        Response response1 = ApiUtils.retryApiCall(() -> UserEndPoints2.ReadUser(payload.getUsername()), 5, 9000);
	        Assert.assertEquals(response1.getStatusCode(), 200);
	        logger.info("-------viwed  User----------");
	    }

	/*   @Test(priority = 4)
	    public void TestDeleteUser() throws InterruptedException {
	        Response response = UserEndPoints.DeleteUser(payload.getUsername());
	        response.then().log().all();
	        Assert.assertEquals(response.getStatusCode(), 200);

	        // Verify user is deleted
	        Response response1 = ApiUtils.retryApiCall(() -> UserEndPoints.ReadUser(payload.getUsername()), 5, 9000);
	        Assert.assertEquals(response1.getStatusCode(), 404);*/
	  //  }
	
	   @Test(priority = 4)
	   public void TestDeleteUser()  {
//	       System.out.println("Deleting User: " + payload.getUsername());  // Print username before delete
//	       Response response = UserEndPoints.DeleteUser(payload.getUsername());
//	       
//	       response.then().log().all().time(Matchers.lessThan(9000L));
//	       Assert.assertEquals(response.getStatusCode(), 200, "User deletion filed!");

	       // Verify user is deleted
		   logger.info("-------Deleting   User----------");
	       Response response1 = ApiUtils.retryApiCall(() -> UserEndPoints2.DeleteUser(payload.getUsername()), 5, 9000);
	       response1.then().log().all();
	       Assert.assertEquals(response1.getStatusCode(), 200, "Deleted user");
	       logger.info("-------Deleted  User----------");
	   }

	
	
}
