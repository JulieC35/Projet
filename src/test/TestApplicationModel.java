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

	/*
	 * Initialise les objets avant chaque tests
	 */
	@Before()
	public void beforeTest() throws Exception{
		app1 = new ApplicationModel();
		userOk = new User("root", "Administrator", "", "root@localhost", "", Authorization.SUPERADMIN, null, null);
		userNnOk = new User("test", "prenomTest", "nomTest", "test@localhost", "", Authorization.DEFAULT, null, null);

	}

	/*
	 * Nettoie les objets après chaque tests
	 */
	@After()
	public void clearTest(){
		userOk = null;
		userNnOk = null;
	}


	/*
	 * Test de la méthode login de la classe Application
	 */
	@Test()
	public void testLogin(){
		Assert.assertTrue(app1.login(userOk.getUsername(), ""));
		Assert.assertFalse(app1.login(userNnOk.getUsername(), ""));
	}

	

	/*
	 * test de la méthode connect de la classe Application
	 */
	@Test()
	public void testConnect(){
	}

}