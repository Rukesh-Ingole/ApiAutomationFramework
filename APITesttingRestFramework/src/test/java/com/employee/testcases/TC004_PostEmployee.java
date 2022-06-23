package com.employee.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;

public class TC004_PostEmployee extends TestBase {
	
	@BeforeClass
	public void postEmployee(){
		logger.info("======TC004_PostEmployee======");
		RestAssured.baseURI = "https://reqres.in";
		httprequest = RestAssured.given();
		
		JSONObject requesparam = new JSONObject();
		requesparam.put("email", "eve.holt@reqres.in");
		requesparam.put("password","pistol");
		
		httprequest.header("Content-Type","application/json");
		httprequest.body(requesparam.toJSONString());
		
		response = httprequest.request(Method.POST,"/api/register");
	}
	@Test
	public void statusCode(){
		int statuscode = response.getStatusCode();
		logger.info("Status  code is "+200);
		Assert.assertEquals(statuscode, 200);
	}
	@Test
	public void statusLine(){
		String statusline = response.getStatusLine();
		logger.info("Status line is "+statusline);
	}
	@Test
	public void responseBody(){
		String responsebody = response.getBody().asString();
		logger.info("Response body is "+responsebody);
		Assert.assertEquals(responsebody.contains("4"), true);
		Assert.assertEquals(responsebody.contains("QpwL5tke4Pnpja7X4"), true);
	}
	@Test
	public void checkallHeaders(){
		Headers  allheader = response.headers();
		for (Header header : allheader) {
			logger.info(header.getName()+"==="+header.getValue());
		}
	}
	@Test
	public void particularHeaderCheck(){
		String x = response.header("X-Powered-By");
		logger.info("Particular header X-Powered-By is "+x);
		Assert.assertEquals(x, "Express");
	}

}
