import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

public class Solution {

    // leave empty to read from stdin/stdout
    private static final String TASK_NAME_FOR_IO = "";

    // file names
    private static final String FILE_IN = TASK_NAME_FOR_IO + ".in";
    private static final String FILE_OUT = TASK_NAME_FOR_IO + ".out";

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tokenizer = new StringTokenizer("");

    public static void main(String[] args) {
        new Solution().run();
    }

    int n, k;
    long[] a, d;

    private void solve() throws IOException {
        // timing();
        // stress();

        n = nextInt();
        k = nextInt();

        a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextLong();
        }

        out.println(solveFast());
    }

    private void timing() {
        n = 100000;
        k = 100000;
        a = new long[n];
        Random r = new Random(123456789L);
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(2000000000);
        }
        solveFast();
    }

    private void stress() {
        Random r = new Random(123456789L);

        int tcNum = 10000000;
        for (int tc = 0; tc < tcNum; tc++) {
            System.out.println("Stress: " + tc);

            n = 1 + r.nextInt(50);
            k = 1 + r.nextInt(n);

            a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = r.nextInt(2000000000);
                // a[i] = r.nextInt(4);
            }

            long ans1 = solveFast();
            long ans2 = solveNaive();
            long ans3 = ans2;
            // long ans3 = solveNaiveSlow();

            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println(n + " " + k);
                for (int i = 0; i < n; i++) {
                    System.out.println(a[i]);
                }

                throw new IllegalStateException(ans1 + " - " + ans2 + " - " + ans3);
            }
        }
        System.err.println("Stress test passed");
    }

    long[] copy(long[] src, int oldLen, int newLen) {
        long[] dst = new long[newLen];
        System.arraycopy(src, 0, dst, 0, oldLen);
        return dst;
    }

    int D_OFFSET, tSize;
    TreeNode[] md;

    class TreeNode {

        boolean needPushdown;
        long sumCountPDown;
        long sumP;

        int lo, hi;
        boolean isLeaf;

        TreeNode(int lo, int hi, boolean isLeaf) {
            this.lo = lo;
            this.hi = hi;
            this.isLeaf = isLeaf;
        }

    }

    private TreeNode buildTree(int k, int a, int b) {
        if (k >= tSize) {
            int kIdx = k - tSize;
            if (kIdx < 4 * n) {
                // we are hitting a real node, so calculate P and D^P
                md[k] = new TreeNode(a, b, true);
            } else {
                // fake node (no impact on sum, no impact on multiplication)
                md[k] = new TreeNode(a, b, true);
            }
            return md[k];
        }

        int mid = (a + b) / 2;
        buildTree(k << 1, a, mid);
        buildTree((k << 1) + 1, mid + 1, b);
        md[k] = new TreeNode(a, b, false);
        return md[k];
    }

    private void doPushdown(int k) {
        if (md[k].needPushdown) {
            {
                int posL = k << 1;

                long sum = md[k].sumCountPDown;
                md[posL].sumP += sum;

                if (md[posL].needPushdown) {
                    md[posL].sumCountPDown += md[k].sumCountPDown;
                } else if (!md[posL].isLeaf) {
                    md[posL].sumCountPDown = md[k].sumCountPDown;
                    md[posL].needPushdown = true;
                }
            }

            {
                int posR = (k << 1) + 1;

                long sum = md[k].sumCountPDown;
                md[posR].sumP += sum;

                if (md[posR].needPushdown) {
                    md[posR].sumCountPDown += md[k].sumCountPDown;
                } else if (!md[posR].isLeaf) {
                    md[posR].sumCountPDown = md[k].sumCountPDown;
                    md[posR].needPushdown = true;
                }
            }

            md[k].needPushdown = false;
        }
    }

    private long searchMaxFast(int k, int a, int b, int lo, int hi) {
        if (hi < a || lo > b || a > b || lo > hi) {
            return 0;
        }

        if (a == lo && b == hi) {
            return md[k].sumP;
        }

        doPushdown(k);

        int posL = k << 1;
        int posR = posL + 1;
        int mid = (a + b) / 2;
        long l = searchMaxFast(posL, a, mid, lo, Math.min(hi, mid));
        long r = searchMaxFast(posR, mid + 1, b, Math.max(lo, mid + 1), hi);
        return Math.max(l, r);
    }

    private void increaseSumFast(int k, int a, int b, int lo, int hi, long v) {
        if (hi < a || lo > b || a > b || lo > hi) {
            return;
        }

        if (a == lo && b == hi) {
            md[k].sumP += v;

            // pushdown needs to happen to children at some point
            if (!md[k].isLeaf) {

                if (md[k].needPushdown) {
                    md[k].sumCountPDown += v;
                } else {
                    md[k].needPushdown = true;
                    md[k].sumCountPDown = v;
                }

            }
            return;
        }

        doPushdown(k);

        int posL = k << 1;
        int posR = posL + 1;
        int mid = (a + b) / 2;
        increaseSumFast(posL, a, mid, lo, Math.min(hi, mid), v);
        increaseSumFast(posR, mid + 1, b, Math.max(lo, mid + 1), hi, v);
        md[k].sumP = Math.max(md[posL].sumP, md[posR].sumP);
    }

    private long solveFast() {
        a = copy(a, n, n + 1);
        n++;

        D_OFFSET = 2 * n;
        d = new long[4 * n];

        // build a tree out of d^p products
        tSize = 1;
        while (tSize < 4 * n) {
            tSize <<= 1;
        }

        //  1   2 3     4 5 6 7
        // [tsize - 1]  [tsize]
        int alloc = tSize << 1;
        md = new TreeNode[alloc];
        buildTree(1, 0, tSize - 1);

        long answer = 0;
        for (int i = 0; i < n; i++) {
            // d[i] - the answer if the sequence ends on i-th element and i-th element is erased
            // let's assume we will be taking the block of 'len' elements, where 'len' <= 'k'. then:
            // d[i] = d[i - len - 1] + sum( a[i - len + 1] ... a[i] )

            // example: i = 5, len = 2
            //   i-4     i-3        i-2       i-1       i
            //   1        2          3         4        5
            //          SKIP       TAKE      TAKE     SKIP
            //         I-LEN-1

            long maxValue = getMaxValueDFast(i - k - 1 + D_OFFSET, i - 1 + D_OFFSET);
            answer = Math.max(answer, maxValue);
            adjustDFast(i + D_OFFSET, i + D_OFFSET, maxValue);
            adjustDFast(i - k + D_OFFSET, i - 1 + D_OFFSET, a[i]);
        }

        return answer;
    }

    private void adjustDFast(int lo, int hi, long value) {
        increaseSumFast(1, 0, tSize - 1, lo, hi, value);
    }

    private void adjustD(int lo, int hi, long value) {
        for (int i = lo; i <= hi; i++) {
            d[i] += value;
        }
    }

    private long getMaxValueDFast(int lo, int hi) {
        return searchMaxFast(1, 0, tSize - 1, lo, hi);
    }

    private long getMaxValueD(int lo, int hi) {
        long result = 0;
        for (int i = lo; i <= hi; i++) {
            result = Math.max(result, d[i]);
        }
        return result;
    }

    private long solveNaive() {
        d = new long[n + 1];
        for (int i = 0; i < n; i++) {
            // skip i-th element
            d[i + 1] = Math.max(d[i + 1], d[i]);

            // take i-th element, some of the subsequent ones
            long sum = 0;
            for (int j = 1; (j <= k) && (i + j <= n); j++) {
                sum += a[i + j - 1];

                // if we run into the last element, this is okay. otherwise we have to skip the last one
                if (i + j - 1 == n - 1) {
                    d[i + j] = Math.max(d[i + j], d[i] + sum);
                } else {
                    d[i + j + 1] = Math.max(d[i + j + 1], d[i] + sum);
                }
            }
        }

        long answer = 0;
        for (long v : d) {
            answer = Math.max(answer, v);
        }
        return answer;
    }

    long answerSlow;

    private long solveNaiveSlow() {
        answerSlow = 0;
        recSlow(0, 0, 0);
        return answerSlow;
    }

    private void recSlow(int pos, int taken, long sum) {
        if (pos >= n) {
            answerSlow = Math.max(answerSlow, sum);
            return;
        }

        if (taken < k) {
            // try to take
            recSlow(pos + 1, taken + 1, sum + a[pos]);
        }

        // skip
        recSlow(pos + 1, 0, sum);
    }

    public void run() {
        long timeStart = System.currentTimeMillis();

        boolean fileIO = TASK_NAME_FOR_IO.length() > 0;
        try {

            if (fileIO) {
                in = new BufferedReader(new FileReader(FILE_IN));
                out = new PrintWriter(new FileWriter(FILE_OUT));
            } else {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(new OutputStreamWriter(System.out));
            }

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        long timeEnd = System.currentTimeMillis();

        if (fileIO) {
            System.out.println("Time spent: " + (timeEnd - timeStart) + " ms");
        }
    }

    private String nextToken() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            String line = in.readLine();
            if (line == null) {
                return null;
            }
            tokenizer = new StringTokenizer(line);
        }
        return tokenizer.nextToken();
    }

    private int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    private long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

}