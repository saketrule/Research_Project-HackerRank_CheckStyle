import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan =new Scanner(System.in);
        int t = scan.nextInt();
        for(int z=0;z<t;z++){
            int x = scan.nextInt();
            int y = scan.nextInt();
            String ans = findWinner(x,y);
            System.out.println(ans);
        }
    }
    public static String findWinner(int x, int y){
    x=x%4; 
    y=y%4;
    if((y==0)||(y==3)||(x==0)||(x==3)) return "First";
    return "Second";
} 
}