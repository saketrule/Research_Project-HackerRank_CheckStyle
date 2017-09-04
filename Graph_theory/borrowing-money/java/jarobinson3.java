import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Borrow implements Comparator<LinkedList<Integer>> {

    static class Wrapper {
        boolean[][] graph;
        int[] cost;

        int best;
        int count = 0;

        int N;
    } 

    public int compare(LinkedList<Integer> a, LinkedList<Integer> b) {
        if( a == b ) return 0;
        if( a == null ) return -1;
        if( b == null ) return 1;
        if( a.size() != b.size() ) return a.size() - b.size();
        for(int v : a ) {
            if( !b.contains(v)) {
                return -1;
            }
        }
        return 0;
    }


    private static TreeSet<LinkedList<Integer>> sets = new TreeSet<LinkedList<Integer>>(new Borrow());

    private static LinkedList<Integer> path = new LinkedList<Integer>();

    private static int[] offlimits = new int[64];

    private static <T> void printList(List<T> list) {
        for(T v : list) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    private static void search(Wrapper w, int pos, int curr) {
        path.push(pos);
        curr = curr + w.cost[pos];

        if(curr > w.best) {
            sets.clear();
            sets.add(new LinkedList<Integer>(path));
            w.best = curr;
        }else if(curr == w.best) {
            sets.add(new LinkedList<Integer>(path));
        }

        // Mark off limits for search
        for(int i = pos + 1; i < w.N; ++i) {
            if(w.graph[pos][i]) {
                ++offlimits[i];
            }
        }

        for(int i = pos + 1; i < w.N; ++i) {
            if(offlimits[i] == 0) {
                search(w,i,curr);
            }
        }

        // Add back to search
        for(int i = pos + 1; i < w.N; ++i) {
            if(w.graph[pos][i]) {
                --offlimits[i];
            }
        }
        path.pop();
    }

    private static void doIt(boolean[][] graph, int[] cost) {
        Wrapper w = new Wrapper();
        w.N = graph.length;
        w.graph = graph;
        w.cost = cost;
        w.best = 0;
        w.count = 0;
        
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 1; i < w.N; ++i) {
            offlimits[i] = 1;
            search(w,i,0);
            offlimits[i] = 1;
        }
        System.out.println(w.best + " " + sets.size());
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int M = input.nextInt();
        int[] cost = new int[N+1];
        boolean[][] graph = new boolean[N+1][N+1];

        for(int i = 1 ; i <= N; ++i) {
            cost[i] = input.nextInt();
        }
        for(int i = 1 ; i <= M; ++i) {
            int u = input.nextInt();
            int v = input.nextInt();
            graph[u][v] = graph[v][u] = true;
        }

        doIt(graph,cost);
    }
}