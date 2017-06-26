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
		userOk = new User("julie", "Julie", "Chapdelaine", "juliechapdelaine@live.fr", "", Authorization.DEFAULT, null, null);
		userNnOk = new User("celie", "Celie", "Rault", "celie.rault@gmail.com", "", Authorization.DEFAULT, null, null);
	}

	/**
	 * test de la méthode testConnectivity de la classe DBConnection
	 */
	@Test()
	public void testUser(){
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