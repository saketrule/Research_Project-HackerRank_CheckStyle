import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Solution {
    
    public static void backtrack(int node, 
                                ArrayList<ArrayList<Integer>> neighbors, 
                                int[] mark,
                                int[] money,
                                int current_money,
                                AtomicInteger max_money,
                                ArrayList<Integer> set,
                                int n) 
    {
        
        if (max_money.get() < current_money) {
            max_money.set(current_money);
        }
        
        for (int i = node + 1; i <= n; i++) {
            if (mark[i] == 0) {
                
                mark[i] = -1;
                for (int neighbor : neighbors.get(i)) {
                    mark[neighbor]++;
                }
                set.add(i);
                current_money = current_money + money[i];
                
                backtrack(i, neighbors, mark, money, current_money, max_money, set, n);
                
                current_money = current_money - money[i];
                set.remove(set.size() - 1);
                mark[i] = 0;
                for (int neighbor : neighbors.get(i)) {
                    mark[neighbor]--;
                }
            }
        }
        
    }
    
    public static void backtrack_count(int node, 
                                ArrayList<ArrayList<Integer>> neighbors, 
                                int[] mark,
                                int[] money,
                                int current_money,
                                int max_money,
                                AtomicInteger count,
                                ArrayList<Integer> set,
                                ArrayList<ArrayList<Integer>> sets,
                                int n) 
    {
        
        
        if (max_money == current_money) {
            boolean found = false;
            for (ArrayList<Integer> list : sets) {
                if (list.equals(set)) {
                    found = true;
                    break;
                }
            }
            if (found == false) {
                sets.add(new ArrayList<Integer>(set));
                count.incrementAndGet();
            }
        }
        //System.out.println(current_money);
        for (int i = node + 1; i <= n; i++) {
            if (mark[i] == 0) {
                
                mark[i] = -1;
                for (int neighbor : neighbors.get(i)) {
                    mark[neighbor]++;
                }
                set.add(i);
                current_money = current_money + money[i];
                
                backtrack_count(i, neighbors, mark, money, current_money, max_money, count, set, sets, n);
                
                current_money = current_money - money[i];
                set.remove(set.size() - 1);
                mark[i] = 0;
                for (int neighbor : neighbors.get(i)) {
                    mark[neighbor]--;
                }
            }
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[] money = new int[n + 1];
        int[] mark = new int[n + 1];
        
        money[0] = 0; 
        mark[0] = 0;
        AtomicInteger max_money = new AtomicInteger(0);
        AtomicInteger count = new AtomicInteger(0);
        ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>(n+1);
        ArrayList<ArrayList<Integer>> sets = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> set = new ArrayList<Integer>();
        ArrayList<Integer> sd = new ArrayList<Integer>();
        neighbors.add(new ArrayList<Integer>());
        
        for (int i = 1; i <= n; i++) {
            neighbors.add(new ArrayList<Integer>());
            money[i] = sc.nextInt();
            mark[i] = 0;
        }
        
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            neighbors.get(a).add(b);
            neighbors.get(b).add(a);
        }
        
        backtrack(0, neighbors, mark, money, 0, max_money, set, n);
        
        for (int i = 1; i <= n; i++) {
            mark[i] = 0;
        }
        
        backtrack_count(0, neighbors, mark, money, 0, max_money.get(), count, sd, sets, n);
        
        System.out.print(max_money.get());
        System.out.print(" ");
        System.out.print(count.get());
        
        /*
        for (int i = 1; i <= n; i++) {
            System.out.print(Integer.toString(i) + ":");
            for (int x : neighbors.get(i)) {
                System.out.print(Integer.toString(x) + " ");
            }
            System.out.println(" ");
        }
        */
    }
}