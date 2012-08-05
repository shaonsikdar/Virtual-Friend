/**
 * 
 */
package com.virtualfriend;

import junit.framework.TestCase;

/**
 * @author Shaon
 *
 */
public class Validation extends TestCase {

	/**
	 * @param name
	 */
	public Validation(String name) {
		 super("com.virtualfriend"); 
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		loginActivity loginAc = getActivity();
	}

	private loginActivity getActivity() {
		// TODO Auto-generated method stub
		return null;
	}

}
