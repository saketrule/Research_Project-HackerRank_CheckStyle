import java.io.*;
import java.util.*;

public class Solution {

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new Solution().run();
    }

    public void run() {
        try {
            long t1 = System.currentTimeMillis();
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(System.out);
           
            Locale.setDefault(Locale.US);
            solve();
            in.close();
            out.close();
            long t2 = System.currentTimeMillis();
            System.err.println("Time = " + (t2 - t1));
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            System.exit(-1);
        }
    }

    String readString() throws IOException {
        while (!tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }

    int readInt() throws IOException {
        return Integer.parseInt(readString());
    }

    long readLong() throws IOException {
        return Long.parseLong(readString());
    }

    double readDouble() throws IOException {
        return Double.parseDouble(readString());
    }

    // solution
    void solve() throws IOException {
        int n = readInt(), k = readInt();
        long[] profit = new long[n + 2];
        for (int i = 1; i <= n; i++)
            profit[i] = readLong();
        n++;//add zero billboard to the end
        MaxTree tree = new MaxTree(n + 20);
        long res = 0;
        for (int i = 1; i <= n; i++)
        {
            long value = tree.getMax(1, 0, n, Math.max(0, i - k - 1), Math.max(0, i - 1));
            tree.increase(1, 0, n, 0, i - 1, profit[i]);
            tree.increase(1, 0, n, i, i, value);
            res = Math.max(res, value);
           // System.out.println(i + " = " + value);
        }
        out.println(res);
    }
}

class MaxTree {

    long[] max;
    long[] push;

    MaxTree(int size) {
        max = new long[size * 8];
        push = new long[size * 8];
    }
    
    void increase(int id, int l, int r, int lv, int rv, long incr)
    {
      //  if (id == 1)
      //      System.out.println("increase " + id + " " + l + "-" + r + " " + lv + "-" + rv + " " + incr);
        if (l == lv && r == rv)
        {
            max[id] += incr;
            push[id] += incr;
            return;
        }
        
        if (push[id] > 0)
            pushNode(id);
        
        if (lv <= (l + r) / 2)
            increase(id * 2, l, (l + r) / 2, lv, Math.min(rv, (l + r) / 2), incr);
        if (rv >= (l + r) / 2 + 1)
            increase(id * 2 + 1, (l + r) / 2 + 1, r, Math.max(lv, (l + r) / 2 + 1), rv, incr);
        max[id] = Math.max(max[id * 2], max[id * 2 + 1]);
    }
    
    long getMax(int id, int l, int r, int lv, int rv)
    {
      //  if (id == 1)
       ///     System.out.println("getMax " + id + " " + l + "-" + r + " " + lv + "-" + rv);
        if (l == lv && r == rv)
            return max[id];
        
        if (push[id] > 0)
            pushNode(id);
        
        long res = 0;
        if (lv <= (l + r) / 2)
            res = Math.max(res, getMax(id * 2, l, (l + r) / 2, lv, Math.min(rv, (l + r) / 2)));
        if (rv >= (l + r) / 2 + 1)
            res = Math.max(res, getMax(id * 2 + 1, (l + r) / 2 + 1, r, Math.max(lv, (l + r) / 2 + 1), rv));
     //   if (id == 1)
     //       System.out.println("return " + res);
        return res;
    }
    
    void pushNode(int id)
    {
        max[id * 2] += push[id];
        max[id * 2 + 1] += push[id];
        push[id * 2] += push[id];
        push[id * 2 + 1] += push[id];
        push[id] = 0;
    }
}