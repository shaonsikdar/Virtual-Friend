/**
 * 
 */
package com.virtualfriend;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.virtualfriend.Base64.InputStream;

/**
 * @author Shaon
 *
 */

public class serverActivity extends Activity {
	
	InputStream inputStream;
	ArrayList<NameValuePair> nameValuePairs;
	
	//All below variable for using camera intent and to delete image
	boolean lastInsertId;
	Calendar cal;
	Integer date, month, year, hour, min, sec;
	
	httpClient http = new httpClient();	 							//object of httpClient

	private static String loginURL = "http:/192.168.100.1/android/vf/login.php";
	private static String msgURL = "http://192.168.100.1/android/vf/msg.php";
	private static String regURL = "http://192.168.100.1/android/vf/createuser.php";
	private static String imageURL = "http://192.168.100.1/android/vf/image.php";
   
	
	/**
	 * passing user information for registration to the server
	 * @param un username of user 
	 * @param em email address of user
	 * @param pass password of user
	 * @param stat status of user
	 * it will sent with this information to the server to add into the database 
	 */
	public void registrationIntoServer(String un,String em, String pass,String stat){
		
		try {
				nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("username",un));
				nameValuePairs.add(new BasicNameValuePair("email",em));
				nameValuePairs.add(new BasicNameValuePair("password",pass));
				nameValuePairs.add(new BasicNameValuePair("status",stat));
				
				
				Thread regThread = new Thread(){
					
					public void run(){
							// passing user email and password for validating  user from server
						
						http.httpConnection(nameValuePairs, inputStream,regURL);
						
					}//end of void run()
					
				};//end of userThread
				
				regThread.start();
				
				Log.d("<--RegistrationServer-->","Success in RegistrationServer");
			
		} catch (Exception e) {
			
			Log.e("<--RegistrationServer-->","Error in RegistrationServer" + e.toString());
			e.printStackTrace();
		}
		
	}//end of method
	
	/**
	 * passing inforamtion of email and password to check valid user from server
	 * @param em email of user
	 * @param pass password of user
	 * @return 
	 */
    public JSONObject loginDataIntoServer(String em, String pass){
    		
    	try {
    			nameValuePairs = new ArrayList<NameValuePair>();
    			nameValuePairs.add(new BasicNameValuePair("email",em));
    			nameValuePairs.add(new BasicNameValuePair("password",pass));
    			
    			JSONObject json = http.getJSONFromUrl(loginURL, nameValuePairs);
    			// return json
    			// Log.e("JSON", json.toString());
    			return json;
    		}finally{
    			
    			try {
    				
    			}
		    	catch (Exception e) {
					Log.e("<--r-->","Error in RegistrationServer" + e.toString());
					e.printStackTrace();
				}

    		}//end of finally
    	
    }//end of JSONObject
    
	/**
	 *Sent message to the server
	 *@param msg sent by user
	 */
	public void insertMsgIntoServer(String msg) {
	  
		try {
				nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("UserMessage", msg));		//Passing user message with Index Value "UserMessage"
				
				Thread msgThread = new Thread(){
					
					public void run(){
						
						http.httpConnection(nameValuePairs,inputStream,msgURL);			        //Calling httpConnection passing arrayList and InputStream						response = http.httpConnection(nameValuePairs);
						
					}//end of void run()
					
				};//end of userThread
				
				msgThread.start();
				Log.d("<<<-Inside insertMsgToServer->>>", "Success in saving msg into server");
	
			}catch(Exception ex) {
				
				Log.d("<<<-Inside insertMsgToServer->>>", "Error in saving msg into server");
				
			}//end of exception handling
	
	 }//end of insertMsg()
	
	 
	/**
	 *insertImage method will save the image into server with image information 
	 * @param imageData sent the image for processing to sent into the server
	 */
	 public void insertImageIntoServer(byte[] imageData) {
	    	
			cal = Calendar.getInstance();
			
			date = cal.get(Calendar.DATE);
			month = cal.get(Calendar.MONTH);
			year = cal.get(Calendar.YEAR);
		    
			hour = cal.get(Calendar.HOUR_OF_DAY);
			min = cal.get(Calendar.MINUTE);
			sec = cal.get(Calendar.SECOND);
			
			//String imageName = year.toString() + "-" + month.toString() + "-" + date.toString() + " " + hour.toString() + "." + min.toString() + "." + sec.toString();
			//String userMsg = etUserText.getText().toString();
			cameraActivity camera = new cameraActivity();
			
			try {
					/**
					 * First we get the image into JPEG format, then have to convert into Bitmap then into 
					 * ByteArrayOutputStream,after that into byte, then byte to String(Base64 encoded)
					 * All are these is into Android Side
					 * In Server side(String->binary(64decoded)->Image)
					 */


					String encodeImage = Base64.encodeBytes(imageData); 								//Using Base64.java file
					nameValuePairs = new ArrayList<NameValuePair>();
					lastInsertId = nameValuePairs.add(new BasicNameValuePair("UserImage", encodeImage));//
					
					http.httpConnection(nameValuePairs, inputStream,imageURL);
					
					//camera.deleteLastImageFromGallery();	//After Saving Image Into Server Image Will Delete From Gallery							
					
					Toast.makeText(getApplicationContext(), "Image inserted successfully!", Toast.LENGTH_SHORT).show();
			     
				}catch(Exception ex) {
					
						Toast.makeText(getApplicationContext(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
						
				}//end of exception handling
			
	}//end of insertImageIntoServer(byte[] imageData)

	
}//end of main class