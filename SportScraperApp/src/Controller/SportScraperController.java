package Controller;

import java.util.ArrayList;
import javax.swing.*;

import Model.Model;
import Model.RetrieveRS;
import Model.StoreDB;
import View.SportScraperView;
import View.View;

/**
 * Allows initialization of controller that is connected to a model and a view
 * that are associated with the same qCommand, dynamic change of view and/or model, 
 * retrieval of model and/or view, execution of model, and printing of statistics.
 */
public class SportScraperController implements Controller {
	public JTable testTable;
	public String qCommand;
	private Model model;
	private View view;
	
	/**
	 * Initializes SportScraperController object in association with a particular qCommand.
	 */
	public SportScraperController() {
		setModel(new RetrieveRS());
		setView(new SportScraperView(this, (RetrieveRS)getModel()));
	}
	
	/**
	 * Facilitates retrieval of currently associated model.
	 * 
	 * @return			Returns model
	 */
	public Model getModel() { return this.model; }
	
	/**
	 * Facilitates switching of currently associated model.
	 * 
	 * @param model		Model that is being switched to
	 */
	public void setModel(Model model) { this.model = model; }
	
	/**
	 * Facilitates retrieval of currently associated view.
	 * 
	 * @return			Returns view
	 */
	public View getView() { return this.view; }
	
	/**
	 * Facilitates switching of currently associated view.
	 * 
	 * @param view		View that is being switched to
	 */
	public void setView(View view) { this.view = view; }
	
	/**
	 * Executes the model according to the associated qCommand.
	 * 
	 * @param sport		String casting of the current qCommand
	 */
	public void getSelection(String sport) {
		switch(sport) {
			case "NHL Stats" : 
				qCommand = sport;
				view.setTableModel(StoreDB.QueryToTableModel("SELECT * FROM NHL ORDER BY \"W Wins\" DESC;"));
				break;
			case "MLB Stats" : 
				qCommand = sport;
				view.setTableModel(StoreDB.QueryToTableModel("SELECT * FROM MLB ORDER BY \"W Wins\" DESC;"));
				break;
			case "NFL Stats" : 
				qCommand = sport;
				view.setTableModel(StoreDB.QueryToTableModel("SELECT * FROM NFL ORDER BY \"W Wins\" DESC;"));
				break;
			case "NBA Stats" : 
				qCommand = sport;
				view.setTableModel(StoreDB.QueryToTableModel("SELECT * FROM NBA ORDER BY \"W Wins\" DESC;"));
				break;
			case "College Football Stats" : 
				qCommand = sport;
				view.setTableModel(StoreDB.QueryToTableModel("SELECT * FROM College_Football ORDER BY \"W Wins\" DESC;"));
				break;
			default :
				break;
		}
	}
	
	/**
	 * Initializes a table for holding the retrieved statistics, that can be accessed locally.
	 * 
	 * @param statList	The retrieved statistics
	 */
	public void printStats(ArrayList<ArrayList<Double>> statList) {
		JTable tempTable = new JTable();
		testTable = tempTable;
	}
}