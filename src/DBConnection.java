import java.sql.*;

public class DBConnection{
	
	private String name;
	private String username;
	private String password;
	private String host;
	private String databaseName;

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
	 */
	public void setName(String name){
		if(name != null){
			this.name = name;
		}
	}

	/**
	 * Getter de l'username
	 * @return l'attribut username
	 */
	public String getusername(){
		return this.username;
	}

	/**
	 * Setter de l'username
	 * @param username le nouveau nom d'utilisateur
	 */
	public void setusername(String username){
		if(username != null){
			this.username = username;
		}
	}

	/**
	 * Setter du password
	 * @param password le nouveau mot de passe
	 */
	public void setPassword(String password){
		if(password != null){
			this.password = password;
		}
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
	 */
	public void setHost(String host){
		if(host != null){
			this.host = host;
		}
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
	 */
	public void setDatabaseName(String databaseName){
		if ( databaseName != null )
			this.databaseName = databaseName;
	}

	/**
	 * Test la connexion à la BDD 
	 * @return vrai si la connexion s'effectue
	 */
	public boolean testConnectivity(){
		boolean ret = true;
	    try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
        }

        String address = "jdbc:mysql://" + this.host + "/" + this.databaseName;

        try {
			Connection connection = DriverManager.getConnection(address, this.username, this.password);
        } catch (SQLException ex) {
            ret = false;
        }

		return ret;
	}

	/**
	 * Affiche les caractéristiques de la connexion
	 * @return les caractéristiques
	 */
	public String toString(){
		String s = "Le nom de la connexion est: " + this.name;
		s += "\nVotre nom d'utilisateur: " + this.username;
		s += "\nLa base de donnée est stockée ici: " + this.host;
		s += "\nLe nom de la base données: " + this.databaseName;
		return s;
	}


}
