/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.BigInteger;

class Solution
{
    // dp[i] = min(dp[i-j]) where j = 1 to K+1
    // final result = min(dp[n-j])
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        String s=br.readLine();
        String[] si = s.split(" ");
        int N = Integer.parseInt(si[0]);
        int K = Integer.parseInt(si[1]);
        ArrayList<BigInteger> num = new ArrayList<BigInteger>();
        BigInteger[] P = new BigInteger[N];
        for (int i = 0; i < N; i++)
        {
            String pr = br.readLine();
            P[i] = new BigInteger(pr);
        }
        solve(N,K,P);
    }

    public static TreeMap<BigInteger,Integer> PQ = new TreeMap<BigInteger,Integer>();
    public static void solve(int N, int K, BigInteger[] P){
        BigInteger[] DP = new BigInteger[N];
        BigInteger sum = new BigInteger("0");

        for(int i=0;i<N;i++){
            sum = sum.add(P[i]);
            DP[i] = new BigInteger(P[i].toString());
            if(i>K){
                BigInteger b = PQ.firstKey();
                DP[i] = DP[i].add(b);
                remove(DP[i-K-1]);
            }
            put(DP[i]);
            //System.out.println(PQ);
        }
        System.out.println(sum.subtract(PQ.firstKey()));
    }

    public static void remove(BigInteger b){
        Integer v=PQ.remove(b);    
        if(v!=null && v!=1)PQ.put(b,v-1);
    }

    public static void put(BigInteger b){
        Integer v=PQ.get(b);    
        if(v==null)v=0;
        PQ.put(b,v+1);
    }
}