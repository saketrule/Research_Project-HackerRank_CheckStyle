import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        for (int i=0; i<n; i++) {
            String[] a = in.nextLine().split(" ");
            int city = Integer.parseInt(a[0]);
            int road = Integer.parseInt(a[1]);
            
            HashMap<Integer, ArrayList<Integer>> res = new HashMap<Integer, ArrayList<Integer>>();
            HashSet<Integer> set = new HashSet<Integer>(); 
            for (int j=0; j<road; j++) {
                String[] curr = in.nextLine().split(" ");
                int a1 = Integer.parseInt(curr[0]);
                int a2 = Integer.parseInt(curr[1]);
                if (res.containsKey(a1)) {
                    res.get(a1).add(a2);
                }
                else {
                    res.put(a1, new ArrayList<Integer>(Arrays.asList(a2)));
                }
                if (res.containsKey(a2)) {
                    res.get(a2).add(a1);
                }
                else {
                    res.put(a2, new ArrayList<Integer>(Arrays.asList(a1)));
                }
            }
            int start = Integer.parseInt(in.nextLine());
            if (!res.containsKey(start)) {
                for (int l=0; l< city-1; l++) {
                    System.out.print(1+" ");
                }
            }
            else if (road < city -1) {
               HashSet<Integer> setv = new HashSet<Integer>(res.get(start));
                int[] dis = new int[city+1];
                for (int k=1; k< city+1; k++) {
                    if (k != start) {
                        if (setv.contains(k))
                           System.out.print(2+" ");
                        else
                            System.out.print(1+" ");
                    }
                }
            }
            else {
                wander(res, city, start, road);
            }
            System.out.println();
        }
    }
    
    private static void wander(HashMap<Integer, ArrayList<Integer>> res, int city, int start, int road) {
        ArrayList<Integer> curr = res.get(start);
        int[] dis = new int[city+1];
        HashSet<Integer> setv = new HashSet<Integer>(curr);
        HashSet<Integer> nonvisit = new HashSet<Integer>();
        for (int i=1; i< city+1; i++) {
            if (!setv.contains(i)) {
                nonvisit.add(i);
                dis[i] = 1;
            }
            else {
                dis[i] = 2;
            }
        }
        
        int missing = 2*(road+2-city);
        int cal = city-1-curr.size();
        for (int k: setv) {
            if (missing <=0)
                break;
            int sz =res.get(k).size();
            if (sz < nonvisit.size()) {
                //System.out.println("OK"+k);
                if (sz > 1) {
                   for (int m: res.get(k)) {
                     if (!nonvisit.contains(m))
                        missing--;
                     else if (m != start)
                        missing -= 2;
                   }
                }                    
            }
            else {
                int count = 0;
                for (int m: res.get(k)) {
                    //System.out.println(missing +" "+m);
                    if (nonvisit.contains(m))
                        count++;
                    else
                        missing--;
                }
                //System.out.println(missing +"aaa"+k);
                if (count >= nonvisit.size()) {
                    dis[k] = 3;
                    missing -= 2;
                }    
            }
        }            
        for (int i=1; i< city+1; i++) {
            if (i != start)
                System.out.print(dis[i]+" ");
        }    
    }
}