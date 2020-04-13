package edu.csus.csc131.euc;

import javax.swing.*;
import java.awt.*;
import java.nio.ByteOrder;

public class EnterRatesDialog extends JFrame {
    private JPanel topPanel;
    private JPanel entryPanel;
    private JPanel bottomPanel;
    private JButton winterRatesButton;
    private JButton summerRatesButton;
    private JButton confirmButton;
    private JButton abortButton;

    public EnterRatesDialog() {
        super("Enter Rates");
        setLayout(new BorderLayout());

        topPanel = new JPanel();
        entryPanel = new WinterRatesPanel();
        bottomPanel = new JPanel();
        winterRatesButton = new JButton("Winter Rates");
        summerRatesButton = new JButton("Summer Rates");
        confirmButton = new JButton("Confirm");
        abortButton = new JButton("Abort");

        topPanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new FlowLayout());

        topPanel.add(winterRatesButton);
        topPanel.add(summerRatesButton);

        bottomPanel.add(confirmButton);
        bottomPanel.add(abortButton);

        add(topPanel, BorderLayout.NORTH);
        add(entryPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }
}
