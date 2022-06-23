package com.employee.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;

public class TC001_GetEmployee extends TestBase{

	@BeforeClass
	public void GetEmployee(){
		logger.info("======TC001_GetEmployee======");
		RestAssured.baseURI="https://reqres.in";
		httprequest = RestAssured.given();
		
		response = httprequest.request(Method.GET,"/api/users?page=2") ;
	}
	@Test
	public void checkStatusCode(){
		int statuscode = response.getStatusCode();
		logger.info("Status code is "+statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	@Test
	public void chekStatusLine(){
		String statusline = response.statusLine();
		logger.info("Status line is "+statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
	@Test
	public void chekAllHeaders(){
		Headers allheader = response.headers();
		for (Header header : allheader) {
			logger.info(header.getName()+"==="+header.getValue());
		}
	}
	@Test
	public void resposBody(){
		String responsebody = response.getBody().asString();
		logger.info("Response body is "+responsebody);
		Assert.assertEquals(responsebody.contains("michael.lawson@reqres.in"), true);
	}
	@Test
	public void checkParticularHeader(){
		String head = response.header("Server");
		logger.info("CLoudflare header is "+head);
		Assert.assertEquals(head, "cloudflare");
	}

	
	
	
}
