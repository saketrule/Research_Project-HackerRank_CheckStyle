import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
//Day 2: Nim Game
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int[] s = new int[100];
        int i,j,n;
        
        int nimsum;
        for(int tt =0;tt<t;tt++){
            n = in.nextInt();
            for(i = 0;i<n;i++){
                s[i]=in.nextInt();
            }
            nimsum = s[0];
            for(i = 1;i<n;i++){
                nimsum^=s[i];
            }
            
            if (nimsum > 0) System.out.println("First");
            else System.out.println("Second");
        }
    }
}