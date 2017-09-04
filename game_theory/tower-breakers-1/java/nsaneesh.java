import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
       Scanner s=new Scanner(System.in);
       int no=s.nextInt();
       int t=0;
       while(t<no)
       {
           int a=s.nextInt();
           int b=s.nextInt();
           if(b==1 || (a%2)==0)
           {
               System.out.println(2);
           }
           else
           {
               System.out.println(1);
           }
           t++;
       }
    }
}