import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int max = Integer.MIN_VALUE;
    static long count =0;
    static int isoTotal = 0;
    static int total = 0;
    static HashMap<Integer,List<Integer>> map;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[] c = new int[n+1];
        int[] house = new int[n+1];
        map = new HashMap<Integer,List<Integer>>();
        for(int i=1;i<=n;i++)
            {
            c[i] = scan.nextInt();
            house[i] = 1;
            map.put(i,new ArrayList<Integer>());
        
        }
        for(int i=0;i<m;i++)
            {
            int from = scan.nextInt();
            int to = scan.nextInt();
            List<Integer> list1 = map.get(from);
            List<Integer> list2 = map.get(to);
            list1.add(to);
            list2.add(from);
            map.put(from,list1);
            map.put(to,list2);
        }
        int isoZeroCount =0;
        for(int i: map.keySet())
        {
            List<Integer> list = map.get(i);
            if(list.isEmpty())
                {
                isoTotal += c[i];
                house[i] = -1;
                if(c[i]==0)
                    isoZeroCount++;
            }
        }
        double isoZeroCountPermu = Math.pow(2,isoZeroCount);
        //print(house);
        total = isoTotal;
        steal(house,c,1,total);
        /*if(value > max)
        {
            max = value;
            count = 1;
        }
        else if(value == max)
        {
            count++;
        }*/
        count = count * (long)isoZeroCountPermu;
        System.out.print(max + " " + count);
    }
 public static void steal(int[] house,int[] c,int index,int total)
 {  
     if(index == house.length)
         return;
     
     for(int i=index;i<house.length;i++)
        {
         if(house[i]==1)
             {
             house[i]--;
             List<Integer> list = map.get(i);
             for(int j: list)
                 {
                 house[j]--;
             }
             //print(house);
             total+= c[i];
             steal(house,c,i+1,total);
             if(total > max)
             {
                 max = total;
                 count = 1;
             }
             else if(total == max)
                 {
                 count++;
             }
            for(int j: list)
                 {
                 house[j]++;
             }
             house[i]++;
             total -=c[i];
         }
     }
     
 }
    public static void print(int[] house)
        {
        for(int i=1;i<house.length;i++)
            {
            System.out.print(house[i] + " ");
        }
        System.out.println(" ");
    }
    
    
}