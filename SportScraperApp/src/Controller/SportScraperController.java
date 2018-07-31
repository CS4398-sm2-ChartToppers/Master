package Controller;
import java.util.ArrayList;
import javax.swing.*;

import Model.Model;
import Model.RetrieveRS;
import View.SportScraperView;
import View.View;

public class SportScraperController implements Controller{
	private View view;
	private Model model;
	
	public SportScraperController() {
		setModel(new RetrieveRS());
		setView(new SportScraperView(this, (RetrieveRS)getModel()));
	}
	
	public View getView() { return this.view; }
	public Model getModel() { return this.model; }
	public void setView(View view) { this.view = view; }
	public void setModel(Model model) { this.model = model; }
	
	public void getSelection(String sport) {
		switch(sport) {
			case "NHL Stats" : 
				model.execute(sport);
				break;
			case "MLB Stats" : 
				model.execute(sport);
				break;
			case "NFL Stats" : 
				model.execute(sport);
				break;
			default :
				break;
		}
	}
	
	public void printStats(ArrayList<ArrayList<Double>> statList) {
		SportScraperView tempView = (SportScraperView) getView();
		SportScraperView tempPane = (SportScraperView) tempView.getContentPane();
		JTable tempTable = new JTable();
		/*
		 * From here one would extract the values from our data structure
		 * and add them to the JTable, and then adding that onto the container.
		 * 
		 * tempPane is assigned to the content pane, which is how the JTable can be
		 * added.
		 */
	}
}