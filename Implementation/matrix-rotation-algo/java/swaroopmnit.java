import java.util.*;
public class Solution {
 private static long[] rotation(long[] c, int x, long r) {
  // TODO Auto-generated method stub
  int rot=(int) r;
  if(r%x==0)
   return c;
  else if(r>x)
  {
   rot=(int) (r%x);
  }
  long rotate[]=new long[x];
  int t=0;
  for (int i=0; i <rot; i++) {
   rotate[t++]=c[i+x-rot];
  }
  for (int j = 0; j <x-rot; j++) {
   rotate[t++]=c[j];
  }
  return rotate;
 }
    private static Scanner in;
    public static void main(String[] args) {
        in = new Scanner(System.in);
     int m,n,m1,n1;
     long r;
     m1=m=in.nextInt();
     n1=n=in.nextInt();
     r=in.nextLong();
     long a[][]=new long[m][n];
     for (int i = 0; i <m; i++) {
   for (int j = 0; j <n; j++) {
    a[i][j]=in.nextInt();
   }
  }
     int loop=Math.min(m,n)/2;
     int x;
     long c[];
     for (int i = 0; i < loop; i++) {
      x=2*m+2*(n-2);
      c=new long[x];
      int corner=1;
      int t=0;
      for(int w=0;w<x;w++)
      { 
       if(corner==1)
       {
        c[w]=a[i+t++][i];
        if(t==m)
        {
         t=1;
         corner=2;
        }
       }
       else if(corner==2)
       {
        c[w]=a[m-1+i][i+t++];
        if(t==n)
        {
         t=m-2;
         corner=3;
        }
       }
       else if(corner==3)
       {
        c[w]=a[i+t--][i+n-1];
        if(t<0)
        {
         t=n-2;
         corner=4;
        }
       }
       else{
        c[w]=a[i][i+t--];
       }
      }
      c=rotation(c,x,r);
      corner=1;
      t=0;
      for(int w=0;w<x;w++)
      { 
       if(corner==1)
       {
        a[i+t++][i]=c[w];
        if(t==m)
        {
         t=1;
         corner=2;
        }
       }
       else if(corner==2)
       {
        a[m-1+i][i+t++]=c[w];
        if(t==n)
        {
         t=m-2;
         corner=3;
        }
       }
       else if(corner==3)
       {
        a[i+t--][i+n-1]=c[w];
        if(t<0)
        {
         t=n-2;
         corner=4;
        }
       }
       else{
        a[i][i+t--]=c[w];
       }
      }
      n-=2;
      m-=2;
     }
     
     for (int i = 0; i <m1; i++) {
      int f=0;
   for (int j = 0; j <n1; j++) {
    if(f==0)
    { System.out.print(a[i][j]);
     f=1;
    }
    else
     System.out.print(" "+a[i][j]);
   }
   System.out.println();
  } 
    }
 
}