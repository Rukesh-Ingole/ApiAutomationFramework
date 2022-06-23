package com.employee.testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC002_PostEmployee extends TestBase {
	
	@BeforeClass
	public void PostEmplyee(){
		logger.info("======TC002_PostEmployee======");
		RestAssured.baseURI = "https://reqres.in";
		httprequest = RestAssured.given();
		
		JSONObject requestparams = new JSONObject();
		requestparams.put("name", "morpheus");
		requestparams.put("job", "leader");
		
		httprequest.header("Content-Type","application/json");
		httprequest.body(requestparams.toJSONString());
		response = httprequest.request(Method.POST,"/api/users");
	}
	@Test
	public void checkStatusCode(){
		int statuscode = response.getStatusCode();
		logger.info("Status code is "+statuscode);
		Assert.assertEquals(statuscode, 201);
	}
	@Test
	public void checkStatusLine(){
		String statusline = response.getStatusLine();
		logger.info("Status Line is "+statusline);
	}
	@Test
	public void checkReponseBody(){
		String responsebody = response.body().asString();
		Assert.assertEquals(responsebody.contains("morpheus"), true);
		Assert.assertEquals(responsebody.contains("leader"), true);
		logger.info("Status Line is "+responsebody);
	}
	@Test
	public void ChekAllHeaders(){
		Headers allheaders = response.headers();
		for (Header header : allheaders) {
			logger.info(header.getName()+"==="+header.getValue());		
		}
	}
	@Test
	public void checkParticularheader(){
		String header = response.getHeader("Content-Type");
		logger.info("Headers is "+header);
		Assert.assertEquals(header, "application/json; charset=utf-8");
		
	}

}
