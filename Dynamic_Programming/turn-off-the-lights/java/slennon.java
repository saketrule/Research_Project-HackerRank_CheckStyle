import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
   static int findCost(int [][] dp, int [] array, int k, int start, int end, int cost)
   {
      if (dp[start][end] != -1)
      {
         return dp[start][end];
      }
      
      if (start == end)
      {
         return cost + array[start];
      }
      
      int ret = Integer.MAX_VALUE;
      for (int i=start; i<=end; i++)
      {
         boolean recured = false;
         cost += array[i];
         if (i-k-1 >= start)
         {
            recured = true;
            ret = Math.min(ret,
                  findCost(dp, array, k, start, i-k-1, cost + array[i]));
         }
         if (i+k+1 <= end)
         {
            recured = true;
            ret = Math.min(ret,
                  findCost(dp, array, k, i+k+1, end, cost));
         }
         if (!recured)
         {
            ret = Math.min(ret, cost);
         }
         cost -= array[i];
      }
      dp[start][end] = ret;
      return ret;
   }
   
   static int findCost(int [] array, int k)
   {
      if (k == 0)
      {
         int ret = 0;
         for (int i=0; i<array.length; i++)
         {
            ret += array[i];
         }
         return ret;
      }
      int [][] dp = new int[array.length][array.length];
      for (int i=0; i<dp.length; i++)
      {
         Arrays.fill(dp[i], -1);
      }
      return findCost(dp, array, k, 0, array.length-1, 0);
   }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int [] bulbs = new int[n];
        for (int i=0; i<bulbs.length; i++){
            bulbs[i] = in.nextInt();
        }
        System.out.println(findCost(bulbs, k));
    }
}