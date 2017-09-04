import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static int head;
    private static long min;
    
    public static void main(String[] args) {
                            Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            if (n == 0) {
                System.out.println(0);
                continue;
            }
            long a[] = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            Arrays.sort(a);
            long team[][] = new long[2][n];// first row willl have last element,
            min = Integer.MAX_VALUE;
            head = 0;
            for(int i=0;i<n;i++){
                int t1 = getTeamToAdd(a[i], team);
                team[0][t1] = a[i];
                team[1][t1]++;
            }
            int in = head;
            while(in<n && team[1][in]!=0){
                if(min>team[1][in]){
                    min = team[1][in];
                }
                in++;
            }
            System.out.println(min);

        }
    }
        private static int getTeamToAdd(long l,long team[][]){
        long t = Integer.MAX_VALUE;
        int ind = -1;
        int i =head;
        while(team[1][i]!=0){
            if(team[0][i]<l-1){
                head = i;
                if(team[1][i]<min)
                    min = team[1][i];
            }
            if(team[0][i]==l-1){
                if(t>team[1][i])
                    t = team[1][i];
                ind = i;
            }
            i++;
        }
         ind = ind!=-1?ind:i;

        return ind;
    }
}