import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static class Edge {
        int id;
        BigInteger cost;
    }
    static class Node implements Comparable<Node>{
        int id;
        ArrayList<Edge> edges = new ArrayList<>();
        BigInteger cost;
        public int compareTo(Node node){
            if(cost == null || node.cost == null)
                return 0;
            return cost.compareTo(node.cost);
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int e = s.nextInt();
        Node[] nodes = new Node[n];
        for (int i = 0;i<n;i++) {
            nodes[i] = new Node();
            nodes[i].id = i;
        }
        for (int j = 0;j<e;j++) {
            int a = s.nextInt() - 1;
            int b = s.nextInt() - 1;
            int c = s.nextInt();
            Edge e1 = new Edge();
            e1.id = b;
            BigInteger temp = new BigInteger("0");
            e1.cost = temp.setBit(c);
            nodes[a].edges.add(e1);
            e1 = new Edge();
            e1.id = a;
            temp = new BigInteger("0");
            e1.cost = temp.setBit(c);
            nodes[b].edges.add(e1);        
        }
        BigInteger sum = new BigInteger("0");
        for(int i = 0;i<n;i++){
            for (int j = 0;j<n;j++) {
                nodes[j].cost = null;
            }
            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.add(nodes[i]);
            nodes[i].cost = new BigInteger("0");
            while (!queue.isEmpty()) {
                Node node = queue.remove();
                if(node.id > i)
                    sum = sum.add(node.cost);
                for (Edge ed : node.edges) {
                    if(nodes[ed.id].cost == null || nodes[ed.id].cost.compareTo(node.cost.add(ed.cost)) > 0){
                        queue.remove(nodes[ed.id]);
                        nodes[ed.id].cost = node.cost.add(ed.cost);
                        queue.add(nodes[ed.id]);
                    }
                }
            }
        }
        
        System.out.println(sum.toString(2));
    }
}