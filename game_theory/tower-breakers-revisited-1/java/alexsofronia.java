import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
//Day 2: Tower Breakers revisited
 static ArrayList<Integer> p;

    public static boolean isPrime(int i){//odd primes only
        for(int j=0;j<p.size() && p.get(j)<=Math.sqrt(i);j++){
            if(i%p.get(j)==0) return false;
        }
        return true;    
    }
    
    public static int divNo(int n){
        if(n==1) return 0;
        
        int count = 0;
        boolean prime = true;
        //add primes if necessary
        if(p.get(p.size()-1) < n){ 
            for(int i=p.get(p.size()-1)+2; i<=n;i+=2){
                if(isPrime(i)) p.add(i);
            }
        }
        
        for(int i=0,j=n; j>1 && p.size()>i && p.get(i)<=j;i++){
            while(j%p.get(i)==0) {//p[i] divides j
                j/=p.get(i); count++; prime=false;
            }
        }
        if(!prime) return count;
        else return 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int [] s = new int[100];
        int [] q = new int[100];
        p =  new ArrayList<Integer>(Arrays.asList(2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97));
        int i,j,n,m;
        for(int tt =0;tt<t;tt++){
            n = in.nextInt();
            
            for(i=0;i<n;i++){
                s[i] = in.nextInt();
                q[i] = divNo(s[i]);
            }
            int nimsum = q[0];
            for(i = 1;i<n;i++){
                nimsum^=q[i];
            }
            
            if(nimsum>0) System.out.println("1");
            else System.out.println("2");   
        }
    }
}