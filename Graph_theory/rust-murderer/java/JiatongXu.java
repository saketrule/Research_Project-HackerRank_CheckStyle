 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Solution {
 public static class MyQueue {
  Node head;
  Node tail;
  int length;

  public boolean isEmpty() {
   if (this.length == 0)
    return true;
   return false;
  }

  public void offer(Node nd) {

   if (this.length == 0) {
    this.head = nd;
    this.tail = nd;
   } else {
    this.tail.next = nd;
    this.tail = nd;
   }
   this.length++;
  }

  public Node poll() {
   Node n = this.head;
   this.head = this.head.next;
   this.length--;
   return n;
  }
 }

 public static class Node {
  int distance;
  int idx;
  Node next;

  public Node(int distance, int idx) {
   this.distance = distance;
   this.idx = idx;
  }
 }

 public static void BFS(int fromVertex, HashSet<Integer>[] hs, int N,
   int[] dist, HashSet<Integer> novisted) {
  boolean[] visited = new boolean[N];
  visited[fromVertex] = true;
  MyQueue q = new MyQueue();
  q.offer(new Node(0, fromVertex));
  while (!q.isEmpty()) {
   Node nd = q.poll();
   HashSet<Integer> next = new HashSet<Integer>();
   for (int i : novisted) {

    if (!visited[i] && hs[nd.idx].contains(i)) {
     next.add(i);
     continue;
    }
    visited[i] = true;

    dist[i] = nd.distance + 1;
    q.offer(new Node(nd.distance + 1, i));

   }
   novisted = next;
  }

 }

 public static void main(String args[]) throws Exception {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  int T = Integer.valueOf(br.readLine());
  for (int i = 0; i < T; i++) {
   String[] nbs = br.readLine().split(" ");
   int N = Integer.valueOf(nbs[0]);
   int M = Integer.valueOf(nbs[1]);
   HashSet<Integer>[] hs = new HashSet[N];
   HashSet<Integer> novisted = new HashSet<Integer>();
   int N1 = N;

   while (N1-- > 0) {
    hs[N1] = new HashSet<Integer>();

   }
   for (int j = 0; j < M; j++) {
    nbs = br.readLine().split(" ");
    int a = Integer.valueOf(nbs[0]) - 1;
    int b = Integer.valueOf(nbs[1]) - 1;
    hs[a].add(b);
    hs[b].add(a);

   }
   int fromVertex = Integer.valueOf(br.readLine()) - 1;
   N1 = N;

   while (N1-- > 0) {

    if (fromVertex != N1)
     novisted.add(N1);
   }
   int[] dist = new int[N];

   BFS(fromVertex, hs, N, dist, novisted);
   StringBuilder sb = new StringBuilder(N);
   boolean first = true;
   for (int j = 0; j < N; j++) {
    if (j == fromVertex)
     continue;
    if (first) {
     sb.append(dist[j]);
     first = false;
    } else
     sb.append(" " + dist[j]);

   }
   System.out.println(sb);

  }
  System.out.close();
  System.exit(0);
 }
}