import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
        private static List<Set<Node>> sets;

    private static class Node {
        int number;
        List<Node> links;
        Set<Node> set;

        public Node(int number) {
            this.number = number;
            links = new ArrayList<Node>();
        }

        public void setContainingSet(Set<Node> set) {
            this.set = set;
        }

        public Set<Node> getContainingSet() {
            return set;
        }
    }

    private static void executeCommand(char code, int data1, int data2) {
        switch (code) {
            case 'A':
                Set<Node> set = new HashSet<Node>();
                for (int index = 0; index < data1; index++) {
                    Node node = new Node(index);
                    set.add(node);
                    node.setContainingSet(set);
                }
                sets.add(set);
                break;
            case 'B':
                Set<Node> set1 = sets.get(data1 - 1);
                Set<Node> set2 = sets.get(data2 - 1);
                for (Node node : set1) {
                    for (Node node2 : set2) {
                        node.links.add(node2);
                        node2.links.add(node);
                    }
                }
                break;
            case 'C':
                Set<Node> targetSet = sets.get(data1 - 1);
                Set<Node> newSet = new HashSet<Node>();
                for (Node node : targetSet) {
                    addNode(node, newSet);
                }
                sets.add(newSet);
        }

    }

    private static void addNode(Node node, Set<Node> set) {
        if (set.contains(node)) {
            return;
        }
        node.getContainingSet().remove(node);
        set.add(node);
        node.setContainingSet(set);
        if (node.links != null) {
            for (Node link : node.links) {
                addNode(link, set);
            }
        }
    }

    private static int getIndependentNodes() {
        int totalNodes = 0;
        for (Set<Node> set : sets) {
            for (Node node : set) {
                for (Node node1 : set) {
                    if (node != node1 && !node.links.contains(node1)) {
                        totalNodes++;
                    }
                }
            }
        }
        return totalNodes / 2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long q = scanner.nextLong();
        scanner.nextLine();
        sets = new ArrayList<Set<Node>>();
        for (long index = 0; index < q; index++) {
            String line = scanner.nextLine();
            String[] command = line.split(" ");
            char code = command[0].charAt(0);
            int data1 = Integer.valueOf(command[1]);
            int data2 = 0;
            if (command.length > 2) {
                data2 = Integer.valueOf(command[2]);
            }
            executeCommand(code, data1, data2);
        }
        System.out.println(getIndependentNodes());
    }
}