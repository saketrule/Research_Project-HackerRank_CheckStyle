import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

        public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bfr.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        int[][] weight = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(weight[i], -1);
        }

        //floyd

        for (int i = 0; i < N; i++) {
            weight[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {

            temp = bfr.readLine().split(" ");
            int x = Integer.parseInt(temp[0]) - 1;
            int y = Integer.parseInt(temp[1]) - 1;
            int z = Integer.parseInt(temp[2]);
            weight[x][y] = z;
        }


        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (weight[i][k] >= 0
                            && weight[k][j] >= 0
                            && (weight[i][j] < 0 || weight[i][k] + weight[k][j] < weight[i][j])) {
                        weight[i][j] = weight[i][k] + weight[k][j];
                    }
                }
            }
        }

        int query = Integer.parseInt(bfr.readLine());

        for (int i = 0; i < query; i++) {
            temp = bfr.readLine().split(" ");
            int x = Integer.parseInt(temp[0]) - 1;
            int y = Integer.parseInt(temp[1]) - 1;

            if (weight[x][y] < 0) {
                System.out.println(-1);
            } else {
                System.out.println(weight[x][y]);
            }
        }

    }

}