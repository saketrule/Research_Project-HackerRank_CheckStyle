import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
   static void buildSegmentTree(boolean [] used, List<Integer> data, int start, int end, int [] out, int pos)
   {
      if (start == end)
      {
         out[pos] = data.get(start);
         used[pos] = true;
         return;
      }
      int mid = (end + start) / 2;
      buildSegmentTree(used, data, start, mid, out, 2*pos+1);
      buildSegmentTree(used, data, mid+1, end, out, 2*pos+2);

      if (!used[2*pos+1] && !used[2*pos+2])
      {
         out[pos] = 0;
      }
      else if (!used[2*pos+2])
      {
         out[pos] = out[2*pos+1];
      }
      else if (!used[2*pos+1])
      {
         out[pos] = out[2*pos+2];
      }
      else
      {
         out[pos] = Math.min(out[2*pos+1], out[2*pos+2]);
      }
      used[pos] = true;
   }
    
    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      int amount = in.nextInt();
      
      int total = amount * 2 - 1;
      int [] numbers = new int[total];
      boolean [] used = new boolean[total];
      Map<Integer, Integer> counts = new HashMap<>();
      ArrayList<Integer> list = new ArrayList<>();
      
      for (int i=0; i<total; i++)
      {
         int num = in.nextInt();
         Integer count = counts.get(num);
         if (count == null)
         {
            list.add(num);
            count = 0;
         }
         counts.put(num, count+1);
      }
      Collections.sort(list);
      
      if (list.size() == amount)
      {
         buildSegmentTree(used, list, 0, list.size()-1, numbers, 0);
         System.out.println("YES");
         for (int num : numbers)
         {
            System.out.print(num);
            System.out.print(" ");
         }
      }
      else
      {
         System.out.println("NO");
      }
    }
}