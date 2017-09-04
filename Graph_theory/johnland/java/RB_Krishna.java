import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.lang.*;
import java.util.regex.*;

public class Solution {
    static BigInteger TWO = new BigInteger("2");
    static BigInteger TEN = new BigInteger("10");
    static BigInteger FIVE = new BigInteger("5");
    static BigInteger MINUS_ONE = new BigInteger("-1");
    static BigInteger MAX = TWO.pow(2*(int)Math.pow(10,5));
    public static void floydWarshall(BigInteger graph[][], int V) {
        BigInteger dist[][] = new BigInteger[V][V];
        int i, j, k;
 
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (dist[i][k].compareTo(MINUS_ONE)!=0 && dist[k][j].compareTo(MINUS_ONE)!=0) 
                        if(dist[i][j].compareTo(MINUS_ONE)<=0 && i!=j)
                            dist[i][j] = dist[i][k].add(dist[k][j]);
                        else if(dist[i][k].add(dist[k][j]).compareTo(dist[i][j])<0)
                            dist[i][j] = dist[i][k].add(dist[k][j]);
                }
            }
        }
        BigInteger sum = new BigInteger("0");
        for(i=0;i<V;i++) {
            for(j=i+1;j<V;j++) {
                if(!(dist[i][j].compareTo(MINUS_ONE)==0)) {                  
                    sum = sum.add(dist[i][j]);
                }
            }
        }
        System.out.println(sum.toString(2));
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int V = in.nextInt();
        int n = in.nextInt();
        BigInteger graph[][] = new BigInteger[V][V];
        for(int i=0;i<V;i++) {
            for(int j=0;j<V;j++)
                graph[i][j] = new BigInteger("-1");
        }
        in.nextLine();
        for(int i=0;i<n;i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            int next = in.nextInt();
            BigInteger dist = TWO.pow(next);
            graph[a][b] = dist;
            graph[b][a] = dist;
            if(in.hasNextLine())
                in.nextLine();
        }
        floydWarshall(graph,V);
    }
}