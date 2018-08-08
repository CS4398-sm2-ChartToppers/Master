package View;

import junit.framework.TestCase;
import org.junit.*;

import Controller.Controller;
import Model.Model;

public class ViewJUnit extends TestCase {
	private Controller controller;
	private Model model;
	
	public ViewJUnit(String testName) {
		super(testName);
	}
	
	@Test
	public void testInitView() {
		SportScraperView view = new SportScraperView(controller, model);
		Controller getController = view.getController();
		Model getModel = view.getModel();
		
		assertEquals(getController, controller);
		assertEquals(getModel, model);
	}
	
	@Test
	public void testGetModel() {
		SportScraperView view = new SportScraperView(controller, model);
		Model getModel = view.getModel();
		
		assertEquals(getModel, model);
	}
	
	@Test
	public void testFailGetModel() {
		SportScraperView view = new SportScraperView(controller, model);
		Model getModel = view.getModel();
		
		assertEquals(getModel, controller);
	}
	
	@Test
	public void testGetController() {
		SportScraperView view = new SportScraperView(controller, model);
		Controller getController = view.getController();
		
		assertEquals(getController, controller);
	}
	
	@Test
	public void testFailGetController() {
		SportScraperView view = new SportScraperView(controller, model);
		Controller getController = view.getController();
		
		assertEquals(getController, model);
	}
}