import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {

 public static void main(String[] args) throws IOException {
  long programTime = System.currentTimeMillis();

  Scanner in = new Scanner((args.length > 0) ? new FileInputStream(args[0]) : System.in);
  System.err.print("Type input here: ");
  int p = in.nextInt();
  int q = in.nextInt();

  Graph mergedGraph = null;
  if (q == 2) {
   List<Graph> list = GraphQ2Helper.findGraphs(p);
   mergedGraph = Graph.mergeGraphs(list);
  } else if (q % 2 == 1) { // Q is odd.
   mergedGraph = GraphQ3PlusHelper.createTriangleStarGraph(p, q);
  } else { // Q is even other than 2.
   mergedGraph = GraphQ3PlusHelper.createStarGraph(p, q);
  }

  if (mergedGraph.vertexCount() > 100 || mergedGraph.edgeCount() > 100) {
   if (mergedGraph.vertexCount() > 100) {
    System.err.printf("P=%1$d, Q=%2$d, |V|=%3$d is larger than 100!\n", p, q, mergedGraph.vertexCount());
   } else {
    System.err.printf("P=%1$d, Q=%2$d, |E|=%3$d is larger than 100!\n", p, q, mergedGraph.edgeCount());
   }
   System.out.println("0 0");
  } else {
   System.out.println(mergedGraph);
  }

  System.err.printf("Program took %1$d msec!\n", (System.currentTimeMillis() - programTime));
 }

 // //////////////////////////////
 static class GraphQ3PlusHelper {

  /**
   * A triangle in the center of the graph. Each vertex has outward a, b, c vertices where:
   * 
   * <pre>
   *  P = a * b * c
   *  |V| = |E| = 3*(Q-1)/2 + a + b + c when Q is odd.
   * </pre>
   * 
   * Find the smallest |V| that satisfies P. If P is a prime number, then the only choice is P = P*1*1. Clearly,
   * the smallest |V| is when a, b, c are close to each other.
   * 
   * <pre>
   * For example, Q=3 & P=8. 
   *  P=2*2*2 -> |V| = 3*(3-1)/2 + 2 + 2 + 2 = 9
   *  P=4*2*1 -> |V| = 3*(3-1)/2 + 4 + 2 + 1 = 10
   *  P=8*1*1 -> |V| = 3*(3-1)/2 + 8 + 1 + 1 = 13
   * 
   * Basically we need to pick P=2*2*2 which yields |V|=9. The graph can be depicted below:
   *       
   *         o   o 
   *          \ /
   *           o
   *          / \
   *     o - o - o - o
   *        /     \
   *       o       o
   * </pre>
   * 
   * @param p
   *            number of lovely triplets.
   * @param q
   *            the min distance required between each pair of lovely triplets. Q > 2 and odd.
   * 
   * @return the graph that satisfies the given P and Q.
   */
  static Graph createTriangleStarGraph(int p, int q) {
   //List<int[]> fiveList = calcFiveList(p, q);
   List<int[]> fiveList = findTrio(p, q);
   int[] totals = calcTotals(fiveList);
   Graph g = new Graph(totals[0], totals[1]);

   // Num of levels from center (including) to leaf nodes (excluding). Q=3 -> 1 and Q=5 -> 2.
   int levelCount = (q - 1) / 2;
   int next = 1;// The first 3 vertices form a triangle at the center.
   for (int[] five : fiveList) {
    printFive(five);

    int base = next;
    // Build levels outwards from center by spinning around.
    for (int i = 0; i < levelCount; i++) {
     for (int j = 0; j < 3; j++) {
      if (i == 0) {
       // Build central triangle.
       int vertexDegree = 2 + (levelCount > 1 ? 1 : five[j]);
       g.ar[next] = new int[vertexDegree];
       g.ar[next][0] = base + (j + 1) % 3;
       g.ar[next][1] = base + (j + 2) % 3;
      } else {
       int vertexDegree = 1 + (i < levelCount - 1 ? 1 : five[j]);
       int parent = next - 3;
       g.ar[next] = new int[vertexDegree];
       g.ar[next][0] = parent; // put parent to adj list.
       g.ar[parent][g.ar[parent].length - 1] = next;
      }
      next++;
     }
    }

    next = buildLeafVertices(g, next, five);
   }

   return g;
  }

  /**
   * A star graph (one vertex at the center). There are three paths outward from the center. The leaf nodes of the
   * three paths will have a, b, c number of vertices where:
   * 
   * <pre>
   *  Q is even (except 2) 
   *  P = a * b * c
   *  |E| = 3*(Q-2)/2 + a + b + c
   *  |V| = |E| + 1
   * </pre>
   * 
   * Find the smallest |E| that satisfies P. If P is a prime number, then the only choice is P = P*1*1. Clearly,
   * the smallest |E| is when a, b, c are close to each other (~ cubic-root of P).
   * 
   * <pre>
   * For example, Q=4 & P=8. 
   *  P=2*2*2 -> |E| = 3*4/2 + 2 + 2 + 2 = 12
   *  P=4*2*1 -> |E| = 3*4/2 + 4 + 2 + 1 = 13
   *  P=8*1*1 -> |E| = 3*4/2 + 8 + 1 + 1 = 16
   * 
   * Basically we need to pick P=2*2*2 which yields |E|=12. The graph can be depicted below:
   *       
   *        o     o 
   *         \   /
   *           o
   *           |
   *           O
   *         /   \
   *    o - o     o - o
   *       /       \
   *      o         o
   * </pre>
   * 
   * @param p
   *            number of lovely triplets.
   * @param q
   *            the min distance required between each pair of lovely triplets. Q > 2 and even.
   * 
   * @return the graph that satisfies the given P and Q.
   */
  static Graph createStarGraph(int p, int q) {
   //List<int[]> fiveList = calcFiveList(p, q);
   List<int[]> fiveList = findTrio(p, q);
   int[] totals = calcTotals(fiveList);
   Graph g = new Graph(totals[0], totals[1]);

   int middleVertexCount = (q - 2) / 2; // Q=4 -> 1 and Q=6 -> 2.
   int next = 1;// The first vertex sits at the center of star. 2, 3 and 4 are around it.
   for (int[] five : fiveList) {
    printFive(five);

    int base = next;
    g.ar[next++] = new int[] { base + 1, base + 2, base + 3 };
    // Build 3 paths outwards from center by spinning around.
    for (int i = 0; i < middleVertexCount; i++) {
     for (int j = 0; j < 3; j++) {
      int vertexDegree = 1 + ((i < middleVertexCount - 1) ? 1 : five[j]);
      g.ar[next] = new int[vertexDegree];
      int parent = ((next - base) > 3 ? (next - 3) : base);
      g.ar[next][0] = parent; // put parent to adj list.
      g.ar[parent][g.ar[parent].length - 1] = next;
      next++;
     }
    }

    next = buildLeafVertices(g, next, five);

   }
   return g;
  }

  private static int buildLeafVertices(Graph g, int next, int[] five) {
   int k = next - 3;
   for (int i = 0; i < 3; i++) {
    for (int m = g.ar[k].length - five[i]; m < g.ar[k].length; m++) {
     g.ar[k][m] = next;
     g.ar[next++] = new int[] { k };
    }
    k++;
   }
   return next;
  }

  private static int[] calcTotals(List<int[]> fiveList) {
   int[] totals = new int[2];
   for (int[] f : fiveList) {
    totals[0] += f[3];
    totals[1] += f[4];
   }
   return totals;
  }

  /**
   * Consider that there can be disconnected graphs satisfying P with smallest number of vertices rather than a
   * single graph.
   * 
   * @param p
   * @param q
   * @return
   */
  static List<int[]> calcFiveList(int p, int q) {
   List<int[]> list = new ArrayList<int[]>();
   int payload = calcPayload(q);
   if (p > payload) {
    List<int[]> subList = calcByCubicRoot(p, q);

    int[] totals = calcTotals(subList);
    int[] alternativeFive = calcByFactors(p, q);

    if (alternativeFive[3] < totals[0]) {
     List<int[]> alternativeList = new ArrayList<int[]>();
     alternativeList.add(alternativeFive);
     return alternativeList;
    } else {
     list.addAll(subList);
    }
   } else {
    int[] anotherFive = calcByFactors(p, q);
    list.add(anotherFive);
   }

   return list;
  }

  static Map<Integer, List<int[]>> memoizationMap = new HashMap<Integer, List<int[]>>();

  // Dynamic programming with memoization!
  static List<int[]> findTrio(int p, int q) {
   if (memoizationMap.containsKey(p)) {
    return memoizationMap.get(p);
   }

   List<int[]> list = new ArrayList<int[]>();
   int[] fiveByFactors = calcByFactors(p, q);
   if (p < 8) {
    list.add(fiveByFactors);
    memoizationMap.put(p, list);
    printFindTrio(p, list);
    return list;
   }

   int cubicRootP = (int) Math.cbrt(p); // clears decimal part.
   int lowerBoundP = cubicRootP * cubicRootP * cubicRootP;
   list.addAll(getCubicFive(cubicRootP, q));

   if (p == lowerBoundP) { // best case.
    memoizationMap.put(p, list);
    printFindTrio(p, list);
    return list;
   }

   List<int[]> subList = findTrio(p - lowerBoundP, q);
   list.addAll(subList);
   int[] totals = calcTotals(list);
   if (fiveByFactors[3] < totals[0]) {
    list.clear();
    list.add(fiveByFactors);
   }

   totals = calcTotals(list);

   List<int[]> minList = list;
   int minVertexCount = totals[0];
   for (int i = lowerBoundP + 1; i < p; i++) {
    List<int[]> subList1 = findTrio(p - i, q);
    List<int[]> subList2 = findTrio(i, q);
    List<int[]> newList = new ArrayList<int[]>(subList1.size() + subList2.size());
    newList.addAll(subList1);
    newList.addAll(subList2);
    int[] newTotals = calcTotals(newList);
    if (newTotals[0] < minVertexCount) {
     minList = newList;
     minVertexCount = newTotals[0];
    }
   }

   memoizationMap.put(p, minList);
   printFindTrio(p, minList);

   return minList;
  }

  private static List<int[]> getCubicFive(int cubicRootP, int q) {
   int p = cubicRootP * cubicRootP * cubicRootP;
   if (memoizationMap.containsKey(p)) {
    return memoizationMap.get(p);
   } else {
    int[] five = new int[] { cubicRootP, cubicRootP, cubicRootP, 0, 0 };
    List<int[]> list = new ArrayList<int[]>();
    list.add(calcVertexAndEdgeCount(q, five));
    memoizationMap.put(p, list);
    printFindTrio(p, list);
    return list;
   }
  }

  private static void printFindTrio(int p, List<int[]> list) {
   System.err.printf("P=%1$d findTrio() results: \n", p);
   for (int[] f : list) {
    printFive(f);
   }
   System.err.println("");
   // System.err.printf("END findTrio(%1$d) results! \n\n", p);
  }

  private static void printFive(int[] f) {
//   System.err.printf("[%1$d * %2$d * %3$d]=%4$d, |V|:%5$d, |E|:%6$d\n", f[0], f[1], f[2],
//     (f[0] * f[1] * f[2]), f[3], f[4]);
  }

  // calc list of trio (a, b, c) and |V| for given p, q pair.
  private static List<int[]> calcByCubicRoot(int p, int q) {
   List<int[]> list = new ArrayList<int[]>();
   int cubicRootP = (int) Math.cbrt(p); // clears decimal part.
   int[] five = { cubicRootP, cubicRootP, cubicRootP, 0, 0 };
   for (int i = 0; i < 3; i++) { // at most 3 iterations as pow((cubicRootP + 1),3) > remainingP always!
    int estimatedP = (five[i] + 1) * five[(i + 1) % 3] * five[(i + 2) % 3];
    if (estimatedP < p) {
     five[i]++;
    }
   }
   calcVertexAndEdgeCount(q, five);
   list.add(five);

   int remainingP = p - five[0] * five[1] * five[2];
   if (remainingP == 0) {
    return list;
   }

   List<int[]> subList = calcFiveList(remainingP, q);
   list.addAll(subList);

   return list;
  }

  static int[] calcVertexAndEdgeCount(int q, int[] five) {
   if (q % 2 == 1) {// Q is odd.
    five[3] = calcPayload(q) + five[0] + five[1] + five[2]; // vertex-count
    five[4] = five[3]; // edge-count
   } else {// Q is even and Q > 2.
    five[3] = calcPayload(q) + five[0] + five[1] + five[2];
    five[4] = five[3] - 1;
   }
   return five;
  }

  /**
   * Calculate the payload of vertex numbers to create a new star graph. This is the number that does not
   * contribute to satisfying P directly.
   */
  static int calcPayload(int q) {
   if (q % 2 == 1) {// Q is odd.
    return (3 * (q - 1) / 2);
   } else {// Q is even and Q > 2.
    return (3 * (q - 2) / 2 + 1);
   }
  }

  // Find all factors of the given P in ascending order. For example, P=60 -> 2, 2, 3, 5
  // Assumes the divisors are sorted in ascending order.
  // returns {A, B, C, |V|, |E|} array where A*B*C=P.
  static int[] calcByFactors(int p, int q) {
   List<Integer> div = new ArrayList<Integer>(); // divisors
   int tmp = p;
   int sqrtP = (int) Math.sqrt(tmp) + 1;
   int i = 2;
   while (i < sqrtP) {
    if (tmp % i == 0) {
     div.add(i);
     tmp = tmp / i;
     sqrtP = (int) Math.sqrt(tmp) + 1;
    } else {
     i++;
    }
   }
   if (tmp > 1) {
    div.add(tmp);
   }

   int size = div.size();
   int[] five = new int[] { 1, 1, 1, 0, 0 };
   int minIndex = 0;
   // Distribute the divisors into 3 buckets starting from the biggest.
   for (int j = size - 1; j >= 0; j--) {
    five[minIndex] *= div.get(j);
    for (int k = 0; k < 3; k++) {
     if (five[k] < five[minIndex]) {
      minIndex = k;
     }
    }
   }

   calcVertexAndEdgeCount(q, five);

   return five;
  }
 }

 // //////////////////////////////
 static class GraphQ2Helper {
  static final int P3_ID = 0;

  // Oth index is just for edge count. Vertex index starts with 1.
  static final Graph P3_GRAPH = new Graph(new int[][] { { 7 }, { 6, 2 }, { 1, 3 }, { 2, 4 }, { 3, 5 }, { 4, 6 },
    { 7, 5, 1 }, { 6 } });

  /**
   * Combination(N, 3) where 3 <= N <= 32.
   * 
   * N*(N-1)*(N-2)/3*2 => N*(N-1)*(N-2)/6.
   */
  static final int[] N_CHOOSE_3 = fill_nChoose3();

  /**
   * K(1,n) star-type of graphs have C(n,3) lovely triplets of distance-2.
   * 
   * Use only indices between 3 and 32 because C(32,3) = 4960, which is the greatest number of lovely triplets
   * that can be obtained with a star graph below 5000.
   */
  static int[] fill_nChoose3() {
   int[] nChoose3 = new int[33];
   for (int i = 3; i <= 32; i++) {
    nChoose3[i] = i * (i - 1) * (i - 2) / (3 * 2 * 1);
   }
   return nChoose3;
  }

  /**
   * Use binary search on N_CHOOSE_3 array.
   * 
   * P3 is the special case. It has a special hexagon shape with extra edge (not a star graph). |V|=7 & |E|=7. We
   * would choose P3 = P1 + P1 + P1 but this merged graph would require 1 extra vertex.
   * 
   * @param p
   *            1 <= P <= 5000
   * 
   * @return list of star graph ids to generate exactly P num of lovely triplets.
   */
  static List<Graph> findGraphs(int p) {
   List<Graph> list = new ArrayList<Graph>();
   findGraphs(p, 3, N_CHOOSE_3.length - 1, list);
   return list;
  }

  static void findGraphs(int p, int begin, int end, List<Graph> list) {
   if (p == 3) {
    list.add(P3_GRAPH);
    System.err.println("P(3) = " + P3_GRAPH);
    return;
   }
   int low = begin;
   int high = end;
   int mid = high;
   while (low < high) {
    mid = (low + 1 + high) / 2;
    if (p == N_CHOOSE_3[mid]) {
     list.add(createStarGraph(mid));
     System.err.println("C(" + mid + ",3) = " + N_CHOOSE_3[mid]);
     System.err.println(list.get(list.size() - 1));
     return;
    } else if (p > N_CHOOSE_3[mid]) {
     if (mid == high || (mid < high && p < N_CHOOSE_3[mid + 1])) {
      break;
     } else {
      low = mid + 1;
     }
    } else { // p < nChoose3[mid]
     high = (mid < high) ? mid : mid - 1;
     if (low == high) {
      mid = low;
     }
    }
   }
   list.add(createStarGraph(mid));
   System.err.println("C(" + mid + ",3) = " + N_CHOOSE_3[mid]);
   System.err.println(list.get(list.size() - 1));
   int remaining_p = p - N_CHOOSE_3[mid];
   if (remaining_p > 0) {
    // find a suitable graph for the remaining part recursively.
    findGraphs(remaining_p, begin, mid, list);
   }
  }

  /**
   * Create a K(1,n) star-type of graphs that have C(n,3) lovely triplets of distance-2.
   * 
   * P = C(n,3) and Q = 2. |V| = n + 1. |E| = n.
   */
  static Graph createStarGraph(int n) {
   if (n == P3_ID) {
    return P3_GRAPH;
   }

   Graph g = new Graph(n + 1, n);
   g.ar[1] = new int[n]; // Choose 1st vertex as the center of star with n edges.
   for (int i = 0; i < n; i++) {
    g.ar[1][i] = i + 2;
    g.ar[i + 2] = new int[] { 1 };
   }
   return g;
  }
 }

 // //////////////////////////////
 static class Graph {
  int[][] ar; // adjancency list of the graph.

  Graph(int[][] ar) {
   this.ar = ar;
  }

  Graph(int vertexCount, int edgeCount) {
   // Recall 0-to-1 index adjustment. +1 is for it.
   this.ar = new int[vertexCount + 1][];
   this.setEdgeCount(edgeCount);
  }

  int vertexCount() {
   return ar.length - 1;
  }

  int edgeCount() {
   return ar[0][0];
  }

  void setEdgeCount(int edgeCount) {
   if (ar[0] == null) {
    ar[0] = new int[1];
   }
   ar[0][0] = edgeCount;
  }

  @Override
  public String toString() {
   StringBuilder sb = new StringBuilder();
   sb.append(ar.length - 1).append(' ').append(ar[0][0]).append('\n');
   for (int i = 1; i < ar.length; i++) {
    Arrays.sort(ar[i]);
    for (int j = 0; j < ar[i].length; j++) {
     if (i < ar[i][j]) {
      sb.append(i).append(' ').append(ar[i][j]).append('\n');
     }
    }
   }
   return sb.toString();
  }

  /**
   * Merges the given two graphs g1 and g2 via edge overlapping of E1 and E2 edges where those edges are defined
   * with v1,u1 and v2,u2 vertex pairs. One of those vertices has the smallest degree. Assume that g1 and g2 are
   * non-null and connected graphs.
   * 
   * Merged graph will have the following properties:
   * 
   * <pre>
   * |merged-E| = |E1| + |E2| - 1
   * |merged-V| = |V1| + |V2| - 2
   * P(merged-G) = P(g1) + P(g2)
   * </pre>
   * 
   * @param graph2
   *            another graph that will be merged with this graph.
   * @return a new instance of graph that has merged vertices and edges.
   */
  Graph merge(Graph graph2) {
   int mergedVertexCount = this.vertexCount() + graph2.vertexCount() - 2;
   int mergedEdgeCount = this.edgeCount() + graph2.edgeCount() - 1;
   Graph mergedG = new Graph(mergedVertexCount, mergedEdgeCount);
   this.copyTo(mergedG);

   int[] v1 = this.findMergingVertex();
   int[] v2 = graph2.findMergingVertex();

   graph2.startMappedIndexing(this.ar.length);

   int u2 = graph2.ar[v2[0]][0]; // the other vertex of the merging edge from G2.
   // Add G2 into mergedG, except v2 & u2 vertices.
   for (int i = 1; i < graph2.ar.length; i++) {
    if (i == v2[0]) { // ignore v2.
     // FIXME If degree(v2) > 1, it won't work as v2 will have another edge not just E(v2,u2).
     continue;
    }

    if (i == u2) {
     int u2Degree = graph2.ar[u2].length;
     int mergedV1Degree = v1[1] + u2Degree - 1; // -1 to skip E(v2,u2) edge in the merging graph.
     int[] mergedV1Edges = new int[mergedV1Degree];
     int mergedCount = this.ar[v1[0]].length;
     System.arraycopy(this.ar[v1[0]], 0, mergedV1Edges, 0, mergedCount);
     for (int j = 0; j < graph2.ar[u2].length; j++) {
      if (graph2.ar[u2][j] == v2[0]) { // Skip E(v2,u2) edge in the merging graph.
       continue;
      }
      mergedV1Edges[mergedCount++] = graph2.mapped(graph2.ar[u2][j]);
     }
     mergedG.ar[v1[0]] = mergedV1Edges; // Assign new edge array to v1 position in the merged-graph.
    } else {
     int mapped_i = graph2.mapped(i);
     mergedG.ar[mapped_i] = new int[graph2.ar[i].length];
     for (int j = 0; j < graph2.ar[i].length; j++) {
      int mapped_x;
      if (graph2.ar[i][j] == u2) {
       mapped_x = v1[0];
      } else {
       mapped_x = graph2.mapped(graph2.ar[i][j]);
      }
      mergedG.ar[mapped_i][j] = mapped_x;
     }
    }
   }

   return mergedG;
  }

  static Graph mergeGraphs(List<Graph> list) {
   Graph mergedGraph = null;
   for (Graph g : list) {
    if (mergedGraph == null) {
     mergedGraph = g;
    } else {
     if (g.edgeCount() > mergedGraph.edgeCount()) {
      mergedGraph = g.merge(mergedGraph);
     } else {
      mergedGraph = mergedGraph.merge(g);
     }
    }
   }
   return mergedGraph;
  }

  // Copy G1(this) directly into mergedG.
  private void copyTo(Graph mergedG) {
   for (int i = 1; i < this.ar.length; i++) {
    // Object reference assignment since an int array is an object, too!
    mergedG.ar[i] = this.ar[i];
   }
  }

  private int[] findMergingVertex() {
   int[] v = { 0, Integer.MAX_VALUE }; // vertex-id, vertex-degree
   // Find a candidate edge in graph with the smallest vertex-degree, possibly 1 for merging.
   for (int i = ar.length - 1; i >= 1; i--) {
    if (v[1] > ar[i].length) {
     v[1] = ar[i].length;
     v[0] = i;
     if (v[1] == 1) {
      break;
     }
    }
   }
   return v;
  }

  private int nextAvailVertexId = 0;
  private Map<Integer, Integer> mapper = null;

  private void startMappedIndexing(int startIndex) {
   nextAvailVertexId = startIndex; // The next available index of mergedG.
   mapper = new HashMap<Integer, Integer>((int) (this.ar.length / 0.75f));
  }

  private int mapped(int x) {
   int m_x;
   if (mapper.containsKey(x)) {
    m_x = mapper.get(x);
   } else {
    m_x = nextAvailVertexId++;
    mapper.put(x, m_x);
   }
   return m_x;
  }
 }

}