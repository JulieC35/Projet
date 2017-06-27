package test;

import library.entities.*;
import library.*;
import org.junit.*; 
import static org.junit.Assert.*;

public class TestQueryBuilder{

	private QueryBuilder queryOk;
	private QueryBuilder queryNnOk;
	private Table tableOk;

	/**
	 * Initialise les objets avant chaque tests
	 */
	@Before()
	public void beforeTest(){
		queryOk = new QueryBuilder();
		queryNnOk = new QueryBuilder();
		tableOk = new Table();
		tableOk.setName("table");
	}

	/**
	 * test de l'initialisation des valeurs du query
	 */
	@Test()
	public void testSelectAll(){
		queryOk.selectAllFromTable(tableOk);
		Assert.assertNotNull(queryOk.getQuery());
		queryOk.selectAllFromTable("table");
		Assert.assertNotNull(queryOk.getQuery());
		queryNnOk.selectAllFromTable(new String());
		Assert.assertNotNull(queryNnOk.getQuery());
	}

	/**
	 *
	 */
	@Test()
	public void testCreateTable(){
		queryOk.createTable(tableOk);
		Assert.assertNotNull(queryOk.getQuery());
		queryNnOk.createTable(null);
		Assert.assertNotNull(queryNnOk.getQuery());
	}

	/**
	 *
	 */
	@Test()
	public void testDropTable(){
		queryOk.dropTable("table");
		Assert.assertNotNull(queryOk.getQuery());
		queryNnOk.dropTable(null);
		Assert.assertNotNull(queryNnOk.getQuery());
	}

	/**
	 * Nettoie les objets après chaque tests
	 */
	@After()
	public void clearTest(){
		queryOk = null;
		queryNnOk = null;
	}


}