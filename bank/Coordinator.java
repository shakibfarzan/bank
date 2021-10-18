package bank;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Coordinator {
    private int[] generateIds(int n, int min, int max) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * (double) (max - min) + min);
        }
        return array;
    }

    private String[] generateCities(int n){
        Random rand = new Random();
        String[] cities = {"Sari", "Shiraz", "Tehran", "Kerman", "Tabriz", "Zahedan", "Arak", "Yazd", "Isfahan", "Hamedan","Mashhad", "Abadan"};
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

    private long getTime() {
        return System.nanoTime();
    }

    public void experiment(int n, int maxRep) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (int num = 100; num <= n; num += 50) // all  sizes
        {
            for (int rep = 0; rep < maxRep; rep++) {
                System.out.println("Testing n= " + num);
                Bank bank1 = new Bank("Bank 1");
                Bank2 bank2 = new Bank2("Bank 2");
                Bank3 bank3 = new Bank3("Bank 3");
                Bank4 bank4 = new Bank4("Bank 4");

                ArrayList<BankInterface> banks = new ArrayList<>();
                banks.add(bank1);
                banks.add(bank2);
                banks.add(bank3);
                banks.add(bank4);

                writer.write(num + ",");
                int[] ids = generateIds(num, 1, 2000000000); //key 1 to max
                Account[] vals = new Account[num];
                String[] cities = generateCities(num);
                double[] balances = generateBalances(num, 1, 10000000);

                for (BankInterface bank : banks) {
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

//                    begin = getTime();
//                    for (int i = 0; i < num; i++) {
//                        bank.getTotalCountPerRange();
//                    }
//                    finish = getTime();
//                    writer.write((finish - begin) / num + ",");
                }
                writer.write("\n");
            }
        }
        writer.close();
    }
}

