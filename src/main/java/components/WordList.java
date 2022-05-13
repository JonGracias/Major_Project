package components;

import java.util.*;

public class WordList {
    String V;
    public Deque<ArrayList<String>> sentences = new LinkedList<>();

    String relatedSources = "";

    public WordList(){

    }

    public WordList(String V){
            this.V = V;

    }

    public void setV(String text){
            this.V = text;
            setWords();
    }

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

    public void setRelatedSources(String source){
        relatedSources = source;
    }
    public String toString() {
        String sentences = "sentences: "+ this.sentences;
        return (relatedSources + sentences);
    }
}
