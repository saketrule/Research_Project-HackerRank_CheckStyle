import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
//Day 3: Tower Breakers again
    static ArrayList<Integer> p;

    public static boolean isPrime(int i){//odd primes only
        for(int j=0;j<p.size() && p.get(j)<=Math.sqrt(i);j++){
            if(i%p.get(j)==0) return false;
        }
        return true;    
    }
    
    public static int grundy(int n){
        int i,j;
        if(n==1) return 0;                                      //g(n)=0 for terminal position
        
        //Phase 1: count log_2
        int m = n, pow2;
        for (pow2=0; m%2 == 0;pow2++){
            m/=2;
        }//pow2 = power of 2 in n
        
        if(m==1) //n>1
            return 1;                                           //g(n)=1 if n=2**pow2
        
        //Phase 2: count rest of primes > 2
        //add primes if necessary to test for a divsor of m
        if(p.get(p.size()-1) < m){ 
            for(i=p.get(p.size()-1)+2; i<=m;i+=2){
                if(isPrime(i)) p.add(i);
            }
        }
        
        //test m with primes>=3
        int count = 0;
        boolean prime = true;
        for(i=1,j=m; j>1 && p.size()>i && p.get(i)<=j;i++){
            while(j%p.get(i)==0) {//p[i] divides j
                j/=p.get(i); count++; prime=false;
            }
        }
        
        if(!prime) //count = sum of powers for odd primes
        {
            if(pow2==0) return count;                           //g(n)=m if n=odd and m is the sum of powers for odd primes
            else return count+1;                                //g(n)=m+1 if n=2**pow2*odd, m is the sum of powers for odd primes
        }
        else return 1;                                          //g(n)=1 if n=prime
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int [] s = new int[100];
        int i,j,n,m;
        
        p =  new ArrayList<Integer>(Arrays.asList(2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97));
        
        
        for(int tt =0;tt<t;tt++){
            n = in.nextInt();
            int xor = 0;
            for(i=0;i<n;i++){
                s[i] = in.nextInt();
                xor^=grundy(s[i]);
            }
            
            if(xor>0) System.out.println("1");
            else System.out.println("2");   
        }
    }
}