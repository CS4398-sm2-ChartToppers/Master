package parser;

import java.util.List;

public class ScrapedData {
	List<List<String>> teamStats;
	List<String> columnHeaders, imgUrls;
	
	public ScrapedData(List<String> headers, List<List<String>> values, List<String> imgUrls) {
		columnHeaders = headers;
		teamStats = values;
		this.imgUrls = imgUrls;
	}
	
	public List<String> getColumnHeaders() { return this.columnHeaders; }
	
	public List<List<String>> getTeamStats(){ return this.teamStats; }
	
	public void displayTeamStats() {
		for (List<String> team : teamStats) { System.out.println(team); }
	}
	
	public List<String> getImgUrls() { return this.imgUrls; }
	
	public void displayImgUrls() {
		for(String url : imgUrls) { System.out.println(url); }
	}
}