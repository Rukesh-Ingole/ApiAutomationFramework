package com.employee.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.employee.base.TestBase;

import io.restassured.RestAssured;

public class TC006_GetEmployee extends TestBase{
	
	@Test
	public void GetEmployee(){
		logger.info("======TC006_GetEmployee======");
		
		RestAssured.given().when().get("https://reqres.in/api/users/2")
		.then().log().all();
	}
}
