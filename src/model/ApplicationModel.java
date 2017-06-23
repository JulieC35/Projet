/**
 * The application class
 */

package model;

import model.entities.*;
import model.managers.*;
import lang.*;
import java.sql.*;

public class ApplicationModel {
    private UserManager authSystem;
	private QueryBuilder queryBuilder;

	private User currentUser;
	private DBConnection currentConnectionProfile;
	private Connection currentConnection;
	private String currentTable;

	/**
	 * Initialisation des paramètres
	 */
	public ApplicationModel(){
		this.authSystem = new UserManager();
		this.queryBuilder = new QueryBuilder();
		this.currentUser = null;
		this.currentConnectionProfile = null;
		this.currentConnection = null;
	
	}

	/**
	 * @return the currentConnection
	 */
	public Connection getConnection(){
		return this.currentConnection;
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
		return this.currentUser;
	}

	/**
	 * Getter de la connexion à la base de données
	 */
	public DBConnection getConnectionProfile(){
		return this.currentConnectionProfile;
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
	 * @param currentUsername The currentUsername
	 * @param password  the password
	 * @return true if the login process succeeded
	 */
	public boolean login(String currentUsername, String password){
		boolean ret = false;
		User tempUser = this.authSystem.authenticate(currentUsername, password);
		if ( tempUser != null ) {
			this.currentUser = tempUser;
			this.currentConnectionProfile = null;
			ret = true;
		}
		return ret;	
	}

	/**
	 * Permet à l'utilisateur courant de se déconnecter
	 */
	public void logout(){
        this.disconnect();
        this.currentUser = null;
	}

	/**
	 * Configuration du profil de connexion
	 */
	public void setConnectionProfile(DBConnection dbC){
		if ( dbC != null ) {
			this.currentConnectionProfile = dbC;
		}
	}

	/**
	 * Allows to chagne the curent table name
	 * @param table The new current table name
	 * @return true if the table name was set properly
	 * @throws SQLException if the table doesn't exist
	 */
	public boolean setCurrentTable(String table) throws SQLException{
		boolean ret = false;
		
		Statement stm = null;
		this.currentConnection.createStatement().executeQuery("SELECT * FROM " + table);
		this.currentTable = table;
		ret = true;

		return ret;
	}

	/**
	 * @return The current table name
	 */
	public String getCurrentTable(){
		return ( this.currentTable != null && !this.currentTable.equals("") ) ? this.currentTable : "n/a";
	}

	/**
	 * Sets the currentConnection profile and initializes the database
	 */
	public void connect(){
		if ( this.currentConnectionProfile != null )
			this.currentConnection = this.currentConnectionProfile.initialize();
	}

	/**
	 * Déconnexion de la base de données courante et du profil de connexion
	 */
	public void disconnect(){
		try{
			if ( this.currentConnection != null )	
				this.currentConnection.close();
		} catch(SQLException ex){
			System.err.println(L.get("sql-error-closing"));
		}
		this.currentConnectionProfile = null;
	}

    /**
     * Executes the reieved query on the current connection
     * @param query
     */
    public QueryResult processSQL(String query) {
        QueryResult ret = new QueryResult();
		try {
			Statement stm = this.currentConnection.createStatement();
			stm.execute(query);
            ret = new QueryResult(stm.getResultSet());
		} catch(SQLException ex){
			ret.setMessage(ex.getMessage());
		} catch(NullPointerException ex){
			ret.setMessage("Can't process a null query");
		}
        return ret;
	}

	/**
	 * Summerizes the application's information
	 * @return The information as a string
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.currentUser + "\n");
		sb.append(this.currentConnectionProfile + "\n");

		return sb.toString();
	}
}
