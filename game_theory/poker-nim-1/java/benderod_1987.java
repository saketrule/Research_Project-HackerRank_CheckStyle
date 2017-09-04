import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
  while(t > 0) {
   int n = scanner.nextInt();
   int k = scanner.nextInt();
   int b = 0;
   while(n-->0) {
    b ^= scanner.nextInt();
   }
   System.out.println(b==0 ? "Second" : "First");
   t--;
  }  
 }
}