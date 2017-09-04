import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by cristian.zamfirescu on 29/04/16.
 * <p/>
 * https://www.hackerrank.com/challenges/rust-murderer
 */
public class Solution {

    private static class Node implements Comparable {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Object o) {
            return cost - ((Node) o).cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 128 * 1024);
        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            String[] line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);

            HashMap<Integer, HashSet<Integer>> bigRoads = new HashMap<Integer, HashSet<Integer>>();

            for (int i = 0; i < m; i++) {
                line = br.readLine().split(" ");
                int x = Integer.parseInt(line[0]);
                int y = Integer.parseInt(line[1]);

                HashSet<Integer> neighbours = bigRoads.get(x);
                if (neighbours == null) {
                    neighbours = new HashSet<Integer>();
                    bigRoads.put(x, neighbours);
                }
                neighbours.add(y);

                neighbours = bigRoads.get(y);
                if (neighbours == null) {
                    neighbours = new HashSet<Integer>();
                    bigRoads.put(y, neighbours);
                }
                neighbours.add(x);
            }

            int start = Integer.parseInt(br.readLine());

            PriorityQueue<Node> pending = new PriorityQueue<Node>();
            pending.add(new Node(start, 0));
            HashSet<Integer> usedNodes = new HashSet<Integer>();
            usedNodes.add(start);

            int[] distance = new int[n + 1];
            for (int i = 0; i < distance.length; i++) {
                distance[i] = Integer.MAX_VALUE;
            }

            while (!pending.isEmpty() && usedNodes.size() != n) {
                Node current = pending.poll();

                for (int i = 1; i <= n; i++) {
                    if (i != current.node && !usedNodes.contains(i) && (bigRoads.get(current.node) == null || !bigRoads.get(current.node).contains(i))) {
                        int cost = current.cost + 1;
                        pending.add(new Node(i, cost));
                        distance[i] = Math.min(distance[i], cost);
                        usedNodes.add(i);
                    }
                }
            }
            for (int i = 1; i < distance.length; i++) {
                if (i != start) {
                    System.out.print(distance[i] + " ");
                }
            }
            System.out.println();            
        }
    }
}