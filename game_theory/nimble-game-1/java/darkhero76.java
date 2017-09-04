import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan =new Scanner(System.in);
        int t = scan.nextInt();
        for(int z=0;z<t;z++){
            int n = scan.nextInt();
            int[] ar = new int[n];
            for(int i=0;i<n;i++){
                ar[i] = scan.nextInt();
            }
            int s = ar[0];
            int c = 1;
            int b  = 0;
            for(int i=1;i<n;i++){
                if(ar[i]>0){
                    if(ar[i]%2 == 0){
                        c = 0;
                    }else{
                        c = i;
                    }
                    b = b ^ c;
                }
            }
            if(b==0){
                System.out.println("Second");
            }else{
                System.out.println("First");
            }
        }
    }
}