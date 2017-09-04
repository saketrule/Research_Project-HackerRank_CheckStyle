import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int [] primes = new int[100001];
        Arrays.fill(primes, 1);
        primes[0] = 0;
        primes[1] = 0;
        
        for (int i = 2; i*i<primes.length; i++) {
            if (primes[i]==1) {
                for (int j = i*i; j < primes.length; j=j+i) {
                    primes[j] = 0;
                }
            }
        }
       
        Scanner in = new Scanner(System.in);
        
        int g = in.nextInt();
        
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            int numprimes = 0;
            for (int i = 2; i <= n; i++) {
                if (primes[i]==1) {
                    numprimes++;
                }
            }
            
            if ((numprimes%2)==0) {
                System.out.println("Bob");
            } else {
                System.out.println("Alice");
            }

        }
    }
}