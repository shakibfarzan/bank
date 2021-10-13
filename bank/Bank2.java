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

class Bank2 
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
    
    public void  reportCity(HashMap<String,Double> balances,HashMap<String,Integer> counts)
    {
        System.out.println();
		System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        for (String city: balances.keySet())
        {
            System.out.println(city+"\t \t "+balances.get(city)+" \t \t "+balances.get(city)/(double)counts.get(city)); 
        }
    }
	
    public HashMap<Integer,Integer> getTotalCountPerRange( ArrayList<Integer> ranges)
    {
        HashMap<Integer, Integer> totalCountsPerRange = new HashMap<>();
        for (Account acc: accounts.values()){
            double balance = acc.getBalance();
            for (int i = 0; i < ranges.size()-1; i++) {
                Integer min = ranges.get(i);
                Integer max = ranges.get(i+1);
                if (balance >= min && balance < max){
                    Integer val = totalCountsPerRange.get(max);
                    if (val == null){
                        totalCountsPerRange.put(max, 1);
                    }else{
                        totalCountsPerRange.put(max, ++val);
                    }
                }
            }
        }
        return totalCountsPerRange;
    }
    public void reportRanges( ArrayList<Integer> ranges,HashMap<Integer,Integer> countsPerRange)
    {
        System.out.println();
        for (int i=0;i<ranges.size()-1;i++)
        {
            Integer max = ranges.get(i+1);
            Integer count = countsPerRange.get(max);
            System.out.println("Number of accounts between "+ranges.get(i)+" and "+max+" = "+(count==null?0:count));
        }
        System.out.println();

    }
   
}
 
