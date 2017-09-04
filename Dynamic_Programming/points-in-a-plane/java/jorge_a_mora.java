import java.util.*;
class Solution
{
 public static void main(String[] args) 
 {
  final int mod = 1000000007;
  Scanner sc = new Scanner(System.in);
  int T = sc.nextInt();
  int twoMax = (1<<16);
  int[] dp = new int[twoMax];
  int[] x = new int[16];
  int[] y = new int[16];
  boolean[] ok = new boolean[twoMax];
  int[] dp3 = new int[twoMax];
  int[] member = new int[16];
  ArrayList<Integer> o;
  for(int t = 0; t < T; t++)
  {
   int N = sc.nextInt();
   int twoN = (1<<N);
   for(int i = 0; i < N; i++)
   {
    x[i] = sc.nextInt();
    y[i] = sc.nextInt();
   }
   o = new ArrayList<Integer>();
   for(int i = twoN-1; i > 0; i--)
   {
    ok[i] = false;
    if(isCollinear(i, member, x, y))
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
   System.out.println(""+ dp[(twoN)-1] + " " + dp3[(twoN)-1]);
   
  }
   sc.close();
 }
 static boolean isCollinear(int set, int [] member, int [] x, int [] y)
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
}