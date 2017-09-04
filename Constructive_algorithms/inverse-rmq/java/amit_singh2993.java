import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution  {

 @SuppressWarnings("unchecked")
 public static void main(String[] args) throws IOException {
  
  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     int n=Integer.parseInt(br.readLine());
     int size=2*n-1;
     int arr[]=new int[size];
     int height=(int)(Math.log(size+1)/Math.log(2));
     TreeMap<Integer, Integer> map=new TreeMap<Integer,Integer>();
     PrintWriter printWriter=new PrintWriter(System.out);
     //int hashTable[]=new int[Integer.MAX_VALUE-100000000];
     String strr=br.readLine();
     String input[]=strr.split(" ");
     for(int i=0;i<size;i++)
     {
      arr[i]=Integer.parseInt(input[i]);
      if(map.containsKey(arr[i]))
      {
       map.put(arr[i],((Integer)map.get(arr[i]))+1);
      }
      else
       map.put(arr[i], 1);
     }
     
     TreeMap<Integer,Integer> secondaryMap=new TreeMap<Integer,Integer>();
      
     for(Map.Entry<Integer, Integer> entry:map.entrySet())
     {
      if(((Integer)entry.getValue())>0)
      {
       secondaryMap.put(entry.getKey(), ((Integer)entry.getValue()));
      }
     }
     
     if(map.size()!=n)
      printWriter.println("NO");
     else if(((Integer)secondaryMap.get(secondaryMap.firstKey()))!=(height))
      printWriter.println("NO");
     else
     {
      int sortedArray[]=new int[size];
      int root=-1;
      boolean flag=true;
      int parent=0;
      for(;;)
      {
       if(secondaryMap.size()==0)
        break;
       
       Entry<Integer, Integer> entryPoint=null;
       for(int i=root+1;i<size;i++)
       {
        if(sortedArray[i]==0)
         {
          root=i;
          break;
         }
       }
       height=1;
       int temp=root;
       while(true)
       {
        temp=2*temp+2;
        if(temp<=size)
         height++;
        else 
         break;
       }
       for(Map.Entry<Integer, Integer> entry2:secondaryMap.entrySet())
       {
        if(((Integer)entry2.getValue())==height)
         {
         parent=(root-1)/2;
         {
          if(root==0 || parent>=0 && sortedArray[parent]<entry2.getKey())
          {
           entryPoint=entry2;
           break;
          }
         }
         }
       }
       if(entryPoint!=null)
       {
         root=fillSortedArray(root, entryPoint.getKey(), entryPoint.getValue(), sortedArray);
         secondaryMap.remove(entryPoint.getKey());
       }
       else
       {
        flag=false;
        break;
       }
       
      }
      
      if(flag)
      {
      printWriter.println("YES");
      Iterator itr=map.entrySet().iterator();
      for(int i=0;i<n;i++)
      {
       if(sortedArray[size-n+i]==0 && itr.hasNext())
       {
        Entry<Integer, Integer> thisEntry = (Entry) itr.next();
        sortedArray[size-n+i]=(Integer)thisEntry.getKey();
       }
      }
      StringBuilder str=new StringBuilder();
      for(int i=0;i<size;i++)
      {
       str.append(sortedArray[i]);
       if(i+1<size)
        str.append(" ");
      }
       printWriter.println(str);
       printWriter.flush();
      }else if(!flag)
       printWriter.println("NO");
     }
     printWriter.flush();
 }

 private static int fillSortedArray(int root,int val,int count,int arr[])
 {
  if(count<=0)
   return 0;
  else
  {
   fillSortedArray(2*root+1, val, count-1,arr);
   arr[root]=val;
   return root;
  }
 }

}