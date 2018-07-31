package Model;

//connects to DB
//TO RUN: add mssql.jdbc-6.4.0.jre8.jar to build-path 
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class StoreDB {	 
	public StoreDB(List<String> colHeaders, List<List<String>> data, String tableName) {
		createTable(colHeaders, data, tableName);
	}
			
	public StoreDB(String tableName) {
		emptyTable(tableName);
	}
	
	//storing tableName in null table (i.e. delete table)
	public static void emptyTable(String tableName) {
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//creates table: drops table if it exists-> creates new table-> inserts col and row data
	public static void createTable(List<String> colHeaders, List<List<String>> data, String tableName) {
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
				for (int j = 0; j < row_data.size()-2; j++) { //build sql INSERT statement for num elements in row minus one
					sql = sql + " " + dummyElement + ", ";
				}
    	  
				sql += dummyElement+");"; //add last element in row 
				PreparedStatement statementp = conn.prepareStatement(sql);
    	  
				//set the values into the sql statement
				for (int k = 0; k < row_data.size(); k++){
					statementp.setString((k+1), row_data.get(k));
				}
				// try {
				statementp.executeUpdate();
				// }catch (Exception e) {
				//	  e.getMessage();
				// }
			} 
		} catch(Exception e) {
		  e.printStackTrace();
		}
	}
}//class