package Controller;

import java.io.IOException;
import Model.StoreDB;
import parser.CbsSportsParser;
import parser.ScrapedData;
import querycommand.CreateCbsSqlTables;
import querycommand.QueryCommand;

public class SportScraperMain {
	public static void main(String[] args) throws IOException {
		//lazy testing to show how the parser works
		new SportScraperController();
		QueryCommand createTables = new CreateCbsSqlTables();
	}
}