package edu.csus.csc131.euc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDayDialog extends JFrame implements ActionListener {

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel hour1UsageLabel;
    private JLabel hour2UsageLabel;
    private JTextField hour1UsageTF;
    private JTextField hour2UsageTF;
    private JButton finalizeButton;
    private JButton cancelButton;
    private static int HOURS_IN_DAY = 24;
    private static Dimension TF_DIMENSION = new Dimension(100, 25);
    private static Dimension BUTTON_DIMENSION = new Dimension(150, 25);
    private static Dimension LABEL_DIMENSION = new Dimension(180, 25);

    public AddDayDialog() {
        super("Enter Usage in KW/h [Ex: 12.32]");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setLocationRelativeTo(null);

        leftPanel = new JPanel();
        rightPanel = new JPanel();

        leftPanel.setLayout(new FlowLayout());
        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setPreferredSize(new Dimension(200, 800));
        rightPanel.setPreferredSize(new Dimension(200, 800));

        for (int i = 0; i < HOURS_IN_DAY; i++)
        {
            if(i == 0){
                JLabel usageLabel = new JLabel("Usage from " + (i + 12) + "am to " + (i + 1) + "am");
                usageLabel.setPreferredSize(LABEL_DIMENSION);
                leftPanel.add(usageLabel);
            }
            else if (i < 11){
                JLabel usageLabel = new JLabel("Usage from " + i + "am to " + (i + 1) + "am");
                usageLabel.setPreferredSize(LABEL_DIMENSION);
                leftPanel.add(usageLabel);
            }
            else if (i == 11) {
                JLabel usageLabel = new JLabel("Usage from " + i + "am to " + (i + 1) + "pm");
                usageLabel.setPreferredSize(LABEL_DIMENSION);
                leftPanel.add(usageLabel);
            }
            else if (i == 12) {
                JLabel usageLabel = new JLabel("Usage from " + i + "pm to " + (i - 11) + "pm");
                usageLabel.setPreferredSize(LABEL_DIMENSION);
                leftPanel.add(usageLabel);
            }
            else if (i > 12 && i < 24) {
                JLabel usageLabel = new JLabel("Usage from " + (i - 12) + "pm to " + (i - 11) + "pm");
                usageLabel.setPreferredSize(LABEL_DIMENSION);
                leftPanel.add(usageLabel);
            }
        }

        //add a text field for each hour
        for (int i = 0; i < HOURS_IN_DAY; i++)
        {
            JTextField usageTF = new JTextField("0.0");
            usageTF.setPreferredSize(TF_DIMENSION);
            rightPanel.add(usageTF);
        }

        finalizeButton = new JButton ("Finalize Usage");
        cancelButton = new JButton("Cancel");

        finalizeButton.setPreferredSize(BUTTON_DIMENSION);
        cancelButton.setPreferredSize(BUTTON_DIMENSION);

        finalizeButton.addActionListener(this);
        cancelButton.addActionListener(this);

        leftPanel.add(finalizeButton);
        rightPanel.add(cancelButton);

        leftPanel.setBorder(BorderFactory.createTitledBorder("Times"));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Usage"));

        //add left and right panels to the Add Day Dialog
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        pack ();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == finalizeButton) {
            this.dispose();
        }
        else if (source == cancelButton) {
            this.dispose();
        }
    }
}
