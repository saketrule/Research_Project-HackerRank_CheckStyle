import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static class Edge {
        int to;
        int dist;
        public Edge(int t, int d) {
            to = t;
            dist = d;
        }
    }
    
    public static class Result {
        public long in_out;
        public long one_way;
        public long inside;
        public long inside_with_root;
        public int count;
    }
    
    public static Result solve(int x, int p, List<Edge>[] g, boolean[] l) {
        if (g[x].size() == 1 && p != -1) {
            Result r = new Result();
            r.count = l[x] ? 1 : 0;
            r.in_out = 0;
            r.one_way = 0;
            r.inside = 0;
            r.inside_with_root = 0;
            return r;
        }
        Result r = new Result();
        r.count = l[x] ? 1 : 0;
        r.in_out = 0;
        r.one_way = 0;
        r.inside = 0;
        r.inside_with_root = 0;
        long bestChild = 0;
        long secondBestChild = 0;
        int childCount = 0;
        Result lastChild = null;
        int lastChildEdge = 0;
        for (Edge e : g[x]) {
            if (e.to != p) {
                Result child = solve(e.to, x, g, l);
                if (child.count > 0) {
                    lastChild = child;
                    lastChildEdge = e.dist;
                    ++childCount;
                    r.count += child.count;
                    long in_out = 2 * e.dist + child.in_out;
                    r.in_out += in_out;
                    long val = in_out - e.dist - child.one_way;
                    if (val > bestChild) {
                        secondBestChild = bestChild;
                        bestChild = val;
                    } else if (val > secondBestChild) {
                        secondBestChild = val;
                    }
                }
            }
        }
        r.one_way = r.in_out - bestChild;
        r.inside_with_root = r.one_way - secondBestChild;
        if (childCount > 1) {
            r.inside = r.one_way - secondBestChild;
        } else if (childCount == 1) {
            if (l[x]) {
                if (lastChild.one_way + lastChildEdge < lastChild.inside_with_root + 2 * lastChildEdge) {
                    r.inside = lastChild.one_way + lastChildEdge;
                } else {
                    r.inside = lastChild.inside_with_root + 2 * lastChildEdge;
                }
            } else {
                r.inside = lastChild.inside;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        boolean[] letters = new boolean[n];
        for (int i = 0; i < n; ++i)
            letters[i] = false;
        for (int i = 0; i < k; ++i) {
            letters[in.nextInt() - 1] = true;;
        }
        List<Edge>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<Edge>();
        }
        for (int i = 1; i < n; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();
            g[a].add(new Edge(b, c));
            g[b].add(new Edge(a, c));
        }
        Result r = solve(0, -1, g, letters);
        System.out.println(r.inside);
    }
}