/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/


import java.util.Scanner;


class Solution 
{
 
 public static void main(String[] args)
 {
  Scanner scanner = new Scanner(System.in);
  int n = scanner.nextInt();
  int k = scanner.nextInt();
  
  int[] A = new int[n+1];
  A[0] = 0; // use 1 index
  for (int i = 1; i < n+1; i++)
   A[i] = scanner.nextInt();
  
  // Single array for opt values
  
  long[] P = new long[n+1];
  P[0] = 0;
  
  int hole = 0;

  for (int i = 1; i < n+1; i++)
  {
   long intervalSum = 0;
   long v = 0;
   int h;
   
   // optimization for O(2*n)
   if (i - hole <= k)
   {
    P[i] = P[i-1] + A[i];
   }
   else
   {
    for (int j = 0; j < k; j++)
    {
     if (i-j <= 0)
      break;
     
     intervalSum += A[i-j];
     h = (i-2-j > 0) ? i-2-j : 0;
     if (intervalSum + P[h] > v)
     {
      v = intervalSum + P[h];
      hole = h + 1;
     }
    }
    P[i] = (v > P[i-1]) ? v : P[i-1];
   }
  }
  System.out.println(P[n]);
 }
}