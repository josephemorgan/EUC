package edu.csus.csc131.euc;

import javax.swing.*;

public class MainWindow extends JFrame
{
	private int height = 810;
	private int width = 960;
	// Declare objects here

	MainWindow() {
	    initAndShowGUI();
	}

	private void initAndShowGUI()
	{
	    setSize(width, height);
		setTitle("Electricity Usage Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//initialize objects and add them here

		setVisible(true);
	}
}
