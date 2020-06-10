package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert; 
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {

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

	@Test(priority = 1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		closeablehttpresponse = restclient.get(url);

		int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.print("Status Code is " + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		String responseString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.print("Response JSON" + responseJSON);

		String perPageValue = TestUtil.getValueByJPath(responseJSON, "/per_page");
		System.out.println("Value of per page is -- " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		String totalPageValue = TestUtil.getValueByJPath(responseJSON, "/total");
		System.out.println("Value of total page is -- " + totalPageValue);
		Assert.assertEquals(Integer.parseInt(totalPageValue), 12);

		// get value from JSON Array
		String last_name = TestUtil.getValueByJPath(responseJSON, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJSON, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJSON, "/data[0]/avatar");
		String first_name = TestUtil.getValueByJPath(responseJSON, "/data[0]/first_name");

		System.out.println(last_name);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);

		Header[] headersArray = closeablehttpresponse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Heading Array" + allHeaders);

	}

	@Test(priority = 2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restclient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		closeablehttpresponse = restclient.get(url, headerMap);

		int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.print("Status Code is " + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		String responseString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.print("Response JSON" + responseJSON);

		String perPageValue = TestUtil.getValueByJPath(responseJSON, "/per_page");
		System.out.println("Value of per page is -- " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		String totalPageValue = TestUtil.getValueByJPath(responseJSON, "/total");
		System.out.println("Value of total page is -- " + totalPageValue);
		Assert.assertEquals(Integer.parseInt(totalPageValue), 12);

		// get value from JSON Array
		String last_name = TestUtil.getValueByJPath(responseJSON, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJSON, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJSON, "/data[0]/avatar");
		String first_name = TestUtil.getValueByJPath(responseJSON, "/data[0]/first_name");

		System.out.println(last_name);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);

		Header[] headersArray = closeablehttpresponse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Heading Array" + allHeaders);

	}

}
