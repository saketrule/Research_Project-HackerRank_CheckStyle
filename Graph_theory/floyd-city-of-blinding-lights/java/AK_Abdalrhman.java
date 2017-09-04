//package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraPoly {

 static class E implements Comparable<E> {
  int src, dst, wgt;

  public E(int src, int dst, int weight) {
   this.src = src;
   this.dst = dst;
   this.wgt = weight;
  }

  public int compareTo(E that) {
   return this.wgt - that.wgt;
  }
 }

 static class AdjT<T extends Comparable<T>> implements Iterable<T> {
  ArrayList<T> adj;

  public AdjT() {
   this.adj = new ArrayList<T>();
  }

  public int size() {
   return adj.size();
  }

  public void add(T edge) {
   adj.add(edge);
  }

  public void add(ArrayList<T> edges) {
   adj.addAll(edges);
  }

  @Override
  public Iterator<T> iterator() {
   return adj.iterator();
  }
 }

 static class Adj implements Iterable<E> {
  ArrayList<E> adj;

  public Adj() {
   this.adj = new ArrayList<E>();
  }

  public int size() {
   return adj.size();
  }

  public void add(E edge) {
   adj.add(edge);
  }

  public void add(ArrayList<E> edges) {
   adj.addAll(edges);
  }

  @Override
  public Iterator<E> iterator() {
   return adj.iterator();
  }
 }

  public static int[] dijkstraSingle(final Adj[] g, final int S) {
  int N = g.length, exploreNodes = 0, OO = 1000000000;
  int[] dist = new int[N];
  int[] prnt = new int[N];
  boolean[] vist = new boolean[N];

  Arrays.fill(dist, OO);
  Arrays.fill(prnt, -1);

  PriorityQueue<E> pq = new PriorityQueue<E>();
  pq.add(new E(S, S, 0));
  while (!pq.isEmpty() && exploreNodes < N) {
   E cur = pq.poll();
   if (!vist[cur.dst]) {
    dist[cur.dst] = cur.wgt;
    prnt[cur.dst] = cur.src;
    vist[cur.dst] = true;
    exploreNodes++;
    for (E nxt : g[cur.dst]) {
     if (!vist[nxt.dst]) pq.offer(new E(nxt.src, nxt.dst,
       dist[nxt.src] + nxt.wgt));
    }
   }
  }
  return dist;
 }

 public static int[][] dijkstraAll(final Adj[] g) {
  int N = g.length;
  int[][] dist = new int[N][N];
  for (int n = 0; n < N; n++)
   dist[n] = dijkstraSingle(g, n);
        //for (int n = 0; n < N; n++) System.out.println(Arrays.toString(dist[n]));
  return dist;
 }

 public static int I(String s) {
  return Integer.parseInt(s);
 }

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int N, M, Q;
  String[] s;
  int[][] adj;
  s = in.nextLine().split("\\s+");
  N = I(s[0]);
  M = I(s[1]);
  adj = new int[N][N];
  for (int i = 0; i < N; i++)
   Arrays.fill(adj[i], 1000000000);
  for (int i = 0; i < M; i++) {
   s = in.nextLine().split("\\s+");
   int src = I(s[0]) - 1, dst = I(s[1]) - 1, w = I(s[2]);
      adj[src][dst] = w;
  }

  Adj[] g = new Adj[N];
  for (int i = 0; i < N; i++) {
   g[i] = new Adj();
   for (int j = 0; j < N; j++) {
    if (adj[i][j] < 1000000000 && i != j) g[i].add(new E(i, j, adj[i][j]));
   }
  }
  Q = I(in.nextLine());
  int[][] dist = dijkstraAll(g);
  while (Q-- > 0) {
   s = in.nextLine().split("\\s+");
   int src = I(s[0]) - 1, dst = I(s[1]) - 1;
   System.out.println(dist[src][dst] < 1000000000 ? dist[src][dst]
     : -1);
  }

  in.close();
 }
}