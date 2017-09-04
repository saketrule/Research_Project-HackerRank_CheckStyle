import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/floyd-city-of-blinding-lights
 * 
 * @author sokol
 *
 */
public class Solution {
  private final int N;
  private int[][] dist;
  // private Map<Integer, Set<Edge>> nodes;

  public Solution(int N) {
    this.N = N;
    dist = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (i == j) {
          dist[i][j] = 0;
        } else {
          dist[i][j] = -1;
        }
      }
    }
    //
    // nodes = new HashMap<>();
    // for (int i = 1; i <= this.N; i++) {
    // nodes.put(i, new HashSet<Edge>());
    // }
  }

  public void addEdge(int n1, int n2, int w) {
    // nodes.get(n1).add(new Edge(n1, n2, w));
    dist[n1][n2] = w;
  }

  /**
   * Calculates dist from each pair of nodes in O(V^3).
   */
  public void floydWarshall() {

    for (int r = 1; r <= N; r++) {
      for (int u = 1; u <= N; u++) {
        for (int v = 1; v <= N; v++) {
          if (dist[u][r] >= 0 && dist[r][v] >= 0
              && (dist[u][v] < 0 || dist[u][r] + dist[r][v] < dist[u][v])) {
            dist[u][v] = dist[u][r] + dist[r][v];
          }
        }
      }
    }
  }

  public int shortestPath(int n1, int n2) {
    return dist[n1][n2];
  }

  public static void main(String[] argv) {
    Scanner scanner = new Scanner(System.in);

    int N = scanner.nextInt();
    int M = scanner.nextInt();

    Solution sol = new Solution(N);

    for (int i = 0; i < M; i++) {
      int n1 = scanner.nextInt();
      int n2 = scanner.nextInt();
      int w = scanner.nextInt();

      sol.addEdge(n1, n2, w);
    }

    sol.floydWarshall();

    StringBuilder out = new StringBuilder();
    int Q = scanner.nextInt();
    for (int i = 0; i < Q; i++) {
      int n1 = scanner.nextInt();
      int n2 = scanner.nextInt();
      out.append(sol.shortestPath(n1, n2) + "\n");
    }

    System.out.println(out);
    scanner.close();
  }
}