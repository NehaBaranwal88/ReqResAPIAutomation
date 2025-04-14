package com.api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.endpoints.UserEndPoints;
import com.api.payload.User;
import com.api.utilities.ReadParamUtils;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	String user_payload;
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
	   faker=new Faker();
	   logger= LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testGetUser() {
	  logger.info("*****Getting User information******");	
	  Response response=UserEndPoints.GetUser();
	  response.then().log().all();
	  Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testGetSingleUser() {
	  logger.info("*****Creating Single User*****");	
	  Response response=UserEndPoints.GetSingleUser();
	  response.then().log().all();
	  Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testCreateUser() {
		User user = new User(null,faker.name().fullName(),faker.job().title(), null);
		
		logger.info("*****Creating User*****");
		Response response=UserEndPoints.CreateUser(user);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals(response.jsonPath().getString("user_name"),user.getUser_name());
		Assert.assertEquals(response.jsonPath().getString("user_job"),user.getUser_job());
		Assert.assertNotNull(response.jsonPath().getString("id"), "id should not be null");
		Assert.assertNotNull(response.jsonPath().getString("createdAt"), "time should not be null");
	}
	
	@Test(priority=4)
	public void testUpdateUser() {
		
		User user = new User(null,faker.name().fullName(), faker.job().title(), null);
		
		logger.info("*****Updating User Information*****");
		Response response=UserEndPoints.UpdateUser(user);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("user_name"),user.getUser_name());
		Assert.assertEquals(response.jsonPath().getString("user_job"),user.getUser_job());
		Assert.assertNotNull(response.jsonPath().getString("updatedAt"), "time should not be null");
	}
	
	@Test(priority=5)
	public void testDeleteUser()  {
		logger.info("*****Deleting User*****");
		Response response=UserEndPoints.DeleteUser();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 204);
	}
	
	@Test(priority=6)
	public void testRegisterSuccessful(){   	
		User user = new User("eve.holt@reqres.in",null, null,faker.internet().password());
		
		logger.info("*****User is able to Register successfully*****");
		Response response=UserEndPoints.RegisterSuccessful(user);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertNotNull(response.jsonPath().getString("id"), "id should not be null");
		Assert.assertNotNull(response.jsonPath().getString("token"), "token should not be null");
		
	}
	
    @Test(priority=7)
	public void testRegisterUnSuccessful()
	{   
		User user = new User(faker.internet().emailAddress(),null,null,null);
		
		logger.info("*****User is able to Register unsuccessfully*****");
		Response response=UserEndPoints.RegisterUnsuccessful(user);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 400);
		Assert.assertEquals(response.jsonPath().getString("error"),"Missing password");
	}
	
    @Test(priority=8)
	public void testLoginSuccessful()
	{   
    	User user = new User("eve.holt@reqres.in",null, null,faker.internet().password());
	    
		logger.info("*****User is able to login successfully*****");
		Response response=UserEndPoints.SuccessfulLogin(user);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertNotNull(response.jsonPath().getString("token"), "token should not be null");
	}
    
	@Test(priority=9)
	public void testLoginUnSuccessful()
	{ 
	    User user = new User(faker.internet().emailAddress(),null,null,null);
	 	
		logger.info("*****User is able to login unsuccessfully*****");
		Response response=UserEndPoints.UnsuccessfulLogin(user);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 400);
		Assert.assertEquals(response.jsonPath().getString("error"),"Missing password");
	}
	
	@Test(priority=10)
	public void testDelayedResponse()
	{
		logger.info("*****There is delay in Response*****");
		Response response=UserEndPoints.DelayedResponse();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	

}
