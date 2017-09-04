import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static int[] findDistances(HashMap<Integer,HashSet<Integer>> graph, int curr_pos) {
        int[] distances = new int[graph.size()];
        Deque<Integer> queue = new ArrayDeque<Integer>();
        Deque<Integer> unvisited = new ArrayDeque<Integer>();
        for (int x = 0; x < graph.size(); x++) {
            if (x != curr_pos) { unvisited.add(x); }
        }
        queue.add(curr_pos);
        int current_distance = 1;
        while (!queue.isEmpty() && unvisited.size() > 0) {
            int queue_length = queue.size();
            while (queue_length > 0) {
            int front = queue.removeFirst();
            int rnd = unvisited.size();
            while (rnd > 0) {
                int x = unvisited.removeFirst();
                if (!graph.get(front).contains(x)) {
                    queue.addLast(x);
                    distances[x] = current_distance;
                }
                else {
                    unvisited.add(x);
                }
                rnd--;
            }
              queue_length--;
          }
            current_distance++;
       }
       return distances;
    }

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int test_ct = Integer.parseInt(br.readLine());
       String[] toPrint = new String[test_ct];
       while (test_ct > 0) {
           String[] cts = br.readLine().split(" ");
           int node_ct = Integer.parseInt(cts[0]);
           int road_ct = Integer.parseInt(cts[1]);
           HashMap<Integer,HashSet<Integer>> compGraph = new HashMap<Integer,HashSet<Integer>>();
           for (int x = 0; x < node_ct; x++) {
               compGraph.put(x,new HashSet<Integer>());
           }
           while (road_ct > 0) {
               String[] tuple = br.readLine().split(" ");
               int src = Integer.parseInt(tuple[0])-1;
               int snk = Integer.parseInt(tuple[1])-1;
               compGraph.get(src).add(snk);
               compGraph.get(snk).add(src);
               road_ct--;
           }
           int current_pos = Integer.parseInt(br.readLine())-1;
           int[] distances = findDistances(compGraph,current_pos);
           StringBuilder s = new StringBuilder();
           for (int y = 0; y < distances.length; y++) {
               if (y != current_pos) {
                 s.append(distances[y] + " ");
               }
           }
           toPrint[toPrint.length-test_ct] = s.toString();
           test_ct--;
       }
       // print out results
       for (int x = 0; x < toPrint.length; x++) {
           System.out.println(toPrint[x]);
       }
    }
}