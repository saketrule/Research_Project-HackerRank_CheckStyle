import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int t,i;
        Scanner sc=new Scanner(System.in);
        t=sc.nextInt();
        int x[]=new int[t];
        int y[]=new int[t];
       for(i=0;i<t;i++)
           {
           x[i]=sc.nextInt();
           y[i]=sc.nextInt();
       }
        for(i=0;i<t;i++)
            {
        if((y[i]%4==0)||(y[i]%4==3)||(x[i]%4==0)||(x[i]%4==3))
            System.out.println("First");
        else
            System.out.println("Second");
    }
    }
}