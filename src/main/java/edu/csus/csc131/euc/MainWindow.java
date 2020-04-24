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

	private Week listOfDays;
	private JLabel dailyUsage;
	private JLabel totalUsage;
	private JLabel dailyUsageValue;
	private JLabel dailyCostValue;
	private JLabel totalUsageValue;
	private JLabel totalCostValue;
	private JButton addDayB;
	private JButton removeDayB;
	private JButton enterHourlyRatesB;
	private JButton helpButton;
	private String noDaysString = "Please Add a Day";
	private JComboBox addedDaysComboBox;
	private JFrame mainwindow;
	private ActionListener comboBoxActionListener;

	public void createWindow() {
		listOfDays = new Week();
		dailyUsage = new JLabel("Daily Usage:");
		totalUsage = new JLabel("Total Usage:");
		dailyUsageValue = new JLabel ("0 kW*h", SwingConstants.RIGHT);
		dailyCostValue = new JLabel ("$0.00", SwingConstants.RIGHT);
		totalUsageValue = new JLabel ("0 kW*h", SwingConstants.RIGHT);
		totalCostValue = new JLabel ("$0.00", SwingConstants.RIGHT);
		addedDaysComboBox = new JComboBox(new String[]{noDaysString});
		mainwindow = new JFrame("Electricity Usage Calculator");
		//#####################################################
		//################### Main Window #####################
		//#####################################################

		//Create new object for Window Frame
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

		addedDaysComboBox.setFont(new Font("Arial", Font.BOLD, 20));

		addedDaysComboBox.setBounds(25, 25, 335, 35);
		mainwindow.add(addedDaysComboBox);

		//#####################################################
		//################### BUTTONS #########################
		//#####################################################

		addDayB = new JButton ("Add a Day");
		removeDayB = new JButton ("Remove Selected Day");
		enterHourlyRatesB = new JButton("Enter Hourly Rates");
		helpButton = new JButton("?");

		//Set bounds and location of buttons
		addDayB.setBounds(25, 85, 165, 25);
		removeDayB.setBounds(195, 85, 165, 25);
		enterHourlyRatesB.setBounds(25, 120, 285, 25);
		helpButton.setBounds(315, 120, 45, 25);


		mainwindow.add(helpButton);
		//Add Button Objects & Action Listeners to Window UI

		//add day
		mainwindow.add(addDayB); // add day button added
		addDayB.addActionListener(new ActionListener() { //action listener for button being clicked
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				AddDayDialog a = new AddDayDialog(mainwindow, listOfDays);
				refreshMainWindow();
			}
		});

		//remove day
		mainwindow.add(removeDayB);// remove day button added
		removeDayB.addActionListener(new ActionListener() { //action listener for button being clicked
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				String itemToRemove = addedDaysComboBox.getSelectedItem().toString();
				if (!addedDaysComboBox.getSelectedItem().equals(noDaysString)) {
					listOfDays.removeDay(itemToRemove);
					refreshMainWindow();
				} else {
					JOptionPane.showMessageDialog(mainwindow, "There are no days to remove!");
				}
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

		comboBoxActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				int selectedIndex = addedDaysComboBox.getSelectedIndex();
				if (!listOfDays.getDay(selectedIndex).getDateAsString().equals(noDaysString)) {
					refreshUsageValues();
				}
			}
		};
		addedDaysComboBox.addActionListener(comboBoxActionListener);

		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String helpMessageString = "Thanks for using our Electricity Usage Calculator!\n\n" +
						"\tThe calculator comes loaded with some default rates for summer and winter, but if\n" +
						"you'd like to change either summer or winter rates, just click \"Set Usage Rates.\"\n\n" +
						"\tOnce you're happy with the usage rates, you can enter your daily usage by clicking\n" +
						"\"Add a Day.\" If you try to add a day for a date that's already been added, the new\n" +
						"day will replace the old one with the same date.\n\n" +
						"\tThe dropdown box at the top of the main window is where you can see a list of days\n" +
						"that you've entered usage information for. Select a day from the box, and the main\n" +
						"window will be updated with that day's usage in kilowatt-hours and the associated cost\n" +
						"in dollars, according to the rates that have been set.\n\n" +
						"\tIf you notice that you've made a mistake, you can select the incorrect day from the\n" +
						"drop down, and click \"Remove Selected Day.\"";
				JOptionPane.showMessageDialog(mainwindow, helpMessageString);
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

	private void refreshMainWindow() {
		refreshComboBox();
		refreshUsageValues();
		mainwindow.validate();
		mainwindow.repaint();
	}

	private void refreshComboBox() {
		addedDaysComboBox.removeActionListener(comboBoxActionListener);
		addedDaysComboBox.removeAllItems();
		if (listOfDays.getNumOfDays() == 0) {
			addedDaysComboBox.addItem(noDaysString);
			addedDaysComboBox.setSelectedIndex(0);
		} else {
			if (addedDaysComboBox.getItemAt(0) == noDaysString) {
				addedDaysComboBox.removeItem(noDaysString);
			}
			for (int i = 0; i < listOfDays.getNumOfDays(); i++) {
				addedDaysComboBox.addItem(listOfDays.getDay(i).getDateAsString());
			}
			addedDaysComboBox.setSelectedIndex(listOfDays.getNumOfDays() - 1);
		}
		addedDaysComboBox.addActionListener(comboBoxActionListener);
	}

	private void refreshUsageValues() {
		if (listOfDays.getNumOfDays() == 0) {
			dailyUsageValue.setText("0 kW*h");
			dailyCostValue.setText("$0.00");
			totalUsageValue.setText("0 kW*h");
			totalCostValue.setText("$0.00");
		} else {
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
			dailyUsageValue.setText(String.valueOf(listOfDays.getDay(addedDaysComboBox.getSelectedIndex()).getDailyUsage()) + " kW*h");
			dailyCostValue.setText(currencyFormatter.format(listOfDays.getDay(addedDaysComboBox.getSelectedIndex()).getDailyCost()));
			totalUsageValue.setText(String.valueOf(listOfDays.calculateTotalUsage()) + " kW*h");
			totalCostValue.setText(currencyFormatter.format(listOfDays.calculateTotalCost()));
		}
	}
}
