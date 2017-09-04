import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int mDistances[][];
    static int mNodes, mEdges;
    static int mQueries;
    public static void main(String[] args) {
        /* Enter your c
        ode here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        
        mNodes = sc.nextInt();
        mEdges = sc.nextInt();
        
        mDistances = new int[mNodes+1][mNodes+1];
        
        for (int i =1; i <= mNodes; i++)
            {
            
            for (int j =1; j <=mNodes;j++)
                {
                mDistances[i][j] = Integer.MAX_VALUE;
             
            }
        }
        
           for (int i =1; i <= mNodes; i++)
            {
               mDistances[i][i] =0;
           }
        
        for (int i  = 0 ; i < mEdges; i++)
            {
            int from = sc.nextInt();
            int to = sc.nextInt();
            
            mDistances[from][to] = sc.nextInt();
            
           
        }
        
        
        //FW
        
        for (int k = 1; k <= mNodes; k++)
            {
            
            for (int i = 1; i <= mNodes; i++)
                {
                
                for (int j = 1; j <= mNodes; j++)
                    {
                    
                       if (mDistances[i][k]!= Integer.MAX_VALUE  && mDistances[k][j] != Integer.MAX_VALUE )
                           {
                            if (mDistances[i][j] > mDistances[i][k] + mDistances[k][j])
                                {
                                
                                mDistances[i][j] = mDistances[i][k] + mDistances[k][j];
                            }
                           
                       }
                    
                }
            }
            
        }
        
        mQueries = sc.nextInt();
        
        for (int i =0; i < mQueries; i++)
            {
            int dist = mDistances[sc.nextInt()][sc.nextInt()];
            
            if (dist == Integer.MAX_VALUE)
                {
                dist = -1;
            }
            
            System.out.println(dist);
            
        }
        
        
        
        
        
        
        
        
    
    }
}