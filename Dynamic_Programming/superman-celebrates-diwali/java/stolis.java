import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class SupermanCelebratesDiwali {

    public static void main(String[] args) throws IOException {
        //InputReader reader = new InputReader(System.in);
        //int N = reader.readInt();
        //int H = reader.readInt();
        //int I = reader.readInt();
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int H = input.nextInt();
        int I = input.nextInt();
        int[][] people = new int[N][H];
        for (int n=0; n<N; n++) {
            //int no = reader.readInt();
            int no = input.nextInt();
            for (int i=0; i<no; i++) {
                //int floor = reader.readInt()-1;
                int floor = input.nextInt()-1;
                people[n][floor]++;
            }
        }
        int[][] save = new int[N][H];
        int[] max = new int[H];
        for (int n=0; n<N; n++) {
            int value = people[n][0];
            max[0] = Math.max(max[0], value);
            save[n][0] = value;
        }
        for (int h=1; h<H; h++) {
            int maxPeople = 0;
            for (int n=0; n<N; n++) {
                int value = save[n][h-1];
                if (h >= I) {
                    value = Math.max(value, max[h-I]);
                }
                value += people[n][h];
                maxPeople = Math.max(maxPeople, value);
                save[n][h] = value;
            }
            max[h] = maxPeople;
        }
        int answer = 0;
        for (int n=0; n<N; n++) {
            answer = Math.max(save[n][H-1], answer);
        }
        System.out.println(answer);
    }

    static final class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() throws IOException {
            if (curChar >= numChars) {
                curChar = 0;
                numChars = stream.read(buf);
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public final int readInt() throws IOException {
            return (int)readLong();
        }

        public final long readLong() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
                if (c == -1) throw new IOException();
            }
            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = read();
            }
            long res = 0;
            do {
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return negative ? -res : res;
        }

        public final int[] readIntArray(int size) throws IOException {
            int[] array = new int[size];
            for (int i=0; i<size; i++) {
                array[i] = readInt();
            }
            return array;
        }

        public final long[] readLongArray(int size) throws IOException {
            long[] array = new long[size];
            for (int i=0; i<size; i++) {
                array[i] = readLong();
            }
            return array;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }

}