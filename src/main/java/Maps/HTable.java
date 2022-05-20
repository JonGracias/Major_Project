package Maps;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

public class HTable extends Hashtable<Integer, WordPositions> {
    public Hashtable<Integer, WordPositions> hTable = new Hashtable<>();
    Set<Entry<Integer, WordPositions>> entrySet = hTable.entrySet();
    Entry<Integer, WordPositions> entry;
    public static int size = 0;
    public HTable(){

    }
    public HTable(ArrayList<WordPositions> value){
        createTable(value);
    }

    public void createTable(@NotNull ArrayList<WordPositions> value) {
        for (WordPositions pair : value) {
            add(size, pair);
            size++;
        }
    }

    public void add(int key, WordPositions value){
        hTable.put(key, value);
    }

    public boolean isEmpty(){
        return size==0;
    }

    public static long compute_hash(String word){
        int p = 53;
        int m = (int) (1e9+9);
        long hash_value = 0;
        long p_pow = 1;
        char[] chars = word.toCharArray();
        for(char c: chars){
            hash_value = (hash_value + (c - 'a' + 1) * p_pow) % m;
            p_pow = (p_pow * p) % m;
        }
        return hash_value;
    }


    public StringBuilder toPrint(){
        StringBuilder sb = new StringBuilder();
        for (Entry<Integer, WordPositions> integerWordPositionsEntry : entrySet) {
            entry = integerWordPositionsEntry;
            sb.append("Word: ").append(entry.getValue());
            sb.append(System.lineSeparator());
        }
        return sb;
    }
}
