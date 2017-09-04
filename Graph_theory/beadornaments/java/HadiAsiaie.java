import java.io.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Solution {

    void solve()throws Exception
    {

        Fact=new long[100];
        rFact=new long[100];
        Pow=new long[100][100];
        for(int i=0;i<100;i++)
        {
            Fact[i]=fact(i);
            rFact[i]=BigInteger.valueOf(Fact[i]).modInverse(BigInteger.valueOf(mod)).longValue();
            for(int j=0;j<100;j++)
                Pow[i][j]=BigInteger.valueOf(i).modPow(BigInteger.valueOf(j),BigInteger.valueOf(mod)).longValue();
        }
        int testCases=nextInt();
        for(int test=1;test<=testCases;test++)
        {
            solveTestCase(test);
        }

    }

    final static long mod=1000000007;
    long[]Fact;
    long[]rFact;
    long[][]Pow;

    private void solveTestCase(int testNumber)throws Exception{
        int n=nextInt();
        int[]a=new int[n];
        for(int i=0;i<n;i++)
            a[i]=nextInt();
        int[]deg=new int[n];
        int totalDeg=2*(n-1);
        long res=go(0,totalDeg,deg,a);
        System.out.println(res);

    }

    private long go(int at, int remainingDeg, int[] deg, int[] a) {
        if(at==a.length || a.length==1)
        {
            if(remainingDeg==0)
            {
                return count(deg,a);
            }
            else
                return 0;
        }
        long res=0;
        for(int x=1;x<=remainingDeg;x++)
        {
            deg[at]=x;
            res+=go(at+1,remainingDeg-x,deg,a);
            res%=mod;
        }
        return res;
    }

    private long count(int[] deg, int[] a) {
        long res=1;
        for(int i=0;i<deg.length;i++)
        {
            res=res* Pow[a[i]][deg[i]]%mod;
            int pow=Math.max(a[i]-2,0);
            res=res*Pow[a[i]][pow]%mod;
        }

        res=res*calc(deg)%mod;
        return res;
    }

    private long calc(int[] deg) {
        int n=deg.length;
        if(n==1)
            return 1;
        long res=Fact[n-2];
        for(int i=0;i<deg.length;i++)
        {

            res=res*rFact[deg[i]-1]%mod;
        }
        return res;

    }

    private long fact(int n) {
        long res=1;
        for(int i=0;i<n;i++)
            res=res*(i+1)%mod;
        return res;
    }


    ////////////
    BufferedReader reader;
    PrintWriter writer;
    StringTokenizer stk;
    void run()throws Exception
    {

        reader=new BufferedReader(new InputStreamReader(System.in));
        //reader=new BufferedReader(new FileReader("input.txt"));
        stk=null;
        writer=new PrintWriter(new PrintWriter(System.out));
        //writer=new PrintWriter(new FileWriter("output.txt"));
        solve();
        reader.close();
        writer.close();
    }
    int nextInt()throws Exception
    {
        return Integer.parseInt(nextToken());
    }

    long nextLong()throws Exception
    {
        return Long.parseLong(nextToken());

    }
    double nextDouble()throws Exception
    {
        return Double.parseDouble(nextToken());


    }

    String nextString()throws Exception
    {
        return nextToken();
    }
    String nextLine()throws Exception
    {
        return reader.readLine();
    }
    String nextToken()throws Exception
    {
        if(stk==null || !stk.hasMoreTokens())
        {
            stk=new StringTokenizer(nextLine());
            return nextToken();

        }
        return stk.nextToken();
    }

    public static void main(String[]args) throws Exception
    {
        new Solution().run();
    }








}