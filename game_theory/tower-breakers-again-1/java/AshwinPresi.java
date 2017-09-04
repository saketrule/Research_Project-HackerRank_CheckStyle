import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int t = 0;
        int winner;
        int N = 0;
        int endFlag;
        Scanner in = new Scanner(System.in);
        t = in.nextInt();
        while(t>0)
            {
            winner = 2;
            endFlag = 0;
            N = in.nextInt();
            int[] a = new int[N];
            int[] b = new int[N];
            for(int i =0;i<N;i++)
                {
                a[i] = in.nextInt();
                b[i] = factorCount(a[i]);
            }
            while(endFlag == 0)
                {
                  for(int i=0;i<N;i++)
                      {
                      if(b[i]>1)
                          {
                          b[i] = b[i]-1;
                          if(winner == 2)
                          {
                          winner = 1;
                          }
                          else 
                              {
                              winner = 2;
                          }
                          break;
                       }
                      else
                          {
                          if(i == N-1)
                              {
                              endFlag = 1;
                          }
                          continue;
                      }
                  }
            }
            System.out.println(winner);
            t--;
        }
    }
    public static int factorCount(int num)
        {
          int count = 1;
          for(int i = num/2;i > 0;i--) // there is a more optimal way here :P 
              {
              if(num % i == 0)
                  {
                  count++;
              }
          }
        return count;
    }
}