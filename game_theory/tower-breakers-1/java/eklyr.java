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
        for(int i=0;i<t;i++){
            int n=sc.nextInt();
            int m=sc.nextInt();
            //String winner=(m+((n%2)+1))%2==0 ? "1" : "2";
           // winner=m==1 ? "2" : winner;
            String winner=(m==1||n%2==0) ? "2" : "1";
            System.out.println(winner);
        }
    }
}