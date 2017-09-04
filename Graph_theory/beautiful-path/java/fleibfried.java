import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static class Node {
        public int index;
        public ArrayList<Node> neighbours = new ArrayList<Node>();
        public ArrayList<Integer> weights = new ArrayList<Integer>();
        public Node(int index) {
            this.index = index;
        }
        public void print() {
            System.out.println("Index: "+(this.index+1));
            System.out.print("Neigbours: ");
            for (int i=0; i<this.neighbours.size(); i++) System.out.print((this.neighbours.get(i).index+1)+" ");
            System.out.println();
            System.out.print("Weights: ");
            for (int i=0; i<this.weights.size(); i++) System.out.print(this.weights.get(i)+" ");
            System.out.println();
        }
    }
    
    public static int minPenPath(Node[] nodes, int a, int b) {
        HashMap<String,Boolean> hm = new HashMap<String,Boolean>();
        hm.put(a+"-"+0,true);
        for (int i=0; i<nodes.length; i++) {
            //long seed = System.nanoTime();
            //Collections.shuffle(nodes[i].neighbours, new Random(seed));
            //Collections.shuffle(nodes[i].weights, new Random(seed));
            if (i!=a) hm.put(i+"-"+0,false);
        }
        HashSet<String> hs = new HashSet<String>();
        for (int i=0; i<1024; i++) {
            if (minPenPathBool(nodes, b,  i, hm, hs)) return i;
        }
        return -1;
    }
    
    public static boolean minPenPathBool(Node[] nodes, int node, int c, HashMap<String,Boolean> hm, HashSet<String> hs) {       
        String key = node+"-"+c;
        hs.add(key);
        if (hm.containsKey(key)) return hm.get(key);
        Node nodeObj = nodes[node];
        for (int i=0; i<nodeObj.neighbours.size(); i++) {
            Node neib = nodeObj.neighbours.get(i);
            int weight = nodeObj.weights.get(i);
            if (weight<=c) {
                for (int j=0; j<=c; j++) {
                    int potc = j|weight;
                    if (potc==c) {
                        String subKey = neib.index+"-"+j;
                        if (!hs.contains(subKey)) {
                            boolean flag = minPenPathBool(nodes, neib.index, j, hm, hs);
                            hm.put(subKey,flag);
                            if (flag) {
                                hm.put(key,true);
                                return true;
                            }
                        }                    
                    }
                }
            }            
        }
        hm.put(key,false);
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Node[] nodes = new Node[n];
        for (int i=0; i<n; i++) {
            nodes[i] = new Node(i);
        }
        for (int i=0; i<m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int c = scanner.nextInt();
            nodes[u-1].neighbours.add(nodes[v-1]);
            nodes[u-1].weights.add(c);
            nodes[v-1].neighbours.add(nodes[u-1]);
            nodes[v-1].weights.add(c);
        }
        //for (int i=0; i<n; i++) nodes[i].print();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(minPenPath(nodes, a-1, b-1));
    }
    
}