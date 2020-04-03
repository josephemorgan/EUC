/* File:
 * 	MainWindow.java
 * Developers:
 * 	Amrin, Joe, Kelly, Dylan, Connor
 * Description:
 * 	Gui app manager
 */
package edu.csus.csc131.euc;

import javax.swing.*;
import java.util.ArrayList;

/* NEEDED:
 * Text Fields:
 * 		Electricity Rates [x]
 * 		Electricity Usage [x]
 * 		Days [x]
 * 
 * Buttons:
 * 		Calculate Daily Electrical Usage kWh / cost [x]
 * 		Calculate Total Electrical Usage kWh / cost [x]
 */

public class MainWindow {
	private ArrayList<Day> listOfDays = new ArrayList<Day> ();

    private JFrame mainFrame;

	private JTextField electricityUsageTF, electricityRateTF, numOfDaysTF;

	private JButton calculateDailyB, calculateTotalB;

	public void createWindow() {
		//Create new object for Window Frame
		mainFrame = new JFrame ("Electricity Project");
		//Create new Text Field Object for electricity Usage
		electricityUsageTF = new JTextField ("Enter Hours Here");
		//Create new Text Field Object for electricity rate
		electricityRateTF = new JTextField ("Enter Rates Here");
		//Create new Text Field Object for number of days
		numOfDaysTF = new JTextField ("Enter Days Here");
		//Create new Button Object for calculating daily rates and cost
		calculateDailyB = new JButton ("Calculate Daily");
		//Create new Button Object for calculating total rates and cost
		calculateTotalB = new JButton ("Calculate Total");
		//Set bounds and location of text fields
		electricityUsageTF.setBounds (0, 10, 100, 25);
		electricityRateTF.setBounds (101, 10, 100, 25);
		numOfDaysTF.setBounds (202, 10, 100, 25);
		//Set bounds and location of buttons
		calculateDailyB.setBounds (0, 45, 150, 25);
		calculateTotalB.setBounds (151, 45, 150, 25);
		//Add Text Objects to Window UI
		mainFrame.add (electricityUsageTF);
		mainFrame.add (electricityRateTF);
		mainFrame.add (numOfDaysTF);
		//Add Button Objects to Window UI
		mainFrame.add (calculateDailyB);
		mainFrame.add (calculateTotalB);
		//Set properties of the created window
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout (null);
		mainFrame.setSize (700, 700);
		mainFrame.setVisible (true);
	}
}
