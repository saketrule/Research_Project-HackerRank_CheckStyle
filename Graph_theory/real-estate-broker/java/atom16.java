import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        //ArrayList<Integer[]> clients=new ArrayList<Integer[]>();
        int[] client_a=new int[n];
        int[] broker_a=new int[m];
        int[] client_p=new int[n];
        int[] broker_p=new int[m];
        
        for(int i=0;i<n;i++)
            {
            client_a[i]=sc.nextInt();
            client_p[i]=sc.nextInt();
        }
        
        for(int i=0;i<m;i++)
        {
            broker_a[i]=sc.nextInt();
            broker_p[i]=sc.nextInt();
        }
        
        //int[] canbuy=new int[n];
        Hashtable<Integer,ArrayList<String>> canbuy=new Hashtable();
        for(int i=0;i<n;i++)
            {
            ArrayList<String> t=new ArrayList<String>();
            for(int j=0;j<m;j++)
                {
                //if(!canbuy.contains(i))
                  //  {
                if(broker_a[j]>=client_a[i]&&broker_p[j]<client_p[i])
                {
                    String s="";
                    s+=j;
                    t.add(s);
                    //canbuy.put(i,t.add(j));
                }
                    
            //    }
                canbuy.put(i,t);
            
            }
        }
        
        Hashtable<String,Integer> rev=new Hashtable<>();
        
        
        
       /* Enumeration names = canbuy.keys();
   while(names.hasMoreElements()) {
      int key =(Integer) names.nextElement();
     System.out.print("\n"+key+" : ");//+ " & Value: " +
       ArrayList l=canbuy.get(key);
       for(int i=0;i<l.size();i++)
           System.out.print(l.get(i)+" ");
   }*/
        
        for(int i=0;i<m;i++)
            {
            String s="";
            s+=i;
            int flag=0,j=0;
            for(j=0;j<n;j++)
                {
                if(canbuy.get(j).contains(s))
                    {
                    flag=1;
                    break;
                }
            }
            
            if(flag==1)
                rev.put(s,j);
            else
                rev.put(s,-1);
        }
        int count=0;
    Enumeration f = rev.keys();
   while(f.hasMoreElements()) {
      String key =(String) f.nextElement();
     
       if(rev.get(key)>=0)
           count++;
       
       //System.out.print("\n"+key+" : ");//+ " & Value: " +
     //  ArrayList l=rev.get(key);
       //for(int i=0;i<l.size();i++)
         //  System.out.print(l.get(i)+" ");
   }    
        
        System.out.println(count);
        
        
        
    }
}