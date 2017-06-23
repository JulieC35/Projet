/**
 * 
 */

package model.entities;

import java.util.*;
import java.sql.*;
import lang.*;

public class QueryResult{
	private ArrayList<Column> scheme;
	private ArrayList<Row> rows;
	private String message;

	/**
	 * The constructor
	 * @param result The SQL ResultSet
	 */
	public QueryResult(ResultSet result){
		this.message = "";
		this.scheme = new ArrayList<Column>();
		this.rows = new ArrayList<Row>();

		Row tempRow = null;

		try {
			// Creating the scheme
			ResultSetMetaData metaData = result.getMetaData();
			for ( int i = 1 ; i <= metaData.getColumnCount() ; i++ ) {
				this.scheme.add(new Column(metaData.getColumnName(i), metaData.getColumnTypeName(i), false, false, false));
			}

			// inserting entries
			while ( result.next() ){
				tempRow = new Row();
				for (int i = 1 ; i <= metaData.getColumnCount() ; i++){
					tempRow.setValue(metaData.getColumnName(i), result.getString(i));
				}
				this.rows.add(tempRow);
			}
		} catch (SQLException ex){
			this.message = ex.getMessage();
		}
	}

	/**
	 * The constructor
	 * @param result The SQL ResultSet
	 */
	public QueryResult(){
		this.message = "";
		this.scheme = new ArrayList<Column>();
		this.rows = new ArrayList<Row>();
	}

	/**
	 * Allwos to set the message
	 * @param message The message of this query result
	 */
	public void setMessage(String message){
		this.message = ( message != null ) ? message : "";
	}

	/**
	 * Allows to retrieve the message
	 * @return The message of this query result
	 */
	public String getMessage(){
		return this.message;
	}

	/**
	 * @return The rows of the query result
	 */
	public ArrayList<Row> getRows(){
		return this.rows;
	}

	/** 
	 * Allows to retrive the scheme of the query result
	 * @return the scheme
	 */
	public ArrayList<Column> getScheme(){
		return this.scheme;
	}
}	
