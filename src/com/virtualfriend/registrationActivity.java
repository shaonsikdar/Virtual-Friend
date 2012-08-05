/**
 * 
 */
package com.virtualfriend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Shaon
 *
 */
public class registrationActivity extends Activity {
	
	Button btnlLoginPage,btnReg;
	EditText etRegUsername,etRegEmail,etRegPass,etRegStatus;
	java.io.InputStream is;
	ProgressDialog progresDialog; 
	serverActivity server = new serverActivity();

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);   
        
        initializeView();
    
    }//end of onCreate()
	
	/**
	 * Do everything for initializing the layout of xml file
	 */
	public void initializeView(){
	
		try {
				etRegUsername = (EditText) findViewById(R.id.editTextRegUsername);
				etRegEmail = (EditText) findViewById(R.id.editTextRegEmail);
				etRegPass = (EditText) findViewById(R.id.editTextRegPassword);
				etRegStatus = (EditText) findViewById(R.id.editTextRegStatus);
				btnlLoginPage = (Button) findViewById(R.id.buttonLoginPage);
				btnReg = (Button) findViewById(R.id.buttonRegistion);
				
				btnReg.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						
						registrationProcess();
						
					}//end of onClick()
					
				});//end of btnLogin event handler
				
				btnlLoginPage.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						
						finish();
						
					}//end of onClick()
				});//end of btnRegPafe event handler
		} catch (Exception e) {
			
			Log.e("<--initializeViewRegistration-->","Error in initializeViewRegistration" + e.toString());
			e.printStackTrace();
			
		}//end of exception handling
		
	}//end of initializeView()
	

	public void registrationProcess(){
		
		try {
				String username = etRegUsername.getText().toString();
				String email = etRegEmail.getText().toString();
				String pass = etRegPass.getText().toString();
				String status = etRegStatus.getText().toString();
				
				progresDialog = ProgressDialog.show(registrationActivity.this, "", "Wait! Loading....", true, false);
				server.registrationIntoServer(username, email, pass, status);
				Toast.makeText(getApplicationContext(), "You are Succesfully Register", Toast.LENGTH_LONG).show();
				Log.d("<--Registration-->","Success in Registration");
				finish();
			
		} catch (Exception e) {
			
			Toast.makeText(getApplicationContext(), "Error In Register", Toast.LENGTH_LONG).show();
			Log.e("<--Registration-->","Error in Registration" + e.toString());
			e.printStackTrace();
			
		}//end of exception handling
	}//end of registrationProcess()

}//end of main class