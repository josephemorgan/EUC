package edu.csus.csc131.euc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EnterRatesDialog extends JFrame implements ActionListener {
    private Week listOfDays;
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    private WinterRatesPanel winterPanel;
    private SummerRatesPanel summerPanel;
    private JButton winterRatesButton;
    private JButton summerRatesButton;
    private JButton confirmButton;
    private JButton abortButton;
    private Season seasonBeingEntered;

    public EnterRatesDialog(Week listOfDays) {
        super("Enter Rates");
        this.listOfDays = listOfDays;
        setLayout(new BorderLayout());

        topPanel = new JPanel();
        midPanel = new JPanel();
        bottomPanel = new JPanel();
        winterPanel = new WinterRatesPanel();
        summerPanel = new SummerRatesPanel();
        winterRatesButton = new JButton("Winter Rates");
        summerRatesButton = new JButton("Summer Rates");
        confirmButton = new JButton("Confirm");
        abortButton = new JButton("Back");
        seasonBeingEntered = Season.WINTER;

        topPanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new FlowLayout());

        topPanel.add(winterRatesButton);
        topPanel.add(summerRatesButton);

        midPanel.add(winterPanel);

        bottomPanel.add(confirmButton);
        bottomPanel.add(abortButton);

        add(topPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        winterRatesButton.addActionListener(this);
        summerRatesButton.addActionListener(this);
        confirmButton.addActionListener(this);
        abortButton.addActionListener(this);

        pack();
        setVisible(true);
    }

    private void readComboBoxes() {
        Rates temp = new Rates();

        if (seasonBeingEntered == Season.WINTER) {
            if (validateWinterComboBox()) {
                // Break this up into 3 loops because there are 3 blocks
                for (int i = 0; i < winterPanel.getOffPeakToBox(); i++) {
                    temp.setRates(i, winterPanel.getOffPeakRatesField());
                }
                for (int i = winterPanel.getOffPeakToBox(); i < winterPanel.getOnPeakToBox(); i++) {
                    temp.setRates(i, winterPanel.getOnPeakRatesField());
                }
                for (int i = winterPanel.getOnPeakToBox(); i < 24; i++) {
                    temp.setRates(i, winterPanel.getOffPeakRatesField());
                }
                listOfDays.setWinterRates(temp);
            }
        } else if (seasonBeingEntered == Season.SUMMER) {
            if (validateSummerComboBox()) {
                // Break this up into 4 loops because there are 4 blocks
                for (int i = 0; i < summerPanel.getOffPeakToBox(); i++) {
                    temp.setRates(i, summerPanel.getOffPeakRatesField());
                }
                for (int i = summerPanel.getOffPeakToBox(); i < summerPanel.getMidPeakToBox(); i++) {
                    temp.setRates(i, summerPanel.getMidPeakRatesField());
                }
                for (int i = summerPanel.getMidPeakToBox(); i < summerPanel.getOnPeakToBox(); i++) {
                    temp.setRates(i, summerPanel.getOnPeakRatesField());
                }
                for (int i = summerPanel.getOnPeakToBox(); i < 24; i++) {
                    temp.setRates(i, summerPanel.getMidPeakRatesField());
                }
                listOfDays.setSummerRates(temp);
            }
        }
    }

    private boolean validateWinterComboBox() {
        // TODO: Add error checking to make sure that user has entered valid $/kWh values and time spans
        return true;
    }

    private boolean validateSummerComboBox() {
        // TODO: Add error checking to make sure that user has entered valid $/kWh values and time spans
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source == winterRatesButton) {
            seasonBeingEntered = Season.WINTER;
            midPanel.removeAll();
            midPanel.add(winterPanel);
            pack();
            getContentPane().validate();
            getContentPane().repaint();
        } else if (source == summerRatesButton) {
            seasonBeingEntered = Season.SUMMER;
            midPanel.removeAll();
            midPanel.add(summerPanel);
            pack();
            getContentPane().validate();
            getContentPane().repaint();
        } else if (source == confirmButton) {
            readComboBoxes();
        } else if (source == abortButton) {
            // TODO: It would be nice if this popped up an "are you sure" dialog.
            this.dispose();
        }
    }
}
