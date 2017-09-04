import java.util.*;

public class Solution {

    long MAX = 1000000000;

    static class Node {
 int id;

 Map<Node, Integer> in;
 Map<Node, Integer> out;

 boolean src = false;
 boolean dst = false;
 
 long ways = 0;

 Node(int i) {
     id = i;
     in = new HashMap<Node, Integer>();
     out = new HashMap<Node, Integer>();
 }

 boolean valid() {
     return (src && dst);
 }
    }

    void bfs(Node[] nodes, Node start, boolean forward) {
 ArrayDeque<Node> S = new ArrayDeque<Node>();
 S.add(start);
 start.src = true;
 start.dst = true;
 Node curr;
 while (!S.isEmpty()) {
     curr = S.remove();
     if (forward) {
  for (Node s : curr.out.keySet()) {
      if (!s.src) {
   S.add(s);
   s.src = true;
      }
  }
     } else {
  for (Node s : curr.in.keySet()) {
      if (!s.dst) {
   S.add(s);
   s.dst = true;
      }
  }
     }
 }
    }

    int label(Node[] nodes, int size) {
 //forward
 bfs(nodes, nodes[1], true);
 bfs(nodes, nodes[size-1], false);
 int valid_count = 0;
 for (int i = 1; i < size; i++) {
     if (!nodes[i].valid()) {
  for (Node other : nodes[i].out.keySet()) {
      other.in.remove(nodes[i]);
  }
  for (Node other : nodes[i].in.keySet()) {
      other.out.remove(nodes[i]);
  }
  nodes[i].in.clear();
  nodes[i].out.clear();
     } else {
  valid_count++;
     }
 }
 return valid_count;
    }

    void ways(Node[] nodes) {
 int size = nodes.length;
 int valid_count = label(nodes, size);

 ArrayDeque<Node> S = new ArrayDeque<Node>();
 for (int i = 1; i < size; i++) {
     if (nodes[i].valid() && nodes[i].in.isEmpty()) {
  S.add(nodes[i]);
     }
 }
 if (S.isEmpty()) {
     System.out.println("INFINITE PATHS");
     return;
 }
 int count = 0;
 Node curr;
 while (!S.isEmpty()) {
     count++;
     curr = S.remove();
     for (Node x : curr.out.keySet()) {
  x.ways = (x.ways + curr.ways * curr.out.get(x)) % MAX;
  x.in.remove(curr);
  if (x.in.isEmpty()) {
      S.add(x);
  }
     }
     curr.out.clear();
 }

 if (count < valid_count) {
     System.out.println("INFINITE PATHS");     
 } else {
     System.out.println(nodes[size-1].ways);
 }
    }

    public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 int N = sc.nextInt();
 int M = sc.nextInt();
 Node[] nodes = new Node[N+1];
 for (int i = 1; i <= N; i++) {
     nodes[i] = new Node(i);
 }
 nodes[1].ways = 1;
 int n, m;
 Integer count;
 for (int i = 0; i < M; i++) {
     n = sc.nextInt();
     m = sc.nextInt();

     count = nodes[n].out.get(nodes[m]);
     if (count == null)
  count = new Integer(0);
     count++;
     nodes[n].out.put(nodes[m], count);

     count = nodes[n].in.get(nodes[n]);
     if (count == null)
  count = new Integer(0);
     count++;
     nodes[m].in.put(nodes[n], count);
 }

 Solution S = new Solution();
 S.ways(nodes);
    }
}