import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numNodes = s.nextInt();
        int numEdges = s.nextInt();
                
        Graph g = new Graph(numNodes);
        
        for(int i = 0; i < numEdges; ++i){
            g.addEdge(s.nextInt() - 1, s.nextInt() - 1, s.nextShort());
        }
        
        //g.printGraph();
        
        int origin = s.nextInt() - 1;
        int destination = s.nextInt() - 1;
        
        System.out.println(findMinimumPenaltyPath(origin, destination, g));
        //System.out.println("origin: " + origin);
        //System.out.println("destination: " + destination);
    }
    
    public static short findMinimumPenaltyPath(final int origin, 
                                               final int destination, 
                                               final Graph g){
        
        Queue<Integer> nodes = new LinkedList<Integer>();
        Stack<Integer> path = new Stack<Integer>();
        
        short[] distances = new short[g.size()];
        Arrays.fill(distances, Short.MAX_VALUE);
        int[] parents = new int[g.size()];
                
        distances[origin] = 0;
        nodes.offer(origin);
        
        while(!nodes.isEmpty()){
            int node = nodes.poll();
            //System.out.println("while: " + node);
            HashSet<Edge> edges = g.getNode(node);
            
            for(Edge e : edges){
                if(distances[e.node] == Short.MAX_VALUE){
                    distances[e.node] = (short)(distances[node] | e.penalty);
                    //System.out.println("set distances[" + e.node + "] to: " + (short)(distances[node] | e.penalty));
                    parents[e.node] = node;
                    nodes.offer(e.node);
                }else if((short)(distances[node] | e.penalty) < distances[e.node]){
                    distances[e.node] = (short)(distances[node] | e.penalty);
                    //System.out.println("set distances[" + e.node + "] to: " + (short)(distances[node] | e.penalty));
                    parents[e.node] = node;
                    nodes.offer(e.node);
                }
            }
        }
        
        for(short s : distances){
            //System.out.print(s + " ");
        }
        //System.out.print("\n");
        for(int i : parents){
            //System.out.print((i+1) + " ");
        }
        
        return distances[destination];
    }
}

class Graph{
    private ArrayList< HashSet<Edge> > graph;
    
    public Graph(final int numNodes){
        graph = new ArrayList< HashSet<Edge> >();
        
        for(int i = 0; i < numNodes; ++i){
            graph.add(new HashSet<Edge>());
        }
    }
    
    public HashSet<Edge> getNode(final int node){
        return graph.get(node);
    }
    
    public int size(){ 
        return graph.size();
    }
    
    public void addEdge(final int n1, final int n2, final short penalty){
        graph.get(n1).add(new Edge(n2, penalty));
        graph.get(n2).add(new Edge(n1, penalty));
        //System.out.println("adding: " + n1 + ", " + n2 + ", " + penalty);
    }
    
    public void printGraph(){
        int nodeIndex = 1;
        
        for(HashSet<Edge> h : graph){
            
            System.out.print(nodeIndex + ": ");
                
            for(Edge e : h){
                System.out.print("(" + (e.node + 1) + ", " + e.penalty + ") ");
            }
            ++nodeIndex;
            System.out.print('\n');
        }
    }
}
    
class Edge{

    int node;
    short penalty;

    protected Edge(final int node, final short penalty){
        this.node = node;
        this.penalty = penalty;
    }
}

class Path{
    short totalPenalty;
    ArrayList<Integer> path;
}