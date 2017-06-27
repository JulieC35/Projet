package test;

import library.entities.*;
import org.junit.*; 
import static org.junit.Assert.*;

public class TestQueryResult{

	private QueryResult queryOk;
	private QueryResult queryNnOk;

	/**
	 * Initialise les objets avant chaque tests
	 */
	@Before()
	public void beforeTest(){
		queryOk = new QueryResult();
		queryNnOk = new QueryResult(null);
	}

	/**
	 * test de l'initialisation des valeurs du query
	 */
	@Test()
	public void testResult(){
		Assert.assertNotNull(new QueryResult());
		Assert.assertNotNull(queryNnOk);
		Assert.assertEquals("", queryNnOk.getMessage());
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