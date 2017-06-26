package test;

import library.entities.*;
import org.junit.*; 
import static org.junit.Assert.*;

public class TestColumn{

	private Column colOk;
	private Column colNnOk;

	/*
	 * Initialise les objets avant chaque tests
	 */
	@Before()
	public void beforeTest(){
		colOk = new Column();
		colNnOk = new Column();
	}

	/**
	 * test la primarité d'une colonne
	 */
	@Test()
	public void testIsPrimary(){
		colOk.setPrimary(true);
		Assert.assertTrue(colOk.isPrimary());
		Assert.assertFalse(colNnOk.isPrimary());
	}

	/**
	 * test l'unicité d'une colonne
	 */
	@Test()
	public void testIsUnique(){
		colOk.setUnique(true);
		Assert.assertTrue(colOk.isUnique());
		Assert.assertFalse(colNnOk.isUnique());
	}

	/**
	 * test de la condition not null d'une colonne
	 */
	@Test()
	public void testIsNotNull(){
		colOk.setNotNull(true);
		Assert.assertTrue(colOk.isNotNull());
		Assert.assertFalse(colNnOk.isNotNull());
	}

	/**
	 * test de l'auto incrémentation d'une colonne
	 */
	@Test()
	public void testHasAutoInc(){
		colOk.setAutoIncrement(true);
		Assert.assertTrue(colOk.hasAutoIncrement());
		Assert.assertFalse(colNnOk.hasAutoIncrement());
	}

	/**
	 *
	 */
	@Test()
	public void testToSQL(){
		colOk = new Column("colonne ok", "INT", true, true, true, true); //initialisation précise des colonnes afin de savoir avec quoi comparer
		colNnOk = new Column("colonne non ok", "INT", false, false, false, false); //initialisation précise des colonnes afin de savoir avec quoi comparer
		Assert.assertEquals("`colonne ok` INT NOT NULL AUTO_INCREMENT,\nPRIMARY KEY (`colonne ok`)", colOk.toSQL());
		Assert.assertEquals("`colonne non ok` INT", colNnOk.toSQL());
	}

	/*
	 * Nettoie les objets après chaque tests
	 */
	@After()
	public void clearTest(){
		colOk = null;
		colNnOk = null;
	}


}