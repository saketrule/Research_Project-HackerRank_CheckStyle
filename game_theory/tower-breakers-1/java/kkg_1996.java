import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
  Scanner scan = new Scanner(System.in);
  int t = scan.nextInt();
  while (t-- != 0) {
   int n = scan.nextInt();
   int height = scan.nextInt();
   if (height == 1) {
    System.out.println("2");
   } else {
    if (n % 2 == 0) {
     System.out.println("2");
    } else {
     System.out.println("1");
    }
   }
  }
 
    }
}