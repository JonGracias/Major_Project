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
    private final LinkedList<String> list = new LinkedList<>();
    private final SimpleAttributeSet attributeSet = new SimpleAttributeSet();

    public FileIn(JTextPane pane, String fileName) throws IOException, BadLocationException {
        doc = pane.getStyledDocument();
        this.fileName = fileName;
        read();
        docText();
    }

    public void read() throws IOException {
        File fileObj = new File(this.fileName);
        if (fileObj.exists()) {
            Scanner fileIn = new Scanner(fileObj);
            // Ensures that length of each row does not exceed the length of the JTextPane
            while (fileIn.hasNextLine()) {
                String result = fileIn.nextLine();
                result = result.trim();
                result = result.replace("\n", "").replace("\r", "");
                StringBuilder sb = new StringBuilder(result);

                int i = 0;
                while (i + 72 < sb.length() && (i = sb.lastIndexOf(" ", i + 72)) != -1) {
                    sb.replace(i, i + 1, "\n");
                }
                list.add(String.valueOf(sb));
                list.add("\n");
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