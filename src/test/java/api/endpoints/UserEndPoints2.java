package api.endpoints;

import com.aventstack.extentreports.gherkin.model.Given;

import api.payload.User;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	static ResourceBundle getURl() {
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}

	public static Response CreateUser(User payload)
	{
		
		String post_url=getURl().getString("post_url");
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		    .post(post_url);
	       return response;
		
	}
	
	public static Response ReadUser(String UserName)
	{
		String get_url=getURl().getString("get_url");
	Response	response=given()
		                .pathParam("username", UserName)
		                 .when()
		                     .get(get_url);
	                     return response;
		
	}
	public static Response UpdateUser(String UserName , User payload)
	{
		String update_url=getURl().getString("update_url");
		Response	response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", UserName)
				
				.when()
				    .put(update_url);
			       return response;
		
	}
	
	public static Response DeleteUser(String UserName)
	{
		String delete_url=getURl().getString("delete_url");
	Response	response=given()
			.pathParam("username", UserName)

		.when()
		    .delete(delete_url);
	       return response;
	}
	
}
