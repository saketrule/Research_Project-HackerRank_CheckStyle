import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        for(int i=0;i<t;i++)
            {
            int n=sc.nextInt();
            int a=0;
            int count=0;
            while(n>1)
                {
                a=n%7;
                if(a==0||a==1){n=n-3;count++;continue;}
                if(a>=5){n-=5;count++;continue;}
                if(a>=3){n-=3;count++;continue;}
                if(a>=2){n-=2;count++;continue;}
            }
            if(count%2==0)System.out.println("Second");
            else System.out.println("First");
        }
    }
}