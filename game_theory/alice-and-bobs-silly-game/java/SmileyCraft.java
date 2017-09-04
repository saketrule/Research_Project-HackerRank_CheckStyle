import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        outerloop:
        for (int i = 3; i <= 100001; i += 2){
            for (int p : primes){
                if (i % p == 0)
                    continue outerloop;
                else if (p * p > i)
                    break;
            }
            primes.add(i);
        }
        boolean[] cumulative = new boolean[100001];
        cumulative[0] = true;
        for (int i = 1; i < 100001; i++){
            cumulative[i] = cumulative[i-1];
            if (primes.contains(i))
                cumulative[i] = !cumulative[i];
        }
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            System.out.println(cumulative[n] ? "Bob" : "Alice");
        }
    }
}