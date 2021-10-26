package bank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    public String[] generateCities(int n, int distinct){
        Random rand = new Random();
        String[] cities = randomString(distinct, 5);
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

    public void experiment(int n, int maxRep, int start, int step) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"));
        for (int num = start; num <= n; num += step) // all  sizes
        {

            for (int rep = 0; rep < maxRep; rep++) {
                int[] ids = generateIds(num, 1, 2000000000); //key 1 to max
                Account[] vals = new Account[num];
                String[] cities = generateCities(num, num/50);
                double[] balances = generateBalances(num, 1, 10000000);
                ArrayList<Integer> ranges = generateRanges(num/30,0,10000000);
                System.out.println("Testing n= " + num);
                Bank bank1 = new Bank("Bank 1");
                Bank2 bank2 = new Bank2("Bank 2");
                Bank3 bank3 = new Bank3("Bank 3");
                Bank4 bank4 = new Bank4("Bank 4");

                ArrayList<Banks> banks = new ArrayList<>();
                banks.add(bank1);
                banks.add(bank2);
                banks.add(bank3);
                banks.add(bank4);

                writer.write(num + ",");
                for (Banks bank : banks) {
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
                    bank.getTotalBalancePerCity();
                    finish = getTime();
                    writer.write((finish - begin) + ",");

                    begin = getTime();
                    bank.getTotalCountPerCity();
                    finish = getTime();
                    writer.write((finish - begin) + ",");

                    begin = getTime();
                    bank.getTotalCountPerRange(ranges);
                    finish = getTime();
                    writer.write((finish - begin) + ",");

//                    begin = getTime();
//                    bank.getTotalCountPerRangeSorted(ranges);
//                    finish = getTime();
//                    writer.write((finish - begin) + ",");
                }
                writer.write("\n");
            }
        }
        writer.close();
    }
}

