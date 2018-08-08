package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;

/**
 * Allows pulling of statistics data from tables in an html document, or indirectly 
 * from a url that is used to generate an html document, and divides this data into
 * a list of data labels (headers), a list of lists of the statistics data, and a list
 * of links to images related to individual teams.
 */
public class CbsSportsParser implements StandingsParser {

	Document htmlDocument;
	
	/**
	 * Initializes a CbsSportsParser object with a given html document.
	 * 
	 * @param html		The provided html document
	 */
	public CbsSportsParser (Document html) { this.htmlDocument = html; }
	
	/**
	 * Initializes a CbsSportsParser object with an html document that is generated
	 * from the given url
	 * 
	 * @param url		The provided url
	 */
	public CbsSportsParser (String url) {
		try { htmlDocument = Jsoup.connect(url).get(); } 
		catch (IOException e) { e.getMessage(); }
	}
	
	/**
	 * Parses data from a table within and html file that is generated from the given
	 * url into a ScrapedData object containing a list of data labels, a list of lists 
	 * of the statistics data, and a list of links to images related to individual teams.
	 * Not configured for use with MLB data.
	 * 
	 * @param url		The provided url
	 * @return			The ScrapedData object containing parsed data
	 * @throws IOException
	 */
	public static ScrapedData parse(String url) throws IOException {
		/* Will parse most of the sports with HTML Tables on CbsSports 
		 * URLS: /nfl/standings/
		 * 		 /nba/standings/regular/division/
		 * 	     /mlb/standings/regular/
		 * 		 /nhl/standings/
		 * 		 /college-football/standings/
		 */
		final List<String> colHeaders, imgUrls;
		final List<List<String>> teamStats;
		
		//Get HTML file from URL
		Document htmlFile = Jsoup.connect(url).maxBodySize(0).get();
		//Parse
		colHeaders = getHeaders(htmlFile);
		teamStats = getTeamStats(htmlFile, colHeaders.size());
		imgUrls = getImageURLS(htmlFile);
				
		return new ScrapedData(colHeaders,teamStats, imgUrls);
	}
	
	/**
	 * Parses data from a table within and html file that is generated from the given
	 * url into a ScrapedData object containing a list of data labels, a list of lists 
	 * of the statistics data, and a list of links to images related to individual teams.
	 * Used particularly for MLB data.
	 * 
	 * @param url			The provided url
	 * @return				The ScrapedData object containing parsed data
	 * @throws IOException	If input is invalid
	 */
	public static ScrapedData parseMLB(String url) throws IOException {
		final List<String> colHeaders, imgUrls;
		final List<List<String>> teamStats;
		
		//Get HTML file from URL
		Document htmlFile = Jsoup.connect(url).maxBodySize(0).get();
		//Parse
		colHeaders = getHeaders(htmlFile);
		teamStats = getMLBStats(htmlFile, colHeaders.size());
		imgUrls = getImageURLS(htmlFile);
		
		return new ScrapedData(colHeaders, teamStats, imgUrls);
	}
	
	/**
	 * Retrieves a list of data labels (headers) from a table within an html file.
	 * 
	 * @param htmlFile	The provided html file
	 * @return			The list of data labels
	 */
	private static List<String> getHeaders(Document htmlFile) {
		LinkedHashSet<String> colHeaders = new LinkedHashSet<String>();
		
		for(Element headers : htmlFile.select("th")) {
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
		return headers;
	}
	
	/**
	 * Retrieves a list of lists containing all team data for a particular team within 
	 * a table in the given html file, using the header number to distinguish which table,
	 * and therefore which team, is being accessed. Not configured for use with MLB data.
	 * 
	 * @param htmlFile		The provided html file
	 * @param numHeaders	The index for the table being accessed
	 * @return				The list of lists of team data
	 */
	private static List<List<String>> getTeamStats(Document htmlFile, int numHeaders) {
		/*
		 * cbssports.com repeats the same information on their page multiple
		 * times using the same HTML tags and elements. 
		 * 
		 * Does not work for MLB. Make change!
		 * 
		*/
		HashSet<List<String>> teamStats = new HashSet<List<String>>();
		for(Element row : htmlFile.select("tr")) {
			List<String> team = new ArrayList<String>();
			int i = 0;
			
			for(Element tds: row.select("td")) {
				if(i <= numHeaders) { team.add(tds.text()); }
				i++;
			}
			
			teamStats.add(team);
		}
		
		teamStats.removeIf(list -> list.isEmpty());
		List<List<String>> stats = new ArrayList<List<String>>(teamStats);
		
		return stats;
	}
	
	/**
	 * Retrieves a list of lists containing all team data for a particular team within 
	 * a table in the given html file, using the header number to distinguish which table,
	 * and therefore which team, is being accessed. Used particularly for MLB data.
	 * 
	 * @param htmlFile		The provided html file
	 * @param numHeaders	The index for the table being accessed
	 * @return				The list of lists of team data
	 */
	private static List<List<String>> getMLBStats(Document htmlFile, int numHeaders) {
		HashSet<List<String>> teamStats = new HashSet<List<String>>();

		for(Element row : htmlFile.select("tr")) {
			List<String> team = new ArrayList<String>();
			for(Element tds: row.select("td")) { team.add(tds.text()); }
			teamStats.add(team);
		}
		
		teamStats.removeIf(list -> list.isEmpty());
		teamStats.removeIf(list -> list.size() > numHeaders+1);
		List<List<String>> stats = new ArrayList<List<String>>(teamStats);
		
		return stats;
	}
	
	/**
	 * Retrieves a list of links to images associated with particular teams from a 
	 * given html file.
	 * 
	 * @param htmlFile	The provided html file
	 * @return			The list of links to images
	 */
	private static List<String> getImageURLS(Document htmlFile) {
		/*
		 * cbssports.com repeats the same information on their page multiple
		 * times using the same HTML tags and elements. 
		 * 
		 * In order to not pull duplicates, the function
		 * stores the first URL string inside of firstUrl variable
		 * and sets repeat state to true.
		 * 
		 */
		List<String> imgUrls = new ArrayList<String>();
		boolean repeat = false;
		String firstUrl = null;
		
		for (Element img : htmlFile.select("img")) {
			if(repeat == true && urlEquals(firstUrl, img.attr("data-lazy"))) break;
			if(img.attr("alt").equals("team logo")) {
				imgUrls.add(img.attr("data-lazy"));
				if(repeat = false) {
					firstUrl = img.attr("data-lazy");
					repeat = true;
				}
			}
		}
		
		return imgUrls;
	}
	
	/**
	 * Checks if two urls are the same.
	 * 
	 * @param firstUrl	The first url to be compared
	 * @param secondUrl	The second url to be compared
	 * @return			True if the urls are the same, false otherwise
	 */
	private static boolean urlEquals(String firstUrl, String secondUrl) {
		int checkLastDigits = 7;
		if(firstUrl.length() == secondUrl.length()) {
			char v1[] = firstUrl.toCharArray();
			char v2[] = secondUrl.toCharArray();
			int i = 0;
			while(checkLastDigits != 0) {
				if(v1[i--] !=  v2[i--]) { return false; }
			}
			return true;
		}
		else { return false; }	
	}
	
	/**
	 * Runs the parser.
	 */
	@Override
	public void run() { }
}