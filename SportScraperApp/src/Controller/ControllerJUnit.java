package Controller;

import java.util.ArrayList;
import javax.swing.*;
import junit.framework.TestCase;
import org.junit.*;

import Model.Model;
import View.View;

/**
 * Tests valid and invalid (when applicable) implementations of SportScraperController methods.
 */
public class ControllerJUnit extends TestCase {
	private ArrayList<ArrayList<Double>> statList;
	private JTable table;
	private Model model;
	private View view;
	
	/**
	 * Initializes controller JUnit test.
	 * 
	 * @param testName		Name of the test, for clarity during runtime
	 */
	public ControllerJUnit(String testName) {
		super(testName);
	}
	
	/**
	 * Tests successful initialization of the controller by ensuring that the model and
	 * view are set properly.
	 */
	@Test
	public void testInitController() {
		SportScraperController controller = new SportScraperController();
		controller.setModel(model);
		controller.setView(view);
		
		assertEquals(model, controller.getModel());
		assertEquals(view, controller.getView());
	}
	
	/**
	 * Tests successful retrieval of the model by ensuring that the retrieved model is
	 * the same as the set model.
	 */
	@Test
	public void testGetModel() {
		SportScraperController controller = new SportScraperController();
		controller.setModel(model);
		Model getModel = controller.getModel();
		
		assertEquals(model, getModel);
	}
	
	/**
	 * Tests unsuccessful retrieval of the model by not first setting the model before
	 * retrieving it.
	 */
	@Test
	public void testFailGetModel() {
		SportScraperController controller = new SportScraperController();
		Model getModel = controller.getModel();
		
		assertNotSame(model, getModel);
	}
	
	/**
	 * Tests successful setting of the model by initializing the controller and ensuring
	 * that the appropriate model has been set.
	 */
	@Test
	public void testSetModel() {
		SportScraperController controller = new SportScraperController();
		Model setModel = model;
		controller.setModel(model);
		
		assertEquals(model, setModel);
	}
	
	/**
	 * Tests unsuccessful setting of the model by ensuring that the initialized controller
	 * model is different from one that is set afterwards.
	 */
	@Test
	public void testFailSetModel() {
		SportScraperController controller = new SportScraperController();
		Model getModel = controller.getModel();
		controller.setModel(model);
		
		assertNotSame(model, getModel);
	}
	
	/**
	 * Tests successful retrieval of the view by ensuring that the retrieved view is
	 * the same as the set view.
	 */
	@Test
	public void testGetView() {
		SportScraperController controller = new SportScraperController();
		controller.setView(view);
		View getView = controller.getView();
		
		assertEquals(model, getView);
	}
	
	/**
	 * Tests unsuccessful retrieval of the view by not first setting the view before
	 * retrieving it.
	 */
	@Test
	public void testFailGetView() {
		SportScraperController controller = new SportScraperController();
		View getView = controller.getView();
		
		assertNotSame(model, getView);
	}
	
	/**
	 * Tests successful setting of the view by initializing the controller and ensuring
	 * that the appropriate view has been set.
	 */
	@Test
	public void testSetView() {
		SportScraperController controller = new SportScraperController();
		View setView = view;
		controller.setView(view);
		
		assertEquals(view, setView);
	}
	
	/**
	 * Tests unsuccessful setting of the view by ensuring that the initialized controller
	 * view is different from one that is set afterwards.
	 */
	@Test
	public void testFailSetView() {
		SportScraperController controller = new SportScraperController();
		View getView = controller.getView();
		controller.setView(view);
		
		assertNotSame(view, getView);
	}
	
	/**
	 * Tests successful NHL statistics selection by ensuring that the qCommand associated
	 * with the controller is for NHL.
	 */
	@Test
	public void testNHLGetSelection() {
		SportScraperController controller = new SportScraperController();
		String qCommand = "NHL Stats";
		controller.getSelection(qCommand);
		String qTest = controller.qCommand;
		
		assertEquals(qTest, qCommand);
	}
	
	/**
	 * Tests successful MLB statistics selection by ensuring that the qCommand associated
	 * with the controller is for MLB.
	 */
	@Test
	public void testMLBGetSelection() {
		SportScraperController controller = new SportScraperController();
		String qCommand = "MLB Stats";
		controller.getSelection(qCommand);
		String qTest = controller.qCommand;
		
		assertEquals(qTest, qCommand);
	}
	
	/**
	 * Tests successful NFL statistics selection by ensuring that the qCommand associated
	 * with the controller is for NFL.
	 */
	@Test
	public void testNFLGetSelection() {
		SportScraperController controller = new SportScraperController();
		String qCommand = "NFL Stats";
		controller.getSelection(qCommand);
		String qTest = controller.qCommand;
		
		assertEquals(qTest, qCommand);
	}
	
	/**
	 * Tests unsuccessful statistics selection by ensuring that the qCommand associated
	 * with the controller is not valid.
	 */
	@Test
	public void testFailGetSelection() {
		SportScraperController controller = new SportScraperController();
		String qCommand = "WNBA Stats";
		controller.getSelection(qCommand);
		String qTest = controller.qCommand;
		
		assertNull(qTest);
	}
	
	/**
	 * Tests successful table initialization by ensuring that the empty initialized table
	 * in the controller is the same as another empty table.
	 */
	@Test
	public void testPrintStats() {
		SportScraperController controller = new SportScraperController();
		controller.printStats(statList);
		
		assertEquals(table, controller.testTable);
	}
}