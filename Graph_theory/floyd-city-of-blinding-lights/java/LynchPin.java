import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static int [][] graph;
    public static int [] weight;
    public static int INF = 10000;
    public static void initGraph(int n) {
        graph = new int[n+1][n+1];
        for(int i=1;i<n+1;i++)
            for(int j=1;j<n+1;j++)
                graph[i][j] = INF;
        weight = new int[n+1];
        for(int i=1;i<n+1;i++)
            weight[i] = INF;
    }
    public static void shortestPath(int start, int end) {
        weight[start] = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        while(queue.size() != 0) {
            int node = queue.removeFirst();   
            for(int i=1;i<graph.length;i++) {
                if(weight[i] > weight[node]+ graph[node][i] ) {
                    weight[i] = Math.min(weight[i],weight[node]+ graph[node][i]);
                    queue.add(i);
                }
            }
            
        }
    if(weight[end] == INF)
        System.out.println(-1);
     else
        System.out.println(weight[end]);
    for(int i=0;i<weight.length;i++)
        weight[i] = INF;
    }
        public static void shortest() {
            for(int k=1;k<graph[0].length;k++)
                for(int i=1;i<graph[0].length;i++)
                    for(int j=1;j<graph[0].length;j++)
                        graph[i][j] = Math.min(graph[i][j],graph[i][k]+graph[k][j]);
        //System.out.println(graph[1][2]);
        }
         public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
       Scanner a = new Scanner(System.in);
           int n = a.nextInt();
           int m = a.nextInt();
           initGraph(n);
           for(int j=0;j<m;j++) {
               int x = a.nextInt();
               int y = a.nextInt();
               int w = a.nextInt();
                    graph[x][y] = w;
           }
            shortest();
           int q = a.nextInt();
           for(int s=0;s<q;s++) {
            int start = a.nextInt();
            int end = a.nextInt();
//                shortestPath(start, end);
            if(start == end)
                System.out.println(0);
            else if(graph[start][end] == INF)
            System.out.println(-1);
            else  System.out.println(graph[start][end]);
           }
           //shortestPath(start,weight);
       }
}