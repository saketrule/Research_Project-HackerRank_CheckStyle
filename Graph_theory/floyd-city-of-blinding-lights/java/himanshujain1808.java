import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;



public class Solution {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int graph[][] = new int[n+1][n+1];
        for(int i=0;i<n+1;i++)
            Arrays.fill(graph[i],0); 
        long dist[][] = new long[n+1][n+1];
        
        for(int i=0;i<m;i++){
            graph[sc.nextInt()][sc.nextInt()] = sc.nextInt();
        }
        
        for(int i=0;i<n+1;i++){
            for(int j=0;j<n+1;j++){
                if(i!=j && graph[i][j]==0){
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        dist = flyod(graph);
        
        int q = sc.nextInt();
        while(q>0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            if(dist[u][v] == Integer.MAX_VALUE)
                System.out.println(-1);
            else
                System.out.println(dist[u][v]);
            q--;
        }
        
    }
    
    public static long[][] flyod(int [][]graph){
        int len = graph[0].length;
        long dist[][] = new long[len][len];
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                dist[i][j] = graph[i][j];
            }
        }
           
        for(int k=1;k<len;k++){
           for(int i=1;i<len;i++){
               for(int j=1;j<len;j++){
                   if(dist[i][k] + dist[k][j] < dist[i][j]){
                       dist[i][j] = dist[i][k] + dist[k][j];
                   }
               }
           }
            
        }
            
        return dist;
        
    }
}