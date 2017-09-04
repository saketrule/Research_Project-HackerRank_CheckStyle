import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {
 public static void main(String[] args) {
  //worstPermutation(System.in);
  //numberList(System.in);
  //numberListTest();
  supermanCelebratesDiwali(System.in);
 }

 public static void supermanCelebratesDiwali(InputStream in) {
  MyScanner scan = new MyScanner(in);
  int n = scan.nextInt();
  int h = scan.nextInt();
  int loss = scan.nextInt();
  int[][] people = new int[h+1][n];
  for (int i = 0; i < n; i++) {
   int m = scan.nextInt();
   int[] a = scan.nextIntArray(m);
   for (int j = 0; j < a.length; j++) {
    people[a[j]][i]++;
   }
  }
  int[][] dp = new int[h+1][n];
  for (int j = h; j >= 0; j--) {
//   for (int i = 0; i < n; i++) {
//    for (int k = 0; k < n; k++) {
//     int dist = 1;
//     if (k != i) {
//      dist = loss;
//     }
//     dp[j][i] = Math.max(dp[j][i], people[j][i] + (j+dist <= h ? dp[j+dist][k] : 0));
//    }    
//   }
   int max = 0;
   for (int i = 0; i < n; i++) {
    max = Math.max(max, (j+loss <= h ? dp[j+loss][i] : 0));
   }
   for (int i = 0; i < n; i++) {
    dp[j][i] = people[j][i] + Math.max(max, (j+1 <= h ? dp[j+1][i] : 0));
   }
  }
  int max = 0;
  for (int i = 0; i < n; i++) {
   max = Math.max(dp[0][i], max);
  }
  System.out.println(max);
 }

 public static void numberListTest() {
  int numTests = 100000;
  int range = 20;
  Random rand = new Random(0);
  for (int t = 0; t < numTests; t++) {
   int len = rand.nextInt(range)+1;
   int[] a = new int[len];
   for (int i = 0; i < a.length; i++) {
    a[i] = rand.nextInt(range)+1;
   }
   int k = rand.nextInt(range+10);
   long exp = numberList3(a.length, k, a);
   long act = numberList2(a.length, k, a);
   if (exp != act) {
    System.out.println("fail");
   }
    
  }
 }

 public static void numberList(InputStream in) {
  MyScanner scan = new MyScanner(in);
  int tests = scan.nextInt();
  for (int t = 0; t < tests; t++) {
   int n = scan.nextInt();
   int k = scan.nextInt();
   int[] a = scan.nextIntArray(n);
   System.out.println(numberList2(n, k, a));
  }
 }

 public static long numberList3(int n, int k, int[] a) {
  int c = 0;
  for (int i = 0; i < a.length; i++) {
   for (int j = i; j < a.length; j++) {
    int max = 0;
    for (int m = i; m <= j; m++) {
     max = Math.max(max, a[m]);
    }
    if (max > k) {
     c++;
    }
   }
  }
  return c;
 }

 public static long numberList2(int n, int k, int[] a) {
  long freq = (long)n*(n+1)/2;
  long len = 0;
  for (int i = 1; i < a.length; i++) {
   if (i == 0) {
    if (a[i] <= k) {
     len = 1;
    }
   } else {
    if (a[i-1] <= k) {
     len++;
    } else {
     freq -= len*(len+1)/2;
     len = 0;
    }
   }
  }
  if (a[n-1] <= k) {
   len++;
  }
  freq -= len*(len+1)/2;
  return freq;
 }

 public static long numberList(int n, int k, int[] a) {
  long freq = 0;
  //long[] f = new long[10];
  for (int i = 0; i < a.length; i++) {
   int right = 1;
   for (int j = i+1; j < a.length && a[j] < a[i]; j++) {
    right++;
   }
   int left = 1;
   for (int j = i-1; j >= 0 && a[j] < a[i]; j--) {
    left++;
   }
   //f[a[i]] += (long)left * right;
   if (a[i] > k) {
    freq += (long)left * right; 
   }
  }
  return freq;
  //System.out.println(freq);
  //System.out.println(Arrays.toString(f));
 }

 public static void worstPermutation(InputStream in) {
  MyScanner scan = new MyScanner(in);
  int n = scan.nextInt();
  int k = scan.nextInt();
  int[] a = scan.nextIntArray(n);
  int[] pos = new int[n+1];
  for (int i = 0; i < a.length; i++) {
   pos[a[i]] = i;
  }
  
  for (int i = 0; i < a.length && k > 0; i++) {
   if (pos[n-i] > i) {
    int temp = a[i];
    a[i] = a[pos[n-i]];
    a[pos[n-i]] = temp;
    
    pos[a[pos[n-i]]] = pos[n-i];
    pos[a[i]] = i;
    k--;
   }
  }
  
  StringBuilder sb = new StringBuilder();
  for (int i = 0; i < a.length; i++) {
   sb.append(a[i] + " ");
  }
  System.out.println(sb);
 }

 public static class MyScanner {
  BufferedReader br;
  StringTokenizer st;

  public MyScanner(InputStream in) {
   this.br = new BufferedReader(new InputStreamReader(in));
  }

  public void close() {
   try {
    this.br.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }

  String next() {
   while (st == null || !st.hasMoreElements()) {
    try {
     String s = br.readLine();
     if (s != null) {
      st = new StringTokenizer(s);
     } else {
      return null;
     }
    } catch (IOException e) {
     e.printStackTrace();
    }
   }
   return st.nextToken();
  }

  long[] nextLongArray(int n) {
   long[] a = new long[n];
   for (int i = 0; i < a.length; i++) {
    a[i] = this.nextLong();
   }
   return a;
  }

  int[] nextIntArray(int n) {
   int[] a = new int[n];
   for (int i = 0; i < a.length; i++) {
    a[i] = this.nextInt();
   }
   return a;
  }

  int nextInt() {
   return Integer.parseInt(next());
  }

  long nextLong() {
   return Long.parseLong(next());
  }

  double nextDouble() {
   return Double.parseDouble(next());
  }

  String nextLine(){
   String str = "";
   try {
    str = br.readLine();
   } catch (IOException e) {
    e.printStackTrace();
   }
   return str;
  }
 }
}