import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TowerBreakerAgain
{
    static int[] arr;
    static int[] input;
    static ArrayList<Integer> divisor(int n)
    {   ArrayList<Integer> al=new ArrayList<Integer>();
        al.add(1);
        for(int i=2;i*i<=n;i++)
        {  if(n%i==0)
            {
              al.add(i);al.add(n/i);
            }        
         }
         return al;
    }
    static int grundy(int n)
    {  if(arr[n]>-1)return arr[n];
       ArrayList<Integer> al=divisor(n);
       TreeSet<Integer> ts=new TreeSet<Integer>(); 
       for(int i=0;i<al.size();i++)
       {  
          int x=al.get(i);
          if((n/x)%2==0)ts.add(0);
          else   ts.add(grundy(x));         
       }
       int i=0;
       for(;;i++)
       {
         if(i==ts.size()||!ts.contains(i))
             break;
       }
        return arr[n]=i;        
    }
    public static void main(String[] args) 
    { Scanner scan=new Scanner(System.in);
      int t=scan.nextInt();
     arr=new int[100001];Arrays.fill(arr,-1);arr[1]=0;
      while(t--!=0)
      {  int n=scan.nextInt();
         input =new int[n];
         int xor=0;
           for(int i=0;i<n;i++)
               xor=xor^grundy(scan.nextInt());      
         if(xor==0)System.out.println(2);
          else System.out.println(1);
       }
      
    }
}