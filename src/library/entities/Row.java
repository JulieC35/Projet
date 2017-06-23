package library.entities;

import java.util.*;
import lang.*;

/**
* Class Row
*/
public class Row{
	private ArrayList<String> keys;
	private ArrayList<String> values;

	/**
	* The constructor of the method
	* @param schema the list of column
	*/
	public Row(){
		this.keys = new ArrayList<String>();
		this.values = new ArrayList<String>();
	}

	/**
	* Get the value
	* @param col the column
	*/
	public String getValue(String col){
		String ret = "n/a";
		if ( col != null ) {
			if ( this.keys.indexOf(col) != -1 )
				ret = this.values.get(this.keys.indexOf(col));
		}
		return ret;
	}

	/**
	* Set the value
	* @param col the column
	* @param val the value
	*/
	public void setValue(String col, String val){
		if ( col != null && val != null ) {
			if ( this.keys.indexOf(col) == -1 )
				this.keys.add(col);

			this.values.add(this.keys.indexOf(col), val);
		}
	}

	/**
	* Display informations
	* @return the row as a string
	*/
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < this.keys.size() ; i++){
			sb.append("\t" + this.values.get(i));
		}
		return sb.toString();
	}

}