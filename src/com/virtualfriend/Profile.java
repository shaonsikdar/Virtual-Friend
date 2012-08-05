/**
 * 
 */
package com.virtualfriend;

/**
 * @author Shaon
 *
 */
public class Profile {
	
	private int id;
	private String username;
	private String email;
	private String pass;
	private String status;
	
	/**
	 * 
	 * @param id
	 * @param username
	 * @param email
	 * @param pass
	 * @param status
	 */
	public Profile(int id, String username, String email, String pass, String status) {
	
		this.id = id;
		this.username = username;
		this.email = email;
		this.pass = pass;
		this.status = status;
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * 
	 * @param pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 
	 */
	public String toString(){

		return "UserID: "+id+ " Username: " +username+ " Email: " +email+ "Password: " +pass+ "Status: "+status ; 
		
	}//end of toString()
	
}//end of main class
