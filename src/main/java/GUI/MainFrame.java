package GUI;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.IOException;

public class MainFrame extends JFrame {
    public JFrame frame;
    public MenuBar menuBar;
    public JSplitPane splitPane;
    public static Left left;
    public static Right right;
    public static String focus = "Left";

    public MainFrame() {
        try {
            left = new Left();
            right = new Right();
        } catch (BadLocationException | IOException e) {
            throw new RuntimeException(e);
        }
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left.jScrollPane, right.jScrollPane);
        menuBar = new MenuBar();
        frame = new JFrame();
        splitPane.setDividerLocation(0.5);
        splitPane.setResizeWeight(0.5);
        //Dimension minimumSize = new Dimension(600, 500);

        frame.setJMenuBar(menuBar.menuBar());
        frame.add(splitPane);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.validate();
        frame.repaint();
    }

    public static JTextPane Pane() {
        if (MainFrame.focus.matches("Left")) {
            return MainFrame.left.textPane;
        } else
            return  MainFrame.right.textPane;
    }
}