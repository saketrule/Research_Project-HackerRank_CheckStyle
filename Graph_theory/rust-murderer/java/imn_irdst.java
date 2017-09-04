import java.io.*;
        import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter   out = new PrintWriter(System.out);
        int tc = Integer.parseInt(sc.readLine());

        while (tc-->0){
            String[] tokens = sc.readLine().split("\\s+");
            int n = Integer.parseInt(tokens[0]);
            int m = Integer.parseInt(tokens[1]);
            int[] dist = new int[n]; Arrays.fill(dist, -1);
            HashSet<Integer>[] adjRev = new HashSet[n];
            for (int i=0 ; i<n ; i++) adjRev[i] = new HashSet<>();

            for (int i=0, u, v ; i<m ; i++){
                tokens = sc.readLine().split("\\s+");
                u = Integer.parseInt(tokens[0]) - 1;
                v = Integer.parseInt(tokens[1]) - 1;
                adjRev[u].add(v); adjRev[v].add(u);
            }

            int s = Integer.parseInt(sc.readLine()) - 1;

            int discovered = 1;
            Queue<Integer> q = new LinkedList<>();
            q.add(s); dist[s] = 0;
            while (!q.isEmpty() && discovered < n){
                int u = q.poll();
                for(int v=0 ; v<n ; v++){
                    if(!adjRev[u].contains(v) && dist[v]<0){
                        dist[v] = dist[u] + 1; q.add(v); discovered++;
                    }
                }
            }
            for (int u=0 ; u<n ; u++){
                if (u != s) out.print(dist[u] + " ");
            } out.println();
        }
        sc.close();
        out.close();
    }
}