/* Enter your code here. Read input from STDIN. Print output to STDOUT */


import java.io.*;
import java.util.*;


public class Solution
{
    public static void main(String[] args) throws Exception
    {
     
    // System.out.println("hahahaha");
     BufferedReader in=new BufferedReader( new InputStreamReader(System.in));   
    
        int T = Integer.parseInt(in.readLine());

        int MAXN = 16;
        long MOD = 1000000007;

        Result[] dp = new Result[(1 << MAXN)];

      


        Map<Integer,List<Integer>>[] dics = new Map[MAXN];

        for (int i = 0; i < dics.length; i++)
        {
            dics[i] = new TreeMap<Integer, List<Integer>>();
        }

        HashSet<Integer> edgeSet = new HashSet<Integer>();
        HashSet<Integer> edgeSetAll = new HashSet<Integer>();

        for (int t = 0; t < T; t++)
        {
            int N = Integer.parseInt(in.readLine());

            int[] x = new int[N];
            int[] y = new int[N];

           

            for (int i = 0; i < N; i++)
            {
                String[] temp = in.readLine().split(" ");
                x[i] = Integer.parseInt(temp[0]);
                y[i] = Integer.parseInt(temp[1]);
            }

            for (int i = 0; i < N; i++)
                dics[i].clear();

            for (int i = 0; i < N; i++)
                for (int j = 0; j < N && j != i; j++)
                {
                    int key = formKey(x[j] - x[i], y[j] - y[i]);

                    if (dics[i].containsKey(key))
                        dics[i].get(key).add(j);
                    else
                    {
                        List<Integer> s = new ArrayList<Integer>();
                        s.add(j);
                        dics[i].put(key, s);
                    }

                }

            edgeSet.clear();
            for (int i = 0; i < N; i++)
                for(int key : dics[i].keySet())
                {
                    int num=(1<<i);

                    for (int id : dics[i].get(key))
                        num = num + (1 << id);

                    if (!edgeSet.contains(num))
                        edgeSet.add(num);
                }

            
            
            edgeSetAll.clear();
            List<Integer> temp =new ArrayList<Integer>();
            
            for (Integer edge : edgeSet)                
            {
                temp.clear();
                
                for(int i=0;i<N;i++)
                 if((edge&(1<<i))!=0)
                  temp.add(i);
                
                for(int isq=0;isq<(1<<temp.size());isq++)
                {
                 int t_edge=0;
                
                 for(int i=0;i<temp.size();i++)
                 {
                  if((isq&(1<<i))!=0)
                   t_edge+=(1<<temp.get(i));
                 }       
                 
                 if(t_edge!=0&&!edgeSetAll.contains(t_edge))
                     edgeSetAll.add(t_edge);
                }
                
               
            }
            
            List<Integer> edges=new ArrayList<Integer>();
            
            for (Integer edge : edgeSetAll)                
            {
             edges.add(edge);
            }
           // for (int i = 0; i < N; i++)
            //    edges.add((1<<i));
               
          
            for (int i = 0; i < (1 << N); i++)
                dp[i] = null;

            Result result = getResult(edges,dp,0,N,MOD);

            System.out.println(result.min + " " + result.min_sum);


        }
  
    }

    public static Result getResult(List<Integer> edges,Result[] dp,int matched,int N,long MOD)
    {

//        System.out.print("State:");
//        for (int i = 0; i < N; i++)
//        {
//            if ((matched & (1 << i)) == 0)
//              System.out.print(0);
//            else
//              System.out.print(1);
//          
//        }
//        System.out.println();

//        for (Integer edge : edges)
//        {
//
//         System.out.print("Edge=");
//            for (int i = 0; i < N; i++)
//            {
//                if ((edge & (1 << i)) == 0)
//                  System.out.print(0);
//                else
//                  System.out.print(1);
//
//            }
//            System.out.println();
//        }
//
//        System.out.println();

         if (dp[matched] != null)
             return dp[matched];


        if (matched == (1 << N) - 1)
        {

            dp[matched] = new Result(0,1);

            return dp[matched];
        }

        Result result = null;

        HashSet<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < edges.size(); i++)
        {
            if ((edges.get(i) | matched) == matched)
                continue;

            set.clear();

            int next_m = (edges.get(i) | matched);

            List<Integer> next_edges = new ArrayList<Integer>();

            for (int j = 0; j < edges.size(); j++)
            {

                int t_edge = (((1 << N) -1 - next_m) & edges.get(j));

                if (t_edge != 0 && !set.contains(t_edge))
                    set.add(t_edge);
                    
            }

            for (int edge : set)
                next_edges.add(edge);

            Result tr = getResult(next_edges, dp, next_m, N, MOD);
            
            if (result == null)
                result = new Result(tr.min + 1, tr.min_sum);
            else if (result.min == tr.min + 1)
            {
                result.min_sum = (result.min_sum + tr.min_sum) % MOD;
            }
            else if (result.min > tr.min + 1)
            {
                result = new Result(tr.min + 1, tr.min_sum);
            }

        }

        dp[matched] = result;

//        System.out.print("State:");
//        for (int i = 0; i < N; i++)
//        {
//            if ((matched & (1 << i)) == 0)
//              System.out.print(0);
//            else
//              System.out.print(1);
//
//        }
//
//        System.out.println(" ="+result.min+" "+result.min_sum);


        return dp[matched];

    }

    public static int gcd(int a,int b)
    {
        a=a>0?a:-a;
        b=b>0?b:-b;

        while (a % b != 0)
        {
            int temp = b;

            b = a % temp;

            a = temp;
        }

        return b;
    }

    public static int formKey(int x, int y)
    {


        if (x == 0)
            return Integer.MAX_VALUE;

        if (y == 0)
            return 0;

        int tag = 1;

        if (x < 0)
            tag = -tag;

        if (y < 0)
            tag = -tag;


        x = x > 0 ? x : -x;
        y = y > 0 ? y : -y;

        int g = gcd(x, y);

        x = x / g;
        y = y / g;

        return tag * (y * 1000 + x);

    }
}

class point
{
    public int x;
    public int y;
}

class Result
{
    public int min;
    public long min_sum;

    public Result(int min, long min_sum)
    {
        this.min = min;
        this.min_sum = min_sum;
    }
}