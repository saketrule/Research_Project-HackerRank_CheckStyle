import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.HashMap;
import java.util.regex.*;

public class Solution {
    static int maxAmount = 0;
    
    static long setBit(long number, int position){
        return number | ((long)Math.pow(2, position));
    }
    
    static long clearBit(long number, int position){
        return number & (~((long)Math.pow(2, position)));
    }
    
    static boolean isBitSet(long number, int position){
        return (number & ((long)Math.pow(2, position))) != 0;
    }
    
    static void demand(int[] money, long[] neigh, long home ,long robbed, int currentSum, List<Map<Long, Boolean>> cache){
        if(cache.get(currentSum).containsKey(robbed)) {
            return;
        };
        
        for(int i = 1; i < money.length; i++){
            if(!isBitSet(home, i)) continue; // not home;
            int sum = currentSum + money[i];
            robbed = setBit(robbed, i);

            if(sum > maxAmount) maxAmount = sum;
            long homeCpy = home;

            homeCpy = clearBit(homeCpy, i);
            homeCpy &= ~neigh[i]; // neighbours left home

            demand(money, neigh, homeCpy, robbed, sum, cache);
            
            cache.get(sum).put(robbed, true);
            
            robbed = clearBit(robbed, i);
        }
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int money[] = new int[n + 1];
        long neigh[] = new long[n + 1];
        long home = -1;
        long robbed = 0L;
        List<Map<Long, Boolean>> cache = new ArrayList<Map<Long, Boolean>>();
        for(int i = 0; i <= 3402; i++){
            cache.add(new HashMap<Long, Boolean>());
        }
        
        for(int i = 1; i <= n; i++){
            money[i] = in.nextInt();
            neigh[i] = 0L;
        }
        
        while(m-- > 0){
            int u = in.nextInt();
            int v = in.nextInt();
            neigh[u] = setBit(neigh[u], v);
            neigh[v] = setBit(neigh[v], u);
        }
        
        demand(money, neigh, home, robbed, 0, cache);
        
        System.out.print(maxAmount + " " + cache.get(maxAmount).size());
        
    }
}