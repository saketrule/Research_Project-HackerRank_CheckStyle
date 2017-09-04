import java.io.*;
import java.util.*;

public class SynchronousShopping {

    public static InputReader in;
    public static PrintWriter out;
    public static final int MOD = (int)1e9 + 7;
    
    public static void main(String[] args) {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);
        
        int N = in.nextInt(),
                M = in.nextInt(),
                K = in.nextInt();
        int[] val = new int[N];
        for(int i = 0; i < N; i++) {
            int Ti = in.nextInt();
            while(Ti-- > 0)
                val[i] |= 1<<(in.nextInt()-1);
        }
        
        @SuppressWarnings("unchecked")
        ArrayList<Node>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)     graph[i] = new ArrayList<Node>();
        for (int j = 0; j < M; j++) {
            int Xj = in.nextInt() - 1,
                    Yj = in.nextInt() - 1;
            long Zj = in.nextLong();
            graph[Xj].add(new Node(Yj, Zj));
            graph[Yj].add(new Node(Xj, Zj));
        }
        long[][] dp = DFSandDP(graph, val, K);
        int maxMask = 1<<K;
        long result = Long.MAX_VALUE;
        for (int i = 0; i < maxMask; i++) {
            if(dp[N-1][i] == Long.MAX_VALUE)    continue;
            for (int j = 0; j < maxMask; j++) {
                if(dp[N-1][j] == Long.MAX_VALUE)    continue;
                if(Math.max(dp[N-1][i], dp[N-1][j]) < result && ((i|j) == maxMask - 1))
                    result = Math.max(dp[N-1][i], dp[N-1][j]);
            }
        }
        for(int i = 0; i< N; i++) for (int j = 0; j < maxMask; j++) {
            if(dp[i][j] == Long.MAX_VALUE)  dp[i][j] = -1;
        }
        //for (int i = 0; i < dp.length; i++) out.println(Arrays.toString(dp[i]));
        out.println(result);
        out.close();
    }
    
    private static long[][] DFSandDP(ArrayList<Node>[] graph, int[] values, int k) {
        int N = graph.length;
        int maxMask = 1<<k;
        long[][] dp = new long[N][maxMask];
        for (int i = 0; i < N; i++)     Arrays.fill(dp[i], Long.MAX_VALUE);
        boolean[] visited = new boolean[N];
        int start = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(start);
        dp[0][values[0]] = 0;
        while(!stack.isEmpty()) {
            int curr = stack.peek();
            if(!visited[curr]) {
                visited[curr] = true;
                for (Node neighbours : graph[curr]) {
                    for (int i = 0; i < maxMask; i++) if(dp[curr][i] != Long.MAX_VALUE)
                        dp[neighbours.v][(values[neighbours.v]|i)] = Math.min(dp[neighbours.v][(values[neighbours.v]|i)], dp[curr][i] + neighbours.time);
                    if(!visited[neighbours.v])
                        stack.push(neighbours.v);
                }
            } else {
                for (Node neighbours : graph[curr]) {
                    for (int i = 0; i < maxMask; i++) if(dp[curr][i] != Long.MAX_VALUE)
                        dp[neighbours.v][(values[neighbours.v]|i)] = Math.min(dp[neighbours.v][(values[neighbours.v]|i)], dp[curr][i] + neighbours.time);
                }
                stack.pop();
            }
        }
        
        
        visited = new boolean[N];
        start = N-1;
        stack = new Stack<Integer>();
        stack.push(start);
        dp[0][values[0]] = 0;
        while(!stack.isEmpty()) {
            int curr = stack.peek();
            if(!visited[curr]) {
                visited[curr] = true;
                for (Node neighbours : graph[curr]) {
                    for (int i = 0; i < maxMask; i++) if(dp[curr][i] != Long.MAX_VALUE)
                        dp[neighbours.v][(values[neighbours.v]|i)] = Math.min(dp[neighbours.v][(values[neighbours.v]|i)], dp[curr][i] + neighbours.time);
                    if(!visited[neighbours.v])
                        stack.push(neighbours.v);
                }
            } else {
                for (Node neighbours : graph[curr]) {
                    for (int i = 0; i < maxMask; i++) if(dp[curr][i] != Long.MAX_VALUE)
                        dp[neighbours.v][(values[neighbours.v]|i)] = Math.min(dp[neighbours.v][(values[neighbours.v]|i)], dp[curr][i] + neighbours.time);
                }
                stack.pop();
            }
        }
        
        
        
        return dp;
    }

    static class Node implements Comparable<Node>{
        int v;
        long time;
        
        public Node (int v, long time) {
            this.v = v;
            this.time = time;
        }
        
        public void print() {
            System.out.println(" " + v + " " + time);
        }
        
        public int compareTo(Node that) {
           return Long.compare(this.time, that.time);
        }
    }
    
    static class InputReader {

        private InputStream stream;
        private byte[] buf = new byte[8192];
        private int curChar, snumChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int snext() {
            if (snumChars == -1)
                throw new InputMismatchException();
            if (curChar >= snumChars) {
                curChar = 0;
                try {
                    snumChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (snumChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public long nextLong() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int a[] = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public String readString() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = snext();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}