package Controller;

import java.io.IOException;
import Model.StoreDB;
import parser.CbsSportsParser;
import parser.ScrapedData;
import querycommand.CreateCbsSqlTables;
import querycommand.QueryCommand;

public class SportScraperMain {
	public static void main(String[] args) throws IOException {
		new ControllerJUnit();
	}
}