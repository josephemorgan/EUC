/* File:
 * 	MainWindow.java
 * Developers:
 * 	Amrin, Joe, Kelly, Dylan, Connor
 * Description:
 * 	Gui app manager
 */
package edu.csus.csc131.euc;

import javax.swing.*;
import java.text.NumberFormat;
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
		JLabel dailyUsageValue = new JLabel ("0 kW/h", SwingConstants.RIGHT);
		JLabel dailyCostValue = new JLabel ("$0.00", SwingConstants.RIGHT);
		JLabel totalUsageValue = new JLabel ("0 kW/h", SwingConstants.RIGHT);
		JLabel totalCostValue = new JLabel ("$0.00", SwingConstants.RIGHT);


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

		//Set bounds and location of buttons
		addDayB.setBounds(25, 85, 165, 25);
		removeDayB.setBounds(195, 85, 165, 25);
		enterHourlyRatesB.setBounds(25, 120, 335, 25);


		//Add Button Objects & Action Listeners to Window UI

		//add day
		mainwindow.add(addDayB); // add day button added
		addDayB.addActionListener(new ActionListener() { //action listener for button being clicked
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AddDayDialog a = new AddDayDialog(mainwindow, listOfDays);

				if (listOfDays.getNumOfDays() != 0) {
					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
					dailyUsageValue.setText(String.valueOf(listOfDays.getDay(listOfDays.getNumOfDays() - 1).getDailyUsage()) + " kW/h");
					dailyCostValue.setText(currencyFormatter.format(listOfDays.getDay(listOfDays.getNumOfDays() - 1).getDailyCost()));
					totalUsageValue.setText(String.valueOf(listOfDays.getTotalUsage()) + " kW/h");
					totalCostValue.setText(currencyFormatter.format(listOfDays.getTotalCost()));
					comboBox.addItem(listOfDays.getDay(listOfDays.getNumOfDays() - 1).getDateAsString());
					mainwindow.validate();
					mainwindow.repaint();
				}
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
			    JDialog d = new EnterRatesDialog(mainwindow, listOfDays);
			}
		});

		//#####################################################
		//################### LABELS ##########################
		//#####################################################

		dailyUsage.setBounds(20, 155, 200, 25);
		totalUsage.setBounds(20, 185, 200, 25);
		dailyUsageValue.setBounds(125, 155, 100, 25);
		dailyCostValue.setBounds(250, 155, 100, 25);
		totalUsageValue.setBounds(125, 185, 100, 25);
		totalCostValue.setBounds(250, 185, 100, 25);


		mainwindow.add(dailyUsage);
		mainwindow.add(totalUsage);
		mainwindow.add(totalUsageValue);
		mainwindow.add(totalCostValue);
		mainwindow.add(dailyCostValue);
		mainwindow.add(dailyUsageValue);



		//must be at the end to avoid bug
		mainwindow.setVisible(true);
	}

}
