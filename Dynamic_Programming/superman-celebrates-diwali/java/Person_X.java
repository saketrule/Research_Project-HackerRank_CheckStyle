import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{

    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int h = in.nextInt();
        int fall = in.nextInt();
        int[][] people = new int[n][h];
        for(int i=0;i<n;i++)
        {
            int numPeople = in.nextInt();
            for(int j=0;j<numPeople;j++)
            {
                int floor = in.nextInt()-1;
                people[i][floor]++;
            }
        }
        int[][] dp = new int[n][h];
        for(int i=0;i<n;i++)
            Arrays.fill(dp[i], -1);
        int max = 0;
        int[] otherDp = new int[h];
        Arrays.fill(otherDp, -1);
        for(int i=0;i<n;i++)
            max = Math.max(max, recurse(dp, people, h-1, fall, i, otherDp));
        System.out.println(max);
        /*for(int i=0;i<dp.length;i++)
        {
            for(int j=0;j<dp[i].length;j++)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }*/
    }
    public static int recurse(int[][] dp, int[][] people, int level, int fall, int curBuilding, int[] otherDp)
    {
        if(level<0)
            return 0;
        if(dp[curBuilding][level]!=-1)
            return dp[curBuilding][level];
        int max = 0;
        if(level-fall<0)
            return recurse(dp, people, level-1, fall, curBuilding, otherDp)+people[curBuilding][level];
        if(otherDp[level-fall]!=-1)
            max = otherDp[level-fall];
        else
        {
            for(int i=0;i<people.length;i++)
            {
                max = Math.max(max, recurse(dp, people, level-fall, fall, i, otherDp));
            }
            otherDp[level-fall]=max;
        }
        max = Math.max(max, recurse(dp, people, level-1, fall, curBuilding, otherDp));
        max+=people[curBuilding][level];
        dp[curBuilding][level]=max;
        return max;
    }
}