import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static SortedMap<Integer, SortedSet<Integer> >  roadMap; 
    static Set<Integer> visitedDFS;
    // fish supply from node 1: node to bitmask
    static Map<Integer, Map<Integer, Integer> > supply;
    static Map<Integer, Integer> bmMap;

    public static void dfs(int bm, int index, int dist) {
        
        Map<Integer, Integer> sp = supply.get(bm);
        if (sp == null) { sp = new HashMap<Integer, Integer>(); supply.put(bm, sp); }
        Integer olddist = sp.get(index);
        if (olddist == null || olddist > dist) {
            sp.put(index, dist);
        }
        //System.out.println("DDDDDD " + index + " " + dist + " " + visitedDFS + " " + bm);

        SortedSet<Integer> st = roadMap.get(index);
        if (st == null) return;
        for (Integer ii : st) {
            int w = ii / 10000;
            int nn = ii % 10000;
            if (visitedDFS.contains(nn)) continue;
            visitedDFS.add(nn);
            dfs(bm | bmMap.get(nn), nn, dist + w);
            visitedDFS.remove(nn);
        }
    }
    
    public static void main(String[] args) {
 try{
    BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        String [] s1 = br.readLine().replaceAll("\\n\\r", "").split(" ");
        final int n = Integer.parseInt(s1[0]);
        final int m = Integer.parseInt(s1[1]);
        final int k = Integer.parseInt(s1[2]);
        
        bmMap = new HashMap<Integer, Integer>();
        
        for (int i=1; i <= n; i++) {
            s1 = br.readLine().replaceAll("\\n\\r", "").split(" ");
            int t = Integer.parseInt(s1[0]);
            int bm = 0;
            for (int j=0; j < t; j++) {
                int f = Integer.parseInt(s1[j+1]);
                bm += (1 << f);
            }
            bmMap.put(i, bm);
        }

        // SortedSet in roadMap is sorted by weight
        roadMap = new TreeMap<Integer, SortedSet<Integer> >();
        
        for (int i=0; i < m; i++) {
            s1 = br.readLine().replaceAll("\\n\\r", "").split(" ");
            int n1 = Integer.parseInt(s1[0]);
            int n2 = Integer.parseInt(s1[1]);
            int w = Integer.parseInt(s1[2]);
            
            SortedSet<Integer> mm = roadMap.get(n1);
            if (mm == null) { mm = new TreeSet<Integer>(); roadMap.put(n1, mm); }
            mm.add(n2 + 10000*w);

            mm = roadMap.get(n2);
            if (mm == null) { mm = new TreeSet<Integer>(); roadMap.put(n2, mm); }
            mm.add(n1 + 10000*w);
        }
        
        Map<Integer, Integer> distN = new HashMap<Integer, Integer> ();
        
        // order by weights, to get the closest nodes first
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(n);
        distN.put(n, 0);
        Set<Integer> visited = new HashSet<Integer>();
        while(!q.isEmpty()) {
            int v = q.poll();
            if (visited.contains(v)) continue;
            visited.add(v);
            int prev = distN.get(v);
            SortedSet<Integer> st = roadMap.get(v);
            if (st == null) continue;
            for (Integer ii : st) {
                int w = ii / 10000;
                int nn = ii % 10000;
                Integer d = distN.get(nn);
                if (d == null || d > prev + w) {
                    distN.put(nn, prev + w);
                }
                q.add(nn);
            }
        }

        supply = new HashMap<Integer, Map<Integer, Integer> >();
        visitedDFS = new TreeSet<Integer>();
        visitedDFS.add(1);
        dfs(bmMap.get(1), 1, 0);
        
        //System.out.println("AAAAAA2 " + supply);

        Map<Integer, Integer> spath = new HashMap<Integer, Integer>();
        for (Integer cat : supply.keySet()) {
            Map<Integer, Integer> vv = supply.get(cat);
            int sp = Integer.MAX_VALUE;
            for (Integer nd : vv.keySet()) {
                int p = vv.get(nd) + distN.get(nd);
                if (p < sp) sp = p;
            }
            spath.put(cat, sp);
        }
        
        final int ALLFISH = (1 << (k+1)) - 2;
        int mindist = Integer.MAX_VALUE;
        for (Integer cat1 : supply.keySet()) {
            for (Integer cat2 : supply.keySet()) {
                if (((cat1 | cat2) & ALLFISH) != ALLFISH) continue;
                int d1 = spath.get(cat1);
                int d2 = spath.get(cat2);
                if (d2 < d1) d2 = d1;
                if (d2 < mindist) mindist = d2;
            }
        }
        System.out.println(mindist);
       }catch(IOException io){
    io.printStackTrace();
    }
    }
}