package edu.csus.csc131.euc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.io.File;

public class AddDayDialog extends JFrame implements ActionListener {

    private JPanel topPanel;
    private JPanel fieldPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JButton finalizeButton;
    private JButton readFromFileButton;
    private JButton cancelButton;
    private Week week;
    private JTextField usageTextFields[] = new JTextField[HOURS_IN_DAY];
    public double[] hoursInDayUsage = new double[HOURS_IN_DAY];
    private int focusCounter = 0;
    private static int HOURS_IN_DAY = 24;
    private static Dimension TF_DIMENSION = new Dimension(100, 25);
    private static Dimension BUTTON_DIMENSION = new Dimension(150, 25);
    private static Dimension LABEL_DIMENSION = new Dimension(180, 25);

    public AddDayDialog(Week wk) {
        super("Enter Usage in KW/h [Ex: 12.32]");
        this.week = wk;

        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        topPanel = new JPanel();
        fieldPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.setLayout(new FlowLayout());
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        leftPanel.setLayout(new FlowLayout());
        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setLayout(new FlowLayout());
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
            usageTextFields[i] = new JTextField("0.0");
            usageTextFields[i].setPreferredSize(TF_DIMENSION);

            usageTextFields[i].addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        usageTextFields[focusCounter + 1].requestFocusInWindow();
                        usageTextFields[focusCounter + 1].selectAll();
                        focusCounter++;
                    }
                }
            });

            rightPanel.add(usageTextFields[i]);
        }

        finalizeButton = new JButton ("Finalize Usage");
        readFromFileButton = new JButton ("Read From File");
        cancelButton = new JButton("Cancel");

        finalizeButton.addActionListener(this);
        readFromFileButton.addActionListener(this);
        cancelButton.addActionListener(this);

        bottomPanel.add(finalizeButton);
        bottomPanel.add(readFromFileButton);
        bottomPanel.add(cancelButton);

        leftPanel.setBorder(BorderFactory.createTitledBorder("Times"));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Usage"));

        //add left and right panels to the Add Day Dialog
        fieldPanel.add(leftPanel, BorderLayout.WEST);
        fieldPanel.add(rightPanel, BorderLayout.EAST);

        topPanel.add(new JLabel("Enter Date: "));
        topPanel.add(new JTextField(24));
        add(topPanel, BorderLayout.NORTH);
        add(fieldPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack ();

        //setting focus initially for new frame must be after pack() according to stack overflow
        usageTextFields[focusCounter].requestFocusInWindow();
        usageTextFields[focusCounter].selectAll();

        //must be last or bugggggggs
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == finalizeButton) {
            Day dayToAdd;
            for (JTextField textfield : usageTextFields)
            {
                hoursInDayUsage[textfield.getComponentCount()] = Double.parseDouble(textfield.getText());
            }
            for (int i = 0; i < HOURS_IN_DAY; i++)
            {
                System.out.println(hoursInDayUsage[i]);
            }
            this.dispose();
        }
        else if (source == cancelButton) {
            this.dispose();
        }
        else if (source == readFromFileButton)
        {
            //Open a file browser, save file path
            JFileChooser fileChooser = new JFileChooser();

            //default the directory to the users home directory
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

            //variable to store the result of the dialog menu
            int result = fileChooser.showOpenDialog(this);

            //if the result is an approved option (a file)
            if (result == JFileChooser.APPROVE_OPTION) {
                //new file object to hold the file
                File selectedFile = fileChooser.getSelectedFile();

                //Fetch data from file and add a day to week collection
                week.fetchDayFromFile(selectedFile.getAbsolutePath());

                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            }
            else
            {
                System.out.println("Failed to select a file or choose an approved file.");
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            usageTextFields[focusCounter + 1].requestFocus();
        }
    }
}
