import java.util.*;

public class Solution
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int r = scan.nextInt(), c = scan.nextInt(), rot = scan.nextInt();
        int[][] base = new int[r][c];
        for(int i = 0; i < r; i++) for(int j = 0; j < c; j++) base[i][j] = scan.nextInt();

        int x1 = 0, y1 = 0, x2 = c - 1, y2 = r - 1;

        int[][] mat = new int[r][c];

        while(x2 - x1 > 0 && y2 - y1 > 0)
        {
            int[][] indices = loopIndices(x1, y1, x2, y2);

            for(int i = 0; i < indices.length; i++) mat[indices[(i + rot) % indices.length][0]]
                                                       [indices[(i + rot) % indices.length][1]] = base[indices[i][0]][indices[i][1]];

            x1++;
            y1++;
            x2--;
            y2--;
        }

        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++) System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }

    public static int[][] loopIndices(int x1, int y1, int x2, int y2)
    {
        int dx = x2 - x1, dy = y2 - y1, n = 2 * dx + 2 * dy;
        int[][] indices = new int[n][2];

        int offset = 0;
        for(int i = 0; i < dy; i++) indices[i] = new int[]{y1 + i, x1};

        offset += dy;
        for(int i = 0; i < dx; i++) indices[i + offset] = new int[]{y2, x1 + i};

        offset += dx;
        for(int i = 0; i < dy; i++) indices[i + offset] = new int[]{y2 - i, x2};

        offset += dy;
        for(int i = 0; i < dx; i++) indices[i + offset] = new int[]{y1, x2 - i};

        return indices;
    }
}