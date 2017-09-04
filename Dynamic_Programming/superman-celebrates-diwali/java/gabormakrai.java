import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
  Scanner s = new Scanner(System.in);
  int n = s.nextInt();
  int h = s.nextInt();
  int jumpCost = s.nextInt();
        
  int[][] people = new int[n][];
  for (int i = 0; i < n; ++i) {
   people[i] = new int[h];
  }
  for (int i = 0; i < n; ++i) {
   int p = s.nextInt();
   for (int j = 0; j < p; ++j) {
    int k = s.nextInt();
    ++people[i][k - 1];
   }
  }
        
  int[][] solution = new int[n][];
  for (int i = 0; i < n; ++i) {
   solution[i] = new int[h];
  }
        
  int[] jumps = new int[h];
  
  for (int level = 0; level < h; ++level) {
   for (int building = 0; building < n; ++building) {
    
    int previousLevel = (level == 0) ? 0 : solution[building][level - 1];
    int sol = Math.max(previousLevel + people[building][level], jumps[level] + people[building][level]);
    solution[building][level] = sol;
    if (level + jumpCost < h) {
     jumps[level + jumpCost] = Math.max(sol, jumps[level + jumpCost]);
//     for (int i = 0; i < n; ++i) {
//      solution[i][level + jumpCost] = Math.max(solution[i][level + jumpCost], sol);
//     }
    }
        
   }
  }
        
  int max = Integer.MIN_VALUE;
  for (int i = 0; i < n; ++i) {
   max = Math.max(max, solution[i][h - 1]);
  }
  System.out.println(max);
    }
}