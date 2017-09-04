/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

 long[] t;

 public static void main(String[] args) {
  Scanner s = new Scanner(System.in);
  int N = s.nextInt();
  int K = s.nextInt();
  int[] p = new int[N];
  for (int i = 0; i < N; i++)
   p[i] = s.nextInt();

  if (N == 0) {
   System.out.println(0);
  } else if (N == 1) {
   System.out.print(p[0]);
  } else {
   final long[] b = new long[N + 1];
   for (int i = 0; i < N; i++) {
    b[i + 1] = b[i] + p[i];
   }
   final PriorityQueue<Node> bucket = new PriorityQueue<Node>();
   bucket.offer(new Node(b[N], N + 1));
   Node hi = new Node(b[N - 1], N);
   bucket.offer(hi);
   long maxSum = 0;
   for (int i = N - 1; i >= 0; i--) {
    while (bucket.peek().index > i + K + 1)
     bucket.poll();
    final long max = bucket.peek().value - b[i];
    if (max > maxSum)
     maxSum = max;
    hi = new Node(max + (i > 0 ? b[i - 1] : 0), i);
    bucket.offer(hi);
   }
   System.out.println(maxSum);
  }
 }

 static class Node implements Comparable<Node> {
  long value;
  int index;

  public Node(final long value, final int index) {
   this.value = value;
   this.index = index;
  }

  public int compareTo(final Node other) {
   return (int) (other.value - this.value);
  }

  @Override
  public String toString() {
   return index + "," + value;
  }

 }

}