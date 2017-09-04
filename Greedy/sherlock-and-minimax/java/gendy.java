import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Solution {
 public static int findMinimum(int [] numbers , int num)
 {
  int min=Integer.MAX_VALUE;
  for (int j = 0; j < numbers.length; j++) {
   if(Math.abs(num-numbers[j])<min)
   {
    min=Math.abs(num-numbers[j]);
   }
  }
  return min;
 }

 public static void main(String[] args) {
  Scanner s=new Scanner(System.in);
  int cases=Integer.parseInt(s.nextLine());
  StringTokenizer st=new StringTokenizer(s.nextLine());
  int [] num=new int[cases];
  for (int i = 0; i < cases; i++) {
   num[i]=Integer.parseInt(st.nextToken());
  }
  Arrays.sort(num);
  st=new StringTokenizer(s.nextLine());
  int p=Integer.parseInt(st.nextToken());
  int q=Integer.parseInt(st.nextToken());
  int max=findMinimum(num, p);
  int needed=p;
  int minq=findMinimum(num, q);
  if(minq>max)
  {
   max=minq;
   needed=q;
  }
  for (int i = 0; i <num.length-1; i++) {
   
    int mid=(num[i+1]+num[i])/2;
    if(mid>p && mid<q)
    {
     int min=findMinimum(num, mid);
     if(min>max)
     {
      max=min;
      needed=mid;
     }
    }
   
  }
  System.out.println(needed);
 }
 
 
}