import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;



public class Solution {
    
    static class Target {
        int node;
        int weight;
        public Target(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        
        public String toString() {
            return Integer.toString(node) + "-" + Integer.toString(weight);
        }
    }
    
    static class Vertex implements Comparable<Vertex>{
        int bigCatPosition, smallCatPosition;
        int k;
        boolean[] missingFish;
        int countMissing;
        int dbc, dsc;
        Vertex previous;
        
        public String toString() {
            return "bc:" + Integer.toString(bigCatPosition) + ",sc:" + Integer.toString(smallCatPosition)
                + ",missingFish:" + Arrays.toString(missingFish) 
                + ",dbc:" + Integer.toString(dbc) + ",dsc:" + Integer.toString(dsc);
                //+ ",previous:" + ((previous == null) ? "null" : previous.toString());
        }
        
        public Vertex(int k, List<List<Boolean>> fishSold) {
            this.bigCatPosition = 0;
            this.smallCatPosition = 0;
            this.k = k;
            this.countMissing = k;
            this.missingFish = new boolean[k];
            for (int i = 0; i < k; i++) {
                missingFish[i] = !fishSold.get(0).get(i);
                if (!missingFish[i]) countMissing--;
            }
            dbc = 0;
            dsc = 0;
            previous = null;
        }

        public Vertex(int n, int k) {
            this.bigCatPosition = n-1;
            this.smallCatPosition = n-1;
            this.k = k;
            this.missingFish = new boolean[k];
            countMissing = 0;
            dbc = 0;
            dsc = 0;
            previous = null;
        }

        public Vertex(Target tbc, Target tsc, Vertex currVertex, List<List<Boolean>> fishSold) {
            this.bigCatPosition = tbc.node;
            this.smallCatPosition = tsc.node;
            this.k = currVertex.k;
            this.countMissing = k;
            this.missingFish = new boolean[k];
            for (int i = 0; i < k; i++) {
                missingFish[i] = currVertex.missingFish[i] 
                        && !fishSold.get(tbc.node).get(i) && !fishSold.get(tsc.node).get(i);
                if (!missingFish[i]) countMissing--;
            }
            dbc = -1;
            dsc = -1;
            previous = null;
            
        }

        @Override
        public int compareTo(Vertex o) {
            
            int thisDistance = Math.max(this.dbc, this.dsc);
            int thatDistance = Math.max(o.dbc, o.dsc);
            
            if (thisDistance < thatDistance) return -1;
            if (thisDistance > thatDistance) return 1;
            
            if (this.countMissing < o.countMissing) return -1;
            if (this.countMissing > o.countMissing) return 1;

            int thisMin = Math.min(this.bigCatPosition, this.smallCatPosition);
            int thisMax = Math.max(this.bigCatPosition, this.smallCatPosition);
            int thatMin = Math.min(o.bigCatPosition, o.smallCatPosition);
            int thatMax = Math.max(o.bigCatPosition, o.smallCatPosition);
            if (thisMin < thatMin) return -1;
            if (thisMin > thatMin) return 1;
            if (thisMax < thatMax) return -1;
            if (thisMax > thatMax) return 1;

            for (int i = 0; i < k; i++) {
                int booleanCompare = Boolean.compare(this.missingFish[i], o.missingFish[i]);
                if (booleanCompare != 0) {
                    return booleanCompare;
                }
            }
            return 0;
        }

        public int distance() {
            return Math.max(dbc, dsc);
        }

    }
    
    static class CompareIgnoreDistance implements Comparator<Vertex> {
        
        private int k;

        public CompareIgnoreDistance(int k) {
            this.k = k;
        }

        @Override
        public int compare(Vertex o1, Vertex o2) {
            if (o1.countMissing < o2.countMissing) return -1;
            if (o1.countMissing > o2.countMissing) return 1;

            /*
            int thisMin = Math.min(o1.bigCatPosition, o1.smallCatPosition);
            int thisMax = Math.max(o1.bigCatPosition, o1.smallCatPosition);
            int thatMin = Math.min(o2.bigCatPosition, o2.smallCatPosition);
            int thatMax = Math.max(o2.bigCatPosition, o2.smallCatPosition);
            if (thisMin < thatMin) return -1;
            if (thisMin > thatMin) return 1;
            if (thisMax < thatMax) return -1;
            if (thisMax > thatMax) return 1;
            */
            if (o1.bigCatPosition < o2.bigCatPosition) return -1;
            if (o1.bigCatPosition > o2.bigCatPosition) return 1;
            if (o1.smallCatPosition < o2.smallCatPosition) return -1;
            if (o1.smallCatPosition > o2.smallCatPosition) return 1;

            for (int i = 0; i < this.k; i++) {
                int booleanCompare = Boolean.compare(o1.missingFish[i], o2.missingFish[i]);
                if (booleanCompare != 0) {
                    return booleanCompare;
                }
            }
            return 0;        
            }
        
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int k = scan.nextInt();
        
        List<List<Boolean>> fishSold = new ArrayList<List<Boolean>>(n);
        for (int i = 0; i < n; i++) {
            int currn = scan.nextInt();
            
            List<Boolean> l = new ArrayList<Boolean>(k);
            for (int j = 0; j < k; j++) {
                l.add(false);
            }
            for (int j = 0; j < currn; j++) {
                int currFishType = scan.nextInt()-1;
                l.set(currFishType, true);
            }
            fishSold.add(l);
        }
        
        Map<Integer, List<Target>> edges = new HashMap<Integer, List<Target>>();
        for (int i = 0; i < m; i++) {
            int u = scan.nextInt()-1;
            int v = scan.nextInt()-1;
            int w = scan.nextInt();
            addToEdges(edges, u, v, w);
            addToEdges(edges, v, u, w);
        }
        
        addToEdges(edges, n-1, n-1, 0);
        
        Vertex source = new Vertex(k, fishSold);
        
        TreeSet<Vertex> toVisitGetMin = new TreeSet<Vertex>();
        TreeSet<Vertex> toVisitFind = new TreeSet<Vertex>(new CompareIgnoreDistance(k));
        
        TreeSet<Vertex> visited = new TreeSet<Vertex>(new CompareIgnoreDistance(k));
        toVisitGetMin.add(source);
        toVisitFind.add(source);
        
        int minSoFar = -1;
        while (!toVisitGetMin.isEmpty()) {
            Vertex currVertex = toVisitGetMin.iterator().next();
            //System.out.println(currVertex.toString());
            visited.add(currVertex);
            toVisitGetMin.remove(currVertex);
            toVisitFind.remove(currVertex);
            
            for (Target tbc: edges.get(currVertex.bigCatPosition)) {
                for (Target tsc: edges.get(currVertex.smallCatPosition)) {
                    
                    int altBc = currVertex.dbc + tbc.weight; 
                    int altSc = currVertex.dsc + tsc.weight; 
                    int alt = Math.max(altBc, altSc);
                    
                    if (minSoFar != -1 && alt >= minSoFar) continue;
                    
                    Vertex v = new Vertex(tbc, tsc, currVertex, fishSold);
                    
                    if (toVisitFind.contains(v)) {
                        Vertex targetVertex = toVisitFind.floor(v);
                        if (alt < targetVertex.distance()) {
                            toVisitFind.remove(targetVertex);
                            toVisitGetMin.remove(targetVertex);
                            targetVertex.dbc = altBc;
                            targetVertex.dsc = altSc;
                            targetVertex.previous = currVertex;
                            
                            minSoFar = updateMinSoFar(n, toVisitGetMin, toVisitFind, minSoFar, altBc, altSc,
                                    targetVertex);
                            
                        }
                    } else if (visited.contains(v)) {
                        Vertex targetVertex = visited.floor(v);
                        if (alt < targetVertex.distance()) {
                            visited.remove(targetVertex);
                            targetVertex.dbc = altBc;
                            targetVertex.dsc = altSc;
                            targetVertex.previous = currVertex;
                            minSoFar = updateMinSoFar(n, toVisitGetMin, toVisitFind, minSoFar, altBc, altSc,
                                    targetVertex);
                        }
                    } else {
                        v.dbc = altBc;
                        v.dsc = altSc;
                        v.previous = currVertex;
                        minSoFar = updateMinSoFar(n, toVisitGetMin, toVisitFind, minSoFar, altBc, altSc,
                                v);
                    }
                    
                }
            }
        }
        
        System.out.println(minSoFar);
        
    }

    private static int updateMinSoFar(int n, TreeSet<Vertex> toVisitGetMin, TreeSet<Vertex> toVisitFind, int minSoFar,
            int altBc, int altSc, Vertex targetVertex) {
        if (targetVertex.bigCatPosition == n-1 && targetVertex.smallCatPosition == n-1 && targetVertex.countMissing == 0
                && (minSoFar == -1 || Math.max(altBc, altSc) < minSoFar)) {
            minSoFar = Math.max(altBc, altSc);
        } else if (minSoFar == -1 ||  Math.max(altBc, altSc) <= minSoFar) {
            toVisitFind.add(targetVertex);
            toVisitGetMin.add(targetVertex);
        }
        return minSoFar;
    }

    private static void addToEdges(Map<Integer, List<Target>> edges, int u, int v, int w) {
        Target t = new Target(v, w);
        if (edges.containsKey(u)) {
            edges.get(u).add(t);
        } else {
            List<Target> l = new LinkedList<Target>();
            l.add(t);
            edges.put(u, l);
        }
    }
}