import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        int t = readInt();
        int[] solutions = new int[t];
        for (int i = 0; i < t; i++) {
            solutions[i] = readAndSolveGrid();
        }
        for (int solution : solutions) {
            System.out.println(solution);
        }
    }

    private static final class Grid {
        public static final int BLOCKED = -1;
        public static final int EMPTY = 0;

        private int[] grid, markers;
        private final int rows, cols;

        private Grid(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            grid = new int[rows * cols];
            markers = new int[rows * cols];
            for (int i = 0; i < grid.length; i++) {
                grid[i] = EMPTY;
                markers[i] = Integer.MAX_VALUE;
            }
        }

        public void setRow(int row, String def) {
            for (int i = 0; i < cols; i++) {
                if ('#' == def.charAt(i))
                    grid[row * cols + i] = BLOCKED;
            }
        }


        private int get(int r, int c) {
            return grid[r * cols + c];
        }

        private void set(int r, int c, int pattern) {
            grid[r * cols + c] = pattern;
        }

        private boolean emptyPath(int r, int c, String path, int brush) {
            path = path.toUpperCase();
            boolean on = false;
            int matches = 0;
            for (int i = 0; i < path.length(); i++) {
                switch (path.charAt(i)) {
                    case '0':
                        on = false;
                        break;
                    case '1':
                        on = true;
                        break;
                    case 'R':
                        c++;
                        break;
                    case 'L':
                        c--;
                        break;
                    case 'U':
                        r--;
                        break;
                    case 'D':
                        r++;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid direction in path: " + path);
                }
                if (r < 0 || r >= rows || c < 0 || c >= cols
                        || (on && get(r, c) != EMPTY)) {
                    return false;
                }
                if (on && markers[r * cols + c] < brush) {
                    matches++;
                }
            }
            if (matches == 4) {
                return false;
            }
            return true;
        }

        private void fillBlock(int r, int c, String path, int brush) {
            path = path.toUpperCase();
            boolean on = false;
            for (int i = 0; i < path.length(); i++) {
                switch (path.charAt(i)) {
                    case '0':
                        on = false;
                        break;
                    case '1':
                        on = true;
                        break;
                    case 'R':
                        c++;
                        break;
                    case 'L':
                        c--;
                        break;
                    case 'U':
                        r--;
                        break;
                    case 'D':
                        r++;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid direction in path: " + path);
                }
                if (on) {
                    set(r, c, brush);
                    if (brush != EMPTY && brush < markers[r * cols + c])
                        markers[r * cols + c] = brush;
                }
            }
        }

        private boolean solved() {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i] == EMPTY) {
                    return false;
                }
            }
            return true;
        }

        private boolean allBlocked() {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i] != BLOCKED) {
                    return false;
                }
            }
            return true;
        }

        private static final String[] BLOCKS = {"1DRR", "R1LDD", "1RRD", "R1DDL",
                "D1RRU", "1DDR", "D1URR", "1RDD"};

        private int solutionsCount = 0;

        public int fillSolutions() {
            if (allBlocked()) {
                return 1;
            } else {
                fillSolutions(1);
                return solutionsCount % 1000000007;
            }
        }

        private boolean fillSolutions(int brush) {
            boolean res = false;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    for (String block : BLOCKS) {
                        if (emptyPath(r, c, block, brush)) {
                            res = true;
                            emptyPath(r, c, block, brush);
                            fillBlock(r, c, block, brush);
                            fillSolutions(brush + 1);

                            if (solved()) {
                                solutionsCount++;
                            }

                            fillBlock(r, c, block, EMPTY);
                        }
                    }
                }
            }
            return res;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            for (int r = 0; r < rows; r++) {
                res.append("\n");
                for (int c = 0; c < cols; c++) {
                    int v = grid[r * cols + c];
                    if (v == BLOCKED) {
                        res.append("#");
                    } else if (v == EMPTY) {
                        res.append(".");
                    } else {
                        res.append((char) ('a' + v - 1));
                    }
                }
            }
            return res.toString();
        }
    }

    private static int readAndSolveGrid() {
        try {
            String[] rc = br.readLine().trim().split(" ");
            int rows = Integer.parseInt(rc[0]);
            int cols = Integer.parseInt(rc[1]);
            Grid grid = new Grid(rows, cols);
            for (int i = 0; i < rows; i++) {
                grid.setRow(i, br.readLine());
            }
            return grid.fillSolutions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int readInt() {
        try {
            return Integer.parseInt(br.readLine().trim());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}