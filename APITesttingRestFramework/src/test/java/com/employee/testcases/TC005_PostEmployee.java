package com.employee.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.employee.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class TC005_PostEmployee extends TestBase{
	
	@Test
	public void postEmployee() {
		logger.info("======TC005_PostEmployee======");
		RestAssured.given().when().contentType(ContentType.JSON)
		.body("{\r\n" +
				"    \"name\" : \"morpheus\",\r\n" + 
				"    \"job\"     :  \"leader\",\r\n" +
				"}").post("https://reqres.in/api/users").then().log().all();
	}
}
