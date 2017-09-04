import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0){
            int n=sc.nextInt();int temp=n;int sum=0;
            while(n-->0){
                int val=sc.nextInt();
                sum+=val;
            }
            if(sum!=temp){
            if(sum%2==0) System.out.println("2");
                else  System.out.println("1");}
            else System.out.println("2");
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}