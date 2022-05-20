package Maps;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

import static java.lang.Character.isWhitespace;

public class PositionList {
    private int line = 0;
    private String V;
    public LinkedList<WordPositions> position;
    BstWords wordTree;
    public HTable allWords = new HTable();
    public HTable wordPositions = new HTable();
    private final SimpleAttributeSet attributeSet = new SimpleAttributeSet();
    int count = 1;

    public PositionList(){
        position = new LinkedList<>();

    }
    public PositionList(String V){
        this.V = V;

    }
    public void setV(String text) throws BadLocationException {
        this.V = text;
        setPosition();
        line += V.length();

    }

    public void setPosition(){
        int start;
        int x = 0;
        int length;
        for(int i = 0; i < V.length();i ++){
            if(isWhitespace(V.charAt(i))||V.length()==i+1) {
                String str = V.substring(x, i+1);
                length = str.trim().length();
                x = i;
                start = (i) - length  + line;
                if(length != 0) {
                    /*String word = L.getDocument().getText(start, length+l);
                    word = word.trim();*/
                    //System.out.println(i + ": "+ L.getDocument().getText(start, length));
                    count++;
                    setSentences(new WordPositions(str.trim(), start, length));
                }
            }
        }

    }
    public void setSentences(WordPositions words){
        position.addNode(words);
        String hash = words.Word().replaceAll("\\p{Punct}", "");
        int hashKey = (int) HTable.compute_hash(hash);
        allWords.add(hashKey, words);
    }

    public void setText(JTextPane pane, String list) throws BadLocationException {
        System.out.println(count);
        switch (list) {
            case "HashTable" -> {
                pane.setText("");
                pane.getDocument().insertString(0, String.valueOf(allWords.toPrint()), attributeSet);
            }
            case "LinkedList" -> {
                pane.setText("");
                pane.getDocument().insertString(0, String.valueOf(position.printNodes()), attributeSet);
            }
            case "BST" -> {
                int pos = Integer.parseInt(JOptionPane.showInputDialog("start position"));
                int len = Integer.parseInt(JOptionPane.showInputDialog("length"));
                System.out.println(pane.getDocument().getText(pos,len));
            }
        }
    }

    public void print() {
        //position.printNodes();
        //wordTree.traversePreOrder();
        //wordTree.traverseInOrder();
        //wordTree.traversePostOrder();
        //System.out.println(hTable.toString());
    }
}
