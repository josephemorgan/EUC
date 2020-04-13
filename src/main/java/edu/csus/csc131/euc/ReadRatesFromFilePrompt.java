package edu.csus.csc131.euc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadRatesFromFilePrompt extends JFrame implements ActionListener{
    private JButton readFromFileButton;
    private JButton enterManuallyButton;

    public ReadRatesFromFilePrompt() {
        super("Read From File?");

        setLayout(new FlowLayout());

        readFromFileButton = new JButton("Read rates from a file");
        enterManuallyButton = new JButton("Enter rates manually");

        this.add(readFromFileButton);
        this.add(enterManuallyButton);

        readFromFileButton.addActionListener(this);
        enterManuallyButton.addActionListener(this);

        this.pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == readFromFileButton) {
            System.out.println("Clicked readFromFileButton");
        } else if (source == enterManuallyButton) {
            setVisible(false);
            EnterRatesDialog d = new EnterRatesDialog();
        }
    }
}
