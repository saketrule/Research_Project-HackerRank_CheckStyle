import java.util.Scanner;

public class Solution {

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int n = in.nextInt();
  int e = in.nextInt();
  int original[][] = new int[n][n];
  int graph[][][] = new int[n][n][10];

  int temp1, temp2, temp3;

  for (int a0 = 0; a0 < e; a0++) {
   int x = in.nextInt();
   int y = in.nextInt();
   int r = in.nextInt();

   temp1 = r % 10;
   temp2 = 10 - temp1;
   // temp3 = (temp1+temp2)%10;

   original[x - 1][y - 1] = temp1;
   original[y - 1][x - 1] = temp2;

   graph[x - 1][y - 1][temp1] = 1;
   graph[y - 1][x - 1][temp2] = 1;
   /*
    * graph[x-1][y-1][temp3] = 1; graph[y-1][x-1][temp3] = 1;
    */

  }

  int temp;

  for (int i = 0; i < n - 1; ++i) {
   for (int j = i + 1; j < n; ++j) {
    for (int k = 0; k < n; ++k) {
     if (i == k || k == j)
      continue;
     for (int l = 0; l < 10; ++l) {
      if (graph[i][k][l] != 1)
       continue;
      for (int m = 0; m < 10; ++m) {

       if (graph[k][j][m] == 1) {
        temp = (l + m) % 10;
        graph[i][j][temp] = 1;
        graph[j][i][10 - temp] = 1;
       }
      }
     }
    }
   }

  }
  int solution[] = new int[10];
  for (int i = 0; i < n - 1; ++i) {
   for (int j = i + 1; j < n; ++j) {
    for (int k = 0; k < 10; ++k) {
     solution[k] += graph[i][j][k];
     solution[k] += graph[j][i][k];
    }
   }
  }
  for (int k = 0; k < 10; ++k)
   System.out.println(solution[k]);
  in.close();
 }
}