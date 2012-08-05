/**
 * 
 */
package com.virtualfriend;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

/**
 * @author Shaon
 *
 */

public class droidResponse extends Activity{

	/**
	 * activity of response of Droid 
	 * @return 
	 */
	
	String vfOutput ="";
	
	public String talkBack(String msg) {
	    	
	    	try {
	    		

					Log.i("<--droidActivity-->","Working in droidActivity talk() " + msg.toString());
					String userInput = msg.toString();
	
					
					if (userInput.equalsIgnoreCase("Hi")
							|| userInput.equalsIgnoreCase("Hey")
							|| userInput.equalsIgnoreCase("Hello")) {
						vfOutput = "Hellow";
					} else if (userInput.contains("What")
							|| userInput.contains("name")) {
						vfOutput = "Droid, Yours?";
					} else if (userInput.contains("thanks")
							|| userInput.equalsIgnoreCase("Thanks")
							|| userInput.equalsIgnoreCase("Thank You")) {
						vfOutput = "You are Welcome.";
					} else if (userInput.equalsIgnoreCase("Ron")
							|| userInput.equalsIgnoreCase("Shaon")) {
						vfOutput = "Wellcome " + userInput
								+ "!";
					} else if (userInput.equalsIgnoreCase("calc")
							|| userInput.equalsIgnoreCase("calculator")
							|| userInput.contains("calc")
							|| userInput.contains("calculator")) {
						vfOutput = "Wait!! Calculator Is opening .";
						
						actionCalc();
						
					} else {
						vfOutput = "Sorry !! I am unable to getting your point";
					}
					
					return vfOutput;
				
			} catch (Exception e) {
				
				Log.e("<--droidActivity-->","Error in droidActivity talk() " + e.toString());
				
			}//end of exception handling  
	    	
	    	if(vfOutput!=null)
	    	
	    		return vfOutput;
	    	
	    	else return "";
	    	
			
	    	/*} catch (Exception e) {v
				Log.e("<--droidActivity-->","Error in droidActivity talk() " + e.toString());
				e.printStackTrace();
			}*/
	    	
	    }//end of talkBack()

	/**
	 * Opening Calculator and many other applications on the request of user
	 */
	public  void actionCalc(){
		
		try {
				
				Intent calc = new Intent();
				calc.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
				startActivity(calc);
				
		} catch (Exception e) {
			
				Log.e("<--droidActivity-->","Error in droidActivity Calculator " + e.toString());
				e.printStackTrace();
		}//end of exception handling
		
	}//end of actionCalc()

}//end of main class
