package Model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.*;

/**
 * Tests valid and invalid initialization conditions of the database.
 */
public class ModelJUnit extends TestCase {
	/**
	 * Initializes model JUnit test.
	 * 
	 * @param testName		Name of the test, for clarity during runtime
	 */
	public ModelJUnit(String testName) { super(testName); }
	
	/**
	 * Tests the successful emptying of a database by ensuring that the status boolean
	 * variable of the StoreDB object is false when an empty database is initialized.
	 */
	@Test
	public void testSetEmptyDB() {
		@SuppressWarnings("unused")
		StoreDB testDB = new StoreDB("Empty");
		
		assertFalse(StoreDB.status);
	}
	
	/**
	 * Tests the unsuccessful emptying of a database by ensuring that the status boolean
	 * variable of the StoreDB object is true when a full database is initialized.
	 */
	@Test
	public void testFailSetEmptyDB() {
		List<String> test = Arrays.asList("1", "2", "3");
		List<List<String>> ls2d = new ArrayList<List<String>>();
		List<String> x = new ArrayList<String>();
		x.add("Hello");
		x.add("world!");
		ls2d.add(x);
		@SuppressWarnings("unused")
		StoreDB testDB = new StoreDB(test, ls2d, "Full");
		
		assertTrue(StoreDB.status);
	}
	
	/**
	 * Tests the successful filling of a database by ensuring that the status boolean
	 * variable of the StoreDB object is true when a full database is initialized.
	 */
	@Test
	public void testSetFilledDB() {
		List<String> test = Arrays.asList("1", "2", "3");
		List<List<String>> ls2d = new ArrayList<List<String>>();
		List<String> x = new ArrayList<String>();
		x.add("Hello");
		x.add("world!");
		ls2d.add(x);		
		@SuppressWarnings("unused")
		StoreDB testDB = new StoreDB(test, ls2d, "Full");
		
		assertTrue(StoreDB.status);
	}
	
	/**
	 * Tests the unsuccessful filling of a database by ensuring that the status boolean
	 * variable of the StoreDB object is false when an empty database is initialized.
	 */
	@Test
	public void testFailSetFilledDB() {
		@SuppressWarnings("unused")
		StoreDB testDB = new StoreDB("Empty");
		
		assertFalse(StoreDB.status);
	}
}