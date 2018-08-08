package View;

import junit.framework.TestCase;
import org.junit.*;

import Controller.Controller;
import Model.Model;

/**
 * Tests valid and invalid (when applicable) implementations of SportScraperView methods.
 */
public class ViewJUnit extends TestCase {
	private Controller controller;
	private Model model;
	
	/**
	 * Initializes view JUnit test.
	 * 
	 * @param testName		Name of the test, for clarity during runtime
	 */
	public ViewJUnit(String testName) { super(testName); }
	
	/**
	 * Tests successful initialization of the controller by ensuring that the model and
	 * controller are set properly.
	 */
	@Test
	public void testInitView() {
		SportScraperView view = new SportScraperView(controller, model);
		Controller getController = view.getController();
		Model getModel = view.getModel();
		
		assertEquals(getController, controller);
		assertEquals(getModel, model);
	}
	
	/**
	 * Tests successful retrieval of the model by ensuring that the retrieved model is
	 * the same as the set model.
	 */
	@Test
	public void testGetModel() {
		SportScraperView view = new SportScraperView(controller, model);
		Model getModel = view.getModel();
		
		assertEquals(getModel, model);
	}
	
	/**
	 * Tests successful setting of the model by initializing the view and ensuring
	 * that the appropriate model has been set.
	 */
	@Test
	public void testSetModel() {
		@SuppressWarnings("unused")
		SportScraperView view = new SportScraperView(controller, model);
		Model setModel = model;
		
		assertEquals(model, setModel);
	}
	
	/**
	 * Tests successful retrieval of the controller by ensuring that the retrieved controller is
	 * the same as the set controller.
	 */
	@Test
	public void testGetController() {
		SportScraperView view = new SportScraperView(controller, model);
		Controller getController = view.getController();
		
		assertEquals(getController, controller);
	}
	
	/**
	 * Tests successful setting of the controller by initializing the view and ensuring
	 * that the appropriate controller has been set.
	 */
	@Test
	public void testSetController() {
		@SuppressWarnings("unused")
		SportScraperView view = new SportScraperView(controller, model);
		Controller setController = controller;
		
		assertEquals(controller, setController);
	}
}