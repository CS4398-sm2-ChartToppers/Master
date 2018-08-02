package Controller;

import junit.framework.TestCase;
import java.util.ArrayList;
import javax.swing.*;
import org.junit.*;

import Model.Model;
import View.View;

public class ControllerJUnit extends TestCase {
	public SportScraperController controller;
	public Model model;
	public View view;
	private ArrayList<ArrayList<Double>> statList;
	private JTable table;
	
	public ControllerJUnit(String testName) {
		super(testName);
	}
	
	@Test
	public void testGetModel() {
		Model getModel = controller.getModel();
		assertEquals(model, getModel);
	}
	
	@Test
	public void testSetModel() {
		Model setModel = model;
		controller.setModel(model);
		assertEquals(model, setModel);
	}
	
	@Test
	public void testGetView() {
		View getView = controller.getView();
		assertEquals(view, getView);
	}
	
	@Test
	public void testSetView() {
		View setView = view;
		controller.setView(view);
		assertEquals(view, setView);
	}
	
	@Test
	public void testNHLGetSelection() {
		String qCommand = "NHL Stats";
		controller.getSelection(qCommand);
		String qTest = controller.qCommand;
		assertEquals(qTest, qCommand);
	}
	
	@Test
	public void testMLBGetSelection() {
		String qCommand = "MLB Stats";
		controller.getSelection(qCommand);
		String qTest = controller.qCommand;
		assertEquals(qTest, qCommand);
	}
	
	@Test
	public void testNFLGetSelection() {
		String qCommand = "NFL Stats";
		controller.getSelection(qCommand);
		String qTest = controller.qCommand;
		assertEquals(qTest, qCommand);
	}
	
	@Test
	public void testFailGetSelection() {
		String qCommand = "WNBA Stats";
		controller.getSelection(qCommand);
		String qTest = controller.qCommand;
		assertNull(qTest);
	}
	
	@Test
	public void testPrintStats() {
		controller.printStats(statList);
		assertEquals(table, controller.testTable);
	}
}