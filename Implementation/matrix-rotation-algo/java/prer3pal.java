import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s=new Scanner(System.in);
        int M=s.nextInt();
        int N=s.nextInt();
        long R=s.nextLong();
        long[][] arr=new long[M][N];
        for(int i=0;i<M;i++) {
            for(int j=0;j<N;j++)
                arr[i][j]=s.nextLong();
        }
        
        int[][][] res;
        
            res=new int[M/2][(M+N-2)*2][2];
            int cnt=0;
            for(int i=0;i<M/2;i++) {
                int c=0;
                for(int j=i;j<N-i;j++) {
                    res[cnt][c][0]=i;
                    res[cnt][c][1]=j;
                    c++;
                }
                for(int j=i+1;j<M-i;j++) {
                    res[cnt][c][0]=j;
                    res[cnt][c][1]=N-i-1;
                    c++;
                }
                for(int j=N-i-2;j>=i;j--) {
                    res[cnt][c][0]=M-i-1;
                    res[cnt][c][1]=j;
                    c++;
                }
                for(int j=M-i-2;j>i;j--) {
                    res[cnt][c][0]=j;
                    res[cnt][c][1]=i;
                    c++;
                }
                cnt++;
                if (i+1>=N/2)
                    break;
            }
            for(int i=0;i<cnt;i++) {
                rotate(res[i],(M+N-4*i-2)*2,arr,R);
            }
            for(int i=0;i<M;i++) {
                for(int j=0;j<N;j++)
                    System.out.print(arr[i][j]+" ");
                System.out.println();
            }            
        
    }
    
    public static void rotate(int[][] r,int len, long[][] arr, long Rr) {
        if (len<=0)
            return;
        int R=(int)(Rr%len);
        long[] newar=new long[len];
        for(int i=0;i<len;i++)
            newar[i]=arr[r[i][0]][r[i][1]];
        for(int i=0;i<len;i++) {
            int x=(i+R)%len;
            arr[r[i][0]][r[i][1]]=newar[x];
        }                
    }
}