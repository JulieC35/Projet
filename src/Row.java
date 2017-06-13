import java.util.*;

/**
* Class Row
*/
public class Row{
	private ArrayList<Column> schema;
	private HashMap<String, String> values;

	/**
	* The constructor of the method
	* @param schema the list of column
	*/
	public Row(ArrayList<Column> schema){
		if(schema!=null){
			this.schema=schema;
		}
	}

	/**
	* Get the value
	* @param col the column
	*/
	public String getValue(String col){
		return col;
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
	public HashMap<String, String> getValues(){
		return this.values;
	}

	/**
	* Set the values of the list
	* @param vals the hashmap 
	*/
	public void setValues(HashMap<String, String> vals){
		this.values=vals;
	}

	/**
	* Display informations
	*/
	public String toString(){
		String s="Cette ligne a "+schema.size()+"colonnes";
		return s;
	}

}