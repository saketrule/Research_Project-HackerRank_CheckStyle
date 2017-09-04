import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        int n[]=new int[t];
        int m[]=new int[t];
        for(int i=0;i<t;i++)
            {
            n[i]=sc.nextInt();
            m[i]=sc.nextInt();
            }
        for(int i=0;i<t;i++)
            {
           if(m[i]== 1 || n[i] % 2 == 0)
                System.out.println("2");
            else
                System.out.println("1");
        }
    }
}