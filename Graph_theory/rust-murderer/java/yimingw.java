import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        StringBuilder sb = new StringBuilder();
  Scanner sc = new Scanner(System.in);
  int T = sc.nextInt();
  while (T-- > 0) {
   int N = sc.nextInt(), M = sc.nextInt();
   List<Set<Integer>> adj = new ArrayList<Set<Integer>>();
   for (int i = 0; i < N; i++) {
    adj.add(new HashSet<Integer>());
   }
   for (int i = 0; i < M; i++) {
    int a = sc.nextInt(), b = sc.nextInt();
    adj.get(a-1).add(b-1);
    adj.get(b-1).add(a-1);
   }
   int S = sc.nextInt() - 1;
   Set<Integer> unvisited = new HashSet<Integer>();
   for (int i = 0; i < N; i++)
    unvisited.add(i);
   Queue<Integer> queue = new LinkedList<Integer>();
   queue.add(S); unvisited.remove(S);
   int step = 0;
   int[] res = new int[N-1];
   while (! queue.isEmpty()) {
    int count = queue.size();
    for (int i = 0; i < count; i++) {
     int p = queue.remove();
     if (p != S) {
      if (p > S) {
       res[p-1] = step;
      } else {
       res[p] = step;
      }
     }
     Set<Integer> set = adj.get(p);
     Set<Integer> toRemove = new HashSet<Integer>();
     for (Integer j : unvisited) {
      if (! set.contains(j)) {
       queue.add(j); toRemove.add(j);
      }
     }
     unvisited.removeAll(toRemove);
    }
    step++;
   }
   for (int j = 0; j < N-1; j++) {
    sb.append(res[j]);
    if (j < N-2)
     sb.append(" ");
   }
   sb.append("\n");
  }
  sc.close();
  System.out.println(sb.toString());
    }
}