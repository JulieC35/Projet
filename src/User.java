public class User{
	private String name;
	private Authorization authorization;
	private String fName;
	private String lName;
	private String email;
	private String pwd;
	

	/**
	*
	*/
	public Authorization getAuthLevel(){
		return this.authorization;
	}

	/**
	*
	* @return name the name of the user
	*/
	public String getName(){
		return this.name;
	}

	/**
	*
	* @param name the name of the user
	*/
	public void setName(String name){
		this.name=name;
	}

	/**
	*
	* @return fName the first name of the user
	*/
	public String getFirstName(){
		return this.fName;
	}

	/**
	*
	* @param fName the first name of the user
	*/
	public void setFirstName(String fName){
		this.fName=fName;
	}

	/**
	*
	* @return lName the last name of the user
	*/
	public String getLastName(){
		return this.lName;
	}

	/**
	*
	* @param lName the last name of the user
	*/
	public void setLastName(String lName){
		this.lName=lName;
	}

	/**
	*
	* @return email the email of the user
	*/
	public String getEmail(){
		return this.email;
	}

	/**
	*
	* @param email the email of the user
	*/
	public void setEmail(String email){
		this.email=email;
	}

	/**
	*
	* @param pwd the password of the user
	*/
	public void setPasswrd(String pwd){
		this.pwd=pwd;
	}

	/**
	*
	*
	*/
	public DBConnection[] getDBConnections(){
		return null;
	}

	/**
	*
	*/
	public void addConnection(){

	}

	/**
	*
	* @return language 
	*/
	public Language getLanguage(){
		return null;
	}

	/**
	*
	* @param lng the language
	*/
	public void setLanguage(Language lng){

	}

	/**
	*
	* @param name the name of the connection
	*/
	public void removeDBConnection(String name){

	}

	/**
	*
	* @param name the name of the connection
	*/
	public void editDBConnection(String name){

	}

	/**
	* 
	* @return the informations 
	*/
	public String toString(){
		return null;
	}
}