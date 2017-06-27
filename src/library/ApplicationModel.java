/**
 * The global information of the application.<br>
 * Contains the current user, DBConnection, database, table name that are used by the whole application.
 */

package library;

import library.entities.*;
import library.managers.*;
import lang.*;
import java.sql.*;
import java.util.*;

public class ApplicationModel {
    private UserManager authSystem;
	private QueryBuilder queryBuilder;
	private User currentUser;
	private DBConnection currentConnectionProfile;
	private Connection currentConnection;
	private String currentTable;
	private HashMap<String, String> currentConfiguration;
	private HashMap<String, Object> post;

	/**
	 * The constructor of the class.<br>
	 * Initializes the UserManager and the QueryBuilder of the application
	 */
	public ApplicationModel(){
		this.currentConfiguration = FilesHandler.xmlToMap("data/config.xml");

		// If the HashMap is empty, we create it and save the file for further use
		if ( this.currentConfiguration == null ){
			this.currentConfiguration = new HashMap<String, String>();
			this.currentConfiguration.put("language", "FRENCH");
			this.saveConfiguration();
		}

		L.initialize(this.currentConfiguration.get("language"));


		this.authSystem = new UserManager();
		this.queryBuilder = new QueryBuilder();
		this.currentUser = null;
		this.currentConnectionProfile = null;
		this.currentConnection = null;
		this.post = new HashMap<String, Object>();
	}

	/**
	 * Allows to store posted values
	 * @param key The key of the value
	 * @param posted The value to store
	 */
	public void postValue(String key, Object value){
		if ( key != null && value != null && !key.equals("") )
			this.post.put(key, value);
	}

	/**
	 * Allows to retrive a posted value in the map. Once the value is retrieved, it gets deleted from the map
	 * @return The key of the value
	 * @throws Exception In case something went wrong
	 */
	public Object getPostedValue(String key) throws Exception{
		Object ret = null;
		
		ret = this.post.get(key);
		this.post.remove(key);

		return ret;
	}

	/**
	 * Allows to save the current configuration of the application
	 */
	public void saveConfiguration(){
		FilesHandler.mapToXml("data/config.xml", this.currentConfiguration);
	}

	/**
	 * Allows to edit a configuration rule of the application
	 * @param code The configuration rule's identifier
	 * @param code The rule new value
	 */
	public void editConfigurationRule(String code, String value){
		if ( code != null & value != null && !code.equals("") )
			this.currentConfiguration.put(code, value);
	}

	/**
	 * Allows to retrieve a configuration rule of the application
	 * @param code The configuration rule's identifier
	 */
	public String getConfigurationRule(String code){
		return this.currentConfiguration.get(code);
	}

	/**
	 * @return the currentConnection
	 */
	public Connection getConnection(){
		return this.currentConnection;
	}

	/**
	 * @return The auth system
	 */
	public UserManager getAuthSystem(){
		return this.authSystem;
	}

	/**
	 * @return The current user
	 */
	public User getUser(){
		return this.currentUser;
	}

	/**
	 * @return the current connection profile
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
	 * Allows to log in the user, granted that the username and password are valid
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
	 * Allows to log out the user
	 */
	public void logout(){
        this.disconnect();
        this.currentUser = null;
	}

	/**
	 * @param dbC The DBConnection to connect to
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
	 * Initializes the database connection based on the current connection profile
	 */
	public void connect(){
		if ( this.currentConnectionProfile != null )
			this.currentConnection = this.currentConnectionProfile.initialize();
	}

	/**
	 * Allows to disconnect from the current database/connection profile
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
     * @param query The query to process
	 * @return The result of the SQL query
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
