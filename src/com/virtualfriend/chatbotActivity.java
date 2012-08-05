/**
 * 
 */
package com.virtualfriend;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
public class chatbotActivity extends Activity {

	public EditText etUserText;
	Button btnTalk;
	TextView tvChatArea;
	serverActivity server = new serverActivity();
	
	//Below all variable used for camera INTENT
	Button buttonCamera;
	//cameraActivity camera = new cameraActivity();
	private static final int CAMERA_REQUEST_CODE = 100;
	boolean _taken;
	
	//Context context = getApplicationContext();
	//CharSequence text = "Hello toast!";
	//int duration = Toast.LENGTH_SHORT;
	//Toast toast = Toast.makeText(context, text, duration);
	//Toast position = toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbot);   
        
        initializeView();
    
    }//end of onCreate()
	
	/**
	 * Do everything for initializing the layout of xml file
	 */
	public void initializeView(){
	
	    try {
				etUserText = (EditText) findViewById(R.id.editText1);
				tvChatArea = (TextView) findViewById(R.id.chatArea);
				btnTalk = (Button) findViewById(R.id.buttonTalk);
				buttonCamera = (Button) findViewById(R.id.buttonCamera);
				final droidResponse droid = new droidResponse();
	   
				btnTalk.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						
						String userMsg = etUserText.getText().toString();
						tvChatArea.setText(tvChatArea.getText().toString() + "You: " + etUserText.getText().toString() + "\n" + "Droid : " + droid.talkBack(userMsg) + "\n");
					
						server.insertMsgIntoServer(userMsg);//sent usercurrent messgae to the server to excute user mood
						etUserText.setText("");
						
					}//end of onClick()
					
				});//end of btnTalk event handler
				
				buttonCamera.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						
						startCameraActivity();
						
					}//end of onClick()
				});//end of buttonCamera event handler
			
		} catch (Exception e) {
			
			Log.e("<--initializeViewchatbot-->","Error in initializeViewchatbot" + e.toString());
			e.printStackTrace();
			
		}//end of exception handling
		
	}//end of initializeView()
	
			
	public void startCameraActivity() {
			
	    	try {
	    		
					Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					Log.e("<--cameraActivity-->",cameraIntent.toString());
					startActivityForResult(cameraIntent,CAMERA_REQUEST_CODE);
					
			} catch (Exception e) {
				
				Log.e("<--cameraActivity-->","Error in startCameraActivity " + e.toString());
				e.printStackTrace();
			}
			
		}//end of startCameraActivity()
	
	/**
	 * onActivityResult for capturing image 
	 * reqestCode is the CAMERA_REQUEST_CODE 
	 * resultCode is the result of camera 
	 */
	 public void onActivityResult(int requestCode, int resultCode, Intent intent) {
			
	    	if(requestCode == CAMERA_REQUEST_CODE) {
					if(resultCode == RESULT_OK) {
						
							onPhotoTaken(intent);
							
					 }else {
						
							 Toast.makeText(getApplicationContext(), "Camera request rejected!", Toast.LENGTH_LONG).show();
							
				       }//end of if else condition
			}//end of if condition
	    	
	 }//end of onActivityResult
	 
	 
	 /**
	  * In this method what will do after capturing image
	  * @param i is the image sending from camera intent
	  */
	 public void onPhotoTaken(Intent i) {
			
		 	try {
					_taken = true;
					serverActivity server = new serverActivity();
					
					Bitmap bitmap = (Bitmap) i.getExtras().get("data");
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					bitmap.compress(CompressFormat.JPEG, 0, bos);
					byte[] imageData = bos.toByteArray();
					server.insertImageIntoServer(imageData); //passing image to save image into server
					
			} catch (Exception e) {
				Log.e("<--cameraAcitivity-->","Error in onPhotoTake " + e.toString());
				e.printStackTrace();
			}
			
		}//end of onPhotoTaken()
		
	 
		/**
		 * Last Image will be delete from the Image Gallery after Sending Image to the Server
		 */
	    public void deleteLastImageFromGallery() {
	    	
	    	try {
					int i=0;	    	
					String[] projection = {MediaStore.Images.Media.DATA};
					Cursor cursor = this.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
					
					ArrayList<String> imagePath = new ArrayList<String>();
					
					while(cursor.moveToNext()) {
					
						imagePath.add(cursor.getString(0));
						i++;
						
					}//end of while loop 
					
					cursor.close();
					i--;
					
					File file = new File(imagePath.get(i));
					file.delete();
					Toast.makeText(getApplicationContext(), "Last image has been deleted!", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				
				Log.e("<--cameraActivity-->","Error in deleteLastImageFromGallery " + e.toString());
				e.printStackTrace();
			}
	    	
	    }//end of deleteLastImageFromGallery()
	
}//end of main class