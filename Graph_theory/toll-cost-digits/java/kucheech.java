import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Map.Entry;

public class Solution<T> {

      private final GraphFindAllPaths<T> graph;

    /**
     * Takes in a graph. This graph should not be changed by the client
     */
    public Solution(GraphFindAllPaths<T> graph) {
        if (graph == null) {
            throw new NullPointerException("The input graph cannot be null.");
        }
        this.graph = graph;
    }

    private void validate(T source, T destination) {

        if (source == null) {
            throw new NullPointerException("The source: " + source + " cannot be  null.");
        }
        if (destination == null) {
            throw new NullPointerException("The destination: " + destination + " cannot be  null.");
        }
        if (source.equals(destination)) {
            throw new IllegalArgumentException("The source and destination: " + source + " cannot be the same.");
        }
    }

    /**
     * Returns the list of paths, where path itself is a list of nodes.
     *
     * @param source the source node
     * @param destination the destination node
     * @return List of all paths
     */
    public List<List<T>> getAllPaths(T source, T destination) {
        validate(source, destination);
        Map<List<T>, Integer> pathWithCost = new HashMap<List<T>, Integer>();

        List<List<T>> paths = new ArrayList<List<T>>();
        List<Integer> totalCost = new ArrayList<Integer>();
        Integer cost = new Integer(0);
        recursive(source, destination, paths, new LinkedHashSet<T>(), totalCost, cost, new HashMap<T, Integer>());
        for (int i = 0; i < paths.size(); i++) {
            pathWithCost.put(paths.get(i), totalCost.get(i));
        }
        return paths;
    }

    // so far this dude ignore's cycles.
    private void recursive(T current, T destination, List<List<T>> paths, LinkedHashSet<T> path, List<Integer> totalCost, Integer cost, Map<T, Integer> allEdges) {
        path.add(current);
        if (allEdges.get(current) != null) {
            cost = cost + allEdges.get(current);
        }
        if (current == destination) {
            cost = cost + allEdges.get(current);
            paths.add(new ArrayList<T>(path));

            cost = cost - allEdges.get(current);
            totalCost.add(cost);
            path.remove(current);
            return;
        }

        allEdges = graph.edgesFrom(current);

        final Set<T> edges = graph.edgesFrom(current).keySet();

        for (T t : edges) {
            if (!path.contains(t)) {
                //System.out.println(t);
                recursive(t, destination, paths, path, totalCost, cost, allEdges);
            }
        }

        path.remove(current);
    }

    /**
     * Returns the list of paths, where path itself is a list of nodes.
     *
     * @param source the source node
     * @param destination the destination node
     * @return List of all paths
     */
    public Map<List<T>, Integer> getAllPathsWithCost(T source, T destination) {
        validate(source, destination);
        Map<List<T>, Integer> pathWithCost = new HashMap<List<T>, Integer>();

        List<List<T>> paths = new ArrayList<List<T>>();
        List<Integer> totalCost = new ArrayList<Integer>();
        Integer cost = new Integer(0);
        recursiveWithCost(source, destination, paths, new LinkedHashSet<T>(), totalCost, cost, new HashMap<T, Integer>());
        for (int i = 0; i < paths.size(); i++) {
            pathWithCost.put(paths.get(i), totalCost.get(i));
        }
        return pathWithCost;
    }

    // so far this dude ignore's cycles.
    private void recursiveWithCost(T current, T destination, List<List<T>> paths, LinkedHashSet<T> path, List<Integer> totalCost, Integer cost, Map<T, Integer> allEdges) {
        path.add(current);
        if (allEdges.get(current) != null) {
            cost = cost + allEdges.get(current);
        }
        if (current == destination) {
            cost = cost + allEdges.get(current);
            paths.add(new ArrayList<T>(path));

            cost = cost - allEdges.get(current);
            totalCost.add(cost);
            path.remove(current);
            return;
        }

        allEdges = graph.edgesFrom(current);

        final Set<T> edges = graph.edgesFrom(current).keySet();

        for (T t : edges) {
            if (!path.contains(t)) {
                //System.out.println(t);
                recursiveWithCost(t, destination, paths, path, totalCost, cost, allEdges);
            }
        }

        path.remove(current);
    }

  
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
      
        GraphFindAllPaths<Integer> graphFindAllPaths = new GraphFindAllPaths<Integer>();
        for (int i = 1; i <= n; i++) {
            graphFindAllPaths.addNode(i);
        }                
      
        int e = in.nextInt();
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            graphFindAllPaths.addEdge(x, y, r);
            graphFindAllPaths.addEdge(y, x, 1000-r);
        }
      
        Solution<Integer> findAllPaths = new Solution<Integer>(graphFindAllPaths);
        int[] d = new int[10];
      
        for(int i = 1; i <= n; i++) {
          for(int j = 1; j <= n; j++) {
            if(i != j) {
              Map<List<Integer>, Integer> pathWithCost = findAllPaths.getAllPathsWithCost(i, j);        
              for (Entry<List<Integer>, Integer> s : pathWithCost.entrySet()) {
                d[s.getValue() % 10]++;
              }  
            }                
          }                   
        }
      
      for(int t : d) {
            System.out.println(t);
      }
        
    }
}

class GraphFindAllPaths<T> implements Iterable<T> {

    /* A map from nodes in the graph to sets of outgoing edges.  Each
     * set of edges is represented by a map from edges to doubles.
     */
    public final Map<T, Map<T, Integer>> graph = new HashMap<T, Map<T, Integer>>();

    /**
     * Adds a new node to the graph. If the node already exists then its a
     * no-op.
     *
     * @param node Adds to a graph. If node is null then this is a no-op.
     * @return true if node is added, false otherwise.
     */
    public boolean addNode(T node) {
        if (node == null) {
            throw new NullPointerException("The input node cannot be null.");
        }
        if (graph.containsKey(node)) {
            return false;
        }

        graph.put(node, new HashMap<T, Integer>());
        return true;
    }
  
     public int getNumNodes() {
        return graph.size();
    }

    /**
     * Given the source and destination node it would add an arc from source to
     * destination node. If an arc already exists then the value would be
     * updated the new value.
     *
     * @param source the source node.
     * @param destination the destination node.
     * @param length if length if
     * @throws NullPointerException if source or destination is null.
     * @throws NoSuchElementException if either source of destination does not
     * exists.
     */
    public void addEdge(T source, T destination, int length) {
        if (source == null || destination == null) {
            throw new NullPointerException("Source and Destination, both should be non-null.");
        }
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            throw new NoSuchElementException("Source and Destination, both should be part of graph");
        }
        /* A node would always be added so no point returning true or false */
        graph.get(source).put(destination, length);

    }

    /**
     * Removes an edge from the graph.
     *
     * @param source If the source node.
     * @param destination If the destination node.
     * @throws NullPointerException if either source or destination specified is
     * null
     * @throws NoSuchElementException if graph does not contain either source or
     * destination
     */
    public void removeEdge(T source, T destination) {
        if (source == null || destination == null) {
            throw new NullPointerException("Source and Destination, both should be non-null.");
        }
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            throw new NoSuchElementException("Source and Destination, both should be part of graph");
        }
        graph.get(source).remove(destination);
    }

    /**
     * Given a node, returns the edges going outward that node, as an immutable
     * map.
     *
     * @param node The node whose edges should be queried.
     * @return An immutable view of the edges leaving that node.
     * @throws NullPointerException If input node is null.
     * @throws NoSuchElementException If node is not in graph.
     */
    public Map<T, Integer> edgesFrom(T node) {
        if (node == null) {
            throw new NullPointerException("The node should not be null.");
        }
        Map<T, Integer> edges = graph.get(node);
        if (edges == null) {
            throw new NoSuchElementException("Source node does not exist.");
        }
        return Collections.unmodifiableMap(edges);
    }

    /**
     * Returns the iterator that travels the nodes of a graph.
     *
     * @return an iterator that travels the nodes of a graph.
     */
    @Override
    public Iterator<T> iterator() {
        //System.out.println(graph.keySet().iterator());
        return graph.keySet().iterator();
    }
}