import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Notes {
    /**
     * the following is old code to get rid of the white spaces in WordList.java
     * It was removed because of errors it caused when using backspace.
     * The problem was that there was no way to remove old text after a backspace from the deque.
     * it used deque.removeLast however if the previous line was white space it would still remove
     * the last element in the deque
     * A solution would be to record all the positions in a hashmap and remove the last element only if
     * the last line (by position) was blank space.
     */
    String V;
    Deque<ArrayList<String>> sentences = new LinkedList<>();
    public void setWords(){
        String str = V.replaceAll("[^\\p{ASCII}]", " ");
        String[] lines = str.trim().split("(\\s+)");
        if(!(lines[0].isBlank())) {
            ArrayList<String> words = new ArrayList<>(List.of(lines));
            setSentences(words);
        }
        //System.out.println(Arrays.toString(lines));

    }

    public void setSentences(ArrayList<String> words){
        sentences.add(words);
    }
    public void resetSentences(){
        sentences.removeLast();
    }

    /**
     * The following is test code for the same problem in TextPane document listener.
     * It will check if the line (by position) is blank space.
     * To save time it has been commented out.
     */

    /*public String getText(){

        int caretPosition = textPane.getDocument().getLength();
        Element root = textPane.getDocument().getDefaultRootElement();
        StringBuilder text = new StringBuilder("1" + System.getProperty("line.separator"));
        for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
            text.append(i).append(System.getProperty("line.separator"));
        }
        return text.toString();
    }

    public void wordIndex()  throws BadLocationException {
        int caretPosition = textPane.getDocument().getLength();
        Element root = textPane.getDocument().getDefaultRootElement();
        int row = root.getElementIndex(caretPosition);
        String l = textPane.getDocument().getText(lineStart[0], caretPosition - lineStart[0]);
        System.out.println("the string "+ textPane.getDocument().getText(lineStart[0], caretPosition - lineStart[0]));
        boolean o = l.matches("(^[ \\t\\n]*$)");
        System.out.println("Previous blank? "+o);
        System.out.println("before x "+x[0]);
        System.out.println("row "+row);
        if (row > x[0]) {
            String lines = textPane.getDocument().getText(lineStart[0], caretPosition - lineStart[0]);

            if(MainFrame.focus.matches("Left")){
                wordList.setV(lines);
                wordList.setRelatedSources(MainFrame.focus);
                //System.out.println(wordList.toString());
            } else {
                wordList2.setV(lines);
                wordList2.setRelatedSources(MainFrame.focus);
                //System.out.println(wordList2.toString());
            }
            x[0]++;
            System.out.println("after x "+x[0]);
            System.out.println("after row "+ row);
            lineStart[0] = caretPosition;
        }else if(row < x[0]){
            if(MainFrame.focus.matches("Left")){
                wordList.resetSentences();
            } else {
                wordList2.resetSentences();
            }
            x[0]--;
            lineStart[0] = caretPosition;
        }
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        lines.setText(getText());
        try {
            wordIndex();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent de) {
        lines.setText(getText());
        try {
            wordIndex();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        lines.setText(getText());
        try {
            wordIndex();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }


});*/
}
