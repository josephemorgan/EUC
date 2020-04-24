package edu.csus.csc131.euc;

import javax.swing.*;
import java.awt.*;

public class DayEntryFocusTraversalPolicy extends ContainerOrderFocusTraversalPolicy {
    @Override
    public boolean accept(Component aComponent) {
        if (aComponent instanceof JLabel)
            return false;
        return true;
    }
}
