package Maps;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.util.ArrayList;

import static java.lang.Character.isWhitespace;

public class PositionList {
    private int line = 0;

    private String V;
    public list<ArrayList<Pair>> position = new list<>();
    ArrayList<Pair> list = new ArrayList<>();
    BstWords wordTree;
    HTable hTable;

    public PositionList(){

    }
    public PositionList(String V){
        this.V = V;

    }
    public void setV(String text, JTextPane L) throws BadLocationException {
        this.V = text;
        setPosition(L);
        line += V.length();

    }

    public void setPosition(JTextPane L) throws BadLocationException {
        int start;
        int x = 0;
        int length;
        /** testing
        System.out.println("----------------------------");
        System.out.println(V);
        System.out.println("----------------------------");*/
        for(int i = 0; i < V.length();i ++){
            if(isWhitespace(V.charAt(i))||V.length()==i+1) {
                String str = V.substring(x, i);
                length = str.length();
                x = i;
                start = (i) - length  + line;
                int[] pos = new int[]{start, length};
                String word = L.getDocument().getText(start, length + 1);
                list.add(new Pair(pos,word));
                setSentences(list);
            }
        }

    }
    public void setSentences(ArrayList<Pair> words){
        position.addNode(words);
        wordTree = BstWords.createTree(words);
        hTable = new HTable(words);


    }
    public void print() {
        //position.printNodes();
        //wordTree.traversePreOrder();
        //wordTree.traverseInOrder();
        //wordTree.traversePostOrder();
        System.out.println(hTable.toString());
    }
}
