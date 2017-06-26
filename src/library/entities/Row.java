/**
 * A QueryResult row.<br>
 * Two ArrayList objects are used : the first one stores the MySQL attributes, and the other the associated values.
 */
package library.entities;

import java.util.*;
import lang.*;

public class Row{
	private ArrayList<String> keys;
	private ArrayList<String> values;

	/**
	* The constructor of the class
	*/
	public Row(){
		this.keys = new ArrayList<String>();
		this.values = new ArrayList<String>();
	}

	/**
	* Allows to retrieve a value based on the associated attribute.
	* @param attribute the column
	* @return The value if found, or null
	*/
	public String getValue(String attribute){
		String ret = null;
		if ( attribute != null ) {
			if ( this.keys.indexOf(attribute) != -1 )
				ret = this.values.get(this.keys.indexOf(attribute));
		}
		return ret;
	}

	/**
	* Allows to modify a value of the row based on its associated attribute.<br>
	* If the attribute doesn't exist in the row, it is created.
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
	* @return The values of the row as a String, separated with tabulations
	*/
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < this.keys.size() ; i++){
			sb.append("\t" + this.values.get(i));
		}
		return sb.toString();
	}

}