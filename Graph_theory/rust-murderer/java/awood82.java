import java.io.*;
import java.util.*;

public class Solution {
    
    public static void main(String[] args) throws IOException {
        long totalTime = 0;
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String[] ints;
        
        // The first line contains T, denoting the number of test cases. T testcases follow. 
        int T = Integer.parseInt(bi.readLine());
        for (int t = 0; t < T; t++) {
            // First line of each test case has two integers N, denoting the number of cities in the map and M, denoting the number of roads in the map.
            line = bi.readLine();
            ints = line.split("\\s");
            int N = Integer.parseInt(ints[0]); // # of cities
            int M = Integer.parseInt(ints[1]); // # of roads
            
            // The next M lines each consist of two space-separated integers x and y denoting a main road between city x and city y.
            int[] x = new int[M];
            int[] y = new int[M];
            for (int m = 0; m < M; m++) {
                line = bi.readLine();
                ints = line.split("\\s");
                x[m] = Integer.parseInt(ints[0]) - 1; // Vertex 1; make 0-based
                y[m] = Integer.parseInt(ints[1]) - 1; // Vertex 2; make 0-based
            }
            
            // The last line has an integer S, denoting the current position of Rust.
            int S = Integer.parseInt(bi.readLine()) - 1; // make 0-based
            
            // Finally... solve!
            int[] distances = findShortestPaths(N, M, x, y, S);
            
            StringBuilder sb = new StringBuilder();
            for (int distance : distances) {
                if (distance != 0) {
                    sb.append(distance);
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString());
        }
    }
    
    public static int[] findShortestPaths(final int numCities, final int numRoads, final int x[], final int y[], final int S) {
        // Build up the lists of edges
        List<Set<Integer>> edges = new ArrayList<Set<Integer>>(numRoads);
        for (int n = 0; n < numCities; n++) {
            edges.add(new HashSet<Integer>());
        }
        // These are bi-directional roads
        for (int m = 0; m < numRoads; m++) {
            edges.get(x[m]).add(y[m]);
            edges.get(y[m]).add(x[m]);
        }
        
        int[] distances = new int[numCities]; // return value
        // The graph is sparse, so there will be way fewer undiscovered
        // cities than discovered ones
        Set<Integer> undiscovered = new HashSet<Integer>();
        for (int n = 0; n < numCities; n++) {
            undiscovered.add(n);
        }
        List<Integer> q = new LinkedList<Integer>();
        q.add(S);
        undiscovered.remove(S);
        int discoveredCount = 1;
        while (!q.isEmpty() && discoveredCount < numCities) {
            int v = q.remove(0);
            // See which cities are reachable from this node
            for (int n = 0; n < numCities; n++) {
                if (undiscovered.contains(n)) {
                    if (!edges.get(v).contains(n)) {
                        // There's no road, so there is a side lane. Add it!
                        //   "Where we're going, we don't need roads."
                        //                                 - Doc Brown
                        undiscovered.remove(n);
                        distances[n] = distances[v] + 1;
                        discoveredCount++;
                        q.add(n);
                    }
                }
            }
        }
        
        return distances;
    }
}