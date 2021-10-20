package bank;

/**
 *
 * @author hooman
 */


import SortAlgorithms.QuickSort;

import java.util.*;

public class Bank1 implements Bank<ArrayList>
{
    private String name;
    private ArrayList<Account>accounts;

    public Bank1(String name)
    {
            this.name=name;
            accounts=new ArrayList<Account>();
    }
    
    public Account findAccount(int id) // find the account for the given id. Returns null if not found
    {
        for(Account acc: accounts){
            if (acc.getID()==id){
                return acc;
            }
        }
        return null;
    }
	
    public boolean addAccount(Account account)
    {
        for (Account acc: accounts) {
            if(acc.getID() == account.getID()){
                return false;
            }
        }
        return accounts.add(account);
    }

    public void printAccounts()
    {
        for (Account acc:accounts)
        {
            acc.print();
        }
    }

    public double  calcTotalBalance()
    {
        double total = 0;
        for (Account acc : accounts) 
        {
            total += acc.getBalance();
        }
        return total;
    }
	
	
    public ArrayList<String> populateDistinctCityList()
    {
        ArrayList<String> cities = new ArrayList<>();
        for (Account acc: accounts) {
            boolean hasCity = false;
            String city = acc.getCity();
            for (String c: cities) {
                if (c.equals(city)) {
                    hasCity = true;
                    break;
                }
            }
            if (!hasCity){
                cities.add(city);
            }

        }
        return cities;
    }

    public ArrayList<Double> getTotalBalancePerCity(){
        return getTotalBalancePerCity(populateDistinctCityList());
    }

    private ArrayList<Double> getTotalBalancePerCity(ArrayList<String> cities)
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

    public ArrayList<Integer> getTotalCountPerCity(){
        return getTotalCountPerCity(populateDistinctCityList());
    }

    private ArrayList<Integer> getTotalCountPerCity(ArrayList<String> cities)
    {
        ArrayList<Integer> counts = new ArrayList<>();
        for (String city: cities) {
            int count = 0;
            for (Account acc : accounts) {
                if (acc.getCity().equals(city)) {
                    count++;
                }
            }
            counts.add(count);
        }
        return counts;
    }
    public void reportCity(ArrayList<String> cities, ArrayList<Integer> counts, ArrayList<Double> totals)
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

    @Override
    public ArrayList<Integer> getTotalCountPerRangeUsingSort(ArrayList<Integer> ranges, Account[] sortedAccounts) {
        int left = 0, right = sortedAccounts.length-1;
        int minIndex = 0;
        int maxIndex = 0;
        ArrayList<Integer> totalCounts = new ArrayList<>();
        for (int i = 0; i < ranges.size()-1; i++) {
            double min = ranges.get(i);
            double max = ranges.get(i+1);
            //Using binary search
            while (left <= right)
            {
                int mid = (left + right) / 2;
                if ((sortedAccounts[mid].getBalance() >= min && mid==sortedAccounts.length-1)||(sortedAccounts[mid].getBalance() <= max && sortedAccounts[mid+1].getBalance()> max)) {
                    maxIndex = mid;
                    break;
                }
                else if (sortedAccounts[mid].getBalance() > max) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
            int count = maxIndex-minIndex+1;
            minIndex = maxIndex + 1;
            right = sortedAccounts.length-1;
            left = minIndex;
            totalCounts.add(count);
        }
        return totalCounts;
    }

    @Override
    public Account[] sortAccounts() {
        Account[] accountsArray = new Account[accounts.size()];
        // Copy accounts to array
        for (int i = 0; i < accounts.size(); i++) {
            accountsArray[i] = accounts.get(i);
        }
        // Sort accounts using quick sort
        QuickSort<Account> quickSort = new QuickSort<>();
        quickSort.sort(accountsArray);
        return accountsArray;
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

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                '}';
    }
}

