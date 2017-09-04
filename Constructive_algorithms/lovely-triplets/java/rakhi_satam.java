import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner.*;
import java.util.Set;
public class Solution {

    public static void main(String[] args) {
       int[] arr,helper;
        Scanner scn=new Scanner(System.in);
        int x=scn.nextInt();
        arr=new int[x];
        helper=new int[x];
        for(int i=0;i<x;i++)
        {
            arr[i]=scn.nextInt();
        }
        int triplets=0;
        helper[0]=0;
        Map<Integer,Integer> map1=new HashMap<Integer,Integer>();

        for(int i=0;i<x;i++)
        {
            int minVals=0;

            Set<Integer> set=new HashSet<Integer>();
            int tempTrip=0;
            for(int j=i-1;j>=0;j--)
            {   
                if(arr[j]<arr[i] && !set.contains(arr[j]))
                {
                    minVals++;
                    tempTrip+=helper[j];
                    set.add(arr[j]);
                }
            }
            helper[i]=minVals;
            if(!map1.containsKey(arr[i]))
            {
                triplets+=tempTrip;
                map1.put(arr[i],tempTrip);
            }
            else
            {
                triplets=triplets-map1.get(arr[i])+tempTrip;
            }
        }
        System.out.println(triplets);
        
    }
}