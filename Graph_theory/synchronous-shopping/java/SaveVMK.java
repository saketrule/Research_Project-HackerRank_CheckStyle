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
        int k = sc.nextInt();
        int[] sf = new int[n];
        for (int i = 0; i < n; i++) {
            int nf = sc.nextInt();
            for (int j = 0; j < nf; j++) {
                sf[i] |= 1<<(sc.nextInt()-1);
            }
        }
        ArrayList<ArrayList<Integer>> roads = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            roads.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            int w = sc.nextInt();
            roads.get(u).add(w*n+v);
            roads.get(v).add(w*n+u);
        }
        int[][] dist = new int[1<<k][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (1<<k); j++) {
                dist[j][i] = 1000000000;
            }
        }
        PriorityQueue<Long> pq = new PriorityQueue<Long>();
        pq.add((long)sf[0]*n);
        for (int i = 0; i < (1<<k); i++) {
            if ((i&sf[0])==i) {
                dist[i][0] = 0;
            }
        }
        while (!pq.isEmpty()) {
            long rem = pq.remove();
            int u = (int)(rem%n);
            int vis = (int)((rem/n)%(1<<k));
            int w = (int)(rem/(1<<k)/n);
            for (int wv : roads.get(u)) {
                int v = wv%n;
                int w2 = wv/n;
                int vis2 = vis | sf[v];
                if (w+w2<dist[vis2][v]) {
                    pq.add((long)(w+w2)*(1<<k)*n+(long)vis2*n+v);
                    for (int i = 0; i < (1<<k); i++) {
                        if ((i&vis2)==i) {
                            dist[i][v] = Math.min(dist[i][v],w+w2);
                        }
                    }
                }
            }
        }
        int mindist = Integer.MAX_VALUE;
        for (int i = 0; i < (1<<k); i++) {
            if (Math.max(dist[i][n-1],dist[(1<<k)-i-1][n-1]) < mindist)
                mindist = Math.max(dist[i][n-1],dist[(1<<k)-i-1][n-1]);
        }
        System.out.println(mindist);
    }
}