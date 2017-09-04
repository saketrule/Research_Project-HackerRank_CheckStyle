import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Random;

public class Solution {

 public static void main(String[] args) throws Exception {
  // TODO Auto-generated method stub
  new Solution().run();
 }

 StreamTokenizer st;

 int n;

 private void run() throws Exception {
  // TODO Auto-generated method stub
  st = new StreamTokenizer(new BufferedReader(new InputStreamReader(
    System.in)));
  n = nextInt();
  int[] a = new int[n];
  for (int i = 0; i < n; i++) {
   a[i] = nextInt();
  }
  int from = nextInt();
  int to = nextInt();
  Arrays.sort(a);
  int max = -1;
  int mr = Integer.MAX_VALUE;
  int r = check(a, from);
  if (max < r || max == r && from < mr) {
   max = r;
   mr = from;
  }
  r = check(a, to);
  if (max < r || max == r && to < mr) {
   max = r;
   mr = to;
  }
  for (int i = 0; i < n - 1; i++) {
   int l = a[i + 1] - a[i] + 1;
   if (l % 2 == 1) {
    int value = (a[i + 1] + a[i]) / 2;
    if (value >= from && value <= to) {
     r = check(a, value);
     if (max < r || max == r && value < mr) {
      max = r;
      mr = value;
     }
    }
   } else {
    int value = (a[i + 1] + a[i]) / 2;
    if (value >= from && value <= to) {
     r = check(a, value);
     if (max < r || max == r && value < mr) {
      max = r;
      mr = value;
     }
    }
    value = (a[i + 1] + a[i]) / 2 + 1;
    if (value >= from && value <= to) {
     r = check(a, value);
     if (max < r || max == r && value < mr) {
      max = r;
      mr = value;
     }
    }
   }
  }
  System.out.println(mr);
 }

 private int check(int[] a, int value) {
  int pos = Arrays.binarySearch(a, value);
  if (pos >= 0)
   return 0;
  pos = -(pos + 1);
  int res = Integer.MAX_VALUE;
  if (pos < a.length)
   res = Math.min(res, Math.abs(value - a[pos]));
  if (pos + 1 < a.length)
   res = Math.min(res, Math.abs(value - a[pos + 1]));
  if (pos - 1 >= 0)
   res = Math.min(res, Math.abs(value - a[pos - 1]));
  return res;
 }

 private int nextInt() throws Exception {
  // TODO Auto-generated method stub
  st.nextToken();
  return (int) st.nval;
 }

}