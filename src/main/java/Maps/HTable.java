package Maps;

import java.util.ArrayList;
import java.util.Hashtable;

public class HTable {
    static Hashtable<Integer, Pair> hTable = new Hashtable<>();
    public HTable(ArrayList<Pair> value){
        createTable(value);
    }

    public static void createTable(ArrayList<Pair> value) {
        for (Pair pair : value) {
            Integer key = pair.getPosition()[0];
            int[] length = {pair.getPosition()[1]};
            String word = pair.getWord();
            HTable.add(key, length, word);
        }
    }

    public static void add(Integer key, int[] length, String word){
        Pair value = new Pair(length, word);
        hTable.put(key,value);
    }

    public String toString(){
        return hTable.toString();
    }
}
