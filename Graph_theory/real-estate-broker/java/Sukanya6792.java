import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        long[] areaClient = new long[n];
        long[] priceClient = new long[n];
        
        for(int i=0; i<n; i++)
            {
            areaClient[i] = s.nextLong();
            priceClient[i] = s.nextLong();
            
        }
        
        long[] areaHouse = new long[m];
        long[] priceHouse = new long[m];
        for(int i=0; i<m; i++)
            {
            areaHouse[i] = s.nextLong();
            priceHouse[i] = s.nextLong();
            
        }
        
        
        HashMap<Integer,Integer> clientMap = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> houseMap = new HashMap<Integer,Integer>();
        
        for(int i=0; i<n; i++)
           { for(int j=0; j<m; j++)
            {
               if((areaHouse[j] > areaClient[i]) && (priceHouse[j] <= priceClient[i]) )
                   {
                   clientMap.put(i,j);
                   houseMap.put(j,i);
               }
            
            }
           }
        int x = houseMap.size();
        int y = clientMap.size();
        
        System.out.println(x<y?x:y);
        
        
    }
}