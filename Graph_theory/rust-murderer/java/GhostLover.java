import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader(System.in);
        int T = reader.readInt();
        for (int t=0; t<T; t++) {
            int N = reader.readInt();
            int M = reader.readInt();
            Node[] nodes = new Node[N];
            for (int n=0; n<N; n++) {
                nodes[n] = new Node();
            }
            for (int m=0; m<M; m++) {
                int u = reader.readInt()-1;
                int v = reader.readInt()-1;
                Node nodeu = nodes[u];
                Node nodev = nodes[v];
                nodeu.next.add(v);
                nodev.next.add(u);
            }
            int S = reader.readInt()-1;
            nodes[S].dist = 0;
            Set<Integer> set = nodes[S].next;
            List<Integer> unknown = new ArrayList<Integer>();
            List<Integer> known = new ArrayList<Integer>();
            for (int n=0; n<N; n++) {
                if (n == S) continue;
                if (set.contains(n)) {
                    unknown.add(n);
                } else {
                    nodes[n].dist = 1;
                    known.add(n);
                }
            }
            int d = 1;
            while (!unknown.isEmpty()) {
                d++;
                List<Integer> newKnown = new ArrayList<Integer>();
                List<Integer> stillUnknown = new ArrayList<Integer>();
                unknown: for (Integer unknownIndex : unknown) {
                    set = nodes[unknownIndex].next;
                    for (Integer knownIndex : known) {
                        if (!set.contains(knownIndex)) {
                            nodes[unknownIndex].dist = d;
                            newKnown.add(unknownIndex);
                            continue unknown;
                        }
                    }
                    stillUnknown.add(unknownIndex);
                }
                known = newKnown;
                unknown = stillUnknown;
            }

            StringBuilder output = new StringBuilder();
            for (Node node : nodes) {
                int dist = node.dist;
                if (dist != 0) {
                    if (output.length() != 0) {
                        output.append(' ');
                    }
                    output.append(dist);
                }
            }
            System.out.println(output);
        }
    }

    static class Node {
        Set<Integer> next = new HashSet<Integer>();
        int dist = Integer.MAX_VALUE;
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