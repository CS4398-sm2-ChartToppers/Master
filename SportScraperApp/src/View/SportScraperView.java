package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Controller.Controller;
import Controller.SportScraperController;
import Model.Model;

public class SportScraperView extends JFrame implements View, ActionListener{
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private Model model;
	
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
	
	public void setModel(Model model) { this.model = model; }
	public Model getModel() { return this.model; }
	public void setController(Controller controller) { this.controller = controller; }
	public Controller getController() { return this.controller; }
	
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox<String> temp = (JComboBox) e.getSource();
	    String newSelection = (String) temp.getSelectedItem();
	    ((SportScraperController)getController()).getSelection(newSelection);
	}
}