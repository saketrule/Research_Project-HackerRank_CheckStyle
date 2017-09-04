import java.io.*;
import java.util.*;

public class Solution {


    private static class Edge {
        int weight;
        int start;
        int end;
    }

    public static int[][] createDistanceMatrix(int n, Edge[] edges) {

        int[][] result = new int[n][n];
        for (int i =0; i < n; ++i) {
            for (int j=0; j < n; ++j){
                result[i][j] = -1;
            }
            result[i][i] = 0;
        }

        for (Edge edge : edges) {
            result[edge.start][edge.end] = edge.weight;
        }
        for (int k =0; k < n; ++k) {
            for (int i =0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {

                    int newDistance =  result[i][k]>=0 && result[k][j] >=0 ? result[i][k] + result[k][j] : -1;
                    if (result[i][j] < 0 || (newDistance >= 0 && result[i][j] > newDistance)) {
                        result[i][j] = newDistance;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);

        String[] parts = scanner.nextLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        Edge edges[] = new Edge[m];
        for (int i=0; i < m; ++i) {
            parts = scanner.nextLine().split(" ");
            Edge edge = new Edge();
            edge.start = Integer.parseInt(parts[0]) - 1;
            edge.end = Integer.parseInt(parts[1]) - 1;
            edge.weight = Integer.parseInt(parts[2]);
            edges[i] = edge;
        }
        int [][] distanceMatrix = createDistanceMatrix(n, edges);
        int q = scanner.nextInt();
        scanner.nextLine();
        for (int i=0; i < q; ++i) {
            parts = scanner.nextLine().split(" ");
            int start = Integer.parseInt(parts[0]) - 1;
            int finish = Integer.parseInt(parts[1]) - 1;
            System.out.println(distanceMatrix[start][finish]);
        }
    }
}