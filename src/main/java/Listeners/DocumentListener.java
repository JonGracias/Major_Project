package Listeners;

import Maps.PositionList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import java.awt.*;

public class DocumentListener implements javax.swing.event.DocumentListener {
    private final JTextPane lines;
    private final JTextPane textPane;
    private final int[] lineStart = {0};
    private final int[] x = {0};
    public PositionList positionList;
    public DocumentListener(JTextPane textPane, PositionList positionList){
        this.positionList = positionList;
        this.textPane = textPane;
        lines = new JTextPane();
        lines.setBackground(Color.LIGHT_GRAY);
        lines.setEditable(false);

    }

    public String getText() {
        int caretPosition = textPane.getDocument().getLength();
        Element root = textPane.getDocument().getDefaultRootElement();
        StringBuilder text = new StringBuilder("1" + System.getProperty("line.separator"));
        for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
            text.append(i).append(System.getProperty("line.separator"));
        }
        return text.toString();
    }

    /** Do Not Delete Text in Document - Backspace is not allowed!
     *
     * @throws BadLocationException
     *
     * See Notes in test folder.
     */
    public void wordIndex () throws BadLocationException {
        int caretPosition = textPane.getDocument().getLength();
        Element root = textPane.getDocument().getDefaultRootElement();
        int row = root.getElementIndex(caretPosition);
        if (row > x[0]) {
            String lines = textPane.getDocument().getText(lineStart[0], caretPosition - lineStart[0]);
            positionList.setV(lines);

            x[0]++;
            lineStart[0] = caretPosition;
        }
    }

    @Override
    public void changedUpdate (DocumentEvent de){
        lines.setText(getText());
        try {
            wordIndex();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertUpdate (DocumentEvent de){
        lines.setText(getText());
        try {
            wordIndex();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeUpdate (DocumentEvent de){
        lines.setText(getText());
        try {
            wordIndex();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }
    public JTextPane getLines(){
        return lines;
    }
}
