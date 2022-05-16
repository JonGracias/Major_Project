package Maps;

import java.util.ArrayList;
import java.util.List;

public class WordList {
    String V;
    public list<ArrayList<String>> sentences = new list<>();

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
        //String str = V.replaceAll("[^\\p{ASCII}]", " ");
        String str = V.replaceAll("(\\w+)\\p{Punct}(\\s|$)", "$1$2");
        String[] lines = str.trim().split("(\\s+)");
        if(!(lines[0].isBlank())) {
            ArrayList<String> words = new ArrayList<>(List.of(lines));
            setSentences(words);
        }
        //System.out.println(Arrays.toString(lines));

    }

    public void setSentences(ArrayList<String> words){
        sentences.addNode(words);
        BstWords wordTree = BstWords.createTree(words);
        wordTree.traversePreOrder();
        wordTree.traverseInOrder();
        wordTree.traversePostOrder();
    }
    public void resetSentences(){
        sentences.removeLast();
    }

    public void setRelatedSources(String source){
        relatedSources = source;

    }
    public void print() {
       //sentences.printNodes();
      // sentences.printMiddle();
    }
}
