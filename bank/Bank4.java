package bank;

import java.util.*;

public class Bank4 implements Banks<BinarySearchTree>
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
    public void  reportCity(ArrayList<String> cities, BinarySearchTree<String,Double> balances, BinarySearchTree<String,Integer> counts) {
        System.out.println();
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        List<Item<String, Double>> balancesToList = balances.convertToList();
        int i = 0;
        for (Item<String, Double> item: balancesToList){
            String city = cities.get(i);
            Double balance = item.data;
            System.out.println(city + "\t \t " + balance + " \t \t " + balance / (double) counts.search(city));
            i++;
        }

    }

    public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges)
    {
        ArrayList<Integer> counts = new ArrayList<>();
        List<Item<Integer, Account>> accountsToList = accounts.convertToList();
        for (int i = 0; i < ranges.size()-1; i++) {
            int counter = 0;
            for (Item<Integer, Account> item: accountsToList){
                if(item.data.getBalance() >= ranges.get(i) && item.data.getBalance() < ranges.get(i+1)){
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
        List<Item<Integer, Account>> accountsToList = accounts.convertToList();
        Account[] accountsArray = new Account[accountsToList.size()];
        // Copy accounts to array
        int i = 0;
        for (Item<Integer, Account> item: accountsToList){
            accountsArray[i] = item.data;
            i++;
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
        return "Bank4{" +
                "name='" + name + '\'' +
                '}';
    }
}
