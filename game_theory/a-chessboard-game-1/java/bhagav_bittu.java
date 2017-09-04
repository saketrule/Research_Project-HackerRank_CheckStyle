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
            int x=sc.nextInt();
            int y=sc.nextInt();
            x=x%4;
            y=y%4;
            if(x==0||x==3||y==0||y==3)System.out.println("First");
            else System.out.println("Second");
        }
    }
}