package bank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoomanTest
{   
    public static final int accountCount=27700;// 277000
    public static void main(String[] args) throws IOException 
    {  
        ArrayList<String> cities=(ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath ("cities.txt"));
        
        // These are generated from correct code at the end of the code bank
        ArrayList<String> countsGroundTruth=(ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath ("counts.txt"));
        ArrayList<String> balancesGroundTruth=(ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath ("balances.txt"));
        ArrayList<String> rangesGroundTruth=(ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath ("ranges.txt"));
        
        Integer [] ranges={0,100,1000,5000,10000,20000,25000,45000,50000,80000,85000,87000,88000,100000,110000,
            120000,122000,130000,200000,250000,300000,310000,400000,400500,800000,850000,1000000,1000900,2000000,200000000,2000000000};
        ArrayList<Integer> rangesList=new ArrayList<Integer>(Arrays.asList(ranges));
        
        long [] addTime=new long[10];
        long [] searchTime=new long[10];
        long [] cityAggregationCountTime=new long[10];
        long [] cityAggregationBalTime=new long[10];
        long [] rangeAggregationCountTime=new long[10];
        
        ArrayList<Account>accounts=new ArrayList<>();
        for ( int i=1;i<=accountCount;i++)
        {
            String name="name"+Integer.toString(i)+"hSh"+Integer.toString(i*2);
            Account ac=new Account(i,name,i%1000*1000,cities.get(i%277));
            accounts.add(ac);
        }
        System.out.println("Accounts created");

//        // bank test
//        
        for( int i=0;i<10;i++)
        {
            Bank bank=new Bank("Hooman Bank 1");
            // experiment add account
            long start=System.nanoTime();
            for(Account ac:accounts)
            {
                if(!bank.addAccount(ac))
                {
                    System.out.println("error in adding accounts");
                    //System.exit(0);
					break;
                }
            }
            addTime[i]=System.nanoTime()-start;

            //test duplicate adding. Should not work
            for(Account ac:accounts)
            {
                if(bank.addAccount(ac))
                {
                    System.out.println("error in adding accounts (duplicate)");
                    //System.exit(0);
					break;
                }
            }

            //System.out.println("Total balance = "+bank.calcTotalBalance());
            if (Double.compare((double)bank.calcTotalBalance(),1.373185E10)!=0)
            {
                System.out.println("error in total balance");
                //System.exit(0);
            }
            //experiment search account
            start=System.nanoTime();
            for (int id=1;id<=accountCount;id++)       
            {
                if (bank.findAccount(id)==null)
                {
                    System.out.println("error in searching valid accounts");
                    //System.exit(0);
					break;
                }
            }
            searchTime[i]=System.nanoTime()-start;

            //test search (valid accounts)
            for (int id=1;id<=accountCount;id++)
            {
                Account ac=bank.findAccount(id);
                if (ac==null||!(ac.getName().equals(accounts.get(id-1).getName()))
                        || ac.getID()!=accounts.get(id-1).getID()
                        || !(ac.getCity().equals(accounts.get(id-1).getCity())))
                {
                    System.out.println("error in searching valid accounts");
                    //System.exit(0);
					break;
                }
                if(ac.withdraw(100))
                    ac.deposit(100); // don't change the balance
            }

            //System.out.println("Total balance after withdraw = "+bank.calcTotalBalance());
            if (Double.compare((double)bank.calcTotalBalance(),1.373185E10)!=0)
            {
                System.out.println("error in total balance after withdraw and deposit");
                //System.exit(0);
            }
            //test search (invalid accounts)
            for (int id=accountCount+1;id<=2*accountCount;id++)
            {
                if (bank.findAccount(id)!=null)
                {
                    System.out.println("error in searching invalid accounts");
                    //System.exit(0);
					break;
                }
            }

            //experiment city aggregation-Balance
            start=System.nanoTime();
            ArrayList<Double>balances=bank.getTotalBalancePerCity(cities);
            cityAggregationBalTime[i]=System.nanoTime()-start;

            //experiment city aggregation-Count
            start=System.nanoTime();
            ArrayList<Integer>counts=bank.getTotalCountPerCity(cities);
            cityAggregationCountTime[i]=System.nanoTime()-start;

            //experiment range aggregation-Count
            start=System.nanoTime();
            ArrayList<Integer> countsPerRange=bank.getTotalCountPerRange(rangesList);
            rangeAggregationCountTime[i]=System.nanoTime()-start;     
            
// -----------------------------------------------------------------
// -----Only fist time to generate the ground truth------------------
           
//            //test outputs
//            bank.reportTotalPerCity(cities,counts,balances);
//            bank.reportRanges(rangesList, countsPerRange);
//            
//            //generate the data files the first time
//            BufferedWriter writer=new BufferedWriter(new FileWriter("counts.txt"));
//            counts.stream().forEach(x-> {
//                try 
//                {
//                    writer.write(x+"\n");
//                } 
//                catch (IOException ex) 
//                {
//                    System.exit(0);
//                }
//            });
//            writer.close();
//            
//            //generate the data files the first time
//            BufferedWriter writer1=new BufferedWriter(new FileWriter("balances.txt"));
//            balances.stream().forEach(x-> {
//                try 
//                {
//                    writer1.write(x+"\n");
//                } 
//                catch (IOException ex) 
//                {
//                    System.exit(0);
//                }
//            });
//            writer1.close();
//            
//            //generate the data files the first time
//            BufferedWriter writer2=new BufferedWriter(new FileWriter("ranges.txt"));
//            countsPerRange.stream().forEach(x-> {
//                try 
//                {
//                    writer2.write(x+"\n");
//                } 
//                catch (IOException ex) 
//                {
//                    System.exit(0);
//                }
//            });
//            writer2.close();
//---------------------------------------------------------------------
            // test count aggregation
            for( int k=0;k<countsGroundTruth.size();k++)
            {
                if (Integer.compare(counts.get(k),Integer.parseInt(countsGroundTruth.get(k)))!=0)
                {
                    System.out.println("error in city-count aggregation");
                    //System.exit(0);
					break;
                }
            }
            
            // test balance aggregation
            for( int k=0;k<balancesGroundTruth.size();k++)
            {
                if (Double.compare(balances.get(k),Double.parseDouble(balancesGroundTruth.get(k)))!=0)
                {
                    System.out.println("error in city-balance aggregation");
                    //System.exit(0);
					break;
                }
            }
            
            // test range aggregation
            for( int k=0;k<rangesGroundTruth.size();k++)
            {
                if (Integer.compare(countsPerRange.get(k),Integer.parseInt(rangesGroundTruth.get(k)))!=0)
                {
                    System.out.println("error in range-count aggregation");
                    //System.exit(0);
					break;
                }
            }
            
        }
        
        System.out.println("Bank I (ArrayList) report: ");  
        System.out.println("Add Time = " +Arrays.stream(addTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("Search Time = " +Arrays.stream(searchTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("City Aggregation Count Time = "+ Arrays.stream(cityAggregationCountTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("City Aggregation Bal Time = " + Arrays.stream(cityAggregationBalTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("Range Aggregation Count Time = " +Arrays.stream(rangeAggregationCountTime).average().getAsDouble()/accountCount+" ns \n\n");
        	 
        
        // bank2
        
        for( int i=0;i<10;i++)
        {
            Bank2 bank=new Bank2("Hooman Bank 2");
            // experiment add account
            long start=System.nanoTime();
            for(Account ac:accounts)
            {
                if(!bank.addAccount(ac))
                {
                    System.out.println("error in adding accounts");
                    //System.exit(0);
					break;
                }
            }
            addTime[i]=System.nanoTime()-start;

            //test duplicate adding. Should not work
            for(Account ac:accounts)
            {
                if(bank.addAccount(ac))
                {
                    System.out.println("error in adding accounts (duplicate) ");
                    //System.exit(0);
					break;
                }
            }

            //System.out.println("Total balance = "+bank.calcTotalBalance());
            if (Double.compare((double)bank.calcTotalBalance(),1.373185E10)!=0)
            {
                System.out.println("error in total balance");
                //System.exit(0);
            }
            //experiment search account
            start=System.nanoTime();
            for (int id=1;id<=accountCount;id++)       
            {
                if (bank.findAccount(id)==null)
                {
                    System.out.println("error in searching valid accounts ");
                    //System.exit(0);
					break;
                }
            }
            searchTime[i]=System.nanoTime()-start;

            //test search (valid accounts)
            for (int id=1;id<=accountCount;id++)
            {
                Account ac=bank.findAccount(id);
                if (ac==null||!(ac.getName().equals(accounts.get(id-1).getName()))
                        || ac.getID()!=accounts.get(id-1).getID()
                        || !(ac.getCity().equals(accounts.get(id-1).getCity())))
                {
                    System.out.println("error in searching valid accounts");
                    //System.exit(0);
					break;
                }
                if(ac.withdraw(100))
                    ac.deposit(100); // don't change the balance
            }

            //System.out.println("Total balance after withdraw = "+bank.calcTotalBalance());
            if (Double.compare((double)bank.calcTotalBalance(),1.373185E10)!=0)
            {
                System.out.println("error in total balance after withdraw and deposit");
                //System.exit(0);
            }
            //test search (invalid accounts)
            for (int id=accountCount+1;id<=2*accountCount;id++)
            {
                if (bank.findAccount(id)!=null)
                {
                    System.out.println("error in searching invalid accounts");
                    //System.exit(0);
					break;
                }
            }

            //experiment city aggregation-Balance
            start=System.nanoTime();
            HashMap<String,Double>balances=bank.getTotalBalancePerCity();
            cityAggregationBalTime[i]=System.nanoTime()-start;

            //experiment city aggregation-Count
            start=System.nanoTime();
            HashMap<String,Integer>counts=bank.getTotalCountPerCity();
            cityAggregationCountTime[i]=System.nanoTime()-start;

            //experiment range aggregation-Count
            start=System.nanoTime();
            ArrayList<Integer> countsPerRange=bank.getTotalCountPerRange(rangesList);
            rangeAggregationCountTime[i]=System.nanoTime()-start;     
            
            // test count aggregation
            for( int k=0;k<countsGroundTruth.size();k++)
            {
                if (Integer.compare(counts.get(cities.get(k)),Integer.parseInt(countsGroundTruth.get(k)))!=0)
                {
                    System.out.println("error in city-count aggregation");
                    //System.exit(0);
					break;
                }
            }
            
            // test balance aggregation
            for( int k=0;k<balancesGroundTruth.size();k++)
            {
                if (Double.compare(balances.get((cities.get(k))),Double.parseDouble(balancesGroundTruth.get(k)))!=0)
                {
                    System.out.println("error in city-balance aggregation");
                    //System.exit(0);
					break;
                }
            }
            
            // test range aggregation
            for( int k=0;k<rangesGroundTruth.size();k++)
            {
                if (Integer.compare(countsPerRange.get(k),Integer.parseInt(rangesGroundTruth.get(k)))!=0)
                {
                    System.out.println("error in range-count aggregation");
                    //System.exit(0);
					break;
                }
            }
        }
        
        System.out.println("Bank II (HashMap) report: ");    
        System.out.println("Add Time = " +Arrays.stream(addTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("Search Time = " +Arrays.stream(searchTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("City Aggregation Count Time = "+ Arrays.stream(cityAggregationCountTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("City Aggregation Bal Time = " + Arrays.stream(cityAggregationBalTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("Range Aggregation Count Time = " +Arrays.stream(rangeAggregationCountTime).average().getAsDouble()/accountCount+" ns \n\n");
        
        
        // bank3
        
        for( int i=0;i<10;i++)
        {
            Bank3 bank=new Bank3("Hooman Bank 3");
            // experiment add account
            long start=System.nanoTime();
            for(Account ac:accounts)
            {
                if(!bank.addAccount(ac))
                {
                    System.out.println("error in adding accounts");
                    //System.exit(0);
					break;
                }
            }
            addTime[i]=System.nanoTime()-start;

            //test duplicate adding. Should not work
            for(Account ac:accounts)
            {
                if(bank.addAccount(ac))
                {
                    System.out.println("error in adding accounts (duplidate) ");
                    //System.exit(0);
					break;
                }
            }

            //System.out.println("Total balance = "+bank.calcTotalBalance());
            if (Double.compare((double)bank.calcTotalBalance(),1.373185E10)!=0)
            {
                System.out.println("error in total balance");
                //System.exit(0);
            }
            //experiment search account
            start=System.nanoTime();
            for (int id=1;id<=accountCount;id++)       
            {
                if (bank.findAccount(id)==null)
                {
                    System.out.println("error in searching valid accounts ");
                    //System.exit(0);
					break;
                }
            }
            searchTime[i]=System.nanoTime()-start;

            //test search (valid accounts)
            for (int id=1;id<=accountCount;id++)
            {
                Account ac=bank.findAccount(id);
                if (ac==null||!(ac.getName().equals(accounts.get(id-1).getName()))
                        || ac.getID()!=accounts.get(id-1).getID()
                        || !(ac.getCity().equals(accounts.get(id-1).getCity())))
                {
                    System.out.println("error in searching valid accounts");
                    //System.exit(0);
					break;
                }
                if(ac.withdraw(100))
                    ac.deposit(100); // don't change the balance
            }

            //System.out.println("Total balance after withdraw = "+bank.calcTotalBalance());
            if (Double.compare((double)bank.calcTotalBalance(),1.373185E10)!=0)
            {
                System.out.println("error in total balance after withdraw and deposit");
                //System.exit(0);
            }
            //test search (invalid accounts)
            for (int id=accountCount+1;id<=2*accountCount;id++)
            {
                if (bank.findAccount(id)!=null)
                {
                    System.out.println("error in searching invalid accounts");
                    //System.exit(0);
					break;
                }
            }

            //experiment city aggregation-Balance
            start=System.nanoTime();
            MyHashMap<String,Double>balances=bank.getTotalBalancePerCity();
            cityAggregationBalTime[i]=System.nanoTime()-start;

            //experiment city aggregation-Count
            start=System.nanoTime();
            MyHashMap<String,Integer>counts=bank.getTotalCountPerCity();
            cityAggregationCountTime[i]=System.nanoTime()-start;

            //experiment range aggregation-Count
            start=System.nanoTime();
            ArrayList<Integer> countsPerRange=bank.getTotalCountPerRange(rangesList);
            rangeAggregationCountTime[i]=System.nanoTime()-start;     
            
            //check bank3
            //System.out.println(counts.toList());
            //System.out.println(balances.toList());
            //counts.printList();
            //balances.printList();
            //bank.reportCity(cities,balances, counts);
            
            // test count aggregation
            for( int k=0;k<countsGroundTruth.size();k++)
            {
                if (Integer.compare(counts.get(cities.get(k)),Integer.parseInt(countsGroundTruth.get(k)))!=0)
                {
                    System.out.println("error in city-count aggregation");
                    //System.exit(0);
					break;
                }
            }
            
            // test balance aggregation
            for( int k=0;k<balancesGroundTruth.size();k++)
            {
                if (Double.compare(balances.get((cities.get(k))),Double.parseDouble(balancesGroundTruth.get(k)))!=0)
                {
                    System.out.println("error in city-balance aggregation");
                    //System.exit(0);
					break;
                }
            }
            
            // test range aggregation
            for( int k=0;k<rangesGroundTruth.size();k++)
            {
                if (Integer.compare(countsPerRange.get(k),Integer.parseInt(rangesGroundTruth.get(k)))!=0)
                {
                    System.out.println("error in range-count aggregation");
                    //System.exit(0);
					break;
                }
            }
        }
        
        System.out.println("Bank III (MyHashMap) report: ");    
        System.out.println("Add Time = " +Arrays.stream(addTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("Search Time = " +Arrays.stream(searchTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("City Aggregation Count Time = "+ Arrays.stream(cityAggregationCountTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("City Aggregation Bal Time = " + Arrays.stream(cityAggregationBalTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("Range Aggregation Count Time = " +Arrays.stream(rangeAggregationCountTime).average().getAsDouble()/accountCount+" ns \n\n");

        // bank4
        
        //shuffle 4 BST
        for ( int i=1;i<accountCount;i++)
        {
            int ind1=(int)(Math.random()*accountCount);
            int ind2=(int)(Math.random()*accountCount);
            Account temp= accounts.get(ind1);
            accounts.set(ind1,accounts.get(ind2));
            accounts.set(ind2,temp);   
        }
        
        for( int i=0;i<10;i++)
        {
            Bank4 bank=new Bank4("Hooman Bank 4");
            
            // experiment add account
            long start=System.nanoTime();
            for(Account ac:accounts)
            {
                if(!bank.addAccount(ac))
                {
                    System.out.println("error in adding accounts");
                    //System.exit(0);
					break;
                }
            }
            addTime[i]=System.nanoTime()-start;

            //test duplicate adding. Should not work
            for(Account ac:accounts)
            {
                if(bank.addAccount(ac))
                {
                    System.out.println("error in adding accounts (duplidate)");
                    //System.exit(0);
					break;
                }
            }
            //bank.printAccounts();
            //System.out.println("Total balance = "+bank.calcTotalBalance());
            if (Double.compare((double)bank.calcTotalBalance(),1.373185E10)!=0)
            {
                System.out.println("error in total balance");
                //System.exit(0);
            }
            //experiment search account
            start=System.nanoTime();
            for (int id=1;id<=accountCount;id++)       
            {
                if (bank.findAccount(id)==null)
                {
                    System.out.println("error in searching valid accounts ");
                    //System.exit(0);
					break;
                }
            }
            searchTime[i]=System.nanoTime()-start;

            //test search (valid accounts)
            for (int id=1;id<=accountCount;id++)
            {
                Account ac=bank.findAccount(id);
//                if (ac==null||!(ac.getName().equals(accounts.get(id-1).getName()))
//                        || ac.getID()!=accounts.get(id-1).getID()
//                        || !(ac.getCity().equals(accounts.get(id-1).getCity())))
//                {
//                    System.out.println("error in searching valid accounts");
//                    System.exit(0);
//                }
                if(ac.withdraw(100))
                    ac.deposit(100); // don't change the balance
            }

            //System.out.println("Total balance after withdraw = "+bank.calcTotalBalance());
            if (Double.compare(bank.calcTotalBalance(),1.373185E10)!=0)
            {
                System.out.println("error in total balance after withdraw and deposit");
                System.exit(0);
            }
            //test search (invalid accounts)
            for (int id=accountCount+1;id<=2*accountCount;id++)
            {
                if (bank.findAccount(id)!=null)
                {
                    System.out.println("error in searching invalid accounts");
                    //System.exit(0);
					break;
                }
            }

            //experiment city aggregation-Balance
            start=System.nanoTime();
            BinarySearchTree<String,Double>balances=bank.getTotalBalancePerCity();
            cityAggregationBalTime[i]=System.nanoTime()-start;

            //experiment city aggregation-Count
            start=System.nanoTime();
            BinarySearchTree<String,Integer>counts=bank.getTotalCountPerCity();
            cityAggregationCountTime[i]=System.nanoTime()-start;

            //experiment range aggregation-Count
            start=System.nanoTime();
            ArrayList<Integer> countsPerRange=bank.getTotalCountPerRange(rangesList);
            rangeAggregationCountTime[i]=System.nanoTime()-start;     
            
            //check bank4
//            System.out.println(counts.toList());
//            System.out.println(balances.toList());
//            counts.print();
//            balances.print();
//            bank.reportCity(cities,balances, counts);
            
            // test count aggregation
            for( int k=0;k<countsGroundTruth.size();k++)
            {
                if (Integer.compare(counts.search(cities.get(k)),Integer.parseInt(countsGroundTruth.get(k)))!=0)
                {
                    System.out.println("error in city-count aggregation");
                    //System.exit(0);
					break;
                }
            }
            
            // test balance aggregation
            for( int k=0;k<balancesGroundTruth.size();k++)
            {
                if (Double.compare(balances.search((cities.get(k))),Double.parseDouble(balancesGroundTruth.get(k)))!=0)
                {
                    System.out.println("error in city-balance aggregation");
                    //System.exit(0);
					break;
                }
            }
            
            // test range aggregation
            for( int k=0;k<rangesGroundTruth.size();k++)
            {
                if (Integer.compare(countsPerRange.get(k),Integer.parseInt(rangesGroundTruth.get(k)))!=0)
                {
                    System.out.println("error in range-count aggregation");
                    //System.exit(0);
					break;
                }
            }
        }
        
        System.out.println("Bank IV (BST) report: ");    
        System.out.println("Add Time = " +Arrays.stream(addTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("Search Time = " +Arrays.stream(searchTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("City Aggregation Count Time = "+ Arrays.stream(cityAggregationCountTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("City Aggregation Bal Time = " + Arrays.stream(cityAggregationBalTime).average().getAsDouble()/accountCount+" ns ");
        System.out.println("Range Aggregation Count Time = " +Arrays.stream(rangeAggregationCountTime).average().getAsDouble()/accountCount+" ns \n\n");
    }
}