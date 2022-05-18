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
    public String fileName;
    public Document doc;
    public JTextPane pane;
    private final LinkedList<String> list = new LinkedList<>();
    private final SimpleAttributeSet attributeSet = new SimpleAttributeSet();

    public FileIn(JTextPane pane) throws IOException, BadLocationException {
        this.pane = pane;
        this.doc = pane.getStyledDocument();
    }

    public void setFile(String fileName) throws IOException, BadLocationException {
        this.fileName = fileName;
        read();
    }

    public void read() throws IOException, BadLocationException {
        File fileObj = new File(this.fileName);
        if (fileObj.exists()) {
            pane.setText("");
            Scanner fileIn = new Scanner(fileObj);
            while (fileIn.hasNextLine()) {
                String result = fileIn.nextLine();
                writeToList(result);
            }
            fileIn.close();
            docText();
        } else
            showMessageDialog(null, "Error File not found.");
    }

    public void writeToList(String input) throws BadLocationException {
        input = input.trim();
        StringBuilder sb = new StringBuilder(input);

        int i = 0;
        while (i + 72 < sb.length() && (i = sb.lastIndexOf(" ", i + 72)) != -1) {
            sb.replace(i, i + 1, System.lineSeparator());
        }
        list.add(String.valueOf(sb));
        list.add(System.lineSeparator());
    }

    // iterates to through the LinkedList and adds text to the Document Interface
    public void docText() throws BadLocationException {
        for (String s : this.list) {
            doc.insertString(doc.getLength(), String.valueOf(s), attributeSet);
        }
        this.list.clear();
    }
}