/**
 * The application class
 */

package model;

import model.entities.*;
import model.managers.*;
import lang.*;
import java.sql.*;

public class Application {
    private UserManager authSystem;
	private QueryBuilder queryBuilder;
	private User user;
	private DBConnection connectionProfile;
	private Connection connection;

	/**
	 * Initialisation des paramètres
	 */
	public Application(){
		this.authSystem = new UserManager();
		this.queryBuilder = new QueryBuilder();
		this.user = null;
		this.connectionProfile = null;
		this.connection = null;
	}

	/**
	 * @return The auth systeù
	 */
	public UserManager getAuthSystem(){
		return this.authSystem;
	}

	/**
	 * Getter de l'utilisateur
	 */
	public User getUser(){
		return this.user;
	}

	/**
	 * Getter de la connexion à la base de données
	 */
	public DBConnection getConnectionProfile(){
		return this.connectionProfile;
	}

	/**
	 * @return The query builder of the application
	 */
	public QueryBuilder getQueryBuilder(){
		return this.queryBuilder;
	}

	/**
	 * Permet d'indiquer quel utilisateur est connecté
	 * s'il à les bons identifiants/mot de passe
	 * @param username The username
	 * @param password  the password
	 * @return true if the login process succeeded
	 */
	public boolean login(String username, String password){
		boolean ret = false;
		User tempUser = this.authSystem.authenticate(username, password);
		if ( tempUser != null ) {
			this.user = tempUser;
			this.connectionProfile = null;
			ret = true;
		}
		return ret;	
	}

	/**
	 * Permet à l'utilisateur courant de se déconnecter
	 */
	public void logout(){
        this.disconnect();
        this.user = null;
	}

	/**
	 * Configuration du profil de connexion
	 */
	public void setConnectionProfile(DBConnection dbC){
		if ( dbC != null ) {
			this.connectionProfile = dbC;
		}
	}

	/**
	 * Sets the connection profile and initializes the database
	 */
	public void connect(){
		if ( this.connectionProfile != null )
			this.connection = this.connectionProfile.initialize();
	}

	/**
	 * Déconnexion de la base de données courante et du profil de connexion
	 */
	public void disconnect(){
		try{
			if ( this.connection != null )	
				this.connection.close();
		} catch(SQLException ex){
			System.err.println(L.get("sql-error-closing"));
		}
		this.connectionProfile = null;
	}

	/**
	 * Summerizes the application's information
	 * @return The information as a string
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.user + "\n");
		sb.append(this.connectionProfile + "\n");

		return sb.toString();
	}
}
