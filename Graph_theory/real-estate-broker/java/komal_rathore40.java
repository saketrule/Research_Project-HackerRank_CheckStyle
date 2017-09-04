import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n  = in.nextInt();
        int m  = in.nextInt();
        long a[] = new long[n];
        long p[] = new long[n];
        long x[] = new long[m];
        long y[] = new long[m];
        int house[] = new int[m];
        for(int i=0;i<n;i++){
            a[i] = in.nextLong();
            p[i] = in.nextLong();
        }
         for(int j=0;j<m;j++){
            x[j] = in.nextLong();
            y[j] = in.nextLong();
        }
        for(int i=0;i<m;i++){
            house[i] = 0;
        }
        for(int c=0;c<n;c++){
            for(int h=0;h<m;h++){
                if(x[h] > a[c] && y[h] <= p[c]){
                    house[h]++;
                   
                }
            }
        }
        int s = 0;
        for(int g=0;g<m;g++){
            if(house[g] > 0)
                s++;
          
        }
        System.out.println(s);
    }
}