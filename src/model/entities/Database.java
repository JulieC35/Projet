package model.entities;

import java.util.*;
import lang.*;

/**
* Class Database
*/
public class Database {
	

	/**
	* The constructor of the method
	*/
	public Database(){

	}

	/**
	* Add a table to the data base
	* @param name the name of the table
	* @param schema the list of column
	*/
	public void addTable(String name, ArrayList<Column> schema){

	}

	/**
	* Delete a table of the data base
	* @param name the name of the table
	*/
	public void deleteTable(String name){

	}

	/**
	* Get the table
	* @param name the name of the table
	* @return table the table
	*/
	public Table getTable(String name){
		return null;
	}

	/**
	* Get the list of tables
	* @return tables the list
	*/
	public ArrayList<Table> getTables(){
		return null;
	}

	/**
	* Display informations
	*/
	public String toString(){
		return null;
	}
}