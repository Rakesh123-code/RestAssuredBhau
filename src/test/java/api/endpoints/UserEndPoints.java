package api.endpoints;

import com.aventstack.extentreports.gherkin.model.Given;

import api.payload.User;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response CreateUser(User payload)
	{
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		    .post(Routes.Post_url);
	       return response;
		
	}
	
	public static Response ReadUser(String UserName)
	{
	Response	response=given()
		                .pathParam("username", UserName)
		                 .when()
		                     .get(Routes.Get_url);
	                     return response;
		
	}
	public static Response UpdateUser(String UserName , User payload)
	{
		Response	response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", UserName)
				
				.when()
				    .put(Routes.Update_url);
			       return response;
		
	}
	
	public static Response DeleteUser(String UserName)
	{
	Response	response=given()
			.pathParam("username", UserName)

		.when()
		    .delete(Routes.Delete_url);
	       return response;
	}
	
}
