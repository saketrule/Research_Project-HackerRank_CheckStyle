import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {


    static int compare(BitSet a, BitSet b) {
        int aN = a.previousSetBit(a.size()-1);
        int bN = b.previousSetBit(b.size()-1);
        while (aN >= 0 && bN >= 0 && aN == bN) {
            aN = a.previousSetBit(aN-1);
            bN = b.previousSetBit(bN-1);
        }
        return aN - bN;
    }

    static BitSet add(BitSet a, BitSet b) {
        BitSet sum;
        BitSet addend;
        if (compare(a, b) >= 0) {
            sum = (BitSet)a.clone();
            addend = b;
        }
        else {
            sum = (BitSet)b.clone();
            addend = a;
        }
        boolean carry = false;
        for (int n = addend.nextSetBit(0); n >= 0; n = addend.nextSetBit(n+1)) {
            carry = sum.get(n);
            sum.flip(n);
            if (carry) {
                int maxBit = sum.previousSetBit(sum.size()-1);
                for (int j=n+1; carry; ++j) {
                    if (j <= maxBit) {
                        carry = sum.get(j);
                        sum.flip(j);
                    }
                    else {
                        carry = false;
                        sum.set(j);
                    }
                }
            }
        }

        return sum;
    }

    static String displayString(BitSet a) {
        return displayString(a, false);
    }

    static String displayString(BitSet a, boolean showAll) {
        int M = a != null ? a.size() : 0;
        return displayString(a, M, showAll);
    }

    static String displayString(BitSet a, int M) {
        return displayString(a, M, false);
    }

    static String displayString(BitSet a, int M, boolean showAll) {
        StringBuilder sb = new StringBuilder();
        if (a == null) {
            while (M-- > 0) {
                sb.append('0');
            }
        }
        else if (!showAll && a.cardinality() == 0) {
            sb.append('0');
        }
        else {
            int maxBit = a.previousSetBit(a.size()-1);
            int n = showAll ? Math.max(M-1, maxBit) : maxBit;
            while (n >= 0) {
                sb.append(a.get(n) ? '1' : '0');
                --n;
            }
        }
        return sb.toString();
    }

    static void display(BitSet[][] dist, int M, boolean showAll) {
        StringBuilder sb = new StringBuilder();
        for (int j=0; j < dist.length; ++j) {
            sb.append(displayString(dist[j][0], M, showAll));
            for (int k=1; k < dist[j].length; ++k) {
                sb.append(", ");
                sb.append(displayString(dist[j][k], M, showAll));
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    static void test1() {
        BitSet a = new BitSet(4);
        BitSet b = new BitSet(4);
        BitSet sum;

        a.set(0);
        b.set(0);
        sum = testAdd(4, a, b);
        testCompare(4, a, b);

        a.set(1);
        sum = testAdd(4, sum, a);
        testCompare(4, sum, a);
        testCompare(4, a, sum);
        testCompare(4, sum, sum);

        a.set(2);
        sum = testAdd(4, sum, a);

        a.set(3);
        sum = testAdd(4, sum, a);

        sum = testAdd(4, sum, sum);
        testCompare(4, sum, a);
        testCompare(4, a, sum);
        testCompare(4, sum, sum);
    }

    static void testCompare(int M, BitSet a, BitSet b) {
        int cmp = compare(a, b);

        StringBuilder sb = new StringBuilder();
        sb.append(displayString(a, M, true));
        sb.append(cmp < 0 ? " < " : cmp > 0 ? " > " : " == ");
        sb.append(displayString(b, M, true));
        System.out.println(sb.toString());
        System.out.println("\n");
    }

    static int getMinWidth(int M, String first, List<String> rest) {
        int minWidth = Math.max(M, first.length());
        for (String s : rest) {
            minWidth = Math.max(minWidth, s.length());
        }
        return minWidth;
    }

    static BitSet testAdd(int M, BitSet first, BitSet second, BitSet... rest) {
        LinkedList<String> numStrings = new LinkedList<String>();

        BitSet sum = add(first, second);
        numStrings.add(displayString(first, M, true));
        numStrings.add(displayString(second, M, true));
        for (BitSet n : rest) {
            sum = add(sum, n);
            numStrings.add(displayString(n, M, true));
        }
        String sumStr = displayString(sum, M, true);

        int minWidth = getMinWidth(M, sumStr, numStrings);
        System.out.printf("minWidth=%d\n", minWidth);

        String FMT = "%" + minWidth + "s\n";
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        sb.append(String.format(FMT, numStrings.remove()));
        while (!numStrings.isEmpty()) {
            sb.append(" + ");
            sb.append(String.format(FMT, numStrings.remove()));
        }
        sb.append("----------\n");
        sb.append("   ");
        sb.append(String.format(FMT, sumStr));

        System.out.println(sb.toString());
        System.out.println("\n");

        return sum;
    }


    static class Edge implements Comparable<Edge> {
        final int A;
        final int B;
        final int M;
        final BitSet len;
        Edge(int a, int b, int m, int r) {
            this.A   = Math.min(a,b);
            this.B   = Math.max(a,b);
            this.M   = m;
            this.len = new BitSet(m);
            this.len.set(r);
        }

        public int compareTo(Edge that) {
            if (this == that) return 0;
            if (that == null) return -1;

            int cmpval = compare(this.len, that.len);
            if (cmpval != 0)       return cmpval;
            if (this.A != that.A) return this.A - that.A;
            return this.B - that.B;
        }

        public String toString() {
            return String.format("Edge{ len=%s %4d -> %-4d }", displayString(this.len, M, true), this.A, this.B);
        }

    }

    static class DisjointSet {
        final int N;
        int[] parent;
        int[] rank;


        DisjointSet(int n) {
            this.N = n;
            this.parent = new int[N+1];
            this.rank   = new int[N+1];
            for (int j=1; j <= this.N; ++j) {
                this.parent[j] = j;
                this.rank[j]   = 0;
            }
        }

        int find(int x) {
            while(x != parent[x]){
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        void union(int A, int B) {
            int a = this.find(A);
            int b = this.find(B);
            if (a == b) return;
            if (this.rank[a] < this.rank[b]) {
                this.parent[a] = b;
            }
            else if (this.rank[a] > this.rank[b]) {
                this.parent[b] = a;
            }
            else {
                this.parent[b] = a;
                ++this.rank[a];
            }
        }

        int size() {
            int size = 0;
            for (int j=1; j <= this.N; ++j) {
                if (this.parent[j] == j) ++size;
            }
            return size;
        }



        void display() {
            for (int j=1; j <= this.N; ++j) {
                System.out.printf("%4d) p=%-4d r=%d\n", j, this.parent[j], this.rank[j]);
            }
        }

        void compute_ranks() {
            int[] depth = new int[N+1];
            for (int j=1; j <= this.N; ++j) {
                depth[j] = 0;
            }

            for (int j=1; j <= this.N; ++j) {
                int x = j;
                int currDepth = 0;
                while(x != parent[x]){
                    ++currDepth;
                    x = parent[x];
                    if (depth[x] < currDepth) depth[x] = currDepth;
                }
            }

            for (int j=1; j <= this.N; ++j) {
                System.out.printf("%4d) p=%-4d r=%-2d d=%d\n", j, this.parent[j], this.rank[j], depth[j]);
            }

        }

    }





    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            String[] vals;

            line = in.readLine().trim();
            vals = line.split(" +", 2);
            int N = Integer.parseInt(vals[0]);
            int M = Integer.parseInt(vals[1]);

            LinkedList<Edge> sortedEdges = new LinkedList<Edge>();
            LinkedList<Edge> mstEdges = new LinkedList<Edge>();

            int x,y,r;
            for (int j=0; j < M; ++j) {
                line = in.readLine().trim();
                vals = line.split(" +", 3);
                x = Integer.parseInt(vals[0]);
                y = Integer.parseInt(vals[1]);
                r = Integer.parseInt(vals[2]);
                Edge e = new Edge(x, y, M, r);
//System.out.println(e);
                sortedEdges.add(e);
            }

            Collections.sort(sortedEdges);
if (DEBUG && true) {
    Iterator<Edge> it = sortedEdges.iterator();
    while (it.hasNext()) {
        System.out.println(it.next());
    }
}

            Map<Integer, Set<Integer>> mstAdj = new HashMap<Integer, Set<Integer>>();
            DisjointSet A = new DisjointSet(N);
            for (Edge e : sortedEdges) {
                int a = A.find(e.A);
                int b = A.find(e.B);
                if (a != b) {
                    mstEdges.add(e);
                    A.union(a, b);
                    if (!mstAdj.containsKey(e.A)) mstAdj.put(e.A, new HashSet<Integer>());
                    if (!mstAdj.containsKey(e.B)) mstAdj.put(e.B, new HashSet<Integer>());
                    mstAdj.get(e.A).add(e.B);
                    mstAdj.get(e.B).add(e.A);
                }
            }

//System.out.println("\n\n=============================\n");
//            Collections.sort(mstEdges);
if (DEBUG && true) {
    Iterator<Edge> it = mstEdges.iterator();
    while (it.hasNext()) {
        System.out.println(it.next());
    }
}


//if (true) { System.out.println(mstAdj); return; }

            BitSet sum = new BitSet(M);
            for (Edge e : mstEdges) {
                int subtreeSize = countSubtree(e.A, e.B, mstAdj);
                int paths = subtreeSize * (N - subtreeSize);
                while (paths-- > 0) {
                    sum = add(sum, e.len);
                }
            }


            System.out.println(displayString(sum, M));




        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }


    static int countSubtree(int n, int parent, Map<Integer, Set<Integer>> adj) {
        int size = 1;
        Set<Integer> children = adj.get(n);
//System.out.printf("countSubtree(n=%d, p=%d, children=%s)\n", n, parent, children.toString());
        for (int c : adj.get(n)) {
            if (c == parent) continue;
            size += countSubtree(c, n, adj);
        }
        return size;
    }


    public static final boolean DEBUG = false;
}