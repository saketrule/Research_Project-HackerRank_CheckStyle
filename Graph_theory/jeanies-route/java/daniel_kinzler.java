import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {   
    
    public static int sumOfWeights = 0;
    public static boolean[] visited = null;
    public static int max = 0;
    public static ArrayList<LinkedList<Edge>> edges = null;
    public static boolean[] isDestination = null;
    
    public static void main(String[] args) throws IOException {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));        
        StringTokenizer tok = new StringTokenizer(bi.readLine());       
        
        int n = Integer.parseInt(tok.nextToken());
        int k = Integer.parseInt(tok.nextToken());
        isDestination = new boolean[n];
              
        tok = new StringTokenizer(bi.readLine());
        
        int start = Integer.parseInt(tok.nextToken())-1;
        isDestination[start] = true;
        
        for(int i = 1; i < k; i++) {
            int dest = Integer.parseInt(tok.nextToken())-1;
            isDestination[dest] = true;
        }
        
        edges = new ArrayList<LinkedList<Edge>>(n);
        for(int j = 0; j < n; j++) {
            edges.add(new LinkedList<Edge>());
        }
        
        for(int j = 0; j < n-1; j++) {               
            String[] line = bi.readLine().split(" ");
            int x = Integer.parseInt(line[0])-1;
            int y = Integer.parseInt(line[1])-1;
            int weight = Integer.parseInt(line[2]);
            sumOfWeights += weight;
            edges.get(x).add(new Edge(x,y,weight));
            edges.get(y).add(new Edge(y,x,weight));
        }

        visited = new boolean[n];
        //remove edges to subtrees that don't contain any destination nodes and delete the edge weight from the sum        
        dfs(start);
        //an optimal (minimal) solution will use any edge twice except for the edges on the longest path in the remaining tree
        visited = new boolean[n];
        dfs1(start);        
        System.out.println(2*sumOfWeights-max);
    }

    public static int dfs(int x) {
        visited[x] = true;
        int result = 0;
        if(isDestination[x]) {
            result++;
        }
        for(int i = 0; i < edges.get(x).size(); i++) {
            Edge e = edges.get(x).get(i);
            if(!visited[e.y]) {
                int r = dfs(e.y);
                if(r == 0) {
                    edges.get(x).remove(i);
                    sumOfWeights -= e.weight;
                    i--;
                } else {
                    result += r;
                }
            }
        }
        return result;
    }
    
    public static int dfs1(int x) {
        visited[x] = true;
        int localMax1 = 0;
        int localMax2 = 0;
        for(Edge e : edges.get(x)){
            if(!visited[e.y]) {
                int r = dfs1(e.y);
                r += e.weight;
                if(r > localMax1) {
                    localMax2 = localMax1;
                    localMax1 = r;
                } else if(r > localMax2) {
                    localMax2 = r;
                }
            }
        }
        if(localMax1 + localMax2 > max) {
            max = localMax1 + localMax2;
        }
        return localMax1;
    }
    
    static class Edge {
        int x;
        int y;
        int weight;
        
        Edge(int x, int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
}