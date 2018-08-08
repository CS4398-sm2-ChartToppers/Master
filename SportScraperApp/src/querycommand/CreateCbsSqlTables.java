package querycommand;

import java.io.IOException;

import Model.StoreDB;
import parser.CbsSportsParser;
import parser.ScrapedData;

public class CreateCbsSqlTables implements QueryCommand {
	private final String urls[] = {"/nfl/standings/",
			 	 				   "/nba/standings/regular/division/",
			 	 				   "/nhl/standings/",
			 	 				   "/college-football/standings/"
			 	 				   };
	private final String tblNames[] = {"NFL", "NBA","NHL", "College_Football"};
	private final String domain = "http://www.cbssports.com";	
	public CreateCbsSqlTables() { run(); }
	
	@Override
	public void run() {
		ScrapedData data;
		for (int i = 0; i < urls.length; i ++) {
			try {
				System.out.println("Gathering Data for " + tblNames[i] + " rankings...");
				data = CbsSportsParser.parse(domain+urls[i]);
				System.out.println("Creating SQL Table for " + tblNames[i] + " rankings...");
				StoreDB.createTable(data.getColumnHeaders(), data.getTeamStats(), tblNames[i]);
			} catch (IOException e) { e.getMessage(); }
		}
		
		try {
			System.out.println("Gathering Data for MLB rankings...");
			data = CbsSportsParser.parseMLB(domain+"/mlb/standings/regular");
			System.out.println("Creating SQL Table for MLB rankings...");
			StoreDB.createTable(data.getColumnHeaders(), data.getTeamStats(), "MLB");
		} catch (IOException e) { e.getMessage(); }
		System.out.println("Finished Creating Tables!");
	}
}