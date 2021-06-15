package com.pickMySolar.tests;

import org.testng.annotations.Test;

import com.pickMySolar.models.CreateFileInsideFolderRequest;
import com.pickMySolar.models.CreateFolderRequest;
import com.pickMySolar.utlis.BaseSteps;

import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class GoogleDriveHandling extends BaseSteps{
	
	public String folderid; 
	public String fileid;



	@BeforeSuite
	public void configurationOfTests() {
		
		//configuration for reading property file
		readPropertyfile("src//main//resources//config.properties");
		
	}
	@Test(priority=0)
	public void createFolderInGoogleDrive() {
		CreateFolderRequest request=new CreateFolderRequest("APIFolder","application/vnd.google-apps.folder");
		String jsonBody=stringtoJson(request);
		System.out.println(jsonBody);
		Response response=createPostrequestUsingOauth2(configProperty.getProperty("baseUrl"),configProperty.getProperty("bearerToken"),jsonBody);
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		folderid=response.path("id").toString();
		System.out.println(folderid);
	}

	@Test(priority=1)
	public void createFileInsideFolder() {
		
		List<String> parentFolder=new ArrayList<String>();
		parentFolder.add(folderid);
		CreateFileInsideFolderRequest request=new CreateFileInsideFolderRequest("APIFile.txt",parentFolder);
		String jsonBody=stringtoJson(request);
		System.out.println(jsonBody);
		Response response=createPostrequestUsingOauth2(configProperty.getProperty("baseUrl"),configProperty.getProperty("bearerToken"),jsonBody);
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		fileid=response.path("id").toString();
		
	}
	@Test(priority=2)
	public void getafileFromFolder() {
		
		String url=configProperty.getProperty("baseUrl")+"/"+fileid+"?folder_id="+folderid;
		System.out.println(url);
		Response response=createGetrequestUsingOauth2(url,configProperty.getProperty("bearerToken"));
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test(priority=3)
	public void deletefileFromFolder() {
		
		String url=configProperty.getProperty("baseUrl")+"/"+fileid;
		Response response=createDeleterequestUsingOauth2(url,configProperty.getProperty("bearerToken"));
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(), 204);
	}

	@Test(priority=4)
	public void deleteFolderfromDrive() {
		String url=configProperty.getProperty("baseUrl")+"/"+folderid;
		Response response=createDeleterequestUsingOauth2(url,configProperty.getProperty("bearerToken"));
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(), 204);
	}




	@AfterSuite
	public void afterSuite() {
	}

}
