package bank;

/**
 *
 * @author hooman
 */


import java.util.*;

public class Bank
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
    public void reportTotalPerCity(ArrayList<String> cities, ArrayList<Integer> counts,ArrayList<Double> totals)
    {
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        for (int i=0;i<counts.size();i++)
            System.out.println(cities.get(i)+" \t \t "+totals.get(i)+" \t \t "+totals.get(i)/(double)counts.get(i));  
    }
    
	
    public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges)
    {
        ArrayList<Integer> counts = new ArrayList<>();
        for (int i = 0; i < ranges.size()-1; i+=1) {
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

