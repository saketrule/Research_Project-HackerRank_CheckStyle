import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
//Day 2: Misere Nim
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int[] s = new int[100];
        int i,j,n, max;
        
        int nimsum;
        for(int tt =0;tt<t;tt++){
            n = in.nextInt();
            max = 0;
            for(i = 0;i<n;i++){
                s[i]=in.nextInt();
                max = Math.max(s[i], max);
            }
            
            nimsum = s[0];
            for(i = 1;i<n;i++){
                nimsum^=s[i];
            }
            
            if (max==1 && nimsum == 1 || max>1 && nimsum==0) System.out.println("Second");
            else System.out.println("First");
        }
    }
}