import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int []a = new int[101];
        int i,n;
        Arrays.fill(a,2);
        for (i=2; i<=6;i++) a[i]=1;
        for(i=7;i<101;i++){
            if(a[i-2]!=1 || a[i-3]!=1 || a[i-5]!=1) a[i]=1;
        }
            
        for (int tt=0;tt<t;tt++){
            n = in.nextInt();
            if (a[n]==2) System.out.println("Second");
            else System.out.println("First");
        }
    }
}