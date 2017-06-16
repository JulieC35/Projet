package model.entities;

import java.util.*;
import lang.*;

public class QueryResult{
	private ArrayList<Column> schema;
	private ArrayList<Row> rows;

	/**
	 * The constructor
	 */
	public QueryResult(){

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
