

import library.*;
import library.entities.*;
import library.managers.*;

/**
* Class Application
*/
public class Application {
	private UserManager authSystem;
	private User user;
	private Database db;
	private DBConnection connection;

	/**
	 * The constructor of the class
	 */
	public Application(){
		this.authSystem = new UserManager();
	}

	/**
	 * Get the user
	 * @return user the user
	 */
	public User getUser(){
		return this.user;
	}

	/**
	 * Allows the user to login if his username and his password are good
	 */
	public void login(){
	}

	/**
	 * Allows the user to logout
	 */
	public void logout(){
        this.user = null;
        this.connection = null;
        this.db = null;
	}

	/**
	 * Get the connection to the database
	 * @return connection the connection
	 */
	public DBConnection getDBConnection(){
		return this.connection;
	}

	/**
	 * Get the database 
	 * @return db the database
	 */
	public Database getDatabase(){
		return this.db;
	}

	/**
	 * Connection to a database
	 * @param dbC the connection to the database
	 */
	public void connect(DBConnection dbC){
		
	}

	/**
	 * Disconnection to the database
	 */
	public void disconnect(){

	}

}