import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static boolean[] sol;
    
    private static boolean firstwin(int tot) {
        switch(tot) {
            case 1: return false;
            case 2: return true;
            case 3: return true;
            case 4: return true;
            case 5: return true;
            default: return (!sol[tot-2-1] || !sol[tot-3-1] || !sol[tot-5-1]);
            
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner key=new Scanner(System.in);
        int t=key.nextInt();
        int[] caso=new int[t];
        for(int i=0; i<t; i++) {
            caso[i]=key.nextInt();
        }
        int max=0;
        for(int x:caso) {
            if(x>max) {max=x;}
        }
        sol=new boolean[max+1];
        for(int i=1; i<=max; i++) {
            sol[i-1]=firstwin(i);
        }
        for(int i=0; i<t; i++) {
            if(sol[caso[i]-1]) {System.out.println("First");}
            else {System.out.println("Second");}
        }
    }
}