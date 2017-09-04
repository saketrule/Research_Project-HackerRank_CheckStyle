import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int nrOfBoxes, nrOfCoins;
        ArrayList<Integer> boxes;
        int xor = 0;
        int c;
        boolean isEven = true;
        int el;
        for (int k = 0; k < T; k++) {
            nrOfBoxes = sc.nextInt();
            boxes = new ArrayList<Integer>();
            for (int i = 0; i < nrOfBoxes; i++) {
                nrOfCoins = sc.nextInt();

                if (nrOfCoins != 1) {
                    isEven = false;
                }
                boxes.add(nrOfCoins);

            }
            verifiy(boxes);
//            if (isEven && xor(boxes) == 1) {
//                System.out.println("Second");
//            } else if (!isEven && xor(boxes) == 0) {
//                System.out.println("Second");
//            } else if (!isEven && boxes.size() == 2) {
//                System.out.println("Second");
//            } else {
//                System.out.println("First");
//            }
        }
    }

    private static void verifiy(ArrayList<Integer> array) {
        int b = 0, c;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > 0) {
                if (array.get(i) % 2 == 0) {
                    c = 0;
                } else {
                    c = i;
                }
                b = b ^ c;
            }
        }
        if (b == 0) {
            System.out.println("Second");
        } else {
            System.out.println("First");
        }
    }

    private static int xor(ArrayList<Integer> array) {
        int xor;
        if (array.size() > 1) {
            xor = array.get(0) ^ array.get(1);
        } else {
            return 1;
        }
        for (int i = 2; i < array.size(); i++) {
            xor ^= array.get(i);
        }
        return xor;
    }
}