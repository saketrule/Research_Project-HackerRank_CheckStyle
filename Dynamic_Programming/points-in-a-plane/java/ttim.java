import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Solution {
    private static final long MODULO = 1000000007L;
    private static final int MAX_POSITION = 1 << 16;
    private static final int[][] POSITION_TO_POINTS = new int[MAX_POSITION][];
    private static final int[] POINTS_COUNT = new int[MAX_POSITION];
    private static final int[] RIGHT_ORDERED_POSITIONS = new int[MAX_POSITION];
    private static final int[] FIRST_BIT_SUBTRACT = new int[MAX_POSITION];

    int maxPos;
    int[] minimum;
    long[] count;

    int[][] masks;
    int[][] enumeratedMasks;
    int[] linesMasks;

    static {
        // position -> points
        for (int pos = 0; pos < MAX_POSITION; pos++) {
            POSITION_TO_POINTS[pos] = oneBits(pos, 16);
            POINTS_COUNT[pos] = POSITION_TO_POINTS[pos].length;
        }
        // positions in right order
        int pos = 0;
        for (int pointsCount = 0; pointsCount <= 16; pointsCount++) {
            for (int position = 0; position < MAX_POSITION; position++) {
                if (POSITION_TO_POINTS[position].length == pointsCount) {
                    RIGHT_ORDERED_POSITIONS[pos++] = position;
                }
            }
        }
        // first bit subtract
        for (pos = 1; pos < MAX_POSITION; pos++) {
            FIRST_BIT_SUBTRACT[pos] = 1 << POSITION_TO_POINTS[pos][0];
        }
    }

    private static int getMaskFromLine(int p1, int p2, int[] x, int[] y) {
        int dx = x[p2] - x[p1], dy = y[p2] - y[p1];
        // point in line if (px, py) == (x1,y1)+k*(dx,dy)
        // px-x1, py-y1 == k*(dx,dy)
        // px-x1/dx == py-y1/dy
        // (px-x1)*dy = (py-y1)*dx
        int x1 = x[p1], y1 = y[p1];

        int mask = 0;
        for (int p = 0; p < x.length; p++) {
            if (((x[p] - x1) * dy == (y[p] - y1) * dx)) {
                mask += (1 << p);
            }
        }

        return mask;
    }

    private void run(Scanner input, PrintWriter output) {
        int n = input.nextInt();
        int[] x = new int[n], y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = input.nextInt();
            y[i] = input.nextInt();
        }

        maxPos = 1 << n;

        // precalc lines mask
        // masks of all lines
        masks = new int[n][n];
        enumeratedMasks = new int[n][n];

        Set<Integer> linesMasksSet = new HashSet<Integer>();
        for (int p1 = 0; p1 < n; p1++) {
            for (int p2 = p1 + 1; p2 < n; p2++) {
                masks[p1][p2] = getMaskFromLine(p1, p2, x, y);
                linesMasksSet.add(masks[p1][p2]);
            }
        }

        Map<Integer, Integer> maskToId = new HashMap<Integer, Integer>();
        linesMasks = new int[linesMasksSet.size()];
        int num = 0;
        for (int mask : linesMasksSet) {
            maskToId.put(mask, num);
            linesMasks[num++] = mask;
        }

        for (int p1 = 0; p1 < n; p1++) {
            for (int p2 = p1 + 1; p2 < n; p2++) {
                enumeratedMasks[p1][p2] = maskToId.get(masks[p1][p2]);
            }
        }

        // two steps. first - calc minimum
        minimum = new int[maxPos];
        minimum[0] = 0;
        for (int position : RIGHT_ORDERED_POSITIONS) {
            if (position < maxPos && position > 0) {
                int currentMinimum = Integer.MAX_VALUE;

                int[] points = POSITION_TO_POINTS[position];
                // one point
                for (int point : points) {
                    currentMinimum = Math.min(currentMinimum, minimum[position - (1 << point)] + 1);
                }

                // two points
                for (int p1 = 0; p1 < points.length; p1++) {
                    for (int p2 = p1 + 1; p2 < points.length; p2++) {
                        int line = masks[points[p1]][points[p2]] & position;
                        currentMinimum = Math.min(currentMinimum, minimum[position - line] + 1);
                    }
                }

                minimum[position] = currentMinimum;
            }
        }

        count = new long[maxPos];
        Arrays.fill(count, -1);
        count[0] = 1;

        // second - count
        output.println(minimum[maxPos - 1] + " " + calcCount(maxPos - 1));
    }

    private long calcCount(int position) {
        if (count[position] != -1) {
            return count[position];
        } else {
            long result = 0;

            int[] points = POSITION_TO_POINTS[position];

            // 1 point
            for (int p : points) {
                if (minimum[position] == minimum[position - (1 << p)] + 1) {
                    result += calcCount(position - (1 << p));
                }
            }

            // 2 points
            boolean[] usedMasks = new boolean[linesMasks.length];
            for (int p1 = 0; p1 < points.length; p1++) {
                for (int p2 = p1 + 1; p2 < points.length; p2++) {
                    if (!usedMasks[enumeratedMasks[points[p1]][points[p2]]]) {
                        usedMasks[enumeratedMasks[points[p1]][points[p2]]] = true;

                        int lineMask = masks[points[p1]][points[p2]] & position;
                        if (minimum[position] == minimum[position - lineMask] + 1) {
                            result += checkAllSubsets(position, lineMask, 0);
                        }
                    }
                }
            }

            // save and return
            result %= MODULO;
            count[position] = result;
            return result;
        }
    }

    private long checkAllSubsets(int position, int lineMask, int addition) {
        // should check all subsets of lineMask
        if (POINTS_COUNT[lineMask + addition] < 2) {
            return 0;
        }

        if (minimum[position - lineMask - addition] + 1 > minimum[position]) {
            return 0;
        }

        if (lineMask == 0) {
            return calcCount(position - addition);
        }

        // first zero or first one
        int subtract = FIRST_BIT_SUBTRACT[lineMask];
        return checkAllSubsets(position, lineMask - subtract, addition + subtract) +
                checkAllSubsets(position, lineMask - subtract, addition);
    }

    private static int[] oneBits(int v, int n) {
        int[] _result = new int[n];
        int count = 0;

        for (int pos = 0; pos < n; pos++) {
            if ((v & (1 << pos)) != 0) {
                _result[count++] = pos;
            }
        }

        int[] result = new int[count];
        System.arraycopy(_result, 0, result, 0, count);
        return result;
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        PrintWriter output = new PrintWriter(System.out);

        int testsCount = input.nextInt();
        input.nextLine();

        for (int test = 0; test < testsCount; test++) {
            (new Solution()).run(input, output);
        }

        output.flush();
    }
}