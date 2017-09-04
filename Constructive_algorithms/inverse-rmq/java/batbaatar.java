import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        TreeMap<Integer, Integer> intMap = new TreeMap<Integer, Integer>();
        TreeMap<Integer, SortedSet<Integer>> countMap = new TreeMap<Integer, SortedSet<Integer>>(new DescComp());
        
        for(int i = 0 ; i < 2 * n - 1; i++)
        {
         int k = sc.nextInt();
         
         if(!intMap.containsKey(k))
         {
          intMap.put(k, 1);
         }
         else
         {
          intMap.put(k, intMap.get(k) + 1);
         }
        }
        
        sc.close();
        
        if(intMap.size() != n)
        {
         System.out.println("NO");
         return;
        }
        
        for(Integer key : intMap.keySet())
        {
         int count = intMap.get(key);
         
         if(!countMap.containsKey(count))
         {
          countMap.put(count, new TreeSet<Integer>());
         }
         
         countMap.get(count).add(key);
        }        
        
        int[] a = new int[2 * n - 1];
        Arrays.fill(a, Integer.MAX_VALUE);
        
        for(int i = n - 1; i < 2 * n - 1; i++)
        {
         int height = countEmpty(a, i);
         
         if(!countMap.containsKey(height))
         {
             System.out.println("NO");
             return;
         }
         else
         {
          int fillVal = countMap.get(height).first();
          countMap.get(height).remove(fillVal);
          
          if(countMap.get(height).size() == 0)
          {
           countMap.remove(height);
          }
          
          fillEmpty(a, i, fillVal);
         }
        }
        
        System.out.println("YES");
        
        for(int i = 0; i < 2 * n - 1; i++)
        {
         System.out.print(a[i] + " ");
        }
    }
    
    public static int countEmpty(int[] a, int x)
    {
     int ans = 0;
     while(x >= 0 && a[x] == Integer.MAX_VALUE)
     {
      ans++;
      if(x % 2 == 0)
      {
       x = (x - 2) / 2;
      }
      else
      {
       x = x / 2;
      }
     }
     
     return ans;
    }
    
    public static void fillEmpty(int[] a, int x, int val)
    {
     while(x >= 0 && a[x] == Integer.MAX_VALUE)
     {
      a[x] = val;
      
      if(x % 2 == 0)
      {
       x = (x - 2) / 2;
      }
      else
      {
       x = x / 2;
      }
     }
    }
}

class DescComp implements Comparator<Integer> {
 @Override
 public int compare(Integer arg0, Integer arg1){
  return arg1 - arg0;
 }
}