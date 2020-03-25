/* File:
 * 	MainWindow.java
 * Developers:
 * 	Amrin, Joe, Kelly, Dylan, Connor
 * Description:
 * 	Gui app manager
 */
package edu.csus.csc131.euc;

import javax.swing.*;

public class MainWindow {
	public static void createWindow() {
		JFrame f = new JFrame();
		
		JTextField hourlyUsage;
		hourlyUsage = new JTextField("Enter Hours Here");
		
		JButton button;
		button = new JButton("Click?");
		
		button.setBounds(100, 100, 100, 100);
		
		hourlyUsage.setBounds(0, 0, 100, 25);
		
		f.add(button);
		f.add(hourlyUsage);
		f.setLayout(null);
		f.setSize(700, 700);
		f.setVisible(true);
	}
}
