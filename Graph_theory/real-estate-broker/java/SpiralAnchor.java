import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        int n=s.nextInt();
        int m=s.nextInt();
        int clients[]= new int[n];
        int houses[]= new int[m];
        int a[]=new int[n];
        int p[] =new int[n];
        int x[]= new int[m];
        int y[]=new int[m];
        boolean interested[]= new boolean[m];
        
        for(int i=0;i<n;i++){
            a[i]=s.nextInt();
            p[i]=s.nextInt();
        }
        
        for(int i=0;i<m;i++){
            x[i]=s.nextInt();
            y[i]=s.nextInt();
        }
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(a[i] < x[j] && p[i] >= y[j])
                    interested[j]=true;
            }
        }
        
        int count=0;
        for(int i=0;i<m;i++)
            if(interested[i] == true)
                count++;
        System.out.println(count);
    }
}