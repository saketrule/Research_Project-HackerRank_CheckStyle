/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kkarthik
 */import java.io.*;
public class Solution {
public static void main(String args[]) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] words = br.readLine().split(" ");
    int N = Integer.parseInt(words[0]);
    int K= Integer.parseInt(words[1]);
    long[] val = new long[N+1];
    val[0]=0;
    for(int i=1;i<=N;i++){
        val[i]= Long.parseLong(br.readLine());
    }
    long[] sum = new long[N+1];
    sum[0]=0;
    long max,S,ans_i=-1,k=0;
    long[] ans = new long[N+1];
    for(int i=1;i<=K;i++){
        sum[i]=sum[i-1]+val[i];
        
    }

  
    for(int i=K+1;i<=N;i++){
        sum[i]=sum[i-1]+val[i];
        if(i-ans_i >= K+2)     {
            S = val[i];
            max=0;
            S += sum[i-2];
            if(S>max){
                max=S;
                ans_i = i-2;
            }
            S= val[i];
            k=0;
            for(int j=0;j<K-1;j++){
             for(;k<=j;k++){
                 S += val[i-((int)k+1)];
             }
             S += sum[i - (int)(k + 2)];
             if(S>max){
                max=S;
                ans_i = i-(k+2);
            }
             S -= sum[i - (int)(k + 2)];
             
          }
            if (S > max) {
                max = S;
                ans_i = i;
            }
            if (sum[i - 1] > max) {
                max = sum[i - 1];
                ans_i = i - 1;
            }
            sum[i] = max;
        } else{
            sum[i] = sum[i - 1] + val[i];
        }
        
    }
    System.out.println(sum[N]);
}
}