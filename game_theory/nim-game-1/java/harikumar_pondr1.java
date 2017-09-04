import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
   public static int N,M[];
    public static void main(String[] args) {
     Scanner sc =  new Scanner(System.in);
     int T = sc.nextInt();
     int allOrEq =0;
     
     for (int i = 0; i < T; i++) {
   N = sc.nextInt();
   allOrEq =sc.nextInt();
   for (int j = 1; j < N; j++) {
    /*if(sc.nextInt()%2 == 0){
     allOrEq++;
    }*/
    allOrEq  = allOrEq ^ sc.nextInt();
    
   }
   if(/*N%2==1 || (N%2==0 && !(allOrEq ==0 || allOrEq==N))*/ allOrEq==0){
       System.out.println("Second");
      }else{
       System.out.println("First");
      }
  }
     
     
    }
}