import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {

 public static class Node {
  private int id;
  private int types;
  private Map<Integer, Integer> options;

  public Node(int id, int types) {
   this.id = id;
   this.types = types;
   this.options = new HashMap<Integer, Integer>();
  }
  
  public boolean addOption(int types, int cost) {
   boolean added = false;
   Map<Integer, Integer> newOptions = new HashMap<Integer, Integer>();
   for (int optionTypes : options.keySet()) {
    newOptions.put(optionTypes | types, options.get(optionTypes) + cost);
   }
   for (int newOptionTypes : newOptions.keySet()) {
    int newOptionCost = newOptions.get(newOptionTypes);
    Integer optionCost = options.get(newOptionTypes);
    if (optionCost != null && newOptionCost < optionCost || optionCost == null) {
     options.put(newOptionTypes, newOptionCost);
     added = true;
    }
   }
   //System.out.println(options);
   Integer defaultCost = options.get(this.types | types);
   if (defaultCost == null || defaultCost > cost) {
    options.put(this.types | types, cost);
    added = true;
   }
   return added;
  }
  
  public String toString() {
   return "(" + id + ", " + types + ", " + options + ")";
  }
 }


 private static void traverse(Map<Integer, Node> graph, Map<Integer, Map<Integer, Integer>> relations, int n, int k, int from) {
  /*if (from == n) {
   return;
  }*/
  Node fromNode = graph.get(from);
  Map<Integer, Integer> tos = relations.get(from);
  for (int to : tos.keySet()) {
   Node toNode = graph.get(to);
   boolean added = false;
   for (int fromOptionTypes : fromNode.options.keySet()) {
    int fromOptionCost = fromNode.options.get(fromOptionTypes);
    int fromToCost = relations.get(from).get(to);
    added |= toNode.addOption(fromOptionTypes, fromOptionCost + fromToCost);
   }
   if (added) {
    traverse(graph, relations, n, k, to);
   }
  }
 }

 public static int solve(Map<Integer, Node> graph, Map<Integer, Map<Integer, Integer>> relations, int n, int k) {
  Node first = graph.get(1);
  first.options.put(first.types, 0);
  traverse(graph, relations, n, k, 1);
  int minCost = Integer.MAX_VALUE;
  Node node = graph.get(n);
  //System.out.println(node);
  for (int t1 : node.options.keySet()) {
   int c1 = node.options.get(t1);
   int mask = (1 << k) - 1;
   if (t1 == mask) {
    minCost = Math.min(minCost, c1);
   }
   for (int t2 : node.options.keySet()) {
    if (t1 == t2) {
     continue;
    }
    int c2 = node.options.get(t2);
    if ((t1 | t2) == mask) {
     //System.out.println((t1 | t2) + ", c1: " + c1 + ", c2: " + c2);
     minCost = Math.min(minCost, Math.max(c1, c2));
    }
   }
  }
  return minCost;
 }
 
 public static void main(String[] args) throws IOException {
  InputReader in = new InputReader();
  try {
   int n = in.nextInt();
   int m = in.nextInt();
   int k = in.nextInt();
   Map<Integer, Node> graph = new HashMap<Integer, Node>();
   for (int i = 1; i <= n; i++) {
    int numberOfTypes = in.nextInt();
    int types = 0;
    while (numberOfTypes-- > 0) {
     int type = in.nextInt();
     types |= (1 << (type - 1));
    }
    graph.put(i, new Node(i, types));
   }
   Map<Integer, Map<Integer, Integer>> relations = new HashMap<Integer, Map<Integer, Integer>>();
   for (int i = 0; i < m; i++) {
    int from = in.nextInt();
    int to = in.nextInt();
    int cost = in.nextInt();
    Map<Integer, Integer> tos = relations.get(from);
    if (tos == null) {
     tos = new HashMap<Integer, Integer>();
    }
    tos.put(to, cost);
    relations.put(from, tos);
    Map<Integer, Integer> froms = relations.get(to);
    if (froms == null) {
     froms = new HashMap<Integer, Integer>();
    }
    froms.put(from, cost);
    relations.put(to, froms);
   }
   System.out.println(solve(graph, relations, n, k));
  } finally {
   in.close();
  }
 }

 public static class InputReader {
  private BufferedReader in;
  private String[] input = new String[0];
  private int index = 0;

  public InputReader() {
   in = new BufferedReader(new InputStreamReader(System.in));
  }

  public String next() throws IOException {
   if (index >= input.length) {
    input = in.readLine().split("\\s");
    index = 0;
   }
   return input[index++];
  }

  public int nextInt() throws NumberFormatException, IOException {
   return Integer.valueOf(next());
  }

  public long nextLong() throws NumberFormatException, IOException {
   return Long.valueOf(next());
  }

  public int[] nextIntArray(int n) throws NumberFormatException, IOException {
   int[] array = new int[n];
   for (int i = 0; i < n; i++) {
    array[i] = nextInt();
   }
   return array;
  }

  public long[] nextLongArray(int n) throws NumberFormatException, IOException {
   long[] array = new long[n];
   for (int i = 0; i < n; i++) {
    array[i] = nextLong();
   }
   return array;
  }

  public int[][] nextIntMatrix(int m, int n) throws NumberFormatException, IOException {
   int[][] matrix = new int[m][n];
   for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
     matrix[i][j] = nextInt();
    }
   }
   return matrix;
  }

  public void close() throws IOException {
   in.close();
  }
 }

 public static class ModUtils {
  private int mod;

  public ModUtils(int mod) {
   this.mod = mod;
  }

  public long add(long x, long y) {
   return (x + y) % mod;
  }
 }

}