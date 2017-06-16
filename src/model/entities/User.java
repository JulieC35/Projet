package model.entities;

import java.util.*;
import model.*;
import lang.*;

public class User{

	private String username;
	private String password;
	private Authorization authorization;
	private String firstName;
	private String lastName;
	private String mail;
	private Language language;
	private ArrayList<DBConnection> connections;

	public User(String username, String firstName, String lastName, String mail, String pwd, Authorization authorization, Language language, ArrayList<DBConnection> connections){
		if(username != null){
			this.username = username;
		}
		if(firstName != null){
			this.firstName = firstName;
		}
		if(lastName != null){
			this.lastName = lastName;
		}
		if(mail != null){
			this.mail = mail;
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
	public String getMail(){
		return this.mail;
	}

	/**
	* Set the mail
	* @param email the email of the user
	*/
	public void setMail(String email){
		this.mail=email;
	}

	/**
	* Get the DBConnection
	* @return 
	*/
	public DBConnection[] getDBConnections(){
		DBConnection[] ret = new DBConnection[this.connections.size()];
		ret = (DBConnection[])connections.toArray();
		return ret;
	}

	/**
	* Add a connection
	*/
	public void addDBConnection(String name, String username, String password, String host, String databaseName){
		this.connections.add(new DBConnection(name, username, password, host, databaseName));
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
	public void removeDBConnection(String nameCo){
		boolean ret = false;
		Iterator<DBConnection> itr = this.connections.iterator();
		while((itr.hasNext()) && (!ret)){
			DBConnection db = itr.next();
			if(db.getName().equals(nameCo)){
				ret = this.connections.remove(db);
			}
		}
		if(!ret){
			System.err.println("La base de donnée n'existait pas");
		}
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
		sb.append(", mail: " + this.mail);
		sb.append(", language: " + this.language);
		sb.append(", authorization: " + this.authorization);
		sb.append(", connections: " + this.connections.size());
		return sb.toString();
	}
}