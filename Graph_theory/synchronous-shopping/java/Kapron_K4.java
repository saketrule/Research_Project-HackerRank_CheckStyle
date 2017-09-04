import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static class edge {
        edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        public int to;
        public int weight;
    }
    
    public static int dijkstra(edge[][] graph, int start, int end) {
        Queue<Integer> q = new PriorityQueue<>();
        q.add(new Integer(start));
        int[] distances = new int[graph.length];
        for (int i = 0; i < distances.length; ++i) {
            distances[i] = 2000000000;
        }
        distances[start] = 0;
        while(!q.isEmpty()) {
            int current = q.remove();
            if (current == end)
                return distances[current];
            for (int i = 1; i < graph[current][0].weight; ++i) {
                edge current_edge = graph[current][i];
                if (distances[current_edge.to] > distances[current] + current_edge.weight) {
                    distances[current_edge.to] = distances[current] + current_edge.weight;
                    q.add(new Integer(current_edge.to));
                }
            }
            
        }
        return -1;
    }
    public static int count(int size) {
        return (1<<size);
    }
    public static class state implements Comparable {
        static state end(int size, int centers) {
            int endf = count(size);
            return new state(endf-1, centers -1, centers -1, 0, 0);
        }
        static state begin(int size) {
            return new state(0, 0, 0, 0, 0);
        }
        
        public int compareTo(Object o) {
            state obj = (state)o;
            if (time() == obj.time())
                return 0;
            if (time() < obj.time())
                return -1;
            return 1;
        }
        
        public boolean boughtAll(int size) {
            return fishes == count(size) -1;
        }
        boolean equals(state other) {
            return fishes == other.fishes && big_cat == other.big_cat && small_cat == other.small_cat;
        }
        public void buyFish(int index) {
            fishes |= (1 << index);
        }
        public int time() {
            return bct > sct ? bct : sct;
        }
        public ArrayList<state> neighbours(edge[][] graph, int[][] centers) {
            ArrayList<state> ret = new ArrayList<>();
            if (sct == bct) {
                for (int i = 1; i < graph[small_cat][0].weight; ++i) {
                    for (int j = 1; j < graph[big_cat][0].weight; ++j) {
                        edge sce = graph[small_cat][i];
                        edge bce = graph[big_cat][j];  

                        state next = new state(fishes, sce.to, bce.to, sct+sce.weight, bct + bce.weight);
                        for (int k = 1; k < centers[sce.to][0]; ++k) {
                            next.buyFish(centers[sce.to][k]);
                        }
                        for (int k = 1; k < centers[bce.to][0]; ++k) {
                            next.buyFish(centers[bce.to][k]);
                        }
                        ret.add(next);
                    }
                }
                for (int i = 1; i < graph[small_cat][0].weight; ++i) {
                    edge sce = graph[small_cat][i];
                    state next = new state(fishes, sce.to, big_cat, sct+sce.weight, bct);
                    for (int k = 1; k < centers[sce.to][0]; ++k) {
                            //System.out.println(centers[sce][k]);
                        next.buyFish(centers[sce.to][k]);
                    }
                    ret.add(next);
                }
                for (int i = 1; i < graph[big_cat][0].weight; ++i) {
                    edge bce = graph[big_cat][i];
                    state next = new state(fishes, small_cat, bce.to, sct, bct + bce.weight);
                    for (int k = 1; k < centers[bce.to][0]; ++k) {
                            //System.out.println(centers[sce][k]);
                        next.buyFish(centers[bce.to][k]);
                    }
                    ret.add(next);
                }
            } else {
                if (sct > bct) {
                    for (int i = 1; i < graph[big_cat][0].weight; ++i) {
                        edge bce = graph[big_cat][i];
                        state next = new state(fishes, small_cat, bce.to, sct, bct + bce.weight);
                        for (int k = 1; k < centers[bce.to][0]; ++k) {
                        //System.out.println(centers[sce][k]);
                            next.buyFish(centers[bce.to][k]);
                        }
                        ret.add(next);
                    }
                } else {
                    for (int i = 1; i < graph[small_cat][0].weight; ++i) {
                        edge sce = graph[small_cat][i];
                        state next = new state(fishes, sce.to, big_cat, sct + sce.weight, bct);
                        for (int k = 1; k < centers[sce.to][0]; ++k) {
                        //System.out.println(centers[sce][k]);
                            next.buyFish(centers[sce.to][k]);
                        }
                        ret.add(next);
                    }    
                }
            }
            return ret;
        }
        state(int f, int s, int b, int st, int bt) {
            this.fishes = f;
            this.small_cat = s;
            this.big_cat = b;
            this.sct = st;
            this.bct = bt;
        }
        state(state other) {
            this.fishes = other.fishes;
            this.small_cat = other.small_cat;
            this.big_cat = other.big_cat;
            this.sct = other.sct;
            this.bct = other.bct;
        }
        public int fishes;
        public int small_cat;
        int sct;
        int big_cat;
        int bct;
    }
    public static int solve(edge[][] graph, int[][] centers, int f) {
        Queue<state> q = new PriorityQueue<>();
        state begin = state.begin(f);
        for (int i = 1; i < centers[0][0]; ++i) {
            begin.buyFish(centers[0][i]);
        }
        q.add(begin);
        int[][][] distances = new int[count(f)][centers.length][centers.length];
        for (int i = 0; i < count(f); ++i) {
            for (int j = 0; j < centers.length; ++j) {
                for (int k = 0; k < centers.length; ++k) {
                    distances[i][j][k] = 2000000000;
                }
            }
        }
        distances[begin.fishes][begin.small_cat][begin.big_cat] = 0;
        while (!q.isEmpty()) {
            state current = q.remove();
            //System.out.println("CURRENT: Small: " + current.small_cat + " Big: " + current.big_cat + " Fishes: " + Integer.toBinaryString(current.fishes) + " Time: " + current.time());
            if (current.equals(state.end(f, centers.length))) {
                //return current.time();
                return distances[current.fishes][current.small_cat][current.big_cat];
            }
            for (state next : current.neighbours(graph, centers)) {
                int time = next.time();
                //System.out.println("NEXT: Small: " + next.small_cat + " Big: " + next.big_cat + " Fishes: " + Integer.toBinaryString(next.fishes) + " BTime: " + next.bct + " STime: " + next.sct);
                //System.out.println("nf" + next.fishes + "ns" + next.small_cat + "nb" + next.big_cat);
                if (distances[next.fishes][next.small_cat][next.big_cat] > time) {
                    distances[next.fishes][next.small_cat][next.big_cat] = time;
                    //System.out.println("Small:" + current.small_cat + "->" + next.small_cat);
                    //System.out.println("Big:  " + current.big_cat + "->" + next.big_cat);
                    q.add(next);
                }
            }
        }
        for (int i = 0; i < distances.length; ++i) {
            for (int j = 0; j < distances[i].length; ++j) {
                System.out.print(distances[i][j][1]);
                System.out.print(" ");
            }
            System.out.println();
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int centers_ = s.nextInt();
        int roads = s.nextInt();
        int fishes = s.nextInt();
        int[][] centers = new int[centers_][fishes+1];
        edge[][] graph = new edge[centers_][roads+1];
        for (int c = 0; c < centers_; ++c) {
            graph[c][0] = new edge(0, 1);
            centers[c][0] = 1;
            int I = s.nextInt();
            for (int i = 0; i < I; ++i) {
                centers[c][centers[c][0]++] = s.nextInt() - 1;
            }
        }
        for (int r = 0; r < roads; ++r) {     
            int from = s.nextInt() -1;
            int to = s.nextInt() -1;
            int weight = s.nextInt();
            graph[from][graph[from][0].weight++] = new edge(to, weight);
            graph[to][graph[to][0].weight++] = new edge(from, weight);
        }
        System.out.println(solve(graph, centers, fishes));
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}