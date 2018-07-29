package querycommand;

import java.io.IOException;
import java.util.List;

import Model.StoreDB;
import parser.CbsSportsParser;
import parser.ScrapedData;

public class CreateCbsSqlTables implements QueryCommand {
	private final String urls[] = {"/nfl/standings/",
			 	 				   "/nba/standings/regular/division/",
			 	 				   "/mlb/standings/regular/",
			 	 				   "/nhl/standings/",
			 	 				   "/college-football/standings/"
			 	 				   };
	private final String tblNames[] = {"NFL", "NBA", "MLB", "NHL", "College_Football"};
	private final String domain = "http://www.cbssports.com";	
	public CreateCbsSqlTables() {
		run();
	}
	@Override
	public void run() {
		for (int i = 0; i < urls.length; i ++) {
			try {
			System.out.println("Gathering Data for " + tblNames[i]+ " rankings...");
			ScrapedData data = CbsSportsParser.parse(domain+urls[i]);
			System.out.println("Creating SQL Table for " + tblNames[i]+ " rankings...");
			StoreDB.createTable(data.getColumnHeaders(), data.getTeamStats(), tblNames[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		}
		System.out.println("Finished Creating Tables!");
		
	}


}
