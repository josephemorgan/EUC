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
        this.setResizable(false);

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
        }
    }

    private boolean validateWinterComboBox() {

        // logic:
        // (known: not null string, is double)
        // if the text fields are null, dont validate
        // then if the fields are greater than 0, the fields are validated

        boolean isValid = false;

        if (winterPanel.getOffPeakRatesField() == null || winterPanel.getOnPeakRatesField() == null)
        {
            isValid = false;
            return isValid;
        }

        if(winterPanel.getOffPeakRatesField() > 0 && winterPanel.getOnPeakRatesField() > 0)
        {
            isValid = true;
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please enter positive data");
        }
        return isValid;
    }

    private boolean validateSummerComboBox() {
        // logic:
        // (known: not null string, is double)
        // if the text fields are null, dont validate
        // then if the fields are greater than 0, the fields are validated

        boolean isValid = false;

        if (summerPanel.getOffPeakRatesField() == null || summerPanel.getOnPeakRatesField() == null || summerPanel.getMidPeakRatesField() == null)
        {
            isValid = false;
            return isValid;
        }

        if(summerPanel.getOffPeakRatesField() > 0 && summerPanel.getOnPeakRatesField() > 0 && summerPanel.getMidPeakRatesField() > 0)
        {
            isValid = true;
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please enter positive data");
        }
        return isValid;
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
