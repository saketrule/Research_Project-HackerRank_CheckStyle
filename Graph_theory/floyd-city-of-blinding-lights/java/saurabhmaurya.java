import java.util.Scanner;

public class Main {
 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int n = sc.nextInt();
  int e = sc.nextInt();
  //int weightArr[][] = new int[n + 1][n + 1];
  int path[][] = new int[n + 1][n + 1];

  for (int i = 1; i <= n; i++)
   for (int j = 1; j <= n; j++)
    if (i == j) {
     path[i][j] = 0;
     
    } else {
     path[i][j] = Integer.MAX_VALUE;
     
    }

  for (int i = 1; i <= e; i++) {
   int start = sc.nextInt();
   int end = sc.nextInt();
   int w = sc.nextInt();
   
   // weightArr[end][start] = w;
   path[start][end] = w;
  }

  /*
   * for (int i = 1; i <= n; i++) { for (int j = 1; j <= n; j++)
   * 
   * System.out.print(path[i][j] + " ");
   * 
   * System.out.println(); }
   */
  for (int k = 1; k <= n; k++)
   for (int i = 1; i <= n; i++)
    for (int j = 1; j <= n; j++) {
     if (j != i) {
      int jToIPath = path[i][k];
      int iToKPath = path[k][j];
      int temp;
      if (jToIPath == Integer.MAX_VALUE)
       temp = Integer.MAX_VALUE;
      else if (iToKPath == Integer.MAX_VALUE)
       temp = Integer.MAX_VALUE;
      else
       temp = iToKPath + jToIPath;
      
      if (temp < path[i][j]) {

       path[i][j] = temp;
      }
     }
    }
  int q = sc.nextInt();
  for (int i = 0; i < q; i++) {
   int sp = path[sc.nextInt()][sc.nextInt()];
   if (sp == Integer.MAX_VALUE)
    System.out.println(-1);
   else
     System.out.println(sp);
  }

  

  sc.close();

 }
}