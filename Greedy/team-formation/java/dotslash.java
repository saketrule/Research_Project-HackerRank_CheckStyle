import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Created by ssai
 * Jan-30-2015.
 */


public class TeamFoundation {
    static class IO  {
        //Standard IO
        static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer tokenizer = null;

        static int ni() {
            return Integer.parseInt(ns());
        }

        static long nl() {
            return Long.parseLong(ns());
        }

        static double nd() {
            return Double.parseDouble(ns());
        }

        static String ns() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                }
            }
            return tokenizer.nextToken();
        }

        static String nline() {
            tokenizer = null;
            String ret = null;
            try {
                ret = br.readLine();
            } finally {
                return ret;
            }
        }
    }


    public static void main(String[] args) {
        int tc = IO.ni();
        for (int i = 0; i < tc; i++) {
            solve();
        }
    }

    private static final int MAX = 100001;


    private static final TreeMap<Integer, Integer> map = new TreeMap<>();

    private static void add(int key) {
        Integer val = map.get(key);
        if (val == null) {
            val = 0;
        }
        val++;
        map.put(key, val);
    }

    private static void dec(int key) {
        Integer val = map.get(key);
        if (val == null || val.equals(0)) {
            throw new IllegalStateException();
        }
        if (val > 1) {
            val = val - 1;
            map.put(key, val);
        } else {
            map.remove(key);
        }
    }

    private static final int[] toRem = new int[MAX];
    private static int toRemInd = 0;

    private static void solve() {
        int n = IO.ni();
        if (n == 0) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < n; i++) {
            add(IO.ni());
        }
        int ans = MAX;
        if (map.isEmpty()) {
            ans = 0;
        }
        while (!map.isEmpty()) {
            toRemInd = 0;
            int prev = 0, prevCnt = 0;
            int presLen = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (toRemInd != 0) {
                    if (prev != (entry.getKey() - 1) || prevCnt > entry.getValue()) {
                        ans = Math.min(ans, presLen);
                        break;
                    } else {
                        presLen++;
                    }
                } else {
                    presLen = 1;
                }

                prev = entry.getKey();
                prevCnt = entry.getValue();
                toRem[toRemInd] = entry.getKey();
                toRemInd++;
            }
            ans = Math.min(presLen, ans);
            for (int i = 0; i < toRemInd; i++) {
                dec(toRem[i]);
            }
        }

        System.out.println(ans);

    }

}