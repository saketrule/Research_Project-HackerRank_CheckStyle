import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {public static void main(String[] args) {
  // TODO Auto-generated method stub-
  nim();
 }

 public static void nim() {
  Scanner sc = new Scanner(System.in);
  int g = sc.nextInt();
  while (g-- > 0) {
   int n = sc.nextInt();
   boolean isAllOne = true;
   int result = 0;
   while(n-->0){
    int t = sc.nextInt();
    if(t!=1){
     isAllOne = false;
    }
    result = result ^ t;
   }
   
   if(!isAllOne){
    if (result == 0) {
     System.out.println("Second");
    } else {
     System.out.println("First");
    }
   } else {
    if (result == 0) {
     System.out.println("First");
    } else {
     System.out.println("Second");
    }
   }
  }
 }
 
}