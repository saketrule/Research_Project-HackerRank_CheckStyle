import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int n;
        Scanner s1=new Scanner(System.in);
        n=s1.nextInt();
        int len=(2*n)-1;
        int[] arr=new int[n];
        int[] brr=new int[len];
        for(int i=0;i<len;i++)
        {
          brr[i]=s1.nextInt();
        }
        
        Arrays.sort(brr);
int t=0,k,i,j;

     int[] crr=new int[len];   

        for(i=0;i<len;i++)
t+=brr[i];




for(i=0,k=0,j=i+1;j<len;j++)
{
if(t-brr[i]==t-brr[j])
{
continue;
}
else
{
 

crr[k]=brr[i];
k++;
i=j;
}
}
if(i==(len-1)&&j==(len))
{

  crr[k]=brr[len-1];
  k++;
}

if(k==n)
{
  int[] occur=new int[k];
  double h=Math.log(n)/(Math.log(2));
  int h1=(int)h;
  h1=h1+1;
 for(i=0;i<k;i++)
 {
  for(j=0;j<len;j++)
  {
    if(crr[i]==brr[j])
    {
      occur[i]++;
    }
  }
 }
 int[] occur1=new int[k];
 for(i=0;i<k;i++)
 {
  occur1[i]=occur[i];
 }
int o=0;
for(i=0;i<h1;i++)
 {
   for(j=0;j<k;j++)
   {
     if(occur1[j]==(h1-i))
     {
      o++;
      occur1[j]--;
     }
   }
 }
if(o==len)
{
  System.out.println("YES");
 for(i=0;i<h1;i++)
 {
   for(j=0;j<k;j++)
   {
     if(occur[j]==(h1-i))
     {
      System.out.print(crr[j]+" ");
      occur[j]--;
     }
   }
 }
}
else
{
  System.out.println("NO");
}

}
else
{
  System.out.println("NO");
}
    }
}