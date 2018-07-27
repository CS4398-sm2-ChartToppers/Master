//connects to DB
//still messing with permissions [on the database side] (?) to execute the statements 

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


//establishes server connection
//arbitrary example for simple test
  public static void main(String [] args){
	  List<List<String>> data = new ArrayList<List<String>>();
	  List<String> singleList = new ArrayList<String>();
	  singleList.add("a");
	  singleList.add("b");
	  data.add(singleList);
	  List<String> cols = new ArrayList<String>();
	  cols.add("firstcol");
	  cols.add("secondcol");
	  cols.add("thirdcol");
	  String table = "testTable";
	  
	 createTable(cols, data, table);

  }


//can create new blank DB, essentailly deletes the db but creates empty one with the same name.

  public static void createDB(String db){

	  String userName = "ChartToppers";
	  String password = "12345678";
	  String url = "jdbc:sqlserver://charttoppers.cji1q0n2fjrx.us-east-1.rds.amazonaws"; 
	  		
	  try {
		  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		  Connection conn = DriverManager.getConnection(url, userName, password);
		  
		  String sql = "DROP DATABASE IF EXISTS " + db + ";" + " CREATE DATABASE " + db + ";"; 
	      Statement statement = conn.createStatement();
	      statement.executeUpdate(sql);
		  
		 
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
		  String sql = "DROP TABLE IF EXISTS dbo." + tableName + ";";
		  //drop if table already exists
		 Statement statement = conn.createStatement();
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
      
      //traverses through all rows of teams
      for(int i = 0; i < data.size(); i++ ) {
    	  
    	  List<String> stats = data.get(i); 
    	  sql = "INSERT into " + tableName + " values(";
    	  
    	  //traverses through each row (individual team stats)
    	  for (int j = 0; j < stats.size()-1; j++){ //build sql INSERT statement for num elements in row minus one
    		  
    		  sql = sql + dummyElement + ", ";
    	  }

    	  sql = sql + dummyElement + ");"; //add last element in row
    	  PreparedStatement statementp = conn.prepareStatement(sql);

      
      //set the values into the sql statement
    	  for (int k = 0; k < stats.size() -1; k++){

    		  statementp.setString(i+1, stats.get(i));

    	  }
    	  
    	  statementp.executeUpdate();

      }
 
	  }catch(Exception e) {
		  e.printStackTrace();
   	}

  }

}//class



