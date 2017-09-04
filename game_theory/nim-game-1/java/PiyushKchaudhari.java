import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0){
            int n=sc.nextInt();
            int sum=sc.nextInt();
            for(int i=1;i<n;i++){
                sum^=sc.nextInt();
            }
            if(sum==0){
                System.out.println("Second");
            }
            else{
                System.out.println("First");
            }
        }
    }
}