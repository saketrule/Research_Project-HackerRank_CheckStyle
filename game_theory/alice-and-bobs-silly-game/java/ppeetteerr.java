import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        
        TreeSet<Integer> primes = new TreeSet<Integer>(getPrimesAsSet(1_000_000));
        
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            int size = primes.subSet(0, true, n, true).size();
            System.out.println(size%2==0 ? "Bob" : "Alice");
        }
    }
    
     public static boolean isPrime(long n){
  BigInteger big = BigInteger.valueOf(n);
  return big.isProbablePrime(20);
 }
 
 /**
  * Return all primes <=maxPrime
  * Faster than getPrimesAsSet
  * 
  * @param maxPrime should be <= 10^7
  * @return
  */
 public static List<Integer> getPrimesAsList(int maxPrime){
  List<Integer> primes = new ArrayList<Integer>();
  if(maxPrime<2) return primes;
  
  primes.add(2);
  
  boolean[] isComposed = new boolean[(maxPrime+1)>>1];
  // i points to 2*i+1
  for (int i = 1; i < isComposed.length; i++) {
       if(!isComposed[i]){
        primes.add(2*i+1);
        
        for(long j=(1L*(2*i+1)*(2*i+1)-1)/2; j<isComposed.length; j+=2*i+1){
         isComposed[(int)j] = true;
        }
       }
      }
  
  return primes;
 }
 
 /**
  * Return all primes <=maxPrime
  * 
  * @param maxPrime should be <= 10^7
  * @return
  */
 public static Set<Integer> getPrimesAsSet(int maxPrime){
  Set<Integer> primes = new HashSet<Integer>();
  if(maxPrime<2) return primes;
  
  primes.add(2);
  boolean[] isComposed = new boolean[(maxPrime+1)>>1];
  // i points to 2*i+1
  for (int i = 1; i < isComposed.length; i++) {
       if(!isComposed[i]){
        primes.add(2*i+1);
        
        for(long j=(1L*(2*i+1)*(2*i+1)-1)/2; j<isComposed.length; j+=2*i+1){
         isComposed[(int)j] = true;
        }
       }
      }
  
  return primes;
 }
 
 
 /**
  * Return all primes <=maxPrime
  * 
  * @param maxPrime should be <= 10^7
  * @return
  */
 public static int[] getPrimesAsArray(int maxPrime){
  int[] primes = new int[1_000_000];
  if(maxPrime<2) return Arrays.copyOf(primes, 0);;
  
  int index = 0;
  primes[index++] = 2;
  boolean[] isComposed = new boolean[(maxPrime+1)>>1];
  // i points to 2*i+1
  for (int i = 1; i < isComposed.length; i++) {
       if(!isComposed[i]){
        primes[index++] = 2*i+1;
        
        for(long j=(1L*(2*i+1)*(2*i+1)-1)/2; j<isComposed.length; j+=2*i+1){
         isComposed[(int)j] = true;
        }
       }
      }
  
  return Arrays.copyOf(primes, index);
 }

     public static int[] primesInRange(int min, int max){
        if(min<2) return getPrimesAsArray(max);
        
        int[] divisors = getPrimesAsArray((int)Math.sqrt(max));
        
        // isComposed[i] points to min+i
        boolean[] isComposed = new boolean[max-min+1];
        for(Integer divisor:divisors){
            int multiply = (int)Math.ceil(1.0*min/divisor)*divisor;
            if(multiply==divisor) multiply*=2;
            while (multiply<=max){
                isComposed[multiply-min] = true;
                multiply+=divisor;
            }
        }
        
        int[] primes = new int[1_000_000];
        int counter = 0;
        for(int i=0; i<max-min+1; i++){
            if(!isComposed[i]) primes[counter++] = min+i;
        }
        
        return Arrays.copyOf(primes, counter);
    }
}