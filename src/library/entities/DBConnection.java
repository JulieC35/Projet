package library.entities;

import java.sql.*;
import java.io.Serializable;
import lang.*;

public class DBConnection implements Serializable{
	
	private String name;
	private String username;
	private String password;
	private String host;
	private String databaseName;
	private String driver;
	private String prefix;

	/**
	 * Constructeur de la classe
	 * @param name le nom de la connexion
	 * @param username le nom utilisé sur JDBC (nom de l'utilisateur sur la BDD)
	 * @param password le mot de passe pour la connexion JDBC
	 * @param host le lieu de stockage de la BDD
	 * @param databaseName le nom de la bdd
	 */
	public DBConnection(String name, String username, String password, String host, String databaseName){
		if(name != null){
			this.name = name;
		}
		if(username != null){
			this.username = username;
		}
		if(password != null){
			this.password = password;
		} else{
			this.password = new String("");
		}
		if(host != null){
			this.host = host;
		}
		if(databaseName != null){
			this.databaseName = databaseName;
		}
		this.prefix = "jdbc:mysql";
		this.driver = "com.mysql.jdbc.Driver";
	}

	/**
	 * Constructeur de la classe
	 */
	public DBConnection(){
		this.name = "";
		this.username = "";
		this.password = "";
		this.host = "";
		this.databaseName = "";
		this.prefix = "jdbc:mysql";
		this.driver = "com.mysql.jdbc.Driver";
	}

	/**
	 * Getter du nom
	 * @return l'attribut name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Setter du nom
	 * @param nom le nouveau nom
	 * @return true if the setting succeeded
	 */
	public boolean setName(String name){
		boolean ret = false;
		if( name != null && !name.equals("") && (name.length() == name.replaceAll("\\s","").length()) ){
			this.name = name;
			ret = true;
		}

		return ret;
	}

	/**
	 * Getter de l'username
	 * @return l'attribut username
	 */
	public String getUsername(){
		return this.username;
	}

	/**
	 * Setter de l'username
	 * @param username le nouveau nom d'utilisateur
	 * @return true if the setting succeeded
	 */
	public boolean setUsername(String username){
		boolean ret = false;
		if( username != null && !username.equals("") ){
			this.username = username;
			ret = true;
		}

		return ret;
	}

	/**
	 * Setter du password
	 * @param password le nouveau mot de passe
	 * @return true if the setting succeeded
	 */
	public boolean setPassword(String password){
		boolean ret = false;
		if(password != null){
			this.password = password;
			ret = true;
		}

		return ret;
	}

	/**
	 * Getter du host
	 * @return l'attribut host
	 */
	public String getHost(){
		return this.host;
	}

	/**
	 * Setter du host
	 * @param host la nouvelle valeur de host
	 * @return true if the setting succeeded
	 */
	public boolean setHost(String host){
		boolean ret = false;
		if( host != null && !host.equals("") ){
			this.host = host;
			ret = true;
		}

		return ret;
	}

	/**
	 * Getter du databaseName
	 * @return l'attribut databaseName
	 */
	public String getDatabaseName(){
		return this.databaseName;
	}

	/**
	 * Setter du databaseName
	 * @param databaseName le nouveau databaseName
	 * @return true if the setting succeeded
	 */
	public boolean setDatabaseName(String databaseName){
		boolean ret = false;
		if ( databaseName != null && !databaseName.equals("") ){
			this.databaseName = databaseName;
			ret = true;
		}

		return ret;
	}

	/**
	 * Initializes the connection
	 * @return The connection depending on this connection profile
	 */
	public Connection initialize(){
		Connection ret = null;

	    try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException ex) {
        }

        String address = this.prefix + "://" + this.host + "/" + this.databaseName;

        try {
			ret = DriverManager.getConnection(address, this.username, this.password);
        } catch (SQLException ex) {
        }

		return ret;
	}

	/**
	 * Test la connexion à la BDD 
	 * @return vrai si la connexion s'effectue
	 */
	public boolean testConnectivity(){
		boolean ret = false;
		
		if ( this.initialize() != null )
			ret = true;
			
		return ret;
	}

	/**
	 * Affiche les caractéristiques de la connexion
	 * @return les caractéristiques
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append(this.username + "@" + this.databaseName);

		return sb.toString();
	}
}
