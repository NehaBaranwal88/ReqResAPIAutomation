package com.api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.endpoints.UserEndPoints;
import com.api.payload.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	String user_payload;
	public Logger logger;
	@BeforeClass
	public void setupData() throws JsonProcessingException
	{
	   faker=new Faker();
	   userPayload=new User();
	   userPayload.setUser_name(faker.name().fullName());
	   userPayload.setUser_job(faker.job().title());
	   ObjectMapper objMapper= new ObjectMapper();
	   user_payload= objMapper.writeValueAsString(userPayload);
	   
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
}
