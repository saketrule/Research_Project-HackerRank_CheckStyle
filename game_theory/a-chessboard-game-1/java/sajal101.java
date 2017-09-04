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
  while(t>0) {
   x=scan.nextInt();
   y=scan.nextInt();
   x %=4;
   y %=4;
   
   System.out.println(x==0||x==3||y==0||y==3?"First":"Second");
   t--;
  }
    }
}