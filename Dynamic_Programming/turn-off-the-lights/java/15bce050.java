import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt();
        
        int ar[]=new int[a];
        for(int i=0;i<a;i++)
            {
            ar[i]=sc.nextInt();
        }
        Arrays.sort(ar);
        System.out.println(ar[0]);
    }
}