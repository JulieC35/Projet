/**
 * The result of a query on the database
 * Stores the rows of the JDBC ResultSet and a possible message.<br>
 * Two ArrayList objects are created : one for the scheme (the list of attributes) and 
 * another one for the rows of the result themselves
 */

package library.entities;

import java.util.*;
import java.sql.*;
import lang.*;

public class QueryResult{
	private ArrayList<Column> scheme;
	private ArrayList<Row> rows;
	private String message;

	/**
	 * The constructor of the class.<br>
	 * It recieves a ResultSet and fills the two ArrayList objects with the MetaData for the scheme and the ResultSet itself for the rows.
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
				this.scheme.add(new Column(metaData.getColumnName(i), metaData.getColumnTypeName(i), false, false, false, false));
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
	 * The constructor of the class
	 */
	public QueryResult(){
		this.message = "";
		this.scheme = new ArrayList<Column>();
		this.rows = new ArrayList<Row>();
	}

	/**
	 * @param message The message of this QueryResult
	 */
	public void setMessage(String message){
		this.message = ( message != null ) ? message : "";
	}

	/**
	 * @return The message of this QueryResult
	 */
	public String getMessage(){
		return this.message;
	}

	/**
	 * @return The rows of the QueryResult
	 */
	public ArrayList<Row> getRows(){
		return this.rows;
	}

	/** 
	 * @return the scheme of the QueryResult
	 */
	public ArrayList<Column> getScheme(){
		return this.scheme;
	}
}	
