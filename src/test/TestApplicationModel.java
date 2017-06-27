package test;

import org.junit.*; 
import static org.junit.Assert.*;
import library.*;
import library.exceptions.*;
import library.entities.*;
import library.managers.*;
import lang.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class TestApplicationModel{

	private ApplicationModel app1;
	private User userOk;
	private User userNnOk;
	private DBConnection dbCo;

	/**
	 * Initialise les objets avant chaque tests
	 */
	@Before()
	public void beforeTest() throws Exception{
		app1 = new ApplicationModel();
		userOk = new User("root", "Administrator", "", "root@localhost", "", Authorization.SUPERADMIN, null, null);
		userNnOk = new User("test", "prenomTest", "nomTest", "test@localhost", "", Authorization.DEFAULT, null, null);
		dbCo = new DBConnection("testOk", "sql11182120", "TuMf9tYKN5", "sql11.freemysqlhosting.net", "sql11182120");

	}

	/**
	 *
	 */
	@Test()
	public void testEditConfigurationRule(){
		String code = "test-Edit-Config";
		String value = "test de l'editeur de configuration";
		app1.editConfigurationRule(code, value);
		Assert.assertTrue(app1.getConfigurationRule(code).equals(value));
	}

	/**
	 * Test de la méthode login de la classe Application
	 */
	@Test()
	public void testLogin(){
		Assert.assertTrue(app1.login(userOk.getUsername(), ""));
		Assert.assertFalse(app1.login(userNnOk.getUsername(), ""));
	}

	/**
	 *
	 */
	@Test()
	public void testLogout(){
		app1.logout();
		Assert.assertNull(app1.getUser());
	}

	/**
	 *
	 */
	@Test()
	public void testConnect(){
		app1.setConnectionProfile(dbCo);
		//app1.connect();
		//assertNotNull(app1.getConnection());
	}

	/**
	 *
	 */
	@Test()
	public void testDisconnect(){
		app1.disconnect();
		assertNull(app1.getConnectionProfile());
	}

	/**
	 * Nettoie les objets après chaque tests
	 */
	@After()
	public void clearTest(){
		userOk = null;
		userNnOk = null;
	}

}