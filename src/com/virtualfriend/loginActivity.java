/**
 * 
 */
package com.virtualfriend;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Shaon
 *
 */
public class loginActivity extends Activity {
	
	Button btnlLogin,btnRegPage,btnInfo;
	EditText etEmail,etPassword;
	TextView tvInfo;
	CheckBox cbAutoLogin;
	String result = "0";
	serverActivity server = new serverActivity();
	httpClient http = new httpClient();
	
	String response;
	ProgressDialog progresDialog; 
	ArrayList<NameValuePair> nameValuePairs;
	
	
	 // JSON Response node names
    private static String KEY_SUCCESS = "success";
  
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);   

        initializeView();
    
    }//end of onCreate()
	
	/**
	 * Do everything for initializing the layout of xml file
	 */
	public void initializeView(){
	
	    try {
				etEmail = (EditText) findViewById(R.id.editTextEmail);
				etPassword = (EditText) findViewById(R.id.editTextPassword);
				btnlLogin = (Button) findViewById(R.id.ButtonLogin);
				btnRegPage = (Button) findViewById(R.id.buttonRegistrationPage);
				cbAutoLogin = (CheckBox) findViewById(R.id.RememberLoginBox);
				btnInfo = (Button) findViewById(R.id.buttonYourInfo);
				tvInfo=(TextView) findViewById(R.id.textViewInfo);
				
				/**
				 * event handler of btnLogin. it's the logic what will happen
				 * after clicking the button
				 */
				btnlLogin.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						
//						String email = etEmail.getText().toString();
//						String pass = etPassword.getText().toString();
						
//						JSONObject json = server.loginDataIntoServer(email, pass);
//					Toast.makeText(getApplicationContext(), (CharSequence) json, Toast.LENGTH_LONG).show();
						
						Intent chat = new Intent(getApplicationContext(), chatbotActivity.class);				   // Make a intent of chatbotactivity
					    startActivity(chat);																	   // Start Chatbot Activity
					    
					    finish();     																			   // Finish loginActivity
					    
					}//end of onClick(View v)
					
				});//end of btnLogin event handler
				
				
				/**
				 * Button Handler of btnRegPage
				 * After Pressing this button user will shift from this activity
				 * to Registration activity
				 */
				btnRegPage.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
					
						// Switching to Register screen
				        Intent reg = new Intent(getApplicationContext(), registrationActivity.class);
				        startActivity(reg);
				        
					}//end of onClick()
				});//end of btnRegPafe event handler
				
				
				/**
				 * btnInfo button handler.
				 * Getting all information of specific user written in
				 * email and password field in the login form
				 */
				btnInfo.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

						String inputEmail = etEmail.getText().toString();
						String inputPass = etPassword.getText().toString();
						
						nameValuePairs = new ArrayList<NameValuePair>();
						nameValuePairs.add(new BasicNameValuePair("email", inputEmail));		//Passing user email with Index Value "email"
						nameValuePairs.add(new BasicNameValuePair("password", inputPass));      //Passing user password with Index Value "password"
						
						progresDialog = ProgressDialog.show(loginActivity.this, "", "Wait! Loading....", true, false);
						
						//Creating A thread for processing login faster 
						Thread userThread = new Thread(){
							
							public void run(){
								
								// By Using Below code app will be slower/sleep for 10000   
								
/*								try {
									Thread.sleep(10000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
*/
								
								// passing user email and password for validating  user from server
								
								response = http.httpConnection(nameValuePairs);
								handler.sendEmptyMessage(0);
								
							}//end of void run()
							
						};//end of userThread
						
						userThread.start();
						
					}//end of onClick(View V)
					
				});//end of btnInfo.serOnClickListener()
				
		} catch (Exception e) {
			
			Log.e("<--initializeViewLogin-->","Error in initializeViewLogin" + e.toString());
			e.printStackTrace();
			
		}//end of exception handling
	    
	}//end of initializeView()
	
	
	//Create arrayList to keep the profile of the user. So to use the profile info any time.
	
	ArrayList<Profile> userProfile = new ArrayList<Profile>();
	Handler handler = new Handler(){
		
		/**
		 * 
		 */
		public void handleMessage(Message msg) {
    		progresDialog.dismiss();
    		if(response !=null){
				
    			Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
				//tvInfo.setText(response);
				
				try {
					
						JSONArray jsonUserArray = new JSONArray(response);			//initialize array for keeping user information into array
						String tv = ""; 											// Initially tv textfield is blank
						
							//int i = 0; 											// 												
							JSONObject userObj = jsonUserArray.getJSONObject(0);	// An Object of jsonUserArray
							int id = Integer.parseInt(userObj.getString("id"));		// Put id of user into id variable  
							String name = userObj.getString("username");			// Put id of username into username variable
							String email = userObj.getString("email");				// Put id of email into email variable
							String pass = userObj.getString("password");			// Put id of password into password variable
							String status = userObj.getString("status");			// Put id of status into status variable
							
							Profile profile = new Profile(id, name, email, pass, status);  // Put all data of user into Profile Object
							userProfile.add(profile);									   // Add previous profile object into userProfile ArrayList
							
							tv += profile.toString();								// Add all information of user 
						
						tvInfo.setText(tv);										    // Show user information of user into tvInfo textField
					
						String inputEmail = etEmail.getText().toString(); 			// keep User Input Email into inputEmail Varible 
						String inputPass = etPassword.getText().toString();			// keep User Input Password into inputPass Varible
							
						// Checking the input email and password form server
						if (inputEmail.equals(email)&&inputPass.equals(pass)){
						        try {
		
						        			Toast.makeText(getApplicationContext(), "Suceesfully Log In!", Toast.LENGTH_LONG).show();  // Give a successful message
											Intent chat = new Intent(getApplicationContext(), chatbotActivity.class);				   // Make a intent of chatbotactivity
										    startActivity(chat);																	   // Start Chatbot Activity
										    
										    finish();     																			   // Finish loginActivity 
										    
									}//end of try block
		
						        catch (Exception e) {
								
						        	Log.e("<--inputEmail.equals(email)&&inputPass.equals(pass)-->","inputEmail.equals(email)&&inputPass.equals(pass) :" + e.toString());
						        	
								}//end of exception handling
						        
						}else{
							
				        	Toast.makeText(getApplicationContext(), "Check your email and password!", Toast.LENGTH_LONG).show(); 	  // An Error Message to user for checking mail and password
				        	
				        }//end of if else condition

						
				} catch (JSONException e) {
					
					e.printStackTrace();
					
				}//end of exception handling
				
			}//end of if condition
    		
    		super.handleMessage(msg);
    		
    	}//end of handleMessage()
    	
    };//end of handler

 }//end of main class