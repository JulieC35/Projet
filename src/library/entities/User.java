package library.entities;

import java.util.*;
import java.io.Serializable;
import library.*;
import lang.*;

public class User implements Serializable{

	private String username;
	private String password;
	private Authorization authorization;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Language language;
	private ArrayList<DBConnection> connections;

	/*
	 * The constructor of the class
	 */
	public User(String username, String firstName, String lastName, String emailAddress, String pwd, Authorization authorization, Language language, ArrayList<DBConnection> connections){
		if(username != null){
			this.username = username;
		}
		if(firstName != null){
			this.firstName = firstName;
		}
		if(lastName != null){
			this.lastName = lastName;
		}
		if(emailAddress != null){
			this.emailAddress = emailAddress;
		}
		if(pwd != null){
			this.password = pwd;
		} else{
			this.password = new String("");
		}
		if(authorization != null){
			this.authorization = authorization;
		} else{
			this.authorization = Authorization.DEFAULT;
		}
		if(language != null){
			this.language = language;
		} else{
			this.language = Language.FRENCH;
		}
		if(connections != null){
			this.connections = connections;
		} else{
			this.connections = new ArrayList<DBConnection>();
		}
	}

	/**
	* Get the authorization level
	* @return authorization the level
	*/
	public Authorization getAuthorization(){
		return this.authorization;
	}

	/**
	* Get the pseudonym
	* @return username the username of the user
	*/
	public String getUsername(){
		return this.username;
	}

	/**
	* Set the username
	* @param username the pseudonym of the user
	*/
	public void setUsername(String username){
		this.username=username;
	}

	/**
	 * Allows to change the password of the user
	 * @param password The new password
	 */
	public void setPassword(String password){
		this.password = ( password != null ) ? password : "";
	}

	/**
	* Get the first name 
	* @return firstName the first name of the user
	*/
	public String getFirstName(){
		return this.firstName;
	}

	/** 
	* Set the first name
	* @param firstName the first name of the user
	*/
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}

	/**
	* Get the last name
	* @return lastName the last name of the user
	*/
	public String getLastName(){
		return this.lastName;
	}

	/**
	* Set the last name
	* @param lastName the last name of the user
	*/
	public void setLastName(String lastName){
		this.lastName=lastName;
	}

	/**
	* Get the mail
	* @return mail the email of the user
	*/
	public String getEmailAddress(){
		return this.emailAddress;
	}

	/**
	* Set the mail
	* @param email the email of the user
	*/
	public void setEmailAddress(String emailAddress){
		this.emailAddress=emailAddress;
	}

	/**
	* Get the DBConnection list
	* @return The list of connections
	*/
	public ArrayList<DBConnection> getDBConnections(){
		return this.connections;
	}

	/**
	* Get the DBConnection with the corresponding name
	* @param nameCo the name of the DBConnection
	* @return The connection
	*/
	public DBConnection getDBConnection(int idCo){
		DBConnection ret = null;
		if ( idCo >= 0 && idCo < this.connections.size() )
			ret = this.connections.get(idCo);

		return ret;
	}

	/**
	* Adds a connection
	*/
	public void addDBConnection(String name, String username, String password, String host, String databaseName){
		this.connections.add(new DBConnection(name, username, password, host, databaseName));
	}

	/**
	* Adds a connection
	* @param dbC The connection to add
	*/
	public void addDBConnection(DBConnection dbC){
		if ( dbC != null ){
			this.connections.add(dbC);
		}
	}

	/**
	* Get the language
	* @return language 
	*/
	public Language getLanguage(){
		return this.language;
	}

	/**
	* Set the language
	* @param lng the language
	*/
	public void setLanguage(Language lng){
		if(lng != null){
			this.language = lng;
		} else{
			System.err.println("Pas d'autre langage en paramètre");
		}
	}

	/**
	* Remove a connection
	* @param nameCo the name of the connection
	*/
	public boolean removeDBConnection(int idCo){
		boolean ret = false;
		if ( idCo >= 0 && idCo < this.connections.size() && this.connections.remove(idCo) != null )
			ret = true;

		return ret;
	}

	/**
	* Edit a connection 
	* @param nameCo the name of the connection
	* @param db the dbConnection to change name
	*/
	public void editDBConnection(String nameCo, DBConnection db){
		if((nameCo != null) && (db != null)){
			if(connections.contains(db)){
				int index = connections.indexOf(db);
				connections.get(index).setName(nameCo);
			}
		}
	}

	/**
	 * Verifies if the given password matches the user's
	 * @param password The password to check
	 * @return true if the passwords match
	 */
	public boolean verifyPassword(String password){
		return ( password != null && this.password.equals(password) ) ? true : false;
	}

	/**
	* Display informations
	* @return the informations 
	*/
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("username: " + this.username);
		sb.append(", firstName: " + this.firstName);
		sb.append(", lastName: " + this.lastName);
		sb.append(", emailAddress: " + this.emailAddress);
		sb.append(", language: " + this.language);
		sb.append(", authorization: " + this.authorization);
		sb.append(", connections: " + this.connections.size());
		return sb.toString();
	}
}