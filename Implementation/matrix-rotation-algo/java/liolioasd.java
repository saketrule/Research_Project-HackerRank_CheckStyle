import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Solution 
{
    public static void main(String[] args) throws IOException
    {      
        int m,n,r;
        
        Set<Integer> set = new HashSet<>();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        m = Integer.valueOf(arr[0]);
        n = Integer.valueOf(arr[1]);
        r = Integer.valueOf(arr[2]);
        int[][] x = new int[m][n];
        
        for(int i = 0; i < m; i++)
        {
            int j = 0;
            for(String s : br.readLine().split(" "))
            {
                x[i][j] = Integer.valueOf(s);
                
                if(set.size() < 2)
                    set.add(x[i][j]);
                j++;
            }
        }
        
        if(set.size() == 1) //all elements are same
        {
            printMatrix(x);
            return;
        }
        
        rotateMatrix(x, r);
        printMatrix(x);
    }
    
    private static void rotateMatrix(int[][] x, int r)
    {  
        int ud, rl; //swaps needed (up/down, right/left)
        int t1,t2; //temp vars
        int i = -1, j = -1;
        int m = x.length + 2;
        int n = x[0].length + 2;
        
        for(;;)
        {
            i++;
            j++;

            m = m - 2;
            n = n - 2;
            ud = m-1;
            rl = n-1;
            if(m <= 1 || n <= 1)
                break;
            //circular rotation
            int rotsFor360 = ud*2 + rl*2; //how many rotations are needed
            //for full circle of current layer
            int rots = r;
            while(rots >= rotsFor360 )
                rots -= rotsFor360;

            for(int rotation = 0; rotation < rots; rotation++)
            {
                t1 = x[i][j];
                for(int k = 0; k < ud; k++) //swaps down
                {
                    t2 = x[i+1][j];
                    x[i+1][j] = t1;
                    t1 = t2;
                    i++;
                }

                for(int k = 0; k < rl; k++) //swaps right
                {
                    t2 = x[i][j+1];
                    x[i][j+1] = t1;
                    t1 = t2;
                    j++;
                }

                for(int k = 0; k < ud; k++) //swaps up
                {
                    t2 = x[i-1][j];
                    x[i-1][j] = t1;
                    t1 = t2;
                    i--;
                }

                for(int k = 0; k < rl; k++) //swaps left
                {
                    t2 = x[i][j-1];
                    x[i][j-1] = t1;
                    t1 = t2;
                    j--;
                }
            } 
        }
    }
    
    private static void printMatrix(int[][] x)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < x.length; i++)
        {
            for(int j = 0; j < x[0].length; j++)
            {
                sb.append(x[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}