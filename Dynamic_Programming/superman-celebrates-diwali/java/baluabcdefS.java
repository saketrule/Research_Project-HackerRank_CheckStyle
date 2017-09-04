import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int H = in.nextInt();
        int I = in.nextInt();
        int[][] person = new int[N][H];
        for(int i=0;i<N;i++){
            int u = in.nextInt();
            for(int j=0;j<u;j++){
                person[i][in.nextInt()-1]++;
            }
        }
        int opt[][] =new int[N][H];
        int max[] = new int[H];
        for(int i=H-1;i>=0;i--){
            for(int n=0;n<N;n++){
                if(i == H-1)
                    opt[n][i] = person[n][i];
                else if(i+I > H-1)
                    opt[n][i] = person[n][i] + opt[n][i+1];
                else
                    opt[n][i] = person[n][i] + Math.max(opt[n][i+1], max[i+I]);
            }
            int _max = Integer.MIN_VALUE;
            for(int n=0;n<N;n++){
                _max = Math.max(_max, opt[n][i]);
            }
            max[i] = _max;
        }
        System.out.println(max[0]);
    }
}