package bank;

import searchStructures.BinarySearchTree;
import searchStructures.Item;
import searchStructures.MyHashMap;

import java.util.*;

public class Bank4 implements BankInterface<BinarySearchTree>
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
        List<Item<Integer, Account>> accountsToList = accounts.convertToList();
        for (Item<Integer, Account> item: accountsToList){
            total+=item.data.getBalance();
        }
        return total;
    }


    public BinarySearchTree<String, Double> getTotalBalancePerCity()
    {
        BinarySearchTree<String,Double> totalBalances = new BinarySearchTree<>();
        List<Item<Integer, Account>> accountsToList = accounts.convertToList();
        for (Item<Integer, Account> item: accountsToList){
            String city = item.data.getCity();
            Double bal = totalBalances.search(city);
            if (bal == null){
                totalBalances.insert(city, item.data.getBalance());
            }else{
                totalBalances.insert(city, bal + item.data.getBalance());
            }
        }
        return totalBalances;
    }

    public BinarySearchTree<String,Integer> getTotalCountPerCity()
    {
        BinarySearchTree<String,Integer> counts = new BinarySearchTree<>();
        List<Item<Integer, Account>> accountsToList = accounts.convertToList();
        for (Item<Integer, Account> item: accountsToList){
            String city = item.data.getCity();
            Integer count = counts.search(city);
            if (count == null){
                counts.insert(city, 1);
            }else{
                counts.insert(city, count + 1);
            }
        }
        return counts;
    }
    public void  reportCity(BinarySearchTree<String,Double> balances, BinarySearchTree<String,Integer> counts) {
        System.out.println();
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        List<Item<String, Double>> balancesToList = balances.convertToList();
        for (Item<String, Double> item: balancesToList){
            String city = item.key;
            Double balance = item.data;
            System.out.println(city + "\t \t " + balance + " \t \t " + balance / (double) counts.search(city));
        }

    }

    public BinarySearchTree<Integer,Integer> getTotalCountPerRange(ArrayList<Integer> ranges)
    {
        BinarySearchTree<Integer,Integer> totalCountsPerRange = new BinarySearchTree<>();
        List<Item<Integer, Account>> accountsToList = accounts.convertToList();
        for (Item<Integer, Account> item: accountsToList){
            double balance = item.data.getBalance();
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

    @Override
    public String toString() {
        return "Bank4{" +
                "name='" + name + '\'' +
                '}';
    }
}
