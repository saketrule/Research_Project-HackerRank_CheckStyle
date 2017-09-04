import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan=new Scanner(System.in);
        int t=scan.nextInt();
        for(int cas=0;cas<t;cas++){
            int n=scan.nextInt(),ans=0;
            for(int i=0;i<n;i++) ans^=scan.nextInt();
            if(ans==0) System.out.println("Second");
            else System.out.println("First");
        }
    }
}