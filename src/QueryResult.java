import java.util.*;

public class QueryResult{
	private ArrayList<Column> schema;
	private ArrayList<Row> rows;

	public QueryResult(){

	}

	public Row[] getRows(){
		Row[] ret;
		ret = (Row[])this.rows.toArray();
		return ret;
	}

	public String toString(){
		String s = "";
		return s;
	}
}	
