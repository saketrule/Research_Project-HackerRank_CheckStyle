/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/

import java.util.LinkedList;
import java.util.Scanner;

public class Solution {
 public static void main(String[] args){
  Scanner scan = new Scanner(System.in);
  int N = scan.nextInt();
  int K = scan.nextInt();
  int[] p = new int[N+1];
  
  long[] min = new long[N+1];
  
  long total=0;
  
  LinkedList<Long> list = new LinkedList<Long>();
  list.addFirst(0L);
  
  for(int i=1;i<=N;i++){
   p[i]=scan.nextInt();
   total+=p[i];
   if(i>K+1){
    long top = list.getFirst();
    if(top==min[i-K-2]){
     list.removeFirst();
    }
   }
   
   min[i]=list.getFirst()+p[i];
   
   while(!list.isEmpty()){
    long bottom= list.getLast();
    if(min[i]<bottom){
     list.removeLast();
    }else{
     break;
    }
   }
   list.addLast(min[i]);
   
  }
  
  //Util.println(min);
  
  long maxBill=total-min[N];
 
  long temp=0;
  
  for(int i=N-1;i>=N-K;i--){
   temp=total-min[i];
   if(temp>maxBill){
    maxBill=temp;
   }
  }
  System.out.println(maxBill);

 
 }

}