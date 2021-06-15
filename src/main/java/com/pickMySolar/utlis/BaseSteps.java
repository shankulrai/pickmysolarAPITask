package com.pickMySolar.utlis;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseSteps 
{
	public Properties configProperty;
	
	public void readPropertyfile(String filePath)
	{
		try {
			FileInputStream config = new FileInputStream(new File(filePath));
			configProperty = new Properties();
			configProperty.load(config);
			

		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	public Response createPostrequestUsingOauth2(String endPointUrl,String bearerToken,String requestBody)
	{
		RequestSpecification httpRequest=RestAssured.given().auth().oauth2(bearerToken).header("Content-Type","application/json").body(requestBody);
		Response response=httpRequest.request(Method.POST, endPointUrl);
		return response;
	}
	
	public String stringtoJson(Object obj)
	{
		String json=new Gson().toJson(obj);
		System.out.println(json);
		return json;
		
	}
	
	public Response createGetrequestUsingOauth2(String endPointUrl,String bearerToken)
	{
		RequestSpecification httpRequest=RestAssured.given().auth().oauth2(bearerToken).header("Content-Type","application/json");
		Response response=httpRequest.request(Method.GET, endPointUrl);
		return response;
	}
	
	public Response createDeleterequestUsingOauth2(String endPointUrl,String bearerToken)
	{
		RequestSpecification httpRequest=RestAssured.given().auth().oauth2(bearerToken);
		Response response=httpRequest.request(Method.DELETE, endPointUrl);
		return response;
	}

}
