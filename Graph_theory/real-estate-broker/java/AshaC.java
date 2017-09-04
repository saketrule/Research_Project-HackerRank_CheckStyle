import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int a[] = new int[m];
        int b[] = new int[n];
        TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
        TreeMap<Integer, Integer> tm1 = new TreeMap<Integer, Integer>();
        for(int i=0;i<m;i++){
           tm.put(sc.nextInt(), sc.nextInt());
        }
        for(int i=0;i<n;i++){
           tm1.put(sc.nextInt(), sc.nextInt());
        }
        
        Iterator<Integer> it = tm.keySet().iterator();
        Iterator<Integer> it1 = tm1.keySet().iterator();
        int count = 0;
        int countOfHouses[] =new int[n];
        int i = 0;
        while(it.hasNext()) {
            int a0 = it.next();
            int p0 = tm.get(a0);
            
           // System.out.println(a0+" "+p0);
             it1 = tm1.keySet().iterator();
            i=0;
            while(it1.hasNext()){
                int x0 = it1.next();
                int y0 = tm1.get(x0);
             //    System.out.println(x0 + " "+y0);
                if(a0<x0 && p0>y0){
                  //  count++;
                    countOfHouses[i] = 1;
                }
              //  System.out.println(count);
                i++;
            }
        }
        for(int j=0;j<n;j++) {
            if(countOfHouses[j]==1){
                count++;
            }
        }
        System.out.println(count);
    }
}