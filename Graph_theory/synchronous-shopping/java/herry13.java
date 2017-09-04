import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static class Edge {
        Node dest;
        int time;
        
        public Edge(Node n, int t) {
            this.dest = n;
            this.time = t;
        }
    }
    
    static class Node {
        int id;
        List<Edge> edges = new ArrayList<Edge>();
        List<Integer> fishes = new ArrayList<Integer>();
        
        public Node(int id) {
            this.id = id;
        }
    }
    
    static class State implements Comparable {
        int littleCatPos = 0;
        int bigCatPos = 0;
        int littleCatTime = 0;
        int bigCatTime = 0;
        Set<Integer> fishesToBuy = new HashSet<Integer>();
        Node goal;
        
        public State(Node goal) {
            this.goal = goal;
        }
        
        public String toString() {
            return littleCatPos + " " + bigCatPos + " " + getTime() + " " + 
                Arrays.toString(fishesToBuy.toArray());
        }
        
        public State(State s) {
            this.goal = s.goal;
            this.littleCatPos = s.littleCatPos;
            this.bigCatPos = s.bigCatPos;
            this.littleCatTime = s.littleCatTime;
            this.bigCatTime = s.bigCatTime;
            this.fishesToBuy = new HashSet<Integer>(s.fishesToBuy);
        }
        
        public boolean equals(Object obj) {
            if (obj instanceof State) {
                State s = ((State) obj);
                return s.littleCatPos == this.littleCatPos &&
                    s.bigCatPos == this.bigCatPos &&
                    s.fishesToBuy.hashCode() == this.fishesToBuy.hashCode();
            }
            return false;
        }
        
        public int hashCode() {
            int h = littleCatPos;
            h = 37 * h + bigCatPos;
            h = 37 * h + this.fishesToBuy.hashCode();
            return h;
        }
        
        public boolean littleCatAtGoal() {
            return this.littleCatPos == goal.id;
        }
        
        public boolean bigCatAtGoal() {
            return this.bigCatPos == goal.id;
        }
        
        public boolean atGoal() {
            return littleCatAtGoal() && bigCatAtGoal() && this.fishesToBuy.size() == 0;
        }
        
        public int getTime() {
            return littleCatTime > bigCatTime ? littleCatTime : bigCatTime;
        }

        public void littleCatGoTo(Node n, int time) {
            littleCatPos = n.id;
            littleCatTime += time;
            if (fishesToBuy.size() > 0) {
                fishesToBuy.removeAll(n.fishes);
            }
        }
        
        public void bigCatGoTo(Node n, int time) {
            bigCatPos = n.id;
            bigCatTime += time;
            if (fishesToBuy.size() > 0) {
                fishesToBuy.removeAll(n.fishes);
            }
        }
        
        public int compareTo(Object obj) {
            if (obj instanceof State) {
                return this.getTime() - ((State) obj).getTime();
            }
            return 0;
        }
    }

    static Node[] parseNodes(int N, Scanner in) {
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
            int n = in.nextInt();
            for (int j = 0; j < n; j++) {
                nodes[i].fishes.add(in.nextInt());
            }
        }
        return nodes;
    }
    
    static void parseEdges(Node[] nodes, int M, Scanner in) {
        for (int i = 0; i < M; i++) {
            int n1 = in.nextInt() - 1;
            int n2 = in.nextInt() - 1;
            int time = in.nextInt();
            nodes[n1].edges.add(new Edge(nodes[n2], time));
            nodes[n2].edges.add(new Edge(nodes[n1], time));
        }
    }

    static int search(int start, int end, int K, Node[] nodes) {
        State state = new State(nodes[nodes.length-1]);
        // Prepare initial state
        for (int i = 1; i <= K; i++) {
            state.fishesToBuy.add(i);
        }
        state.littleCatGoTo(nodes[0], 0);
        state.bigCatGoTo(nodes[0], 0);
        
        // visited state
        Set<State> visited = new HashSet<State>();
        visited.add(state);

        // open state
        Queue<State> open = new PriorityQueue<State>();
        open.offer(state);
        
        int step = 0;
        /*System.out.println(step + ": visited=" + visited.size() +
                           " open=" + open.size() + " best=" + state.getTime() +
                           " state:" + state);*/
        while (open.peek() != null) {
            state = open.poll();
            if (state.atGoal()) {
                break;
            }
            if (!state.littleCatAtGoal()) {
                Node n = nodes[state.littleCatPos];
                for (Edge e : n.edges) {
                    State s = new State(state);
                    //System.out.println("new state => " + s);
                    s.littleCatGoTo(e.dest, e.time);
                    if (!visited.contains(s)) {
                        open.offer(s);
                        visited.add(s);
                    }
                }
            }
            if (!state.bigCatAtGoal()) {
                Node n = nodes[state.bigCatPos];
                for (Edge e : n.edges) {
                    State s = new State(state);
                    //System.out.println("new state => " + s);
                    s.bigCatGoTo(e.dest, e.time);
                    if (!visited.contains(s)) {
                        open.offer(s);
                        visited.add(s);
                    }
                }
            }
            
            /*System.out.println((++step) + ": visited=" + visited.size() +
                               " open=" + open.size() + " best=" + state.getTime() +
                               " state:" + state);*/
        }
        /*System.out.println("final: visited=" + visited.size() +
                           " open=" + open.size() + " time=" + state.getTime() +
                           " state:" + state + " at-goal:" + state.atGoal());*/
        
        return state.getTime();
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int K = in.nextInt();
        Node[] nodes = parseNodes(N, in);
        parseEdges(nodes, M, in);
        System.out.println(search(0, N-1, K, nodes));
    }
}