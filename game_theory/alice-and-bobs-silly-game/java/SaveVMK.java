import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        for (int i = 3; i <= 100000; i+=2) {
            int sqrt = (int)Math.sqrt(i);
            boolean prime = true;
            for (int p : primes) {
                if (p > sqrt)
                    break;
                if (i%p==0) {
                    prime = false;
                    break;
                }
            }
            if (prime)
                primes.add(i);
        }
        int[] counts = new int[100001];
        int currIndex = 0;
        for (int i = 0; i <= 100000; i++) {
            if (currIndex < primes.size() && primes.get(currIndex) == i)
                currIndex++;
            counts[i] = currIndex;
        }
        int g = sc.nextInt();
        for (int i = 0; i < g; i++) {
            System.out.println(counts[sc.nextInt()]%2==0?"Bob":"Alice");
        }
    }
}