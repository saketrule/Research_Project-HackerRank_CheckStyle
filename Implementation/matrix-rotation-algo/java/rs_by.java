import java.util.*;

public class Solution {

    private static List<CircularList<Integer>> layers;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int N = in.nextInt();
        int R = in.nextInt();
        int[][] m = new int[M][N];
        layers = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                m[i][j] = in.nextInt();
            }
        }

        buildCycles(m, M, N);
        shiftMatrix(m, R, M, N);
        printMatrix(m, M, N);
    }

    private static void buildCycles(int[][] m, int M, int N) {
        for (int l = 0; l < Math.min(M, N) / 2; ) {
            CircularList<Integer> list = new CircularList<>();
            int count = 0;
            for (int i = l; i < M; i++) {
                for (int j = l; j < N - l; j++) {
                    if (i == l) {
                        list.addLast(m[i][j]);
                    } else if (i == M - l - 1) {
                        list.addFirst(m[M - l - 1][j]);
                        count++;
                    } else if (j == l && i >= l && i < M - l) {

                        list.addLast(m[i][N - l - 1]);
                        list.addFirst(m[i][l]);
                        count++;

                    }

                }
            }
            for (int i = 0; i < count; i++) {
                list.addLast(list.get(0));
                list.remove(0);
            }
            layers.add(list);
            l++;
        }
    }

    private static void shiftMatrix(int[][] m, int R, int M, int N) {
        Stack<Pair> stack = new Stack<>();
        for (int l = 0; l < Math.min(M, N) / 2; l++) {
            CircularList<Integer> list = layers.get(l);
            int index = 0;
            for (int i = l; i < M; i++) {
                for (int j = l; j < N - l; j++) {
                    if (i == l) {
                        m[i][j] = list.get(index + R);
                        index++;
                    } else if (i == M - l - 1) {
                        stack.push(new Pair(i, j));
                    } else if (j == l && i >= l && i < M - l) {
                        m[i][N - l - 1] = list.get(index + R);
                        index++;
                        stack.push(new Pair(i, l));
                    }

                }
            }
            while (!stack.empty()) {
                Pair p = stack.pop();
                m[p.i][p.j] = list.get(index + R);
                index++;
            }
        }
    }

    private static void printMatrix(int[][] m, int M, int N) {
        for (int[] aM : m) {
            for (int anAM : aM) {
                System.out.print(anAM + " ");
            }
            System.out.println();
        }
    }

    private static class CircularList<T> extends LinkedList<T> {

        @Override
        public T get(int index) {
            return super.get(index % this.size());
        }
    }

    private static class Pair {
        public int i;
        public int j;

        private Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}