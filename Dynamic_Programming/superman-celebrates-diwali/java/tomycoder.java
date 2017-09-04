import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int n, h, a;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        h = sc.nextInt();
        a = sc.nextInt();
        
        int[][] people = new int[n][h];
        
        for (int b=0; b<n; b++) {
            int p = sc.nextInt();
            
            for (int i=0; i<p; i++) {
                int f = sc.nextInt()-1;
                people[b][f] += 1;
            }
        }
        
        int[][] max = new int[h][2];
        
        int[][] opt = new int[n][h];
        
        for (int b=0; b<n; b++) {
            opt[b][h-1] = people[b][h-1];
            if (opt[b][h-1] >= max[h-1][0]) {
                max[h-1][1] = max[h-1][0];
                max[h-1][0] = opt[b][h-1];
            }
        }
        
        for(int f=h-2; f>=0; f--) {
            for (int b=0; b<n; b++) {
                if (f + a >= h) {
                    opt[b][f] = people[b][f] + opt[b][f+1];
                } else {
                    
                    int m = max[f+a][0] == opt[b][f+a] ? max[f+a][1] : max[f+a][0];
                    
                    opt[b][f] = people[b][f] + Math.max(opt[b][f+1], m);
                }
                
                if (opt[b][f] >= max[f][0]) {
                    max[f][1] = max[f][0];
                    max[f][0] = opt[b][f];
                }
            }
        }
        
        System.out.println(max[0][0]);
    }
}