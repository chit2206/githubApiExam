package com.tests.gitHubTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
//import io.restasured.ResrAssured;

public class GetRepo {
	@Test
	
	public void getRepo()
	
	{ 
		//Header header1=new Header("token","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");
		Response rs= RestAssured.given().get("https://github.com/chit2206/NovPractice");
	int i=rs.getStatusCode();
	System.out.println("status code is="+i);
	
	}

}
