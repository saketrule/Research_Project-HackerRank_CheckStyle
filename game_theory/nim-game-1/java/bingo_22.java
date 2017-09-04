import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        for(int i=0;i<t;i++){
            int n=in.nextInt();
            int s[]=new int[n];
            for(int j=0;j<n;j++)
                s[j]=in.nextInt();
            int x=0;
            for(int j=0;j<n;j++)
                x=x^s[j];
            if(x>0)
                System.out.println("First");
            else
                System.out.println("Second");
        }
    }
}