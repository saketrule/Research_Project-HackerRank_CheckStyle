import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static void complement(int n, int[][] graph)
    {
        for (int i = 0; i != n; ++i) {
            for (int j = i + 1; j != n; ++j) {
                graph[i][j] = (graph[i][j] - 1) * (-1);
                graph[j][i] = (graph[j][i] - 1) * (-1);
            }
        }
    }

    private static boolean isComplete(int[][] graph, int[] vertices) {
        for (int i = 0; i != vertices.length; ++i) {
            for (int j = 0; j != vertices.length; ++j) {
                if (i == j) {
                    continue;
                }

                if (graph[vertices[i]][vertices[j]] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int sum(int[][] graph, int[] weights, int[] vertices) {
        int result = 0;

        for (int i = 0; i != vertices.length; ++i) {
            result += weights[vertices[i]];
        }

        return result;
    }

    private static boolean isSet(long mask, int vertex) {
        return (mask & (1L << vertex)) != 0;
    }

    private static long set(long mask, int vertex) {
        return mask | (1L << vertex);
    }

    private static int[] makeClick(int[][] graph, int[] weights, int vertex, long click, long skip, int sum) {
        sum += weights[vertex];

        int[] result = new int[]{sum, 1};

        long currentClick;

        for (int i = 0; i != graph.length; ++i) {
            if (isSet(skip, i) || isSet(click, i)) {
                continue;
            }

            currentClick = click;

            if (graph[i][vertex] == 1) {
                boolean skipVertex = false;

                for (int j = 0; j != graph.length; ++j) {
                    if (!isSet(click, j)) {
                        continue;
                    }

                    if (graph[j][i] == 0) {
                        skipVertex = true;
                        break;
                    }
                }

                if (skipVertex) {
                    continue;
                }

                currentClick = set(currentClick, i);
                int[] currentResult = makeClick(graph, weights, i, currentClick, skip, sum);
                skip = set(skip, i);

                if (currentResult[0] == result[0]) {
                    result[1] += currentResult[1];
                } else if (currentResult[0] > result[0]) {
                    result = currentResult;
                }
            }
        }

        return result;
    }

    private static int[] calc(int[][] graph, int[] weights) {
        int n = graph.length;

        long skip = 0;
        long click;

        int[] result = new int[] {0,1};

        for (int i = 0; i != n; ++i) {
            click = 0;

            click = set(click, i);

            int[] current = makeClick(graph, weights, i, click, skip, 0);

            skip = set(skip, i);

            if (current[0] == result[0]) {
                result[1] += current[1];
            } else if (current[0] > result[0]) {
                result = current;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        int[] c = new int[n];
        for (int i = 0; i != n; ++i) {
            c[i] = in.nextInt();
        }

        int[][] graph = new int[n][n];

        for (int i = 0; i != m; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        complement(n, graph);

        int[] result = calc(graph, c);

        System.out.println(result[0] + " " + result[1]);
    }
}