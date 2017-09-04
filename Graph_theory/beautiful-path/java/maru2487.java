import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static class Node {
        int name;
        int distance  = Integer.MAX_VALUE;
        List<Edge> edges = new ArrayList<>();
    }

    private static class Edge {
        Node node1;
        Node node2;
        int weight;
    }

    private static Map<Integer, Node> nodeMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        for (int i = 1; i <= n ; i++) {
            Node node = new Node();
            node.name = i;
            nodeMap.put(i,node);
        }

        for (int i = 0; i < m; i++) {
            Node n1 = nodeMap.get(scanner.nextInt());
            Node n2 = nodeMap.get(scanner.nextInt());
            int wt = scanner.nextInt();
            Edge edge = new Edge();
            edge.weight = wt;
            edge.node1 = n1;
            edge.node2 = n2;
            n1.edges.add(edge);

            Edge edge1 = new Edge();
            edge1.weight = wt;
            edge1.node1 = n2;
            edge1.node2 = n1;
            n2.edges.add(edge1);
        }


        Node startNode = nodeMap.get(scanner.nextInt());
        startNode.distance = 0;
        for (int i = 0; i < m; i++) {
            findShortest(startNode);
        }
        Node destination = nodeMap.get(scanner.nextInt());
        System.out.println(destination.distance == Integer.MAX_VALUE ? -1 : destination.distance);
    }

    static LinkedList<Node> queue = new LinkedList<>();
    private static void findShortest(Node startNode) {
        if (startNode == null) {
            return;
        }

        List<Edge> edges = startNode.edges;
        for (Edge edge : edges) {
            if (edge.node2.distance > (startNode.distance | edge.weight)) {
                edge.node2.distance = (startNode.distance | edge.weight);
                queue.add(edge.node2);
            }
        }
        findShortest(queue.poll());
    }
}