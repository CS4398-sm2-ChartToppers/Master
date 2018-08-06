package Controller;

import java.io.IOException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import Model.ModelJUnit;
import parser.ParserJUnit;
import parser.ScrapedDataJUnit;
import View.ViewJUnit;

public class SportScraperMain {
	public static void main(String[] args) throws IOException {
		Result result = JUnitCore.runClasses(ControllerJUnit.class);
		for (Failure failure : result.getFailures()) { System.out.println(failure.toString()); }
	    System.out.println(result.wasSuccessful());
	    
	    result = JUnitCore.runClasses(ModelJUnit.class);
	    for (Failure failure : result.getFailures()) { System.out.println(failure.toString()); }
	    System.out.println(result.wasSuccessful());
	    
	    result = JUnitCore.runClasses(ParserJUnit.class);
	    for (Failure failure : result.getFailures()) { System.out.println(failure.toString()); }
	    System.out.println(result.wasSuccessful());
	    
	    result = JUnitCore.runClasses(ScrapedDataJUnit.class);
	    for (Failure failure : result.getFailures()) { System.out.println(failure.toString()); }
	    System.out.println(result.wasSuccessful());
	    
	    result = JUnitCore.runClasses(ViewJUnit.class);
	    for (Failure failure : result.getFailures()) { System.out.println(failure.toString()); }
	    System.out.println(result.wasSuccessful());
	}
}