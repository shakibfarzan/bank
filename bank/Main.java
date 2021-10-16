package bank;

import searchStructures.BinarySearchTree;
import searchStructures.MyHashMap;
import searchStructures.MyLinkedList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Double, String> h = new BinarySearchTree<>();
        h.insert(100.782, "a");
        h.insert(1000.0, "b");
        h.insert(76.0, "c");
        h.insert(1000.769, "d");
        h.print();
    }
}
