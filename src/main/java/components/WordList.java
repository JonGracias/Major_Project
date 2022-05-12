package components;

import java.util.*;

public class WordList {
    int K;
    String V;
    Queue<ArrayList<String>> sentences = new LinkedList<>();
    int index;

    public WordList(){

    }

    public WordList(String V){
        this.V = V;

    }

    public void setV(String text){
        this.V = text;
    }

    public void setIndex(int i){
        this.index = i;
    }

    public void setLines(){
        String[] lines = V.split("\\s");
        ArrayList<String> words = new ArrayList<>(List.of(lines));
        System.out.println("words: "+words);
        setNewLine(words);

    }

    public void setNewLine(ArrayList<String> words){
        sentences.add(words);
        System.out.println("sentences: "+sentences);

    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        if(!this.sentences.isEmpty()) {
            for (ArrayList<String> s : this.sentences) {
                result.append(s);
            }
            return String.valueOf(result);
        }
        return null;
    }

}
