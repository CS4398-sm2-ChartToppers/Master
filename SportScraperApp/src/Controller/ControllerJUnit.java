package Controller;

import junit.framework.TestCase;
import java.util.ArrayList;
import javax.swing.*;
import org.junit.*;

import Model.Model;
import View.View;

public class ControllerJUnit extends TestCase {
	private Model model;
	private View view;
	private ArrayList<ArrayList<Double>> statList;
	private JTable table;
	
	public ControllerJUnit(String testName) {
		super(testName);
	}
	
	@Test
	public void testInitController() {
		SportScraperController controller = new SportScraperController();
		controller.setModel(model);
		controller.setView(view);
		assertEquals(model, controller.getModel());
		assertEquals(view, controller.getView());
	}
	
	@Test
	public void testGetModel() {
		SportScraperController controller = new SportScraperController();
		controller.setModel(model);
		Model getModel = controller.getModel();
		assertEquals(model, getModel);
	}
	
	@Test
	public void testFailGetModel() {
		SportScraperController controller = new SportScraperController();
		Model getModel = controller.getModel();
		assertNotSame(model, getModel);
	}
	
	@Test
	public void testSetModel() {
		SportScraperController controller = new SportScraperController();
		Model setModel = model;
		controller.setModel(model);
		assertEquals(model, setModel);
	}
	
	@Test
	public void testFailSetModel() {
		SportScraperController controller = new SportScraperController();
		Model getModel = controller.getModel();
		controller.setModel(model);
		assertNotSame(model, getModel);
	}
	
	@Test
	public void testGetView() {
		SportScraperController controller = new SportScraperController();
		controller.setView(view);
		View getView = controller.getView();
		assertEquals(model, getView);
	}
	
	@Test
	public void testFailGetView() {
		SportScraperController controller = new SportScraperController();
		View getView = controller.getView();
		assertNotSame(model, getView);
	}
	
	@Test
	public void testSetView() {
		SportScraperController controller = new SportScraperController();
		View setView = view;
		controller.setView(view);
		assertEquals(view, setView);
	}
	
	@Test
	public void testFailSetView() {
		SportScraperController controller = new SportScraperController();
		View getView = controller.getView();
		controller.setView(view);
		assertNotSame(view, getView);
	}
	
	@Test
	public void testNHLGetSelection() {
		SportScraperController controller = new SportScraperController();
		String qCommand = "NHL Stats";
		controller.getSelection(qCommand);
		String qTest = controller.qCommand;
		assertEquals(qTest, qCommand);
	}
	
	@Test
	public void testMLBGetSelection() {
		SportScraperController controller = new SportScraperController();
		String qCommand = "MLB Stats";
		controller.getSelection(qCommand);
		String qTest = controller.qCommand;
		assertEquals(qTest, qCommand);
	}
	
	@Test
	public void testNFLGetSelection() {
		SportScraperController controller = new SportScraperController();
		String qCommand = "NFL Stats";
		controller.getSelection(qCommand);
		String qTest = controller.qCommand;
		assertEquals(qTest, qCommand);
	}
	
	@Test
	public void testFailGetSelection() {
		SportScraperController controller = new SportScraperController();
		String qCommand = "WNBA Stats";
		controller.getSelection(qCommand);
		String qTest = controller.qCommand;
		assertNull(qTest);
	}
	
	@Test
	public void testPrintStats() {
		SportScraperController controller = new SportScraperController();
		controller.printStats(statList);
		assertEquals(table, controller.testTable);
	}
}