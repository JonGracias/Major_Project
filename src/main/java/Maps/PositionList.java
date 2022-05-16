package Maps;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.util.ArrayList;

public class PositionList {
    private int pos;
    private int length;
    private int line = 0;

    private String V;
    public list<ArrayList<String>> position = new list<>();

    public PositionList(){

    }
    public PositionList(String V){
        this.V = V;

    }
    public void setV(String text, JTextPane L) throws BadLocationException {
        this.V = text;
        setPosition(L);

    }

    public void setPosition(JTextPane L) throws BadLocationException {
        int start;
        int end;
        int x = 0;
        int length;
        int currentLine = 0;
        for(int i = 0; i < V.length();i ++){
            if(V.charAt(i) == 32 || V.charAt(i) == 10+13) {
                String str = V.substring(x, i);
                length = str.trim().length();
                System.out.println(str.trim());
                x = i;

                start = (i) - length  + line;
                end = start + length;
                System.out.println("Line = " + line);
                System.out.println("Word length = " + length);
                System.out.println("Word start = " + start);
                System.out.println("Word end = " + end);
            }
            if(V.charAt(i) == 10||V.charAt(i) == 13){
                line += i + 1;
            }
        }
    }
}
