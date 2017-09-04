import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
  int cases = Integer.parseInt(b.readLine());
  int[] solution = new int[cases];
  for(int i=1; i<=cases; i++){
   String str = b.readLine();
   String[] tokens = str.split(" ");
   int rows = Integer.parseInt(tokens[0]);
   int columns = Integer.parseInt(tokens[1]);
   boolean[][] a = new boolean[rows][columns];
   int count =0;
   for(int j=0; j<rows; j++){
    String line = b.readLine();
    char[] lineTokens = line.toCharArray();
    for(int k=0; k<columns; k++){
     char c;
     if((c = lineTokens[k]) == '.') {
      a[j][k] = true;
      count++;
     } 
     else {
      a[j][k] = false;
     }
    }
   } 
   int ways = 1;
   for(int k=0; k<rows; k++){
    for(int l=0; l<columns; l++) {
     if(!a[k][l]) {
      try {
       if(a[k-1][l] && a[k+1][l] && a[k][l-1] && a[k][l+1] && a[k-1][l-1] && a[k-1][l+1] && a[k+1][l-1] && a[k+1][l+1]) {
        ways = ways*4;
        count-=8;
       }
      }
      catch(Exception e) {
      }
     }
    }
   }
   int bb = count/8;
   if(bb!=0)
    ways = ways * (bb * 2);
   solution[i-1] = ways;
  }
  for(int m=1; m<=cases; m++) {
   System.out.println(solution[m-1]);
  }
    }
}