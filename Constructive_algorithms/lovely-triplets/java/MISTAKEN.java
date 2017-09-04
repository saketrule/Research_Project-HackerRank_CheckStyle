import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        LoveTriplets solver = new LoveTriplets();
        solver.solve(1, in, out);
        out.close();
    }

    static class LoveTriplets {
        int[][] bestPos;
        int[] nodeV;
        int[] edgeV;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            nodeV = new int[]{0, 0, 1, 3, 4, 6, 7, 9, 10, 12};
            edgeV = new int[]{0, 0, 0, 3, 3, 6, 6, 9, 9, 12};
            bestPos = new int[5001][4];
            for (int[] arr : bestPos) Arrays.fill(arr, 10000000);
            for (int i = 1; i <= 80; i++) {
                for (int j = 1; j <= 80; j++) {
                    for (int k = 1; k <= 80; k++) {
                        int mul = i * j * k, sum = i + j + k;
                        if (mul > 5000) break;
                        if (bestPos[mul][0] == 0 || bestPos[mul][0] > sum) {
                            bestPos[mul][0] = sum;
                            bestPos[mul][1] = i;
                            bestPos[mul][2] = j;
                            bestPos[mul][3] = k;
                        }
                    }
                }
            }
            int[][] map = new int[5001][3];
            for (int[] ar : map) Arrays.fill(ar, 10000000);
            map[0][0] = 0;
            map[0][1] = -1;
            map[0][2] = -1;
            for (int i = 1; i <= 5000; i++) {
                for (int j = 1; j <= i; j++) {
                    if (map[i - j][0] + bestPos[j][0] < map[i][0]) {
                        map[i][0] = map[i - j][0] + bestPos[j][0];
                        map[i][1] = i - j;
                        map[i][2] = j;
                    }
                }
            }
            int[][] cc = new int[50][50];
            for (int i = 0; i < 50; i++) {
                cc[i][0] = cc[i][i] = 1;
                for (int j = 1; j < i && j < 7; j++) {
                    cc[i][j] = cc[i - 1][j] + cc[i - 1][j - 1];
                }
            }
            boolean[][] edges = new boolean[1001][1001];
            int nodes = 2, edgeCnt = 0;
            int p = in.nextInt(), q = in.nextInt();
            if (q == 2) {
                int par = 1;
                while (p > 0) {
                    int comb = 1;
                    for (int i = 3; i < 50; i++) {
                        if (cc[i][3] > p) break;
                        comb = i;
                    }
                    for (int i = 0; i < comb; i++) {
                        edges[par][nodes++] = true;
                        edgeCnt++;
                    }
                    p -= cc[comb][3];
                    par = nodes++;
                }
            } else if (q !=2 && q % 2 == 0) {
                int par = 1;
                while (map[p][1] != -1) {
                    int ind = map[p][2];
                    p = map[p][1];
                    for (int i = 1; i <= 3; i++) {
                        int con = par;
                        for (int j = 1; j * 2 < q; j++) {
                            edges[con][nodes] = true;
                            ++edgeCnt;
                            con = nodes++;
                        }
                        for (int j = 0; j < bestPos[ind][i]; j++) {
                            edges[nodes++][con] = true;
                            ++edgeCnt;
                        }
                    }
                    par = nodes++;
                }
            } else {
                nodes = 1;
                while (map[p][1] != -1) {
                    int ind = map[p][2];
                    edges[nodes][nodes + 1] = true;
                    edges[nodes][nodes + 2] = true;
                    edges[nodes + 1][nodes + 2] = true;
                    edgeCnt += 3;
                    int[] con = new int[]{nodes, nodes + 1, nodes + 2};
                    nodes += 3;
                    p = map[p][1];
                    for (int j = 1; j <= 3; j++) {
                        if (q != 3) {
                            for (int i = 1; 3 * i <= q; i++) {
                                edges[con[j - 1]][nodes] = true;
                                ++edgeCnt;
                                con[j - 1] = nodes++;
                            }
                        }
                        for (int i = 0; i < bestPos[ind][j]; i++) {
                            edges[con[j - 1]][nodes++] = true;
                            ++edgeCnt;
                        }
                    }

                }
            }
            nodes = 0;
            edgeCnt = 0;
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (edges[i][j]){
                        nodes = Math.max(nodes, Math.max(i, j));
                        ++edgeCnt;
                    } 
                }
            }
            out.println(nodes + " " + edgeCnt);
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (edges[i][j]){
                      out.println(i + " " + j);  
                    } 
                }
            }

        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}