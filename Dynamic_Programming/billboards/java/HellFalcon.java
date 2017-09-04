import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Maximizer {
 
 private int s;
 private long[] data;
 
 public Maximizer(int size) {
  s = 1;
  while (s < size) s <<= 1;
  data = new long[s + size];
  Arrays.fill(data, Long.MIN_VALUE);
 }
 
 public void setValue(int index, long value) {
  index += s;
  while (index > 0) {
   data[index] = value > data[index] ? value : data[index];
   index >>>= 1;
  }
 }
 
 public long getMax(int l, int r) {
  l += s;
  r += s;
  long max = Long.MIN_VALUE;
  while (l <= r) {
   if ((l & 1) == 1) {
    max = max > data[l] ? max : data[l];
    l++;
   }
   l >>>= 1;
   if ((r & 1) == 0) {
    max = max > data[r] ? max : data[r];
    r--;
   }
   r >>>= 1;
  }
  return max;
 }
}

public class Solution {
 static public void main(String[] args) {
  try {
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 64 << 10);
   StringTokenizer tokenizer = new StringTokenizer(br.readLine());
   int n = Integer.parseInt(tokenizer.nextToken());
   int k = Integer.parseInt(tokenizer.nextToken());
   long[] sum = new long[n+1];
   for (int i = 0; i < n; i++) {
    sum[i+1] = sum[i] + Long.parseLong(br.readLine().trim());
   }
   long[] f = new long[n+2];
   Maximizer maximizer = new Maximizer(n+1);
   for (int i = 0; i <= n; i++) {
    maximizer.setValue(i, f[i] - sum[i]);
    f[i+1] = sum[i] + maximizer.getMax(Math.max(0, i-k), i);
   }
   System.out.println(f[n+1]);
  }
  catch (Exception e) {
   System.err.println("Error:" + e.getMessage());
  }
 }
}