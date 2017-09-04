import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    // A DFS based recursive function that returns true if a
    // matching for vertex u is possible
    boolean bpm(boolean bpGraph[][], int u, boolean seen[],
                int matchR[], int N, int M) {
        // Try every job one by one
        for (int v = 0; v < N; v++) {
            // If applicant u is interested in job v and v
            // is not visited
            if (bpGraph[u][v] && !seen[v])
            {
                seen[v] = true; // Mark v as visited
 
                // If job 'v' is not assigned to an applicant OR
                // previously assigned applicant for job v (which
                // is matchR[v]) has an alternate job available.
                // Since v is marked as visited in the above line,
                // matchR[v] in the following recursive call will
                // not get job 'v' again
                if (matchR[v] < 0 || bpm(bpGraph, matchR[v], seen, matchR, N, M)) {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }
 
    // Returns maximum number of matching from M to N
    int maxBPM(boolean bpGraph[][], int N, int M) {
        // An array to keep track of the applicants assigned to
        // jobs. The value of matchR[i] is the applicant number
        // assigned to job i, the value -1 indicates nobody is
        // assigned.
        int matchR[] = new int[N];
 
        // Initially all jobs are available
        for(int i=0; i<N; ++i) {
            matchR[i] = -1;
        }
 
        int result = 0; // Count of jobs assigned to applicants
        for (int u = 0; u < M; u++) {
            // Mark all jobs as not seen for next applicant.
            boolean seen[] =new boolean[N];
            for(int i=0; i<N; ++i) {
                seen[i] = false;
            }
 
            // Find if the applicant 'u' can get a job
            if (bpm(bpGraph, u, seen, matchR, N, M)) {
                result++;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] clients = new int[n][2];
        for(int clienti = 0; clienti < n; clienti++){
            clients[clienti][0] = in.nextInt();
            clients[clienti][1] = in.nextInt();
        }
        int[][] houses = new int[m][2];
        for(int housei=0; housei < m; housei++){
            houses[housei][0] = in.nextInt();
            houses[housei][1] = in.nextInt();
        }
        boolean[][] graph = new boolean[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                if (houses[j][0] > clients[i][0] && houses[j][1] <= clients[i][1]) {
                    graph[i][j] = true;
                } else {
                    graph[i][j] = false;
                }
            }
        }
        
        Solution solution = new Solution();
        System.out.println(solution.maxBPM(graph, m, n));
    }
}