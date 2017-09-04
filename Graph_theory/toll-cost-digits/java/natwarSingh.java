import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 class DFS
{
    private Stack<Integer> stack;
     static int V;
     static int d1=0;
     static int d2=0;
     static int d3=0;
     static int d4=0;
     static int d5=0;
     static int d6=0;
     static int d7=0;
     static int d8=0;
     static int d9=0;
     static int d10=0;
    
     public DFS(int n)  {
        stack = new Stack<Integer>();
       V=n;
     }
 
 
// Prints all paths from 's' to 'd'
static void printAllPaths(int s, int d,int[][] mat)
{
    // Mark all the vertices as not visited
    boolean[] visited = new boolean[V];
 
    // Create an array to store paths
    int[] path = new int[V];
    int path_index = 0; // Initialize path[] as empty
 
    // Initialize all vertices as not visited
    for (int i = 0; i < V; i++)
        visited[i] = false;
 
    // Call the recursive helper function to print all paths
    int sum=mat[s][d];
    int r=sum%10;
      
                switch(r) {
                    case 0: d1++ ;break;
                    case 1: d2++ ;break;
                    case 2: d3++ ;break;
                    case 3: d4++ ;break;
                    case 4: d5++ ;break;
                    case 5: d6++ ;break;
                    case 6: d7++ ;break;
                    case 7: d8++ ;break;
                    case 8: d9++ ;break;
                    case 9: d10++ ;break;
                    default: break;
                }
             
    printAllPathsUtil(s, d, visited, path, path_index,sum,mat);
}
 
// A recursive function to print all paths from 'u' to 'd'.
// visited[] keeps track of vertices in current path.
// path[] stores actual vertices and path_index is current
// index in path[]
static void  printAllPathsUtil(int u, int d, boolean visited[],
                              int path[], int path_index,int sum,int[][] mat)
{
    // Mark the current node and store it in path[]
    visited[u] = true;
    path[path_index] = u;
    path_index++;
 
    // If current vertex is same as destination, then print
    // current path[]
    if (u == d)
    {
        for (int i = 0; i<path_index; i++) {
           // System.out.print(path[i] + " ");
        }    
        int r=sum%10;
                switch(r) {
                    case 0: d1++ ;break;
                    case 1: d2++ ;break;
                    case 2: d3++ ;break;
                    case 3: d4++ ;break;
                    case 4: d5++ ;break;
                    case 5: d6++ ;break;
                    case 6: d7++ ;break;
                    case 7: d8++ ;break;
                    case 8: d9++ ;break;
                    case 9: d10++ ;break;
                    default: break;
                }
        
       // System.out.println(sum);
        
    }
    else // If current vertex is not destination
    {
        // Recur for all the vertices adjacent to current vertex
       
        for(int i=0;i<V;i++)
        {
             //if()
            if (mat[u][i]>=1 && !visited[i]) {
                sum+=mat[u][i]; 
                int r=sum%10;
              
                switch(r) {
                    case 0: d1++ ;break;
                    case 1: d2++ ;break;
                    case 2: d3++ ;break;
                    case 3: d4++ ;break;
                    case 4: d5++ ;break;
                    case 5: d6++ ;break;
                    case 6: d7++ ;break;
                    case 7: d8++ ;break;
                    case 8: d9++ ;break;
                    case 9: d10++ ;break;
                    default: break;
                }
               
                printAllPathsUtil(i, d, visited, path, path_index,sum,mat);
                       
            }
          }
        
    }
 
    // Remove current vertex from path[] and mark it as unvisited
    path_index--;
    visited[u] = false;
}
   
}        
public class Solution {
    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        int adjacency_matrix[][] = new int[n + 1][n + 1];
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            x=x-1;
            y=y-1;
            adjacency_matrix[x][y]=r;
            adjacency_matrix[y][x]=1000-r;
        }
        DFS dfs=new DFS(n);
        for(int i=0;i<n;i++) {
            
             for(int j=0;j<n;j++) {
                 if(i!=j) {
                   dfs.printAllPaths(i,j,adjacency_matrix);
                   dfs.printAllPaths(j,i,adjacency_matrix);  
                 }    
             }
        }
        System.out.println(dfs.d1);
        System.out.println(dfs.d2);
        System.out.println(dfs.d3);
        System.out.println(dfs.d4);
        System.out.println(dfs.d5);
        System.out.println(dfs.d6);
        System.out.println(dfs.d7);
        System.out.println(dfs.d8);
        System.out.println(dfs.d9);
        
    }
}