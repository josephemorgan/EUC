package edu.csus.csc131.euc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class DayEntryPanel extends JPanel {
    private ArrayList<JLabel> hoursLabelList;
    private ArrayList<JTextField> usageTextFieldList;

    public DayEntryPanel() {
        initializeVariables();
        buildPanel();
    }

    public ArrayList<Double> getTextFieldContents() {
        ArrayList<Double> ret = new ArrayList<>();

        for (JTextField field : usageTextFieldList) {
            ret.add(Double.parseDouble(field.getText()));
        }

        return ret;
    }

    public double getTextFieldContents(int i) {
        return Double.parseDouble(usageTextFieldList.get(i).getText());
    }

    private void initializeVariables() {
        hoursLabelList = new ArrayList<>();
        usageTextFieldList = new ArrayList<>();
        for (int i = 0; i < Day.HOURS_IN_DAY; ++i) {
            if (i < 9) {
                hoursLabelList.add(new JLabel("0" + i + ":00 - 0" + (i + 1) + ":00"));
            } else if (i ==9) {
                hoursLabelList.add(new JLabel("09:00 - 10:00"));
            } else {
                hoursLabelList.add(new JLabel(i + ":00 - " + (i + 1) + ":00"));
            }
            usageTextFieldList.add(new JTextField("0.0",8));
        }

    }

    private void buildPanel() {
        this.setLayout(new GridBagLayout());
        Border innerBorder = BorderFactory.createTitledBorder("Enter the usage for each hour in kW*h");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);

        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;

        c.insets = new Insets (5, 10, 5, 5);
        for (int i = 0; i < 12; ++i) {
            setConstraints(c, 0, i);
            this.add(hoursLabelList.get(i), c);
        }

        c.insets = new Insets (5, 5, 5, 10);
        for (int i = 0; i < 12; ++i) {
            setConstraints(c, 1, i);
            this.add(usageTextFieldList.get(i), c);
        }

        c.insets = new Insets (5, 10, 5, 5);
        for (int i = 0; i < 12; ++i) {
            setConstraints(c, 2, i);
            this.add(hoursLabelList.get(i + 12), c);
        }

        c.insets = new Insets (5, 5, 5, 10);
        for (int i = 0; i < 12; ++i) {
            setConstraints(c, 3, i);
            this.add(usageTextFieldList.get(i + 12), c);
        }

        // Set focus listener to highlight boxes when they're focused
        for (JTextField field : usageTextFieldList) {
            field.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    field.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                }
            });
        }

        // This will allow us to tab through elements from top to bottom, left to right
        this.setFocusCycleRoot(true);
        this.setFocusTraversalPolicy(new DayEntryFocusTraversalPolicy());
    }

    private void setConstraints(GridBagConstraints c, int gridx, int gridy, int gridwidth, int gridheight) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
    }

    private void setConstraints(GridBagConstraints c, int gridx, int gridy) {
        setConstraints(c, gridx, gridy, 1, 1);
    }
}
