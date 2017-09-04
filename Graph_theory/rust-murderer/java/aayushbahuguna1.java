import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt();
            int M = in.nextInt();
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
            for (int i = 0; i < N; i++) {
                map.put(i, new ArrayList<Integer>());
            }
            for (int i = 0; i < M; i++) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                addEdge(map, a, b);
            }
            int S = in.nextInt() - 1;
            boolean[] V = new boolean[N];
            bfs(S, map, V);
        
        
            for (int i = 0; i < N; i++) {
                if (i != S)
                    System.out.print(map.get(S).contains(i) ? "2 " : "1 ");
            }
            System.out.println();
        }
        in.close();
    }

    
    static void bfs(int S, HashMap<Integer, ArrayList<Integer>> map, boolean[] isVisited) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(S);
        isVisited[S] = true;
        while(!queue.isEmpty()) {
            int n = queue.remove();
            isVisited[n] = true;
            ArrayList<Integer> list = map.get(n);
            for (int v : list) {
                if (!isVisited[v]) queue.add(v);
            }
        }
    }

    static void addEdge(HashMap<Integer, ArrayList<Integer>> map, int a, int b) {
        ArrayList<Integer> list = map.get(a);
        list.add(b);
        map.put(a, list);
        list = map.get(b);
        list.add(a);
        map.put(b, list);        
    }
}