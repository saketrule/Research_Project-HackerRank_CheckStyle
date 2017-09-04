import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node implements Comparable<Node>{
    int val, cost;
    Node(int val, int cost){
        this.val = val;
        this.cost = cost;
    }
    
    public int compareTo(Node x){
        return Integer.compare(this.cost, x.cost);
    }
}

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            int n = sc.nextInt(), m = sc.nextInt();
            int[][] M = new int[n+1][n+1];
            for(int[] row : M)
                Arrays.fill(row, Integer.MAX_VALUE);
            ArrayList<ArrayList<Node>> adj = new ArrayList<ArrayList<Node>>(n+1);
            for(int i=0; i<n+1; i++)adj.add(new ArrayList<Node>(n+1));
            int x,y,cost;
            while(m-- > 0){
                x = sc.nextInt();
                y = sc.nextInt();
                cost = sc.nextInt();
                if(x != y){
                    if(cost <= M[x][y]){
                        M[x][y] = cost;
                        M[y][x] = cost;
                    }
                }
            }
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(M[i][j] != Integer.MAX_VALUE){
                        adj.get(i).add(new Node(j, M[i][j]));
                        adj.get(j).add(new Node(i, M[j][i]));
                    }
                }
            }
            int A = sc.nextInt();
            int B = sc.nextInt();
            djikstra(A, B, adj, n);
    }
    
    static void djikstra(int s, int t, ArrayList<ArrayList<Node>> adj, int n){
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(s, 0));
        while(pq.size() > 0){
            Node curr = pq.peek(); pq.remove();
            int currN = curr.val;
            Iterator<Node> it = adj.get(currN).iterator();
            while(it.hasNext()){
                Node temp = it.next();
                if(dist[temp.val] > (dist[currN] | temp.cost)){
                    pq.add(new Node(temp.val, (dist[currN]|temp.cost)));
                    dist[temp.val] = dist[currN] | temp.cost;
                }
            }
        }
        //System.out.println(t);
        System.out.print(((dist[t] == Integer.MAX_VALUE)?-1:dist[t]));
        //for(int i=1; i<dist.length; i++){
        //    if(i!=s){
        //        System.out.print(((dist[i] == Integer.MAX_VALUE)?-1:dist[i]) + " ");
        //    }
        //}
        //System.out.println();
    }
}