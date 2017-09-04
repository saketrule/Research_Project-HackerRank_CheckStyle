import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String args[]) throws Exception
 {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  String len[] = br.readLine().split(" ");
  int n = Integer.parseInt(len[0]);
  int m = Integer.parseInt(len[1]);
  long a[] = new long[n+1];
  long p[] = new long[n+1];
  long x[] = new long[n+1];
  long y[] = new long[n+1];
  
  for(int i=1;i<=n;i++)
  {
   String temp[] = br.readLine().split(" ");
   a[i] = Long.parseLong(temp[0]);
   p[i] = Long.parseLong(temp[1]);
  }
  for(int i=1;i<=m;i++)
  {
   String temp[] = br.readLine().split(" ");
   x[i] = Long.parseLong(temp[0]);
   y[i] = Long.parseLong(temp[1]);
  }
  
  HashMap<Integer,ArrayList<Integer>> hm = new HashMap<Integer,ArrayList<Integer>>();
  for(int i=1;i<=n;i++)
  {
   for(int j=1;j<=m;j++)
   {
    if(a[i] < x[j] && p[i] >= y[j])
    {
     if(!hm.containsKey(i))
     {
      ArrayList<Integer> temp = new ArrayList<Integer>();
      temp.add(j);
      hm.put(i,temp);
     }
     else
     {
      ArrayList<Integer> temp = hm.get(i);
      temp.add(j);
      hm.put(i,temp);
     }
    }
   }
  }
  
  /*for(int l : hm.keySet())
  {
   ArrayList<Integer> temp = hm.get(l);
   for(int i=0;i<temp.size();i++)
   {
    System.out.print(temp.get(i) + " ");
   }
   System.out.println();
  }*/
  
  HashMap<Integer,Integer> out = new HashMap<Integer,Integer>();
  for(int l:hm.keySet())
  {
   ArrayList<Integer> temp = hm.get(l);
   for(int i=0;i<temp.size();i++)
   {
    if(!out.containsKey(temp.get(i)))
     out.put(temp.get(i),0);
   }
  }
  System.out.println(out.size());
 }
}