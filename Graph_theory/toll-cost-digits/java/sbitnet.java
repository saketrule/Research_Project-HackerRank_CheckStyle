import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        Graph g = new Graph(n);
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            g.addEdge(x,y,r);
        }
        g.traverse();
    }
    
    static final class Graph {
        List<LinkedList<Edge>> vertices = new ArrayList<LinkedList<Edge>>();
        DJSet djs = null;
        Graph(int n) {
            djs = new DJSet(n);
            for(int i=0;i<n;i++) {
                vertices.add(new LinkedList<Edge>());
            }
        }
        
        void addEdge(int u, int v, int w) {
            vertices.get(u-1).add(new Edge(v-1, w));
            vertices.get(v-1).add(new Edge(u-1, 1000-w));
        }
        
        void traverse() {
            int[][] edges = new int[vertices.size()][10];
            boolean[] visited = new boolean[vertices.size()];
            for(int i=0;i<vertices.size();i++) {
                int t = djs.findRoot(i);
                //traverse(t, t, 0, 0,visited, edges, "" + t);
                traverse(t,edges);
                /*for(int u=0;u<edges.length;u++) {
                    for(int v=0;v<edges[u].length;v++) {
                        for(int m=1;m<=9;m++)
                        System.out.print(edges[u][v][m] + " ");
                    }
                    System.out.println();
                }*/
            }          
            djs.find(edges);
            /*for(int i=0;i<=9;i++) {
                int count = 0;
                for(int u=0;u<edges.length;u++) {
                    for(int v=0;v<edges[u].length;v++) {
                        if(u==v) continue;
                        if(edges[u][v][i] > 0) count +=1;
                    }    
                }
                System.out.println(count);
            }*/
        }
        
        void traverse(int start, int next, long p_w, long w, boolean[] visited, int[][] edges, String str) {
              boolean b = false;
              visited[next] = true;
            //if(visited[next]) {
                //int root = djs.findRoot(next, (int)p_w%10);
                int root = edges[next][(int)w%10];
                if(root>0) {
                    //System.out.println((start+1) + "===" + (next+1) + "===" + root + "===" + w + "===" + str); 
                    djs.merge(root-1, start);
                    //b = true;
                } else {
            //}                             
                    edges[next][(int)w%10] = start+1;
                    for(Edge i : vertices.get(next)) {
                        if(visited[i.v]) continue;
                        long sum = i.w + w;
                        traverse(start, i.v, w, sum, visited, edges, str + i.v);                
                        if(b) break;
                    }
            }
            visited[next] = false;
            //return b;
     }
 
     void traverse(int x, int[][] edges) {
          Stack<Integer> sac1 = new Stack<Integer>();
          Stack<Integer> sac2 = new Stack<Integer>();
          sac1.push(x);
          sac2.push(0);
          while (!sac1.isEmpty()) {
            int n = sac1.pop();
            int w = sac2.pop();
            if(edges[n][w] == 0) {
                edges[n][w] = x+1;
                for(Edge i : vertices.get(n)) {
                    int mod = (w + i.w) % 10;
                    sac1.push(i.v);
                    sac2.push(mod);
                }
            } else {
              djs.merge(edges[n][w]-1, x);
            }
          }
      }
  }
    
    static final class Edge {
        int v,w;
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    
    static final class DJSet {
        int[] A = null;
        int[] B = null;
        DJSet(int n) {
            A = new int[n];
            B = new int[n];
            for(int i=0;i<n;i++) {
                A[i] = i; // root of subset
                B[i] = 1; // count of subset
            }
        }
        
        int findRoot(int n) {
            int u = n;
            while(A[u] != u) {
                u = A[u];
            }
            return u;
        }
        
        void find(int[][] edges) {
            //System.out.println(Arrays.toString(A));
            //System.out.println(Arrays.toString(B));
            long[] res = new long[10];
            for (int i = 0; i < A.length; i++) {
                for (int d = 0; d <= 9; d++) {
                    if (edges[i][d] == 0) continue;
                    int p1 = findRoot(edges[i][d]-1);
                    int w = B[p1];
                    res[d] += w;
                    int p2 = findRoot(i);
                    if (p2 == p1) res[d]--;
                }
            }
            for(int i=0;i<res.length;i++) {
                System.out.println(res[i]);
            }
        }
        
        void merge(int u, int v) {
            if(u==v) return;
            while(A[u] != u) {
                u = A[u];
            }
            while(A[v] != v) {
                v = A[v];
            }
            if(u==v) return;
            if(B[u] > B[v]) {
                A[v] = A[u];
                B[u] += B[v];
            } else {
                A[u] = A[v];
                B[v] += B[u];
            }
        }
    }
}