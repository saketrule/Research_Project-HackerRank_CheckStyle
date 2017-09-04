import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.concurrent.*;

public class Solution {

    static int cash = 0;
    static long visits = 0;
    static int nHouses = 0;
    static int nPaths = 0;
    static int[] money = null;
    static long[] paths = null;
    
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Scanner scan = new Scanner(System.in);
        nHouses = scan.nextInt(); 
        nPaths = scan.nextInt();
        scan.nextLine();
        
        money = new int[nHouses];
        for(int i = 0; i < nHouses; i++){
            int value = scan.nextInt();
            money[i] = value;
        }
        
        paths = new long[nHouses];
        if (nPaths > 0) {
            scan.nextLine();
            for (int i = 0; i < nPaths; i++) {
                int from = scan.nextInt()-1;
                int to = scan.nextInt()-1;
                paths[from] = paths[from] | 1L << to;
                paths[to] = paths[to] | 1L << from;
            }
        }
        scan.close();
                
        eval (0, 0, 0, 0);
        
        System.out.println(cash + " " + visits);
    }
    static void eval(int start, long ps, int c, int v) {
        if (start < nHouses) {
            if ((ps & (1L << start)) > 0) {
                // House not connected
                eval(start + 1, ps | (1L << start), c, v);
                return;
            }
            ps = ps | (1L << start);
            if ((ps & paths[start]) == paths[start]) {
                if (money[start] == 0) {
                    // No money but have to visit
                    eval(start + 1, ps, c, v + 1);
                } else {
                    // Take money
                    eval(start + 1, ps, c + money[start], v);
                }
            } else {
                // Not masked so vist next house and mask this one
                eval(start + 1, ps, c, v);
                eval(start + 1, ps | paths[start], c + money[start], v);
            }        
        }
        else if (start > nHouses) {
           return; 
        }
        else {
            // End of cycle to check max and add visits
            if (c > cash) {
                cash = c;
                visits = 0;
            }
            if (c == cash) {
                visits = visits + (1L << v);
            }
            return;
        }
    }
}