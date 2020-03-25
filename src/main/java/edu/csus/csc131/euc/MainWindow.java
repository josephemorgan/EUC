/* File:
 * 	MainWindow.java
 * Developers:
 * 	Amrin, Joe, Kelly, Dylan, Connor
 * Description:
 * 	Gui app manager
 */
package edu.csus.csc131.euc;

import javax.swing.*;

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
	public static void createWindow() {
		//Create new object for Window Frame
		JFrame f = new JFrame();
		
		//Create new Text Field Object for electricty Usage
		JTextField electricityUsageTF;
		electricityUsageTF = new JTextField("Enter Hours Here");
		
		//Create new Text Field Object for electricity rate
		JTextField electricityRateTF;
		electricityRateTF = new JTextField("Enter Rates Here");
		
		//Create new Text Field Object for number of days
		JTextField numOfDaysTF;
		numOfDaysTF = new JTextField("Enter Days Here");
		
		
		//Create new Button Object for calculating daily rates and cost
		JButton calculateDailyB;
		calculateDailyB = new JButton("Calculate Daily");
		
		//Create new Button Object for calculating total rates and cost
		JButton calculateTotalB;
		calculateTotalB = new JButton("Calculate Total");
		
		//Set bounds and location of text fields
		electricityUsageTF.setBounds(0, 10, 100, 25);
		electricityRateTF.setBounds(101, 10, 100, 25);
		numOfDaysTF.setBounds(202, 10, 100, 25);
		
		//Set bounds and location of buttons
		calculateDailyB.setBounds(0, 45, 150, 25);
		calculateTotalB.setBounds(151, 45, 150, 25);
		
		//Add Text Objects to Window UI
		f.add(electricityUsageTF);
		f.add(electricityRateTF);
		f.add(numOfDaysTF);
		
		//Add Button Objects to Window UI
		f.add(calculateDailyB);
		f.add(calculateTotalB);
		
		f.setLayout(null);
		f.setSize(700, 700);
		f.setVisible(true);
	}
}
