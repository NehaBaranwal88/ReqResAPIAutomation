package com.api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.endpoints.UserEndPoints;
import com.api.payload.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class User_bck_Tests {
	Faker faker;
	User userPayload;
	String user_payload;
	public Logger logger;
	@BeforeClass
	public void setupData() throws JsonProcessingException
	{
	   faker=new Faker();
	   userPayload=new User();
//	   userPayload.setUser_name(faker.name().fullName());
//	   userPayload.setUser_job(faker.job().title());
//	   ObjectMapper objMapper= new ObjectMapper();
//	   user_payload= objMapper.writeValueAsString(userPayload);
	   
	   logger= LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=2)
	public void testGetUser() {
	  logger.info("*****Getting User information******");	
	  Response response=UserEndPoints.GetUser();
	  response.then().log().all();
	  Assert.assertEquals(response.getStatusCode(), 200);
	}
	

	@Test(priority=3)
	public void testGetSingleUser() {
	  logger.info("*****Creating Single User*****");	
	  Response response=UserEndPoints.GetSingleUser();
	  response.then().log().all();
	  Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=1)
	public void testCreateUser() {
		logger.info("*****Creating User*****");
		Response response=UserEndPoints.CreateUser(user_payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test(priority=4)
	public void testUpdateUser() throws JsonProcessingException {
		
		userPayload.setUser_job(faker.job().title());
		ObjectMapper objMapper= new ObjectMapper();
		String user_payload1= objMapper.writeValueAsString(userPayload);
		System.out.println(this.userPayload.getUser_name());
		
		logger.info("*****Updating User Information*****");
		Response response=UserEndPoints.UpdateUser(user_payload1);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
//		Assert.assertEquals(response.jsonPath().getString("name"),this.userPayload.getUser_name());
	}
	@Test(priority=5)
	public void testDeleteUser() throws JsonProcessingException {
		logger.info("*****Deleting User*****");
		Response response=UserEndPoints.DeleteUser();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 204);
	}
	
	@Test(priority=6)
	public void testRegisterSuccessful() throws JsonProcessingException
	{   
		String(faker.name().fullName());
		userPayload.setUser_email(faker.internet().safeEmailAddress());
		
		ObjectMapper objMapper= new ObjectMapper();
		String payload2= objMapper.writeValueAsString(userPayload);
		System.out.println(payload2);
		
		logger.info("*****User is able to Register successfully*****");
		Response response=UserEndPoints.RegisterSuccessful(payload2);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		String id = response.jsonPath().getString("id");
		Assert.assertNotNull(id, "id should not be null");
		String token = response.jsonPath().getString("id");
		Assert.assertNotNull(token, "token should not be null");
		
	}
	
    
	public void testRegisterUnSuccessful()
	{
		logger.info("*****User is able to Register unsuccessfully*****");
		Response response=UserEndPoints.RegisterUnsuccessful(user_payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	public void testLoginSuccessful()
	{
		logger.info("*****User is able to login successfully*****");
		Response response=UserEndPoints.SuccessfulLogin(user_payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	public void testLoginUnSuccessful()
	{
		logger.info("*****User is able to login unsuccessfully*****");
		Response response=UserEndPoints.UnsuccessfulLogin(user_payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	public void testDelayedResponse()
	{
		logger.info("*****There is delay in Response*****");
		Response response=UserEndPoints.DelayedResponse();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
	}
}
