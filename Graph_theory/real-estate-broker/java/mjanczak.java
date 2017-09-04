import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        int[] p = new int[n];
        int[] x = new int[m];
        int[] y = new int[m];
        for(int i=0; i<n; i++){
            a[i] = in.nextInt();
            p[i] = in.nextInt();
        }
        for(int i=0; i<m; i++){
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
           
        /*boolean[][] edges = new boolean[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                edges[i][j] = a[i]>=x[j] && p[i]<=y[j];
            }
        }*/
        AdjList[] adjList = new AdjList[n+1];
        for(int i=0; i<=n; i++)
            adjList[i] = new AdjList();
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if (a[i]<x[j] && p[i]>=y[j]){
                    adjList[i+1].adj.add(j+1+n);
                }
            }
        }
        
        
        System.out.println(hopKarp(adjList, n, m));
    }
    
    private static class AdjList{
        ArrayList<Integer> adj = new ArrayList<Integer>();
    }
    
    public static int hopKarp(AdjList[] adjList, int n, int m){
        int tot = 0;
        int[] dist = new int[n+m+1];
        int[] matched = new int[n+m+1];
        while(bfs(adjList, dist, matched, n, m)){
            int[] pt = new int[n+1];
            for(int i=1; i<=n; i++){
                if(matched[i]==0)
                    if(dfs(adjList, pt, dist, matched, i))
                        tot++;
            }
        }
        return tot;
    }
    
    public static boolean dfs(AdjList[] g, int[] pt, int[] dist, int[] matched, int u){
        for(int i = pt[u]; i<g[u].adj.size(); i++){
            pt[u] = i;
            int v = g[u].adj.get(i);
            if(dist[v] == dist[u] + 1){
                if(matched[v]==0 || (dist[matched[v]] == dist[v] + 1 && dfs(g, pt, dist, matched, matched[v]))){
                    matched[v] = u;
                    matched[u] = v;
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean bfs(AdjList[] g, int[] dist, int[] matched, int n, int m){
        Arrays.fill(dist, -1);
        LinkedList<Integer> q = new LinkedList<Integer>();
        for(int i=1; i<=n; i++){
            if(matched[i]==0){
                dist[i] = 0;
                q.add(i);
            }
        }
        boolean found = false;
        while(q.size()!=0){
            int u = q.poll();
            if(u>n && matched[u]==0) found = true;
            if(u<=n){
                for(Integer v : g[u].adj){
                    if(dist[v]==-1){
                        dist[v] = dist[u]+1;
                        q.add(v);
                    }
                }
            }
            else if(u > n && matched[u]!=0){
                if(dist[matched[u]]==-1){
                    dist[matched[u]] = dist[u] + 1;
                    q.add(matched[u]);
                }
            }
        }
        return found;
    }
    
}