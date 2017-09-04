import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Edge[] edges = new Edge[m];
        List<Edge>[] g = new LinkedList[n];
        for(int i=0;i<m;i++){
            Edge e = new Edge();
            e.u = sc.nextInt()-1;
            e.v = sc.nextInt()-1;
            e.w = sc.nextInt();
            edges[i] = e;
            if(g[e.u] == null) g[e.u] = new LinkedList<Edge>();
            g[e.u].add(e);
            Edge e1 = new Edge();
            e1.u = e.v;
            e1.v = e.u;
            e1.w = e.w;
            if(g[e1.u] == null) g[e1.u] = new LinkedList<Edge>();
            g[e1.u].add(e1);
        }
        int a = sc.nextInt()-1;
        int b = sc.nextInt()-1;
        boolean[][] visited = new boolean[n][2<<12];
        Queue<Node> q = new LinkedList<Node>();
        Node start = new Node();
        start.v = a;
        start.x = 0;
        visited[a][0] = true;
        q.add(start);
        while(!q.isEmpty()){
            Node curr = q.poll();
            for(Edge e : g[curr.v]) {
                if(!visited[e.v][curr.x | e.w]){
                    Node neigh = new Node();
                    neigh.v = e.v;
                    neigh.x = (curr.x | e.w);
                    visited[neigh.v][neigh.x] = true;
                    q.add(neigh);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i=0;i<(1<<12);i++) {
            if(visited[b][i] && i<min)
                min =  i;
        }
        if(min > (1<<12))
            min = -1;
        System.out.println(min);
    }
    
    private static class Node{
        int v;
        int x;
    }       
    
    private static class Edge{
        int u;
        int v;
        int w;
    }
}