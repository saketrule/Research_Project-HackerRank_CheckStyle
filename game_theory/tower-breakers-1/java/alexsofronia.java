import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
//Day 1: Tower Breakers
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int i,j,n,m;
        for(int tt =0;tt<t;tt++){
            n = in.nextInt();
            m = in.nextInt();
            if(m==1) System.out.println("2");
            else if(n%2==1) System.out.println("1");//m>1, n>2
            else System.out.println("2");//m>1, n>2
        }
    }
}