import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
  int t= scan.nextInt();
  int x,y;
  int count=0;
  while(t>0) {
   x=scan.nextInt();
   count=0;
   int[] arr = new int[x];
   for(int i=0;i<x;i++) {
    arr[i] = scan.nextInt();
                count ^=arr[i];
   }
   System.out.println(count!=0?"First":"Second");
   t--;
  }
    }
}