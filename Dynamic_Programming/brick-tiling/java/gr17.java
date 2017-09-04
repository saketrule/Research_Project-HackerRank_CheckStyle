import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author muoi
 */
public class Solution {

    static int MOD = 1000000007;
    static int n, m;
    static int a[][] = new int[20][8];

    static int BIT_CHECK(int a, int b) {
        return ((a) & (1 << (b)));

    }

    static int BIT_SET(int a, int b) {
        return ((1 << (b)));

    }
    static int lshaped_ngang[][][] = {{{1, 1, 1}, {0, 0, 1},}, {{1, 0, 0}, {
                1, 1, 1},},
        {{1, 1, 1}, {1, 0, 0},},
        {{0, 0, 1}, {1, 1, 1},},};
    static int lshaped_doc[][][] = {{{1, 1}, {0, 1}, {0, 1},}, {{0, 1}, {
                0, 1}, {1, 1},}, {{1, 0}, {1, 0}, {1, 1},}, {{1, 1}, {
                1, 0}, {1, 0},},};
    static int num[] = new int[1000000];
    static int new_num[] = new int[1000000];

    {
    }

    static boolean tile_ok_2row(int val, int jj, int shape, AtomicInteger new_val) {

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (lshaped_ngang[shape][i][j] != 0 && BIT_CHECK(val, m * i + (j + jj)) != 0) {
                    return false;
                }
            }
        }

        new_val.set(val);
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (lshaped_ngang[shape][i][j] != 0) {
                    int temp = new_val.get();
                    temp |= BIT_SET(temp, m * i + (j + jj));
                    new_val.set(temp);
                }
            }
        }

        return true;
    }

    static boolean tile_ok_3row(int val, int jj, int shape, AtomicInteger new_val) {

        if (shape < 4) {
            if (jj + 3 > m) {
                return false;
            }
        }

        if (shape < 4) {
            return tile_ok_2row(val, jj, shape, new_val);
        } else {
            shape %= 4;

            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 2; ++j) {
                    if (lshaped_doc[shape][i][j] != 0 && BIT_CHECK(val, m * i + (j + jj)) != 0) {
                        return false;
                    }
                }
            }

            new_val.set(val);

            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 2; ++j) {
                    if (lshaped_doc[shape][i][j] != 0) {
                        int temp = new_val.get();
                        temp |= BIT_SET(temp, m * i + (j + jj));
                        new_val.set(temp);
                    }
                }
            }
            return true;

        }


    }
    static int temp[] = new int[100];

    static void tile_last(int nn, int val, int vt, int sl_shape) {

        if (val == ((1 << (2 * m)) - 1)) {
            num[(1 << (2 * m)) - 1] = (num[((1 << (2 * m)) - 1)] + nn) % MOD;
            return;

        }

        for (int j = vt; j < m - 2; ++j) {
            for (int k = 0; k < 4; ++k) {
                AtomicInteger new_val = new AtomicInteger(0);
                if (tile_ok_2row(val, j, k, new_val)) {
                    temp[sl_shape] = k;
                    tile_last(nn, new_val.get(), j + 1, sl_shape + 1);
                }
            }

        }

    }

    static void tile_3row(int nn, int val, int vt, int sl_shape) {

        if ((val & ((1 << m) - 1)) == ((1 << m) - 1)) {
            new_num[val >> m] = (new_num[val >> m] + nn) % MOD;

            return;
        }

        for (int i = vt; i < m - 1; ++i) {
            for (int j = 0; j < 8; ++j) {
                AtomicInteger new_val = new AtomicInteger(0);
                if (tile_ok_3row(val, i, j, new_val)) {
                    temp[sl_shape] = j;
                    tile_3row(nn, new_val.get(), i + 1, sl_shape + 1);
                }
            }
        }

    }

    static void tile_last2row() {

        for (int i = 0; i < ((1 << (2 * m)) - 1); ++i) {

            if (num[i] != 0) {

                tile_last(num[i], i, 0, 0);
            }
        }
    }

    static int process() {

        if (n == 1) {

            for (int i = 0; i < m; ++i) {
                if (a[0][i] == 0) {
                    return 0;
                }
            }

            return 1;
        }

        Arrays.fill(num, 0);

        int bd = 0;

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < m; ++j) {
                if (a[i][j] != 0) {
                    bd |= BIT_SET(bd, (i * m) + j);
                }
            }
        }

        num[bd] = 1;

        for (int i = 0; i < n - 2; ++i) {

            Arrays.fill(new_num, 0, (1 << (2 * m)), 0);

            for (int j = 0; j < ((1 << (2 * m))); ++j) {
                if (num[j] != 0) {

                    int val = j;
                    for (int k = 0; k < m; ++k) {
                        if (a[i + 2][k] != 0) {
                            val |= BIT_SET(val, 2 * m + k);
                        }
                    }

                    tile_3row(num[j], val, 0, 0);
                }
            }

//            copy(new_num, new_num + (1 << (2 * m)), num);
            System.arraycopy(new_num, 0, num, 0, 1 << (2 * m));

        }

        tile_last2row();
        return num[(1 << (2 * m)) - 1];
    }

    public static void  main(String args[]) {

        int ntest = 0;
        Scanner scanner = new Scanner(System.in);
        ntest = scanner.nextInt();
        for (int x = 0; x < ntest; ++x) {
            n = scanner.nextInt();
            m = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < n; ++i) {
                String line = scanner.nextLine();
                for (int j = 0; j < m; ++j) {
                    char ch = line.charAt(j);
                    if (ch == '.') {
                        a[i][j] = 0;
                    } else {
                        a[i][j] = 1;
                    }
                }

            }

            System.out.println(process());

        }

        
    }
}