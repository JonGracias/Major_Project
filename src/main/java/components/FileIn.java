package components;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import static javax.swing.JOptionPane.showMessageDialog;

public class FileIn {
    public JTextPane pane;
    public String fileName;
    public Document doc;
    private final LinkedList<String> list = new LinkedList<>();
    private final SimpleAttributeSet attributeSet = new SimpleAttributeSet();

    public FileIn(JTextPane pane, String fileName) throws IOException, BadLocationException {
        // SimpleAttributeSet uses a Hash Table which extends a dictionary and
        // Implements Serializable and Cloneable, this is how I implement an Undoable Edit listener
        //SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        //Document doc = pane.getStyledDocument();
        doc = pane.getStyledDocument();
        this.pane = pane;
        this.fileName = fileName;
        read();
        docText();
    }

    public void read() throws IOException {
        // This method adds Stings to a LinkedList in order to format the text from a file
        //LinkedList<String> list = new LinkedList<>();
        // Filename for output
        File fileObj = new File(this.fileName);
        if (fileObj.exists()) {
            Scanner fileIn = new Scanner(fileObj);
            while (fileIn.hasNextLine()) {
                String result = fileIn.nextLine();
                int length = result.length();
                // Ensures that length of each row does not exceed the length of the JTextPane
                int n = 200;
                if (n < length) {
                    n = 200 + (result.substring(200, length).indexOf(" "));
                    for (int i = 0; i < length; i += n) {
                        this.list.addLast(" " + result.substring(i, Math.min(length, i + n)).trim());
                        this.list.addLast("\n");
                    }
                } else {
                    this.list.addLast(" " + result.trim());
                    this.list.addLast("\n");
                }
            }
            fileIn.close();

        } else
            showMessageDialog(null, "Error File not found.");
    }
    // iterates to through the LinkedList and adds text to the Document Interface

    public void docText() throws BadLocationException {
        for (String s : this.list) {
            doc.insertString(doc.getLength(), String.valueOf(s), attributeSet);
        }
    }

}