import org.junit.*; 
import static org.junit.Assert.*;

public class testDBConnection{

	private DBConnection co1;
	private DBConnection co2;

	/*
	 * Initialise les objets avant chaque tests
	 */
	@Before()
	protected void beforeTest(){
		co1 = new DBConnection("Celie", "e16", "1000", "string", 42);
		co2 = new DBConnection("Marc", "e17", "lol", "host", 1000);
	}

	


	/*
	 * test de la méthode testConnectivity de la classe DBConnection
	 */
	@Test()
	public void testTestConnectivity(){
		Assert.assertTrue(co1.testConnectivity());
		Assert.assertFalse(co2.testConnectivity());
	}

	/*
	 * Nettoie les objets après chaque tests
	 */
	@After()
	protected void clearTest(){
		co1 = null;
		co2 = null;
	}


}