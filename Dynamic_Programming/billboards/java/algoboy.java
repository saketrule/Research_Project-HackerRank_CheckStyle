import java.io.*;
import java.util.*;

public class Solution {

 PrintWriter out;

 class Pair implements Comparable<Pair>{
  int index;
  long value;

  public Pair(int index,long value) {
   this.index = index;
   this.value = value;
  }

  @Override
  public int compareTo(Pair o) {
   // TODO Auto-generated method stub
   if(o.value < value) return -1;
   if(o.value > value) return +1;
   return 0;
  }
 }

 public Solution() throws Exception {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  out = new PrintWriter(new OutputStreamWriter(System.out));
  Queue<Pair> pq = new PriorityQueue<Solution.Pair>();
  String []temp = br.readLine().trim().split(" ");
  int N = Integer.parseInt(temp[0]);
  int K = Integer.parseInt(temp[1]);
  long []nums = new long[N + 1];
  long []sums = new long[N + 1];
  long []dp = new long[N + 1];
  
  pq.add(new Pair(0,0));
  for(int i=1;i<=N;i++) {      
   nums[i] = Integer.parseInt(br.readLine());
   sums[i] = nums[i] + sums[i-1];         
  
   
   Pair top = pq.peek();
   
   dp[i] = top.value + sums[i];
   
   pq.add(new Pair(i, dp[i-1] - sums[i]));
   while(i - pq.peek().index >= K) {
    Pair p = pq.remove();
    // System.out.println("R " + p.index + " " + p.value);
   
   }
  }

  out.println(dp[N]);
  out.flush();
 }

 public static void main(String []args) throws Exception {
  new Solution();
 }
}