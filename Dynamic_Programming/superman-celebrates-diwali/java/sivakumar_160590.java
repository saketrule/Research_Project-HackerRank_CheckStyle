import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
       int N,H,I;
        int people[][];
        Scanner sc= new Scanner(System.in);
        N= sc.nextInt();
        H= sc.nextInt();
        I= sc.nextInt();
        people = new int [H][];
        for(int i=0;i<H;i++)
        {
            people[i]= new int[N];
        }
        for(int i=0;i<N;i++)
        {
            int un = sc.nextInt();
            for(int j=0;j<un;j++)
            {
                people[sc.nextInt()-1][i]++;
            }
        }
        
        int curr_best[];
        int prev_best[]= new int[N];
        int max_cost[]= new int[H];
        
        for(int i=0;i<N;i++)
        {
            prev_best[i]= people[0][i];
            if(max_cost[0]<prev_best[i])
                max_cost[0]=prev_best[i];
        } 
        curr_best=new int[N];
        int temp[];
        for(int i=1;i<H;i++)
        {            
            for(int j=0;j<N;j++)
            {
                if(i-I>=0)
                    curr_best[j]=Math.max(prev_best[j], max_cost[i-I])+people[i][j];
                else
                    curr_best[j]=people[i][j]+prev_best[j];
                if(max_cost[i]<curr_best[j])
                max_cost[i]=curr_best[j];
            }
            temp=prev_best;            
            prev_best=curr_best;
            curr_best=temp;
        }   
        System.out.println(max_cost[H-1]);
    }
}