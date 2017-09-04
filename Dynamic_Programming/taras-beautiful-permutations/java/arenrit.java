import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for(int i =1; i<=q; i++){
            int N = sc.nextInt();
            long[] arr = new long[N];
            for(int j = 0 ; j<N; j++){
                arr[j] = sc.nextLong();
            }
            Arrays.sort(arr);
            System.out.println(solve(arr));
        }
    }
    
    public static long solve(long[] arr){
        long[] p = new long[arr.length];
        p[0] = 1;
        for(int i = 1; i<arr.length; i++){
            if(p[i-1]==0) p[i] = 1;
            else{
                if(arr[i] ==arr[i-1]) p[i] = (p[i-1]%1000000007)*((i-1)%1000000007);
                else p[i] = (p[i-1]%1000000007)*((i+1)%1000000007);
            }
        }
        return p[arr.length-1];
    }
}