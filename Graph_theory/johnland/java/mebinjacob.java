import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int M = 1;
    static class Node{
        int v;
        BigInteger[] adjList = new BigInteger[M]; // the stupid edges
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        M = scan.nextInt();
        Node[] vertices = new Node[N];
        for(int i=0; i < vertices.length; i++){
            vertices[i] = new Node();
        }
        for(int i=0; i < M;i++){
            //read the edges
            int v1 = scan.nextInt();
            int v2 = scan.nextInt();
            int e1 = scan.nextInt();
            vertices[v1 - 1].adjList[v2 - 1] = BigInteger.valueOf(0);
            vertices[v1 - 1].adjList[v2 - 1] = vertices[v1 - 1].adjList[v2 - 1].setBit(e1); 
            vertices[v2 - 1].adjList[v1 - 1] = BigInteger.valueOf(0);
            vertices[v2 - 1].adjList[v1 - 1] = vertices[v2 - 1].adjList[v1 - 1].setBit(e1);
        }
        //multiply and raise to the power of two
        // all pairs shortest path
        for(int intermediate = 0; intermediate < vertices.length; intermediate++){
            for(int source = 0; source < vertices.length; source++){
                for(int dest = 0; dest < vertices.length; dest++){
                    BigInteger d = vertices[source].adjList[dest];
                    if(vertices[source].adjList[intermediate] != null && vertices[intermediate].adjList[dest] != null){
                     //   System.out.println("came here");
                        BigInteger d_intermediate = vertices[source].adjList[intermediate].or(vertices[intermediate].adjList[dest]);
                        if(d == null || d_intermediate.compareTo(d) < 0){
                 //          System.out.println("came here 1 " + d_intermediate);
                            vertices[source].adjList[dest] =  d_intermediate;
                        }
                    }
                    
                }
            }
        }
        
        BigInteger sum = BigInteger.valueOf(0);
        for(int i=0; i < vertices.length; i++){
            for(int j = i+1; j < vertices[i].adjList.length; j++){
              if(vertices[i].adjList[j] != null){
                  //System.out.println("came here 2 " + vertices[i].adjList[j]);
                   sum = sum.add(vertices[i].adjList[j]);   
               }
                
            }
        }
        
        System.out.println(sum.toString(2));
    }
}