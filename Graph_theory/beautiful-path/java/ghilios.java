import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private final int numNodes;
    private final Edge[] edges;
    private final List<Edge>[] edgesPerNode;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int[] firstLine = parseNumbers(sc.nextLine(), 2);
        final int numNodes = firstLine[0];
        final int numEdges = firstLine[1];
        final Edge[] edges = new Edge[numEdges];
        for (int i = 0; i < numEdges; ++i) {
            final int[] parsedEdgeNumbers = parseNumbers(sc.nextLine(), 3);
            final Edge edge = new Edge(parsedEdgeNumbers[0] - 1, parsedEdgeNumbers[1] - 1, parsedEdgeNumbers[2]);
            edges[i] = edge;
        }
        final int[] lastLine = parseNumbers(sc.nextLine(), 2);
        final int startNode = lastLine[0] - 1;
        final int endNode = lastLine[1] - 1;
        final Solution solution = new Solution(numNodes, edges);
        System.out.println(solution.minPenalty(startNode, endNode));
    }

    public Solution(final int numNodes,
                    final Edge[] edges) {
        this.numNodes = numNodes;
        this.edges = edges;
        this.edgesPerNode = new List[numNodes];
        for (int i = 0; i < numNodes; ++i) {
            this.edgesPerNode[i] = new LinkedList<>();
        }
        for (final Edge edge: edges) {
            this.edgesPerNode[edge.from].add(edge);
            this.edgesPerNode[edge.to].add(edge);
        }
    }

    public int minPenalty(final int startNode, final int endNode) {
        final int[] minPenaltyPerNode = new int[numNodes];
        for (int i = 0; i < numNodes; ++i) {
            minPenaltyPerNode[i] = Integer.MAX_VALUE;
        }

        final boolean[] visited = new boolean[1024 * 1000 + 1000];
        minPenaltyPerNode[startNode] = 0;
        final Queue<BfsNode> bfsQueue = new ArrayDeque<>();
        for (final Edge startEdge : edgesPerNode[startNode]) {
            bfsQueue.add(new BfsNode(0, startEdge, other(startEdge, startNode)));
        }
        //final HashSet<VisitedNode> visited = new HashSet<>(edges.length);
        visited[visitedIndex(startNode, 0)] = true;
        while (!bfsQueue.isEmpty()) {
            final BfsNode nextBfsNode = bfsQueue.poll();
            final int nextPenalty = nextBfsNode.startPenalty | nextBfsNode.edge.cost;
            final int visitedIndex = visitedIndex(nextBfsNode.to, nextPenalty);
            // final VisitedNode nextVisitedNode = new VisitedNode(nextPenalty, nextBfsNode.to);
            /*
            if (!visited.add(nextVisitedNode)) {
                continue;
            }
            */
            if (visited[visitedIndex]) {
                continue;
            }
            visited[visitedIndex] = true;

            minPenaltyPerNode[nextBfsNode.to] = Math.min(minPenaltyPerNode[nextBfsNode.to], nextPenalty);
            for (final Edge startEdge : edgesPerNode[nextBfsNode.to]) {
                if (!visited[visitedIndex(other(startEdge, nextBfsNode.to), nextPenalty | startEdge.cost)]) {
                    bfsQueue.add(new BfsNode(nextPenalty, startEdge, other(startEdge, nextBfsNode.to)));
                }
            }
        }

        if (minPenaltyPerNode[endNode] == Integer.MAX_VALUE) {
            return -1;
        }
        return minPenaltyPerNode[endNode];
    }

    private static int visitedIndex(final int node, final int penalty) {
        return penalty * 1000 + node;
    }

    private static int other(final Edge edge, final int from) {
        return edge.from == from ? edge.to : edge.from;
    }

    private static class BfsNode {
        public final int startPenalty;
        public final Edge edge;
        public final int to;
        public BfsNode(final int startPenalty, final Edge edge, final int to) {
            this.startPenalty = startPenalty;
            this.edge = edge;
            this.to = to;
        }
    }

    private static class VisitedNode {
        public final int node;
        public final int penalty;
        public VisitedNode(final int node,
                           final int penalty) {
            this.node = node;
            this.penalty = penalty;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof VisitedNode)) return false;
            final VisitedNode that = (VisitedNode) o;
            return node == that.node &&
                    penalty == that.penalty;
        }

        @Override
        public int hashCode() {
            return Objects.hash(node, penalty);
        }
    }

    private static class Edge {
        public final int from;
        public final int to;
        public final int cost;
        public Edge(final int from, final int to, final int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private static int[] parseNumbers(final String line, final int size) {
        final String[] args = line.split(" ");
        final int[] nums = new int[size];
        for (int i = 0; i < size; ++i) {
            nums[i] = Integer.parseInt(args[i]);
        }
        return nums;
    }
}