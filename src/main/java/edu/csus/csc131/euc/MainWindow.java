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
	public void createWindow() {  
		
		//Create new object for Window Frame
		JFrame mainwindow = new JFrame("Electricity Project");
		
		
		/*  Testing if Scroll Pane will work out
		 *  I am still not so sure lol but we
		 *  shall see
		 */
		JTextArea textArea = new JTextArea(20, 20);
		JScrollPane scrollableTextArea = new JScrollPane(textArea);
		
		scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);;
		
		scrollableTextArea.setBounds(20, 20, 330, 200);
		
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
		
		JButton enterHourlyRatesB = new JButton("Enter Hourly Rates");
		
		JButton enterHourlyUsageB = new JButton("Enter Hourly Usage");
		
		JButton readFromFileB = new JButton("Read Usage from File");
		
		JButton calculateB = new JButton ("Calculate");
		
		//Set bounds and location of buttons
		enterHourlyRatesB.setBounds(35, 330, 300, 25);
		enterHourlyUsageB.setBounds(35, 380, 300, 25);
		readFromFileB.setBounds(35, 430, 300, 25);
		calculateB.setBounds(35, 480, 300, 25);
		
		//Add Button Objects to Window UI
		mainwindow.add(enterHourlyRatesB);
		mainwindow.add(enterHourlyUsageB);
		mainwindow.add(readFromFileB);
		mainwindow.add(calculateB);
		
		//#####################################################
		//################### Action Listeners ################
		//#######################(Buttons)#####################
		
		enterHourlyRatesB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				//What will occur when the button is clicked
			}
		});
		
		enterHourlyUsageB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				//What will occur when the button is clicked
			}
		});
		
		readFromFileB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				//Open a file browser, save file path
				JFileChooser fileChooser = new JFileChooser();
				
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(mainwindow);
				if (result == JFileChooser.APPROVE_OPTION) {
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
				//What will occur when the button is clicked
				int dailyUsageAmount = Integer.parseInt(dailyUsageText.getText());
				int dailyRate = Integer.parseInt(dailyRateText.getText());
				
				System.out.println("The Daily Usage is: " + dailyUsageAmount + " kw/h");
				System.out.println("The Daily Rate is: " + dailyRate + " kw/h");

				
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