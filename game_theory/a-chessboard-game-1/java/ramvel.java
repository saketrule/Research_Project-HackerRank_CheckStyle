import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
   
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
        
        for(int i=0;i<n;i++)
        {
        int a1=sc.nextInt();
        int a2=sc.nextInt();            
        System.out.println(findWinner(a1,a2));    
        }
    
    
    }
    
    public static String findWinner(int x, int y){
    x=x%4; 
    y=y%4;
    if((y==0)||(y==3)||(x==0)||(x==3)) return "First";
    return "Second";
}    
    
}