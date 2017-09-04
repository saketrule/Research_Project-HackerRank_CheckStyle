import java.util.*;
import java.io.*;

public class Solution{

 BufferedReader in;
 StringTokenizer str = null;
 
 private int nextInt() throws Exception{
  if (str == null || !str.hasMoreElements())
   str = new StringTokenizer(in.readLine());
  return Integer.parseInt(str.nextToken());
 }
 long []b, s;
 long [][]f;
 int n,k;
 
 public void run() throws Exception{ 
  in = new BufferedReader(new InputStreamReader(System.in));
  
  n = nextInt();
  k = nextInt();
  
  b = new long[n];
  for(int i=0;i<n;i++)
   b[i] = nextInt();
  
  s = new long[n];
  s[0] = b[0];
  for(int i=1;i<n;i++)
   s[i] = s[i-1] + b[i];
  
  TreeSet<pair> set = new TreeSet<pair>();
  
  f = new long[n][2];
  f[0][0] = 0;
  f[0][1] = b[0];
  set.add(new pair(f[0][0] - b[0], 0));
  
  for(int i=1;i<n;i++){
   f[i][0] = Math.max(f[i-1][1], f[i-1][0]);
   if (i - k >=0){
    pair p = set.first();
    f[i][1] = p.val + s[i];
    set.remove(new pair(f[i-k][0] - s[i-k], i-k));
   }else{
    f[i][1] = f[i-1][1] + b[i];
   }
   set.add(new pair(f[i][0] - s[i], i));
  }
  System.out.println(Math.max(f[n-1][0], f[n-1][1]));
 }
 
 public static void main(String args[]) throws Exception{
  new Solution().run(); 
 }
}
class pair implements Comparable<pair>{
 public long val;
 public int idx;
 public pair(long val, int idx){
  this.val = val;
  this.idx = idx;
 }
 
 public int compareTo(pair p){
  if (this.val > p.val) return -1;
  if (this.val < p.val) return 1;
  if (this.idx > p.idx) return -1;
  if (this.idx < p.idx) return 1;
  return 0;
 }
 
 @Override
 public int hashCode(){
  return this.idx;
 }
 
 @Override
 public boolean equals(Object obj){
  pair p = (pair)obj;
  return this.idx == p.idx;
 }
}