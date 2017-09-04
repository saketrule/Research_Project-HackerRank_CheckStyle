import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node implements Comparable<Node>{
    int val, cost;
    Node(int val, int cost){
        this.val = val; this.cost = cost;
    }
    
    public int compareTo(Node x){
        return Integer.compare(this.cost, x.cost);
    }
}

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<ArrayList<Node>> adj = new ArrayList<ArrayList<Node>>(n+1);
        for(int i = 0; i< n+1; i++)adj.add(new ArrayList<Node>(n+1));
        int m = sc.nextInt();
        int max = -1;
        for (int i = 0; i < m; i++) {
                int x = sc.nextInt(), y = sc.nextInt(), cost = sc.nextInt();
                cost = (int) Math.pow(2, cost);
                adj.get(x).add(new Node(y, cost)); //acyclic
                adj.get(y).add(new Node(x, cost));
        }
        int resultSum = 0;
        
        for (int i = 1; i < n; i++) {
            int[] minDistArr = djikstra(i, adj, n);
            for (int j = i+1; j <= n; j++) {
                resultSum += minDistArr[j];
            }
        }
        
        //print out result
        System.out.println(Integer.toBinaryString(resultSum));
    }
    
    
    static int[] djikstra(int s, ArrayList<ArrayList<Node>> adj, int n){
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
                if(dist[temp.val] > dist[currN] + temp.cost){
                    pq.add(new Node(temp.val, dist[currN]+temp.cost));
                    dist[temp.val] = dist[currN] + temp.cost;
                }
            }
        }
        return dist;
    }
}