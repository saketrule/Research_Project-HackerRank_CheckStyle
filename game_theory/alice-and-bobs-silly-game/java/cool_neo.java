import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
 public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
         int numOfGames = in.nextInt();
         for(int i=0;i<numOfGames;i++){
          int maxNumber = in.nextInt();
          //List<Integer> primeList = getPrimes(maxNumber);
          if(getSievePrimes(maxNumber)%2==0){
           System.out.println("Bob");
          }
          else{
           System.out.println("Alice");
          }
          
         }
 }
 
 public static int getSievePrimes(int n){
  // initially assume all integers are prime
        boolean[] isPrime = new boolean[n+1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor*factor <= n; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ...,  n/factor
            if (isPrime[factor]) {
                for (int j = factor; factor*j <= n; j++) {
                    isPrime[factor*j] = false;
                }
            }
        }

        // count primes
        int primes = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes++;
        }
    //    System.out.println("The number of primes <= " + n + " is " + primes);
        return primes;
    }
 
 
 public static List<Integer> getPrimes(int n) {  
        int i =0;
        int num =0;
        //Empty String
        List<Integer>  primeNumbers = new ArrayList();

        for (i = 1; i <= n; i++)         
        {        
           int counter=0;    
           for(num =i; num>=1; num--)
    {
              if(i%num==0)
       {
    counter = counter + 1;
       }
    }
    if (counter ==2)
    {
       //Appended the Prime number to the String
       primeNumbers.add(i);
    } 
        } 
        return primeNumbers;
    }

}