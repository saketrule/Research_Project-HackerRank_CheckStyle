import java.util.Scanner;

public class Solution
{
    private static int n;
    private static int m;
    private static char[][] table;
    private static long ans;

    private static int targetBlockCount;
    private static int curBlockCount;

    private static int curFreeLine;
    private static int curFreeColumn;

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);

        int numOfTests = s.nextInt();
        for (int i = 0; i < numOfTests; i++)
        {
            n = s.nextInt();
            m = s.nextInt();
            s.nextLine();
            table = new char[n][];
            for (int j = 0; j < n; j++)
            {
                table[j] = s.nextLine().toCharArray();
            }

            int ans = calcAns();
            System.out.println(ans);
        }
    }

    private static int calcAns()
    {
        if (!canPlaceAnything())
        {
            return 0;
        }

        findFirstFreeCell(0, 0);
        if (curFreeLine == -1)  // All cells are occupied
        {
            return 1;
        }

        ans = 0;
        makeStep(curFreeLine, curFreeColumn);

        return (int)(ans % 1000000007L);
    }

    private static boolean canPlaceAnything()
    {
        int cnt = 0;
        for (char[] a : table)
        {
            for (char c : a)
            {
                if (c == '.')
                {
                    cnt++;
                }
            }
        }

        if ((cnt & 3) != 0)
        {
            return false;
        }
        else
        {
            targetBlockCount = cnt / 4;
            curBlockCount = 0;
            return true;
        }
    }

    private static void findFirstFreeCell(int startLine, int startColumn)
    {
        for (int i = startColumn; i < m; i++)
        {
            if (table[startLine][i] == '.')
            {
                curFreeLine = startLine;
                curFreeColumn = i;
                return;
            }
        }

        for (int i = startLine + 1; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (table[i][j] == '.')
                {
                    curFreeLine = i;
                    curFreeColumn = j;
                    return;
                }
            }
        }

        curFreeLine = -1;
        curFreeColumn = -1;
    }

    private static void makeStep(int line, int column)
    {
        if (curBlockCount == targetBlockCount)
        {
            ans++;
            return;
        }

        findFirstFreeCell(line, column);
        line = curFreeLine;
        column = curFreeColumn;

        // Now try all variants
        if (line < n - 1 && column < m - 2)
        {
            if (canPlaceFigure1(line, column))
            {
                curBlockCount++;
                makeStep(line, column + 3);
                curBlockCount--;
                clearFigure1(line, column);
            }
            if (canPlaceFigure2(line, column))
            {
                curBlockCount++;
                makeStep(line, column + 3);
                curBlockCount--;
                clearFigure2(line, column);
            }
            if (canPlaceFigure3(line, column))
            {
                curBlockCount++;
                makeStep(line, column + 1);
                curBlockCount--;
                clearFigure3(line, column);
            }
        }
        if (line < n - 1 && column > 1 && canPlaceFigure4(line, column))
        {
            curBlockCount++;
            makeStep(line, column + 1);
            curBlockCount--;
            clearFigure4(line, column);
        }

        if (line < n - 2 && column < m - 1)
        {
            if (canPlaceFigure5(line, column))
            {
                curBlockCount++;
                makeStep(line, column + 2);
                curBlockCount--;
                clearFigure5(line, column);
            }
            if (canPlaceFigure6(line, column))
            {
                curBlockCount++;
                makeStep(line, column + 2);
                curBlockCount--;
                clearFigure6(line, column);
            }
            if (canPlaceFigure7(line, column))
            {
                curBlockCount++;
                makeStep(line, column + 1);
                curBlockCount--;
                clearFigure7(line, column);
            }
        }
        if (line < n - 2 && column > 0 && canPlaceFigure8(line, column))
        {
            curBlockCount++;
            makeStep(line, column + 1);
            curBlockCount--;
            clearFigure8(line, column);
        }
    }

    private static boolean canPlaceFigure1(int l, int c)
    {
        if (table[l][c] == '.' && table[l][c + 1] == '.' && table[l][c + 2] == '.' && table[l + 1][c] == '.')
        {
            table[l][c] = table[l][c + 1] = table[l][c + 2] = table[l + 1][c] = '#';
            return true;
        }
        else
        {
            return false;
        }
    }

    private static void clearFigure1(int l, int c)
    {
        table[l][c] = table[l][c + 1] = table[l][c + 2] = table[l + 1][c] = '.';
    }

    private static boolean canPlaceFigure2(int l, int c)
    {
        if (table[l][c] == '.' && table[l][c + 1] == '.' && table[l][c + 2] == '.' && table[l + 1][c + 2] == '.')
        {
            table[l][c] = table[l][c + 1] = table[l][c + 2] = table[l + 1][c + 2] = '#';
            return true;
        }
        else
        {
            return false;
        }
    }

    private static void clearFigure2(int l, int c)
    {
        table[l][c] = table[l][c + 1] = table[l][c + 2] = table[l + 1][c + 2] = '.';
    }

    private static boolean canPlaceFigure3(int l, int c)
    {
        if (table[l][c] == '.' && table[l + 1][c] == '.' && table[l + 1][c + 1] == '.' && table[l + 1][c + 2] == '.')
        {
            table[l][c] = table[l + 1][c] = table[l + 1][c + 1] = table[l + 1][c + 2] = '#';
            return true;
        }
        else
        {
            return false;
        }
    }

    private static void clearFigure3(int l, int c)
    {
        table[l][c] = table[l + 1][c] = table[l + 1][c + 1] = table[l + 1][c + 2] = '.';
    }

    private static boolean canPlaceFigure4(int l, int c)
    {
        if (table[l][c] == '.' && table[l + 1][c] == '.' && table[l + 1][c - 1] == '.' && table[l + 1][c - 2] == '.')
        {
            table[l][c] = table[l + 1][c] = table[l + 1][c - 1] = table[l + 1][c - 2] = '#';
            return true;
        }
        else
        {
            return false;
        }
    }

    private static void clearFigure4(int l, int c)
    {
        table[l][c] = table[l + 1][c] = table[l + 1][c - 1] = table[l + 1][c - 2] = '.';
    }

    private static boolean canPlaceFigure5(int l, int c)
    {
        if (table[l][c] == '.' && table[l][c + 1] == '.' && table[l + 1][c] == '.' && table[l + 2][c] == '.')
        {
            table[l][c] = table[l][c + 1] = table[l + 1][c] = table[l + 2][c] = '#';
            return true;
        }
        else
        {
            return false;
        }
    }

    private static void clearFigure5(int l, int c)
    {
        table[l][c] = table[l][c + 1] = table[l + 1][c] = table[l + 2][c] = '.';
    }

    private static boolean canPlaceFigure6(int l, int c)
    {
        if (table[l][c] == '.' && table[l][c + 1] == '.' && table[l + 1][c + 1] == '.' && table[l + 2][c + 1] == '.')
        {
            table[l][c] = table[l][c + 1] = table[l + 1][c + 1] = table[l + 2][c + 1] = '#';
            return true;
        }
        else
        {
            return false;
        }
    }

    private static void clearFigure6(int l, int c)
    {
        table[l][c] = table[l][c + 1] = table[l + 1][c + 1] = table[l + 2][c + 1] = '.';
    }

    private static boolean canPlaceFigure7(int l, int c)
    {
        if (table[l][c] == '.' && table[l + 1][c] == '.' && table[l + 2][c] == '.' && table[l + 2][c + 1] == '.')
        {
            table[l][c] = table[l + 1][c] = table[l + 2][c] = table[l + 2][c + 1] = '#';
            return true;
        }
        else
        {
            return false;
        }
    }

    private static void clearFigure7(int l, int c)
    {
        table[l][c] = table[l + 1][c] = table[l + 2][c] = table[l + 2][c + 1] = '.';
    }

    private static boolean canPlaceFigure8(int l, int c)
    {
        if (table[l][c] == '.' && table[l + 1][c] == '.' && table[l + 2][c] == '.' && table[l + 2][c - 1] == '.')
        {
            table[l][c] = table[l + 1][c] = table[l + 2][c] = table[l + 2][c - 1] = '#';
            return true;
        }
        else
        {
            return false;
        }
    }

    private static void clearFigure8(int l, int c)
    {
        table[l][c] = table[l + 1][c] = table[l + 2][c] = table[l + 2][c - 1] = '.';
    }
}