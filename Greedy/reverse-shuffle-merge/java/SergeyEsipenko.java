/* HackerRank Template */

import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class Solution {
 
 static long initTime;
 static final Random rnd = new Random(7777L);
 static boolean writeLog = false;
 
 public static void main(String[] args) throws IOException {
  initTime = System.currentTimeMillis();
  try {
   writeLog = "true".equals(System.getProperty("LOCAL_RUN_7777"));
  } catch (SecurityException e) {}
  new Thread(null, new Runnable() {
   public void run() {
    try {
     try {
      if (new File("input.txt").exists())
       System.setIn(new FileInputStream("input.txt"));
     } catch (SecurityException e) {}
     long prevTime = System.currentTimeMillis();
     new Solution().run();
     writeLog("Total time: " + (System.currentTimeMillis() - prevTime) + " ms");
     writeLog("Memory status: " + memoryStatus());
    } catch (IOException e) {
     e.printStackTrace();
    }
   }
  }, "1", 1L << 24).start(); 
 }

 void run() throws IOException {
  in = new BufferedReader(new InputStreamReader(System.in));
  out = new PrintWriter(System.out);
  solve();
  out.close();
  in.close();
 }
 
 /*************************************************************** 
  * Solution
  **************************************************************/

 void solve() throws IOException  {
  
  char[] s = in.readLine().toCharArray();
  
  rev(s);
  
  int len = s.length;
  
  if (len == 0) {
   out.println();
   return;
  }
  
  int[][] count = new int [len + 1][26];
  for (int i = len - 1; i >= 0; i--) {
   System.arraycopy(count[i + 1], 0, count[i], 0, count[i].length);
   count[i][s[i] - 'a']++;
  }
  int[] need = Arrays.copyOf(count[0], count[0].length);
  for (int i = 0; i < need.length; i++) need[i] >>= 1;
  
  int[][] next = new int [len][26];
  for (int[] a : next) fill(a, -1);
  for (int i = len - 1; i > 0; i--) {
   System.arraycopy(next[i], 0, next[i - 1], 0, next[i].length);
   int c = s[i] - 'a';
   next[i - 1][c] = i;
  }

  int[] cPos = Arrays.copyOf(next[0], next[0].length);
  cPos[s[0] - 'a'] = 0;
  
  char[] ans = new char [len / 2];
  for (int i = 0; i < ans.length; i++) {
   for (int c = 0; c < need.length; c++) {
    if (need[c] > 0) {
     boolean ok = true;
     for (int cc = 0; cc < need.length; cc++) {
      if (count[cPos[c]][cc] < need[cc]) {
       ok = false;
       break;
      }
     }
     if (ok) {
      ans[i] = (char) ('a' + c);
      need[c]--;
      for (int cc = 0; cc < need.length; cc++) {
       if (cc == c || need[cc] == 0) continue;
       while (cPos[cc] < cPos[c])
        cPos[cc] = next[cPos[cc]][cc];
      }
      cPos[c] = next[cPos[c]][c];
      break;
     }
    }
   }
  }
  
  out.println(ans);
 }
 
 void rev(char[] s) {
  int n = s.length;
  for (int i = 0; i + i < s.length; i++) {
   char c = s[i];
   s[i] = s[n - 1 - i];
   s[n - 1 - i] = c;
  }
 }
 
 /*************************************************************** 
  * Input 
  **************************************************************/
 BufferedReader in;
 PrintWriter out;
 StringTokenizer st = new StringTokenizer("");
 
 String nextToken() throws IOException {
  while (!st.hasMoreTokens())
   st = new StringTokenizer(in.readLine());
  return st.nextToken();
 }
 
 int nextInt() throws IOException {
  return Integer.parseInt(nextToken());
 }
 
 long nextLong() throws IOException {
  return Long.parseLong(nextToken());
 }
 
 double nextDouble() throws IOException {
  return Double.parseDouble(nextToken());
 }
 
 int[] nextIntArray(int size) throws IOException {
  int[] ret = new int [size];
  for (int i = 0; i < size; i++)
   ret[i] = nextInt();
  return ret;
 }
 
 long[] nextLongArray(int size) throws IOException {
  long[] ret = new long [size];
  for (int i = 0; i < size; i++)
   ret[i] = nextLong();
  return ret;
 }
 
 double[] nextDoubleArray(int size) throws IOException {
  double[] ret = new double [size];
  for (int i = 0; i < size; i++)
   ret[i] = nextDouble();
  return ret;
 }
 
 String nextLine() throws IOException {
  st = new StringTokenizer("");
  return in.readLine();
 }
 
 boolean EOF() throws IOException {
  while (!st.hasMoreTokens()) {
   String s = in.readLine();
   if (s == null)
    return true;
   st = new StringTokenizer(s);
  }
  return false;
 }
 
 /*************************************************************** 
  * Output 
  **************************************************************/
 void printRepeat(String s, int count) {
  for (int i = 0; i < count; i++)
   out.print(s);
 }
 
 void printArray(int[] array) {
  if (array == null || array.length == 0)
   return;
  for (int i = 0; i < array.length; i++) {
   if (i > 0) out.print(' ');
   out.print(array[i]);
  }
  out.println();
 }
 
 void printArray(long[] array) {
  if (array == null || array.length == 0)
   return;
  for (int i = 0; i < array.length; i++) {
   if (i > 0) out.print(' ');
   out.print(array[i]);
  }
  out.println();
 }
 
 void printArray(double[] array) {
  if (array == null || array.length == 0)
   return;
  for (int i = 0; i < array.length; i++) {
   if (i > 0) out.print(' ');
   out.print(array[i]);
  }
  out.println();
 }
 
 void printArray(double[] array, String spec) {
  if (array == null || array.length == 0)
   return;
  for (int i = 0; i < array.length; i++) {
   if (i > 0) out.print(' ');
   out.printf(Locale.US, spec, array[i]);
  }
  out.println();
 }
 
 void printArray(Object[] array) {
  if (array == null || array.length == 0)
   return;
  boolean blank = false;
  for (Object x : array) {
   if (blank) out.print(' '); else blank = true;
   out.print(x);
  }
  out.println();
 }
 
 @SuppressWarnings("rawtypes")
 void printCollection(Collection collection) {
  if (collection == null || collection.isEmpty())
   return;
  boolean blank = false;
  for (Object x : collection) {
   if (blank) out.print(' '); else blank = true;
   out.print(x);
  }
  out.println();
 }
 
 /*************************************************************** 
  * Utility
  **************************************************************/
 static String memoryStatus() {
  return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() >> 20) + "/" + (Runtime.getRuntime().totalMemory() >> 20) + " MB";
 }
 
 static void checkMemory() {
  System.err.println(memoryStatus());
 }
 
 static long prevTimeStamp = Long.MIN_VALUE;
 
 static void updateTimer() {
  prevTimeStamp = System.currentTimeMillis();
 }
 
 static long elapsedTime() {
  return (System.currentTimeMillis() - prevTimeStamp);
 }
 
 static void checkTimer() {
  System.err.println(elapsedTime() + " ms");
 }
 
 static void chk(boolean f) {
  if (!f) throw new RuntimeException("Assert failed");
 }
 
 static void chk(boolean f, String format, Object ... args) {
  if (!f) throw new RuntimeException(String.format(format, args));
 }
 
 static void writeLog(String format, Object ... args) {
  if (writeLog) System.err.println(String.format(Locale.US, format, args));
 }
 
 static void swap(int[] a, int i, int j) {
  int tmp = a[i];
  a[i] = a[j];
  a[j] = tmp;
 }
 
 static void swap(long[] a, int i, int j) {
  long tmp = a[i];
  a[i] = a[j];
  a[j] = tmp;
 }
 
 static void swap(double[] a, int i, int j) {
  double tmp = a[i];
  a[i] = a[j];
  a[j] = tmp;
 }
 
 static void shuffle(int[] a, int from, int to) {
  for (int i = from; i < to; i++)
   swap(a, i, rnd.nextInt(a.length));
 }
 
 static void shuffle(long[] a, int from, int to) {
  for (int i = from; i < to; i++)
   swap(a, i, rnd.nextInt(a.length));
 }
 
 static void shuffle(double[] a, int from, int to) {
  for (int i = from; i < to; i++)
   swap(a, i, rnd.nextInt(a.length));
 }
 
 static void shuffle(int[] a) {
  if (a == null) return;
  shuffle(a, 0, a.length);
 }
 
 static void shuffle(long[] a) {
  if (a == null) return;
  shuffle(a, 0, a.length);
 }
 
 static void shuffle(double[] a) {
  if (a == null) return;
  shuffle(a, 0, a.length);
 }
 
 static long[] getPartialSums(int[] a) {
  final long[] sums = new long [a.length + 1];
  for (int i = 0; i < a.length; i++)
   sums[i + 1] = sums[i] + a[i];
  return sums;
 }
 
 static long[] getPartialSums(long[] a) {
  final long[] sums = new long [a.length + 1];
  for (int i = 0; i < a.length; i++)
   sums[i + 1] = sums[i] + a[i];
  return sums;
 }
 
 static int[] getOrderedSet(int[] a) {
  final int[] set = Arrays.copyOf(a, a.length);
  if (a.length == 0) return set;
  shuffle(set);
  sort(set);
  int k = 1;
  int prev = set[0];
  for (int i = 1; i < a.length; i++) {
   if (prev != set[i]) {
    set[k++] = prev = set[i];
   }
  }
  return Arrays.copyOf(set, k);
 }
 
 static long[] getOrderedSet(long[] a) {
  final long[] set = Arrays.copyOf(a, a.length);
  if (a.length == 0) return set;
  shuffle(set);
  sort(set);
  int k = 1;
  long prev = set[0];
  for (int i = 1; i < a.length; i++) {
   if (prev != set[i]) {
    set[k++] = prev = set[i];
   }
  }
  return Arrays.copyOf(set, k);
 }
}