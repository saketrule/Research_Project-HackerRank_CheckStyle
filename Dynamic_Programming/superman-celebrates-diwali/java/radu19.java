import java.util.Arrays;
import java.util.Scanner;


public class supermanDiwali {

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int n = in.nextInt();
  int h = in.nextInt();
  int i = in.nextInt();
  Buildings b = new Buildings(n, h, i);
  
  for (int k = 0; k < n; k++) {
   int persInB = in.nextInt();
   for (int j = 0; j < persInB; j++) {
    b.buildings[k][in.nextInt() - 1]++;
   }
  }
  
  System.out.println(b.getMaxSaved());
  in.close();
 }
 
 private static class Buildings {
  int n, h, i;
  int[][] buildings;
  int[] maxes;
  
  public Buildings(int n, int h, int i) {
   this.n = n;
   this.h = h;
   this.i = i;
   initBuildings();
  }
  
  private void initBuildings() {
   buildings = new int[n][h];
   for (int i = 0; i < n; i++)
    for (int j = 0; j < h; j++) 
     buildings[i][j] = 0;
   maxes = new int[h];
   Arrays.fill(maxes, 0);
  }
  
  public int getMaxSaved() {
   fillMaxes();
   for (int k = h - 2; k >= 0; k--) {
    for (int j = 0; j < n; j++) {
     buildings[j][k] += k + i >= h  || maxes[k + i] == j ? buildings[j][k + 1] : Math.max(buildings[j][k + 1], buildings[maxes[k + i]][k + i]);
     if (buildings[j][k] > buildings[maxes[k]][k]) {
      maxes[k] = j;
     }
    }
   }
   
   return buildings[maxes[0]][0];
  }
  

  private void fillMaxes() {
            for (int j = 0; j < n; j++) {
                if (buildings[j][h - 1] > buildings[maxes[h - 1]][h - 1])
                    maxes[h - 1] = j;
            }
  }
 }

}