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

public class TC003_PutEmployee extends TestBase{
	
	@BeforeClass
	public void putEmployee(){
		logger.info("======TC003_PutEmployee======");
		RestAssured.baseURI = "https://reqres.in";
		httprequest = RestAssured.given();
		
		JSONObject requesparams = new JSONObject();
		requesparams.put("name", "morpheus");
		requesparams.put("job", "zion resident");
		
		httprequest.header("Content-Type","application/json");
		httprequest.body(requesparams.toJSONString());
		
		response = httprequest.request(Method.PUT,"/api/users/2");	
	}
	@Test
	public void checkStatucode(){
		int statuscode = response.getStatusCode();
		logger.info("Status code is "+statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	@Test
	public void checkstatusLine(){
		String statusline = response.getStatusLine();
		logger.info("Status line is "+statusline);
	}
	@Test
	public void checkAllHeader(){
		Headers allheader = response.headers();
		for (Header header : allheader) {
			logger.info(header.getName()+"==="+header.getValue());
		}
	}
	@Test
	public void responseBody(){
		String responsebody = response.getBody().asString();
		logger.info("Response body is "+responsebody);
		Assert.assertEquals(responsebody.contains("zion resident"), true);
		Assert.assertEquals(responsebody.contains("morpheus"), true);
	}
	@Test
	public void checkParticularHeader(){
		String header  = response.getHeader("X-Powered-By");
		logger.info("Particular header is "+header);
		Assert.assertEquals(header, "Express");
	}

}
