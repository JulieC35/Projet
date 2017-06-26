/**
 * A MySQL database table attribute.<br>
 * A Column object have several attributes meant to describe it in a SQL way : a name, a type and also several
 * boolean indicators : primary, unique, notNull and autoIncrement<br>
 * Besides accessors, a Column can be converted into an SQL attribute declaration<br><br>
 */

package library.entities;

import lang.*;

public class Column{

	private String name, type;
	private boolean primary, unique, notNull, autoIncrement;
	
	/**
	 * The constructor of the class
	 * @param name the name of the column
	 * @param type the type of the column
	 * @param primary true if this is a primary key
	 * @param unique true if this attribute is unique
	 * @param notNull true if this attribute should not be null
	 * @param autoIncrement true if the attribute has auto increment
	 */
	public Column(String name, String type, boolean primary, boolean unique, boolean notNull, boolean autoIncrement){
		this.name = ( name != null ) ? name : "n/a";
		this.type = ( type != null ) ? type : "n/a";
		this.primary = primary;
		this.unique = unique;
		this.notNull = notNull;
		this.autoIncrement = autoIncrement;
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
		this.autoIncrement = false;
	}

	/**
	 * @return the name of the column
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * @return the type of the column
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
	 * @return true if the column has auto increment set on
	 */
	public boolean hasAutoIncrement(){
		return this.autoIncrement;
	}

	/**
	 * @param name the new name of the column. Should not be null or empty
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
	 * @param type the new type of the column. Should not be null or empty
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
	 * @param primary true if the column is a primary key
	 */
	public void setPrimary(boolean primary){
		this.primary = primary;
	}
	
	/**
	 * @param unique true if the column is unique
	 */
	public void setUnique(boolean unique){
		this.unique = unique;
	}
	
	/**
	 * @param notNull true if the column is not null
	 */
	public void setNotNull(boolean notNull){
		this.notNull = notNull;
	}

	/**
	 * @param autoI true if the column has auto increment
	 */
	public void setAutoIncrement(boolean autoI){
		this.autoIncrement = autoI;
	}

	/**
	 * Returns the column as a MySQL declaration.<br>
	 * Intended to be used in a table-creation context.<br><br>
	 * Example :<br>
	 * Column col = new Column("id", "INT(11)", true, false, true, true);<br>
	 * System.out.println(col.toSQL());<br><br>
	 * 
	 * Result :<br>
	 * `id` INT(11) NOT NULL AUTOINCREMENT, <br>
	 * PRIMARY KEY (`id`)
	 * @return The SQL declaration of the column
	 */
	public String toSQL(){
		StringBuilder sb = new StringBuilder();
		sb.append("`" + this.name + "` " + this.type);
		if ( this.primary ){
			sb.append(" NOT NULL");
			if ( this.autoIncrement )
				sb.append(" AUTO_INCREMENT");
			sb.append(",\nPRIMARY KEY (`" + this.name + "`)");
		}
		else {
			if ( this.unique && this.notNull) {
				sb.append(" NOT NULL");
				sb.append(",\nUNIQUE (`" + this.name + "`)");
				// UNIQUE `uqUserEmail` (`email`)
			}
			else if ( this.unique )
				sb.append(",\nUNIQUE (`" + this.name + "`)");
			else if ( this.notNull ) 
				sb.append(" NOT NULL");
		}

		return sb.toString();
	}

}