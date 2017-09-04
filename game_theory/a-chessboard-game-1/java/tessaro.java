import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int tCases = scan.nextInt();
        for (int idx=0;idx<tCases;idx++) {
            int new_x=scan.nextInt();
            int new_y=scan.nextInt();
            System.out.println(whoWins(new_x, new_y));
        }
        scan.close();
    }
    
    private static String whoWins(int x, int y) {
        x=x%4; 
        y=y%4;
        if((y==0)||(y==3)||(x==0)||(x==3)) return "First";
        return "Second";
    }
}