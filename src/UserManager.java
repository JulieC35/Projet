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

		// FilesHandler.objectsToList(location:String) returns a list of Object objects, we need to cast them into User objects
		for ( Object u : FilesHandler.objectsToList("data/users") ){
			this.users.add((User)u);
		}
	}

	/**
	 * Allows the user to log into the application
	 * @param username The username of the user
	 * @param password The password of the user
	 * @return The user if the login information are correct, null if nothing matches
	 */
	public User authenticate(String username, String password){
		User ret = null;
		User tempUser = null;
		for (int i = 0 ; i < this.users.size() ; i++) {
			tempUser = this.users.get(i);
			if ( tempUser.getUsername().equals(username) && tempUser.verifyPassword(password) )
				ret = tempUser;
		}
		return ret;
	}

	/**
	 * Allows to remove an user from the list
	 * @param user The user to be removed
	 */
	public void removeUser(User user){
		if ( user != null )
			this.users.remove(user);
	}

	/**
	 * Allows to add an user to the list
	 * @param user The user to add
	 */
	public void addUser(User user){
		if ( user != null ) 
			this.users.add(user);
	}

	/**
	 * Allows to retrieve the list of users
	 */
	public ArrayList<User> getUsers(){
		return this.users;
	}
}