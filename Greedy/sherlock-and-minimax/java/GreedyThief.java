import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 public static void main(String[] args) {
  try {
   BufferedReader stdIn = new BufferedReader(new InputStreamReader(
     System.in));
   solve(stdIn);
   stdIn.close();
  } catch (Exception e) {

  }
 }

 private static void solve(BufferedReader stdIn) throws Exception {
  int n = Integer.parseInt(stdIn.readLine());
  long[] data = new long[n];
  String[] parts = stdIn.readLine().split(" ");
  for (int i = 0; i < n; i++) {
   data[i] = Long.parseLong(parts[i]);
  }
        Arrays.sort(data);
        parts = stdIn.readLine().split(" ");
        long P = Long.parseLong(parts[0]);
        long Q = Long.parseLong(parts[1]);
        long result = Long.MAX_VALUE;
  long M = P;
        for (int i = 0; i < n; i++) {
            result = Math.min(Math.abs(data[i] - P), result);
        }
        for (int i = 0; i < n - 1; i++) {
            long mid = (data[i + 1] + data[i]) / 2;
            if (mid >= P && mid <= Q) {
                if (mid - data[i] > result) {
                    result = mid - data[i];
                    M = mid;
                }
            }
        }
        long tmp = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (Math.abs(data[i] - Q) < tmp) {
                tmp = Math.abs(data[i] - Q);
            }
        }
        if (tmp > result) {
            M = Q;
        }
  System.out.println(M);
 }
}