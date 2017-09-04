import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException
    {
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(s.nextToken());
        int m=Integer.parseInt(s.nextToken());
        int c[][]=new int[n][2];
        int b[][]=new int [m][2];
        for(int i=0;i<n;i++)
            {
              StringTokenizer st=new StringTokenizer(br.readLine());
            c[i][0]=Integer.parseInt(st.nextToken());
            c[i][1]=Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<m;i++)
            {
              StringTokenizer sm=new StringTokenizer(br.readLine());
            b[i][0]=Integer.parseInt(sm.nextToken());
            b[i][1]=Integer.parseInt(sm.nextToken());
        }int h=m;
        for(int i=0;i<m;i++)
            {int fl=0;
            for(int j=0;j<n;j++)
                {
                if(b[i][0]>c[j][0]&&b[i][1]<=c[j][1])
                    {
                    fl=1;break;
                }
            
            
            }
       
            if(fl==0)
                {
                h--;
            }
            }
        
        
        System.out.println(h);
        
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    
    
    
    
    
    }
}