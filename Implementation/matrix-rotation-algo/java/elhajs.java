import java.util.Scanner;

public class Solution {
 static int M;
 static int N;
 static int R;

 static int[] index(int i, int j) {
  int[] t = new int[2];

  int l = getLevel(i, j);
  int r = 2 * (M - 4 * l + N - 2);
  r = R % r;
  int r1 = r;
  if (i == l && j < N - 1 - l && j > l) {
   r1 -= j -l;
   if (r1 > 0) {
    j = l;
   }
   else j = j - r;  
  }
  else if (j ==l && i > l && i < M -1 - l) {
   r1 -= M - 1 -l - i;
   if (r1 > 0) {
    i = M - 1 - l;
   }
   else i = i + r;
  }
  else if (i == M - 1 - l && j < N - 1 - l && j > l) {
   r1 -= N - 1 - l - j;
   if (r1 > 0) {
    j = N - 1 - l;
    
   }
   else j = j + r;
  }
  else if (j == N - 1 - l && i > l && i < M - 1 - l) {
   r1 -= i - l;
   if (r1 > 0) {
    i = l;
   }
   else i -= r;
  }
  r = r1;
  
  while (r > 0) {
   if (i == l && j == l) {
    if (M - 1 - l - i > r) {
     i = i + r;
     r = 0;
    }
    else {
     i = M - 1 - l;
     r -= M - 1 - l - l; 
    }
   }
   else if (i == M - 1 - l && j == l) {
    if (N - 1 - l -l > r) {
     j = j + r;
     r = 0; 
    }
    else {
     j = N - 1 - l;
     r -= N - 1 - l - l;
    }
    
   }
   else if (i == M - 1 - l && j == N - 1 - l) {
    if (M - 1 - l - r > l) {
     i = i - r;
     r = 0;
     
    }
    else {
     
     i = l;
     r -= M - 1 - l - l;
    }
   }
   else {
    if (N - 1 - l - r > l) {
     j = j - r;
     r = 0;
    }
    else {
     
     j = l;
     r -= N - 1 - l - l;
    }
   }
  }
  t[0] = i;
  t[1] = j;
  return t;
 }

 static int getLevel(int i, int j) {
  return Math.min(Math.min(i, M - 1 - i), Math.min(j, N - 1 - j));
 }

 public static void main(String[] args) {

  /*
   * Enter your code here. Read input from STDIN. Print output to STDOUT.
   * Your class should be named Solution.
   */
  Scanner in = new Scanner(System.in);
  M = in.nextInt();
  N = in.nextInt();
  R = in.nextInt();
  int[][] arr = new int[M][N];
  for (int i = 0; i < M; i++) {
   for (int j = 0; j < N; j++) {
    int[] t = index(i, j);
    //System.out.println("t( "+i+", "+j+") = ( "+t[0]+", "+t[1]+")");
    arr[t[0]][t[1]] = in.nextInt();
    
   }
  }
  for (int i = 0; i < M; i++) {
   String s = "";
   for (int j = 0; j < N; j++) {
    s += arr[i][j] + " ";
   }
   System.out.println(s.trim());
  }
 }

}