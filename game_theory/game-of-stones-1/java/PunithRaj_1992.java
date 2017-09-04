import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        while(T-->0)
            {
  int n=sc.nextInt();
  if((n==2)||(n==3)||(n==5))
   System.out.println("First");
            else if(n==1)
   System.out.println("Second");
  else
  {
   //int count=0; 
   int m=n;

    /*while(n!=0)
   {
    int x=n-5;
    if(x<0)
    {
     int y=n-3;
     if(y<0)
     {
      int z=n-2;
      if(z<0)
      {
                            
       //System.out.println("second");
       break;

      }
      else
       if(z==0)
       {
        count++;
        //System.out.println("first");
        break;
       }
     }
     else if(y==0)
     {
      count++;
      //System.out.println("first");
      break;
     }
     else
     {
      n=y;
      count++;
     }

    }
    else if(x==0)
    {

     count++;
     //System.out.println("first");
     break;
    }
    else if((x==10)||(x==5)){
      if((m%10)==0)
      {
     n=n-2;
     count++;
      }
      else if((m%5)==0)
      {
       n=n-3;
       count++;
      }
      else
     {
      n=x;
      count++;
     }
    }
    else if((x==9))
    {
     n=n-2;
     count++;
    }
    else if(x==11)
    {
     n=n-3;
     count++;
    }
    else if(x==12)
    {
     n=n-3;
     count++;
    }
     else
     {
      n=x;
      count++;
     }
   }
   
            if((count%2)==0)
            {
            
          System.out.println("Second");
      }
            else
            {
             System.out.println("First");
            }*/
                
     int k=n/7;
   if((m==(k*7))||(m==((k*7)+1)))
    {
    System.out.println("Second");
    }
   else 
   {
    System.out.println("First");
   }
     }
        }
        }

}