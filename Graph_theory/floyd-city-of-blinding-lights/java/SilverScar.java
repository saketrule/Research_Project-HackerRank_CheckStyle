import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        String ve = stdIn.nextLine();
        String[] tmp = ve.split(" ");
        int V = Integer.parseInt(tmp[0]);
        int E = Integer.parseInt(tmp[1]);
        int[][] graph = new int[V+1][V+1];
        
        for(int i=1;i<=V;i++){
            for(int j=1;j<=V;j++){
                if(i==j){
                    graph[i][j] = 0;
                }
                else{
                    graph[i][j] = 140000; 
                }
            }
        }
        
        for(int i=0;i<E;i++){
            String inp_edge = stdIn.nextLine();
            String[] temp = inp_edge.split(" ");
            int v1 = Integer.parseInt(temp[0]);
            int v2 = Integer.parseInt(temp[1]);
            int w = Integer.parseInt(temp[2]);
            
            graph[v1][v2] = w;
        }
        
        for(int k=1;k<=V;k++){
            for(int i=1;i<=V;i++){
                for(int j=1;j<=V;j++){
                    graph[i][j] = Math.min(graph[i][k]+graph[k][j],graph[i][j]);
                }
            }
        }
        int Q = stdIn.nextInt();
        String buffer = stdIn.nextLine();
        for(int i=0;i<Q;i++){
            String inp_edge = stdIn.nextLine();
            String[] temp = inp_edge.split(" ");
            int v1 = Integer.parseInt(temp[0]);
            int v2 = Integer.parseInt(temp[1]);
            if(graph[v1][v2]>130000)
                System.out.println("-1");
            else
                System.out.println(graph[v1][v2]);
        }
    }
}