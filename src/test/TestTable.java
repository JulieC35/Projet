package test;

import library.entities.*;
import org.junit.*; 
import static org.junit.Assert.*;

public class TestTable{

	private Table tableOk;
	private Table tableNnOk;

	/*
	 * Initialise les objets avant chaque tests
	 */
	@Before()
	public void beforeTest(){
		tableOk = new Table();
		tableNnOk = new Table();
	}

	/*
	 * test de la méthode testConnectivity de la classe DBConnection
	 */
	@Test()
	public void testAddColumn(){
		Column colonne = new Column("colonne test", "int", true, true, true, true);
		Column colonneNull = null;
		Assert.assertTrue(tableOk.addColumn(colonne));
		Assert.assertFalse(tableNnOk.addColumn(colonneNull));
	}

	/**
	 *
	 */
	@Test()
	public void testHasPrimary(){
		Column colonne = new Column("colonne test", "int", true, true, true, true);
		Column colonneF = new Column("colonne test", "int", false, false, false, false);
		tableOk.addColumn(colonne);
		tableNnOk.addColumn(colonneF);
		Assert.assertTrue(tableOk.hasPrimary());
		Assert.assertFalse(tableNnOk.hasPrimary());
	}

	/*
	 * Nettoie les objets après chaque tests
	 */
	@After()
	public void clearTest(){
		tableOk = null;
		tableNnOk = null;
	}


}