import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class HR_MinimumPaneltyPass {
    
    public static int result = Integer.MAX_VALUE;
    public static List<ArrayList<Node>> childrenList = new ArrayList<ArrayList<Node>>();
    public static boolean[] visited;
    public static boolean[][] dp; 
    
    public static class Node {
        public int nodeId;
        public int weight;
        
        public Node(int nodeId, int weight) {
            this.nodeId = nodeId;
            this.weight = weight;
        }
    }

    public static void dfs(int cur, int tar, int penalty) {
        if(dp[cur][penalty]) {
            visited[cur] = false;
            return;
        } else if(cur == tar) {
            result = Math.min(result, penalty);
            visited[cur] = false;
            return;
        }
        
        dp[cur][penalty] = true;
        for(Node node : childrenList.get(cur)) {
            if(!visited[node.nodeId]) {
                visited[node.nodeId] = true;
                dfs(node.nodeId, tar, (penalty | node.weight));
            }
        }
        visited[cur] = false;
        return;
    }
    
    public static void main(String[] args) {
  
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        for(int i = 0; i <= N; i++)
            childrenList.add(new ArrayList<Node>());
        for(int i = 0; i < M; i++) {
            int U = in.nextInt();
            int V = in.nextInt();
            int C = in.nextInt();
            childrenList.get(U).add(new Node(V, C));
            childrenList.get(V).add(new Node(U, C));
        }
        int A = in.nextInt();
        int B = in.nextInt();
        visited = new boolean[N+1];
        visited[A] = true;
        dp = new boolean[N+1][1024];
        dfs(A, B, 0);
        if(result == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(result);
        in.close();
    }
}