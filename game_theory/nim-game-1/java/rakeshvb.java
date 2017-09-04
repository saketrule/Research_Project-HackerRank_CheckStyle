import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args)throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int g=Integer.parseInt(br.readLine());
        if(g==0)
            return;
        for(int i=0;i<2*g;i=i+2)
            {
            int n=Integer.parseInt(br.readLine());
            if(n==0)
                continue;
            String s=br.readLine();
            String s_arr[]=(s.split(" "));
            int a[]=new int[s_arr.length];
            for(int k=0;k<s_arr.length;k++)
                {
                a[k]=Integer.parseInt(s_arr[k]);
            }
            int res=0;
            for (int j=0;j<n;j++)
                {
                res^=a[j];
            }
            if(res==0)
                System.out.println("Second");
            else
                System.out.println("First");
                
        }
    }
}