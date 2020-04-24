package edu.csus.csc131.euc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EnterRatesDialog extends JDialog implements ActionListener {
    private Week listOfDays;
    private JPanel entryPanel;
    private JPanel bottomPanel;
    private JPanel messagePanel;
    private JLabel messageLabel;
    private WinterRatesPanel winterPanel;
    private SummerRatesPanel summerPanel;
    private JButton winterRatesButton;
    private JButton summerRatesButton;
    private JButton confirmButton;
    private JButton backButton;
    private Season seasonBeingEntered;
    private GridBagConstraints c;

    public EnterRatesDialog(Frame parent, Week listOfDays) {
        super(parent, true);
        this.listOfDays = listOfDays;
        setLayout(new BorderLayout());

        entryPanel = new JPanel();
        bottomPanel = new JPanel();
        messagePanel = new JPanel();
        winterPanel = new WinterRatesPanel();
        summerPanel = new SummerRatesPanel();
        messageLabel = new JLabel("Enter values and press 'Confirm' to update rates for winter.");
        winterRatesButton = new JButton("Update Winter Rates");
        summerRatesButton = new JButton("Update Summer Rates");
        confirmButton = new JButton("Confirm");
        backButton = new JButton("Back");
        seasonBeingEntered = Season.WINTER;

        bottomPanel.setLayout(new GridBagLayout());

        messagePanel.setBorder(BorderFactory.createTitledBorder(""));

        entryPanel.add(winterPanel);

        messagePanel.add(messageLabel);

        add(entryPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5,10,5,10);

        c.gridx = 0;
        c.gridy = 0;
        bottomPanel.add(confirmButton, c);

        c.gridx = 0;
        c.gridy = 1;
        bottomPanel.add(summerRatesButton, c);

        c.gridx = 0;
        c.gridy = 2;
        bottomPanel.add(backButton, c);

        c.gridx = 0;
        c.gridy = 3;
        bottomPanel.add(messagePanel, c);

        winterRatesButton.addActionListener(this);
        summerRatesButton.addActionListener(this);
        confirmButton.addActionListener(this);
        backButton.addActionListener(this);

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
                updateMessage("Winter rates have been updated.");
            }
            else
            {
                JOptionPane.showMessageDialog(winterPanel, "Please enter all rates in the format: #.####");
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
                updateMessage("Summer rates have been updated.");
            }
            else
            {
                JOptionPane.showMessageDialog(winterPanel, "Please enter all rates in the format: #.####");
            }
        }
    }

    private boolean validateWinterComboBox() {
        boolean isValid = false;
        // logic:
        // if the ___ text box is not empty
        //      and contains a positive double
        //          return true
        // else false (info popup)
        if (winterPanel.getOffPeakRatesField() != null)
        {
            if (winterPanel.getOffPeakRatesField() > 0)
            {
                isValid = true;
            }
        }
        else
            isValid = false;
        return isValid;
    }

    private boolean validateSummerComboBox() {
        // TODO: Add error checking to make sure that user has entered valid $/kWh values and time spans
        // logic:
        // if the ___ text box is not empty
        //      and contains a positive double
        //          return true
        // else false (info popup)
        return true;
    }

    public void updateMessage(String msg) {
        messageLabel.setText(msg);
        validate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source == winterRatesButton) {
            seasonBeingEntered = Season.WINTER;
            entryPanel.removeAll();
            entryPanel.add(winterPanel);
            bottomPanel.remove(winterRatesButton);
            c.gridx = 0;
            c.gridy = 1;
            bottomPanel.add(summerRatesButton, c);
            updateMessage("Enter values and press 'Confirm' to update rates for winter.");
            pack();
            getContentPane().validate();
            getContentPane().repaint();
        } else if (source == summerRatesButton) {
            seasonBeingEntered = Season.SUMMER;
            entryPanel.removeAll();
            entryPanel.add(summerPanel);
            bottomPanel.remove(summerRatesButton);
            c.gridx = 0;
            c.gridy = 1;
            bottomPanel.add(winterRatesButton, c);
            updateMessage("Enter values and press 'Confirm' to update rates for summer.");
            pack();
            getContentPane().validate();
            getContentPane().repaint();
        } else if (source == confirmButton) {
            readComboBoxes();
        } else if (source == backButton) {
            // TODO: It would be nice if this popped up an "are you sure" dialog.
            this.dispose();
        }
    }
}
