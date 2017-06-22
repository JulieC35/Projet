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
		this.message = null;
	}

	/**
	 * @return The rows of the query result
	 */
	public Row[] getRows(){
		Row[] ret;
		ret = (Row[])this.rows.toArray();
		return ret;
	}

	/**
	 * @return The QueryResult as a printable object
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Row element : this.rows){
			sb.append(element + "\n");
		}

		return sb.toString();
	}
}	
