import java.util.*;

public class User{

	private String name;
	private Authorization authorization;
	private String firstName;
	private String lastName;
	private String mail;
	private String pwd;
	private Language language;
	private ArrayList<DBConnection> connections;

	public User(String name, String firstName, String lastName, String mail, String pwd, Authorization authorization, Language language, ArrayList<DBConnection> connections){
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
		if(authorization != null){
			this.authorization = authorization;
		} else{
			this.authorization = Authorization.DEFAULT;
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
		return this.authorization;
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
		//ret = this.connections.toArray(); //Problème de typage
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
	*/
	public void editDBConnection(String nameCo){

	}

	/**
	* Display informations
	* @return the informations 
	*/
	public String toString(){
		return null;
	}
}