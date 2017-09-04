import java.util.*;
import java.io.*;

class Solution
{
 BufferedReader input;
 BufferedWriter out;
 StringTokenizer token;

 int N;
 int[] x,y;
 int[] dp,dp3;
 boolean[] ok;
 int[] member;
 int mod = 1000000007;

 int BitCount(int x)
 {
  int ret = 0;
  while(x > 0)
  {
   if( (x&1) != 0 ) ret++;
   x >>= 1;
  }
  return ret;
 }

 boolean collinear(int set)
 {
  int ctr = 0;
  for(int i = 0; set > 0; i++)
  {
   if( (set&1) != 0 )
    member[ctr++] = i;
   set >>= 1;
  }
  if(ctr <= 2)return true;
  int a = x[member[0]]-x[member[1]];
  int b = y[member[0]]-y[member[1]];
  for(int i = 2; i < ctr; i++)
  {
   int aa = x[member[0]]-x[member[i]];
   int bb = y[member[0]]-y[member[i]];
   if(aa*b != a*bb)return false;
  }
  return true;
 }

 String binary(int x)
 {
  String ret = "";
  for(int i = 0; i < N; i++)
  {
   if( ((x>>i)&1) == 0) ret = "0"+ret;
    else ret = "1"+ret;
  }
  return ret;
 }

 void solve() throws IOException
 {
  long qq = System.currentTimeMillis();
  input = new BufferedReader(new InputStreamReader(System.in));
  out = new BufferedWriter(new OutputStreamWriter(System.out));
  int T = nextInt();
  int twoMax = (1<<16);
  dp = new int[twoMax];
  x = new int[16];
  y = new int[16];
  ok = new boolean[twoMax];
  dp3 = new int[twoMax];
  member = new int[16];
  ArrayList<Integer> o;
  for(int t = 0; t < T; t++)
  {
   N = nextInt();
   int twoN = (1<<N);
   for(int i = 0; i < N; i++)
   {
    x[i] = nextInt();
    y[i] = nextInt();
   }
   o = new ArrayList<Integer>();
   for(int i = twoN-1; i > 0; i--)
   {
    ok[i] = false;
    if(collinear(i))
    {
     ok[i] = true;
     o.add(i);
    }
   }
   Arrays.fill(dp,-1);
   dp[0] = 0;
   dp3[0] = 1;
   int m = 0;
   for(int i = 0; i < o.size(); i++)
   {
    int ii = o.get(i);
    for(int j = m; j >= 0; j--)
    {
     if((ii&j) == 0 && dp[j] != -1)
     {
      m = Math.max(m,j|ii);
      if(dp[j|ii] == -1 || dp[j|ii] > 1+dp[j])
      {
       dp[j|ii] = 1+dp[j];
       dp3[j|ii] = (int)(((long)(dp[j]+1)*dp3[j])%mod);
      }
      else if(dp[j|ii] == 1+dp[j])
      {
       dp3[j|ii] += ((long)(dp[j]+1)*dp3[j])%mod;
       dp3[j|ii] %= mod;
      }
     }
    }
   }
   out.write(""+ dp[(twoN)-1] + " " + dp3[(twoN)-1]);
   out.newLine();
  }
  out.flush();
  out.close();
  input.close();

 }

 int nextInt() throws IOException
 {
  if(token == null || !token.hasMoreTokens())
   token = new StringTokenizer(input.readLine());
  return Integer.parseInt(token.nextToken());
 }

 Long nextLong() throws IOException
 {
  if(token == null || !token.hasMoreTokens())
   token = new StringTokenizer(input.readLine());
  return Long.parseLong(token.nextToken());
 }

 String next() throws IOException
 {
  if(token == null || !token.hasMoreTokens())
   token = new StringTokenizer(input.readLine());
  return token.nextToken();
 }

 public static void main(String[] args) throws Exception
 {
  new Solution().solve();
 }
}