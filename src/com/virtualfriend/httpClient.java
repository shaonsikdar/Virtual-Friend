/**
 * 
 */
package com.virtualfriend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * @author Shaon
 *
 */
public class httpClient {
	
	static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    
	/**
	 * @param nameValuePairs passing arraylist with all information added
	 * @param inputStream passing inputStream from user request
	 */
	public void httpConnection(ArrayList<NameValuePair> nameValuePairs,InputStream inputStream, String url){
		
		try{
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);   //when you want the emulator client to contact a server running on the same host, use the alias 10.0.2.2 to refer to the host computeres loopback interface. From the emulator�s perspective, localhost (127.0.0.1) refers to its own loopback interface.
				//HttpPost httpPost = new HttpPost("http://10.0.2.2/android/createuser.php");
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));		  		//Passing nameValuePairs ArrayList to the server
				Log.d("<<<-setEnhttpConnectionMsg_log_tag->>>","Succes in Http Connection");

				HttpResponse response = httpClient.execute(httpPost);
				Log.d("<<<-reshttpConnectionMsg_log_tag->>>","Succes in Http Connection");
				HttpEntity entity = response.getEntity();
				Log.d("<<<-entityhttpConnectionMsg_log_tag->>>","Succes in Http Connection");
				inputStream = entity.getContent();
				Log.d("<<<-inoputhttpConnectionMsg_log_tag->>>","Succes in Http Connection");
				
				Log.d("<<<-httpConnectionMsg_log_tag->>>","Succes in Http Connection");
				
				
		}catch(Exception e){
			
			Log.e("<<<-httpConnectionMsg_log_tag->>>","Error in Http Connection" + e.toString());
			
		}//end of exception handling		
		
	}//end of httpConnection()
	
	
	/**
	 * This is for validating user login
	 * @param nameValuePairs
	 * @return
	 */

	public String httpConnection(ArrayList<NameValuePair> nameValuePairs){
		
		String Res = null;
		try{
				
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost("http://192.168.100.1/android/vf/login2.php");   //when you want the emulator client to contact a server running on the same host, use the alias 10.0.2.2 to refer to the host computeres loopback interface. From the emulator�s perspective, localhost (127.0.0.1) refers to its own loopback interface.	
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response;
				
				try {
						response = httpClient.execute(httpPost);
						//HttpEntity entity = response.getEntity();
						
						int statusCode = response.getStatusLine().getStatusCode();
						if(statusCode != HttpStatus.SC_OK){
							
							return null;
							
						}else{
							HttpEntity entity = response.getEntity();
							
							if(entity!= null){
							
								Res = EntityUtils.toString(entity);
							}
						}
							
					} catch (ClientProtocolException e) {
							
								e.printStackTrace();
								
					  }catch (IOException e) {
						  
						  		e.printStackTrace();
						  		
					  }//end of exception handling
				
	//			inputStream = entity.getContent();
				
				Log.d("<<<-http_log_tag->>>","Succes in Http Connection");
				
		}catch(Exception e){
			
			Log.e("<<<-http_log_tag->>>","Error in Http Connection" + e.toString());
			
		}//end of exception handling
		
		return Res;
		
	}//end of httpConnection()

	/**
	 * 
	 * @param url url which server php will be execute for event
	 * @param params arraylist of user information 
	 * @return user information getting from server
	 */
    public JSONObject getJSONFromUrl(String url, List<NameValuePair> params) {

        try {
	            DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpPost httpPost = new HttpPost(url);
	            httpPost.setEntity(new UrlEncodedFormEntity(params));

	            HttpResponse httpResponse = httpClient.execute(httpPost);
	            HttpEntity httpEntity = httpResponse.getEntity();
	            is = httpEntity.getContent();
 
	        } catch (UnsupportedEncodingException e) {
	        	Log.e("getJSONFromUrl", "UnsupportedEncodingException : " + e.toString());
	        } catch (ClientProtocolException e) {
	        	Log.e("getJSONFromUrl", "ClientProtocolException : " + e.toString());
	        } catch (IOException e) {
	        	Log.e("getJSONFromUrl", "IOException : " + e.toString());
	        }// end of exception handling
        
        try {
		            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
		            StringBuilder sb = new StringBuilder();
		            String line = null;
		            
		            while ((line = reader.readLine()) != null) {
		            	
		            		sb.append(line + "n");
		            }//end of while loop
            
		            is.close();
		            json = sb.toString();
		            Log.e("JSON", json);
        	} catch (Exception e) {
            
        		Log.e("Buffer Error", "Error converting result " + e.toString());
        	}//end of exception handling

        
        try {
        		
        		jObj = new JSONObject(json);
        	
        	} catch (JSONException e) {
        		
        		Log.e("JSON Parser", "Error parsing data " + e.toString());
            
        	}//end of exception handling
 

        return jObj;		        // return JSON String
        
    }//end of getJSONFromUrl()	
	
}//end of main class