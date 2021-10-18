package bank;

import searchStructures.BinarySearchTree;
import searchStructures.Item;
import searchStructures.MyHashMap;
import searchStructures.MyLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//       Bank4 b = new Bank4("SSbank");
//       System.out.println(b.addAccount(new Account(0, "a", 200.76, "a")));
//       System.out.println(b.addAccount(new Account(1,"b", 300.12, "b")));
//       System.out.println(b.addAccount(new Account(2,"c", 201.1,"c")));
//       System.out.println(b.findAccount(3));
//       System.out.println(b.findAccount(0));
//       System.out.println(b.calcTotalBalance());

//        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
//        bst.insert("a", 1);
//        bst.insert("b", 2);
//        bst.insert("c", 3);
//        bst.insert("d",4);
//        bst.insert("e", 5);
        Random rand = new Random();
        String[] cities = {"Sari", "Shiraz", "Tehran", "Kerman", "Tabriz", "Zahedan", "Arak", "Yazd", "Isfahan", "Hamedan","Mashhad", "Abadan"};
        String[] array = new String[10];
        for (int i = 0; i < 10; i++) {
            array[i] = cities[rand.nextInt(cities.length)];
        }
        System.out.println(Arrays.toString(array));
    }
}
