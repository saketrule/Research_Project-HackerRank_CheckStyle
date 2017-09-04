import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int numOps = scan.nextInt();
        char [] operation = new char [numOps];
        ArrayList <Integer> nums = new ArrayList <Integer> ();
        for (int i = 0; i < numOps; i++) {
            String s = scan.next();
            operation[i] = s.charAt(0);
            nums.add(scan.nextInt());
            if (operation[i] == 'B')
                nums.add(scan.nextInt());
        }
        int bOps = 1;
        ArrayList <NodeSet> sets = new ArrayList <NodeSet>();
        for (int i = 0; i< numOps; i++) {
            if (operation[i] == 'A') {
                NodeSet newSet = new NodeSet();
                int addNodes = nums.get(0);
                nums.remove(0);
                for (int g = 0; g < nums.get(0); g++) {
                    node a_node = new node();
                    newSet.set.add(a_node);
                }
                sets.add(newSet);
            }
            else if (operation[i] == 'B') {
                int from = nums.get(0)-1;
                int to = nums.get(1)-1;
                nums.remove(0); nums.remove(0);
                for (int g = 0; g < sets.get(from).set.size(); g++) {
                    for (int m = 0; m < sets.get(to).set.size();m++) {
                        sets.get(from).set.get(g).addEdge(sets.get(to).set.get(m));
                        sets.get(to).set.get(m).addEdge(sets.get(from).set.get(g));
                    }
                }
                
            }
            else {
                int base = nums.get(0)-1;
                nums.remove(0);
                NodeSet encap = new NodeSet();
                for (int g = 0; g < sets.get(base).set.size();g++) {
                    encap = addRecursiveNodes(sets.get(base),sets.get(base).set.get(g));
                    encap.set = new ArrayList<>(new HashSet<>(encap.set));
                }
                sets.add(encap);
            }
        }
        ArrayList<node> nod = getNodes(sets);
        System.out.println(findMaxIndNodes(new node(),nod)-1);
        
    }
    
    public static int findMaxIndNodes(node parent,ArrayList <node> set_coll){
        if (parent.hasBeenVisited)
            return 0;
        int temp_max1 = 0;
        int temp_max2 = 0;
        for (int i = 0; i < set_coll.size(); i++) {
            node IndNode = set_coll.get(i);
            if (parent == null || parent.isInd == false) {
                IndNode.isInd = true;
                IndNode.hasBeenVisited = true;
                temp_max1++;
                temp_max1+=findMaxIndNodes(IndNode, IndNode.edges);
            }
            IndNode.isInd = false;
            if (parent == null || parent.isInd == true || parent.isInd == false) {
                IndNode.isInd = false;
                IndNode.hasBeenVisited = true;
                temp_max2+=findMaxIndNodes(IndNode,IndNode.edges);
            }
        }
        if (temp_max1 > temp_max2)
            return temp_max1;
        else
            return temp_max2;
    }
    //area where problems may arise
    public static ArrayList<node> getNodes(ArrayList<NodeSet> sets) {
        NodeSet all = new NodeSet();
        for (int i = 0; i < sets.get(0).set.size();i++) {
            all = addRecursiveNodes(sets.get(0),sets.get(0).set.get(i));
            all.set = new ArrayList<>(new HashSet<>(all.set));
        }
        return all.set;
    }
    
    
    
    public static NodeSet addRecursiveNodes(NodeSet full_set, node n) {
        if (n.hasBeenVisited)
            return full_set;
        for (int i = 0; i < n.edges.size(); i++) {
            full_set.set.add(n.edges.get(i));
            n.edges.get(i).hasBeenVisited=true;
            full_set = addRecursiveNodes(full_set, n.edges.get(i));
        }
        return full_set;
    }
    
}

class NodeSet {
    public ArrayList <node> set;
    public NodeSet() {
        set = new ArrayList<node>();
    }
}

class node {
    public ArrayList <node> edges;
    public boolean isInd;
    public boolean hasBeenVisited;
    public node() {
        edges = new ArrayList<node>();
        isInd = false;
        hasBeenVisited = false;
    }
    public void addEdge (node no) {
        if (!edges.contains(no))
            edges.add(no);
    }
}