import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  public static void main(String[] args) {
  
  Scanner scanner = new Scanner(System.in);
  
  int t;
  
  t = scanner.nextInt();
  int[] n = new int[t];
  
  Set<Integer> set = new HashSet<>();
  
  int max = 0;
  for(int i = 0; i<t; i++){
   n[i] = scanner.nextInt();
   if(n[i] > max)
    max = n[i];
  }
  
  for(int j = 2; j <= max; j++){
   if(isPrime(j)){
    set.add(j);
   }
  }
  
  for(int i = 0; i< t; i++){
   int x = n[i];
   int cc = 0;
   for(int j = 2; j<=x; j++){
    if(set.contains(j)){
     cc++;
    }
   }
   if(cc %2 ==0 ){
    System.out.println("Bob");
   }
   else
    System.out.println("Alice");
  }
 }
 
 public static boolean isPrime(int num){
     if ( num > 2 && num%2 == 0 ) {
         return false;
     }
     int top = (int)Math.sqrt(num) + 1;
     for(int i = 3; i < top; i+=2){
         if(num % i == 0){
             return false;
         }
     }
     return true; 
 }
}