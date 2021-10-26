package bank;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author hooman
 */
 
import java.util.*;

class Bank2 implements Banks<HashMap>
{
    public String name;
	public HashMap<Integer,Account>accounts;
    
    public Bank2(String name)
	{
		this.name=name;
		accounts=new HashMap<Integer,Account>();
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
            accounts.put(account.getID(), account);
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
    
    
    public HashMap<String,Double> getTotalBalancePerCity()
    {
        HashMap<String, Double> totalBalances = new HashMap<>();
        for (Account acc: accounts.values()){
            String city = acc.getCity();
            Double val = totalBalances.get(city);
            if(val == null){
                totalBalances.put(city, acc.getBalance());
            }else{
                totalBalances.put(city, val + acc.getBalance());
            }
        }
        return totalBalances;
    }
	
    public HashMap<String,Integer> getTotalCountPerCity()       
    {
        HashMap<String, Integer> totalCounts = new HashMap<>();
        for (Account acc: accounts.values()){
            String city = acc.getCity();
            Integer val = totalCounts.get(city);
            if(val == null){
                totalCounts.put(city, 1);
            }else{
                totalCounts.put(city, ++val);
            }
        }
        return totalCounts;
    }
    
    public void reportCity(HashMap<String,Double> balances,HashMap<String,Integer> counts)
    {
        System.out.println();
		System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        for (String city: balances.keySet())
        {
            System.out.println(city+"\t \t "+balances.get(city)+" \t \t "+balances.get(city)/(double)counts.get(city)); 
        }
    }
	
//    public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges)
//    {
//        ArrayList<Integer> counts = new ArrayList<>();
//        for (int i = 0; i < ranges.size()-1; i++) {
//            int counter = 0;
//            for (Account acc: accounts.values()){
//                if(acc.getBalance() >= ranges.get(i) && acc.getBalance() < ranges.get(i+1)){
//                    counter++;
//                }
//            }
//            counts.add(counter);
//        }
//        return counts;
//    }

    public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges) {
        //Sort accounts
        Account[] sortedAccounts = Banks.convertListToArrayAccount(accounts.values());
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
        for (Account acc : accounts.values())
        {
            total += acc.getBalance();
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
        return "Bank2{" +
                "name='" + name + '\'' +
                '}';
    }
}
 
