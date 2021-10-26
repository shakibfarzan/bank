package bank;

/**
 *
 * @author hooman
 */


import java.util.*;

public class Bank implements Banks<ArrayList>
{
    private String name;
    private ArrayList<Account>accounts;

    public Bank(String name)
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

    public ArrayList<Integer> getTotalCountPerCity(){
        return getTotalCountPerCity(populateDistinctCityList());
    }

    public ArrayList<Integer> getTotalCountPerCity(ArrayList<String> cities)
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
    public void reportTotalPerCity(ArrayList<String> cities, ArrayList<Integer> counts, ArrayList<Double> totals)
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

//    public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges) {
//        //Sort accounts
//        Account[] sortedAccounts = Banks.convertListToArrayAccount(accounts);
////        QuickSort<Account> quickSort = new QuickSort<>();
////        quickSort.sort(sortedAccounts);
////        MergeSort<Account> mergeSort = new MergeSort<>();
////        mergeSort.sort(sortedAccounts);
//        HeapSort<Account> heapSort = new HeapSort<>();
//        heapSort.sort(sortedAccounts);
//        int left = 0, leftFirst = 0, right = sortedAccounts.length-1;
//        double minimumRanges = ranges.get(0);
//        if (minimumRanges!=0){
//            while (leftFirst <= right)
//            {
//                int mid = (leftFirst + right) / 2;
//                double balance = sortedAccounts[mid].getBalance();
//                if ((balance >= minimumRanges && mid==0)||(balance >= minimumRanges && sortedAccounts[mid-1].getBalance()< minimumRanges)) {
//                    left = mid;
//                    break;
//                }
//                else if (balance > minimumRanges) {
//                    right = mid - 1;
//                }
//                else {
//                    leftFirst = mid + 1;
//                }
//            }
//        }
//        int minIndex = left;
//        int maxIndex = 0;
//        right = sortedAccounts.length-1;
//        ArrayList<Integer> totalCounts = new ArrayList<>();
//        for (int i = 0; i < ranges.size()-1; i++) {
//            double max = ranges.get(i+1);
//            //Using binary search
//            boolean has = false;
//            while (left <= right)
//            {
//                int mid = (left + right) / 2;
//                double balance = sortedAccounts[mid].getBalance();
//                if ((balance < max && mid==sortedAccounts.length-1)
//                        ||(balance < max && sortedAccounts[mid+1].getBalance()>= max)) {
//                    maxIndex = mid;
//                    has = true;
//                    break;
//                }
//                else if (balance >= max) {
//                    right = mid - 1;
//                }
//                else {
//                    left = mid + 1;
//                }
//            }
//            int count = has ? maxIndex-minIndex+1:0;
//            minIndex = has ? maxIndex + 1:minIndex;
//            right = sortedAccounts.length-1;
//            left = minIndex;
//            totalCounts.add(count);
//        }
//        return totalCounts;
//    }

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

