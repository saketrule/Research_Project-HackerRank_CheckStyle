import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m =  in.nextInt();
        int a[] = new int[n];
        int p[] = new int[n];
        int x[] = new int[m];
        int y[] = new int[m];
        boolean sold[] = new boolean[m];
        for(int i=0; i<n; i++){
            a[i] =  in.nextInt();
            p[i] =  in.nextInt();
        }
        for(int i=0; i<m; i++){
            x[i] =  in.nextInt();
            y[i] =  in.nextInt();
            sold[i] = false;
        }
        int count = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(x[j]>a[i] && y[j]<= p[i] && !sold[j]){
                    count++;
                    sold[j] = true;
                }
            }
        }
        System.out.println(count);
    }
}