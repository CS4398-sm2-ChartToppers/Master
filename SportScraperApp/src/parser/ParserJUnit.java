package parser;

import junit.framework.TestCase;
import org.junit.*;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class ParserJUnit extends TestCase {
	private String url = "http://www.cbssports.com";
	private Document html;
	private CbsSportsParser parser = new CbsSportsParser(url);
	private ScrapedData scraped;
	
	public ParserJUnit(String testName) {
		super(testName);
	}
	
	public void setUp() {
		try { html = Jsoup.connect(url).get(); } 
		catch (IOException e) { e.getMessage(); }
		
		try { scraped = parser.parse(url); } 
		catch (IOException e) { e.getMessage(); }
	}
	
	@Test
	public void testHtmlDocInit() {
		CbsSportsParser test = new CbsSportsParser(html);
		assertEquals(html, test.htmlDocument);
	}
	
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
	
	@Test
	public void testUrlDocInit() {
		CbsSportsParser test = new CbsSportsParser(url);
		assertEquals(html, test.htmlDocument);
	}
	
	@Test
	public void testFailUrlDocInit() {
		url = "http://google.com";
		try { html = Jsoup.connect(url).get(); } 
		catch (IOException e) { e.getMessage(); }
		url = "http://www.cbssports.com";
		CbsSportsParser test = new CbsSportsParser(url);
		assertNotSame(html, test.htmlDocument);
	}
	
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
		for (Element img : html.select("img")) {
			if(img.attr("alt").equals("team logo")) {
				imgUrls.add(img.attr("data-lazy"));
			}
		}
		
		assertEquals(headers, scraped.getColumnHeaders());
		assertEquals(stats, scraped.getTeamStats());
		assertEquals(imgUrls, scraped.getImgUrls());
	}
	
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
		for (Element img : html.select("img")) {
			if(img.attr("alt").equals("team logo")) {
				imgUrls.add(img.attr("data-lazy"));
			}
		}
		
		assertNotSame(headers, scraped.getColumnHeaders());
		assertNotSame(stats, scraped.getTeamStats());
		assertNotSame(imgUrls, scraped.getImgUrls());
	}
	
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
		for (Element img : html.select("img")) {
			if(img.attr("alt").equals("team logo")) {
				imgUrls.add(img.attr("data-lazy"));
			}
		}
		
		assertEquals(headers, scraped.getColumnHeaders());
		assertEquals(stats, scraped.getTeamStats());
		assertEquals(imgUrls, scraped.getImgUrls());
	}
	
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
		for (Element img : html.select("img")) {
			if(img.attr("alt").equals("team logo")) {
				imgUrls.add(img.attr("data-lazy"));
			}
		}
		
		assertNotSame(headers, scraped.getColumnHeaders());
		assertNotSame(stats, scraped.getTeamStats());
		assertNotSame(imgUrls, scraped.getImgUrls());
	}
}