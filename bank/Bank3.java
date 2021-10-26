package bank;

import java.util.*;

class Bank3 implements Banks<MyHashMap>
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
        return accounts.get(id);
    }

    public boolean addAccount(Account account)
    {
        if (accounts.get(account.getID())!=null){
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
            Double val = totalBalances.get(city);
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
            Integer val = totalCounts.get(city);
            if(val == null){
                totalCounts.insert(city, 1);
            }else{
                totalCounts.insert(city, ++val);
            }
        }
        return totalCounts;
    }

    public void  reportCity(ArrayList<String> cities,MyHashMap<String,Double> balances,MyHashMap<String,Integer> counts)
    {
        System.out.println();
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        List<Item<String, Double>> balancesToList = balances.convertToList();
        int i = 0;
        for (Item<String, Double> item: balancesToList)
        {
            String city = cities.get(i);
            System.out.println(city+"\t \t "+balances.get(city)+" \t \t "+balances.get(city)/(double)counts.get(city));
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

    @Override
    public double calcTotalBalance() {
        double total = 0;
        List<Item<Integer, Account>> accountsToList = accounts.convertToList();
        for (Item<Integer, Account> acc : accountsToList)
        {
            total += acc.data.getBalance();
        }
        return total;
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
        return "Bank3{" +
                "name='" + name + '\'' +
                '}';
    }
}