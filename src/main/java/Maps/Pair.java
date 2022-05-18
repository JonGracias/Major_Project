package Maps;

import java.util.Arrays;

public class Pair {
    final private int[] position;
    final private String word;

    public Pair(String word,int[] pos) {
        this.position = pos;
        this.word = word.replace(System.lineSeparator(), "");

    }

    public Pair getPair(){
        return new Pair(word, position);
    }

    public int[] getPosition(){
        return position;
    }

    public String getWord(){
        return word;
    }

    @Override
    public String toString() {
        return "("+getWord() + Arrays.toString(getPosition())+")";
    }

}
