import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static final BigInteger INFINITY = new BigInteger("2").pow(200001);
    private static class Node implements Comparable<Node> {
        int name;
        BigInteger dist = INFINITY;

        @Override
        public String toString() {
            return "Node{" +
                    "name=" + name +
                    ", edges=" + edges +
                    '}';
        }

        List<Edge> edges = new ArrayList<>();

        @Override
        public int compareTo(Node o) {
            return dist.compareTo(o.dist);
        }
    }

    private static class Edge {
        BigInteger weight;
        Node node1;
        Node node2;
    }

    static Map<Integer, Node> nodeMap = new HashMap<>();
    static Map<String, BigInteger> distanceMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            Node node = new Node();
            node.name = i;
            nodeMap.put(i, node);
        }

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            BigInteger r = new BigInteger("2").pow(scanner.nextInt());
            Node xNode = nodeMap.get(x);
            Node yNode = nodeMap.get(y);
            Edge edge = new Edge();
            edge.node1 = xNode;
            edge.node2 = yNode;
            edge.weight = r;
            xNode.edges.add(edge);
            Edge edge2 = new Edge();
            edge2.node2 = xNode;
            edge2.node1 = yNode;
            edge2.weight = r;
            yNode.edges.add(edge2);
        }

        for (int i = 1; i <= n; i++) {
            Node startNode = nodeMap.get(i);
            startNode.dist = BigInteger.ZERO;
            getShortestRoutes(startNode);
            for (int k = 1; k <= n; k++) {
                distanceMap.put(i + ":" + k, nodeMap.get(k).dist);
                nodeMap.get(k).dist = INFINITY;
            }
        }

        BigInteger total = BigInteger.ZERO;
        for (int i = 1; i <= n ; i++) {
            for (int j = i+1; j <= n; j++) {
                if (distanceMap.get(i+":"+j)!=null) {
                    total = total.add(distanceMap.get(i + ":" + j));
                }
            }
        }

        System.out.println(total.toString(2));
    }

    static PriorityQueue<Node> nodes = new PriorityQueue<>();

    private static void getShortestRoutes(Node startNode) {
        if (startNode == null) {
            return;
        }

        List<Edge> edges = startNode.edges;
        for (Edge edge : edges) {
            BigInteger sum = startNode.dist.add(edge.weight);
            if (edge.node2.dist.compareTo(sum) > 0) {
                edge.node2.dist = startNode.dist.add(edge.weight);
                nodes.add(edge.node2);
            }
        }
        getShortestRoutes(nodes.poll());
    }
}