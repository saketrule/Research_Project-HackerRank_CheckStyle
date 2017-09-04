import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static int findWinner(int x, int y,LinkedList l1,LinkedList l2)
    {
      
      if(l2.size() == 0)
        return x;
       if(l1.size() ==0)
        return y;
      
      Integer range = (Integer)l1.peekLast();
    
      for(int i =0 ;i<range && l2.size()>0; i++)
      {
      
         l2.removeLast();
      
        
      }
     
      return findWinner(y,x,l2,l1);      
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        int q = s.nextInt();
        int si,t;
        int type,x,y;
        LinkedList []l = new LinkedList[k+1];
        for(int m = 1; m < l.length; m++)
        {
          l[m] = new LinkedList<Integer>();
        }
        for(int i = 0; i<n; i++)
        {
           si=s.nextInt();
           t = s.nextInt();
           l[t].add(si);
        }
        
        for(int i = 0; i <q; i++)
        {
            type = s.nextInt();
            if(type == 1)
            {
               si = s.nextInt();
               t = s.nextInt();
               l[t].add(si);
            }
            else
            {
              x= s.nextInt();
              y =s.nextInt();
              Collections.sort(l[x]);
              Collections.sort(l[y]);
              LinkedList l1 = (LinkedList)l[x].clone();
              LinkedList l2 = (LinkedList)l[y].clone();
              System.out.println(findWinner(x,y,l1,l2));
           
              
            }
        }
        
        
    }
}