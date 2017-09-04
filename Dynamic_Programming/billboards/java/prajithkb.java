/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.PriorityQueue;

public class Solution {
 
 
 static class Profit implements Comparable<Profit>{
  int index ;
  long value;
  
  Profit(long value, int index){
   this.value = value;
   this.index = index;
  }
  @Override
  public int compareTo(Profit o) {
   if(value == o.value){
    return index - o.index;
   }
   if( value - o.value  > 0)
    return 1;
   else
    return -1;
  }
  
  public String toString(){
   return String.valueOf(value);
  }
  
  
 }
 static long maxProfitWithLuckOne(long input[], int k){
  long[] dp = new long[input.length];
  int[] count = new int[input.length];
  dp[0] = input[0];
  count[0] = 1;
  long sum = input[0];
  for (int i = 1; i < k; i++) {
   sum += input[i];
   dp[i] =  input[i] + dp[i-1];
   count[i] = count[i-1] + 1;
  }
  for (int i = k ; i < input.length; i++) {
   int h = k-1;
   sum -=input[i-k];
   sum += input[i];
   long intermediateSum = 0;
   long actual = 0;
   h = k - 1;
   int c = 0;
   while(h >= 0){
    actual = 0;
    actual = sum -intermediateSum;
    if(i-h-2 >=0)
     actual += dp[i-h-2];
    c = k-h;
    if(actual > dp[i]){
     dp[i] = actual;
     count[i] = c;
    }
    intermediateSum += input[i-h];
    h--;
   }
  }
//  System.out.println();
//  for (int i = 0; i < dp.length; i++) {
//   System.out.print(dp[i] + ", ");
//  }
//  System.out.println();
//  for (int i = 0; i < count.length; i++) {
//   System.out.print(count[i] + ", ");
//  }
//  System.out.println();
  return dp[input.length-1];
 }

 static long maxProfitWithLuck(long input[], int k){
  long[] dp = new long[input.length];
  PriorityQueue<Profit> minHeap = new PriorityQueue<Profit>();
  int[] count = new int[input.length];
  dp[0] = input[0];
  count[0] = 1;
  long sum = input[0];
  minHeap.add(new Profit(input[0], 0));
  for (int i = 1; i < k; i++) {
   minHeap.add(new Profit(input[i], i));
   sum += input[i];
   dp[i] =  input[i] + dp[i-1];
   count[i] = count[i-1] + 1;
  }
  for (int i = k ; i < input.length; i++) {
   minHeap.add(new Profit(input[i], i));
   Profit p = minHeap.peek();
   while (p.index <= i-k ){
    minHeap.remove();
    p = minHeap.peek();
   }
   if(count[i-1] < k){
    dp[i] = input[i] + dp[i-1];
    count[i] = count[i-1] + 1;
    continue;
   }
   int h = 0;
   sum -=input[i-k];
   sum += input[i];
//   long intermediateSum = 0;
//   long intermediateSum = 0;
   long actual = 0;
   int c = 0;
   if(i-p.index == k-1){
    actual = sum ;
    h = p.index ;
   }
   else{
    h = p.index + 1;
    int l = h;
    while(l <=i){
     actual += input[l];
     l++;
    }
   }
   //while(h >= 0){
  //  actual = 0;
  //  actual = sum -intermediateSum;
   if(actual == 0)
    c = 0;
   else
    c = i-p.index + 1;
    if(h-2 >=0)
     actual += dp[h -2];
    dp[i] = actual;
    count[i] = c;
//    if(actual > dp[i]){
//     dp[i] = actual;
//     count[i] = c;
//    }
//    intermediateSum += input[i-h];
   //}
  }
//  System.out.println();
//  for (int i = 0; i < dp.length; i++) {
//   System.out.print(dp[i] + ", ");
//  }
//  System.out.println();
//  for (int i = 0; i < count.length; i++) {
//   System.out.print(count[i] + ", ");
//  }
//  System.out.println();
  return dp[input.length-1];
 }
 static long maxsum(int n,int k,long[]  profits, int index) {
    long sum=0,max=0,cur;
    int i;
    if (n<=k) {
        for (i=0;i<n;i++) sum+=profits[i + index];
        return sum;
    }
    for (i=0;i<=k;i++) {
        cur=sum+maxsum(n-i-1,k,profits, index+i+1);
        if (cur>max) max=cur;
        sum+=profits[i+index];
    }
    return max;
}

 public static void main(String[] args) throws IOException {
//  long [] r = new long[100000];
//  Random ran = new Random();
//  for (int i = 0; i < r.length; i++) {
//   r[i] = ran.nextInt(100000) * ran.nextInt(100000);
//  }
//  int M = 9999;
  try {
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   String s = br.readLine();
   int N = Integer.parseInt(s.split(" ")[0]);
   int M = Integer.parseInt(s.split(" ")[1]);
   long[] r = new long[N];
   for (int i = 0; i < r.length; i++) {
    s = br.readLine();
    r[i] =  Long.parseLong(s);
   }
  // System.out.println(maxProfit(r, M));
   Date d = new Date();
   if(N > 50000)
    System.out.println(maxProfitWithLuck(r, M));
   else
    System.out.println(maxProfitWithLuckOne(r, M));
   Date e = new Date();
   //5 3System.out.println(e.getTime() -  d.getTime());
   //System.out.println(maxsum(N, M, r, 0));
  } catch (NumberFormatException e) {
   e.printStackTrace();
  } 

 }


}