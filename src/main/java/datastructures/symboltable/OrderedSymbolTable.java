package datastructures.symboltable;

import java.util.List;

public class OrderedSymbolTable<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value>{
    Key[] keys;
    Value[] values;
    int IN;

    OrderedSymbolTable() {
        // Start with size 10
        keys = (Key[]) new Comparable[10];
        values = (Value[]) new Comparable[10];
        IN = 0;
    }

    private void resizeArray(int capacity) {
        Key[] newKeys = (Key[]) new Comparable[capacity];
        Value[] newValues = (Value[]) new Comparable[capacity];

        for (int i=0; i < IN; i++) {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        }
        keys = newKeys;
        values = newValues;
    }

    @Override
    public void put(Key key, Value val) {
        int keyIndex = getIndex(key);
        if (keyIndex != -1) {
            // Update existing key
            values[keyIndex] = val;
            return;
        }

        // Insert a new Key
        keys[IN] = key;
        values[IN] = val;
        // Sort the key in place O(N)
        for (int i = IN; i > 0; i--) {
            if (keys[i].compareTo(keys[i-1]) < 0) {
                // Swap
                Key tempKey = keys[i-1];
                Value tempValue = values[i-1];
                keys[i-1] = keys[i];
                values[i-1] = values[i];
                keys[i] = tempKey;
                values[i] = tempValue;
            }
        }

        IN++;
        if (IN > keys.length) {
            resizeArray(2 * keys.length);
        }
    }

    @Override
    public Value get(Key key) {
        int index = getIndex(key);
        if (index == -1) {
            return null;
        } else {
            return values[index];
        }
    }

    private int getIndex(Key key) {
        int low = 0;
        int high = IN-1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key.compareTo(keys[mid]) < 0) {
                high = mid - 1;
            } else if (key.compareTo(keys[mid]) > 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    @Override
    public void delete(Key key) {
        int keyIndex = getIndex(key);
        if (keyIndex == -1) {
            // Not found
            return;
        }
        // Left shift elements
        for (int i = keyIndex+1; i < IN; i++) {
            keys[i-1] = keys[i];
            values[i-1] = values[i];
        }
        keys[IN-1] = null;
        values[IN-1] = null;
        IN--;
    }

    @Override
    public boolean contains(Key key) {
        return getIndex(key) != -1;
    }

    @Override
    public boolean isEmpty() {
        return IN == 0;
    }

    @Override
    public int size() {
        return IN;
    }

    @Override
    public Iterable<Key> keys() {
        return List.of(keys);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i=0; i < IN; i++) {
            result.append(keys[i]).append(" : ").append(values[i]).append("\t");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        OrderedSymbolTable<String, Integer> ost = new OrderedSymbolTable<>();
        System.out.println(ost);
        ost.put("S", 0);
        ost.put("E", 1);
        ost.put("A", 2);
        ost.put("R", 3);
        ost.put("C", 4);
        ost.put("H", 5);
        ost.put("H", 10);
        System.out.println(ost);
        System.out.println(ost.get("E"));
        System.out.println(ost.get("Z"));
        ost.delete("A");
        ost.delete("S");
        System.out.println(ost);
    }
}
