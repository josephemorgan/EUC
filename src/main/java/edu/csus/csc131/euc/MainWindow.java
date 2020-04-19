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

	//TODO: UNCOMMENT THIS
	//private Week week = new Week();

	//TODO: probably remove with UI changes
	//number of days (SHOULD BE CHANGED BY ADD DAYS BUTTON)
	int numOfDays = 4; //test

	public void createWindow() {

		//TODO: REMOVE THIS CODE
		//THIS IS TEMPORARY CODE FOR TESTING
		double[] summerRates = new double[24];
		double[] winterRates = new double[24];

		for(int i = 0; i < summerRates.length; i++){
			summerRates[i] = 4.0;
		}
		for(int i = 0; i < winterRates.length; i++){
			winterRates[i] = 2.0;
		}

		Rates summer = new Rates(summerRates);
		Rates winter = new Rates(winterRates);

		Week week = new Week(summer, winter);
		//THIS IS TEMPORARY CODE FOR TESTING
		//TODO: REMOVE THIS CODE

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

		String dayEntry[] = {"1/1/2020", "1/2/2020", "1/3/2020"};
		JComboBox comboBox = new JComboBox(dayEntry);

		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 20));

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
				JFrame a = new AddDayDialog(week);

			}
		});

		//remove day
		mainwindow.add(removeDayB);// remove day button added
		removeDayB.addActionListener(new ActionListener() { //action listener for button being clicked
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				JFrame a = new RemoveDayDialog(week);
			}
		});

		//hourly rates
		mainwindow.add(enterHourlyRatesB);//hourly rates button added
		enterHourlyRatesB.addActionListener(new ActionListener() { //action listener for button being clicked
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
			    JFrame f = new EnterRatesDialog();
			}
		});

		mainwindow.add(readFromFileB);

		//#####################################################
		//################### LABELS ##########################
		//#####################################################

		JLabel dailyUsage = new JLabel("Daily Usage:");
		JLabel totalUsage = new JLabel("Total Usage:");
		JLabel kilowattLabel = new JLabel("KW/h");
		JLabel kilowattLabel2 = new JLabel("KW/h");
		JLabel dollarSign1 = new JLabel ("$");
		JLabel dollarSign2 = new JLabel ("$");
		JLabel dailyUsageLabel = new JLabel ("");
		JLabel dailyCostLabel = new JLabel ("");
		JLabel totalUsageLabel = new JLabel ("", SwingConstants.RIGHT);
		JLabel totalCostLabel = new JLabel ("", SwingConstants.RIGHT);


		dailyUsage.setBounds(20, 155, 200, 25);
		totalUsage.setBounds(20, 185, 200, 25);
		kilowattLabel.setBounds(200, 155, 50, 25);
		kilowattLabel2.setBounds(200, 185, 50, 25);
		dollarSign1.setBounds(350, 155, 50, 25);
		dollarSign2.setBounds(350, 185, 50, 25);
		dailyUsageLabel.setBounds(100, 155, 100, 25);
		dailyCostLabel.setBounds(250, 155, 100, 25);
		totalUsageLabel.setBounds(100, 185, 100, 25);
		totalCostLabel.setBounds(250, 185, 100, 25);


		mainwindow.add(dailyUsage);
		mainwindow.add(totalUsage);
		mainwindow.add(kilowattLabel);
		mainwindow.add(kilowattLabel2);
		mainwindow.add(dollarSign1);
		mainwindow.add(dollarSign2);
		mainwindow.add(totalUsageLabel);
		mainwindow.add(totalCostLabel);
		mainwindow.add(dailyCostLabel);
		mainwindow.add(dailyUsageLabel);

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
					week.fetchDayFromFile(selectedFile.getAbsolutePath());

					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}
				else
				{
					System.out.println("Failed to select a file or choose an approved file.");
				}
			}
		});

		//must be at the end to avoid bug
		mainwindow.setVisible(true);
	}

}
