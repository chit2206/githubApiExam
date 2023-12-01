package com.tests.test;
import com.test.helpers.*;
import com.test.utils.Util;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Endpoints.Endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;
 import  io.restassured.module.jsv.JsonSchemaValidator;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.*;
import com.pojo.createPojo;

public class GetRepo {
	
	
	
	@Test
	public void getRepo()
	{
		ReusableMethod.getbaseurl();
		
		int count=0;
		Response  rs=RestAssured.given().header("Authorization","Bearer"+Util.getApplicationProperty("BearerToken")).when().get();
		Assert.assertEquals(rs.statusCode(), 200);
		Assert.assertEquals(rs.contentType(), "application/json; charset=utf-8");
		String branch=rs.jsonPath().get("default_branch");
		Assert.assertEquals(branch, "main");
		String full_name=rs.jsonPath().get("full_name");
		String login=rs.jsonPath().get("owner.login");
		String name=rs.jsonPath().get("name");
		Assert.assertEquals(full_name, login+"/"+name);
		System.out.println(rs.body().asPrettyString());
		System.out.println("full name is "+login+"/"+name);
		System.out.println(rs.jsonPath().get("visibility"));
		
	if(rs.jsonPath().get("visibility").equals("public"))
		System.out.println("name of repo is"+rs.jsonPath().get("name"));
	
		
	}
	@Test
	public void getnoexistRepo()
	{
		ReusableMethod.getnonexistrepobaseurl();
		
		int count=0;
		Response  rs=RestAssured.given().header("Authorization","Bearer"+Util.getApplicationProperty("BearerToken")).when().get();
		Assert.assertEquals(rs.statusCode(), 404);
		Assert.assertEquals(rs.jsonPath().get("message"), "Not Found");
		
	
		
	}
	@Test	
	public void getAllrepo()

	{
         ReusableMethod.getallbaseurl();
		
		
		Response  rs=RestAssured.given().header("Authorization","Bearer"+Util.getApplicationProperty("BearerToken")).when().get();
		Assert.assertEquals(rs.statusCode(), 200);
		rs.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		List<String> totalRepo = rs.jsonPath().getList("id");
		
		int count=totalRepo.size();
		System.out.println("no of repository"+count);
		
		Assert.assertEquals(rs.contentType(), "application/json; charset=utf-8");
		List<Object> allrepo=rs.jsonPath().getList("visibility");
		System.out.println("hello");
	
		for(Object visibility : allrepo)
		{ if((visibility).equals("public")) {
			System.out.println(rs.jsonPath().get("name"));
			count++;
		}
		System.out.println("public repo is"+count);
		
	}
	}
	@Test
	public void createRepo()
	{
		ReusableMethod.getallbaseurl();
		createPojo creatpojo;
		creatpojo=new createPojo();
		creatpojo.setname("newrepo");
		creatpojo.setdescription("this is newrepo");
		creatpojo.sethomepage("https://github.com");
		Response  rs=RestAssured.given().header("Authorization","Bearer"+Util.getApplicationProperty("BearerToken")).body(creatpojo).when().post();
		Assert.assertEquals(rs.statusCode(), 404);
		Assert.assertEquals(rs.jsonPath().get("name"),creatpojo.getname());
		Assert.assertEquals(rs.jsonPath().get("login"),"chit2206");
		Assert.assertEquals(rs.jsonPath().get("type"),"user");
		
	}
	@Test
	public void createExistingRepo()
	{
		ReusableMethod.getallbaseurl();
		createPojo creatpojo;
		creatpojo=new createPojo();
		creatpojo.setname("newrepo");
		creatpojo.setdescription("this is newrepo");
		creatpojo.sethomepage("https://github.com");
		Response  rs=RestAssured.given().header("Authorization","Bearer"+Util.getApplicationProperty("BearerToken")).body(creatpojo).when().post();
		Assert.assertEquals(rs.statusCode(), 422);
		Assert.assertEquals(rs.jsonPath().get("message"),"name already exists on this account");
		
		
	}
	@Test
	public void updateRepo()
	{
		ReusableMethod.updaterepoUrl();
		createPojo creatpojo;
		creatpojo=new createPojo();
		creatpojo.setname("newrepo1");
		creatpojo.setdescription("this is newrepo");
		Response  rs=RestAssured.given().header("Authorization","Bearer"+Util.getApplicationProperty("BearerToken")).body(creatpojo).when().patch();
		Assert.assertEquals(rs.statusCode(), 200);
		Assert.assertEquals(rs.jsonPath().get("name"),creatpojo.getname());
	}
	@Test
	public void DeleteRepo()
	{
		ReusableMethod.deleteUrl();
		Response  rs=RestAssured.given().header("Authorization","Bearer"+Util.getApplicationProperty("BearerToken")).delete();
		Assert.assertEquals(rs.statusCode(), 200);
		Assert.assertEquals(rs.body(),"");
		
	}
	@Test
	public void DeletenonexistRepo()
	{
		ReusableMethod.deletenonExistUrl();
		Response  rs=RestAssured.given().header("Authorization","Bearer"+Util.getApplicationProperty("BearerToken")).delete();
		Assert.assertEquals(rs.statusCode(), 200);
		Assert.assertEquals(rs.body(),"");
		
	}
	
	}

