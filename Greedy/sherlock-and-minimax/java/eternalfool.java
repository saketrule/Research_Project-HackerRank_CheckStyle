import java.util.Arrays;
import java.util.Scanner;

public class Solution {

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int N = in.nextInt();
  int[] ar = new int[N];
  boolean goinsmall;
  boolean goinlarge;
  long answer = 0;
  for (int j = 0; j < N; j++) {
   ar[j] = in.nextInt();
  }
  int P = in.nextInt();
  int Q = in.nextInt();
  Arrays.sort(ar);
  // print(ar);
  long smallsum = -1;
  long largesum = -1;
  if (P <= ar[0]) {
   smallsum = ar[0] - P;
  }
  if (Q > ar[N - 1]) {
   largesum = Q - ar[N - 1];
  }
  int p = 0;
  int q = N - 1;
  while (ar[p] < P) {
   p++;
   if (p == N - 1) {
    break;
   }
  }
  if (P > ar[0]) {
   goinsmall = true;
   P = Math.max((ar[p] + ar[p - 1]) / 2, P);
   // answer = (ar[p] + ar[p - 1]) / 2;
   smallsum = Math.min(ar[p] - P, P - ar[p - 1]);
   // smallsum = Math.min(ar[p] - P, P - ar[p - 1]);
  }
  while (ar[q] > Q) {
   q--;
   if (q == 0) {
    break;
   }
  }
  if (Q < ar[N - 1]) {
   // largesum = Math.min(Q - ar[q],ar[q+1]-Q);
   Q = Math.min((ar[q] + ar[q + 1]) / 2, Q);
   largesum = Math.min(ar[q + 1] - Q, Q - ar[q]);
  }
  //System.out.println("p is " + p);
  //System.out.println("q is " + q);
  long minisum = -1;
  for (int i = q; i > p; i--) {
   if ((ar[i] - ar[i - 1]) / 2 >= minisum) {
    answer = (ar[i] + ar[i - 1]) / 2;
    minisum = Math.min(ar[i] - answer, answer - ar[i - 1]);
    //System.out.println("Minisum is " + minisum + " for " + ar[i]
      //+ " and " + ar[i - 1]);
   }
  }
  //System.out.println("Largesum is " + largesum);
  //System.out.println("smallsum is " + smallsum);
  //System.out.println("minisum is " + minisum);
  if (smallsum >= minisum) {
   answer = P;
   if (largesum > smallsum) {
    answer = Q;
   }
  }
  if (largesum > minisum) {
   answer = Q;
  }
  System.out.println(answer);

 }

 private static void print(int[] ar) {
  System.out.println();
  for (int i = 0; i < ar.length; i++) {
   System.out.print(ar[i] + " ");
  }

 }
}