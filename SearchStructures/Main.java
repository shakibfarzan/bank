package SearchStructures;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Coordinator2 c2 = new Coordinator2();
        try {
            c2.experiment(1000,50);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
