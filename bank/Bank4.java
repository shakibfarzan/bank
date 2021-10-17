package bank;

import searchStructures.BinarySearchTree;
import searchStructures.MyHashMap;

import java.util.*;

public class Bank4
{
    private String name;
    private BinarySearchTree<Integer, Account> accounts;

    public Bank4(String name)
    {
        this.name=name;
        accounts=new BinarySearchTree<>();
    }

    public Account findAccount(int id) // find the account for the given id. Returns null if not found
    {
        return accounts.search(id);
    }

    public boolean addAccount(Account account)
    {
        if (accounts.search(account.getID())!=null){
            return false;
        }else{
            accounts.insert(account.getID(), account);
            return true;
        }
    }

    public void printAccounts()
    {
        accounts.print();
    }

    public double calcTotalBalance()
    {
        double total = 0;
        Stack<BinarySearchTree.Node> stack = new Stack<>();
        stack.push(accounts.root);
        while(!stack.isEmpty()){
            var current = stack.pop();
            Account acc = (Account) current.getItem().data;
            total += acc.getBalance();
            if (current.getLeft() != null){
                stack.push(current.getLeft());
            }
            if (current.getRight() != null){
                stack.push(current.getRight());
            }
        }
        return total;
    }


    public BinarySearchTree<String, Double> getTotalBalancePerCity()
    {
        BinarySearchTree<String,Double> totalBalances = new BinarySearchTree<>();
        Stack<BinarySearchTree.Node> stack = new Stack<>();
        stack.push(accounts.root);
        while (!stack.isEmpty()){
            var current = stack.pop();
            Account acc = (Account) current.getItem().data;
            String city = acc.getCity();
            Double bal = totalBalances.search(city);
            if (bal == null){
                totalBalances.insert(city, acc.getBalance());
            }else{
                totalBalances.insert(city, bal + acc.getBalance());
            }
            if (current.getLeft() != null){
                stack.push(current.getLeft());
            }
            if (current.getRight() != null){
                stack.push(current.getRight());
            }
        }
        return totalBalances;
    }

    public BinarySearchTree<String,Integer> getTotalCountPerCity()
    {
        BinarySearchTree<String,Integer> counts = new BinarySearchTree<>();
        Stack<BinarySearchTree.Node> stack = new Stack<>();
        stack.push(accounts.root);
        while (!stack.isEmpty()){
            var current = stack.pop();
            Account acc = (Account) current.getItem().data;
            String city = acc.getCity();
            Integer count = counts.search(city);
            if (count == null){
                counts.insert(city, 1);
            }else{
                counts.insert(city, count + 1);
            }
            if (current.getLeft() != null){
                stack.push(current.getLeft());
            }
            if (current.getRight() != null){
                stack.push(current.getRight());
            }
        }
        return counts;
    }
    public void  reportCity(BinarySearchTree<String,Double> balances, BinarySearchTree<String,Integer> counts) {
        System.out.println();
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        Stack<BinarySearchTree.Node> stack = new Stack<>();
        stack.push(balances.root);
        while (!stack.isEmpty()) {
            var current = stack.pop();
            String city = (String) current.getItem().key;
            Double balance = (Double) current.getItem().data;
            System.out.println(city + "\t \t " + balance + " \t \t " + balance / (double) counts.search(city));
            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
            if (current.getRight() != null) {
                stack.push(current.getRight());
            }
        }
    }

    public BinarySearchTree<Integer,Integer> getTotalCountPerRange(ArrayList<Integer> ranges)
    {
        BinarySearchTree<Integer,Integer> totalCountsPerRange = new BinarySearchTree<>();
        Stack<BinarySearchTree.Node> stack = new Stack<>();
        stack.push(accounts.root);
        while (!stack.isEmpty()){
            var current = stack.pop();
            Account acc = (Account) current.getItem().data;
            double balance = acc.getBalance();
            for (int i = 0; i < ranges.size()-1; i++) {
                Integer min = ranges.get(i);
                Integer max = ranges.get(i+1);
                if (balance >= min && balance < max){
                    Integer val = totalCountsPerRange.search(max);
                    if (val == null){
                        totalCountsPerRange.insert(max, 1);
                    }else{
                        totalCountsPerRange.insert(max, ++val);
                    }
                }
            }
            if (current.getLeft() != null){
                stack.push(current.getLeft());
            }
            if (current.getRight() != null){
                stack.push(current.getRight());
            }
        }
        return totalCountsPerRange;
    }
    public void reportRanges(ArrayList<Integer> ranges, BinarySearchTree<Integer,Integer> countsPerRange)
    {
        System.out.println();
        for (int i=0;i<ranges.size()-1;i++)
        {
            Integer max = ranges.get(i+1);
            Integer count = countsPerRange.search(max);
            System.out.println("Number of accounts between "+ranges.get(i)+" and "+max+" = "+(count==null?0:count));
        }
        System.out.println();
    }
}
