import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        try{
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        String s[]=in.readLine().split(" ");
        int N=Integer.parseInt(s[0]);
        int H=Integer.parseInt(s[1]);
        int I=Integer.parseInt(s[2]);
        int[][] building=new int[H+1][N];
        for(int i=0;i<N;i++){
            s=in.readLine().split(" ");
            createBuilding(i,s,building,H);
        }
        System.out.println(maxPeople(building,H,N,I));
        }
        catch(Exception e){
            System.out.print(e);
        }
    }
    public static void createBuilding(int i,String[] s,int[][] building,int F){
        int len=Integer.parseInt(s[0]);
        for(int j=0;j<len;j++)
            building[F-Integer.parseInt(s[j+1])][i]+=1;
    }
    public static int maxPeople(int[][] building,int F,int B,int I){
        int maxP=0,i,j,k;
        for(i=0;i<=F;i++){
            for(j=0;j<B;j++){
                if(i-I>=0){
                 if(building[i-I][j]==maxP)
                     building[i][j]+=building[i-1][j];
                 else
                     building[i][j]+=Max(maxP,building[i-1][j]);
                }
                else if(i-1>=0)
                 building[i][j]+=building[i-1][j];
                
            }
            maxP=0;
            if((i-I+1)>=0){
            for(k=0;k<B;k++){
                if(building[i-I+1][k]>maxP)
                    maxP=building[i-I+1][k];
            }
            }
        }
        maxP=0;
        for(j=0;j<B;j++)
        {
         if(building[F][j]>maxP)
          maxP=building[F][j];
        }
        return maxP;
    }
    public static int Max(int a,int b){
        return (a>b?a:b);
    }
}