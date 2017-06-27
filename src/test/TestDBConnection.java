package test;

import library.entities.*;
import org.junit.*; 
import static org.junit.Assert.*;

public class TestDBConnection{

	private DBConnection coOk;
	private DBConnection coNnOk;

	/*
	 * Initialise les objets avant chaque tests
	 */
	@Before()
	public void beforeTest(){
		coOk = new DBConnection("testOk", "sql11182120", "TuMf9tYKN5", "sql11.freemysqlhosting.net", "sql11182120");
		coNnOk = new DBConnection("testNnOk", "e17", "lol", "host", "bdd2");
	}

	/*
	 * test de la méthode testConnectivity de la classe DBConnection
	 */
	@Test()
	public void testTestConnectivity(){
		Assert.assertTrue(coOk.testConnectivity());
		Assert.assertFalse(coNnOk.testConnectivity());
	}

	/*
	 * Nettoie les objets après chaque tests
	 */
	@After()
	public void clearTest(){
		coOk = null;
		coNnOk = null;
	}


}