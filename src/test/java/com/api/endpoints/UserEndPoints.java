package com.api.endpoints;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response GetUser()
	{ 
		Response response=given()
         .queryParam("page", 2)
     .when()
         .get(Routes.USERS);
		 
		return response;
	}
	
	
    public static Response GetSingleUser() {
       Response response= given()
        .when()
            .get(Routes.SINGLE_USER, 2);
        return response;  
    }
    
    public static Response CreateUser(String payload) {
    	Response response= given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post(Routes.USERS);
    	return response; 
    }
    
    public static Response UpdateUser(String payload){
    	
    	Response response= given()
        .contentType(ContentType.JSON)
        .body(payload)
    .when()
        .put(Routes.SINGLE_USER, 2);
       return response;  	
    }
    
    public static Response DeleteUser(){
    	
    	Response response= given()
        .contentType(ContentType.JSON)
    .when()
        .delete(Routes.SINGLE_USER, 2);
       return response;  	
    }
    
    public static Response RegisterSuccessful(String payload) {
        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        Response response= given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post(Routes.REGISTER);
        return response;
    }
    public static Response RegisterUnsuccessful(String payload) {
        String requestBody = "{ \"email\": \"sydney@fife\" }";

        Response response= given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/register");
        return response;
    }
    
    public static Response SuccessfulLogin(String payload) {

    	Response response= given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/login");
        return response;    
    }
    
    public static Response UnsuccessfulLogin(String payload) {

    	Response response= given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/login");
        return response;
    } 
    
    public static Response DelayedResponse() {
    	Response response= given()
            .queryParam("delay", 3)
        .when()
            .get("/users");
        return response;
    }

}
