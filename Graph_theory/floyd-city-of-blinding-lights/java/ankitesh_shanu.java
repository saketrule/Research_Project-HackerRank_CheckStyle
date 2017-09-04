import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int V=sc.nextInt();
        int[][] graph= new int[V][V];
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                if(i!=j)
                    graph[i][j]=Integer.MAX_VALUE;
            }
        }
        int E=sc.nextInt();
        for(int i=0;i<E;i++){
            graph[sc.nextInt()-1][sc.nextInt()-1]=sc.nextInt();
        }
        floydWarshell(graph);
        int Q=sc.nextInt();
        for(int i=0;i<Q;i++){
            int k=sc.nextInt()-1;
            int l=sc.nextInt()-1;
            if(graph[k][l]==Integer.MAX_VALUE)
                System.out.println("-1");
            else
                System.out.println(graph[k][l]);
        }
        sc.close();
    }
    
    private static void floydWarshell(int[][] graph){
        for(int k=0;k<graph.length;k++){
            for(int i=0;i<graph.length;i++){
                for(int j=0;j<graph.length;j++){
                    if(graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE && graph[i][k]+graph[k][j]<graph[i][j])
                        graph[i][j]=graph[i][k]+graph[k][j];
                }
            }
        }
    }
}