import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<Integer>());
        }
        for (int a0 = 0; a0 < e; a0++) {
            int u = in.nextInt()-1;
            int v = in.nextInt()-1;
            int w = in.nextInt()%10;
            edges.get(u).add(v*10+w);
            edges.get(v).add(u*10+(10-w)%10);
        }
        boolean[] vis = new boolean[n];
        long[] ans = new long[10];
        for (int i = 0; i < n; i++) {
            if (vis[i])
                continue;
            vis[i] = true;
            HashSet<Integer> group = new HashSet<Integer>();
            ArrayDeque<Integer> q = new ArrayDeque<Integer>();
            group.add(i*10);
            q.add(i*10);
            while (!q.isEmpty()) {
                int u = q.removeFirst();
                int wb = u%10;
                u /= 10;
                for (int vw : edges.get(u)) {
                    int v = vw/10;
                    int w = (wb+(vw%10))%10;
                    int vwn = v*10+w;
                    if (!group.contains(vwn)) {
                        group.add(vwn);
                        q.add(vwn);
                        vis[v] = true;
                    }
                }
            }
            HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
            for (int j : group) {
                hm.put(j/10, 0);
            }
            for (int j : group) {
                hm.put(j/10, hm.get(j/10)|(1<<j%10));
            }
            for (int j : hm.keySet()) {
                counts.put(hm.get(j), 0);
            }
            for (int j : hm.keySet()) {
                counts.put(hm.get(j), counts.get(hm.get(j))+1);
            }
            HashMap<Integer, HashSet<Integer>> atlas = new HashMap<Integer, HashSet<Integer>>();
            for (int j : counts.keySet()) {
                atlas.put(j, new HashSet<Integer>());
                for (int k = 0; k < 10; k++) {
                    if (((1<<k)&j) > 0)
                        atlas.get(j).add(k);
                }
            }
            
            for (int j : counts.keySet()) {
                for (int k : counts.keySet()) {
                    for (int c = 0; c < 10; c++) {
                        for (int b : atlas.get(k)) {
                            if (atlas.get(j).contains((c+b)%10)) {
                                if (j==k)
                                    ans[c] += (long)counts.get(j)*(counts.get(k)-1);
                                else
                                    ans[c] += (long)counts.get(j)*counts.get(k);
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(ans[i]);
        }
    }
}