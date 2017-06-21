package model.entities;

import lang.*;

public class Column{

	private String name, type;
	private boolean primary, unique, notNull;
	
	/**
	 * The constructor of the class
	 * @param name the name of the column
	 * @param type the type of the column
	 */
	public Column(String name, String type, boolean primary, boolean unique, boolean notNull){
		this.name = ( name != null ) ? name : "n/a";
		this.type = ( type != null ) ? type : "n/a";
		this.primary = primary;
		this.unique = unique;
		this.notNull = notNull;
	}

	/**
	 * The constructor of the class
	 */
	public Column(){
		this.name = "n/a";
		this.type = "n/a";
		this.primary = false;
		this.unique = false;
		this.notNull = false;
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
	 * @return true if the column is a primary key
	 */
	public boolean isPrimary(){
		return this.primary;
	}

	/**
	 * @return true if the column is unique
	 */
	public boolean isUnique(){
		return this.unique;
	}

	/**
	 * @return true if the column is not null
	 */
	public boolean isNotNull(){
		return this.notNull;
	}

	/**
	 * Setter of name
	 * @param name the new name of the column
	 * @return true if the name is valid
	 */
	public boolean setName(String name){
		boolean ret = false;
		if(name != null && !name.equals("")){
			this.name = name;
			ret = true;
		}

		return ret;
	}

	/**
	 * Setter of type
	 * @param type the new type of the column
	 * @return true is the type is valid
	 */
	public boolean setType(String type){
		boolean ret = false;
		if(type != null && !type.equals("")){
			this.type = type;
			ret = true;
		}

		return ret;
	}
	
	/**
	 * @param primary Is the column a primary key ?
	 */
	public void setPrimary(boolean primary){
		this.primary = primary;
	}
	
	/**
	 * @param unique Is the column unique ?
	 */
	public void setUnique(boolean unique){
		this.unique = unique;
	}
	
	/**
	 * @param notNull Is the column not null ?
	 */
	public void setNotNull(boolean notNull){
		this.notNull = notNull;
	}

	/**
	 * Show a description of the column
	 * @return the description
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("`" + this.name + "` " + this.type);
		return sb.toString();
	}

}