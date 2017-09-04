import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        for(int i=0;i<t;i++){
            int n=in.nextInt();
            int s[]=new int[n];
            int count[]=new int[n];
            for(int j=0;j<n;j++){
                s[j]=in.nextInt();
                
                while(s[j]%2==0){
                    count[j]++;
                    s[j]=s[j]/2;
                }
                for(int k=3;k<=Math.sqrt(s[j]);k=k+2){
                    while(s[j]%k==0){
                        count[j]++;
                        s[j]=s[j]/k;
                    }
                }
                if(s[j]>2)
                    count[j]++;
                }
            int x=0;
            for(int j=0;j<n;j++)
                x=x^count[j];
            if(x==0)
                System.out.println("2");
            else
                System.out.println("1");
        }
    }
}