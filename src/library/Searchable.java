package library;

import library.entities.*;

public interface Searchable{
	public QueryResult search(String keywords);
	public QueryResult[] search(String[] keywords);
}