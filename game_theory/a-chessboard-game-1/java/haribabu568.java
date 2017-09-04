import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int x=in.nextInt();
        for(int i=0;i<x;i++){
            int n=in.nextInt();
            int m=in.nextInt();
            n=n%4;
            m=m%4;
            if(n==0||n==3||m==0||m==3)
                System.out.println("First");
            else
                System.out.println("Second");
        }
    }
}