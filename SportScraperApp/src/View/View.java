package View;

import Controller.Controller;
import Model.Model;

/**
 * Interface for SportScraperView class.
 */
public interface View {
	Controller getController();
	void setController(Controller controller);
	
	Model getModel();
	void setModel(Model model);
}