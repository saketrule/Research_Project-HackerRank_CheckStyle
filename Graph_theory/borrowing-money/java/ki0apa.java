import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static boolean[] r;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] coins = new int[n + 1];
        for(int i = 1; i <= n; i++){
            coins[i] = in.nextInt();
        }
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < m; i++){
            int s = in.nextInt();
            int e = in.nextInt();
            graph[s].add(e);
            graph[e].add(s);
        }
        int[] a = recur(graph, coins, 1, new boolean[n + 1]);
        System.out.println(a[0] + " " + a[1]);
    }
    
    public static int[] recur(ArrayList<Integer>[] graph, int[] coins, int i, boolean[] reached){
        if(i >= reached.length){
            return new int[]{0, 1};
        }
        int[] m1 = recur(graph, coins, i + 1, Arrays.copyOf(reached, reached.length));
        if(!(graph[i].size() == 0 || reached[i])){
            for(int j = 0; j < graph[i].size(); j++){
                reached[graph[i].get(j)] = true;
            }
            int[] m2 = recur(graph, coins, i + 1, reached);            
            m2[0] += coins[i];
            if(m2[0] > m1[0]){
             m1 = m2;
            }
            else if(m2[0] == m1[0]){
             m1[1] += m2[1];
            }
        }
        if(graph[i].size() == 0){
            m1[0] += coins[i];
        }
        return m1;
    }
}