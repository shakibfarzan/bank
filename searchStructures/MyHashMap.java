package searchStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MyHashMap<K, V> extends SearchStructure<K, V>{
    private MyLinkedList<K, V>[] hashtable;
    private int length;

    public MyHashMap(int length) {
        this.length = length;
        hashtable = new MyLinkedList[this.length];
    }

    /**
     * hash function
     *
     * @param key the key number of the hash map
     * @return hash
     */
    private int hashFunction(K key) {
        int hash = key.hashCode() % length;
        return ( hash < 0)? -hash: hash;
    }



    @Override
    public boolean insert(K key, V data) {
        int index = hashFunction(key);
        if (hashtable[index] == null) {
            hashtable[index] = new MyLinkedList<>();
        }
        return hashtable[index].insert(key, data);
    }

    @Override
    public boolean delete(K key) {
        int index = hashFunction(key);
        return hashtable[index].delete(key);
    }

    @Override
    public V search(K key) {
        int index = hashFunction(key);
        return (hashtable[index]==null)?null:hashtable[index].search(key);
    }

    public Collection<V> values(){
        Collection <V> values = new ArrayList<>();
        for (MyLinkedList<K, V> l : hashtable) {
            if (l == null) continue;
            MyLinkedList.Node pointer = l.head;
            while (pointer != null) {
                values.add((V) pointer.item.data);
                pointer = pointer.next;
            }
        }
        return values;
    }

    public Collection<K> keys(){
        Collection<K> keys = new ArrayList<>();
        for (MyLinkedList<K, V> l: hashtable){
            if (l == null) continue;
            MyLinkedList.Node pointer = l.head;
            while (pointer != null) {
                keys.add((K) pointer.item.key);
                pointer = pointer.next;
            }
        }
        return keys;
    }

    public void print() {
        int lineNumber=0;
        for (MyLinkedList<K, V> mm : hashtable) {
            System.out.print("line number "+ lineNumber+" : ");
            if (mm != null) {
                mm.print();
            }
            System.out.println();
            lineNumber++;
        }
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "hashtable=" + Arrays.toString(hashtable) +
                ", length=" + length +
                '}';
    }
}