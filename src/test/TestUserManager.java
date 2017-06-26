package test;

import library.entities.*;
import library.*;
import lang.*;
import library.managers.*;
import org.junit.*; 
import static org.junit.Assert.*;

public class TestUserManager{

	private UserManager userman;
	private User userRoot;
	private User userOk;
	private User userNnOk;

	/**
	 * Initialise les objets avant chaque tests
	 */
	@Before()
	public void beforeTest() throws Exception{
		//username, firstName, lastName, emailAddress, pwd, authorization, language, ArrayList<DBConnection> connections
		userRoot = new User("root", "Administrator", "", "root@localhost", "", Authorization.SUPERADMIN, null, null);
		userOk = new User("julie", "Julie", "Chapdelaine", "chapdelainejulie0@gmail.com", "", null, null, null);
		userNnOk = new User("celie", "Celie", "Rault", "celie.rault@gmail.com", "", Authorization.DEFAULT, null, null);
		userman = new UserManager();
	}

	/**
	 * test de la méthode testConnectivity de la classe DBConnection
	 */
	@Test()
	public void testAuthenticate(){
		Assert.assertEquals("root", userman.authenticate("root", "").getUsername());
		Assert.assertNull(userman.authenticate("null", ""));
	}

	/**
	 *
	 */
	@Test()
	public void testAddUser(){
		userman.addUser(userOk);
		Assert.assertNotNull(userman.authenticate("julie", ""));
		Assert.assertNull(userman.authenticate("null", ""));
	}

	/**
	 *
	 */
	@Test()
	public void testRemoveUser(){
		userman.addUser(userOk);
		Assert.assertTrue(userman.removeUser(userOk));
		Assert.assertFalse(userman.removeUser(null));
	}

	/**
	 *
	 */
	@Test()
	public void testCheckUsernameAvailability(){
		Assert.assertFalse(userman.checkUsernameAvailability("julie"));
		Assert.assertTrue(userman.checkUsernameAvailability("test"));
	}

	/**
	 * Nettoie les objets après chaque tests
	 */
	@After()
	public void clearTest(){
		userRoot = null;
		userOk = null;
		userNnOk = null;
	}


}