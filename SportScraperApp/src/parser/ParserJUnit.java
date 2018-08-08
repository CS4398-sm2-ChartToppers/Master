package parser;

import java.io.IOException;
import junit.framework.TestCase;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Tests valid and invalid (when applicable) implementations of CbsSportsParser methods.
 */
public class ParserJUnit extends TestCase {
	private String url = "http://www.cbssports.com";
	private Document html;
	private ScrapedData scraped;
	
	/**
	 * Initializes parser JUnit test.
	 * 
	 * @param testName		Name of the test, for clarity during runtime
	 */
	public ParserJUnit(String testName) { super(testName); }
	
	/**
	 * Setup conditions for private class variables, which generates an html document
	 * from cbssports.com and creates a ScrapedData object using parsed data from this
	 * html document.
	 */
	public void setUp() {
		try { html = Jsoup.connect(url).get(); } 
		catch (IOException e) { e.getMessage(); }
		
		try { scraped = CbsSportsParser.parse(url); } 
		catch (IOException e) { e.getMessage(); }
	}
	
	/**
	 * Tests successful initialization of CbsSportsParser object by ensuring that the
	 * htmlDocument variable in the initialized CbsSportsParser is the same as the one
	 * that is provided to it.
	 */
	@Test
	public void testHtmlDocInit() {
		CbsSportsParser test = new CbsSportsParser(html);
		assertEquals(html, test.htmlDocument);
	}
	
	/**
	 * Tests unsuccessful initialization of CbsSortsParser object by ensuring that the
	 * htmlDocument variable in the initialized CbsSportsParser generated from the 
	 * provided html document is not the same as a different html document formed from 
	 * another url.
	 */
	@Test
	public void testFailHtmlDocInit() {
		url = "http://google.com";
		try { html = Jsoup.connect(url).get(); } 
		catch (IOException e) { e.getMessage(); }
		
		CbsSportsParser test = new CbsSportsParser(html);
		url = "http://cbssports.com";
		try { html = Jsoup.connect(url).get(); } 
		catch (IOException e) { e.getMessage(); }
		
		assertNotSame(html, test.htmlDocument);
	}
	
	/**
	 * Tests unsuccessful initialization of CbsSortsParser object by ensuring that the
	 * htmlDocument variable in the initialized CbsSportsParser generated from the 
	 * provided url is not the same as a different html document formed from 
	 * another url.
	 */
	@Test
	public void testFailUrlDocInit() {
		url = "http://google.com";
		try { html = Jsoup.connect(url).get(); } 
		catch (IOException e) { e.getMessage(); }
		
		url = "http://www.cbssports.com";
		CbsSportsParser test = new CbsSportsParser(url);
		
		assertNotSame(html, test.htmlDocument);
	}
	
	/**
	 * Tests successful parsing of team statistics including the list of data labels
	 * (headers), lists of lists of statistics data, and list of associated team images
	 * is the same in the scraped object as is generated below, with both being provided
	 * data from the same html file. Does not include MLB data.
	 */
	@Test
	public void testParse () {
		LinkedHashSet<String> colHeaders = new LinkedHashSet<String>();
		for(Element headers : html.select("th")) {
			if(headers.text().equals("")) { }
			else {
				if(headers.text().equals("W Wins")) {
					do {
						colHeaders.add("["+headers.text()+"]");
						headers = headers.nextElementSibling();
					} while(headers != null);
					break;
				}
			}
		}
		List<String> headers = new ArrayList<String>(colHeaders);
		
		HashSet<List<String>> teamStats = new HashSet<List<String>>();
		for(Element row : html.select("tr")) {
			List<String> team = new ArrayList<String>();
			int i = 0;
			
			for(Element tds: row.select("td")) {
				if(i <= headers.size()) { team.add(tds.text()); }
				i++;
			}
			
			teamStats.add(team);
		}
		teamStats.removeIf(list -> list.isEmpty());
		List<List<String>> stats = new ArrayList<List<String>>(teamStats);
		
		List<String> imgUrls = new ArrayList<String>();
		for (Element img : html.select("img")) { if(img.attr("alt").equals("team logo")) { imgUrls.add(img.attr("data-lazy")); } }
		
		assertEquals(headers, scraped.getColumnHeaders());
		assertEquals(stats, scraped.getTeamStats());
		assertEquals(imgUrls, scraped.getImgUrls());
	}
	
	/**
	 * Tests unsuccessful parsing of team statistics including the list of data labels
	 * (headers), lists of lists of statistics data, and list of associated team images
	 * is different in the scraped object from that generated below, with both being provided
	 * data from a different html file.
	 */
	@Test
	public void testFailParse () {
		url = "http://google.com";
		try { html = Jsoup.connect(url).get(); } 
		catch (IOException e) { e.getMessage(); }
		
		LinkedHashSet<String> colHeaders = new LinkedHashSet<String>();
		for(Element headers : html.select("th")) {
			if(headers.text().equals("")) { }
			else {
				if(headers.text().equals("W Wins")) {
					do {
						colHeaders.add("["+headers.text()+"]");
						headers = headers.nextElementSibling();
					} while(headers != null);
					break;
				}
			}
		}
		List<String> headers = new ArrayList<String>(colHeaders);
		
		HashSet<List<String>> teamStats = new HashSet<List<String>>();
		for(Element row : html.select("tr")) {
			List<String> team = new ArrayList<String>();
			int i = 0;
			
			for(Element tds: row.select("td")) {
				if(i <= headers.size()) { team.add(tds.text()); }
				i++;
			}
			
			teamStats.add(team);
		}
		teamStats.removeIf(list -> list.isEmpty());
		List<List<String>> stats = new ArrayList<List<String>>(teamStats);
		
		List<String> imgUrls = new ArrayList<String>();
		for (Element img : html.select("img")) { if(img.attr("alt").equals("team logo")) { imgUrls.add(img.attr("data-lazy")); } }
		
		assertNotSame(headers, scraped.getColumnHeaders());
		assertNotSame(stats, scraped.getTeamStats());
		assertNotSame(imgUrls, scraped.getImgUrls());
	}
	
	/**
	 * Tests successful parsing of MLB statistics including the list of data labels
	 * (headers), lists of lists of statistics data, and list of associated team images
	 * is the same in the scraped object as is generated below, with both being provided
	 * data from the same html file.
	 */
	@Test
	public void testMLBParse() {
		LinkedHashSet<String> colHeaders = new LinkedHashSet<String>();
		for(Element headers : html.select("th")) {
			if(headers.text().equals("")) { }
			else {
				if(headers.text().equals("W Wins")) {
					do {
						colHeaders.add("["+headers.text()+"]");
						headers = headers.nextElementSibling();
					} while(headers != null);
					break;
				}
			}
		}
		List<String> headers = new ArrayList<String>(colHeaders);
		
		HashSet<List<String>> teamStats = new HashSet<List<String>>();
		for(Element row : html.select("tr")) {
			List<String> team = new ArrayList<String>();
			for(Element tds: row.select("td")) { team.add(tds.text()); }
			teamStats.add(team);
		}
		teamStats.removeIf(list -> list.isEmpty());
		teamStats.removeIf(list -> list.size() > headers.size() + 1);
		List<List<String>> stats = new ArrayList<List<String>>(teamStats);
		
		List<String> imgUrls = new ArrayList<String>();
		for (Element img : html.select("img")) { if(img.attr("alt").equals("team logo")) { imgUrls.add(img.attr("data-lazy")); } }
		
		assertEquals(headers, scraped.getColumnHeaders());
		assertEquals(stats, scraped.getTeamStats());
		assertEquals(imgUrls, scraped.getImgUrls());
	}
	
	/**
	 * Tests unsuccessful parsing of MLB statistics including the list of data labels
	 * (headers), lists of lists of statistics data, and list of associated team images
	 * is different in the scraped object from that generated below, with both being provided
	 * data from a different html file.
	 */
	@Test
	public void testFailMLBParse() {
		url = "http://google.com";
		try { html = Jsoup.connect(url).get(); } 
		catch (IOException e) { e.getMessage(); }
		
		LinkedHashSet<String> colHeaders = new LinkedHashSet<String>();
		for(Element headers : html.select("th")) {
			if(headers.text().equals("")) { }
			else {
				if(headers.text().equals("W Wins")) {
					do {
						colHeaders.add("["+headers.text()+"]");
						headers = headers.nextElementSibling();
					} while(headers != null);
					break;
				}
			}
		}
		List<String> headers = new ArrayList<String>(colHeaders);
		
		HashSet<List<String>> teamStats = new HashSet<List<String>>();
		for(Element row : html.select("tr")) {
			List<String> team = new ArrayList<String>();
			for(Element tds: row.select("td")) { team.add(tds.text()); }
			teamStats.add(team);
		}
		teamStats.removeIf(list -> list.isEmpty());
		teamStats.removeIf(list -> list.size() > headers.size() + 1);
		List<List<String>> stats = new ArrayList<List<String>>(teamStats);
		
		List<String> imgUrls = new ArrayList<String>();
		for (Element img : html.select("img")) { if(img.attr("alt").equals("team logo")) { imgUrls.add(img.attr("data-lazy")); } }
		
		assertNotSame(headers, scraped.getColumnHeaders());
		assertNotSame(stats, scraped.getTeamStats());
		assertNotSame(imgUrls, scraped.getImgUrls());
	}
}