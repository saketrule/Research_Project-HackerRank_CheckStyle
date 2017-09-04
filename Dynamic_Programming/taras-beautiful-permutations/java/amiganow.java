import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Solution {

    static void printPermutations(int n, int[] ar) {
        HashMap<Integer, Integer> counter = new HashMap<Integer, Integer>();
        for(int i=0; i < ar.length; i++) {
            int item = ar[i];
            if(counter.containsKey(item)) {
                counter.put(item, 2);
            } else {
                counter.put(item, 1);
            }
        }
        int repeated = 0;
        for(Map.Entry<Integer,Integer> item : counter.entrySet()) {
            if(item.getValue() > 1) {
                repeated++;
            }
        }
        int result;
        if(repeated > 0) {
            result = factorial(n - repeated) * repeated - repeated;
        } else {
            result = factorial(n);
        }

        System.out.println(result);
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        for(int i=0; i<q; i++) {
            int n = scanner.nextInt();
            int[] ar = new int[n];
            for (int j=0; j<n; j++) {
                ar[j] = scanner.nextInt();
            }
            printPermutations(n, ar);
        }



    }
}