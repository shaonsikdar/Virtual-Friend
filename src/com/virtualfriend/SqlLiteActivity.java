/**
 * 
 */
package com.virtualfriend;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Shaon
 *
 */
public class SqlLiteActivity extends Activity {

	SQLiteDatabase sampleDB; 
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite);   
        
        sampleDB = this.openOrCreateDatabase("VirtualFriend",SQLiteDatabase.CREATE_IF_NECESSARY, null);
        sampleDB.setVersion(1);  //version of db
        sampleDB.setLocale(Locale.getDefault()); // for iso/utf 
        sampleDB.setLockingEnabled(true); // save tread the db . One thread can only acess at a time.
        
        String createUser=  "create table if not exists USER (id int ,name varchar,password varchar); ";
        sampleDB.execSQL(createUser);
        
        initializeView();
    
    }//end of onCreate()
	
	/**
	 * Do everything for initializing the layout of xml file
	 */
	public void initializeView(){
	
	    try {
//				etUserText = (EditText) findViewById(R.id.editText1);
//				tvChatArea = (TextView) findViewById(R.id.chatArea);
//				btnTalk = (Button) findViewById(R.id.buttonTalk);
//				buttonCamera = (Button) findViewById(R.id.buttonCamera);
//				final droidResponse droid = new droidResponse();
//	   
//				btnTalk.setOnClickListener(new View.OnClickListener() {
//					public void onClick(View v) {
//						
//						String userMsg = etUserText.getText().toString();
//						tvChatArea.setText(tvChatArea.getText().toString() + "You: " + etUserText.getText().toString() + "\n" + "Droid : " + droid.talkBack(userMsg) + "\n");
//					
//						server.insertMsgIntoServer(userMsg);//sent usercurrent messgae to the server to excute user mood
//						etUserText.setText("");
//						
//					}//end of onClick()
//					
//				});//end of btnTalk event handler
//				
//				buttonCamera.setOnClickListener(new View.OnClickListener() {
//					public void onClick(View v) {
//						
//						startCameraActivity();
//						
//					}//end of onClick()
//				});//end of buttonCamera event handler
			
		} catch (Exception e) {
			
			Log.e("<--initializeViewchatbot-->","Error in initializeViewchatbot" + e.toString());
			e.printStackTrace();
			
		}//end of exception handling
		
	}//end of initializeView()

	
}//end of main class