import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    static int vertexCount = -1;
    static int edgeCount = -1;
    public static void main(String[] args) throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        String[] array = bfr.readLine().trim().split(" ");

        vertexCount = Integer.parseInt(array[0]);
        edgeCount = Integer.parseInt(array[1]);

        output = new int[vertexCount + 1][1024];

        for (int i = 0; i <= vertexCount; i++)
            for (int j = 0; j < 1024; j++)
                output[i][j] = 0;

        for (int i = 0; i <= vertexCount; i++) {
            vertices.add(new HashSet<Edge>());
        }

        for (int i = 0; i < edgeCount; i++) {
            String[] earray = bfr.readLine().trim().split(" ");
            int v1 = Integer.parseInt(earray[0]);
            int v2 = Integer.parseInt(earray[1]);

            if (v1 == v2) {
                continue;
            }
            int p = Integer.parseInt(earray[2]);

            Edge e = new Edge(v1, v2, p);
            Edge e2 = new Edge(v2, v1, p);

            if (!allEdges.contains(e) && !allEdges.contains(e2)) {
                vertices.get(v1).add(e);
                vertices.get(v2).add(e);
                allEdges.add(e);
            }
        }

        array = bfr.readLine().trim().split(" ");

        int source = Integer.parseInt(array[0]);
        int dest = Integer.parseInt(array[1]);

        int minPenalty = dfs(source, dest);

        if (minPenalty == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minPenalty);
        }

        bfr.close();
    }

    static List<Set<Edge>> vertices = new ArrayList<>();

    static Set<Edge> allEdges = new HashSet<>();

    static class Edge {
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + penalty;
            result = prime * result + v1;
            result = prime * result + v2;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Edge other = (Edge) obj;
            if (penalty != other.penalty)
                return false;
            if (v1 != other.v1)
                return false;
            if (v2 != other.v2)
                return false;
            return true;
        }

        public Edge(int v1, int v2, int penalty) {
            super();
            this.v1 = v1;
            this.v2 = v2;
            this.penalty = penalty;
        }

        int v1;
        int v2;
        int penalty;

        int other(int v) {
            if (v == v1) {
                return v2;
            } else {
                return v1;
            }
        }
    }

    static int minPenalty = Integer.MAX_VALUE;
    static int dfs(int source, int destination) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        AtomicInteger penalty = new AtomicInteger(0);
        dfsRecurse(source, destination, visited, stack, penalty);
        return minPenalty;
    }

    static int[][] output = null;

    static void dfsRecurse(int curNode, int destination, Set<Integer> visited, Stack<Integer> stack,
            AtomicInteger penalty) {

        if (curNode == destination) {
            if (penalty.get() < minPenalty) {
                minPenalty = penalty.get();
            }
            return;
        }

        visited.add(curNode);
        stack.push(curNode);
        int curPenalty = penalty.get();

        int minimumPenalty = curPenalty;
        for (Edge edge : vertices.get(curNode)) {
            int otherNode = edge.other(curNode);
            if (!visited.contains(otherNode)) {
                int newPenalty = penalty.get() | edge.penalty;
                penalty.set(newPenalty);

                if (output[otherNode][penalty.get()] == 0) {
                    dfsRecurse(otherNode, destination, visited, stack, penalty);
                } else {
                    penalty.set(output[otherNode][penalty.get()]);
                }

                if (penalty.get() < minimumPenalty)
                    minimumPenalty = penalty.get();
            }
            penalty.set(curPenalty);
        }

        output[curNode][penalty.get()] = minimumPenalty;

        stack.pop();
        visited.remove(curNode);
    }
}