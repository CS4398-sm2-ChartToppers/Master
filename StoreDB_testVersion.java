//connects to DB
//created standalone version if anyone needs to test db connection part at any point
//TO RUN: add mssql.jdbc-6.4.0.jre8.jar to buildpath 

import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;



 public class StoreDB_testVersion {



//arbitrary example for simple test
  public static void main(String [] args){
	  List<List<String>> data = new ArrayList<List<String>>();
	  List<String> cols = new ArrayList<String>();
	  cols.add("firstcol");
	  cols.add("secondcol");
	  cols.add("thirdcol");
	  data.add(cols);
	  List<String> row1 = new ArrayList<String>();
	  row1.add("a");
	  row1.add("b");
	  row1.add("c");
	  row1.add("d");
	  data.add(row1);
	  List<String> row2 = new ArrayList<String>();
	  row2.add("1");
	  row2.add("2%");
	  row2.add("3-3");
	  row2.add("4");
	  data.add(row2);

	  String table = "testTable";
	  deleteTable(table);
	 createTable(cols, data, table);

  }


//deletes a table.
public static void deleteTable(String tableName){

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
	      System.out.println("DELETE");
		 
		 
	  } catch(Exception e) {
		  e.printStackTrace();
	  }
  
  }


  //creates table: drops table if it exists-> creates new table-> inserts column header names (wins/loses/...)

  public static void createTable(List<String> colHeaders, List<List<String>> data, String tableName){


	  String userName = "ChartToppers";
	  String password = "12345678";
	  String url = "jdbc:sqlserver://charttoppers.cji1q0n2fjrx.us-east-1.rds.amazonaws.com"; 
	  		
	  try {
		  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		  Connection conn = DriverManager.getConnection(url, userName, password);
		  System.out.println("LOGIN");
		  String sql = "USE CHARTTOPPERS"; 
	      Statement statement = conn.createStatement();
	      statement.executeUpdate(sql);
		  sql = "DROP TABLE IF EXISTS dbo." + tableName + ";";
		  //drop if table already exists
		 statement = conn.createStatement();
		 statement.executeUpdate(sql);
		 sql = " CREATE TABLE dbo." + 
				  tableName + " (team VARCHAR(15));";
		 statement = conn.createStatement();
		 statement.executeUpdate(sql);
		 System.out.println("CREATE TABLE");
          //for each column name, insert it into table:
		  
          for (int i = 0; i < colHeaders.size(); i++){ 
        	  String colName = colHeaders.get(i); //wins/loses/etc column names
        	  sql = ("ALTER table dbo." + tableName + " ADD " + colName + " VARCHAR(15);" 

              ); 
        	  statement = conn.createStatement();
        	  statement.executeUpdate(sql);
        	  System.out.println("ADDCOL");

            }

       	 
 
      String dummyElement = "?";
      System.out.println("..");
      
      //traverses through all rows of team data
      //stars at i=1 because column headers are at i=0
      for(int i = 1; i < data.size()-1; i++ ) {
    	  
    	  List<String> row_data = data.get(i); //
    	  
    	  sql = "INSERT into " + tableName + " values(?,";
    	  
    	  //traverses through each row (individual team stats)
    	  for (int j = 0; j < row_data.size()-2; j++){ //build sql INSERT statement for num elements in row minus one
    		  
    		  sql = sql + " " + dummyElement + ", ";
    		 
    		  
    	  }
    	  
    	  sql = sql + dummyElement + ");"; //add last element in row
    	  System.out.println(sql);
    	  PreparedStatement statementp = conn.prepareStatement(sql);

      
      //set the values into the sql statement
    	 
    	  int val = 1;
    	  for (int k = 0; k < row_data.size(); k++){

    		  statementp.setString(val, row_data.get(k));
    		  
    		  System.out.println(val + " " + row_data.get(k));
    		  val++;
    		  

    	  }
    	 
    	  
    	  statementp.executeUpdate();

      }
 
	  }catch(Exception e) {
		  e.printStackTrace();
   	}

  }

}//class



