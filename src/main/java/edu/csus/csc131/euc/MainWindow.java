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
import java.io.File;
import java.util.Arrays;


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

	private Week listOfDays = new Week();

	public void createWindow() {
		JLabel dailyUsage = new JLabel("Daily Usage:");
		JLabel totalUsage = new JLabel("Total Usage:");
		JLabel kilowattLabel = new JLabel("KW/h");
		JLabel kilowattLabel2 = new JLabel("KW/h");
		JLabel dollarSign1 = new JLabel ("$");
		JLabel dollarSign2 = new JLabel ("$");
		JLabel dailyUsageValue = new JLabel ("");
		JLabel dailyCostValue = new JLabel ("");
		JLabel totalUsageValue = new JLabel ("", SwingConstants.RIGHT);
		JLabel totalCostValue = new JLabel ("", SwingConstants.RIGHT);


		//#####################################################
		//################### Main Window #####################
		//#####################################################

		//Create new object for Window Frame
		JFrame mainwindow = new JFrame("Electricity Project");
		mainwindow.pack();

		//Set properties of the created window
		mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainwindow.setLayout(null);
		mainwindow.setSize(400, 260);
		mainwindow.setLocationRelativeTo(null);


		//centers the app when opened, allowing for dialogue to be center also
		mainwindow.setLocationRelativeTo(null);

		//#####################################################
		//################### Combo Box #######################
		//#####################################################

		String dayEntry[] = {"No days added yet"};
		JComboBox comboBox = new JComboBox(dayEntry);

		comboBox.setFont(new Font("Arial", Font.BOLD, 20));

		comboBox.setBounds(25, 25, 335, 50);
		mainwindow.add(comboBox);

		//#####################################################
		//################### BUTTONS #########################
		//#####################################################

		JButton addDayB = new JButton ("Add a Day");
		JButton removeDayB = new JButton ("Remove a Day");
		JButton enterHourlyRatesB = new JButton("Enter Hourly Rates");
		JButton readFromFileB = new JButton("Read Usage from File");

		//Set bounds and location of buttons
		addDayB.setBounds(25, 85, 165, 25);
		removeDayB.setBounds(195, 85, 165, 25);
		enterHourlyRatesB.setBounds(25, 120, 335, 25);
		readFromFileB.setBounds(35, 430, 300, 25);


		//Add Button Objects & Action Listeners to Window UI

		//add day
		mainwindow.add(addDayB); // add day button added
		addDayB.addActionListener(new ActionListener() { //action listener for button being clicked
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				JFrame a = new AddDayDialog(listOfDays);

			}
		});

		//remove day
		mainwindow.add(removeDayB);// remove day button added
		removeDayB.addActionListener(new ActionListener() { //action listener for button being clicked
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				JFrame a = new RemoveDayDialog(listOfDays);
			}
		});

		//hourly rates
		mainwindow.add(enterHourlyRatesB);//hourly rates button added
		enterHourlyRatesB.addActionListener(new ActionListener() { //action listener for button being clicked
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
			    JFrame f = new EnterRatesDialog(listOfDays);
			}
		});

		mainwindow.add(readFromFileB);

		//Read from file button action listener
		readFromFileB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//Open a file browser, save file path
				JFileChooser fileChooser = new JFileChooser();

				//default the directory to the users home directory
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

				//variable to store the result of the dialog menu
				int result = fileChooser.showOpenDialog(mainwindow);

				//if the result is an approved option (a file)
				if (result == JFileChooser.APPROVE_OPTION) {
					//new file object to hold the file
					File selectedFile = fileChooser.getSelectedFile();

					//Fetch data from file and add a day to week collection
					listOfDays.fetchDayFromFile(selectedFile.getAbsolutePath());
					dailyUsage.setText(listOfDays.getDay(0).toString());
				}
			}
		});

		//#####################################################
		//################### LABELS ##########################
		//#####################################################

		dailyUsage.setBounds(20, 155, 200, 25);
		totalUsage.setBounds(20, 185, 200, 25);
		kilowattLabel.setBounds(200, 155, 50, 25);
		kilowattLabel2.setBounds(200, 185, 50, 25);
		dollarSign1.setBounds(350, 155, 50, 25);
		dollarSign2.setBounds(350, 185, 50, 25);
		dailyUsageValue.setBounds(100, 155, 100, 25);
		dailyCostValue.setBounds(250, 155, 100, 25);
		totalUsageValue.setBounds(100, 185, 100, 25);
		totalCostValue.setBounds(250, 185, 100, 25);


		mainwindow.add(dailyUsage);
		mainwindow.add(totalUsage);
		mainwindow.add(kilowattLabel);
		mainwindow.add(kilowattLabel2);
		mainwindow.add(dollarSign1);
		mainwindow.add(dollarSign2);
		mainwindow.add(totalUsageValue);
		mainwindow.add(totalCostValue);
		mainwindow.add(dailyCostValue);
		mainwindow.add(dailyUsageValue);



		//must be at the end to avoid bug
		mainwindow.setVisible(true);
	}

}
