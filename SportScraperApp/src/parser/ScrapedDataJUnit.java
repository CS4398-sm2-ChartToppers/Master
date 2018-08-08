package parser;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.*;

/**
 * Tests valid and invalid (when applicable) implementations of ScrapeData methods.
 */
public class ScrapedDataJUnit extends TestCase {
	private List<List<String>> teamStats = new ArrayList<List<String>>();
	private List<List<String>> otherTeamStats = new ArrayList<List<String>>();
	private List<String> columnHeaders, imgUrls;
	
	/**
	 * Initializes scraped data JUnit test.
	 * 
	 * @param testName		Name of the test, for clarity during runtime
	 */
	public ScrapedDataJUnit(String testName) {
		super(testName);
	}
	
	/**
	 * Setup conditions for private class variables, which generates fake lists of
	 * data labels and image urls, a lists of lists containing these lists, and another
	 * list of lists containing these lists in a different order.
	 */
	public void setUp() {
		columnHeaders = Arrays.asList("1", "2", "3");
		imgUrls = Arrays.asList("4", "5", "6");	
		teamStats.add(columnHeaders);
		teamStats.add(imgUrls);
		otherTeamStats.add(imgUrls);
		otherTeamStats.add(columnHeaders);
	}
	
	/**
	 * Tests successful initialization of a scraped data object by ensuring that the
	 * provided statistics data is the same as the object's data after initialization.
	 */
	@Test
	public void testSDInit() {
		ScrapedData init = new ScrapedData(columnHeaders, teamStats, imgUrls);
		
		assertEquals(teamStats, init.teamStats);
		assertEquals(columnHeaders, init.columnHeaders);
		assertEquals(imgUrls, init.imgUrls);
	}
	
	/**
	 * Tests unsuccessful initialization of a scraped data object by providing data in
	 * the wrong order and ensuring that this data within the object does not match the
	 * expected data.
	 */
	@Test
	public void testFailSDInit() {
		ScrapedData init = new ScrapedData(imgUrls, otherTeamStats, columnHeaders);
		
		assertNotSame(teamStats, init.teamStats);
		assertNotSame(columnHeaders, init.columnHeaders);
		assertNotSame(imgUrls, init.imgUrls);
	}
	
	/**
	 * Tests successful retrieval of data labels by ensuring that the data labels in
	 * the object after initialization are the same as those provided to it.
	 */
	@Test
	public void testGetHeaders() {
		ScrapedData init = new ScrapedData(columnHeaders, teamStats, imgUrls);
		List<String> getHeaders = init.getColumnHeaders();
		
		assertEquals(getHeaders, init.columnHeaders);
	}
	
	/**
	 * Tests unsuccessful retrieval of data labels by providing data in the wrong order
	 * and ensuring that the data labels within the object are different from what is
	 * expected.
	 */
	@Test
	public void testFailGetHeaders() {
		ScrapedData init = new ScrapedData(imgUrls, teamStats, columnHeaders);
		
		assertNotSame(columnHeaders, init.columnHeaders);
	}
	
	/**
	 * Tests successful retrieval of team statistics by ensuring that the statistics in
	 * the object after initialization are the same as those provided to it.
	 */
	@Test
	public void testGetStats() {
		ScrapedData init = new ScrapedData(columnHeaders, teamStats, imgUrls);
		List<List<String>> getStats = init.getTeamStats();
		
		assertEquals(getStats, init.teamStats);
	}
	
	/**
	 * Tests unsuccessful retrieval of team statistics by providing data in the wrong order
	 * and ensuring that the data labels within the object are different from what is
	 * expected.
	 */
	@Test
	public void testFailGetStats() {
		ScrapedData init = new ScrapedData(columnHeaders, teamStats, imgUrls);
		List<List<String>> getStats = init.getTeamStats();
		
		assertNotSame(getStats, otherTeamStats);
	}
	
	/**
	 * Tests successful retrieval of links to images by ensuring that the links in
	 * the object after initialization are the same as those provided to it.
	 */
	@Test
	public void testGetUrls() {
		ScrapedData init = new ScrapedData(columnHeaders, teamStats, imgUrls);
		List<String> getUrls = init.getImgUrls();
		
		assertEquals(getUrls, init.imgUrls);
	}
	
	/**
	 * Tests unsuccessful retrieval of links to images by providing data in the wrong order
	 * and ensuring that the links within the object are different from what is expected.
	 */
	@Test
	public void testFailGetUrls() {
		ScrapedData init = new ScrapedData(imgUrls, teamStats, columnHeaders);
		
		assertNotSame(imgUrls, init.imgUrls);
	}
}