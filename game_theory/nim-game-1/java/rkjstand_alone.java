import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int t;
        Scanner sc = new Scanner(System.in);
        t=sc.nextInt();
        while(t--!=0){
            int n;
            n=sc.nextInt();
            int a[]=new int[n];
            for(int i=0;i<n;++i){
                a[i]=sc.nextInt();
            }
            int x=a[0];
            for(int i=1;i<n;++i){
              x ^=a[i];
            }
            if(x==0)
                System.out.println("Second");
            else
                System.out.println("First");
        }
    }
}