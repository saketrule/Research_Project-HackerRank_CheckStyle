import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tests = in.nextInt();
            //find all the prime numbers less than max
        int max = 1000000;
        boolean[] primes = new boolean[max];
        for(int i = 0; i < max; i++){
            primes[i] = true;
        }
        for(int p = 2; p*p < max; p++){
            for(int pi = p*p; pi < max; pi+=p){
                if(primes[pi]){
                    primes[pi] = false;
                }            
            }
        }
        List<Integer> primeList = new ArrayList<Integer>();
        for(int i = 2; i < max; i++){
            if(primes[i]){
                primeList.add(i);
            }
        }
        for(int test = 0; test < tests; test++){
            int size = in.nextInt();
            int result = 0;
            for(int i = 0; i < size; i++){
                int v = in.nextInt();
                if(v == 1){
                    continue;
                }
                int prime = 0;
                int index = 0;
                while(v >= primeList.get(index)){
                    int p = primeList.get(index);
                    if(v % p == 0){
                        prime++;
                        v = v/p;
                    }else{
                        index++;
                    }
                }
                result ^= prime;
            }
            System.out.println(result == 0 ? 2 : 1);   
        }
    }
}