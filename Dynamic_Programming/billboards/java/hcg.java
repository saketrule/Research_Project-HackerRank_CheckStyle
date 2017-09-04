/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.io.*;
import java.util.*;

public class Solution {
 static int board, seq;
 static long max, sum, tmpmax;
 static long[] sums, boards;

 public static void main(String[] arg) throws IOException {
  // Scanner sc = new Scanner(new FileInputStream(new File("output")));
  Scanner sc = new Scanner(System.in);
  long st = System.currentTimeMillis();
  board = sc.nextInt();
  seq = sc.nextInt();
  max = 0;
  sum = 0;
  tmpmax = 0;
  sums = new long[board + 1];
  boards = new long[board + 1];
  for (int i = 1; i <= board; i++) {
   boards[i] = sc.nextLong();
   sum += boards[i];
   if (i <= seq + 1)
    sums[i] = boards[i];
  }
  // System.out.println("sum:"+sum);
  if (seq == board)
   System.out.println(sum);
  else {
   int last = -200000;
   for (int i = seq + 2; i <= board; i++) {
    long tmp = Long.MAX_VALUE;
    if (i-last > seq+1) {
     int t = 0;
     for (int j = seq + 1; j >= 1; j--) {
      if (boards[i] + sums[i - j] < tmp) {
       tmp = boards[i] + sums[i - j];
       t = i-j;
      }
     }
     sums[i] = tmp;
     last = t;
    }
    else{
     if(sums[last] < sums[i-1]){
      sums[i] = boards[i] + sums[last];
     }
     else{
      sums[i] = boards[i] + sums[i-1];
      last = i-1;
     }
    }
   }
   long tmpmin = Long.MAX_VALUE;
   for (int i = board; i >= board - seq; i--) {
    if (sums[i] < tmpmin) {
     tmpmin = sums[i];
    }
   }
   System.out.println(sum - tmpmin);
  }
  // System.out.println(System.currentTimeMillis() - st);
 }

}