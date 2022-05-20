package Listeners;

import GUI.MainFrame;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FocusListener extends FocusAdapter {
    @Override
    public void focusGained(FocusEvent e) {
        //System.out.println("focusGained e.getSource().c=" + ((JTextPane) e.getSource()).getName());
        MainFrame.focus = ((JTextPane) e.getSource()).getName();
    }

    @Override
    public void focusLost(FocusEvent e) {
        //System.out.println("focusLost e.getSource().c=" + ((JTextPane) e.getSource()).getName());
    }
}
