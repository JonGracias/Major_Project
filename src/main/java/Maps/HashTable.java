package Maps;

import java.util.ArrayList;
import java.util.Hashtable;

public class HashTable {

    public Hashtable<Integer, String> table;
    public int key;
    String value;

    public HashTable(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public Hashtable<Integer, String> createTable(ArrayList<String> value ) {
        table = new Hashtable<>();
        for( int i = 0; i < value.size(); i++ ) {
            table.put(key,value.get(i));
        }

        return table;
    }
}
