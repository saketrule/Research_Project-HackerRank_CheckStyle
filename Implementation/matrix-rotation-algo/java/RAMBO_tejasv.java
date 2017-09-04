import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  int m = scanner.nextInt(), n = scanner.nextInt(), r = scanner.nextInt();
  int[][] a = new int[m][n];
  //r %= 2 * ( m + n - 2);
  //System.out.println("r: " + r);
  for(int i = 0; i < m; ++ i)
   for(int j = 0; j < n; ++ j)
    a[i][j] = scanner.nextInt();
  
  scanner.close();
    
  int layers = Math.min(m, n) / 2;
  
  //each layer contains 2 * (m + n - 2 - 4 * layer) number of items.
  //the variable layer begins from 0 from outside, and increments by 1.
  for(int layer = 0; layer < layers; ++ layer)
  {
   for(int x = 0; x < r % (2 * (m + n - 2 - 4 * layer)); ++ x)
   {
    int i = layer, j = layer;
    int temp = a[layer][layer];
    
    while(i < m - 1 - layer)
    {
     int temp2 = a[i + 1][j];
     a[i + 1][j] = temp;
     temp = temp2;
     i += 1;
    }
    
    while(j < n - 1 - layer)
    {
     int temp2 = a[i][j + 1];
     a[i][j + 1] = temp;
     temp = temp2;
     j += 1;
    }
    
    while(i > layer)
    {
     int temp2 = a[i - 1][j];
     a[i - 1][j] = temp;
     temp = temp2;
     i -= 1;
    }
    
    while(j > layer)
    {
     int temp2 = a[i][j - 1];
     a[i][j - 1] = temp;
     temp = temp2;
     j -= 1;
    }
   }
  }
  
  display(a);
    }
 
 private static void display(int[][] a)
 {
  for(int x = 0; x < a.length; ++ x)
  {
   for(int y = 0; y < a[x].length; ++ y)
    System.out.print(a[x][y] + " ");
   
   System.out.println();
  }
 }
}