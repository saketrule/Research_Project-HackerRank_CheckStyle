import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
       // Scanner sc = new Scanner(new FileInputStream("src/chessboard.txt"));
  Scanner sc = new Scanner(System.in);
  int T =  sc.nextInt();
  
  
  for(int t = 1;t<T+1;t++)
  {
   int X ,Y;
   X=sc.nextInt();
   Y=sc.nextInt();
   
   
   System.out.println(""+findWinner(X,Y));
   
   
   
   
    }
}//main
    
     public static String findWinner(int x, int y){
     x=x%4; 
     y=y%4;
     if((y==0)||(y==3)||(x==0)||(x==3)) return "First";
     return "Second";
 }    
 
 }