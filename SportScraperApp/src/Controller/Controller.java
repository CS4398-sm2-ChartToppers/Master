package Controller;

import Model.Model;
import View.View;

public interface Controller {
	View getView();
	void setView(View view);
	Model getModel();
	void setModel(Model model);
	void getSelection(String sport);
}
