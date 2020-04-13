package edu.csus.csc131.euc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class EnterRatesDialog extends JFrame implements ActionListener {
    protected Rates r;
    protected Season s;
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    private WinterRatesPanel winterPanel;
    private SummerRatesPanel summerPanel;
    private JButton winterRatesButton;
    private JButton summerRatesButton;
    private JButton confirmButton;
    private JButton abortButton;

    public enum Season {
        WINTER,
        SUMMER
    }

    public EnterRatesDialog() {
        super("Enter Rates");
        s = Season.WINTER; // Default value
        setLayout(new BorderLayout());

        topPanel = new JPanel();
        midPanel = new JPanel();
        bottomPanel = new JPanel();
        winterPanel = new WinterRatesPanel();
        summerPanel = new SummerRatesPanel();
        winterRatesButton = new JButton("Winter Rates");
        summerRatesButton = new JButton("Summer Rates");
        confirmButton = new JButton("Confirm");
        abortButton = new JButton("Abort");

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

        if (s == Season.WINTER) {
            if (validateWinterComboBox()) {
                // Break this up into 3 loops because there are 3 blocks
                for (int i = winterPanel.getOffPeakFromBox(); i < winterPanel.getOffPeakToBox(); i++) {
                    temp.setRate(i, winterPanel.getOffPeakRatesField());
                }
                for (int i = winterPanel.getOnPeakFromBox(); i < winterPanel.getOnPeakToBox(); i++) {
                    temp.setRate(i, winterPanel.getOnPeakRatesField());
                }
                for (int i = winterPanel.getSecondOffPeakFromBox(); i < 24; i++) {
                    temp.setRate(i, winterPanel.getSecondOffPeakRatesField());
                }
                System.out.println(temp.toString());
            }
        } else if ( s == Season.SUMMER) {
            if (validateSummerComboBox()) {
                // Break this up into 4 loops because there are 4 blocks
                for (int i = 0; i < summerPanel.getOffPeakToBox(); i++) {
                    temp.setRate(i, summerPanel.getOffPeakRatesField());
                }
                for (int i = summerPanel.getMidPeakFromBox(); i < summerPanel.getMidPeakToBox(); i++) {
                    temp.setRate(i, summerPanel.getMidPeakRatesField());
                }
                for (int i = summerPanel.getOnPeakFromBox(); i < summerPanel.getOnPeakToBox(); i++) {
                    temp.setRate(i, summerPanel.getOnPeakRatesField());
                }
                for (int i = summerPanel.getSecondMidPeakFromBox(); i < 24; i++) {
                    temp.setRate(i, summerPanel.getSecondMidPeakRatesField());
                }
                System.out.println(temp.toString());
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
            System.out.println("Clicked WinterRatesButton");
            s = Season.WINTER;
            midPanel.removeAll();
            midPanel.add(winterPanel);
            pack();
            getContentPane().validate();
            getContentPane().repaint();
        } else if (source == summerRatesButton) {
            System.out.println("Clicked SummerRatesButton");
            s = Season.SUMMER;
            midPanel.removeAll();
            midPanel.add(summerPanel);
            pack();
            getContentPane().validate();
            getContentPane().repaint();
        } else if (source == confirmButton) {
            // TODO: Need Week class to be finished to handle wiring up submission of info
            readComboBoxes();
        } else if (source == abortButton) {
            // TODO: It would be nice if this popped up an "are you sure" dialog.
            this.dispose();
        }
    }
}
