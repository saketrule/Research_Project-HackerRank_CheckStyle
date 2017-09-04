import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int[][] adj_mat = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                adj_mat[i][j] = 0;
            }
        }
        int[][] edges = new int[M][3];
        for (int i = 0; i < M; ++i) {
            edges[i][0] = scan.nextInt() - 1;
            edges[i][1] = scan.nextInt() - 1;
            edges[i][2] = scan.nextInt();
        }
        int start_node = scan.nextInt() - 1;
        int end_node = scan.nextInt() - 1;
        int[] dist = new int[N];
        for (int i = 0; i < N; ++i) {
            if (i == start_node) {
                dist[i] = 0;
            } else {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        int[] finished_node = new int[N];
        for (int i = 0; i < N; ++i) {
            finished_node[i] = 0;
        }
        int candidate = get_candidate(dist, finished_node);
        while (candidate != -1) {
            for (int i = 0; i < M; ++i) {
                if (edges[i][0] == candidate) {
                    if (dist[edges[i][1]] == Integer.MAX_VALUE) {
                        dist[edges[i][1]] = dist[candidate] | edges[i][2];
                    } else {
                        if (dist[edges[i][1]] > (dist[candidate] | edges[i][2])) {
                            dist[edges[i][1]] = dist[candidate] | edges[i][2];
                        }
                    }
                }
                if (edges[i][1] == candidate) {
                    if (dist[edges[i][0]] == Integer.MAX_VALUE) {
                        dist[edges[i][0]] = dist[candidate] | edges[i][2];
                    } else {
                        if (dist[edges[i][0]] > (dist[candidate] | edges[i][2])) {
                            dist[edges[i][0]] = dist[candidate] | edges[i][2];
                        }
                    }
                }
            }
            for (int i = 0; i < N; ++i) {
                if (adj_mat[candidate][i] != 0) {
                    if (dist[i] == Integer.MAX_VALUE) {
                        dist[i] = dist[candidate] | adj_mat[candidate][i];
                    } else {
                        if (dist[i] > (dist[candidate] | adj_mat[candidate][i])) {
                            dist[i] = dist[candidate] | adj_mat[candidate][i];
                        }
                    }    
                }
            }
            finished_node[candidate] = 1;
            if (candidate == end_node) {
                break;
            }
            candidate = get_candidate(dist, finished_node);    
        }
        if (candidate == -1) {
            System.out.println("-1");
        } else {
            System.out.println(dist[end_node]); 
        }
    }
    public static int get_candidate(int[] dist, int[] finished_node) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;
        for (int i = 0; i < dist.length; ++i) {
            if (finished_node[i] == 0) {
                if (dist[i] < min) {
                    min = dist[i];
                    min_index = i;
                }
            }
        }
        if (min_index == -1) {
            return -1;
        }
        return min_index;
    }
}