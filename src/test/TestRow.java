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

	}

	/**
	 * test de la méthode testConnectivity de la classe DBConnection
	 */
	@Test()
	public void test(){
		
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