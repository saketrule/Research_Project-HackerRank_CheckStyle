import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        
        final int N = scanner.nextInt();
        final int M = scanner.nextInt();
        final int[][] C = new int[N + 1][N + 1];
        
        for (int m = 0; m < M; m++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            
            if (0 < C[u][v]) {
                C[u][v] = C[v][u] = Math.min(C[u][v], scanner.nextInt());
            } else {
                C[u][v] = C[v][u] = scanner.nextInt();
            }
        }
        
        final int A = scanner.nextInt();
        final int B = scanner.nextInt();
        
        // Dijkstra
        final boolean[] visited = new boolean[N + 1];
        final int[] distances = new int[N + 1];
        
        for (int n = 1; n <= N; n++) {
            distances[n] = Integer.MAX_VALUE;
        }
        
        distances[A] = 0;
        
        while (true) {
            
            // Find the node which hasn't been visited and have min distance from A
            int minNode = -1;
            int minNodeDistance = Integer.MAX_VALUE;
            for (int n = 1; n <= N; n++) {
                if (!visited[n] && distances[n] < minNodeDistance) {
                    minNodeDistance = distances[n];
                    minNode = n;
                }
            }
            
            // No more node, or meet the destination
            if (minNode == -1 || minNode == B) {
                break;
            }
            
            // Set the min node to be visited
            visited[minNode] = true;
            
            // Calculate distances for all non-visited neighbors of the min node
            for (int n = 1; n <= N; n++) {
                if (0 < C[minNode][n] && !visited[n]) {
                    int candidate = minNodeDistance | C[minNode][n]; 
                    
                    if (candidate < distances[n]) {
                        distances[n] = candidate;
                    }
                }
            }
        }
        
        System.out.println(distances[B] == Integer.MAX_VALUE ? -1 : distances[B]);
    }
}