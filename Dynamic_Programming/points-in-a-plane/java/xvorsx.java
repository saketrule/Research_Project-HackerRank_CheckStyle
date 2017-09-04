import java.io.*;

/**
 * @author Sergey Vorobyev (svorobyev@spb.com)
 */
public class Solution {

    StreamTokenizer in;
    PrintWriter out;

    public static void main(String[] args) throws Exception {
        Solution instance = new Solution();
        instance.go();
    }

    int ni() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int vect(Point p1, Point p2) {
            int x1 = p1.x - x;
            int x2 = p2.x - x;
            int y1 = p1.y - y;
            int y2 = p2.y - y;
            return x1 * y2 - x2 * y1;
        }
    }

    private int getWithoutLastOne(int x) {
        return (x-1) & x;
    }

    private boolean test(Point[] a, int n, int mask) {
        //long time = System.currentTimeMillis();
        if (Integer.bitCount(mask) < 3) {
            return true;
        }
        int mask1 = getWithoutLastOne(mask);
        if (!collin[mask1]) {
            return false;
        }

        int mask2 = getWithoutLastOne(mask1);
        int mask3 = getWithoutLastOne(mask2);

        int i1 = getLog[mask - mask1];
        int i2 = getLog[mask1 - mask2];
        int i3 = getLog[mask2 - mask3];

        //totalTime += System.currentTimeMillis() - time;

        return a[i1].vect(a[i2], a[i3]) == 0;
    }

    static final long MOD = 1000000007;
    boolean[] collin;
    int[] getLog;
    //long totalTime;

    private void go() throws Exception {
        in = new StreamTokenizer(System.in);
        out = new PrintWriter(System.out);

        getLog = new int[1 << 16];
        for (int i=0; i<16; i++) {
            getLog[1 << i] = i;
        }

        long time = System.currentTimeMillis();

        int T = ni();
        for (int cas=1; cas<=T; cas++) {
            int n = ni();
            Point[] a = new Point[n];
            for (int i=0; i<n; i++) {
                a[i] = new Point(ni(), ni());
            }
            int M = 1 << n;

            collin = new boolean[M];
            int[] count = new int[M];
            int[] d = new int[M];
            d[0] = 0;
            count[0] = 1;

            collin[0] = true;

            for (int mask=1; mask<M; mask++) {
                collin[mask] = test(a, n, mask);
                if (collin[mask]) {
                    d[mask] = 1;
                    count[mask] = 1;
                    continue;
                }
                d[mask] = Integer.MAX_VALUE / 3;

                int highestBit;
                for (highestBit=n; highestBit>=0; highestBit--) {
                    if (((1 << highestBit) & mask) != 0) {
                        break;
                    }
                }
                for (int submask=mask; submask >= (1 << highestBit); submask=(submask-1)&mask) {
                    if (collin[submask]) {
                        int commmask = mask - submask;
                        if (d[commmask] + 1 < d[mask]) {
                            d[mask] = d[commmask] + 1;
                            count[mask] = count[commmask];
                        } else {
                            if (d[commmask] + 1 == d[mask]) {
                                count[mask] += count[commmask];
                                //count[mask] %= MOD;
                            }
                        }
                    }
                }
            }
            //System.out.println("Time = " + (System.currentTimeMillis() - time));
            out.println(d[(1 << n) - 1] + " " + (count[(1 << n) - 1] * fac(d[(1 << n) - 1])) % MOD);
        }

        //System.err.println("Total test time = " + totalTime);


        out.close();
    }

    private long fac(int n) {
        long res = 1;
        for (int i=2; i<=n; i++) {
            res = (res * i) % MOD;
        }
        return res;
    }
}