import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution{
    static int n;
    static int m;
    static int[][] graph;
    static int[] cost;
    static boolean[] visited;
    static int max1;
    static int max2;
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in=new Scanner(System.in);
         n = in.nextInt();
         m = in.nextInt();
        cost=new int[n];
         for(int i=0;i<n;i++){
             cost[i]=in.nextInt();
         }
        graph = new int[n][n];
        for(int h=0;h<m;h++){
            int a = in.nextInt();
            int b = in.nextInt();
            a--;
            b--;
            graph[a][b] =1;
            graph[b][a] = 1;
        }
        if(n==1){
            System.out.println(""+cost[0]+" 1");
            return;
        }
        
        visited = new boolean[n];
        Adjacent res = rec(0);
        System.out.println(""+res.first+" "+res.second);
        
    }
    public static int findCost(int a,int[][] graph){
        int result=0;
        for(int i=0;i<n;i++){
            result+=graph[a][i];
        }
        return result;
    }
    
    public static Adjacent rec(int s){
        if(s==n){
            return new Adjacent(0l,1l);
        }
        Adjacent best = new Adjacent(0l, 0l);
        if(!visited[s]){
            boolean[] bool = visited.clone();
            for(int j=0;j<n;j++)
                if(graph[s][j]==1)
                    visited[j]  =true;
            Adjacent one = rec(s+1);
            one.first += cost[s];
            best = function1(one, best);
            visited = bool.clone();
        }
        Adjacent one = rec(s+1);
        best = function1(one, best);
        return best;
        
    }
    
    public static boolean isAdjacent(int a,int b,int[][] graph){
        boolean result=false;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(graph[i][j]==1)
                    result=true;
            }
        }
        return result;
    }
    
    public static Adjacent function1(Adjacent a, Adjacent b){
        if(a.first < b.first) return b;
        if(a.first > b.first) return a;
        else return new Adjacent(a.first, (a.second+b.second));
    }
    
}

class Adjacent{
    long first;
    long second;
    
    public Adjacent(long a, long b){
        first = a;
        second = b;
    }
    
    
}

class edge{
    int src;
    int dest;
    
    public edge(int src,int dest){
        this.src=src;
        this.dest=dest;
    }
    
    
    
}