package bank;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//       Bank4 b = new Bank4("SSbank");
//       System.out.println(b.addAccount(new Account(0, "a", 200.76, "a")));
//       System.out.println(b.addAccount(new Account(1,"b", 300.12, "b")));
//       System.out.println(b.addAccount(new Account(2,"c", 201.1,"c")));
//       System.out.println(b.findAccount(3));
//       System.out.println(b.findAccount(0));
//       System.out.println(b.calcTotalBalance());

//        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
//        bst.insert("a", 1);
//        bst.insert("b", 2);
//        bst.insert("c", 3);
//        bst.insert("d",4);
//        bst.insert("e", 5);
//        int[] ranges = {0, 5, 10, 15, 20, 25, 50};
//        QuickSort<Double> sort = new QuickSort<>();
//        Double[] arr = {2.12,4.1,1.12,24.24,13.0,45.134,9.1424,1.2134,12.124,25.124};
//        sort.sort(arr);
//        System.out.println(Arrays.toString(arr));
//        int left = 0, right = arr.length-1;
//        int minIndex = 0;
//        int maxIndex = 0;
//        ArrayList<Integer> counts = new ArrayList<>();
//        for (int i = 0; i < ranges.length - 1; i++) {
//            double min = ranges[i];
//            double max = ranges[i+1];
//            while (left <= right)
//            {
//                int mid = (left + right) / 2;
//
//                if ((arr[mid] >= min && mid==arr.length-1)||(arr[mid] <= max && arr[mid+1]> max)) {
//                    maxIndex = mid;
//                    break;
//                }
//                else if (arr[mid] > max) {
//                    right = mid - 1;
//                }
//                else {
//                    left = mid + 1;
//                }
//            }
//            int count = maxIndex-minIndex+1;
//            minIndex = maxIndex + 1;
//            right = arr.length-1;
//            left = minIndex;
//            counts.add(count);
//        }
//        System.out.println(counts);
        Coordinator c = new Coordinator();
        ArrayList<Integer> a = c.generateRanges(100, 0, 1000000);
        System.out.println(a);
    }
}
