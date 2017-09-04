import java.util.*;


public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s=new Scanner(System.in);
        int testcase,caserow;
        testcase=s.nextInt();
        int coords[][][]=new int[testcase][][];
        int result[][]=new int[testcase][2];
        for(int i=0;i<testcase;i++)
        {
         caserow=s.nextInt();
         coords[i]=new int[caserow][2];
         for(int j=0;j<caserow;j++)
         {
          coords[i][j][0]=s.nextInt();
          coords[i][j][1]=s.nextInt();
         }
        }
        
        int permute[]=new int[2];
        int x=0;
        for(int c=0;c<testcase;c++)
        {
        int limit=0,ways=0,n=coords[c].length;
        permute[0]=0;
        permute[1]=1;
        
        result[x][0]=(n%2)+(n/2);
        limit=(n%2==1)?(n-1):(n-2);
        
        while(permute[0]<limit)
            {
            ways++;
            if((permute[0]<(limit-1) && permute[1]==(n-1)))
                {
                    permute[0]++;
                    permute[1]=permute[0]+1;
                    
                    continue;
            }
            else if((permute[0]==(limit-1) && permute[1]==limit))
                {
                break;
            }
            permute[1]++;
        }
        
        result[x][1]=(ways==0)?1:(ways*2);
        x++;
        }
        
        for(int i=0;i<testcase;i++)
        {
           System.out.println(result[i][0]+" "+result[i][1]);
        }
    }   
}