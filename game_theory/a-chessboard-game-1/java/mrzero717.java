import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean board[][] = new boolean[16][16];
        int dx[]= new int[]{-2,-2,1,-1};
        int dy[] = new int[]{-1,1,-2,-2};
        for(int i=2;i<=30;++i)
            for(int j=1;j<Math.min(i,16);++j){
                int x= j;
                int y = i-j;
                if(y<=0 || y>15) continue;
                board[x][y] = false;
                for(int k=0;k<4;++k){
                    int nx = x+dx[k];
                    int ny = y+dy[k];
                    if(nx>0 && ny>0 && nx<16 && ny<16)
                        board[x][y]|=(!board[nx][ny]);
                }
             }
        int T = s.nextInt(); 
        for(int cas =0;cas<T;++cas){
            int n,m;
            n=s.nextInt(); 
            m=s.nextInt(); 
            System.out.println((board[n][m])?"First":"Second");
        }
    }
}