import java.util.PriorityQueue;
import java.util.Scanner;


public class Solution {
 static class Pair implements Comparable<Pair>{
  int index;
  long val;
  @Override
  public int compareTo(Pair rhs) {
   if(val > rhs.val) return -1;
   if(val < rhs.val) return 1;
   return 0;
  }
  public Pair(int i, long v){
   val = v;
   index = i;
  }
 }
 
 
 public static void main(String[] args){
  Scanner sc = new Scanner(System.in);
  int N = sc.nextInt();
  int K = sc.nextInt();
  int[] vals = new int[N];
  for(int i=0; i<N; i++){
   vals[i] = sc.nextInt();
  }
  
  PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
  pq.add(new Pair(N+1,0));
  pq.add(new Pair(N,-vals[N-1]));
  long sum = 0;
  long best = 0;
  for(int i=N-1; i>=0; i--){
   sum += vals[i];
   while(pq.peek().index - i > K + 1){
    pq.remove();
   }
   long cur = pq.peek().val;
   best = cur+sum;
   if(i>0){
    pq.add(new Pair(i, cur-vals[i-1]));
   }
  }
  System.out.println(best);
 }


}