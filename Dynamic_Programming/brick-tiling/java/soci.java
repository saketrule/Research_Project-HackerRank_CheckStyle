import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class Solution {

 private Grid[] testCases = null;

 public static void main(String[] args) throws Exception {
  Solution s = new Solution();

  s.loadInput(System.in);

  for (int i = 0; i < s.testCases.length; i++) {
   System.out.println(s.testCases[i].getNumberOfTilings());
  }

 }

 private void loadInput(InputStream in) throws Exception {
  BufferedReader br = new BufferedReader(new InputStreamReader(in));

  testCases = new Grid[Integer.parseInt(br.readLine())];

  for (int i = 0; i < testCases.length; i++) {
   String[] sizes = br.readLine().split("[\\s]");
   testCases[i] = new Grid(Integer.parseInt(sizes[0]),
     Integer.parseInt(sizes[1]));
   for (int rows = 0; rows < testCases[i].getRowsNum(); rows++) {
    testCases[i].setRow(rows, br.readLine().toCharArray());
   }
  }

 }

 private class Grid {
  private char[][] grid;
  private int empty = 0;
  private int tilingWays = 0;
  private Hashtable<String, Boolean> startPoints = new Hashtable<String, Boolean>();
  
  private final LShape[] shapes = new LShape[] {
    new LShape( 3,  2), 
    new LShape( 3, -2), 
    new LShape(-3,  2),
    new LShape(-3, -2),
    new LShape( 2,  3),
    new LShape( 2, -3),
    new LShape(-2,  3),
    new LShape(-2, -3)};

  private class LShape {
   public int wid;
   public int len;
   public int size = 4;
   public final int minSize = 4;


   public LShape(int len, int wid) {
    this.wid = wid;
    this.len = len;
   }
  }

  public Grid(int n, int m) {
   grid = new char[n][m];
  }

  public int getRowsNum() {
   if (grid == null)
    return 0;
   return grid.length;
  }

  public void setRow(int rowNum, char[] rowChars) {
   grid[rowNum] = rowChars;
   for (int i = 0; i < rowChars.length; i++) {
    if (rowChars[i] == '.')
     empty++;
   }
  }

  public int getNumberOfTilings() {
   tileAllPossibleWays(true);
   if (tilingWays == 0) tilingWays = 1;
   return tilingWays;
  }

  private void tileAllPossibleWays(boolean startLevel) {
   if (empty > 0 && empty < shapes[0].minSize) {
    return;   
   }
   for (int x = 0; x < grid.length; x++) {
    for (int y = 0; y < grid[0].length; y++) {
     if (grid[x][y] == '.') {
      if (startLevel && startPointRegistered(x, y)) continue;
      
      for (int shapeIdx = 0; shapeIdx < shapes.length; shapeIdx++) {       
       if (setNextTile(x, y, shapes[shapeIdx])) {
        empty -= shapes[shapeIdx].size;
        if (empty == 0) {
         tilingWays++;
         registerTileStartPoint(x, y);
        } else {
         tileAllPossibleWays(false);
        }
        clearTile(x, y, shapes[shapeIdx]);
        empty += shapes[shapeIdx].size;
       } 
      }
     }
    }
   }
  }
  
  private void registerTileStartPoint(int x, int y) {
   startPoints.put(x + ":" + y, true);
  }
  
  private boolean startPointRegistered(int x, int y) {
   return startPoints.containsKey(x + ":" + y);
  }

  private boolean setNextTile(int idxL, int idxW, LShape shape) {
   int lenSign = Integer.signum(shape.len);
   int widSign = Integer.signum(shape.wid);
      
   if (idxL + shape.len - lenSign >= grid.length || idxL + shape.len - lenSign < 0) {
    return false;
   }
   if (idxW + shape.wid - widSign >= grid[0].length || idxW + shape.wid - widSign < 0) {
    return false;
   }
   
   for (int l = 0; l < Math.abs(shape.len); l++) {
    if (grid[(l * lenSign) + idxL][idxW ] != '.') {
     return false;
    }
   }
   for (int w = 0; w < Math.abs(shape.wid); w++) {
    if (grid[shape.len - lenSign + idxL][(w * widSign) + idxW] != '.') {
     return false;
    }
   }
   for (int l = 0; l < Math.abs(shape.len); l++) {
    grid[(l * lenSign) + idxL][idxW] = 'L';
   }
   for (int w = 0; w < Math.abs(shape.wid); w++) {
    grid[shape.len - lenSign + idxL][(w * widSign) + idxW] = 'L';
   }
   return true;
  }

  private void clearTile(int idxL, int idxW, LShape shape) {
   int lenSign = Integer.signum(shape.len);
   int widSign = Integer.signum(shape.wid);

   for (int l = 0; l < Math.abs(shape.len); l++) {
    grid[(l * lenSign) + idxL][idxW] = '.';
   }
   for (int w = 0; w < Math.abs(shape.wid); w++) {
    grid[shape.len - lenSign + idxL][(w * widSign) + idxW] = '.';
   }
  }
 }
}