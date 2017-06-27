package test;

import library.entities.*;
import org.junit.*; 
import static org.junit.Assert.*;

public class TestRow{

	private Row rowOk;
	private Row rowNnOk;

	/**
	 * Initialise les objets avant chaque tests
	 */
	@Before()
	public void beforeTest(){
		rowOk = new Row();
		rowNnOk = new Row();
	}

	/**
	 * test de l'initialisation des valeurs du Row
	 */
	@Test()
	public void testSetValue(){
		rowOk.setValue("colonne test", "valeur test");
		Assert.assertTrue(rowOk.getValue("colonne test").equals("valeur test"));
	}

	/**
	 * test du getter d'une valeur
	 */
	@Test()
	public void testGetValue(){
		rowOk.setValue("colonne test", "valeur test");
		Assert.assertNotNull(rowOk.getValue("colonne test"));
		Assert.assertNull(rowNnOk.getValue("colonne nulle"));
	}

	/**
	 * Nettoie les objets après chaque tests
	 */
	@After()
	public void clearTest(){
		rowOk = null;
		rowNnOk = null;
	}


}