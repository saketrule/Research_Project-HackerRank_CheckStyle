import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        while(sc.hasNextInt()) 
            System.out.println(findWinner(sc.nextInt(),sc.nextInt()));
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
    public static String findWinner(int x, int y){
    x=x%4; 
    y=y%4;
    if((y==0)||(y==3)||(x==0)||(x==3)) return "First";
    return "Second";
} 
}