import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        String s="";
        for(int i=1;i<=t;i++){
            int n=scan.nextInt();
            int m=scan.nextInt();
            if(m==1||n%2==0)
                s=s+"2\n";
            else
                s=s+"1\n";
        }
        System.out.println(s);
    }
}