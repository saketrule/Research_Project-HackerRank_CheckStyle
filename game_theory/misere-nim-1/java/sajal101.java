import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
  int t= scan.nextInt();
  int x;
  int count=0,c = 0;
  boolean flag = true;
  while(t>0) {
   x=scan.nextInt();
   count=0;
   flag = true;
   int[] arr = new int[x];
   for(int i=0;i<x;i++) {
    arr[i] = scan.nextInt();
    if(arr[i]>1)
     flag=false;
                count ^=arr[i];
   }
   if((flag&&count==1)||(!flag&&count==0))
    System.out.println("Second");
   else
    System.out.println("First");
    }
    }
}