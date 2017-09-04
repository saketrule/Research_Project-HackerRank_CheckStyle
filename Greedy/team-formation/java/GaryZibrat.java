import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 
 public static class Interval
 {
  int left, right;
  public Interval(int l, int r)
  {
   left = l;
   right = r;
  }
  
  public boolean contains(int n)
  {
   return n >= left && n <= right;
  }
  
  public boolean addable(int n)
  {
   return n == right + 1;
  }
  
  public int size()
  {
   return right - left;
  }
 }
 
 public ArrayList<Interval> groups;
 public ArrayList<Interval> possible;
 public int index;
 
 public Solution(int n)
 {
  groups = new ArrayList<>(n);
  possible = new ArrayList<>(n);
 }
 
 public int smallestGroup()
 {
  int size = Integer.MAX_VALUE;
  for(int i = 0; i < groups.size(); i++)
  {
   int gs = groups.get(i).size();
   if(gs < size)
   {
    size = gs;
   }
  }
  return size + 1;
 }
 
 public void firstAdd(int n)
 {
  groups.add(new Interval(n,n));
 }
 
 public void add(int n)
 {
  int size = 0;
  possible.clear();
  for(int i = index; i < groups.size(); i++)
  {
   Interval in = groups.get(i);
   if(in.addable(n))
   {
    if(possible.isEmpty())
    {
     possible.add(in);
     size = n - in.left;
    }
    else
    {
     int newSize = in.right-in.left +1;
     if(newSize < size)
     {
      possible.clear();
      possible.add(in);
     }
     else if(newSize == size)
     {
      possible.add(in);
     }
    }
   }
   else if(!in.contains(n))
   {
    index++;
   }
  }
  if(possible.isEmpty())
  {
   groups.add(new Interval(n,n));
  }
  else
  {
   Interval inter = possible.get(possible.size()-1);
   inter.right = n;
  }
  index = Math.max(0, Math.min(index, groups.size()-1));
 }
 
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        int T = scan.nextInt();
        for(int t = 0; t < T; t++)
        {
         int N = scan.nextInt();
         if(N == 0)
         {
          System.out.println("0");
          continue;
         }
            Solution s = new Solution(N);
            
            int[] students = new int[N];
            for(int n = 0; n < N; n++)
            {
             students[n] = scan.nextInt();
            }
            Arrays.sort(students);
            
            s.firstAdd(students[0]);
            for(int n = 1; n < N; n++)
            {
             s.add(students[n]);
            }
            System.out.println(s.smallestGroup());
        }
        scan.close();
    }
}