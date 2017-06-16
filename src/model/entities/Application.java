package model.entities;

import model.*;
import model.managers.*;
import lang.*;

public class Application {
	private User user;
	private Database db;
	private DBConnection connection;

	/*
	 * Initialisation des paramètres
	 */
	public Application(){
		
	}

	/*
	 * Getter de l'utilisateur
	 */
	public User getUser(){
		return this.user;
	}

	/*
	 * Permet d'indiquer quel utilisateur est connecté
	 * s'il à les bons identifiants/mot de passe
	 */
	public void login(){
        this.user = null;
        this.connection = null;
        this.db = null;
	}

	/*
	 * Permet à l'utilisateur courant de se déconnecter
	 */
	public void logout(){
        this.user = null;
        this.connection = null;
        this.db = null;
	}

	/*
	 * Getter de la connexion à la base de données
	 */
	public DBConnection getDBConnection(){
		return this.connection;
	}

	/*
	 * Getter de la classe permettant la modification de la base de données
	 * Database
	 */
	public Database getDatabase(){
		return this.db;
	}

	/*
	 * Connexion à une certaine base de données
	 */
	public void connect(DBConnection dbC){

	}

	/*
	 * Déconnexion de la base de données courante
	 */
	public void disconnect(){

	}

}