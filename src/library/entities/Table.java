package library.entities;

import java.util.*;

public class Table{
	private String name;
	private ArrayList<Column> schema;
	private ArrayList<Row> rows;

	/**
	 * The constructor
	 * @param name the name of the table
	 * @param schema all the column of the table
	 */
	public Table(String name, Column[] schema){
		if(name != null){
			this.name = name;
		}
		if(schema != null){
			this.schema = new ArrayList<Column>();
			for(int i = 0; i < schema.length; i++){
				this.schema.add(schema[i]);
			}
			this.rows = new ArrayList<Row>();
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
	public void setName(String name){
		if(name != null){
			this.name = name;
		}
	}

	/**
	 * Getter of the schema 
	 * @return a table of all the column
	 */
	public Column[] getSchema(){
		Column[] ret;
		ret = (Column[])schema.toArray();
		return ret;
	}

	/**
	 * Getter of the rows
	 * @return all the rows in the table
	 */
	public Row[] getRows(){
		Row[] ret;
		ret = (Row[])rows.toArray();
		return ret;
	}

	/**
	 * Add a row in the table
	 * @param row the row to add
	 */
	public void addRow(Row r){
		if(r != null){
			this.rows.add(r);
		}
	}

	/**
	 * Delete a row from the table
	 * @param id the id of the row to delete
	 */
	public void deleteRow(int id){
		if((id >= 0) && (id < this.rows.size())){
			Row r = this.rows.remove(id);
		}
	}

	/**
	 * Clear the table
	 */
	public void empty(){
		this.rows.clear();
	}

	/**
	 * Print a description of the object
	 * @return the description
	 */
	public String toString(){
		String s = "Table";
		return s;
	}



}