package bank;
import SortAlgorithms.QuickSort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Coordinator {
    private String[] randomString (int n, int chars){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rand = new Random();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < chars; j++) {
                int index = rand.nextInt(str.length());
                sb.append(str.charAt(index));
            }
            words[i] = sb.toString();
        }
        return words;
    }
    private int[] generateIds(int n, int min, int max) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * (double) (max - min) + min);
        }
        return array;
    }

    private String[] generateCities(int n){
        Random rand = new Random();
        String[] cities = randomString(n, 5);
        String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = cities[rand.nextInt(cities.length)];
        }
        return array;
    }

    private double[] generateBalances(int n, int min, int max) {
        double[] array = new double[n];
        for (int i = 0; i < n; i++) {
            array[i] = (Math.random() * (double) (max - min) + min);
        }
        return array;
    }

    public ArrayList<Integer> generateRanges(int n, int min, int max){
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * (double) (max - min) + min);
        }
        QuickSort<Integer> quickSort = new QuickSort<>();
        quickSort.sort(array);
        array[0] = min;
        array[array.length-1] = max;
        return new ArrayList<>(Arrays.asList(array).subList(0, n));
    }

    private long getTime() {
        return System.nanoTime();
    }

    public void experiment(int n, int maxRep) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (int num = 100; num <= n; num += 50) // all  sizes
        {
            for (int rep = 0; rep < maxRep; rep++) {
                System.out.println("Testing n= " + num);
                Bank1 bank1 = new Bank1("Bank 1");
                Bank2 bank2 = new Bank2("Bank 2");
                Bank3 bank3 = new Bank3("Bank 3");
                Bank4 bank4 = new Bank4("Bank 4");

                ArrayList<Bank> banks = new ArrayList<>();
                banks.add(bank1);
                banks.add(bank2);
                banks.add(bank3);
                banks.add(bank4);

                writer.write(num + ",");
                int[] ids = generateIds(num, 1, 2000000000); //key 1 to max
                Account[] vals = new Account[num];
                String[] cities = generateCities(num);
                double[] balances = generateBalances(num, 1, 10000000);
                ArrayList<Integer> ranges = generateRanges(10,0,10000000);
                for (Bank bank : banks) {
                    long begin = getTime();
                    for (int i = 0; i < num; i++) {
                        Account acc = new Account(ids[i],"A",balances[i],cities[i]);
                        vals[i] = acc;
                        if (!bank.addAccount(acc))
                            System.out.println(bank.toString() + " : error in add account");

                    }
                    long finish = getTime();
                    writer.write((finish - begin) / num + ",");
                    begin = getTime();
                    for (int i = 0; i < num; i++) {
                        Account temp = bank.findAccount(ids[i]);
                        if (temp != vals[i]) // test & validation
                            System.out.println(bank.toString() + " : error in find. find result=" + temp + " but found for key: " + ids[i] + " expected val: " + vals[i]);
                    }
                    finish = getTime();
                    writer.write((finish - begin) / num + ",");

                    begin = getTime();
                    for (int i = 0; i < num; i++) {
                       bank.getTotalBalancePerCity();
                    }
                    finish = getTime();
                    writer.write((finish - begin) / num + ",");

                    begin = getTime();
                    for (int i = 0; i < num; i++) {
                        bank.getTotalCountPerCity();
                    }
                    finish = getTime();
                    writer.write((finish - begin) / num + ",");

                    begin = getTime();
                    for (int i = 0; i < num; i++) {
                        bank.getTotalCountPerRange(ranges);
                    }
                    finish = getTime();
                    writer.write((finish - begin) / num + ",");

                    //get sorted accounts
                    Account[] sortedAccounts = bank.sortAccounts();
                    begin = getTime();
                    for (int i = 0; i < num; i++) {
                        bank.getTotalCountPerRangeUsingSort(ranges, sortedAccounts);
                    }
                    finish = getTime();
                    writer.write((finish - begin) / num + ",");
                }
                writer.write("\n");
            }
        }
        writer.close();
    }
}

