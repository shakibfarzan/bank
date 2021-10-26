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

//    public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges)
//    {
//        ArrayList<Integer> counts = new ArrayList<>();
//        List<Item<Integer, Account>> accountsToList = accounts.convertToList();
//        for (int i = 0; i < ranges.size()-1; i++) {
//            int counter = 0;
//            for (Item<Integer, Account> item: accountsToList){
//                if(item.data.getBalance() >= ranges.get(i) && item.data.getBalance() < ranges.get(i+1)){
//                    counter++;
//                }
//            }
//            counts.add(counter);
//        }
//        return counts;
//    }

    public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges) {
        //Sort accounts
        Account[] sortedAccounts = Banks.convertListOfItemToArrayAccount(accounts.convertToList());
//        QuickSort<Account> quickSort = new QuickSort<>();
//        quickSort.sort(sortedAccounts);
//        MergeSort<Account> mergeSort = new MergeSort<>();
//        mergeSort.sort(sortedAccounts);
        HeapSort<Account> heapSort = new HeapSort<>();
        heapSort.sort(sortedAccounts);

        int left = 0, leftFirst = 0, right = sortedAccounts.length-1;
        double minimumRanges = ranges.get(0);
        if (minimumRanges!=0){
            while (leftFirst <= right)
            {
                int mid = (leftFirst + right) / 2;
                double balance = sortedAccounts[mid].getBalance();
                if ((balance >= minimumRanges && mid==0)||(balance >= minimumRanges && sortedAccounts[mid-1].getBalance()< minimumRanges)) {
                    left = mid;
                    break;
                }
                else if (balance > minimumRanges) {
                    right = mid - 1;
                }
                else {
                    leftFirst = mid + 1;
                }
            }
        }
        int minIndex = left;
        int maxIndex = 0;
        right = sortedAccounts.length-1;
        ArrayList<Integer> totalCounts = new ArrayList<>();
        for (int i = 0; i < ranges.size()-1; i++) {
            double max = ranges.get(i+1);
            //Using binary search
            boolean has = false;
            while (left <= right)
            {
                int mid = (left + right) / 2;
                double balance = sortedAccounts[mid].getBalance();
                if ((balance < max && mid==sortedAccounts.length-1)
                        ||(balance < max && sortedAccounts[mid+1].getBalance()>= max)) {
                    maxIndex = mid;
                    has = true;
                    break;
                }
                else if (balance >= max) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
            int count = has ? maxIndex-minIndex+1:0;
            minIndex = has ? maxIndex + 1:minIndex;
            right = sortedAccounts.length-1;
            left = minIndex;
            totalCounts.add(count);
        }
        return totalCounts;
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
