import java.io.*;
import java.util.*;

public class Q2 {
    public static MyScanner in = new MyScanner();
    public static PrintWriter out = in.MyOut();
    public static MyViewer view = new MyViewer();
//    public static Reader inRead = new Reader();

    static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        int[] arrayInt(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            return a;
        }

        long[] arrayLong(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextLong();
            }
            return a;
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        void close() {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        static PrintWriter MyOut() {
            return new PrintWriter(new BufferedOutputStream(System.out), true);
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        public int[] arrayInt(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            return a;
        }

        public long[] arrayLong(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextLong();
            }
            return a;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    static class MyViewer {
        static boolean print = true;

        public void on() {
            print = true;
        }

        public void off() {
            print = false;
        }

        public <T extends List> void list(T a) {
            if (!print) return;
            out.print("List: [" + a.get(0));
            for (int i = 1; i < a.size(); i++) {
                out.print(", " + a.get(i));
            }
            out.println("] Len: " + a.size());
        }

        public <T> void array(T[] a) {
            if (!print) return;
            out.print("Array: [" + a[0]);
            for (int i = 1; i < a.length; i++) {
                out.print(", " + a[i]);
            }
            out.println("] Len: " + a.length);
        }

        public void array(int[] a) {
            if (!print) return;
            out.print("int Array: [");
            for (int x : a) {
                out.print(x + ", ");
            }
            out.println("] Len: " + a.length);
        }

        public void array(long[] a) {
            if (!print) return;
            out.print("long Array: [");
            for (long x : a) {
                out.print(x + ", ");
            }
            out.println("] Len: " + a.length);
        }

        public void matrix(int[][] a, int cutoff) {
            if (cutoff == 0)
                cutoff = Integer.MAX_VALUE;
            for (int i = 0; i < a.length; i++) {
                if (i < cutoff) {
                    printMatrixRow(a[i], cutoff);
                } else {
                    out.println("     ...");
                    printMatrixRow(a[a.length - 1], cutoff);
                    break;
                }
            }
        }

        private void printMatrixRow(int[] a, int cutoff) {
            for (int j = 0; j < a.length; j++) {
                if (j < cutoff) {
                    out.printf("%6d  ", a[j]);
                } else {
                    out.printf(" ... %6d", a[a.length - 1]);
                    break;
                }
            }
            out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        int test_cases = in.nextInt();
        while (test_cases-- > 0) {
            int n = in.nextInt();
            int count = runSieve(1,n).size() - 1;
            out.println( count %2 == 0 ? "Bob" : "Alice");
        }
        in.close();
    }

    public static ArrayList<Integer> runSieve(Integer m, Integer n) {

        Integer k = (int) Math.floor(Math.sqrt(n));
        //System.out.println("k: " + k);

        boolean[] A = new boolean[k + 1];
        boolean[] B = new boolean[n - m + 1];
        for (int i = 0; i < A.length; i++)
            A[i] = true;
        for (int i = 0; i < B.length; i++)
            B[i] = true;

        for (int i = 2; i <= k; i++) {
            //System.out.println("Try i: " + i);
            if (A[i]) {
                for (int j = i * i; j <= k; j += i) {
                    A[j] = false;
                }

                int start, multi;

                if (m - i * i < 0)
                    multi = 0;
                else
                    multi = (m - i * i) / i;

                start = i * i + i *multi;
                if( start < m)
                    start = i * i + i * (multi + 1);

                //System.out.println("Start : " + start);

                for (int j = start; j <= n; j += i) {
                    B[j - m] = false;
                    //System.out.println( "False : " + j);
                }
            }
        }

        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            if (B[i])
                al.add(i + m);
        }
        return al;
    }
}