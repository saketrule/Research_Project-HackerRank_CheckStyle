import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int tests;
        Scanner scanner = new Scanner(System.in);
        tests = 1;
        
        while(tests-- != 0){
            int vertices = scanner.nextInt();
            int edges = scanner.nextInt();
            
            EdgeList[] adjList = new EdgeList[vertices];
            for(int i=0;i<vertices;i++){
                adjList[i] = new EdgeList();
            }
            
            for(int i=0;i<edges;i++){
                int v1 = scanner.nextInt() - 1;
                int v2 = scanner.nextInt() - 1;
                int wgt = scanner.nextInt();
                adjList[v1].addEdge(v2,wgt);
                adjList[v2].addEdge(v1,wgt);
            }
            
            dijk(adjList,scanner.nextInt(),scanner.nextInt());
        }
    }
    
    private static void dijk(EdgeList[] adjList,int start,int end){
        start--;
        end--;
        PriorityQueue<Path> heap = new PriorityQueue<Path>(1,new PathComparator());
        
        heap.add(new Path(start,0));
        
        int[] result = new int[adjList.length];
        int shortestFound = 0;
        
        for(int i=0;i<result.length;i++) result[i] = -1;

        while(!heap.isEmpty() && result[end] == -1){
            Path path = heap.poll();
            int vertex = path.vertex;
            if(result[vertex] != -1)
                continue;
            
            result[vertex] = path.weight;
            shortestFound++;
            
            Iterator<Edge> i = adjList[vertex].list.iterator();
            while(i.hasNext()){
                Edge e = i.next();
                if(e.endVertex != vertex && result[e.endVertex] == -1)
                    heap.add(new Path(e.endVertex,e.weight | path.weight));
            }
            
        }
        
        System.out.println(result[end]);
    }
}

class EdgeList {
    List<Edge> list = new LinkedList<Edge>();
    
    public void addEdge(int vertex,int weight){
        list.add(new Edge(vertex,weight));
    }
}

class Path {
    int vertex;
    int weight;
    public Path(int vertex,int weight){
        this.vertex = vertex;
        this.weight = weight;
    }
}

class PathComparator implements Comparator<Path> {
    public int compare(Path e1,Path e2){
        return e1.weight - e2.weight;
    }
}

class Edge {
    int endVertex;
    int weight;
    
    public Edge(int endVertex,int weight) {
        this.endVertex = endVertex;
        this.weight = weight;
    }
}