/**
 * A MySQL database connection.<br>
 * Stores all the information to access a database (username, password, host, database name).<br>
 * The name attribute is used to distinguish the connections in a collection context, and the driver and prefix 
 * attributes are used bu JDBC to create the connection.<br>
 * When a DBConnection is created, the testConnectivity() method should be used to make sure that the connection is
 * correctly configured and available.<br>
 */

package library.entities;

import java.sql.*;
import java.io.Serializable;
import java.util.*;

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
	 * The constructor of the class
	 * @param name The name of the connection
	 * @param username The username to access the database with
	 * @param password The password for this database
	 * @param host The location of the database
	 * @param databaseName The name of the database
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
	 * The constructor of the class
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
	 * @return the name attribute
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * @param name the new name
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
	 * @return the username attribute
	 */
	public String getUsername(){
		return this.username;
	}

	/**
	 * @param username The new database username
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
	 * @param password The new password
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
	 * @return The password of the connection
	 */
	public String getPassword(){
		return this.password;
	}

	/**
	 * @return the host attribute
	 */
	public String getHost(){
		return this.host;
	}

	/**
	 * @param host the new location
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
	 * @return the databaseName attribute
	 */
	public String getDatabaseName(){
		return this.databaseName;
	}

	/**
	 * @param databaseName the new databaseName
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
	 * Allows to retrive the list of tables of this database
	 * @param connection The database connection
	 * @throws SQLException if the table list could not be retrieved
	 */
	public static ArrayList<String> getTablesList(Connection connection) throws SQLException{
		ArrayList<String> ret = new ArrayList<String>();
		if ( connection != null ) {
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet result = metaData.getTables(null, null, "%", null);

			while(result.next()){
				ret.add(result.getString("TABLE_NAME"));
			}
		} else {
			throw new SQLException("Connection not set");
		}

		return ret;
	}

	/**
	 * Tests the connection
	 * @return true if the connection is working
	 */
	public boolean testConnectivity(){
		boolean ret = false;
		
		if ( this.initialize() != null )
			ret = true;
			
		return ret;
	}
}
