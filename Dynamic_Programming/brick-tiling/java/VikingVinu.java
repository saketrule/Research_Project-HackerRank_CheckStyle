import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int T,N,M,i=0,j,k;
        T=sc.nextInt();
        while(i<T){
            N=sc.nextInt();
            M=sc.nextInt();
            for(j=0;j<N;j++)
                sc.next();
            if(N>=3 || M>=3)
                System.out.println(N/3*2+M/3*2);
            else
                System.out.println(1);
            i++;
        }
    }
}