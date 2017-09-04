import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static int b;
    private static int l;
    private static int k;
    private static int[][] p;
    
    public static void main(String[] args) {
        getInput();
        int[][] map = new int[b+1][l];
        for(int i =0;i<l;i++)
        {
            for(int j = 0;j<b;j++)
            {
                map[j][i] = p[j][i];
                if(i>0)
                    map[j][i]+=map[j][i-1];

                if(i-k>=0)
                {
                     map[j][i] = Math.max(p[j][i]+map[b][i-k],map[j][i]);
                }
                
                if(map[j][i] > map[b][i])
                       map[b][i] = map[j][i];
            }
        }
        
        int res = 0;
        for(int i =0;i<b;i++)
        {
            res = res<map[i][l-1]?map[i][l-1]:res;
        }
        
        System.out.println(res);
        
    }
    
    private static void getInput()
    {
        Scanner sc = new Scanner(System.in);
        b = sc.nextInt();
        l = sc.nextInt();
        k = sc.nextInt();
        p = new int[b][l];
        for(int i =0;i<b;i++)
        {
            int buf = sc.nextInt();
            while(buf>0)
            {
                p[i][sc.nextInt()-1]+=1;
                buf--;
            }
        }
    }
}