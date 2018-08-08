package Model;

import junit.framework.TestCase;
import org.junit.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class ModelJUnit extends TestCase {
	public ModelJUnit(String testName) {
		super(testName);
	}
	
	@Test
	public void testSetEmptyDB() {
		@SuppressWarnings("unused")
		StoreDB testDB = new StoreDB("Empty");
		assertFalse(StoreDB.status);
	}
	
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
	
	@Test
	public void testFailSetFilledDB() {
		@SuppressWarnings("unused")
		StoreDB testDB = new StoreDB("Empty");
		assertFalse(StoreDB.status);
	}
}