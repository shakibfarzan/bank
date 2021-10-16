package bank;

import searchStructures.BinarySearchTree;

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

        return total;
    }


    public ArrayList<Double> getTotalBalancePerCity(ArrayList<String> cities)
    {
        ArrayList<Double> balances = new ArrayList<>();
        for (String city: cities) {
            double totalBalance = 0;
            for (Account acc : accounts) {
                if (acc.getCity().equals(city)) {
                    totalBalance += acc.getBalance();
                }
            }
            balances.add(totalBalance);
        }
        return balances;
    }

    public BinarySearchTree<String,Integer> getTotalCountPerCity()
    {
        BinarySearchTree<String,Integer> counts = new BinarySearchTree<>();

        return counts;
    }
    public void reportTotalPerCity(ArrayList<String> cities, ArrayList<Integer> counts,ArrayList<Double> totals)
    {
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        for (int i=0;i<counts.size();i++)
            System.out.println(cities.get(i)+" \t \t "+totals.get(i)+" \t \t "+totals.get(i)/(double)counts.get(i));
    }


    public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges)
    {
        ArrayList<Integer> counts = new ArrayList<>();
        for (int i = 0; i < ranges.size()-1; i++) {
            int counter = 0;
            for (Account acc: accounts){
                if(acc.getBalance() >= ranges.get(i) && acc.getBalance() < ranges.get(i+1)){
                    counter++;
                }
            }
            counts.add(counter);
        }
        return counts;
    }
    public void reportRanges(ArrayList<Integer> ranges, ArrayList<Integer> counts)
    {
        System.out.println();
        for (int i=0;i<ranges.size()-1;i++)
        {
            System.out.println("Number of accounts between "+ranges.get(i)+" and "+ranges.get(i+1)+" = "+counts.get(i) );
        }
        System.out.println();
    }
}
