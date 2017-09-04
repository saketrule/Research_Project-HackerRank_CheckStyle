import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static String findWinner(int x, int y){
        x=x%4; 
        y=y%4;
        if((y==0)||(y==3)||(x==0)||(x==3)) return "First";
        return "Second";
    }    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();
        for(int i=0;i<t;i++){
            int x=sc.nextInt();
            int y=sc.nextInt();
            int s= x<y ? x : y;
           
            //int round= s%2==0
            String winner= s%4==3||s%4==0? "First" : "Second";
            System.out.println(Solution.findWinner(x,y));
        }
    }
}