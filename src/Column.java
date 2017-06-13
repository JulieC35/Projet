public class Column{

	private String name;
	private String type;
	
	/**
	 * The constructor of the class
	 * @param name the name of the column
	 * @param type the type of the column
	 */
	public Column(String name, String type){
		if(name != null){
			this.name = name;
		}
		if(type != null){
			this.type = type;
		}
	}

	/**
	 * Getter of the name
	 * @return the current name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Getter of the type
	 * @return the current type
	 */
	public String getType(){
		return this.type;
	}

	/**
	 * Setter of name
	 * @param name the new name of the column
	 */
	public void setName(String name){
		if(name != null){
			this.name = name;
		}
	}

	/**
	 * Setter of type
	 * @param type the new type of the column
	 */
	public void setType(String type){
		if(type != null){
			this.type = type;
		}
	}

	/**
	 * test if the column is a primary key
	 * @return true if it is
	 */
	public boolean isPrimaryKey(){
		boolean ret = false;
		return ret;
	}

	/**
	 * test if the column is nullable
	 * @return true if it is
	 */
	public boolean isNullable(){
		boolean ret = false;
		return ret;
	}

	/**
	 * test if the column is unique (contains only unique attributs)
	 * @return true if it is
	 */
	public boolean isUnique(){
		boolean ret = false;
		return ret;
	}

	/**
	 * Show a description of the column
	 * @return the description
	 */
	public String toString(){
		String s = "";
		return s;
	}

}