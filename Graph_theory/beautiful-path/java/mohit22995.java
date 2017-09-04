import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static int INF;
    static {
        INF = Integer.MAX_VALUE;
    }
    
    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        PrintWriter ou = new PrintWriter (new BufferedWriter(new OutputStreamWriter(System.out)));
        StringBuilder sb = new StringBuilder("");
        
        String[] pts = br.readLine().split(" ");
        int n = Integer.parseInt(pts[0]);
        int m = Integer.parseInt(pts[1]);
        
        @SuppressWarnings("unchecked")
        List<Edge> edgeList = new ArrayList<Edge>();
        
        for (int i = 0; i < m; i ++) {
            pts = br.readLine().split(" ");
            
            int u = Integer.parseInt(pts[0]);
            int v = Integer.parseInt(pts[1]);
            int w = Integer.parseInt(pts[2]);
            Edge temp = new Edge(u, v, w);
            Edge temp2 = new Edge(v, u, w);
            edgeList.add(temp);
            edgeList.add(temp2);
        }
        
        pts = br.readLine().split(" ");
        int b = Integer.parseInt(pts[0]);
        int a = Integer.parseInt(pts[1]);
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[a] = 0;
        
        for (int i = 1; i <= n; i ++) {
            for (Edge curr : edgeList) {
                if (dist[curr.v] > (dist[curr.u] | curr.w) ) {
                    dist[curr.v] = (dist[curr.u] | curr.w);
                }
            }
        }
        
        
        if (dist[b] == INF) {
            ou.println(-1);
        } else {
            ou.println(dist[b]);
        }
        
        
        ou.close();
        
    }
    
    static class Edge {
        int u, v, w;
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}