import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static Scanner sc = new Scanner(System.in);
    
    public static void solve(int num) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for(int i = 0; i < num; i++) {
            int t = sc.nextInt();
            if(map.containsKey(t)) {
                map.put(t, map.get(t)+1);
            }
            else map.put(t, 1);
        }
        
        solve(map);
    }
    
    public static void solve(TreeMap<Integer, Integer> map) {
        
        int min = Integer.MAX_VALUE;
        
        while(map.lastEntry() != null) {
            int total = 1;
            boolean terminate = false;
            Map.Entry<Integer, Integer> lastEntry = map.lastEntry();
            int lastKey = lastEntry.getKey();
            int lastCount = lastEntry.getValue();
            if(lastEntry.getValue() == 1) map.remove(lastEntry.getKey());
            else map.put(lastEntry.getKey(), lastEntry.getValue() - 1);
            
            while(!terminate) {
                Integer nextKey = lastKey - 1;
                Integer nextCount = map.get(nextKey);
                if(nextCount == null) terminate = true;
                else {
                    if(nextCount < lastCount) {
                        terminate = true;
                    }
                    else {
                        total+=1;
                        if(nextCount == 1) map.remove(nextKey);
                        else map.put(nextKey, nextCount - 1);
                        lastKey = nextKey;
                        lastCount = nextCount;
                    }
                }
            }            
            if(total < min) min = total;
        }
        
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }

    public static void main(String[] args) {
        int testCases = sc.nextInt();
        for(int i = 0; i < testCases; i++) {
            solve(sc.nextInt());
        }
    }
}