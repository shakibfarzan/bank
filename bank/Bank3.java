package bank;
import searchStructures.Item;
import searchStructures.MyHashMap;

import java.util.*;

class Bank3 implements BankInterface<MyHashMap>
{
    public String name;
    public MyHashMap<Integer,Account> accounts;
    private static int length = 10000;
    public Bank3(String name)
    {
        this.name=name;
        accounts=new MyHashMap<>(length);
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
        List<Item<Integer, Account>> accountToList = accounts.convertToList();
        for (Item<Integer, Account> item:accountToList)
        {
            item.data.print();
        }
    }


    public MyHashMap<String,Double> getTotalBalancePerCity()
    {
        MyHashMap<String, Double> totalBalances = new MyHashMap<>(length);
        List<Item<Integer, Account>> accountsToList = accounts.convertToList();
        for (Item<Integer, Account> item: accountsToList){
            String city = item.data.getCity();
            Double val = totalBalances.search(city);
            if(val == null){
                totalBalances.insert(city, item.data.getBalance());
            }else{
                totalBalances.insert(city, val + item.data.getBalance());
            }
        }
        return totalBalances;
    }

    public MyHashMap<String,Integer> getTotalCountPerCity()
    {
        MyHashMap<String, Integer> totalCounts = new MyHashMap<>(length);
        List<Item<Integer, Account>> accountsToList = accounts.convertToList();
        for (Item<Integer, Account> item: accountsToList){
            String city = item.data.getCity();
            Integer val = totalCounts.search(city);
            if(val == null){
                totalCounts.insert(city, 1);
            }else{
                totalCounts.insert(city, ++val);
            }
        }
        return totalCounts;
    }

    public void  reportCity(MyHashMap<String,Double> balances,MyHashMap<String,Integer> counts)
    {
        System.out.println();
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        List<Item<String, Double>> balancesToList = balances.convertToList();
        for (Item<String, Double> item: balancesToList)
        {
            String city = item.key;
            System.out.println(city+"\t \t "+balances.search(city)+" \t \t "+balances.search(city)/(double)counts.search(city));
        }
    }

    public MyHashMap<Integer,Integer> getTotalCountPerRange( ArrayList<Integer> ranges)
    {
        MyHashMap<Integer, Integer> totalCountsPerRange = new MyHashMap<>(length);
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
    public void reportRanges( ArrayList<Integer> ranges,MyHashMap<Integer,Integer> countsPerRange)
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
        return "Bank3{" +
                "name='" + name + '\'' +
                '}';
    }
}