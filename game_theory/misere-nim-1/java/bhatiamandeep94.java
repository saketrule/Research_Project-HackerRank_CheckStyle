import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        
        for(int i=0;i<t;i++) {
         int x = in.nextInt();
         int[] stones = new int[x];
         boolean check = false;
         for(;x>0;x--) {
          stones[x-1] = in.nextInt();
          if(stones[x-1] > 1) {
           check = true;
          }
         }
         int xor=stones[0];
         for(int j=1;j<stones.length;j++) {
          xor = xor ^ stones[j];
         }
         
         if(!check && xor == 1) {
          System.out.println("Second");
         } else if(check && xor == 0) {
          System.out.println("Second");
         } else {
          System.out.println("First");
         }
         
        }
        
 }
}