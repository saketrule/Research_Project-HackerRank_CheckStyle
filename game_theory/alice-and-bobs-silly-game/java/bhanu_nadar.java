import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

     static int [] prime(int num)
    {
     
    boolean []composite=new boolean[num+1];
        for (int i = 2; i * i <= num; i++) {
        if (!composite [i]) {
            for (int j = i; i * j <= num; j++) {
                composite [i*j] = true;
            }
        }
    }
    int numPrimes = 0;
    for (int i = 2; i <= num; i++) {
        if (!composite [i]) numPrimes++;
    }
    int [] primes = new int [numPrimes];
    int index = 0;
    for (int i = 2; i <= num; i++) {
        if (!composite [i]) primes [index++] = i;
    }
    return primes;
 }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            int a[]=prime(n);
            int l=a.length;
            if(l==1)
                System.out.println("Alice");
            else if(l%2==0)
                System.out.println("Bob");
                else
                System.out.println("Alice");
        }
    }
}