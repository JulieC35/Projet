/**
 * Handles the authentication process of the appllication
 */

import java.util.*;

public class UserManager{
	private ArrayList<User> users;
	
	/**
	 * The constructor
	 */
	public UserManager(){
		this.users = new ArrayList<User>();

		// FilesHandler.objectsToList(location:String) returns a list of Objects, we need to cast them into User objects
		for ( Object u : FilesHandler.objectsToList("data/users") ){
			this.users.add((User)u);
		}
	}

	/**
	 * Allows the user to log into the application
	 * @param username The username of the user
	 * @param password The password of the user
	 * @return The user if the login information are correct, null if nothings matches
	 */
	public User authentication(String username, String password){
		User ret = null;
			for ( User u : this.users ){
				if ( u.getUsername().equals(username) )
					ret = u;
			}
		return ret;
	}
}