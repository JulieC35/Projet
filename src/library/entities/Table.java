/**
 * A MySQL table. This class is mostly used to create tables
 */
package library.entities;

import java.util.*;
import lang.*;

public class Table{
	private String name;
	private ArrayList<Column> scheme;

	/**
	 * The constructor of the class
	 */
	public Table(){
		this.name = null;
		this.scheme = new ArrayList<Column>();
	}

	/**
	 * The constructor of the class
	 * @param name the name of the table
	 * @param scheme all the column of the table in an array
	 */
	public Table(String name, Column[] scheme){
		if(name != null){
			this.name = name;
		}
		if(scheme != null){
			this.scheme = new ArrayList<Column>();
			for(int i = 0; i < scheme.length; i++){
				this.scheme.add(scheme[i]);
			}
		}
	}
	
	/**
	 * The constructor of the class
	 * @param name the name of the table
	 * @param scheme all the column of the table in an ArrayList object
	 */
	public Table(String name, ArrayList<Column> scheme){
		if(name != null){
			this.name = name;
		}
		if(scheme != null){
			this.scheme = new ArrayList<Column>();
			for(int i = 0; i < scheme.size(); i++){
				this.scheme.add(scheme.get(i));
			}
		}
	}

	/**
	 * @return the name of the current table
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * @param name the new name of the table
	 * @return true if the setting succeeded
	 */
	public boolean setName(String name){
		boolean ret = false;
		if ( name != null && !name.equals("") && ( name.length() == name.replaceAll("\\s","").length() ) ) {
			this.name = name;
			ret = true;
		}

		return ret;
	}

	/**
	 * @return a table of all the column
	 */
	public ArrayList<Column> getScheme(){
		return this.scheme;
	}

	/**
	 * Allows to add a column to the table scheme
	 * @param col The coloumn to add to the table scheme
	 */
	public boolean addColumn(Column col){
		boolean ret = false;
		if ( col != null ){
			this.scheme.add(col);
			ret = true;
		}
		return ret;
	}

	/**
	 * @return true if the table has a primary key
	 */
	public boolean hasPrimary(){
		boolean ret = false;
		int i = 0;

		while ( i < this.scheme.size() && !ret ){
			if ( this.scheme.get(i).isPrimary() )
				ret = true;

			i++;
		}

		return ret;
	}

	/**
	 * @return The table as a String.
	 */
	public String toString(){
		String s = "name: " + this.name;
		return s;
	}



}