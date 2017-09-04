import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static int[][][] edgeToNbrsEdges(int[] from, int[] to, int[] degree) {
        int n = degree.length-1, m = from.length;
        int[][] nbrs = new int[n+1][];
        int[][] nbr_edges = new int[n+1][];
        for (int i = 1; i <= n; i++) {
            nbrs[i] = new int[degree[i]];
            nbr_edges[i] = new int[degree[i]];
        }
        for (int i = 0; i < m; i++) {
            int from_i = --degree[from[i]], to_i = --degree[to[i]];
            nbrs[from[i]][from_i] = to[i];
            nbrs[to[i]][to_i] = from[i];
            nbr_edges[from[i]][from_i] = i;
            nbr_edges[to[i]][to_i] = i;
        }
        int[][][] ret = {nbrs, nbr_edges};
        return ret;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        int[] degree = new int[n+1];
        int[] e_x = new int[e], e_y = new int[e], e_r = new int[e];
        for (int a0 = 0; a0 < e; a0++) {
            e_x[a0] = in.nextInt();
            e_y[a0] = in.nextInt();
            e_r[a0] = in.nextInt() % 10;
            degree[e_x[a0]]++;
            degree[e_y[a0]]++;
        }
        int[][][] nw;
        int[][] nbrs, nbr_edges;
        nw = edgeToNbrsEdges(e_x, e_y, degree);
        nbrs = nw[0];
        nbr_edges = nw[1];
        int[] e_visit = new int[e], v_visit = new int[n+1], stack_e = new int[e], stack_v = new int[e];
        int v_i = 1, s_i = 0, e_visited = 0;
        long[] ans = new long[10];
        while (v_i <= n && e_visited < e) {
            int nbr_e, visit_e, visit_v = v_i, visit_i = 0;
            int[] r = new int[n+1], visited = new int[n];
            visited[visit_i++] = v_i;
            dfs_connected:
            while (true) {
                v_visit[visit_v]++;
                for (int i = 0; i < nbr_edges[visit_v].length; i++) {
                    nbr_e = nbr_edges[visit_v][i];
                    if (e_visit[nbr_e] == 0) {
                        stack_e[s_i] = nbr_e;
                        stack_v[s_i++] = nbrs[visit_v][i];
                        e_visit[nbr_e]--;
                    }
                }
                if (s_i == 0) {
                    break;
                }
                visit_e = stack_e[--s_i];
                visit_v = stack_v[s_i];
                while (e_visit[visit_e] > 0) {
                    if (s_i == 0) {
                        break dfs_connected;
                    }
                    visit_e = stack_e[--s_i];
                    visit_v = stack_v[s_i];
                }
                e_visit[visit_e] = 1;
                int from, to, rate;
                if (e_x[visit_e] == visit_v) {
                    from = e_y[visit_e];
                    to = e_x[visit_e];
                    rate = 10 - e_r[visit_e];
                } else {
                    from = e_x[visit_e];
                    to = e_y[visit_e];
                    rate = e_r[visit_e];
                }
                if (v_visit[visit_v] == 0) {
                    r[visit_v] = (r[from] + rate) % 10;
                    visited[visit_i++] = visit_v;
                } else {
                    int loop = (10 - r[visit_v] + r[from] + rate) % 10;
                    if (loop % 5 != 0) {
                        loop = 2 + loop % 2;
                    }
                    if (r[v_i] == 0) {
                        r[v_i] = loop;
                    } else if (r[v_i] == 5) {
                        if (loop % 5 != 0) {
                            r[v_i] = 1;
                        }
                    } else if (r[v_i] % 2 == 0) {
                        if (loop % 2 != 0) {
                            r[v_i] = 1;
                        }
                    }
                }
            }
            if (r[v_i] == 1) {
                for (int i = 0; i < 10; i++) {
                    ans[i] += (long) visit_i*(visit_i-1);
                } 
            } else {
                long[] tmp_ans = new long[10];
                long[] d_count = new long[10];
                for (int i = 0; i < visit_i; i++) {
                    d_count[r[visited[i]]]++;
                }
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (i == 0) {
                            tmp_ans[i] += d_count[j]*(d_count[j]-1);
                        } else {
                            tmp_ans[i] += d_count[j]*d_count[(i+j)%10];
                        }
                    }
                }
                int shift = r[v_i];
                for (int i = 0; i < 10; i++) {
                    ans[i] += tmp_ans[i];
                }
                while (shift != 0) {
                    for (int i = 0; i < 10; i++) {
                        ans[i] += tmp_ans[(i+shift)%10];
                    }
                    shift += r[v_i];
                    shift %= 10;
                }
            }
            while (v_i <= n && v_visit[v_i] > 0) {
                v_i++;
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(ans[i]);
        }
    }
}