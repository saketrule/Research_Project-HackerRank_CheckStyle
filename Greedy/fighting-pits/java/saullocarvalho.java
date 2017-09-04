import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static int whoWins(ArrayList<Integer>[] teams, int x, int y) {
        int p = teams[x].size();
        int q = teams[y].size();
        int index = 0;
        
        while (p > 0 && q > 0) {
            if (index % 2 == 0) q -= teams[x].get(p-1);
            else p -= teams[y].get(q-1);
            index++;
        }
        
        if (p > 0) return x;
        return y;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), k = scan.nextInt(), q = scan.nextInt();
        
        ArrayList<Integer>[] teams = new ArrayList[k+1];
        for (int i = 0; i <= k; i++) teams[i] = new ArrayList<Integer>();
        
        for (int i = 0; i < n; i++) {
            int strength = scan.nextInt(), team = scan.nextInt();
            
            teams[team].add(strength);
        }
        
        for (int i = 0; i < k; i++) Collections.sort(teams[i]);
        
        for (int i = 0; i < q; i++) {
            int query = scan.nextInt();
            
            if (query == 1) {
                int p = scan.nextInt(), x = scan.nextInt();
                
                teams[x].add(p);
            } else {
                int x = scan.nextInt(), y = scan.nextInt();
                
                System.out.printf("%d\n", whoWins(teams, x, y));
            }
        }
    }
}