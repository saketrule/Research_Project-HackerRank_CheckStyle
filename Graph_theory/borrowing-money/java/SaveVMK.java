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
        int[] c = new int[n];
        HashSet<Integer> fullGraph = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            c[i] = sc.nextInt();
            fullGraph.add(i);
        }
        ArrayList<HashSet<Integer>> neigh = new ArrayList<HashSet<Integer>>();
        for (int i = 0; i < n; i++) {
            neigh.add(new HashSet<Integer>());
            for (int j = 0; j < n; j++) {
                if (i!=j)
                    neigh.get(i).add(j);
            }
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            neigh.get(u).remove(v);
            neigh.get(v).remove(u);
        }
        ArrayList<Long> mega = tomita(n, neigh, 0l, fullGraph, new HashSet<Integer>());
        int max = -1;
        ArrayList<Long> maxes = new ArrayList<Long>();
        for (long h : mega) {
            int w = 0;
            for (int i = 0; i < n; i++) {
                if ((h & (1l<<i)) > 0)
                    w += c[i];
            }
            if (w > max) {
                maxes = new ArrayList<Long>();
                max = w;
            }
            if (w == max)
                maxes.add(h);
        }
        long nzm = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] > 0)
                nzm += (1l<<i);
        }
        HashMap<Long, ArrayList<Long>> zeroset = new HashMap<Long, ArrayList<Long>>();
        for (long h : maxes) {
            if (!zeroset.containsKey(h&nzm))
                zeroset.put(h&nzm, new ArrayList<Long>());
            zeroset.get(h&nzm).add(h&(~nzm));
        }
        long finans = 0;
        for (long key : zeroset.keySet()) {
            ArrayList<Long> al = zeroset.get(key);
            if (al.size() == 1) {
                long ans = 1;
                for (int i = 0; i < n; i++) {
                    if ((al.get(0)&(1l<<i)) > 0)
                        ans *= 2;
                }
                finans += ans;
            }
            else if (al.size() == 2) {
                long ans = 0;
                long add = 1;
                for (int i = 0; i < n; i++) {
                    if ((al.get(0)&(1l<<i)) > 0)
                        add *= 2;
                }
                ans += add;
                add = 1;
                for (int i = 0; i < n; i++) {
                    if ((al.get(1)&(1l<<i)) > 0)
                        add *= 2;
                }
                if (key == 0)
                    add--;
                ans += add;
                long sub = 1;
                for (int i = 0; i < n; i++) {
                    if ((al.get(0)&(1l<<i)) > 0 && (al.get(1)&(1l<<i)) > 0)
                        sub *= 2;
                }
                ans -= sub;
                finans += ans;
            } else if (al.size() == 3) {
                long ans = 0;
                long add = 1;
                for (int i = 0; i < n; i++) {
                    if ((al.get(0)&(1l<<i)) > 0)
                        add *= 2;
                }
                ans += add;
                add = 1;
                for (int i = 0; i < n; i++) {
                    if ((al.get(1)&(1l<<i)) > 0)
                        add *= 2;
                }
                ans += add;
                add = 1;
                for (int i = 0; i < n; i++) {
                    if ((al.get(2)&(1l<<i)) > 0)
                        add *= 2;
                }
                ans += add;
                long sub = 1;
                for (int i = 0; i < n; i++) {
                    if ((al.get(0)&(1l<<i)) > 0 && (al.get(1)&(1l<<i)) > 0)
                        sub *= 2;
                }
                ans -= sub;
                sub = 1;
                for (int i = 0; i < n; i++) {
                    if ((al.get(0)&(1l<<i)) > 0 && (al.get(2)&(1l<<i)) > 0)
                        sub *= 2;
                }
                ans -= sub;
                sub = 1;
                for (int i = 0; i < n; i++) {
                    if ((al.get(1)&(1l<<i)) > 0 && (al.get(2)&(1l<<i)) > 0)
                        sub *= 2;
                }
                ans -= sub;
                add = 1;
                for (int i = 0; i < n; i++) {
                    if ((al.get(1)&(1l<<i)) > 0 && (al.get(2)&(1l<<i)) > 0 && (al.get(2)&(1l<<i)) > 0)
                        add *= 2;
                }
                ans += add;
                finans += ans;
            } else {
                HashSet<Long> hs = new HashSet<Long>();
                ArrayDeque<Long> ad = new ArrayDeque<Long>(10000000);
                for (long i : al) {
                    hs.add(i);
                    ad.add(i);
                }
                while (!ad.isEmpty()) {
                    long next = ad.removeFirst();
                    for (int i = 0; i < n; i++) {
                        long toAdd = next&(~(1l<<i));
                        if (!hs.contains(toAdd)) {
                            hs.add(toAdd);
                            ad.add(toAdd);
                        }
                    }
                }
                finans += hs.size();
            }
        }
        System.out.println(max+" "+finans);
    }
    
    public static ArrayList<Long> tomita(int n, ArrayList<HashSet<Integer>> neigh, long r, HashSet<Integer> p, HashSet<Integer> x) {
        ArrayList<Long> ret = new ArrayList<Long>();
        if (p.size() == 0 && x.size() == 0) {
            ret.add(r);
            return ret;
        }
        int u = -1;
        int neighCount = -1;
        for (int i = 0; i < n; i++) {
            if (p.contains(i)||x.contains(i)) {
                int nc = 0;
                for (int ne : neigh.get(i)) {
                    if (p.contains(ne))
                        nc++;
                }
                if (nc > neighCount) {
                    u = i;
                    neighCount = nc;
                }
            }
        }
        HashSet<Integer> pc = new HashSet<Integer>(p);
        for (int v : pc) {
            if (neigh.get(u).contains(v))
                continue;
            long newR = r | (1l<<v);
            HashSet<Integer> newP = new HashSet<Integer>(p);
            newP.retainAll(neigh.get(v));
            HashSet<Integer> newX = new HashSet<Integer>(x);
            newX.retainAll(neigh.get(v));
            ret.addAll(tomita(n, neigh, newR, newP, newX));
            p.remove(v);
            x.add(v);
        }
        return ret;
    }
    
/*    
34 32
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 2
2 3
3 1
4 5
5 6
6 4
7 8
8 9
9 7
10 11
11 12
12 10
13 14
14 15
15 13
16 17
17 18
18 16
19 20
20 21
21 19
22 23
23 24
24 22
25 26
26 27
27 25
28 29
29 30
30 28
31 32
33 34
*/
    
/*
34 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
*/
}