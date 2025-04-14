package com.api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.Properties;

import com.api.payload.User;
import com.api.utilities.ReadParamUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	static Properties queryParams=ReadParamUtils.RequestParams();
	
	//get User
	public static Response GetUser()
	{ 
		String query_param=queryParams.getProperty("get_query_param");
		Response response=given()
         .queryParam("page",query_param )
     .when()
         .get(Routes.USERS);
		 
		return response;
	}
	
	// get Single User
	public static Response GetSingleUser() {
       String path_param=queryParams.getProperty("get_path_param");	
       Response response= given()
        .when()
            .get(Routes.SINGLE_USER, path_param);
        return response;  
    }
    
	//Create User
    public static Response CreateUser(User payload) {
    	Response response= given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post(Routes.USERS);
    	return response; 
    }
    
    //Update User
    public static Response UpdateUser(User payload){
    	String path_param=queryParams.getProperty("get_path_param");	
    	Response response= given()
        .contentType(ContentType.JSON)
        .body(payload)
    .when()
        .put(Routes.SINGLE_USER, path_param);
       return response;  	
    }
    
    //Delete User
    public static Response DeleteUser(){
    	String path_param=queryParams.getProperty("get_path_param");
    	Response response= given()
        .contentType(ContentType.JSON)
    .when()
        .delete(Routes.SINGLE_USER, path_param);
       return response;  	
    }
    
    //Register User Successfully
    public static Response RegisterSuccessful(User payload) {
        
    Response response= given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post(Routes.REGISTER);
        return response;
    }
    
    //Register User Unsuccessfully
    public static Response RegisterUnsuccessful(User payload) {
       
    Response response= given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post(Routes.REGISTER);
        return response;
    }
    
    //User login Successfully
    public static Response SuccessfulLogin(User payload) {

    	Response response= given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post(Routes.LOGIN);
        return response;    
    }
    
    //User login Unsuccessfully
    public static Response UnsuccessfulLogin(User payload) {

    	Response response= given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post(Routes.LOGIN);
        return response;
    } 
    
    //Delayed Response
    public static Response DelayedResponse() {
    	String query_param=queryParams.getProperty("delay_query_param");
    	Response response= given()
            .queryParam("delay", query_param)
        .when()
            .get(Routes.USERS);
        return response;
    }

}
