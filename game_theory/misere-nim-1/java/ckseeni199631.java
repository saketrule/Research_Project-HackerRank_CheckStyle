import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            long[] p = new long[n];
   long s = 0,c=0;
            for(int p_i=0; p_i < n; p_i++){
                p[p_i] = in.nextLong();
    s = s ^ p[p_i];
    if(p[p_i]==1)
     c++;
            }
   if((c==n&&s==1)||(c<n&&s==0))
    System.out.println("Second");
   else
    System.out.println("First");
            // your code goes here
        }
    }
}