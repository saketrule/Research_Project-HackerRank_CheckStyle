import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        int N,H,I;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] splits = str.split("\\s+");
        N = Integer.parseInt(splits[0]);
        H = Integer.parseInt(splits[1]);
        I = Integer.parseInt(splits[2]);
        int[][] matrix= new int[H][N];
        int[] maxAtH = new int[H];
        int p,floor;
        for(int i=0;i<H;i++)
            for(int j=0;j<N;j++)
                matrix[i][j] = 0;
        
       
        for(int i=0;i<N;i++){
        str = br.readLine();
           splits = str.split("\\s+");   
           p = Integer.parseInt(splits[0]);
        for(int j=1;j<=p;j++){
         floor = Integer.parseInt(splits[j])-1;//indexing
               matrix[floor][i]++;

        }
        }
        int ANS = -1;
        for(int h=H-1;h>=0;h--){
            maxAtH[h] = 0;
            for(int n=0;n<N;n++){
                int maxPrev = h<H-1?matrix[h+1][n]:0;
                if(h+I<H){
                  /*  int prevH = h+I;
                    for(int n1=0;n1<N;n1++){
                        if(n1!=n){
                            maxPrev = maxPrev>matrix[prevH][n1]?maxPrev:matrix[prevH][n1];
                        }
                    }*/
                    maxPrev = maxPrev > maxAtH[h+I]?  maxPrev : maxAtH [h+I];
                }
                matrix[h][n] += maxPrev;
                maxAtH[h] = maxAtH[h] > matrix[h][n]?maxAtH[h]:matrix[h][n];
            }
        }
        for(int n=0;n<N;n++)
            ANS = ANS>matrix[0][n]?ANS:matrix[0][n];
        System.out.println(ANS);
    }
}