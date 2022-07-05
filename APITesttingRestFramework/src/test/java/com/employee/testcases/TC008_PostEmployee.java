package com.employee.testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TC008_PostEmployee {
	
	public static Response response;
	@Test
	public void PostEmployee(){
		
		JSONObject json= new JSONObject();
		json.put("id", "0");
		json.put("idBook	", "0");
		json.put("firstName", "String");
		json.put("lastName", "String");
		
		System.out.println("POST REQUEST");
		
		response = RestAssured.given().when().contentType(ContentType.JSON).body(json.toJSONString())
		.post("https://fakerestapi.azurewebsites.net/api/v1/Authors");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getHeaders());
		System.out.println(response.getBody().asString());
	}

}
