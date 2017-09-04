import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {
 public static void main(String[] args) { 
  
  Scanner in = new Scanner(System.in);
        
        int arraySize = in.nextInt();
        
        int nums [] = new int [arraySize];
        
        int p, q;
        
        for (int i = 0; i < arraySize; i ++){
         nums [i] = in.nextInt();
        }
        
        p = in.nextInt();
        q = in.nextInt();
        
        System.out.println (min (p, q, nums));
   }
 
 
 public static int min (int p, int q, int nums []){
     
  int biggestSmallestDiff = Integer.MIN_VALUE;
  int target = Integer.MAX_VALUE;

  Arrays.sort (nums);
     
  int i = 0;
     
  for (; p <= q; p ++){
      int smallestDiff = Integer.MAX_VALUE;
      for (; i < nums.length; i ++){
       int sol = nums [i] > p ? nums [i] - p : p - nums [i];
             if (sol < smallestDiff)
              smallestDiff = sol;
             else
              break;
         }
      
      i --;
      
      if (smallestDiff > biggestSmallestDiff){
       target = p;
       biggestSmallestDiff = smallestDiff;
      }
     }
     
     return target;
 }
}