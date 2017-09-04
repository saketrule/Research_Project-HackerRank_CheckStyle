import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution2 {

    public static int n, m;
    public static int[] u;
    public static int[] v;
    public static int[] c;
    public static List[] nodeEdges;
    public static int[] penalty;
    public static int startNode;
    public static int endNode;
    public static int bestPenalty;
    
    public static void go(int src) {
     if (src == endNode) {
      if (bestPenalty == -1 || bestPenalty > penalty[src]) {
       bestPenalty = penalty[src];
      }
      return;
     }
     List<Integer> edges = nodeEdges[src];
     if (edges != null) {
      int cur = penalty[src];
      for (Integer edge : edges) {
       int dst = u[edge] != src ? u[edge] : v[edge];
       if (dst == src) {
        continue;
       }
       if (penalty[dst] == 0 || (cur | c[edge]) < penalty[dst]) {
        penalty[dst] = cur | c[edge];
           go(dst);
       }
      }
     }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        u = new int[m];
        v = new int[m];
        c = new int[m];
        nodeEdges = new List[n+1];
     for (int i=0; i<m; i++) {
         u[i] = in.nextInt();
         v[i] = in.nextInt();
         c[i] = in.nextInt();
         if (nodeEdges[u[i]] == null) {
          nodeEdges[u[i]] = new ArrayList();
         }
         nodeEdges[u[i]].add(new Integer(i));
         if (nodeEdges[v[i]] == null) {
          nodeEdges[v[i]] = new ArrayList();
         }
         nodeEdges[v[i]].add(new Integer(i));
     }
        startNode = in.nextInt();
        endNode = in.nextInt();
        penalty = new int[n+1];
        for (int i=0; i<n; i++) {
         penalty[i] = 0;
        }
        bestPenalty = -1;
        go(startNode);
     System.out.println(bestPenalty);
    }
}