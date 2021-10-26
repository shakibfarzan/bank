package bank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Program
{   
    public static void main(String[] args)
    {
        Account acc1 = new Account (1,"Kim",  100,"Sydney");
        Account acc2 = new Account (2,"Jack", 1800,"Sydney");
        Account acc3 = new Account (3,"Jill", 20000,"Tehran");
        Account acc4 = new Account (4,"Robert", 8000,"Tehran");
        Account acc5 = new Account (5,"A", 100000,"Tehran");
        Account acc6 = new Account (6,"B", 8000,"Sydney");
        Account acc7 = new Account(7,"C",200,"Shiraz");
        Account acc8 = new Account(8,"D",2200,"Shiraz");
        Account acc9 = new Account(9,"E",300,"Sydney");
        Account acc10 = new Account(10, "F",120000,"Shiraz");
	    Bank bank =new Bank("Hooman Bank");
        
        //adding accounts
        bank.addAccount(acc1);
        bank.addAccount(acc2);
        bank.addAccount(acc3);
        bank.addAccount(acc4);
        bank.addAccount(acc5);
        bank.addAccount(acc6);
        bank.addAccount(acc7);
        bank.addAccount(acc8);
        bank.addAccount(acc9);
        bank.addAccount(acc10);
        if( bank.addAccount(acc4))
        {
                 System.out.println("Account has been created successfully");
        }
        else
        {
                 System.out.println("Account is duplicate and has not been created");
        }	

        //or better way
        addAccount(bank,acc4);

      	if (acc1.deposit(-60))
        {
            System.out.println(" deposite was succesful and the new balance is "+acc1.getBalance());
        }
      	else
      	{
            System.out.println(" deposite was not succesful");
      	}
      
        if (acc2.withdraw(600))
        {
            System.out.println(" withdraw was succesful and the new balance is "+acc2.getBalance());
        }
      	else
      	{
            System.out.println(" withdraw was not succesful");
      	}
		
        // or this one which is a better design
        //find an account
        Account acc= bank.findAccount(3);
        withdraw(acc,50);
 
        bank.printAccounts();
        double total = bank.calcTotalBalance();
        System.out.println ("Total balance = " + total);	
		
        //search accounts
        int id=1;
	Scanner sc=new Scanner (System.in);
        while (id!=0)
        {
            System.out.print(" Enter an account ID: ( 0 to exit) ");
            id=sc.nextInt();
            acc= bank.findAccount(id);
            if (acc!=null)
                acc.print();
            else
                System.out.println(" Account has not been found");
        }
        
        // report total balances for all accounts for all given cities
        //bank.reportCity1(); //hardcoded
        //bank.reportRanges1(); //hardcoded
		
        //Data aggregation

        //Data Aggregation Report: print total and average balance per city
        ArrayList<String>cities= bank.populateDistinctCityList();
        ArrayList<Double>balances= bank.getTotalBalancePerCity();
        ArrayList<Integer>counts= bank.getTotalCountPerCity();
        bank.reportTotalPerCity(cities,counts,balances);

        //Data Aggregation Report: print number of accounts per balance range
        Integer [] r={1000,10000,100000,10000000};
        ArrayList<Integer> ranges=new ArrayList<Integer>(Arrays.asList(r));
        ArrayList<Integer> countsPerRange= bank.getTotalCountPerRange(ranges);
        bank.reportRanges(ranges, countsPerRange);

//        System.out.println("Report ranges by sorting");
//        ArrayList<Integer> counts1 = bank.getTotalCountPerRangeSorted(ranges);
//        bank.reportRanges(ranges,counts1);

        Bank2 bank2=new Bank2("hooman Better Bank");

        //adding accounts
        bank2.addAccount(acc1);
        bank2.addAccount(acc2);
        bank2.addAccount(acc3);
        bank2.addAccount(acc4);
        bank2.addAccount(acc5);
        bank2.addAccount(acc6);
        bank2.addAccount(acc7);
        bank2.addAccount(acc8);
        bank2.addAccount(acc9);
        bank2.addAccount(acc10);
        //find an account
        Account accn=bank2.findAccount(3);
        withdraw(accn,10);

        //Data aggregation
        HashMap<String,Double> cities2=bank2.getTotalBalancePerCity();
        HashMap<String,Integer> counts2=bank2.getTotalCountPerCity();
        bank2.reportCity(cities2,counts2);
		
        ArrayList<Integer> countsPerRange1=bank2.getTotalCountPerRange(ranges);
        bank2.reportRanges(ranges,countsPerRange1);


//        System.out.println("Report ranges by sorting");
//        ArrayList<Integer> countsB2 = bank2.getTotalCountPerRangeSorted(ranges);
//        bank2.reportRanges(ranges,countsB2);

        Bank3 bank3=new Bank3("bank with MyHashmap");
        //adding accounts
        bank3.addAccount(acc1);
        bank3.addAccount(acc2);
        bank3.addAccount(acc3);
        bank3.addAccount(acc4);
        bank3.addAccount(acc5);
        bank3.addAccount(acc6);
        bank3.addAccount(acc7);
        bank3.addAccount(acc8);
        bank3.addAccount(acc9);
        bank3.addAccount(acc10);

        //find an account
        Account accnt=bank3.findAccount(3);
        withdraw(accnt,10);

        //Data aggregation
        MyHashMap<String,Double> cities3=bank3.getTotalBalancePerCity();
        MyHashMap<String,Integer> counts3=bank3.getTotalCountPerCity();
        bank3.reportCity(cities,cities3,counts3);

        ArrayList<Integer> countsPerRange2=bank3.getTotalCountPerRange(ranges);
        bank3.reportRanges(ranges,countsPerRange2);

//        System.out.println("Report ranges by sorting");
//        ArrayList<Integer> countsB3 = bank3.getTotalCountPerRangeSorted(ranges);
//        bank3.reportRanges(ranges,countsB3);

        Bank4 bank4 = new Bank4("bank with BST");

        //adding accounts
        bank4.addAccount(acc1);
        bank4.addAccount(acc2);
        bank4.addAccount(acc3);
        bank4.addAccount(acc4);
        bank4.addAccount(acc5);
        bank4.addAccount(acc6);
        bank4.addAccount(acc7);
        bank4.addAccount(acc8);
        bank4.addAccount(acc9);
        bank4.addAccount(acc10);

        //find an account
        Account accnt1=bank4.findAccount(3);
        withdraw(accnt1,10);

        //Data aggregation
        BinarySearchTree<String,Double> cities4=bank4.getTotalBalancePerCity();
        BinarySearchTree<String,Integer> counts4=bank4.getTotalCountPerCity();
        bank4.reportCity(cities,cities4,counts4);

        ArrayList<Integer> countsPerRange3=bank4.getTotalCountPerRange(ranges);
        bank4.reportRanges(ranges,countsPerRange3);


//        System.out.println("Report ranges by sorting");
//        ArrayList<Integer> countsB4 = bank4.getTotalCountPerRangeSorted(ranges);
//        bank4.reportRanges(ranges,countsB4);

        Coordinator coordinator = new Coordinator();
        try {
            coordinator.experiment(20000, 50, 20000, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    public static void withdraw(Account acc, double amount) //UI method
    {
        if (acc.withdraw(amount))
        {
           System.out.println(" withdraw was succesful and the new balance is "+acc.getBalance());
        }
        else
        {
            System.out.println(" withdraw was not succesful");
        }
    }
    public static void addAccount(Bank bank, Account acc) // UI method
    {
        if( bank.addAccount(acc))
        {
                 System.out.println("Account has been created successfully");
        }
        else
        {
                 System.out.println("Account is duplicate and has not been created");
        }	
    }
}