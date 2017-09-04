import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 static long mask[] = new long[64];
 static {
  for (int i = 64 - 1; i >= 0; i--) {
   long temp = 1L << i;
   mask[i] = temp;
  }
 }

 public static void main(String[] args) throws IOException {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  Scanner sc = new Scanner(br);
  int N = sc.nextInt();
  int M = sc.nextInt();
  List<Edge> list = new ArrayList<Edge>();
  for (int i = 0; i < M; i++) {
   list.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
  }
  Collections.sort(list);
  DisjointSet disjointSet = new DisjointSet(N);
  List<Edge> mst = new ArrayList<Edge>();
  Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
  int leaves[] = new int[N];
  for (int i = 0; i < list.size() && mst.size() < N - 1; i++) {
   Edge currentEdge = list.get(i);
   int x = disjointSet.findSet(currentEdge.src - 1);
   int y = disjointSet.findSet(currentEdge.dest - 1);
   if (x != y) {
    mst.add(currentEdge);
    leaves[currentEdge.src - 1]++;
    leaves[currentEdge.dest - 1]++;
    List<Integer> tempList = map.get(currentEdge.src);
    if (tempList == null) {
     tempList = new ArrayList<Integer>();
     map.put(currentEdge.src, tempList);
    }
    tempList.add(currentEdge.dest);
    tempList = map.get(currentEdge.dest);
    if (tempList == null) {
     tempList = new ArrayList<Integer>();
     map.put(currentEdge.dest, tempList);
    }
    tempList.add(currentEdge.src);
    disjointSet.link(x, y);
   }
  }
  // System.out.println(mst);
  Pair p = null;

  for (int i = 0; i < leaves.length; i++) {
   if (leaves[i] == 1) {
    p = new Pair(i + 1, map.get(i + 1).get(0));
    break;
   }
  }

  // int arr[][][] = new int[N + 1][N + 1][2];
  Map<Pair, int[]> arrMap = new HashMap<Solution.Pair, int[]>();
  arrMap.put(p, new int[2]);
  arrMap.get(p)[0] = 1;

  arrMap.get(p)[0] = 1;
  arrMap.get(p)[1] = applyDestinationCount(arrMap, p.src, p.dst, map, N);
  updateUlta(arrMap, p);

  // for (Edge e : mst) {
  // System.out.println(e.src + "-->" + e.dest + " = "
  // + arr[e.src][e.dest][0] + " : " + arr[e.src][e.dest][1]);
  // }
  int sumArr[] = new int[3 * 100000];
  int sumOutput[] = new int[3 * 100000];
  int maxLen = 0;
  for (Edge e : mst) {
   Pair mulPair = new Pair(e.src, e.dest);
   int[] mulVal = arrMap.get(mulPair);
   long multiplier = (1L * mulVal[0]) * mulVal[1];
   int power = e.weight;
   for (int j = 0; j < 64 && multiplier >= mask[j]; j++) {
    if ((multiplier & mask[j]) != 0) {
     sumArr[power + j]++;
     maxLen = Math.max(maxLen, power + j);
    }
   }
  }
  for (int i = 0; i < sumArr.length && i <= maxLen; i++) {
   int current = sumOutput[i] + sumArr[i];
   sumOutput[i] = 0;
   for (int j = 0; j < 64 && current >= mask[j]; j++) {
    if ((current & mask[j]) != 0) {
     sumOutput[i + j]++;
     maxLen = Math.max(maxLen, i + j);
    }
   }
  }
  boolean found1 = false;
  StringBuilder sb = new StringBuilder();
  for (int i = maxLen; i >= 0; i--) {
   if (found1 || sumOutput[i] == 1) {
    found1 = true;
    sb.append(sumOutput[i]);
   }
  }
  System.out.println(sb);
 }

 private static void updateUlta(Map<Pair, int[]> arrMap, Pair p) {
  int srcArr[] = arrMap.get(p);
  Pair dstPair = new Pair(p.dst, p.src);
  int arrValue[] = null;
  if (arrMap.get(dstPair) == null) {
   arrValue = new int[2];
   arrMap.put(dstPair, arrValue);
  }
  arrValue[0] = srcArr[1];
  arrValue[1] = srcArr[0];
 }

 private static int applyDestinationCount(Map<Pair, int[]> arr,
   int sourceVertex, int dstVertex, Map<Integer, List<Integer>> map,
   int total) {
  List<Integer> list = map.get(dstVertex);
  int sum = 1;
  for (int i : list) {
   if (i == sourceVertex)
    continue;
   Integer temp = null;
   Pair p = new Pair(dstVertex, i);
   int arrValue[] = null;
   if (arr.get(p) == null) {
    arrValue = new int[2];
    arr.put(p, arrValue);
   }
   if (arrValue[1] == 0) {
    if (map.get(i).size() == 1) {
     temp = 1;
    } else {
     temp = applyDestinationCount(arr, dstVertex, i, map, total);
    }
    arrValue[1] = temp;
    arrValue[0] = total - temp;
    updateUlta(arr, p);
   } else {
    temp = arrValue[1];
   }
   sum = sum + temp;
  }
  return sum;
 }

 static class DisjointSet {
  private Element[] array;

  public DisjointSet(int n) {
   array = new Element[n];
   for (int i = 0; i < n; i++) {
    array[i] = new Element(i, 0);
   }
  }

  private void link(int i, int j) {
   if (array[i].getRank() > array[j].getRank()) {
    array[j].setP(i);
   } else {
    array[i].setP(j);
    if (array[i].getRank() == array[j].getRank()) {
     array[j].setRank(array[j].getRank() + 1);
    }
   }
  }

  public void union(int i, int j) {
   link(findSet(i), findSet(j));
  }

  public int findSet(int i) {
   if (array[i].getP() != i) {
    array[i].setP(findSet(array[i].getP()));
   }
   return array[i].getP();
  }

  private class Element {
   private int p;
   private int rank;

   public int getP() {
    return p;
   }

   public void setP(int p) {
    this.p = p;
   }

   public int getRank() {
    return rank;
   }

   public void setRank(int rank) {
    this.rank = rank;
   }

   public Element(int p, int rank) {
    super();
    this.p = p;
    this.rank = rank;
   }
  }
 }

 static class Pair {
  int src;
  int dst;

  public Pair(int a, int b) {
   this.src = a;
   this.dst = b;
  }

  @Override
  public int hashCode() {
   final int prime = 31;
   int result = 1;
   result = prime * result + dst;
   result = prime * result + src;
   return result;
  }

  @Override
  public boolean equals(Object obj) {
   if (this == obj)
    return true;
   if (obj == null)
    return false;
   if (getClass() != obj.getClass())
    return false;
   Pair other = (Pair) obj;
   if (dst != other.dst)
    return false;
   if (src != other.src)
    return false;
   return true;
  }

 }

 static class Edge implements Comparable<Edge> {
  int src, dest, weight;
  int srcVertices;
  int dstVertices;

  public Edge(int src, int dest, int weight) {
   if (src > dest) {
    int temp = src;
    src = dest;
    dest = temp;
   }
   this.src = src;
   this.dest = dest;
   this.weight = weight;
  }

  public int compareTo(Edge compareEdge) {
   return this.weight - compareEdge.weight;
  }

  @Override
  public int hashCode() {
   final int prime = 31;
   int result = 1;
   result = prime * result + dest;
   result = prime * result + src;
   return result;
  }

  @Override
  public boolean equals(Object obj) {
   if (this == obj)
    return true;
   if (obj == null)
    return false;
   if (getClass() != obj.getClass())
    return false;
   Edge other = (Edge) obj;
   if (dest != other.dest)
    return false;
   if (src != other.src)
    return false;
   return true;
  }

  @Override
  public String toString() {
   return src + " --- " + dest + "  " + weight;
  }

 }
}