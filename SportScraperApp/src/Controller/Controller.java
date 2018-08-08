package Controller;

import Model.Model;
import View.View;

/**
 * Interface for the SportScraperController class.
 */
public interface Controller {
	View getView();
	void setView(View view);
	
	Model getModel();
	void setModel(Model model);
	
	void getSelection(String sport);
}