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
        
    }
	
	public boolean addAccount(Account account)
    {
        
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
        
    }
	
    public HashMap<String,Integer> getTotalCountPerCity()       
    {
	
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
        
    }
    public void reportRanges( ArrayList<Integer> ranges,HashMap<Integer,Integer> countsPerRange)
    {

    }
   
}
 
