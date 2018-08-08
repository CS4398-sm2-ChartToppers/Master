package Controller;

import java.util.ArrayList;
import javax.swing.*;

import Model.Model;
import Model.RetrieveRS;
import View.SportScraperView;
import View.View;

public class SportScraperController implements Controller{
	private Model model;
	private View view;
	
	public SportScraperController() {
		setModel(new RetrieveRS());
		setView(new SportScraperView(this, (RetrieveRS)getModel()));
	}
	
	public JTable testTable;
	public String qCommand;
	public Model getModel() { return this.model; }
	public void setModel(Model model) { this.model = model; }
	public View getView() { return this.view; }
	public void setView(View view) { this.view = view; }
	
	public void getSelection(String sport) {
		switch(sport) {
			case "NHL Stats" : 
				qCommand = sport;
				model.execute(sport);
				break;
			case "MLB Stats" :
				qCommand = sport;
				model.execute(sport);
				break;
			case "NFL Stats" : 
				qCommand = sport;
				model.execute(sport);
				break;
			default :
				break;
		}
	}
	
	public void printStats(ArrayList<ArrayList<Double>> statList) {
		JTable tempTable = new JTable();
		testTable = tempTable;
	}
}