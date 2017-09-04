import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        if (n == 1) {
         System.out.println(0);
         return;
        }
        long[] roads = new long[m];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            roads[sc.nextInt()] = (long)u*n+v;
        }
        ArrayList<HashSet<Integer>> conn = new ArrayList<HashSet<Integer>>();
        int[] which = new int[n];
        ArrayList<ArrayList<Long>> tree = new ArrayList<ArrayList<Long>>();
        HashSet<Integer> presentRoads = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            conn.add(new HashSet<Integer>());
            conn.get(i).add(i);
            which[i] = i;
            tree.add(new ArrayList<Long>());
        }
        for (int i = 0; i < m; i++) {
            long road = roads[i];
            int u = (int)(road/n);
            int v = (int)(road%n);
            if (which[u]!=which[v]) {
                if (conn.get(which[u]).size() < conn.get(which[v]).size()) {
                    for (int u2 : conn.get(which[u])) {
                        conn.get(which[v]).add(u2);
                        which[u2] = which[v];
                    }
                } else {
                    for (int v2 : conn.get(which[v])) {
                        conn.get(which[u]).add(v2);
                        which[v2] = which[u];
                    }
                }
                tree.get(u).add((long)i*n+v);
                tree.get(v).add((long)i*n+u);
                presentRoads.add(i);
            }
        }
        
        int[] c = new int[n];
        
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        HashSet<Integer> unvisited = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
         c[i] = 1;
            if (tree.get(i).size() == 1) {
             q.add(i);
            }
            else {
             unvisited.add(i);
            }
        }
        
        while (!q.isEmpty()) {
            int u = q.removeFirst();
            if (tree.get(u).size() < 1)
             continue;
            if (tree.get(u).size() > 1) {
             q.add(u);
             continue;
            }
            int v = (int)(tree.get(u).get(0)%n);
            int r = (int)(tree.get(u).get(0)/n);
            if (c[u]+c[v] < n)
             c[v] += c[u];
            tree.get(u).remove(new Long((long)r*n+v));
            tree.get(v).remove(new Long((long)r*n+u));
            if (unvisited.contains(v)) {
             unvisited.remove(v);
             q.add(v);
            }
        }
        
        long[] result = new long[1000001];
        for (int r : presentRoads) {
         long road = roads[r];
         int u = (int)(road/n);
            int v = (int)(road%n);
            long num = Math.min(c[u],c[v]);
            result[r] = num*(n-num);
        }
        int max = 0;
        for (int i = 0; i < 1000000; i++) {
         result[i+1] += result[i]/2;
         result[i] %= 2;
         if (result[i] == 1)
          max = i;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = max; i >= 0; i--) {
         ans.append(result[i]);
        }
        System.out.println(ans);
    }
}