import java.util.*;

public class User{

	private String name;
	private Authorization authLevel;
	private String firstName;
	private String lastName;
	private String mail;
	private String pwd;
	private Language language;
	private ArrayList<DBConnection> connections;

	public User(String name, String firstName, String lastName, String mail, String pwd, Authorization authLevel, Language language, ArrayList<DBConnection> connections){
		if(name != null){
			this.name = name;
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
			this.pwd = pwd;
		} else{
			this.pwd = new String("");
		}
		if(authLevel != null){
			this.authLevel = authLevel;
		} else{
			this.authLevel = Authorization.DEFAULT;
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
		return this.authLevel;
	}

	/**
	* Get the pseudonym
	* @return name the pseudonym of the user
	*/
	public String getName(){
		return this.name;
	}

	/**
	* Set the pseudonym
	* @param name the pseudonym of the user
	*/
	public void setName(String name){
		this.name=name;
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
	* Set the password
	* @param pwd the password of the user
	*/
	public void setPasswrd(String pwd){
		this.pwd=pwd;
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
	* Display informations
	* @return the informations 
	*/
	public String toString(){
		String s = L.get("user-name-label") + this.name;
		s += L.get("user-flName-label") + this.firstName + this.lastName;
		s += L.get("user-mail-label") + this.mail;
		s += L.get("user-lang-label") + this.language;
		s += L.get("user-authorization-label") + this.authLevel;
		s += L.get("user-connection-label") + this.connections.size() + "connections";
		return s;
	}
}