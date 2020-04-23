/* File:
 * 	App.java
 * Developers:
 * 	Amrin, Joe, Kelly, Dylan, Connor
 * Description:
 * 	Main App file
 */
package edu.csus.csc131.euc;

import javax.swing.*;

public class App
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
             public void run() {
                MainWindow GUIWindow = new MainWindow();
                GUIWindow.createWindow();
            }
        });
    }
}
