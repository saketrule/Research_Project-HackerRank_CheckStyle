import java.io.*;
import java.util.*;

public class Solution {
 static TreeMap<Long, Integer> record = new TreeMap<Long, Integer>();
 
 static long least() {
  return record.firstKey();
 }
 
 static void push(long k) {
  if(record.containsKey(k)) {
   record.put(k, record.get(k)+1);
  } else {
   record.put(k, 1);
  }
 }
 
 static void pop(long k) {
  int current = record.get(k);
  if(current == 1)
   record.remove(k);
  else
   record.put(k, current-1);
 }
 
 public static void main(String[] args) throws Exception {
  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
  String[] line = in.readLine().split(" ");
  int N = Integer.parseInt(line[0]);
  int K = Integer.parseInt(line[1]);
  int[] cows = new int[N+2];
  long total = 0;
  for(int i = 0; i < N; i++) {
   cows[i+1] = Integer.parseInt(in.readLine());
   total += cows[i+1];
  }
  long[] minimum = new long[N+2]; // minimum value of skipped cows if [i] is skipped
  push(0);
  for(int i = 1; i <= N+1; i++) {
   if(i-K-2 >= 0)
    pop(minimum[i-K-2]);
   long m = least();
   minimum[i] = m + cows[i];
   push(minimum[i]);
  }
  System.out.println(total - minimum[N+1]);
 }

}