import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

     static Scanner sc = new Scanner(System.in);
  
  static int i(){
        return sc.nextInt();
    }
 
 static long l(){
        return sc.nextLong();
    }
    
    public static void main(String[] args) {
        
        long n = l();
        HashMap<Integer,Integer> hm = new HashMap();
        HashMap<Integer,TreeSet<Integer>> ln = new HashMap();
        PrintWriter fout = new PrintWriter(System.out);
        for(int i=1 ; i<2*n ; i++)
        {
            int v = i();
            if(hm.containsKey(v))
            {
                hm.put(v,hm.get(v)+1);
            }
            else
                hm.put(v,1);
        }
        
        
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) 
  {
       int tree_el = entry.getKey();
             int lev = entry.getValue();
            
             if(ln.containsKey(lev))
             {
                 ln.get(lev).add(tree_el);
             }
             else
             {
                 ln.put(lev,new TreeSet());
                 ln.get(lev).add(tree_el);
             }
        }
        
        int lev = (int)(Math.log(n)/Math.log(2)+1);
     //  System.out.println(lev);
       
        
        if(ln.containsKey(lev))
        {
            if(ln.get(lev).size() != 1)
            {
                System.out.print("NO");
                System.exit(777);
            }
        }
        else
        {
                System.out.print("NO");
                System.exit(777);
        }    
        lev--;
        int k = 1;
        for (;lev>0;) 
  {
              if(!ln.containsKey(lev))
              {
                    System.out.print("NO");
                    System.exit(777);
              } 
            
              if(ln.get(lev--).size() != k)
              {
                    System.out.print("NO");
                    System.exit(777);
              }  
             
             k *= 2;
             
             if(k>n/2)
             break;
        }
        
        
        HashMap<Integer,Integer> final_ans = new HashMap();
        
        int index = 1;
        
        final_ans.put(0,Integer.MIN_VALUE);
        
        for(int i=(int)(Math.log(n)/Math.log(2)+1) ; i>=1 ;i--)
        {
            int index_i = index;
            TreeSet<Integer> t = ln.get(i);
        //    System.out.println("level  "+i+" treeset  "+t);
            for(int j=0;j<t.size();j++)
            {
                int num = t.higher(final_ans.get(index_i-1));
              //  System.out.println("level "+i+"  number"+num);
                
                if(t.higher(final_ans.get(index_i-1)) == null)
                {
                    System.out.print("NO");
                    System.exit(19);
                }
                
                t.remove(num);
                j--;
                for(k=0;k<i;k++)
                {
                    final_ans.put(index_i,num);
                    index_i = 2*index_i;
                }
         //       System.out.println(final_ans);
                index += 2;
                index_i = index;
            }
        }
       System.out.println("YES");
     /*   final_ans.remove(0);
        for(int i :  final_ans.keySet())
            System.out.print(final_ans.get(i)+" ");
        System.out.println();*/
        StringBuilder s = new StringBuilder("");
        for(int i =1 ;i<=2*n-1;i++)
        s.append(final_ans.get(i)+" ");
           
        System.out.print(s);
        
 }
}