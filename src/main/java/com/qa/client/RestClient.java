package com.qa.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//GET method without header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request
		CloseableHttpResponse closeablehttpresponse = httpclient.execute(httpget); // hit the url
		
		return closeablehttpresponse;	
	} 
	
	//GET method with header

	public CloseableHttpResponse get(String url,HashMap <String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request
		
		for(Map.Entry<String, String>entry:headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue() );
		}
		CloseableHttpResponse closeablehttpresponse = httpclient.execute(httpget); // hit the url
		return closeablehttpresponse;	
	}
	
	//POST method
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		
	CloseableHttpClient httpclient	=HttpClients.createDefault();
	HttpPost httppost = new HttpPost(url); //http Post Request
	httppost.setEntity(new StringEntity(entityString)); 
	
	for(Map.Entry<String, String>entry:headerMap.entrySet()) {
		httppost.addHeader(entry.getKey(), entry.getValue() );
	}
	CloseableHttpResponse closeablehttpresponse =httpclient.execute(httppost);
	return closeablehttpresponse;
	
	}
}
