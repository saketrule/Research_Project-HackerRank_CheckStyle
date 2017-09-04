import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static String halfBinary(String s) {
        StringBuilder ss = new StringBuilder(s);
        if (s.length() == 0) return s;
        ss.deleteCharAt(ss.length() - 1);
        return ss.toString();
    }
    
    public static String addBinaries(String a, String b) {
        if (a.length() == 0) return b;
        if (b.length() == 0) return a;
        
        StringBuilder s = new StringBuilder();
        if (a.length() < b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        
        int carry = 0;
        int i = a.length() - 1, 
            j = b.length() - 1;
        for (; j >= 0; i--, j--) {
            int result = carry + (a.charAt(i) - '0') + (b.charAt(j) - '0');
            s.append(result % 2);
            carry = result / 2;
        }
        
        for (; i >= 0; i--) {
            int result = carry + (a.charAt(i) - '0');
            s.append(result % 2);
            carry = result / 2;
        }
        
        if (carry != 0) s.append(carry);
        
        return s.reverse().toString();
    }
    
    private static String addBinaries(String s, NavigableSet<Integer> ss) {
        int size = ss.size();
        if (size == 0) return s;
        
        Iterator<Integer> it = ss.descendingIterator();
        StringBuilder buffer = new StringBuilder();
        
        int k = it.next();
        int num = k;
        
        while (k > 0) {
            if (k == num) { 
                if (it.hasNext()) num = it.next();
                buffer.append(1); 
            } else buffer.append(0);
            --k;
        }
        
        return addBinaries(s, buffer.toString());
    }
    
    private static class Graph {
        
        private static class Vertex implements Comparable<Vertex> {
            public int label;
            public boolean hasPath;
            public NavigableSet<Integer> set;

            public Vertex(int label) {
                this.label = label;
                this.hasPath = false;
                this.set = new TreeSet<>();
            }

            private int compare(NavigableSet<Integer> set) {
                Iterator<Integer> it1 = this.set.descendingIterator();
                Iterator<Integer> it2 = set.descendingIterator();
                
                while (it1.hasNext() && it2.hasNext()) {
                    int num1 = it1.next(), num2 = it2.next();
                    if      (num1 > num2) return  1;
                    else if (num1 < num2) return -1;
                    else                  continue;
                }
                
                if (!it1.hasNext() && !it2.hasNext()) return  0;
                else if (!it1.hasNext())              return -1;
                else                                  return  1;
            }
            
            public int compareTo(Vertex other) {
                if (!this.hasPath && !other.hasPath) return  0;
                if (!this.hasPath)                 return  1;
                if (!other.hasPath)                return -1;
                return compare(other.set);
            }
        }
        
        private static class Edge {
            public int labelA, labelB;
            public int dist;
            public int other(int label) {
                if (labelA == label) return labelB;
                else                 return labelA;
            }
            public Edge(int labelA, int labelB, int dist) {
                this.labelA = labelA;
                this.labelB = labelB;
                this.dist = dist;
            }
        }

        public int N;
        public List<Edge>[] adj;
        public Map<Integer, Vertex> map;
        
        public Graph(int N) {
            this.N = N;
            this.adj = (List<Edge>[]) new List[N + 1];
            for (int i = 0; i < N + 1; i++) 
                adj[i] = new ArrayList<>();
            map = new HashMap<>();
        }
        
        public void addEdge(int A, int B, int C) {
            if (!map.containsKey(A)) map.put(A, new Vertex(A));
            if (!map.containsKey(B)) map.put(B, new Vertex(B));
            
            adj[A].add(new Edge(A, B, C + 1));
            adj[B].add(new Edge(A, B, C + 1));
        }
        
        public String dijkstra(int label) {
//            System.out.println();
//            System.out.println("---- source: " + label);
//            System.out.println();
            
            Vertex source = map.get(label);
            NavigableSet<Vertex> pq = new TreeSet<>();
            for (Vertex v: map.values()) {
                v.hasPath = (v == source);
                v.set = new TreeSet<>();
                pq.add(v);
            }
            
            dijkstra(pq);
            
            String sum = "";
            for (Vertex v: map.values()) {
//                System.out.println("---- label: " + v.label);
//                System.out.println(Arrays.toString(v.set.toArray()));
                sum = addBinaries(sum, v.set);
            }
            return sum;
        }
        
        public void dijkstra(NavigableSet<Vertex> pq) {
            while (!pq.isEmpty()) {
                Vertex u = pq.pollFirst();
                if (!u.hasPath) break;
                
                for (Edge e: adj[u.label]) {
                    Vertex v   = map.get(e.other(u.label));
                    // System.out.println(" -label: " + v.label);
                    NavigableSet<Integer> alt = new TreeSet<>(u.set);
                    alt.add(e.dist);

                    if (!v.hasPath || v.compare(alt) > 0) {
                        // System.out.println(" - " + Arrays.toString(alt.toArray()));
                        pq.remove(v);
                        v.hasPath = true;
                        v.set = alt;
                        pq.add(v);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        Graph graph = new Graph(N);
        
        for (int i = 0; i < M; i++) {
            int A = in.nextInt();
            int B = in.nextInt();
            int C = in.nextInt();
            graph.addEdge(A, B, C);
        }
        
        String sum = "";
        for (int source = 1; source <= N; source++) {
            sum = addBinaries(sum, graph.dijkstra(source));
        }
        sum = halfBinary(sum);
        System.out.println(sum);
    }
}