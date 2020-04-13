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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public void createWindow() {
	    
	    //long time = System.currentTimeMillis();
	    //SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss z");
	    
		// Create new object for Window Frame

		JFrame mainwindow = new JFrame("Electricity Project");

		/*
		 * Testing if Scroll Pane will work out I am still not so sure lol but we shall
		 * see
		 */
		JTextArea textArea = new JTextArea(20, 20);
		JScrollPane scrollableTextArea = new JScrollPane(textArea);

		scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		;

		scrollableTextArea.setBounds(20, 20, 330, 200);

		mainwindow.add(scrollableTextArea);

		// #####################################################
		// ################### Text Fields #####################
		// #####################################################

		// Create new Text Field Object for daily electricity Usage
		JTextField dailyUsageText;
		dailyUsageText = new JTextField("");

		// Create new Text Field Object for daily electricity rate
		JTextField dailyRateText;
		dailyRateText = new JTextField("");

		// Create new Text Field Object for total electricity usage
		JTextField totalUsageText;
		totalUsageText = new JTextField("");

		// Create new Text Field Object for total electricity rate
		JTextField totalRateText;
		totalRateText = new JTextField("");
		
		//Create new Text Field Object for current time
		//JTextField currentTimeTF;
		//currentTimeTF = new JTextField(formatter.format(time));

		// Set bounds and location of text fields
		dailyUsageText.setBounds(100, 260, 100, 25);
		dailyRateText.setBounds(250, 260, 100, 25);
		totalUsageText.setBounds(100, 290, 100, 25);
		totalRateText.setBounds(250, 290, 100, 25);
		//currentTimeTF.setBounds(300, 260, 100, 25);

		// Add Text Objects to Window UI
		mainwindow.add(dailyUsageText);
		mainwindow.add(dailyRateText);
		mainwindow.add(totalUsageText);
		mainwindow.add(totalRateText);

		// #####################################################
		// ################### BUTTONS #########################
		// #####################################################

		JButton enterHourlyUsageB;
		enterHourlyUsageB = new JButton("Enter Hourly Usage");

		JButton enterHourlyRatesB;
		enterHourlyRatesB = new JButton("Enter Hourly Rates");

		JButton readFromFileB;
		readFromFileB = new JButton("Read Usage from File");

		JButton calculateB;
		calculateB = new JButton("Calculate");

		// Set bounds and location of buttons
		enterHourlyRatesB.setBounds(35, 330, 300, 25);
		enterHourlyUsageB.setBounds(35, 380, 300, 25);
		readFromFileB.setBounds(35, 430, 300, 25);
		calculateB.setBounds(35, 480, 300, 25);

		// Add Button Objects to Window UI
		mainwindow.add(enterHourlyRatesB);
		enterHourlyRatesB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
			ReadRatesFromFilePrompt r = new ReadRatesFromFilePrompt();
			}
		});
		mainwindow.add(enterHourlyUsageB);
		mainwindow.add(readFromFileB);
		mainwindow.add(calculateB);

		// #####################################################
		// ################### LABELS #########################
		// #####################################################
		JLabel dailyUsage;
		dailyUsage = new JLabel("Daily Usage:");

		JLabel totalUsage;
		totalUsage = new JLabel("Total Usage:");

		JLabel kilowattLabel;
		kilowattLabel = new JLabel("KW/h");

		JLabel kilowattLabel2;
		kilowattLabel2 = new JLabel("KW/h");

		JLabel dollarSign1;
		dollarSign1 = new JLabel("$");

		JLabel dollarSign2;
		dollarSign2 = new JLabel("$");

		dailyUsage.setBounds(20, 260, 200, 25);
		totalUsage.setBounds(20, 290, 200, 25);
		kilowattLabel.setBounds(200, 260, 50, 25);
		kilowattLabel2.setBounds(200, 290, 50, 25);
		dollarSign1.setBounds(350, 260, 50, 25);
		dollarSign2.setBounds(350, 290, 50, 25);

		mainwindow.add(dailyUsage);
		mainwindow.add(totalUsage);
		mainwindow.add(kilowattLabel);
		mainwindow.add(kilowattLabel2);
		mainwindow.add(dollarSign1);
		mainwindow.add(dollarSign2);

		// #####################################################
		// ################### Main Window #####################
		// #####################################################

		// Set properties of the created window
		mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainwindow.setLayout(null);
		mainwindow.setSize(400, 600);
		mainwindow.setVisible(true);
	}
}
