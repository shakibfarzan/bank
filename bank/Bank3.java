package bank;
import searchStructures.MyHashMap;

import java.util.*;

class Bank3
{
    public String name;
    public MyHashMap<Integer,Account> accounts;
    private static int length = 100;
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
        for (Account acc:accounts.values())
        {
            acc.print();
        }
    }


    public MyHashMap<String,Double> getTotalBalancePerCity()
    {
        MyHashMap<String, Double> totalBalances = new MyHashMap<>(length);
        for (Account acc: accounts.values()){
            String city = acc.getCity();
            Double val = totalBalances.search(city);
            if(val == null){
                totalBalances.insert(city, acc.getBalance());
            }else{
                totalBalances.insert(city, val + acc.getBalance());
            }
        }
        return totalBalances;
    }

    public MyHashMap<String,Integer> getTotalCountPerCity()
    {
        MyHashMap<String, Integer> totalCounts = new MyHashMap<>(length);
        for (Account acc: accounts.values()){
            String city = acc.getCity();
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
        for (String city: balances.keys())
        {
            System.out.println(city+"\t \t "+balances.search(city)+" \t \t "+balances.search(city)/(double)counts.search(city));
        }
    }

    public MyHashMap<Integer,Integer> getTotalCountPerRange( ArrayList<Integer> ranges)
    {
        MyHashMap<Integer, Integer> totalCountsPerRange = new MyHashMap<>(length);
        for (Account acc: accounts.values()){
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

}