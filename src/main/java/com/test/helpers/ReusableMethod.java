package com.test.helpers;

import com.test.Endpoints.Endpoints;
import com.test.utils.Util;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public  class ReusableMethod {
	
	public static void getbaseurl()
	{
		RestAssured.baseURI=Util.getApplicationProperty("baseurl");
		RestAssured.basePath=Endpoints.GETREPO;
	}
	public static void getallbaseurl()
	{
		RestAssured.baseURI=Util.getApplicationProperty("baseurl");
		RestAssured.basePath=Endpoints.GETAll_REPO;
	}
	public static void getnonexistrepobaseurl()
	{
		RestAssured.baseURI=Util.getApplicationProperty("baseurl");
		RestAssured.basePath=Endpoints.NONEXIST_REPO;
	}
	

	public static void updaterepoUrl()
	{
		RestAssured.baseURI=Util.getApplicationProperty("baseurl");
		RestAssured.basePath=Endpoints.NONEXIST_REPO;
	}
	public static void deleteUrl()
	{
		RestAssured.baseURI=Util.getApplicationProperty("baseurl");
		RestAssured.basePath=Endpoints.DELETE_REPO;
	}
	public static void deletenonExistUrl()
	{
		RestAssured.baseURI=Util.getApplicationProperty("baseurl");
		RestAssured.basePath=Endpoints.NONEXIST_REPO;
	}
	
}
