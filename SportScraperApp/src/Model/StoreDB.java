package Model;

//TO RUN: add mssql.jdbc-6.4.0.jre8.jar to build-path 
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Stores either an empty or filled table of statistics into the SQL database, and allows
 * emptying or filling of a currently stored table.
 */
public class StoreDB {	 
	public static boolean status;
	
	/**
	 * Initializes a filled table of statistics to be stored into the SQL database.
	 * 
	 * @param colHeaders	List of labels for individual sets of statistics
	 * @param data			List of lists of all statistics, ordered by label
	 * @param tableName		Name of table
	 */
	public StoreDB(List<String> colHeaders, List<List<String>> data, String tableName) { createTable(colHeaders, data, tableName); }
			
	
	/**
	 * Initializes an empty table of statistics to be stored into the SQL database.
	 * 
	 * @param tableName		Name of table
	 */
	public StoreDB(String tableName) { emptyTable(tableName); }
	
	/**
	 * Stores an empty table of statistics into the SQL database.
	 * 
	 * @param tableName		Name of table
	 */
	public static void emptyTable(String tableName) {
		status = false;
		String userName = "ChartToppers";
		String password = "12345678";
		String url = "jdbc:sqlserver://charttoppers.cji1q0n2fjrx.us-east-1.rds.amazonaws.com"; 
	  		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
		  
			String sql = "USE CHARTTOPPERS"; 
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
			sql = "DROP TABLE IF EXISTS dbo." + tableName + ";";
			statement = conn.createStatement();
			statement.executeUpdate(sql); 
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	/**
	 * Stores a filled table of statistics into the SQL database.
	 * 
	 * @param colHeaders	List of labels for individual sets of statistics
	 * @param data			List of lists of all statistics, ordered by label
	 * @param tableName		Name of table
	 */
	public static void createTable(List<String> colHeaders, List<List<String>> data, String tableName) {
		status = true;
		String userName = "ChartToppers";
		String password = "12345678";
		String url = "jdbc:sqlserver://charttoppers.cji1q0n2fjrx.us-east-1.rds.amazonaws.com"; 
	  		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
		 
			String sql = "USE CHARTTOPPERS"; 
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
			sql = "DROP TABLE IF EXISTS dbo." + tableName + ";";
			//drop if table already exists
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			sql = " CREATE TABLE dbo." + tableName + " (Team VARCHAR(20));";
			statement = conn.createStatement();
			statement.executeUpdate(sql);
		 
			//for each column name, insert it into table:  
			for (int i = 0; i < colHeaders.size(); i++){ 
        	  String colName = colHeaders.get(i); //wins/loses/etc column names
        	  sql = ("ALTER table dbo." + tableName + " ADD " + colName + " VARCHAR(15);");
        	  statement = conn.createStatement();
        	  statement.executeUpdate(sql);
            }

			String dummyElement = "?";
      
			//traverses through all rows of team data
			//stars at i=1 because column headers are at i=0
			for(int i = 0; i < data.size(); i++ ) { 
				List<String> row_data = data.get(i);
				sql = "INSERT into " + tableName + " values(?,";
				
				//traverses through each row (individual team stats)
				//build sql INSERT statement for num elements in row minus one
				for (int j = 0; j < row_data.size()-2; j++) { sql = sql + " " + dummyElement + ", "; }
    	  
				sql += dummyElement+");"; //add last element in row 
				PreparedStatement statementp = conn.prepareStatement(sql);
    	  
				//set the values into the sql statement
				for (int k = 0; k < row_data.size(); k++){ statementp.setString((k+1), row_data.get(k)); }
				statementp.executeUpdate();
			} 
		} catch(Exception e) { e.printStackTrace(); }
	}
}