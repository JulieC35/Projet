package library.entities;

import java.util.*;

/**
* Class Row
*/
public class Row{
	private HashMap<Column, String> values;

	/**
	* The constructor of the method
	* @param schema the list of column
	*/
	public Row(ArrayList<Column> schema){
		values = new HashMap<Column, String>();
		if(schema!=null){
			for(Column col : schema){
				values.put(col, null);
			}
		}
	}

	/**
	* Get the value
	* @param col the column
	*/
	public String getValue(String col){
		String s = "null";
		Iterator iterator = this.values.entrySet().iterator();
		boolean trouve = false;
		while ((iterator.hasNext()) && (!trouve)) {
			Map.Entry entry = (Map.Entry) iterator.next();
			Column key = (Column)entry.getKey();
			String value = (String)entry.getValue();
			if(key.getName().equals(col)){
				trouve = true;
				s = value;
			}
		}
		return s;
	}

	/**
	* Set the value
	* @param col the column
	* @param val the value
	*/
	public void setValue(String col, String val){
		
	}

	/**
	* Get the values of the list
	* @return values the hashmap
	*/
	public HashMap<Column, String> getValues(){
		return this.values;
	}

	/**
	* Set the values of the list
	* @param vals the hashmap 
	*/
	public void setValues(HashMap<Column, String> vals){
		this.values=vals;
	}

	/**
	* Display informations
	*/
	public String toString(){
		String s="Cette ligne a "+values.size()+" colonnes";
		return s;
	}

}