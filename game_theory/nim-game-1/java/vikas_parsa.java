import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        while(t>0){
            int n=in.nextInt();
            int count=0;
            for(int i=0;i<n;i++){
                int a=in.nextInt();
                count=count^a;
            }
            if(count==0){
                System.out.println("Second");
            }
            else System.out.println("First");
            t--;
        }
    }
}