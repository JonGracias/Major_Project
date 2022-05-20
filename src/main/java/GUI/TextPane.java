package GUI;

import Maps.PositionList;
import components.FileIn;
import Listeners.MouseListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public abstract class TextPane extends JPanel {
    public JTextPane textPane;
    public FileIn fileIn;
    public JScrollPane jScrollPane;
    public PositionList positionList;

    public TextPane() throws BadLocationException, IOException {
        positionList = new PositionList();
        textPane = new JTextPane();
        fileIn = new FileIn(textPane);
        jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setMaximumSize(new Dimension(500, 900));
        jScrollPane.setPreferredSize(new Dimension(500, 700));


        textPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

}
