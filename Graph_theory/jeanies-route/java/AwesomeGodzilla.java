import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static int shortestPath(int u, int v, int[][] graph, int numCities, int[][] dist, int visited) {
        int best = 1001;
        if (u == v) {
            return 0;
        }
        if (dist[u][v] > 0) {
            return dist[u][v];
        }
        if ()
        for (int i = 1; i < numCities+1; i++) {
            if (graph[u][i] > 0) {
                if (i != u && i != v && i != visited) {
                    int path = shortestPath(i, v, graph, numCities, dist, u) + graph[u][i];
                    if (path < best) {
                        best = path;
                    }
                }
            }
        }
        return best;
    }
    
    public static int shortestLetterPath(int src, int dst, int[] letters, int numLetters, int[][] dist, int numCities, boolean[] visited, int numLettersSent) {
        if (numLettersSent == numLetters) {
            return 0;
        }
        int path = 0;
        for (int i = 0; i < numCities; i++) {
            path = dist[dst][i];
            if (visited[i] == false && dist[dst][i] > 0) {
                visited[i] = true;
                path += shortestLetterPath(dst, i, letters, numLetters, dist, numCities, visited, numLettersSent+1);
            }
        }
        return path;
    }
    
    public static int walkPaths(int start, int[] letters, int numLetters, int[][] dist, int numCities) {
        int best = 1001;
        for (int i = 0; i < numLetters; i++) {
            if (i != start) {
                boolean[] visited = new boolean[numCities];
                visited[start] = true;
                int path = dist[start][i];
                visited[i] = true;
                path += shortestLetterPath(start, i, letters, numLetters, dist, numCities, visited, 2);
                if (path < best) {
                    best = path;
                }
            }
        }
        return best;
    }
    
    
    public static int solve(int[][] graph, int numCities, int[] letters, int numLetters) {
        /* compute distances between each city */
        int[][] dist = new int[numCities+1][numCities+1];
        for (int i = 1; i < numCities; i++) {
            for (int j = i+1; j < numCities; j++) {
                dist[i][j] = shortestPath(i, j, graph, numCities, dist, 0);
                dist[j][i] = dist[i][j];
            }
            
        }
        
        System.out.printf("DIST\n");
        for (int i = 0; i < numCities+1; i++) {
            for (int j = 0; j < numCities+1; j++) {
                System.out.printf("%d\t", dist[i][j]);
            }
            System.out.println();
        }
        
        /* walk all possible paths between cities */
        int bestPath = 1001;
        for (int i = 0; i < numLetters; i++) {
            int path = walkPaths(i, letters, numLetters, dist, numCities);
            System.out.printf("walked path from %d:%d\n", letters[i], path);
            if (path < bestPath) {
                bestPath = path;
            }
        }
        return bestPath;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int numCities = in.nextInt();
        int numLetters = in.nextInt();
        int[][] graph = new int[numCities+1][numCities+1];
        int[] letters = new int[numLetters];
        for (int i = 0; i < numLetters; i++) {
            letters[i] = in.nextInt();
        }
        while (in.hasNextLine()) {
            int u = in.nextInt();
            int v = in.nextInt();
            int d = in.nextInt();
            //System.out.printf("u:%d v:%d d:%d\n", u, v, d);
            graph[u][v] = d;
            graph[v][u] = d;
        }
        System.out.printf("GRAPH\n");
        for (int i = 0; i < numCities+1; i++) {
            for (int j = 0; j < numCities+1; j++) {
                System.out.printf("%s ", graph[i][j]);
            }
            System.out.println();
        }
        System.out.printf("%s\n", solve(graph, numCities, letters, numLetters));
    }
}