package test;

import library.entities.*;
import library.*;
import org.junit.*; 
import static org.junit.Assert.*;

public class TestUser{

	private User userOk;
	private User userNnOk;

	/**
	 * Initialise les objets avant chaque tests
	 */
	@Before()
	public void beforeTest() throws Exception{
		//username, firstName, lastName, emailAddress, pwd, authorization, language, ArrayList<DBConnection> connections
		userOk = new User("julie", "Julie", "Chapdelaine", "juliechapdelaine@live.fr", "pass", Authorization.DEFAULT, null, null);
		userNnOk = new User("celie", "Celie", "Rault", "celie.rault@gmail.com", "", Authorization.DEFAULT, null, null);
	}

	/**
	 * test de la méthode testConnectivity de la classe DBConnection
	 */
	@Test()
	public void testAddCo1(){
		//"testOk", "sql11182120", "TuMf9tYKN5", "sql11.freemysqlhosting.net", "sql11182120"
		Assert.assertTrue(userOk.addDBConnection("nameTest", "sql11182120", "TuMf9tYKN5", "sql11.freemysqlhosting.net", "sql11182120"));
	}

	/**
	 *
	 */
	@Test()
	public void testAddCo2(){
		Assert.assertTrue(userOk.addDBConnection(new DBConnection("testOk", "sql11182120", "TuMf9tYKN5", "sql11.freemysqlhosting.net", "sql11182120")));
	}

	/**
	 *
	 */
	@Test()
	public void testGetDBCo(){
		userOk.addDBConnection(new DBConnection("testOk", "sql11182120", "TuMf9tYKN5", "sql11.freemysqlhosting.net", "sql11182120"));
		Assert.assertNotNull(userOk.getDBConnection(0));
		Assert.assertNull(userNnOk.getDBConnection(11));
	}

	/**
	 *
	 */
	@Test()
	public void testRemoveDBCo1(){
		userOk.addDBConnection(new DBConnection("testOk", "sql11182120", "TuMf9tYKN5", "sql11.freemysqlhosting.net", "sql11182120"));
		Assert.assertTrue(userOk.removeDBConnection(0));
		Assert.assertFalse(userNnOk.removeDBConnection(11));
	}

	/**
	 *
	 */
	@Test()
	public void testRemoveDBCo2(){
		DBConnection dbCo = new DBConnection("testOk", "sql11182120", "TuMf9tYKN5", "sql11.freemysqlhosting.net", "sql11182120");
		userOk.addDBConnection(dbCo);
		Assert.assertTrue(userOk.removeDBConnection(dbCo));
		Assert.assertFalse(userNnOk.removeDBConnection(dbCo));
	}

	/**
	 *
	 */
	@Test()
	public void testVerifyPassword(){
		Assert.assertTrue(userOk.verifyPassword("pass"));
		Assert.assertFalse(userNnOk.verifyPassword(null));
	}

	/*
	 * Nettoie les objets après chaque tests
	 */
	@After()
	public void clearTest(){
		userOk = null;
		userNnOk = null;
	}


}