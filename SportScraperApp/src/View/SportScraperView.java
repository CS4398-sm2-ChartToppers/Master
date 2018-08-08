package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Controller.Controller;
import Controller.SportScraperController;
import Model.Model;

/**
 * Displays the GUI for viewing data from the SQL database and allows changing and
 * retrieving of model and/or controller, and monitors for user actions to update
 * the view.
 */
public class SportScraperView extends JFrame implements View, ActionListener{
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private Model model;
	
	/**
	 * Initializes the GUI at 200x200px with flow layout and closable, with the option
	 * to view NHL, MLB, or NFL stats via selection from a dropdown menu. 
	 * 
	 * @param controller	The associated controller
	 * @param model			The associated model
	 */
	public SportScraperView(Controller controller, Model model) {
		super("Sport Scraper 1.0");
		
		setController(controller);
		setModel(model);
		
		setLayout(new FlowLayout());
		setSize(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		String[] sportList = {"NHL Stats", "MLB Stats", "NFL Stats"};
		JComboBox<String> sportsDropDown = new JComboBox<String>(sportList);
		sportsDropDown.setSelectedItem(null);
		sportsDropDown.addActionListener(this);
		
		panel.add(sportsDropDown);
		add(panel);
		setVisible(true);
	}
	
	/**
	 * Facilitates switching of currently associated model.
	 * 
	 * @param model			Model that is being switched to
	 */
	public void setModel(Model model) { this.model = model; }
	
	/**
	 * Facilitates retrieval of currently associated model.
	 * 
	 * @return 				Returns model
	 */
	public Model getModel() { return this.model; }
	
	/**
	 * Facilitates switching of currently associated controller.
	 * 
	 * @param model			Controller that is being switched to
	 */
	public void setController(Controller controller) { this.controller = controller; }
	
	/**
	 * Facilitates retrieval of currently associated controller.
	 * 
	 * @return 				Returns controller
	 */
	public Controller getController() { return this.controller; }
	
	/**
	 * Listens for statistics selection and changes view based on selection
	 * 
	 * @param e				User selection performed
	 */
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox<String> temp = (JComboBox) e.getSource();
	    String newSelection = (String) temp.getSelectedItem();
	    ((SportScraperController)getController()).getSelection(newSelection);
	}
}