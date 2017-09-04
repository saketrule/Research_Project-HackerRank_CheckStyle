import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static List<Integer> setupPrimes(int number) {
        boolean[] flags = new boolean[number+1];
        Arrays.fill(flags, true);
        for (int i = 2; i <= number; i++) {
            if (flags[i]) {
                for (int j = 2; i*j <= number; j++) {
                    flags[i*j] = false;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i < flags.length; i++) {
            if (flags[i])
                primes.add(i);
        }
        return primes;
    }

    static String play(int number) {
        List<Integer> primes = setupPrimes(number);
        int turn = 0;
        for (int i = 0; i < primes.size(); i++)
            turn++;
        return (turn % 2 == 0) ? "Bob" : "Alice";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            System.out.println(play(n));
        }
    }
}