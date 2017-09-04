/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;

/**
 * You are given a grid having N rows and M columns. A grid square can either be
 * blocked or empty. Blocked squares are represented by a '#' and empty squares
 * are represented by '.'. Find the number of ways to tile the grid using L
 * shaped bricks. A L brick has one side of length three units while other of
 * length 2 units. All empty squares in the grid should be covered by exactly
 * one of the L shaped tiles, and blocked squares should not be covered by any
 * tile. The bricks can be used in any orientation (they can be rotated or
 * flipped).
 * 
 * Input: The first line contains the number of test cases T. T cases follow.
 * Each case contains N and M on the first line, followed by N lines describing
 * each row of the grid.
 * 
 * Output: Output the number of ways to tile the grid. Output each answer modulo
 * 1000000007.
 * 
 * Constraints: 1 <= T <= 50 1 <= N <= 20 1 <= M <= 8 Each grid square will be
 * either '.' or '#'.
 * 
 * Sample Input: <br>
 * 3<br>
 * 2 4<br>
 * ....<br>
 * ....<br>
 * 3 3<br>
 * ...<br>
 * .#.<br>
 * ...<br>
 * 2 2<br>
 * ##<br>
 * ##<br>
 * 
 * Sample Output: 2 4 1
 * 
 * NOTE : If all points in the grid are blocked the number of ways is 1, as in
 * the last sample testcase.
 * 
 * @author sunweik
 * 
 */
public class Solution
{
  private static final long N         = 1000000007;
  private static boolean    debugmode = false;
  private static int        row;
  private static int        column;
  private static HashMap<BigInteger, Long> cache = new HashMap<BigInteger, Long>();

  public static class Coordinate
  {
    public int _row;
    public int _column;

    public Coordinate(int r, int c)
    {
      _row = r;
      _column = c;
    }
  }

  public static boolean get(byte b, int bit)
  {
    return (b & (1 << bit)) != 0;
  }

  public static byte set(byte b, int bit)
  {
    return (byte) (b | (1 << bit));
  }

  public static byte clear(byte b, int bit)
  {
    return (byte) (b & ~(1 << bit));
  }

  public static boolean check1(byte bs[], int r, int c)
  {
    return !get(bs[r], c) && !get(bs[r + 1], c) && !get(bs[r + 1], c - 1)
        && !get(bs[r + 1], c - 2);
  }

  public static void set1(byte bs[], int r, int c)
  {
    bs[r] = set(bs[r], c);
    bs[r + 1] = set(bs[r + 1], c);
    bs[r + 1] = set(bs[r + 1], c - 1);
    bs[r + 1] = set(bs[r + 1], c - 2);
  }

  public static void clear1(byte bs[], int r, int c)
  {
    bs[r] = clear(bs[r], c);
    bs[r + 1] = clear(bs[r + 1], c);
    bs[r + 1] = clear(bs[r + 1], c - 1);
    bs[r + 1] = clear(bs[r + 1], c - 2);
  }

  public static boolean check2(byte bs[], int r, int c)
  {
    return !get(bs[r], c) && !get(bs[r], c + 1) && !get(bs[r], c + 2)
        && !get(bs[r + 1], c + 2);
  }

  public static void set2(byte bs[], int r, int c)
  {
    bs[r] = set(bs[r], c);
    bs[r] = set(bs[r], c + 1);
    bs[r] = set(bs[r], c + 2);
    bs[r + 1] = set(bs[r + 1], c + 2);
  }

  public static void clear2(byte bs[], int r, int c)
  {
    bs[r] = clear(bs[r], c);
    bs[r] = clear(bs[r], c + 1);
    bs[r] = clear(bs[r], c + 2);
    bs[r + 1] = clear(bs[r + 1], c + 2);
  }

  public static boolean check3(byte bs[], int r, int c)
  {
    return !get(bs[r], c) && !get(bs[r], c + 1) && !get(bs[r], c + 2)
        && !get(bs[r + 1], c);
  }

  public static void set3(byte bs[], int r, int c)
  {
    bs[r] = set(bs[r], c);
    bs[r] = set(bs[r], c + 1);
    bs[r] = set(bs[r], c + 2);
    bs[r + 1] = set(bs[r + 1], c);
  }

  public static void clear3(byte bs[], int r, int c)
  {
    bs[r] = clear(bs[r], c);
    bs[r] = clear(bs[r], c + 1);
    bs[r] = clear(bs[r], c + 2);
    bs[r + 1] = clear(bs[r + 1], c);
  }

  public static boolean check4(byte bs[], int r, int c)
  {
    return !get(bs[r], c) && !get(bs[r + 1], c) && !get(bs[r + 1], c + 1)
        && !get(bs[r + 1], c + 2);
  }

  public static void set4(byte bs[], int r, int c)
  {
    bs[r] = set(bs[r], c);
    bs[r + 1] = set(bs[r + 1], c);
    bs[r + 1] = set(bs[r + 1], c + 1);
    bs[r + 1] = set(bs[r + 1], c + 2);
  }

  public static void clear4(byte bs[], int r, int c)
  {
    bs[r] = clear(bs[r], c);
    bs[r + 1] = clear(bs[r + 1], c);
    bs[r + 1] = clear(bs[r + 1], c + 1);
    bs[r + 1] = clear(bs[r + 1], c + 2);
  }

  public static boolean check5(byte bs[], int r, int c)
  {
    return !get(bs[r], c) && !get(bs[r + 1], c) && !get(bs[r + 2], c)
        && !get(bs[r + 2], c - 1);
  }

  public static void set5(byte bs[], int r, int c)
  {
    bs[r] = set(bs[r], c);
    bs[r + 1] = set(bs[r + 1], c);
    bs[r + 2] = set(bs[r + 2], c);
    bs[r + 2] = set(bs[r + 2], c - 1);
  }

  public static void clear5(byte bs[], int r, int c)
  {
    bs[r] = clear(bs[r], c);
    bs[r + 1] = clear(bs[r + 1], c);
    bs[r + 2] = clear(bs[r + 2], c);
    bs[r + 2] = clear(bs[r + 2], c - 1);
  }

  public static boolean check6(byte bs[], int r, int c)
  {
    return !get(bs[r], c) && !get(bs[r], c + 1) && !get(bs[r + 1], c + 1)
        && !get(bs[r + 2], c + 1);
  }

  public static void set6(byte bs[], int r, int c)
  {
    bs[r] = set(bs[r], c);
    bs[r] = set(bs[r], c + 1);
    bs[r + 1] = set(bs[r + 1], c + 1);
    bs[r + 2] = set(bs[r + 2], c + 1);
  }

  public static void clear6(byte bs[], int r, int c)
  {
    bs[r] = clear(bs[r], c);
    bs[r] = clear(bs[r], c + 1);
    bs[r + 1] = clear(bs[r + 1], c + 1);
    bs[r + 2] = clear(bs[r + 2], c + 1);
  }

  public static boolean check7(byte bs[], int r, int c)
  {
    return !get(bs[r], c) && !get(bs[r + 1], c) && !get(bs[r + 2], c)
        && !get(bs[r], c + 1);
  }

  public static void set7(byte bs[], int r, int c)
  {
    bs[r] = set(bs[r], c);
    bs[r + 1] = set(bs[r + 1], c);
    bs[r + 2] = set(bs[r + 2], c);
    bs[r] = set(bs[r], c + 1);
  }

  public static void clear7(byte bs[], int r, int c)
  {
    bs[r] = clear(bs[r], c);
    bs[r + 1] = clear(bs[r + 1], c);
    bs[r + 2] = clear(bs[r + 2], c);
    bs[r] = clear(bs[r], c + 1);
  }

  public static boolean check8(byte bs[], int r, int c)
  {
    return !get(bs[r], c) && !get(bs[r + 1], c) && !get(bs[r + 2], c)
        && !get(bs[r + 2], c + 1);
  }

  public static void set8(byte bs[], int r, int c)
  {
    bs[r] = set(bs[r], c);
    bs[r + 1] = set(bs[r + 1], c);
    bs[r + 2] = set(bs[r + 2], c);
    bs[r + 2] = set(bs[r + 2], c + 1);
  }

  public static void clear8(byte bs[], int r, int c)
  {
    bs[r] = clear(bs[r], c);
    bs[r + 1] = clear(bs[r + 1], c);
    bs[r + 2] = clear(bs[r + 2], c);
    bs[r + 2] = clear(bs[r + 2], c + 1);
  }

  public static Coordinate nextAvailableLoc(byte bs[], int r, int c)
  {
    while (r < row) {
      if (get(bs[r], c)) {
        c++;
        if (c >= column) {
          c = 0;
          r++;
        }
      } else
        break;
    }

    if (r >= row)
      return null;
    else
      return new Coordinate(r, c);
  }

  public static void dumpBS(byte bs[])
  {
    if (debugmode) {
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < column; j++) {
          if (get(bs[i], j)) {
            System.out.print('#');
          } else {
            System.out.print('.');
          }
        }
        System.out.println();
      }
      System.out.println();
    }
  }

  public static long brickTiling(byte bs[], int startRow, int startColumn)
  {
    long result = 0;
    if (debugmode) {
      dumpBS(bs);
      System.out.println(startRow + ", " + startColumn);
    }
    if (cache.containsKey(new BigInteger(bs))) {
      if (debugmode) {
      System.out.println("hit cache: " + cache.get(new BigInteger(bs)));
      }
      return cache.get(new BigInteger(bs));
    }

    if (((startRow + 1) < bs.length) && ((startColumn - 2) >= 0)) {
      if (check1(bs, startRow, startColumn)) {
        set1(bs, startRow, startColumn);

        if (debugmode) {
          System.out.println("set 1");
        }
        Coordinate coord = nextAvailableLoc(bs, startRow, startColumn);
        if (coord == null) {
          result++;
          clear1(bs, startRow, startColumn);
          if (debugmode) {
            dumpBS(bs);
            System.out.println("****************************");
          }
        } else {
          result += brickTiling(bs, coord._row, coord._column);
          clear1(bs, startRow, startColumn);
        }
      }
    }

    if (((startRow + 1) < bs.length) && ((startColumn + 2) < column)) {
      if (check2(bs, startRow, startColumn)) {
        set2(bs, startRow, startColumn);
        if (debugmode) {
          System.out.println("set 2");
        }
        Coordinate coord = nextAvailableLoc(bs, startRow, startColumn);
        if (coord == null) {
          result++;
          clear2(bs, startRow, startColumn);
          if (debugmode) {
            dumpBS(bs);
            System.out.println("****************************");
          }
        } else {
          result += brickTiling(bs, coord._row, coord._column);
          clear2(bs, startRow, startColumn);
        }
      }

      if (check3(bs, startRow, startColumn)) {
        set3(bs, startRow, startColumn);
        if (debugmode) {
          System.out.println("set 3");
        }
        Coordinate coord = nextAvailableLoc(bs, startRow, startColumn);
        if (coord == null) {
          result++;
          clear3(bs, startRow, startColumn);
          if (debugmode) {
            dumpBS(bs);
            System.out.println("****************************");
          }
        } else {
          result += brickTiling(bs, coord._row, coord._column);
          clear3(bs, startRow, startColumn);
        }
      }

      if (check4(bs, startRow, startColumn)) {
        set4(bs, startRow, startColumn);
        if (debugmode) {
          System.out.println("set 4");
        }
        Coordinate coord = nextAvailableLoc(bs, startRow, startColumn);
        if (coord == null) {
          result++;
          clear4(bs, startRow, startColumn);
          if (debugmode) {
            dumpBS(bs);
            System.out.println("****************************");
          }
        } else {
          result += brickTiling(bs, coord._row, coord._column);
          clear4(bs, startRow, startColumn);
        }
      }
    }

    if (((startRow + 2) < bs.length) && ((startColumn - 1) >= 0)) {
      if (check5(bs, startRow, startColumn)) {
        set5(bs, startRow, startColumn);
        if (debugmode) {
          System.out.println("set 5");
        }
        Coordinate coord = nextAvailableLoc(bs, startRow, startColumn);
        if (coord == null) {
          result++;
          clear5(bs, startRow, startColumn);
          if (debugmode) {
            dumpBS(bs);
            System.out.println("****************************");
          }
        } else {
          result += brickTiling(bs, coord._row, coord._column);
          clear5(bs, startRow, startColumn);
        }
      }
    }

    if (((startRow + 2) < bs.length) && ((startColumn + 1) < column)) {
      if (check6(bs, startRow, startColumn)) {
        set6(bs, startRow, startColumn);
        if (debugmode) {
          System.out.println("set 6");
        }
        Coordinate coord = nextAvailableLoc(bs, startRow, startColumn);
        if (coord == null) {
          result++;
          clear6(bs, startRow, startColumn);
          if (debugmode) {
            dumpBS(bs);
            System.out.println("****************************");
          }
        } else {
          result += brickTiling(bs, coord._row, coord._column);
          clear6(bs, startRow, startColumn);
        }
      }

      if (check7(bs, startRow, startColumn)) {
        set7(bs, startRow, startColumn);
        if (debugmode) {
          System.out.println("set 7");
        }
        Coordinate coord = nextAvailableLoc(bs, startRow, startColumn);
        if (coord == null) {
          result++;
          clear7(bs, startRow, startColumn);
          if (debugmode) {
            dumpBS(bs);
            System.out.println("****************************");
          }
        } else {
          result += brickTiling(bs, coord._row, coord._column);
          clear7(bs, startRow, startColumn);
        }
      }

      if (check8(bs, startRow, startColumn)) {
        set8(bs, startRow, startColumn);
        if (debugmode) {
          System.out.println("set 8");
        }
        Coordinate coord = nextAvailableLoc(bs, startRow, startColumn);
        if (coord == null) {
          result++;
          clear8(bs, startRow, startColumn);
          if (debugmode) {
            dumpBS(bs);
            System.out.println("****************************");
          }
        } else {
          result += brickTiling(bs, coord._row, coord._column);
          clear8(bs, startRow, startColumn);
        }
      }
    }

    if (debugmode) {
      System.out.println("======================================");
    }
    
    cache.put(new BigInteger(bs), result%N);
    return result % N;
  }

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException
  {
//    BufferedReader in1 = new BufferedReader(new FileReader("src/Brick_Tiling"));

     BufferedReader in2 = new BufferedReader(new
     InputStreamReader(System.in));

    BufferedReader in = in2;
    String line = in.readLine();
    int testNumber = Integer.parseInt(line);
    while (true) {
      line = in.readLine();
      String data[] = line.split(" ");
      row = Integer.parseInt(data[0]);
      column = Integer.parseInt(data[1]);
      byte bs[] = new byte[row];
      for (int i = 0; i < row; i++) {
        line = in.readLine();
        for (int j = 0; j < column; j++) {
          if (line.charAt(j) == '#') {
            bs[i] = set(bs[i], j);
          } else {
            bs[i] = clear(bs[i], j);
          }
        }
      }

      Coordinate start = nextAvailableLoc(bs, 0, 0);
      if (start == null) {
        System.out.println(1);
      } else {
        cache.clear();
        System.out.println(brickTiling(bs, start._row, start._column) % N);
      }

      testNumber--;
      if (testNumber == 0)
        break;
    }
  }

}