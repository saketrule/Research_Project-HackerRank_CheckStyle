import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int t;
        Scanner sc = new Scanner(System.in);
        t=sc.nextInt();
        while(t-->0){
            int n=sc.nextInt();
            int m=sc.nextInt();
            int ans=0;
            if(m==0 || m==1 || n%2==0){
                ans=2;
            }else{
                ans=1;
            }
            System.out.println(ans);   
        }
    }
}