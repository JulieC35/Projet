import java.util.*;

public class Table{
	private String name;
	private ArrayList<Column> schema;
	private ArrayList<Row> rows;

	public Table(String name, Column[] schema){
		if(name != null){
			this.name = name;
		}
		if(schema != null){
			this.schema = new ArrayList<Column>();
			for(int i = 0; i < schema.length; i++){
				this.schema.add(schema[i]);
			}
			this.rows = new ArrayList<Row>();
		}
	}


}