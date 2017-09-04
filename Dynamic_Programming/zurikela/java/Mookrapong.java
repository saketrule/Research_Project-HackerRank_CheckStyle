import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node {
 HashSet<Node> set;
    HashSet<Node> nodes;
    public Node() {
        this.nodes = new HashSet<Node>();
    }
    
    public void connect(Node n) {
     this.nodes.add(n);
     n.nodes.add(this);
    }
    
    public void addToSet(HashSet<Node> set) {
     if (this.set != null) {
      this.set.remove(this);
     }
     set.add(this);
  this.set = set;
    }
}

public class Solution {
 public static long solve(ArrayList<Node> nodes, int idx, HashSet<Node> independentSet) {
  if (idx >= nodes.size()) {
   return independentSet.size();
  }
  
  long max = solve(nodes, idx+1, independentSet);
  
  boolean isIndependentNode = true;
  Node node = nodes.get(idx);
  for (Node n : node.nodes) {
   if (independentSet.contains(n)) {
    isIndependentNode = false;
    break;
   }
  }
  if (isIndependentNode) {
   independentSet.add(node);
   long temp = solve(nodes, idx+1, independentSet);
   independentSet.remove(node);
   if (temp > max) {
    max = temp;
   }
  }
  
  return max;
 }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int Q = in.nextInt();
        int x, y;
        in.nextLine();
        String[] opt;
        ArrayList<Node> nodes = new ArrayList<Node>();
        ArrayList<HashSet<Node>> sets = new ArrayList<HashSet<Node>>();
        HashSet<Node> set1, set2;
        Node node;
        LinkedList<Node> list = new LinkedList<Node>();
        for (int i = 0; i < Q; i++) {
            opt = in.nextLine().split(" ");
            if (opt[0].equalsIgnoreCase("A")) {
                set1 = new HashSet<Node>();
                x = Integer.parseInt(opt[1]);
                for (int j = 0; j < x; j++) {
                 node = new Node();
                 nodes.add(node);
                    set1.add(node);
                    node.addToSet(set1);
                }
                sets.add(set1);
            } else if (opt[0].equalsIgnoreCase("B")) {
                x = Integer.parseInt(opt[1]) - 1;
                y = Integer.parseInt(opt[2]) - 1;
                set1 = sets.get(x);
                set2 = sets.get(y);
                for (Node n1 : set1) {
                 for (Node n2 : set2) {
                  n1.connect(n2);
                 }
                }
            } else if (opt[0].equalsIgnoreCase("C")) {
                x = Integer.parseInt(opt[1]) - 1;
                set1 = new HashSet<Node>();
                sets.add(set1);
                
                set2 = sets.get(x);
                list.addAll(set2);
                while (list.size() > 0) {
                 node = list.poll();
                 node.addToSet(set1);
                 for (Node n : node.nodes) {
                  if (n.set != set1) {
                   list.add(n);
                  }
                 }
                }
            }
        }
        
        long count = solve(nodes, 0, new HashSet<Node>());
        System.out.println(count);
        
        in.close();
    }
}