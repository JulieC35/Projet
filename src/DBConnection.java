public class DBConnection{
	
	private String name;
	private String username;
	private String password;
	private String host;
	private int port;

	/**
	 * Constructeur de la classe
	 * @param name le nom de la connexion
	 * @param username le nom utilisé sur JDBC (nom de l'utilisateur sur la BDD)
	 * @param password le mot de passe pour la connexion JDBC
	 * @param host le lieu de stockage de la BDD
	 * @param port le port de connexion à la BDD
	 */
	public DBConnection(String name, String username, String password, String host, int port){

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

	}

	/**
	 * Getter du port
	 * @return l'attribut port
	 */
	public int getPort(){
		return this.port;
	}

	/**
	 * Setter du port
	 * @param port le nouveau port
	 */
	public void setPort(int port){

	}

	/**
	 * Test la connexion à la BDD 
	 * @return vrai si la connexion s'effectue
	 */
	public boolean testConnectivity(){
		return false;
	}

	/**
	 * Affiche les caractéristiques de la connexion
	 * @return les caractéristiques
	 */
	public String toString(){
		return "lol";
	}


}