import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static class SetK {
        public ArrayList<Node> nodes;
        public int id;
        public static int index = 1;
        
        public SetK(int nb) {
            nodes = new ArrayList<>();
            for (int i = 0; i < nb; i++) {
                nodes.add(new Node(this));
            }
            id = index++;
        }
        
        public void link(SetK y) {
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < y.nodes.size(); j++) {
                    nodes.get(i).link(y.nodes.get(j));
                }
            }
        }
        
        public SetK merge() {
            SetK s = new SetK(0);
            for (int k = this.nodes.size() - 1; k >= 0; k--) {
                Node nnnn = this.nodes.remove(k);
                nnnn.s = s;
                s.nodes.add(nnnn);
            }
            boolean has_changed = true;
            while (has_changed) {
                has_changed = false;
                for (int i = s.nodes.size() - 1; i >= 0; i--) {
                    Node node = s.nodes.get(i);
                    for (int j = node.nodes.size() - 1; j >= 0; j--) {
                        Node node_2 = node.nodes.get(j);
                        if (node_2.s.id != s.id) {
                            has_changed = true;
                            for (int k = node_2.s.nodes.size() - 1; k >= 0; k--) {
                                Node nnnn = node_2.s.nodes.remove(k);
                                nnnn.s = s;
                                s.nodes.add(nnnn);
                            }
                        }
                    }
                }
            }
            return s;
        }
        
        public String toString() {
            return id + "  " + nodes;
        }
    }
    
    public static class Node {
        public ArrayList<Node> nodes;
        public int id;
        public int dependent;
        public boolean independent;
        public static int index = 1;
        public SetK s;
        
        public Node(SetK s) {
            nodes = new ArrayList<>();
            this.independent = false;
            this.id = index++;
            this.s = s;
            this.dependent = 0;
        }
        
        public void link(Node n) {
            nodes.add(n);
            dependent++;
            n.nodes.add(this);
            n.dependent++;
        }
        
        public String toString() {
            return id + " dep:" + dependent + " ind:" + independent;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<SetK> sets = new ArrayList<>();
        in.nextLine();
        for (int i = 0; i < n; i++) {
            String line[] = in.nextLine().split(" ");
            switch (line[0]) {
                case "A":
                    sets.add(new SetK(Integer.parseInt(line[1])));
                break;
                case "B":
                    sets.get(Integer.parseInt(line[1])-1).link(sets.get(Integer.parseInt(line[2])-1));
                break;
                case "C":
                    sets.add(sets.get(Integer.parseInt(line[1])-1).merge());
                break;
            }
        }
        
        for (SetK s : sets) {
            if (s.nodes.size() == 0) continue;
            int min = s.nodes.get(0).dependent;
            for (Node node : s.nodes) {
                if (node.dependent < min) min = node.dependent;
            }
            for (Node node: s.nodes) {
                if (node.dependent == min) node.independent = true;
            }
        }
        
        for (SetK s : sets) {
            if (s.nodes.size() == 0) continue;
            for (Node node : s.nodes) {
                if (node.independent) {
                    for (Node nn : node.nodes) {
                        if (nn.independent) {
                            if (nn.dependent < node.dependent) {
                                node.independent = false;
                            } else {
                                nn.independent = false;
                            }
                        }
                    }
                }
            }
        }
        int count = 0;
        for (SetK s : sets) {
            if (s.nodes.size() == 0) continue;
            for (Node node : s.nodes) {
                if (node.independent) count++;
            }
        }
        System.out.println(count);
    }
}