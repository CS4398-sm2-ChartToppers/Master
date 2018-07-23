import java.net.*;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.lang.reflect.Array;

public class GetUrl {

	
	public static void main(String[] args)throws Exception {
		
		
   // public static ArrayList<Array[]> parse(String url) throws Exception{
    	      
   //   Document htmlFile = Jsoup.connect(url).get();
     
		
		 Document htmlFile = Jsoup.connect("https://www.cbssports.com/mlb/standings/regular/").get();	
     for(Element headers : htmlFile.select("th")) {
    	  	System.out.println(headers.text());
      }
     for(Element row : htmlFile.select("tr")){
    	 	for(Element tds: row.select("td")) {
    	 		System.out.println(tds.text());
    	 	}
     }
   }
     
     


    }

