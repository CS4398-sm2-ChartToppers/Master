package parser;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.*;

public class ScrapedDataJUnit extends TestCase {
	private List<List<String>> teamStats = new ArrayList<List<String>>();
	private List<List<String>> otherTeamStats = new ArrayList<List<String>>();
	private List<String> columnHeaders, imgUrls;
	
	public ScrapedDataJUnit(String testName) {
		super(testName);
	}
	
	public void setUp() {
		columnHeaders = Arrays.asList("1", "2", "3");
		imgUrls = Arrays.asList("4", "5", "6");	
		teamStats.add(columnHeaders);
		teamStats.add(imgUrls);
		otherTeamStats.add(imgUrls);
		otherTeamStats.add(columnHeaders);
	}
	
	@Test
	public void testSDInit() {
		ScrapedData init = new ScrapedData(columnHeaders, teamStats, imgUrls);
		assertEquals(teamStats, init.teamStats);
		assertEquals(columnHeaders, init.columnHeaders);
		assertEquals(imgUrls, init.imgUrls);
	}
	
	@Test
	public void testFailSDInit() {
		ScrapedData init = new ScrapedData(imgUrls, otherTeamStats, columnHeaders);
		assertNotSame(teamStats, init.teamStats);
		assertNotSame(columnHeaders, init.columnHeaders);
		assertNotSame(imgUrls, init.imgUrls);
	}
	
	@Test
	public void testGetHeaders() {
		ScrapedData init = new ScrapedData(columnHeaders, teamStats, imgUrls);
		List<String> getHeaders = init.getColumnHeaders();
		assertEquals(getHeaders, init.columnHeaders);
	}
	
	@Test
	public void testFailGetHeaders() {
		ScrapedData init = new ScrapedData(columnHeaders, teamStats, imgUrls);
		List<String> getHeaders = init.getImgUrls();
		assertNotSame(getHeaders, init.columnHeaders);
	}
	
	@Test
	public void testGetStats() {
		ScrapedData init = new ScrapedData(columnHeaders, teamStats, imgUrls);
		List<List<String>> getStats = init.getTeamStats();
		assertEquals(getStats, init.teamStats);
	}
	
	@Test
	public void testFailGetStats() {
		ScrapedData init = new ScrapedData(columnHeaders, teamStats, imgUrls);
		List<List<String>> getStats = init.getTeamStats();
		assertNotSame(getStats, otherTeamStats);
	}
	
	@Test
	public void testGetUrls() {
		ScrapedData init = new ScrapedData(columnHeaders, teamStats, imgUrls);
		List<String> getUrls = init.getImgUrls();
		assertEquals(getUrls, init.imgUrls);
	}
	
	@Test
	public void testFailGetUrls() {
		ScrapedData init = new ScrapedData(columnHeaders, teamStats, imgUrls);
		List<String> getUrls = init.getColumnHeaders();
		assertNotSame(getUrls, init.imgUrls);
	}
}