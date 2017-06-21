package model.entities;

import java.util.*;
import lang.*;

public class QueryResult{
	private ArrayList<Column> schema;
	private ArrayList<Row> rows;
	private String message;

	/**
	 * The constructor
	 */
	public QueryResult(){
		this.message = null;
	}

	public Row[] getRows(){
		Row[] ret;
		ret = (Row[])this.rows.toArray();
		return ret;
	}

	public String toString(){
		String s = "QueryResult";
		return s;
	}
}	
