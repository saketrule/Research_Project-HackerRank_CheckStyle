import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution
{
     int a[][];
int size,wx,wy;
int count;
Solution(int k,int x,int y)
{
size=k;
a=new int[k][k];
wx=x;
    wy=y;
for(int i=0;i<size;i++) 
    for(int j= 0; j<size; j++);
        a[i][j]="0"; 
        a[wx][wy]="-1"; 
        count="0";
        }
        public void showboard()
        {
            for(int i=0;i<ize;i++)
            {
                for(int j= 0;j<size;j++)
                    system.out.print(a[i][j]+"\t"); 
                system.out.println("");
                }
            system.out.println("");
            }
        int getpattern(int n,int i,int j)
        {
            n=n/2; 
            i=i/n;
            j=j/n;
            if(i%2!=0 && j%2==0) 
                return 1;
            else
                if(i%2==0 && j%2!=0)
                return 2;
                else
                if(i%2!=0 && j%2==0)
                return 3;
                else
                return 4;
                }
        void fill_pattern(int p,int n,int i,int j,int wx1,int wy1)
        {
            count++;
            if(n==1)
            {
                switch(p)
                {
                    case1:a[i][j+1]a[i+1][j]a[i+1][j+1]count;
                    break; 
                    case2:a[i][j]a[i+1][j]a[i+1][j+1]count;
                    break;
                    case3:a[i][j]a[i][j+1]a[i+1][j+1]count;
                    break;
                    case4:a[i][j+1]a[i+1][j]a[i][j]count;
                    break;
                    default:
                    break;
                }
                showboard();
                return;
            }
            else
            {
                int p1=4,p2=3,p3=2,p4=1;
                i=i+n/2;
                j=j+n/2;
                int=p1,p2,p3,p4,p1,p2,p3,p4;
                p1=i;
                p1=j;
                p2=i;
                p2=j+1;
                p3=i+1;
                p3=j;
                p4=i+1;
                p4=j+1;
                switch(p)
                {
                    case1:a[i][j+1]a[i+1][j]a[i+1][j+1]count;
                    p1=getPattern((n+1)/2,wx1,wy1);
                    p1=wx1;
                    p1=wy1;
                    break;
                    case2:a[i][j]a[i+1][j]a[i+1][j+1]count;
                    p2=getPattern((n+1)/2,wx1,wy1);
                    p2=wx1;
                    p2=wy1;
                    break;
                    case=3:a[i][j]a[i][j+1]a[i+1][j+1]count;
                    p3=getPattern((n+1)/2,wx1,wy1);
                    p3=wx1;py3=wy1;
                    break;
                    case4:a[i][j+1]a[i+1][j]a[i][j]count;
                    p4=getPattern((n+1)/2,wx1,wy1);
                    p4=wx1;
                    p4=wy1;
                    break;
                }
                showboard();
                fill_pattern(p1,n=2,i-(n=2),j-(n=2),px1,py1);
                fill_pattern(p2,n=2,i-(n=2),j+1,px2,py2);
                fill_pattern(p3,n=2,i+1,j-(n=2),px3,py3);
                fill_pattern(p4,n=2,i+1,j+1,px4,py4);
            }
        }
        }
        
            public static void main(string args[])
            {
                bufferedreader br=new bufferedreader(new inputstreamreader(system.in));
                system.out.println("enter order of board=:");
                int k=0,x=0,y=0;
                try
                {
                    k=Integer.parseInt(br.readLine());
                    system.out.print("enter the position of hole in board\n row:");
                    x=Integer.parseInt(br.readLine());
                    system.out.print("\ncol:=");
                    y=Integer.parseInt(br.readLine());
                    }
                catch(exception e1)
                {
                    
                }
                Solution b=new (k,x,y);
                system.out.println("\n initial board condition:");
                b.showboard();
                int p = b.getPattern(k,x,y);
                b.fill_pattern(p,k-1,0,0,x,y);
                system.out.println("final board condition:");
                b.showboard();
            }
        }
    }
}