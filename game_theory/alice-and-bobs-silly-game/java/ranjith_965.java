import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        boolean prime[] = new boolean[100005];
        for(int i=0;i<100005;i++)
            prime[i] = true; 
        for(int p = 2; p*p <=100004; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*2; i <= 100004; i += p)
                    prime[i] = false;
            }
        }
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            // your code goes here
            int count=0;
            for(int i=2;i<=n;i++){
                if(prime[i]==true){
                    count=count+1;
                }
            }
            if(count%2==1){
                System.out.println("Alice");
            }
            else{
                System.out.println("Bob");
            }
        }
    }
}