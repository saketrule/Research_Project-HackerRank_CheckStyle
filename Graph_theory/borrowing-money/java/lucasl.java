import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 static class MyDirectedGraph<T> {
  private Map<T, Set<T>> nodeToNeighbors;

  public MyDirectedGraph() {
   nodeToNeighbors = new HashMap<T, Set<T>>();
  }

  /**
   * Add a new node to the graph.
   * 
   * @param node
   *            the new node to add to the graph
   */
  public void addNode(T node) {
   if (!nodeToNeighbors.containsKey(node)) {
    nodeToNeighbors.put(node, new HashSet<T>());
   }
  }

  /**
   * Add an unidirectional edge from a given start node to a destination
   * node.
   * 
   * @param start
   *            the node with outgoing edge
   * @param dest
   *            the node with the incoming edge
   * 
   * @throws NoSuchElementException
   *             if one or both nodes don't exist
   */
  public void addEdge(T start, T dest) {
   if (!nodeToNeighbors.containsKey(start) || !nodeToNeighbors.containsKey(dest)) {
    throw new NoSuchElementException("Both nodes must be contained in the graph!");
   } else {
    nodeToNeighbors.get(start).add(dest);
   }
  }

  /**
   * Remove an edge from the graph.
   * 
   * @param start
   *            the node with outgoing edge
   * @param dest
   *            the node with the incoming edge
   * @throws NoSuchElementException
   *             if one or both nodes don't exist
   */
  public void removeEdge(T start, T dest) {
   if (!nodeToNeighbors.containsKey(start) || !nodeToNeighbors.containsKey(dest)) {
    throw new NoSuchElementException("Both nodes must be contained in the graph!");
   } else {
    nodeToNeighbors.get(start).remove(dest);
   }
  }

  /**
   * Get all the adjacent nodes of the given node.
   * 
   * @param node
   *            the node to retrieve the neighbors from
   * @return Set of neighbors
   * @throws NoSuchElementException
   *             if node doesn't exist
   */
  public Set<T> getNeighbors(T node) {
   if (!nodeToNeighbors.containsKey(node)) {
    throw new NoSuchElementException("Node doesn't exist!");
   } else {
    return nodeToNeighbors.get(node);
   }
  }

  public boolean containsNode(T node) {
   return nodeToNeighbors.containsKey(node);
  }

  /**
   * Given two nodes in the graph, returns whether there is an edge from
   * the first node to the second node. If either node does not exist in
   * the graph, throws a NoSuchElementException.
   * 
   * @param start
   *            The start node.
   * @param end
   *            The destination node.
   * @return Whether there is an edge from start to end.
   * @throws NoSuchElementException
   *             If either endpoint does not exist.
   */
  public boolean edgeExists(T start, T dest) {
   /* Confirm both endpoints exist. */
   if (!nodeToNeighbors.containsKey(start) || !nodeToNeighbors.containsKey(dest))
    throw new NoSuchElementException("Both nodes must be in the graph.");

   return nodeToNeighbors.get(start).contains(dest);
  }
 }

 private static int intersection(Set<Integer> a, Set<Integer> b) {
  Set<Integer> c = new HashSet<Integer>(a.size() > b.size() ? a.size() : b.size());
  c.addAll(a);
  c.retainAll(b);

  return c.size();
 }

 private static int sum;
 private static Set<Set<Integer>> sets;
 private static Set<Long> dp;

 private static long createKey(Set<Integer> set) {
  long key = 0;
  for (Integer i:set) {
   key += (2<<i);
  }
  
  return key;
 }
 
 private static boolean solve(MyDirectedGraph<Integer> city, int[] c, int n, Set<Integer> visited, Set<Integer> blacklist, int current, int currentSum) {
  dp.add(createKey(visited));
  
  //System.out.println(dp);
  for (int i = current; i <= n; i++) {

   if (!visited.contains(i) && !blacklist.contains(i) && city.containsNode(i) && intersection(visited, city.getNeighbors(i)) == 0) {
    visited.add(i);
    blacklist.addAll(city.getNeighbors(i));
    blacklist.add(i);

    boolean reachedMax = false;
    Long key = createKey(visited);
    if (!dp.contains(key)) {
     reachedMax = solve(city, c, n, visited, blacklist, i, (currentSum + c[i]));
    }
    visited.remove(i);
    blacklist.removeAll(city.getNeighbors(i));
    blacklist.remove(i);
   }

   if (currentSum == sum) {
    Set<Integer> tmp = new HashSet<Integer>();
    tmp.addAll(visited);
    sets.add(tmp);
   }

  }

  if (blacklist.size() == n) {
   if (currentSum > sum) {
    sum = currentSum;
    sets.clear();
    Set<Integer> tmp = new HashSet<Integer>();
    tmp.addAll(visited);
    sets.add(tmp);
    return true;
   } else if (currentSum == sum) {
    Set<Integer> tmp = new HashSet<Integer>();
    tmp.addAll(visited);
    sets.add(tmp);
    return true;
   }

   //System.out.println(visited+" sum="+currentSum);
   visited.remove(current);
   blacklist.removeAll(city.getNeighbors(current));
   blacklist.remove(current);
   
  }
  return false;

 }

 public static void main(String[] args) throws FileNotFoundException {
  //System.setIn(new FileInputStream(System.getProperty("user.home") + "/" + "in.txt"));
  Scanner scanner = new Scanner(System.in);

  int n = scanner.nextInt(); // number of houses
  int m = scanner.nextInt(); // number of roads
  int[] c = new int[n + 1]; // money
  MyDirectedGraph<Integer> city = new MyDirectedGraph<Integer>();
  for (int i = 1; i <= n; i++) {
   c[i] = scanner.nextInt();
   city.addNode(i);
  }

  for (int i = 0; i < m; i++) {
   int a = scanner.nextInt();
   int b = scanner.nextInt();
   city.addEdge(a, b);
   city.addEdge(b, a);
  }

  dp = new HashSet<Long>();
  sets = new HashSet<Set<Integer>>();
  for (int i = 1; i <= n; i++) {
   solve(city, c, n, new HashSet<Integer>(), new HashSet<Integer>(), i, 0);
  }

  System.out.print(sum + " " + sets.size());

  //  System.out.println();
  //System.out.println(sets);
 }
}