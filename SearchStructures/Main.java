package SearchStructures;

import bank.Bank;
import bank.Coordinator;
import bank.Coordinator2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Coordinator2 c2 = new Coordinator2();
//        try {
//            c2.experiment(1000,50);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Coordinator c = new Coordinator();
        String[] cities = c.generateCities(10000, 100);
        System.out.println(Arrays.toString(cities));
        ArrayList<String> cities1 = new ArrayList<>();
        for (String city: cities) {
            boolean hasCity = false;
            for (String ci: cities1) {
                if (ci.equals(city)) {
                    hasCity = true;
                    break;
                }
            }
            if (!hasCity){
                cities1.add(city);
            }
        }
        System.out.println(cities1);
    }
}
