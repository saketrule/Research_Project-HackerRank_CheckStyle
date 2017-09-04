import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class Solution {
 static class Foo36 {
  static final int MOD = 1000000007;
  int N;
  int M;
  ArrayList<Integer>[][][] stateTrans;
  ArrayList<Integer>[][] transRes;
  // L = {{offset, length, line 1 width, line 2, line 3}..} don't rearrange
  int[][] L = {{0, 2, 2, 1, 1}, {0, 3, 1, 7, 0}, {1, 2, 1, 2, 3}, {0, 3, 3, 4, 0},
    {0, 2, 2, 2, 2}, {0, 3, 3, 1, 0}, 
    {0, 2, 1, 1, 1}, // this one ahead 
    {2, 3, 1, 7, 0}
  };
  
  int[] matrix;
  void main() {
   // precompute
   transRes = new ArrayList[9][];
   for (int i = 1; i <= 8; i++) {
    M = i;
    genStateTrans();
    transRes[i] = stateTrans[0][0];
   }
   BufferedReader br = null;
   try {
    br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int i = 0; i < T; i++) {
     String[] s = br.readLine().split("\\s");
     N = Integer.parseInt(s[0]);
     M = Integer.parseInt(s[1]);
     matrix = new int[N];
     for (int j = 0; j < N; j++) {
      String str = br.readLine();
      int val = 0;
      for (int k = 0; k < M; k++) {
       if (str.charAt(k) == '#')
        val |= 1<<k;
      }
      matrix[j] = val;
     }
     int res = 0;
     if (M == 1) {
      res = 1;
      for (int k = 0; k < N; k++)
       if (matrix[i] == 0)
        res = 0;
      
     } else if (N == 1) {
      res = matrix[0] == (1<<M)-1 ? 1 : 0;
     } else {
      res = foo();
     }
     System.out.println(res);
    }
   } catch (Exception e) {
    e.printStackTrace();
   } finally {
    try {
     br.close();
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
   
  }
  
  int foo() {
   int[][] mem = new int[1<<2*M][N+1];
   for (int[] val : mem) Arrays.fill(val, -1);
   int res = solve(matrix[0] | matrix[1]<<M, 1, mem);
   return res;
  }
  
  int solve(int curr, int index, int[][] mem) {
   if (mem[curr][index] >= 0)
    return mem[curr][index];
   if (index == matrix.length) {
    if ((curr&(1<<M)-1) == (1<<M)-1 && (curr>>M & (1<<M)-1) == 0)
     return mem[curr][index] = 1;
    return mem[curr][index] = 0;
   }
   int res = 0;
   for (int next : transRes[M][curr]) {
    int hi = next>>M & (1<<M)-1;
    if (index+1 >= matrix.length || (matrix[index+1] & hi) == 0) {
     res = (res + solve(next | (index+1 >= matrix.length ? 0 :matrix[index+1]<<M), index+1, mem))%MOD;
    }
   }
   return mem[curr][index] = res;
  }
  
  
  void genStateTrans() {
   stateTrans = new ArrayList[M+1][2][1<<2*M];
   for (int src = 0; src < (1<<2*M); src++) {
    doit(src, 0, 0);
   }
   
  }
  
  
  
  
  // this is one of the most awful functions i've ever written.. 
  ArrayList<Integer> doit(int curr, int k, int oneAhead) {
   if (stateTrans[k][oneAhead][curr] != null)
    return stateTrans[k][oneAhead][curr];
   ArrayList<Integer> res = new ArrayList<Integer>();
   stateTrans[k][oneAhead][curr] = res;
   
   if (k == M) {
    res.add((curr & (1<<M)-1)<<M | curr>>M & (1<<M)-1);
    return res;
   }
   if ((curr & 1<<k) != 0) {
    if (oneAhead == 0)
     curr ^= 1<<k;
    ArrayList<Integer> nextArray = doit(curr, k+1, 0);
    updateArray(res, nextArray);
    return res;
   }
   
   for (int i = 0; i < L.length; i++) {
    int[] val = L[i];
    int offset = k-val[0];
    int length = val[1];
    int line1width = val[2];
    int line2 = val[3];
    int line3 = val[4];
    if (offset < 0 || length+offset > M)
     continue;
    int next = curr;
    // check line 1
    //if (((1<<line1width)-1 << offset & curr) != 0)
    // this almost can't be found! better take a look how others define variables
    if (((1<<line1width)-1 << k & curr) != 0)
     continue;
    
    if (oneAhead == 1) {
     next |= 1<<k;
    }
    // check line 3
    if ((next & line3<<offset) != 0)
     continue;
    next |= line3 << offset;
    // check line 2
    if ((line2 << M+offset & curr) != 0)
     continue;
    next |= line2 << M+offset;
    
    int nextOneAhead = 0;
    if (i == 6) {
     nextOneAhead = 1;     
    }
    ArrayList<Integer> nextArray = doit(next, k+line1width, nextOneAhead);
    updateArray(res, nextArray);
   }
   return res;
  }
  void updateArray(ArrayList<Integer> res, ArrayList<Integer> nextArray) {
   res.addAll(nextArray);
  }
 }
 
 public static void main(String[] args) {
  Foo36 foo = new Foo36();
  foo.main();
 }
}