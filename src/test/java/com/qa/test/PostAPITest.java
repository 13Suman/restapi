package com.qa.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {

	TestBase testbase;
	String serviceurl;
	String apiurl;
	String url;
	RestClient restclient;
	CloseableHttpResponse closeablehttpresponse;

	
	@BeforeMethod
	public void setUp() {
		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		apiurl = prop.getProperty("serviceURL");

		url = serviceurl + apiurl;

	}
	@Test
	public void postAPITest() throws IOException {
	restclient	=new RestClient();
	HashMap<String, String>	headerMap= new HashMap<String, String>();
	headerMap.put("content-type", "application/json");
	
	//Jackson API 
	ObjectMapper mapper = new ObjectMapper();
	Users users= new Users("morpheus", "leader"); //expected users object
	
	//object to JSON file  //MARSHELLING
	mapper.writeValue(new File("/Users/suman/eclipse-workspace/restapi/src/main/java/com/qa/data/users.json"), users);
	
	//Java Object to JSON in string   //MARSHELLING
	String usersJsonString = mapper.writeValueAsString(users);
	System.out.println(usersJsonString);
	
	closeablehttpresponse=restclient.post(url, usersJsonString, headerMap);  //call the API
	
	//validate the response from API
	//Status Code
	int statuscode=closeablehttpresponse.getStatusLine().getStatusCode();
	Assert.assertEquals(statuscode, testbase.RESPONSE_STATUS_CODE_201);
	
	
	//JSON String	
	String responseString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
	
	JSONObject responseJson = new JSONObject(responseString);
	System.out.println("Response from API"+ responseJson);
	
	//Json to Java Object   //UNMARSHELLING
	Users usersObj=mapper.readValue(responseString, Users.class); //actual users object
	System.out.println(usersObj);
	
	Assert.assertTrue(users.getName().equals(usersObj.getName()));
	Assert.assertTrue(users.getJob().equals(usersObj.getJob()));
	System.out.println(usersObj.getId());
	System.out.println(usersObj.getCreatedAt());
}
	
}
