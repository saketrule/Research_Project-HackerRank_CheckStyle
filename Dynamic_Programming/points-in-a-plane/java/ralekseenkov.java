import java.io.*;
import java.util.*;

public class Solution implements Runnable {

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

    int n;
    int[] x, y;

    private void solve() throws IOException {
        int tc = nextInt();

        for (int tcIdx = 0; tcIdx < tc; tcIdx++) {
            n = nextInt();

            x = new int[n];
            y = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = nextInt();
                y[i] = nextInt();
            }

            {
                Answer faster = solveFaster();
                out.println(faster.moves + " " + faster.ways);
            }
            {
                // Answer naive = solveNaive();
                // out.println(naive.moves + " " + naive.ways);
            }
        }

        /*
        int tcIdx = 0;
        Set<Answer> set = new HashSet<Answer>();
        long timeNaive = 0;
        long timeFaster = 0;

        for (; ; ) {
            tcIdx++;

            if (tcIdx > Integer.MAX_VALUE) {
                System.out.println("Time naive: " + timeNaive);
                System.out.println("Time faster: " + timeFaster);
                break;
            }

            if (tcIdx % 100 == 0) {
                System.out.println("TC: " + tcIdx);
                System.out.println("Time naive: " + timeNaive);
                System.out.println("Time faster: " + timeFaster);
            }

            gen();

            Answer naive = solveNaive();
            timeNaive += naive.time;

            Answer fast = solveFaster();
            timeFaster += fast.time;

            if (!naive.equals(fast)) {
                System.out.println(n);
                for (int i = 0; i < n; i++) {
                    System.out.println(x[i] + " " + y[i]);
                }
                throw new IllegalStateException(naive.moves + " vs. " + fast.moves + " (moves), " + naive.ways + " vs. " + fast.ways + " (ways)");
            }

            if (!set.contains(naive)) {
                set.add(naive);
                // System.out.println(naive.moves + " " + naive.ways);
            }
        }
        */

        /*
        for (int tcIdx = 0; tcIdx < 50; tcIdx++) {
            System.err.println(tcIdx);
            gen();
            solveFaster();
        }
        */

    }

    Random r = new Random(1212121L);

    @SuppressWarnings({"UnusedDeclaration"})
    private void gen() {
        n = 5 + r.nextInt(12);
        x = new int[n];
        y = new int[n];

        int cnt = 0;
        while (cnt < n) {

            int x0 = r.nextInt() % 5;
            int y0 = r.nextInt() % 5;
            int dx = r.nextInt() % 4;
            int dy = r.nextInt() % 4;

            int steps = 1 + r.nextInt(n - cnt);
            for (int i = 0; i < steps; i++) {

                boolean exists = false;
                for (int j = 0; j < cnt; j++)
                    if (x[j] == x0 && y[j] == y0) {
                        exists = true;
                        break;
                    }

                if (exists) {
                    break;
                }

                x[cnt] = x0;
                y[cnt] = y0;

                x0 += dx;
                y0 += dy;
                cnt++;
            }

        }
    }

    int MOD = 1000000007;

    class Answer {
        int moves, ways, time;

        Answer(int moves, int ways, int time) {
            this.moves = moves;
            this.ways = ((ways % MOD) + MOD) % MOD;
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Answer answer = (Answer) o;

            if (moves != answer.moves) return false;
            if (ways != answer.ways) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = moves;
            result = 31 * result + ways;
            return result;
        }
    }

    static int[] bitCount;

    static {
        bitCount = new int[1 << 16];
        for (int mask = 1; mask < bitCount.length; mask++) {
            bitCount[mask] = bitCount[mask & (mask - 1)] + 1;
        }
    }

    private Answer solveFaster() {
        long timeStart = System.currentTimeMillis();


        // calculate collinearity masks for each pair of points
        int[][] collinearityMask = new int[n][n];
        for (int i1 = 0; i1 < n; i1++)
            for (int i2 = 0; i2 < n; i2++) {
                int mask = 0;
                if (i1 == i2) {
                    mask |= 1 << i1;
                } else {
                    for (int j = 0; j < n; j++)
                        if (isCollinear(i1, i2, j)) {
                            mask |= 1 << j;
                        }
                }

                collinearityMask[i1][i2] = mask;
            }


        // determine collinearity flag for each mask
        int n2 = 1 << n;
        boolean[] isCollinear = new boolean[n2];
        int cnt1 = 0;
        {
            Set<Integer> processed = new HashSet<Integer>();
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    int mask = collinearityMask[i][j];
                    if (processed.contains(mask)) {
                        continue;
                    }

                    for (int subMask = mask; subMask > 0; subMask = (subMask - 1) & mask) {
                        isCollinear[subMask] = true;
                        cnt1++;
                    }

                    processed.add(mask);
                }
        }


        // calculate minimum number of moves required
        int[] minMoves = new int[n2];
        Arrays.fill(minMoves, Integer.MAX_VALUE / 2);
        minMoves[0] = 0;

        {
            int[] pIdx = new int[n];
            for (int mask = 0; mask < n2; mask++) {

                int pIdxCount = 0;
                for (int i = 0; i < n; i++)
                    if ((mask & (1 << i)) != 0) {
                        pIdx[pIdxCount++] = i;
                        cnt1++;
                    }

                for (int i1 = 0; i1 < pIdxCount; i1++) {
                    int[] cMask = collinearityMask[pIdx[i1]];
                    for (int i2 = 0; i2 <= i1; i2++) {
                        int pMask = mask & ~cMask[pIdx[i2]];
                        int pAns = minMoves[pMask] + 1;
                        if (pAns < minMoves[mask]) {
                            minMoves[mask] = pAns;
                        }
                        cnt1++;
                    }
                    if (minMoves[mask] <= 1) {
                        break;
                    }
                }

            }
        }

        int moves = minMoves[n2 - 1];

        int[] d = new int[n2];
        boolean[] good = new boolean[n2];
        d[n2 - 1] = 1;
        good[n2 - 1] = true;
        int goods = 0;

        int cnt2 = 0;

        int[] nextCollinear = new int[n2];
        Arrays.fill(nextCollinear, -1);
        int[] pIdx = new int[n];

        for (int mask = n2 - 1; mask >= 0; mask--)
            if (good[mask]) {
                goods++;

                int removeMask = mask;
                while (removeMask > 0) {
                    if (isCollinear[removeMask]) {
                        int nMask = mask & ~removeMask;
                        if (minMoves[nMask] == minMoves[mask] - 1) {
                            d[nMask] += d[mask];
                            while (d[nMask] >= MOD) {
                                d[nMask] -= MOD;
                            }
                            good[nMask] = true;
                        }
                        removeMask = (removeMask - 1) & mask;
                        cnt2++;
                    } else if (nextCollinear[removeMask] == -1) {

                        int pIdxCount = 0;
                        for (int i = 0; i < n; i++)
                            if ((removeMask & (1 << i)) != 0) {
                                pIdx[pIdxCount++] = i;
                            }


                        int nRemoveMask = -1;
                        for (int i1 = 0; i1 < pIdxCount; i1++) {
                            int[] cMask = collinearityMask[pIdx[i1]];
                            for (int i2 = 0; i2 <= i1; i2++) {
                                int nCandidateMask = removeMask & cMask[pIdx[i2]];
                                if (nCandidateMask > nRemoveMask) {
                                    nRemoveMask = nCandidateMask;
                                }
                            }
                        }

                        nextCollinear[removeMask] = nRemoveMask;
                        removeMask = nRemoveMask;
                    } else {
                        removeMask = nextCollinear[removeMask];
                    }
                    cnt2++;
                }
            }
        // System.err.println(goods + " (" + cnt1 + " - " + cnt2 + ")");

        int ways = d[0];

        return new Answer(moves, ways, (int) (System.currentTimeMillis() - timeStart));
    }

    private Answer solveNaive() {
        long timeStart = System.currentTimeMillis();

        int n2 = 1 << n;
        boolean[] isCollinear = new boolean[n2];
        for (int mask = 0; mask < n2; mask++) {
            int[] pIdx = new int[n];
            int pIdxCount = 0;
            for (int i = 0; i < n; i++)
                if ((mask & (1 << i)) != 0) {
                    pIdx[pIdxCount++] = i;
                }

            boolean result = true;
            if (pIdxCount > 2) {
                int p0 = pIdx[0];
                int p1 = pIdx[1];
                for (int i = 2; i < pIdxCount; i++) {
                    int pI = pIdx[i];
                    if (!isCollinear(p0, p1, pI)) {
                        result = false;
                        break;
                    }
                }
            }
            isCollinear[mask] = result;
        }

        int[] minMoves = new int[n2];
        Arrays.fill(minMoves, Integer.MAX_VALUE);
        minMoves[n2 - 1] = 0;

        for (int mask = n2 - 1; mask >= 0; mask--)
            if (minMoves[mask] < Integer.MAX_VALUE) {
                int nValue = minMoves[mask] + 1;
                for (int removeMask = mask; removeMask > 0; removeMask = (removeMask - 1) & mask)
                    if (isCollinear[removeMask]) {
                        int nMask = mask & ~removeMask;
                        if (minMoves[nMask] > nValue) {
                            minMoves[nMask] = nValue;
                        }
                    }
            }

        int[] d = new int[n2];
        boolean[] good = new boolean[n2];
        d[n2 - 1] = 1;
        good[n2 - 1] = true;

        for (int mask = n2 - 1; mask >= 0; mask--)
            if (good[mask]) {
                for (int removeMask = mask; removeMask > 0; removeMask = (removeMask - 1) & mask)
                    if (isCollinear[removeMask]) {
                        int nMask = mask & ~removeMask;
                        if (minMoves[nMask] == minMoves[mask] + 1) {
                            d[nMask] += d[mask];
                            while (d[nMask] >= MOD) {
                                d[nMask] -= MOD;
                            }
                            good[nMask] = true;
                        }
                    }
            }

        return new Answer(minMoves[0], d[0], (int) (System.currentTimeMillis() - timeStart));
    }

    private boolean isCollinear(int p0, int p1, int pI) {
        return (x[pI] - x[p1]) * (y[p0] - y[p1]) - (x[p0] - x[p1]) * (y[pI] - y[p1]) == 0;
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

}