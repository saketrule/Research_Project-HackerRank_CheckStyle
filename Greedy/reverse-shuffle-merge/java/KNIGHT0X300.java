import com.sun.org.apache.bcel.internal.generic.RET;
import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        new Solution().run();
    }
    StreamTokenizer in;
    PrintWriter out;
    //deb////////////////////////////////////////////////

    public static void deb(String n, Object n1) {
        System.out.println(n + " is : " + n1);
    }

    public static void deb(int[] A) {

        for (Object oo : A) {
            System.out.print(oo + " ");
        }
        System.out.println("");
    }

    public static void deb(long[] A) {

        for (Object oo : A) {
            System.out.print(oo + " ");
        }
        System.out.println("");
    }

    public static void deb(String[] A) {

        for (Object oo : A) {
            System.out.print(oo + " ");
        }
        System.out.println("");
    }

    public static void deb(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (Object oo : A[i]) {
                System.out.print(oo + " ");
            }
            System.out.println("");
        }

    }

    public static void deb(long[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (Object oo : A[i]) {
                System.out.print(oo + " ");
            }
            System.out.println("");
        }

    }

    public static void deb(String[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (Object oo : A[i]) {
                System.out.print(oo + " ");
            }
            System.out.println("");
        }

    }
    /////////////////////////////////////////////////////////////

    int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    class Pair<X, Y> {

        public X x;
        public Y y;

        public Pair(X x, Y y) {
            this.x = x;
            this.y = y;
        }

        public void setX(X x) {
            this.x = x;
        }

        public void setY(Y y) {
            this.y = y;
        }
    }

    boolean inR(int x, int y) {
        return (x >= 0) && (x < nn) && (y >= 0) && (y < nn);
    }
    static int nn;

    void run() throws IOException {
        //  in = new StreamTokenizer(new BufferedReader(new FileReader("circles.in")));
        //  out = new PrintWriter(new FileWriter("circles.out"));
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        out = new PrintWriter(new OutputStreamWriter(System.out));
        solve();
        out.flush();
    }
    static int ub = 300000;//upper bound
    static boolean[] primes = new boolean[ub + 1];
    static int[] Primes = new int[300000];//sufficiently large ot Ppi
    static int Ppi = 0;

    static void Primegen() {

        for (int i = 2; i <= ub; i++) {
            if (!primes[i]) {
                Primes[Ppi] = i;
                Ppi++;
                for (int j = 2; j * i <= ub; j++) {
                    primes[j * i] = true;
                }
            }
        }
        // System.out.println(Ppi);
        //  System.out.println(Primes[Ppi-1]);
    }

    void solve() throws IOException {
        //   BufferedReader re= new BufferedReader(new FileReader("C:\\Users\\ASELA\\Desktop\\A.in"));
        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
        // Scanner sc= new Scanner(System.in);
        StringBuilder sb = new StringBuilder(re.readLine());
        sb.reverse();
        String S = sb.toString();
        int[] A = new int[26];
        int[] rem = new int[26];

        for (int i = 0; i < S.length(); i++) {
            A[(int) S.charAt(i) - (int) 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            rem[i] = A[i];
            if (A[i] % 2 == 1) {
                S.charAt(-1);
            }
            A[i] /= 2;
        }
        int n = S.length();

        StringBuilder ans = new StringBuilder();
        int pos = 0;
        while (true) {
            int letter = 0;
            while (letter < 26 && A[letter] <= 0) {
                letter++;
            }
            if (letter == 26) {
                break;
            }
            // while(letter<26)
            {
                for (int i = pos; i < n; i++) {
                    while(A[letter]==0)letter++;
                    if ((int) S.charAt(i) - (int) 'a' == letter) {
                        rem[letter]--;
                        A[letter]--;
                        ans.append((char) (letter + (int) 'a'));
                        pos = i + 1;
                        break;
                    } else {
                        int hh = (int) S.charAt(i) - (int) 'a';
                        rem[hh]--;
                        if (rem[hh] < A[hh]) {
                            for (; i >= pos; i--) {
                                rem[(int) S.charAt(i) - (int) 'a']++;
                            }
                            i = pos - 1;
                            letter++;
                        }
                    }
                }

            }

        }
//        for (int i = 0; i < 26; i++) {
//            if (A[i] < 0) {
//                S.charAt(-1);
//            }
//        }
        out.println(ans);
    }
}