/* File:
 * 	MainWindow.java
 * Developers:
 * 	Amrin, Joe, Kelly, Dylan, Connor
 * Description:
 * 	Gui app manager
 */
package edu.csus.csc131.euc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


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
	//number of days (SHOULD BE CHANGED BY ADD DAYS BUTTON)
	int numOfDays = 4;
	
	public void createWindow() {  
		
		//Create new object for Window Frame
		JFrame mainwindow = new JFrame("Electricity Project");
		
		//Defaulted grid rows # of 1 because it cannot be 0
		int gridRows = 1;
		
		//create a gridLayout object in order to dynamically change rows
		GridLayout gridLayout = new GridLayout(0, gridRows);
		
		//create new scroll pane with grid layout to hold each Day in a Row
		JPanel scrollPanel = new JPanel(gridLayout);
		JScrollPane scrollableTextArea = new JScrollPane(scrollPanel);
		
		//scroll bars created and set to show up AS NEEDED
		scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//set the size of the scroll pane
		scrollableTextArea.setBounds(20, 20, 330, 200);
		
		
		//creating buttons depending on how many days have been added to the scrollPane
		Button buttons[] = new Button[numOfDays];
		
		//for each day create a button (May need to change)
		for(int i = 0; i < numOfDays; i++)
		{
			//create button to add with title of the Date
			buttons[i] = new Button("Jan " + (i + 1) + ", 2020"); //(DATE NEEDS TO BE MADE BASED ON ACTUAL DATE)
			
			//increase amount of rows to add a Day
			gridRows++;
			
			//add button to grid slot
			scrollPanel.add(buttons[i]);
		}
		
		//add scroll pane to the main window
		mainwindow.add(scrollableTextArea);

		
		//#####################################################
		//################### Text Fields #####################
		//#####################################################

		//Create new Text Field Object for daily electricity Usage
		JTextField dailyUsageText = new JTextField("");
		
		//Create new Text Field Object for daily electricity rate
		JTextField dailyRateText = new JTextField("");
		
		//Create new Text Field Object for total electricity usage
		JTextField totalUsageText = new JTextField("");
		
		//Create new Text Field Object for total electricity rate
		JTextField totalRateText = new JTextField("");
		
		
		//Set bounds and location of text fields
		dailyUsageText.setBounds(100, 260, 100, 25);
		dailyRateText.setBounds(250, 260, 100, 25);
		totalUsageText.setBounds(100, 290, 100, 25);
		totalRateText.setBounds(250, 290, 100, 25);
		
		//Add Text Objects to Window UI
		mainwindow.add(dailyUsageText);
		mainwindow.add(dailyRateText);
		mainwindow.add(totalUsageText);
		mainwindow.add(totalRateText);

		//#####################################################
		//################### BUTTONS #########################
		//#####################################################
		
		JButton addDayB = new JButton ("Add a Day");
		
		JButton enterHourlyRatesB = new JButton("Enter Hourly Rates");
		
		JButton enterHourlyUsageB = new JButton("Enter Hourly Usage");
		
		JButton readFromFileB = new JButton("Read Usage from File");
		
		JButton calculateB = new JButton ("Calculate");
		
		//Set bounds and location of buttons
		addDayB.setBounds(20, 220, 100, 25);
		enterHourlyRatesB.setBounds(35, 330, 300, 25);
		enterHourlyUsageB.setBounds(35, 380, 300, 25);
		readFromFileB.setBounds(35, 430, 300, 25);
		calculateB.setBounds(35, 480, 300, 25);
		
		//Add Button Objects to Window UI
		mainwindow.add(addDayB);
		mainwindow.add(enterHourlyRatesB);
		mainwindow.add(enterHourlyUsageB);
		mainwindow.add(readFromFileB);
		mainwindow.add(calculateB);
		
		//#####################################################
		//################### Action Listeners ################
		//#######################(Buttons)#####################
		
		//AddDay button action listener
		addDayB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				numOfDays++;
				System.out.println(numOfDays);
			}
	
		});
		
		//Hourly Rates button action listener
		enterHourlyRatesB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				//What will occur when the button is clicked
			}
		});
		
		//Hourly Usage button action listener
		enterHourlyUsageB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				//What will occur when the button is clicked
			}
		});
		
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
					
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}
				else
				{
					System.out.println("Failed to select a file or choose an approved file.");
				}
			}
		});
		
		calculateB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				//When the calculate button is clicked
			}
		});
		
		
		
		//#####################################################
		//################### LABELS ##########################
		//#####################################################
		
		JLabel dailyUsage = new JLabel("Daily Usage:");
		
		JLabel totalUsage = new JLabel("Total Usage:");
		
		JLabel kilowattLabel = new JLabel("KW/h");
		
		JLabel kilowattLabel2 = new JLabel("KW/h");
		
		JLabel dollarSign1 = new JLabel ("$");
		
		JLabel dollarSign2 = new JLabel ("$");
		
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
		
		//#####################################################
		//################### Main Window #####################
		//#####################################################
		
		//Set properties of the created window
		mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainwindow.setLayout(null);
		mainwindow.setSize(400, 600);
		mainwindow.setVisible(true);
	}
}