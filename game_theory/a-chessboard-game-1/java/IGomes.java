import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int t = in.nextInt();
  while (t-- > 0) {
   int x=in.nextInt()%4; 
   int y=in.nextInt()%4; 
   System.out.println((y==0)||(y==3)||(x==0)||(x==3) ? "First" : "Second" );
  }
  in.close();
 }
}