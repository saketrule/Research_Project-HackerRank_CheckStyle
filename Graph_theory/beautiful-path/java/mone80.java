import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in  = new Scanner(System.in);
        int numNode = in.nextInt();
        Map<Integer, List<Edge>> graph = createGraphFromCMDLine(in);
        int source = in.nextInt();
        int destination = in.nextInt();
        //System.out.println(graph);
         MinimumPenaltyPath mpp = new MinimumPenaltyPath(graph, numNode, source, destination);
        //System.out.printf("%d has path to source %d: " + mpp.hasPathTo(distination), source, distination);
        //System.out.printf("%d has MPP path to source %d: " + mpp.getMPPTo(distination), source, distination);
         //System.out.println();
        System.out.println(mpp.getMPPTo(destination));
    }
    
    private static Map<Integer, List<Edge>> createGraphFromCMDLine(Scanner in) {
        Map<Integer, List<Edge>> graph = new HashMap<Integer, List<Edge>>();
        int numEdges = in.nextInt();
        for (int i = 0; i < numEdges; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int weight = in.nextInt();
            List<Edge> edges;
            if (!graph.containsKey(from)){
                edges = new ArrayList<Edge>();
                graph.put(from, edges);
            }
            edges = graph.get(from);
            edges.add(new Edge(from, to, weight));
            
             if (!graph.containsKey(to)){
                edges = new ArrayList<Edge>();
                graph.put(to, edges);
            }

            edges = graph.get(to);
            edges.add(new Edge(to, from, weight));
        }
        return graph;  
    }
    private static class Edge {
        int from;
        int to;
        int weight;
        
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        public String toString() {
            return from + "->" + to + " E:" + weight;
        }
    }
    
    private static class MinimumPenaltyPath {
        private Map<Integer, DistanceToSource> minPenaltyToSource;

        public  MinimumPenaltyPath(final Map<Integer, List<Edge>> graph, int maxNode, int source, int destination ){

            minPenaltyToSource = new  HashMap<Integer, DistanceToSource>();
            if(!graph.containsKey(source) ||
                    !graph.containsKey(destination) ||
                    (source > maxNode || source < 1)  ||
                    (destination > maxNode || destination < 1) )
                       return;

            DistanceToSource disToSrc = new DistanceToSource();
            disToSrc.disTo = 0;
            disToSrc.vertex = source;
            disToSrc.visited = true;
            minPenaltyToSource.put(source, disToSrc);

            computeMPP(graph, source , 0 , destination);
        }


        private void computeMPP(Map<Integer, List<Edge>> graph, int source, int mppValue, int destination) {

            if (minPenaltyToSource.containsKey(source) &&
                    minPenaltyToSource.get(source).mpValue.contains(mppValue)){
                DistanceToSource disToSrc = minPenaltyToSource.get(source);
                disToSrc.disTo = Math.min(disToSrc.disTo, mppValue);
                minPenaltyToSource.get(source).visited = false;
                /*if (source == destination) {
                    System.out.println("source:" +  source );
                    System.out.println("disToSrc.disTo :" +  disToSrc.disTo );
                    System.out.println("value:" + mppValue);
                }*/
                return;

            } else if (minPenaltyToSource.containsKey(source) && destination == source){
                DistanceToSource disToSrc = minPenaltyToSource.get(source);
                disToSrc.disTo = Math.min(disToSrc.disTo, mppValue);
                minPenaltyToSource.get(source).visited = false;
                return;

            }

            minPenaltyToSource.get(source).mpValue.add(mppValue);

            for (Edge e : graph.get(source)) {
               /* if (source == destination) {
                  System.out.println("source:" + source);
                  System.out.println("to:" + e.to);
                }*/
                DistanceToSource disToSrc;
                if (!minPenaltyToSource.containsKey(e.to)){
                    disToSrc = new DistanceToSource();
                    disToSrc.disTo = Integer.MAX_VALUE;
                    disToSrc.vertex = e.to;
                    disToSrc.visited = false;
                    minPenaltyToSource.put(e.to, disToSrc);
                }
                disToSrc = minPenaltyToSource.get(e.to);
                if(!disToSrc.visited) {
                   /* if (e.to == destination) {
                       System.out.println("e.to/edge:" + e);
                       System.out.println("e.to:" + source);
                       System.out.println("sourceMPP:" + (mppValue | e.weight));
                    }*/
                    disToSrc.visited = true;
                   computeMPP(graph, e.to, mppValue | e.weight, destination );
                }
            }

            minPenaltyToSource.get(source).visited = false;
        }

        public boolean hasPathTo(int v) {
            return minPenaltyToSource.containsKey(v);
        }

        public int getMPPTo(int v) {
            if(minPenaltyToSource.containsKey(v))
                return minPenaltyToSource.get(v).disTo;
            return -1;
        }

        private static class DistanceToSource {
            int vertex;
            int disTo;
            boolean visited;
            Set<Integer> mpValue = new HashSet<Integer>();        

        }
    }
   
}