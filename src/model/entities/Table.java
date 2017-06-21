package model.entities;

import java.util.*;
import lang.*;

public class Table{
	private String name;
	private ArrayList<Column> scheme;

	/**
	 * The constructor
	 */
	public Table(){
		this.name = null;
		this.scheme = new ArrayList<Column>();
	}

	/**
	 * The constructor
	 * @param name the name of the table
	 * @param scheme all the column of the table
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
	 * The constructor
	 * @param name the name of the table
	 * @param scheme all the column of the table
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
	 * Getter of the name
	 * @return the name of the current table
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Setter of the name
	 * @param the new name of the table
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
	 * Getter of the scheme 
	 * @return a table of all the column
	 */
	public ArrayList<Column> getScheme(){
		return this.scheme;
	}

	/**
	 * Allows to add a column to the table scheme
	 * @param col The coloumn to add to the table scheme
	 */
	public void addColumn(Column col){
		if ( col != null ) 
			this.scheme.add(col);
	}

	/**
	 * Print a description of the object
	 * @return the description
	 */
	public String toString(){
		String s = "name: " + this.name;
		return s;
	}



}