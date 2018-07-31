package parser;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CbsSportsParser implements StandingsParser {

	Document htmlDocument;
	
	public CbsSportsParser (Document html) { this.htmlDocument = html; }
	
	public CbsSportsParser (String url) {
		try { htmlDocument = Jsoup.connect(url).get(); } 
		catch (IOException e) { e.getMessage(); }
	}
	
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
	
	public static ScrapedData parseMLB(String url) throws IOException {
		final List<String> colHeaders, imgUrls;
		final List<List<String>> teamStats;
		
		//Get HTML file from URL
		Document htmlFile = Jsoup.connect(url).maxBodySize(0).get();
		//Parse
		colHeaders = getHeaders(htmlFile);
		teamStats = getMLBStats(htmlFile, colHeaders.size());
		imgUrls = getImageURLS(htmlFile);
		return new ScrapedData(colHeaders,teamStats, imgUrls);
	}
	
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
	
	private static List<List<String>> getTeamStats(Document htmlFile, int numHeaders){
		/*
		 * cbssports.com repeats the same information on their page multiple
		 * times using the same HTML tags and elements. 
		 * 
		 * Does not work for MLB. Make change!
		 * 
		*/
		HashSet<List<String>> teamStats = new HashSet<List<String>>();
		for(Element row : htmlFile.select("tr")){
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
	
	private static List<List<String>> getMLBStats(Document htmlFile, int numHeaders) {
		int max = 0;
		HashSet<List<String>> teamStats = new HashSet<List<String>>();

		for(Element row : htmlFile.select("tr")) {
			List<String> team = new ArrayList<String>();
			int i = 0;
			
			for(Element tds: row.select("td")) { team.add(tds.text()); }
			
			teamStats.add(team);
		}
		
		teamStats.removeIf(list -> list.isEmpty());
		teamStats.removeIf(list -> list.size() > numHeaders+1);
		List<List<String>> stats = new ArrayList<List<String>>(teamStats);
		return stats;
	}
	
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
	
	private static boolean urlEquals(String firstUrl, String secondUrl) {
		int checkLastDigits = 7;
		if(firstUrl.length() == secondUrl.length()) {
			char v1[] = firstUrl.toCharArray();
			char v2[] = secondUrl.toCharArray();
			int i = 0;
			int j = 0;
			while(checkLastDigits != 0) {
				if(v1[i--] !=  v2[i--]) {
					return false;
				}
			}
			return true;
		}
		else { return false; }	
	}
	
	private static int maxListSize(int max ,int size) {
		if (size > max ) max = size;
		return max;
	}
	
	@Override
	public void run() { }
}
