import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int h = stdin.nextInt();
        int i = stdin.nextInt();

        int p[][] = new int[n+1][h+1];
        int res[][] = new int[n+1][h+1];
        int max[] = new int[h+1];
        for(int b=1;b<=n;b++) {
            int u = stdin.nextInt();
            for(int j=0;j<u;j++) {
                int ui = stdin.nextInt();
                p[b][ui]++;
            }
        }

        for(int f=1;f<=h;f++) {
            max[f] = 1;
        }

        for(int b=1;b<=n;b++) {
            res[b][1] = p[b][1];
            if(res[b][1] > max[1]) {
                max[1] = b;
            }
        }

        for(int f=2;f<=h;f++) {
            for(int b=1;b<=n;b++) {
                res[b][f] = p[b][f] + res[b][f-1];
                if(f > i) {
                    int jump = p[b][f] + res[max[f - i]][f - i];
                    if (jump > res[b][f]) {
                        res[b][f] = jump;
                    }
                }

                if(res[b][f] > res[max[f]][f]) {
                    max[f] = b;
                }
            }
        }

        int maxRes = 0;
        for(int b=1;b<=n;b++) {
            if(res[b][h] > maxRes) {
                maxRes = res[b][h];
            }
        }

        System.out.println(maxRes);
    }
}