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
            while (fileIn.hasNextLine()) {
                String result = fileIn.nextLine();
                this.list.add(result + '\n');
            }
            fileIn.close();

        } else
            showMessageDialog(null, "Error File not found.");
    }

    // iterates to through the LinkedList and adds text to the Document Interface
    public void docText() throws BadLocationException {
        int count = 0;
        for (String s : this.list) {
            doc.insertString(doc.getLength(), String.valueOf(s), attributeSet);
           // System.out.println("line " + count + ":" + this.list.get(count));
           // count++;
        }
    }

}