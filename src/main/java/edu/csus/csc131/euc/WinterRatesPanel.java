package edu.csus.csc131.euc;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class WinterRatesPanel extends JPanel {
    private static final int HOURS_IN_DAY = 24;
    private static Map<String, Integer> TIMES;
    private static String[] TIMES_KEYS;
    private JTextField offPeakRatesField;
    private JTextField onPeakRatesField;
    private JComboBox<String> offPeakToBox;
    private JComboBox<String> onPeakToBox;

    public WinterRatesPanel() {
        initTimes();

        buildPanel();
    }

    public void buildPanel() {
        // Init Elements
        offPeakRatesField = new JTextField(8);
        onPeakRatesField = new JTextField(8);
        offPeakToBox = new JComboBox<String>(TIMES_KEYS);
        onPeakToBox = new JComboBox<String>(TIMES_KEYS);
        offPeakRatesField.setText("0.1006");
        onPeakRatesField.setText("0.1338");
        offPeakToBox.setSelectedIndex(17);
        onPeakToBox.setSelectedIndex(20);

        ////// Build Panels //////
        int ratesLabelIndex = 0;
        int fromLabelIndex = 0;
        int toLabelIndex = 0;
        Insets def = new Insets(3, 20, 3, 20);
        Insets right = new Insets(3, 20, 3, 3);
        Insets left = new Insets(3, 3, 3, 20);

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = def;

        // Row One
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 2;
        add(new JLabel("Off Peak"), c);

        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = right;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Rates:"), c);

        c.gridx = 3;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = left;
        c.anchor = GridBagConstraints.LINE_START;
        add(offPeakRatesField, c);

        c.insets = def;
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 4;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = right;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ends at:"), c);

        c.gridx = 5;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = left;
        c.anchor = GridBagConstraints.LINE_START;
        add(offPeakToBox, c);

        // Row Two
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = def;
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 2;
        c.gridwidth = 2;
        add(new JLabel("Peak"), c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = right;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Rates:"), c);

        c.gridx = 3;
        c.gridy = 2;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = left;
        c.anchor = GridBagConstraints.LINE_START;
        add(onPeakRatesField, c);

        c.insets = def;
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 4;
        c.gridy = 2;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = right;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ends at:"), c);

        c.gridx = 5;
        c.gridy = 2;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = left;
        c.anchor = GridBagConstraints.LINE_START;
        add(onPeakToBox, c);

    }

    public Double getOffPeakRatesField() {
        return Double.valueOf(offPeakRatesField.getText());
    }

    public Double getOnPeakRatesField() {
        return Double.valueOf(onPeakRatesField.getText());
    }

    public int getOffPeakToBox() {
        return TIMES.get(offPeakToBox.getSelectedItem().toString());
    }

    public int getOnPeakToBox() {
        return TIMES.get(onPeakToBox.getSelectedItem().toString());
    }


    private static void initTimes() {
        TIMES_KEYS = new String[HOURS_IN_DAY];
        TIMES = new HashMap<String, Integer>();

        TIMES_KEYS[0] = "00:00";
        TIMES_KEYS[1] = "01:00";
        TIMES_KEYS[2] = "02:00";
        TIMES_KEYS[3] = "03:00";
        TIMES_KEYS[4] = "04:00";
        TIMES_KEYS[5] = "05:00";
        TIMES_KEYS[6] = "06:00";
        TIMES_KEYS[7] = "07:00";
        TIMES_KEYS[8] = "08:00";
        TIMES_KEYS[9] = "09:00";
        TIMES_KEYS[10] = "10:00";
        TIMES_KEYS[11] = "11:00";
        TIMES_KEYS[12] = "12:00";
        TIMES_KEYS[13] = "13:00";
        TIMES_KEYS[14] = "14:00";
        TIMES_KEYS[15] = "15:00";
        TIMES_KEYS[16] = "16:00";
        TIMES_KEYS[17] = "17:00";
        TIMES_KEYS[18] = "18:00";
        TIMES_KEYS[19] = "19:00";
        TIMES_KEYS[20] = "20:00";
        TIMES_KEYS[21] = "21:00";
        TIMES_KEYS[22] = "22:00";
        TIMES_KEYS[23] = "23:00";

        for (int i = 0; i < HOURS_IN_DAY; i++) {
            TIMES.put(TIMES_KEYS[i], i);
        }
    }
}
