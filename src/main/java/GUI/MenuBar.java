package GUI;

import components.OSName;
import components.TextFilter;
import components.save;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledEditorKit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import static GUI.MainFrame.left;
import static GUI.MainFrame.right;

class MenuBar extends JMenuBar {
    private final JFileChooser chooser = new JFileChooser(String.valueOf(new OSName().getOsName()));

    public JMenuBar menuBar() {
        chooser.addChoosableFileFilter(new TextFilter());
        chooser.setAcceptAllFileFilterUsed(false);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenu style = new JMenu("Style");
        JMenu lists = new JMenu("lists");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
        menuBar.add(style);
        menuBar.add(lists);

        //JMenuItem find_Hash = new JMenuItem("Find");

        JMenuItem hash = new JMenuItem("HashTable");
        JMenuItem linked = new JMenuItem("LinkedList");
        JMenuItem BST = new JMenuItem("BST");

        hash.addActionListener(ae -> {
            if (MainFrame.focus.matches("Left")) {
                try {
                    Left.positionListLeft.setText(right.textPane, "HashTable");
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    Right.positionListRight.setText(left.textPane, "HashTable");
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        linked.addActionListener(ae -> {
            if (MainFrame.focus.matches("Left")) {
                try {
                    Left.positionListLeft.setText(right.textPane, "LinkedList");
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    Right.positionListRight.setText(left.textPane, "LinkedList");
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        BST.addActionListener(ae -> {
            if (MainFrame.focus.matches("Left")) {
                try {
                    Left.positionListLeft.setText(left.textPane, "BST");
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    Right.positionListRight.setText(right.textPane, "BST");
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // create menuItems
        JMenuItem m1 = new JMenuItem("Open");
        JMenuItem m2 = new JMenuItem("Save");
        JMenuItem m3 = new JMenuItem("Close");

        Action action = new StyledEditorKit.BoldAction();
        action.putValue(Action.NAME, "Bold");
        style.add(action);
        Action action1 = new StyledEditorKit.ItalicAction();
        action1.putValue(Action.NAME, "Italics");
        style.add(action1);
        Action action2 = new StyledEditorKit.UnderlineAction();
        action2.putValue(Action.NAME, "Underline");
        style.add(action2);
        Action action3 = new StyledEditorKit.FontSizeAction(Action.LARGE_ICON_KEY, 20);
        action3.putValue(Action.NAME, "Increase size");
        style.add(action3);

        m1.addActionListener(ae -> {
            File sourceFile;
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnVal = chooser.showDialog(null, "Open");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                sourceFile = chooser.getSelectedFile();
                String sourceFilePath = sourceFile.getAbsolutePath();
                if (MainFrame.focus.matches("Left")) {
                    try {
                        left.fileIn.setFile(sourceFilePath);
                    } catch (BadLocationException | IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        right.fileIn.setFile(sourceFilePath);
                    } catch (BadLocationException | IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });

        m2.addActionListener(ae -> {
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                try {
                    new save(path, MainFrame.Pane().getText());
                } catch (IOException ignored) {

                }
            }
        });

        m3.addActionListener(ae -> MainFrame.Pane().setText(""));


        // add menu items to menu
        lists.add(hash);
        lists.add(linked);
        lists.add(BST);
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        return menuBar;
    }
}
