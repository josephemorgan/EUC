package edu.csus.csc131.euc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDate;

public class AddDayDialog extends JDialog implements ActionListener {

    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    private JButton finalizeButton;
    private JButton readFromFileButton;
    private JButton cancelButton;
    private JButton enterManuallyButton;
    private JButton helpButton;
    private DayEntryPanel fieldPanel;
    private Week week;
    private JTextField usageTextFields[] = new JTextField[HOURS_IN_DAY];
    private JTextField dateField = new JTextField(LocalDate.now().toString(), 24);
    public double[] hoursInDayUsage = new double[HOURS_IN_DAY];
    private int focusCounter = 0;
    private static int HOURS_IN_DAY = 24;
    private static Dimension TF_DIMENSION = new Dimension(100, 25);
    private static Dimension BUTTON_DIMENSION = new Dimension(150, 25);
    private static Dimension LABEL_DIMENSION = new Dimension(180, 25);

    public AddDayDialog(Frame owner, Week wk) {
        super(owner, true);
        this.week = wk;

        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        topPanel = new JPanel();
        midPanel = new JPanel();
        bottomPanel = new JPanel();
        fieldPanel = new DayEntryPanel();

        topPanel.setLayout(new FlowLayout());
        midPanel.setLayout(new GridBagLayout());
        bottomPanel.setLayout(new GridBagLayout());

        finalizeButton = new JButton ("Finalize Usage");
        readFromFileButton = new JButton ("Read From File");
        enterManuallyButton = new JButton ("Enter Usage Manually");
        cancelButton = new JButton("Cancel");
        helpButton = new JButton("Help");

        finalizeButton.addActionListener(this);
        readFromFileButton.addActionListener(this);
        enterManuallyButton.addActionListener(this);
        cancelButton.addActionListener(this);
        helpButton.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        bottomPanel.add(finalizeButton, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        bottomPanel.add(cancelButton, c);

        c.gridx = 1;
        c.gridy = 1;
        bottomPanel.add(helpButton, c);

        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,0,0);
        midPanel.add(readFromFileButton, c);

        c.weighty = .5;
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        midPanel.add(new JLabel("Or"), c);

        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        midPanel.add(enterManuallyButton, c);

        Border innerBorder = BorderFactory.createTitledBorder("How would you like to enter usage?");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);

        midPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        midPanel.setPreferredSize(new Dimension(400, 100));

        //add left and right panels to the Add Day Dialog
        topPanel.add(new JLabel("Enter Date: "));
        topPanel.add(dateField);

        this.add(midPanel, BorderLayout.CENTER);

        //add(topPanel, BorderLayout.NORTH);
        //add(fieldPanel, BorderLayout.CENTER);
        //add(bottomPanel, BorderLayout.SOUTH);

        pack ();

        //setting focus initially for new frame must be after pack() according to stack overflow
        //usageTextFields[focusCounter].requestFocusInWindow();
        //usageTextFields[focusCounter].selectAll();

        //must be last or bugggggggs
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == enterManuallyButton) {
            this.remove(midPanel);
            this.add(topPanel, BorderLayout.NORTH);
            this.add(fieldPanel, BorderLayout.CENTER);
            this.add(bottomPanel, BorderLayout.SOUTH);
            this.pack();
            this.validate();
            this.repaint();
        } else if (source == readFromFileButton) {
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
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Unable to open file");
            }
        } else if (source == finalizeButton) {
            if (ValidateTextFields() == true) {
                Day dayToAdd;
                Usage usageToAdd = new Usage();
                for (int i = 0; i < HOURS_IN_DAY; ++i) {
                    usageToAdd.setUsage(i, fieldPanel.getTextFieldContents(i));
                }
                dayToAdd = new Day(dateField.getText(), usageToAdd);
                week.addDay(dayToAdd);
                this.dispose();
            }
            else
            {
                //if data was not valid, do nothing
            }
        } else if (source == cancelButton) {
            this.dispose();
        } else if (source == helpButton) {
            String helpMessage = "Add a Day Dialog Help Message:\n\n" +
                            "To add a day manually, please first make sure that you've entered the correct date\n" +
                            "corresponding to the day you want to add. If the date you've entered is the same as a day\n" +
                            "that's already been added, the new usage values will replace those for the previous entry\n\n" +
                            "Next, enter the number of kiloWatt-hours that you used during each hourly period of the day.\n\n" +
                            "Last, click \"Finalize Usage,\" and the values that you've entered with be saved.\n\n" +
                            "You can click \"Abort\" at any time to cancel usage entry.";
            JOptionPane.showMessageDialog(this, helpMessage);
        }
    }

    private boolean ValidateTextFields(){
        // logic:
        // for each text field, check if its non null (already parsed to double)
        //  if non null, then check if its less than 0.  If so then return not valid
        //  if null, return not valid

        boolean isValid = true;
        for (int i = 0; i < HOURS_IN_DAY; i++) {
            if (fieldPanel.getTextFieldContents(i) != null)
            {
                if (fieldPanel.getTextFieldContents(i) < 0) {
                    isValid = false;
                    JOptionPane.showMessageDialog(this, "Please enter data greather than or equal to 0");
                    break;
                }
            }
            else
            {
                isValid = false;
            }
        }
        return isValid;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            usageTextFields[focusCounter + 1].requestFocus();
        }
    }
}
