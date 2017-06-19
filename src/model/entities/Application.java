package model.entities;

import model.*;
import model.managers.*;
import lang.*;

public class Application {
    private UserManager authSystem;
	private User user;
	private Database db;
	private DBConnection connection;

	/**
	 * Initialisation des paramètres
	 */
	public Application(){
		this.authSystem = new UserManager();
	}

	/**
	 * Getter de l'utilisateur
	 */
	public User getUser(){
		return this.user;
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
			ret = true;
		}
		return ret;	
	}

	/**
	 * Permet à l'utilisateur courant de se déconnecter
	 */
	public void logout(){
        this.user = null;
        this.connection = null;
        this.db = null;
	}

	/**
	 * Getter de la connexion à la base de données
	 */
	public DBConnection getDBConnection(){
		return this.connection;
	}

	/**
	 * Getter de la classe permettant la modification de la base de données
	 * Database
	 */
	public Database getDatabase(){
		return this.db;
	}

	/**
	 * Connexion à une certaine base de données
	 */
	public void connect(DBConnection dbC){

	}

	/**
	 * Déconnexion de la base de données courante
	 */
	public void disconnect(){

	}
}