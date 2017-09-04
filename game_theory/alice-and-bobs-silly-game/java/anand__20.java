import java.io.*;
import java.util.*;
public final class hack_dec_b
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
 static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
 static Random rnd=new Random();
 static int[] arr,pre;
 static int maxn=(int)(1e5+3);
 
 static void mark(int num)
 {
  int i=2,elem;
  while((elem=(num*i))<maxn)
  {
   arr[elem]=1;i++;
  }
 }
 
 static void build()
 {
  arr=new int[maxn];pre=new int[maxn];
  for(int i=2;i<maxn;i++)
  {
   if(arr[i]==0)
   {
    mark(i);pre[i]=1;
   }
  }
  for(int i=1;i<maxn;i++)
  {
   pre[i]=pre[i-1]+pre[i];
  }
 }
 
    public static void main(String args[]) throws Exception
    {
  build();int t=sc.nextInt();
  while(t>0)
  {
   int n=sc.nextInt();out.println(pre[n]%2==0?"Bob":"Alice");
   t--;
  }
  out.close();
    }
}
class FastScanner
{
    BufferedReader in;
    StringTokenizer st;

    public FastScanner(BufferedReader in) {
        this.in = in;
    }
 
    public String nextToken() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine());
        }
        return st.nextToken();
    }
 
 public String next() throws Exception {
  return nextToken().toString();
 }
 
    public int nextInt() throws Exception {
        return Integer.parseInt(nextToken());
    }

    public long nextLong() throws Exception {
        return Long.parseLong(nextToken());
    }

    public double nextDouble() throws Exception {
        return Double.parseDouble(nextToken());
    }
}