import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {

 public static void main(String[] args) throws IOException {
  long startTime = System.currentTimeMillis();
  long programTime = startTime;

  MyScanner in = new MyScanner((args.length > 0) ? new FileInputStream(args[0]) : System.in);
  
  int n = in.nextInt();
  int m = in.nextInt();

  // Keep vertex & edge weight in the map to eliminate duplicate edges between the same vertices.
  Map<Edge, Integer> edges = new HashMap<Edge, Integer>((int) Math.ceil(m / 0.75f));

  // initialize edges.
  while (m-- > 0) {
   int v1 = in.nextInt();
   int v2 = in.nextInt();
   int w = in.nextInt();
   Edge newEdge = new Edge(v1, v2, w);
   if (edges.containsKey(newEdge)) {
    if (w < edges.get(newEdge)) {
     edges.remove(newEdge);
    } else {
     continue;
    }
   }
   // This will overwrite any existing edge with the same v1, v2 values with smaller weight if exists!
   // Also check Edge.equals(o): the equality check only focuses on v1 & v2 for this case, ignoring weight.
   edges.put(newEdge, w);
  }

  System.err.printf("Reading input took %1$d msec!\n", (System.currentTimeMillis() - startTime));
  startTime = System.currentTimeMillis();

  Map<Integer, Integer>[] mstAdjList = findKruskalsMSTWeight(edges.keySet(), n, 1);
  System.err.printf("Finding Kruskal's MST took %1$d msec!\n", (System.currentTimeMillis() - startTime));
  startTime = System.currentTimeMillis();

  List<Integer>[] multipliers = runPostOrderTraversal(mstAdjList, n, 1);
  System.err.printf("Running DFS on MST took %1$d msec!\n", (System.currentTimeMillis() - startTime));
  startTime = System.currentTimeMillis();

  BigInteger total = calculateTotalWeightsOfAllShortestPaths(multipliers, n);
  System.err.printf("Calculating BigInteger took %1$d msec!\n", (System.currentTimeMillis() - startTime));
  startTime = System.currentTimeMillis();

  System.out.println(total.toString(2));
  System.err.printf("Printing BigInteger took %1$d msec!\n", (System.currentTimeMillis() - startTime));

  System.err.printf("Program took %1$d msec!\n", (System.currentTimeMillis() - programTime));
 }

 /**
  * Bit mask operation for easy addition of two's power and distinct numbers!
  * 
  * <pre>
  * 00000001 -> 1
  * 00000010 -> 2
  * 00000100 -> 4
  * 00001000 -> 8
  * 00010000 -> 16
  * 00100000 -> 32
  * 01000000 -> 64
  * 10000000 -> -128
  * </pre>
  */
 static final byte[] BIT_MASK = { 1, 2, 4, 8, 16, 32, 64, -128 };

 /**
  * Calculates total weights of all shortest paths as BigInteger. multipliers an array of List of Integers. Each
  * array index represents multiplier. For example i=1 means, 1 *(n-1) and i=n/2 means, (n/2)*(n/2) where n is the
  * total num of nodes.
  * 
  * @param multipliers
  * 
  * @param n
  * 
  * @return total weights of all shortest paths as BigInteger.
  */
 static BigInteger calculateTotalWeightsOfAllShortestPaths(List<Integer>[] multipliers, int n) {
  BigInteger total = ZERO;

  for (int i = 1; i < multipliers.length; i++) {
   if (multipliers[i].isEmpty()) {
    continue;
   }
   int maxWeight = Collections.max(multipliers[i]);
   int arrLength = (int) Math.ceil((maxWeight + 1) / 8f);
   byte[] byteArr = new byte[arrLength];
   for (int weight : multipliers[i]) {
    int requiredBytesCount = (int) Math.ceil((weight + 1) / 8f);
    int byteIndex = arrLength - requiredBytesCount;
    // The remainder is the bit index inside a byte identified by byteIndex.
    int bitIndex = weight % 8;
    byteArr[byteIndex] = (byte) (byteArr[byteIndex] | BIT_MASK[bitIndex]);
   }

   total = total.add(new BigInteger(1, byteArr).multiply(valueOf((long)i * (n - i))));
  }

  return total;
 }

 /**
  * Find Kruskals MST first in order to find all shortest paths between each pair of vertices. This works because
  * the weights of edges are distinct powers of two.
  * 
  * @param edges the set of edges with their min weights.
  * 
  * @param n num of total vertices. 
  * 
  * @param s pick a starting node
  * 
  * @return MST representation in the form of Map array. v1 -> v2 -> weight.
  */
 static Map<Integer, Integer>[] findKruskalsMSTWeight(Set<Edge> edges, int n, int s) {
  @SuppressWarnings("unchecked")
  Map<Integer, Integer>[] mstAdjList = new Map[n + 1]; // min spanning tree.
  for (int i = 1; i <= n; i++) {
   mstAdjList[i] = new HashMap<Integer, Integer>();
  }

  DisjointSet disjointSet = new DisjointSet(n);
  PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>(edges);
  int count = 0; 
  while (count < n - 1) { // There are N-1 edges in an MST.
   // The min-weight edge that is being evaulated must be removed from the queue.
   Edge current = pQueue.poll();
   if (!disjointSet.isSameSet(current.v1, current.v2)) {
    disjointSet.union(current.v1, current.v2);
    count++;
    mstAdjList[current.v1].put(current.v2, current.weight);
    mstAdjList[current.v2].put(current.v1, current.weight);
   }
  }

  return mstAdjList;
 }

 /**
  * This method finds the number of nodes in a sub-tree (including the node itself) with DFS.
  * 
  * In MST, an edge that connects a leaf node to its parent node is used n-1 times when calculating shortest paths
  * from the leaf node to all other nodes. Since BigInteger operations are expensive, this method will try to reduce
  * the operations required on BigIntegers by using the equality (n-1)a + (n-1)b + (n-1)c = (n-1)(a+b+c). Similar
  * method will be applied to nodes in rank-2, where the multiplier will be 2(n-2). Skip root node.
  */
 static List<Integer>[] runPostOrderTraversal(Map<Integer, Integer>[] mstNodes, int n, int mstRoot) {
  // From 1*(n-1) to (n/2)*(n/2), the multipliers of weights of all shortest paths in MST.
  @SuppressWarnings("unchecked")
  List<Integer>[] multipliers = new List[1 + n / 2]; // indices 1 to n/2.
  for (int i = 1; i < multipliers.length; i++) {
   multipliers[i] = new ArrayList<Integer>();
  }

  int[] numOfNodes = new int[n + 1]; // initially all 0.
  int[] weights = new int[n + 1]; // weight between a node and its parent.
  boolean[] visited = new boolean[n + 1]; // initially all false.
  Deque<Integer> stack = new ArrayDeque<Integer>(n);
  // pick the first node as the starting point.
  stack.push(mstRoot);
  while (!stack.isEmpty()) {
   int current = stack.peek();
   if (visited[current] == false) {
    // go down in the tree: from parent to child!
    if (mstNodes[current] != null) {
     for (Map.Entry<Integer, Integer> child : mstNodes[current].entrySet()) {
      if (visited[child.getKey()] == false) {
       stack.push(child.getKey());
       weights[child.getKey()] = child.getValue();
      }
     }
    } else {
     // leaf node
     stack.pop();
     numOfNodes[current] = 1;
     multipliers[1].add(weights[current]);
    }
    visited[current] = true;
   } else {
    // go up in the tree: from child to parent!
    stack.pop();
    for (Map.Entry<Integer, Integer> child : mstNodes[current].entrySet()) {
     numOfNodes[current] += numOfNodes[child.getKey()];
    }
    numOfNodes[current] += 1;
    if (numOfNodes[current] < n) {
     int multiplierIndex = Math.min(numOfNodes[current], n - numOfNodes[current]);
     multipliers[multiplierIndex].add(weights[current]);
    }
   }
  }

  return multipliers;
 }

 // //////////////////////////////////////////
 static class Edge implements Comparable<Edge> {
  int v1;
  int v2;
  int weight;

  Edge(int v1, int v2, int weight) {
   // smaller one will always be v1 and larger one will be v2 so that hashCode() and equals() work smoothly.
   if (v1 < v2) {
    this.v1 = v1;
    this.v2 = v2;
   } else {
    this.v2 = v1;
    this.v1 = v2;
   }

   this.weight = weight;
  }

  @Override
  public int compareTo(Edge o) {
   // each edge has unique weight.
   return weight - o.weight;
  }

  @Override
  public int hashCode() {
   // Even though v1 and v2 are of type 'int', they are actually in 'short' range by problem definition:
   // i.e. 1 <= N <= 100,000
   // So this shift operation works fine with integers.
   return v1 * 17 + v2;
  }

  @Override
  public boolean equals(Object obj) {
   if (!(obj instanceof Edge)) {
    return false;
   }
   Edge other = (Edge) obj;
   // ignoring weight in equality check. Only the minimum weight will be kept.
   return v1 == other.v1 && v2 == other.v2;
  }
 }

 static class DisjointSet {
  Node[] nodes;

  DisjointSet(int n) {
   // 'n+1' is for node-id and array-index matching.
   nodes = new Node[n + 1];
   makeSet(n);
  }

  void makeSet(int size) {
   for (int i = 1; i <= size; i++) {
    nodes[i] = new Node(i);
   }
  }

  int findSet(int id) {
   Node child = nodes[id];
   if (child.parent == child.id) {
    return child.id;
   } else {
    // 'child.parent' assignment here is path compression.
    // i.e. a1.p -> a2.p -> root ==> a1.p -> root
    return child.parent = findSet(child.parent);
   }
  }

  boolean isSameSet(int x, int y) {
   return findSet(x) == findSet(y);
  }

  void union(int x, int y) {
   // find roots rx, ry.
   int rootX = findSet(x);
   int rootY = findSet(y);
   if (rootX == rootY) {
    return; // they are already in the same set.
   }

   // Merge two disjoint sets.
   if (nodes[rootX].rank < nodes[rootY].rank) {
    nodes[rootX].parent = rootY;
   } else if (nodes[rootX].rank > nodes[rootY].rank) {
    nodes[rootY].parent = rootX;
   } else {
    nodes[rootY].parent = rootX;
    nodes[rootX].rank++;
   }
  }
 }

 static class Node {
  int parent; // This parent is for inner-working of disjoint set.
  int id;
  int rank = 0; // initially zero.

  Node(int id) {
   this.id = id;
   this.parent = id; // the 'root' property. initially all nodes are 'root'.
  }

  @Override
  public int hashCode() {
   return id;
  }

  @Override
  public String toString() {
   return "id: " + id + ", p: " + parent + ", r: " + rank + "\n";
  }
 }

 // //////////////////////////////
 static class MyScanner {
  static final char ZERO = '0';
  static final char NINE = '9';
  static final int RADIX = 10;

  InputStream is;
  byte[] charBuf = new byte[1024 * 4];
  int current;
  int charCount;

  MyScanner(InputStream is) {
   this.is = is;
   if (is instanceof FileInputStream) {
    System.err.println("Reading input from file...");
   } else {
    System.err.print("Type input here(System.in): ");
   }
  }

  private int read() {
   if (charCount == -1) {
    throw new InputMismatchException("charCount is -1");
   }
   if (current >= charCount) {
    current = 0;
    try {
     charCount = is.read(charBuf);
    } catch (IOException e) {
     e.printStackTrace();
     throw new InputMismatchException("IOException: " + e.toString());
    }
    if (charCount <= 0) {
     return -1;
    }
   }
   return charBuf[current++];
  }

  int nextInt() {
   int c = read();
   while (isSpace(c)) {
    c = read();
   }
   int sign = 1;
   if (c == '-') {
    sign = -1;
    c = read();
   }
   int result = 0;
   do {
    if (c < ZERO || c > NINE)
     throw new InputMismatchException("result: " + result + ", non-digit char: " + c);
    result *= RADIX; // assume radix = 10
    // digit chars start with '0' char. It is basis.
    result += c - ZERO;
    c = read();
   } while (!isSpace(c));
   return result * sign;
  }

  public String next() {
   int c;
   do {
    c = read();
   } while (isSpace(c));

   StringBuilder sb = new StringBuilder();
   do {
    sb.appendCodePoint(c);
    c = read();
   } while (!isSpace(c));
   return sb.toString();
  }

  boolean isSpace(int c) {
   return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
  }
 }
}