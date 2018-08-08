package parser;

import java.util.List;

/**
 * Creates an object that holds all of the scraped data consisting of team statistics, 
 * including the list of data labels (headers), lists of lists of statistics data, and 
 * list of associated team images.
 */
public class ScrapedData {
	List<List<String>> teamStats;
	List<String> columnHeaders, imgUrls;
	
	/**
	 * Initializes a ScrapedData object that holds all of the team statistics data.
	 * 
	 * @param headers	The list of data labels
	 * @param values	The list of lists of statistics data
	 * @param imgUrls	The list of associated team images
	 */
	public ScrapedData(List<String> headers, List<List<String>> values, List<String> imgUrls) {
		columnHeaders = headers;
		teamStats = values;
		this.imgUrls = imgUrls;
	}
	
	/**
	 * Retrieves the list of data labels.
	 * 
	 * @return			The list of data labels
	 */
	public List<String> getColumnHeaders() { return this.columnHeaders; }
	
	/**
	 * Retrieves the list of lists of team statistics.
	 * 
	 * @return			The list of list of team statistics
	 */
	public List<List<String>> getTeamStats(){ return this.teamStats; }
	
	/**
	 * Displays the team statistics in the console.
	 */
	public void displayTeamStats() {
		for (List<String> team : teamStats) { System.out.println(team); }
	}
	
	/**
	 * Retrieves the list of links to team images.
	 * 
	 * @return			The list of image urls
	 */
	public List<String> getImgUrls() { return this.imgUrls; }
	
	/**
	 * Displays links to team images.
	 */
	public void displayImgUrls() {
		for(String url : imgUrls) { System.out.println(url); }
	}
}