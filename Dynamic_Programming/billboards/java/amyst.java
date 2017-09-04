/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;



public class Solution {
 static int n,k;
 static long sum = 0;
 static ArrayList<Integer> billboard = new ArrayList<Integer>();
 static int limit;
 
 static class MinLoss{
  int index;
  long minloss;
  
  MinLoss(int i, long m){
   index = i;
   minloss = m;
  }

 }
 
 public static void main(String args[]){
  Scanner s = new Scanner(System.in);
  ArrayList<Long> dp = new ArrayList<Long>();
  n = s.nextInt();
  k = s.nextInt();
  for(int i = 0; i < n; i++){
   int crt = s.nextInt(); 
   billboard.add(crt);
   sum+=crt;
  }
  
  
  PriorityQueue<MinLoss> lastMin = new PriorityQueue<MinLoss>(n,new Comparator<MinLoss>() {

   @Override
   public int compare(MinLoss arg0, MinLoss arg1) {
    
    return (int)(arg0.minloss - arg1.minloss);
    
   }
       });
  limit = 0;
  for(int i = 0; i < k+1; i++){
   long elem = billboard.get(i);
   dp.add(elem);
  // mins.add(0);
   lastMin.add(new MinLoss(i, elem));
  }
  
  for(int i = k+1; i < n; i++){
   long crtProfit = billboard.get(i);
   long crtDp = lastMin.peek().minloss +crtProfit;
   
   dp.add(crtDp);
   limit++;
   while(lastMin.peek().index < limit){
    lastMin.poll();
   }
   lastMin.add(new MinLoss(i,crtDp));
   //lastMin.remove(dp.get(i-k-1));
   //lastMin.add(crtDp);
   
  }
  long minimdp = lastMin.peek().minloss;
  /*dp.get(n-1);
  for(int i = 0; i < k; i++){
   long crtElem = dp.get(n-2-i);
   if(minimdp > crtElem){
    minimdp = crtElem;
   }
  }*/
  System.out.println(sum-minimdp);
  
  
 }
}