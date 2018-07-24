import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

 public class StoreDB {

  private String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=master;user=ChartToppers;password=4398_sm2";


  public static void main(String [] args){
    //...making main method for initial testing if it works
  }

  //sets up connection to sql server
  public static Connection getConnection(){
    return connection = DriverManager.getConnection(connectionUrl);

  }

//can create new blank DB, essentailly deletes the db but creates empty one with the same name.
  public static void createDB(String db){


      try (getConnection()) { 

        
        String sql = "DROP DATABASE IF EXISTS " + db + ";" + " CREATE DATABASE " + 
          db + ";"; 

          try (Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            
          }

      }
  }

  //creates table: drops table if it exists-> creates new table-> inserts column header names (wins/loses/...)
  public static void createTable(String[] colHeaders, String tableName){

    try{

      // Load SQL Server JDBC driver and establish connection.
      System.out.print("Connecting to SQL Server ... ");

      try (getConnection()) { 

        System.out.println("Done.");
        String sql = "DROP TABLE IF EXISTS " + tableName + ";" + " CREATE TABLE " + 
          tableName + ";"; //drop and recreate table if table already exists

          try (Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            
          }

          //for each column name, try to connect/insert it into DB
          for (int i = 0; i < colHeaders.length; i++){ 

            String colName = colHeaders[i]; //wins/loses/etc
            
            sql = ("ALTER table " + tableName + "; " + 
                  "ADD " + colName + " VARCHAR(10);" 
              ); //all varchars datatype for simple insert, shouldn't affect anything downstream 

            try (Statement statement = connection.createStatement()) {
              statement.executeUpdate(sql);
            }

          } 

      }//second try

    }//first try
  }


  public static void setRowData(String[] row, String tableName){
    
    try (getConnection()) { 
      String dummyElement = "?";
      String sql = ("INSERT into " + tableName + " values(";

      for (int i = 0; i < row.length -1; i++){ //build sql INSERT statement for num elements in row minus one

        sql = sql + dummyElement + ", ";
      }

      sql = sql + dummyElement + ");"; //add last element in row
      
      PreparedStatement statement = connection.prepareStatement(sql);

      for (int i = 0; i < row.length -1; i++){
        statement.setString(i+1, row[i]);
      }

      statement.executeUpdate();
    }
}




}//class


  


