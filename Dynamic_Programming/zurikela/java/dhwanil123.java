//package CodeSprint;
import java.io.*;
import java.math.*;
import java.util.*;
import java.util.*;

public class q7 {

 static final int mod = (int) 1e9 + 7;
 static int[] val;
 static boolean[] ac;
 static int[] par;
 static int n;
 static int anstmp[];
 static int ansnot[]; 
 static int ans1[];
 static ArrayList<Integer> next[];
 public static void main(String ars[]) {
  FasterScanner s = new FasterScanner();
  int q=s.nextInt();
  n=0;
  val=new int[q];
  ac=new boolean[q];
  anstmp=new int[q];
  ansnot=new int[q];
  next = new ArrayList[q];
  par = new int[q]; 
  ans1=new int[q];
  for(int h=0;h<q;h++)
  {
   String st=s.nextLine();
   String[] in = st.split(" ");
   if(in[0].equals("A")){
    val[n]=Integer.parseInt(in[1]);
    next[n]=new ArrayList<>();
    ac[n]=true;
    par[n++]=-1;
   }
   if(in[0].equals("B")){
    int x=Integer.parseInt(in[1])-1;
    int y=Integer.parseInt(in[2])-1;
    if(ac[x] && ac[y]){
     next[x].add(y);
     par[y]=x;
    }
   }
   if(in[0].equals("C")){
    int z=Integer.parseInt(in[1])-1;
    while(par[z]!=-1)
     z=par[z];
    int ans=cal(z)[1];
    val[n]=ans;
    next[n]=new ArrayList<>();
    ac[n]=true;
    par[n++]=-1;
   }
  }
  int l=0;
  while(true)
  {
   ans1[l]=par[l]-val[n];
   
   if(l==0)
    break;
   l++;
  }
  int ans=0;
  for(int i=0;i<n;i++){
   if(ac[i]){
    int z=i;
    while(par[z]!=-1)
     z=par[z];
    ans+=cal(z)[1];
   }
  }
  System.out.println(ans);
 }
 
 
 
 
 
 public static long gcd(long a, long b) 
 {

  if(a==b)
   return a;
  if(a==0)
   return b;
  if(b==0)
   return a;

  if(a>b)
   return gcd(b,a%b);
  else
   return gcd(a,b%a);
 }

 public static long modPow( long a, long b, long mod )
    {
     long res=1,pow=a;
     while( b>0 )
     {
      if( (b&1)==1 )
      {
       res = (pow*res)%mod;
      }
      pow = (pow*pow)%mod;
      b=b/2;
     }
     return res;
    }
 
 static class nck
 {
  public static long ncr[][];
  
  public nck(long z,long p)//z==maxvalue  p==mod
  {
   generateNCR(z, p);
  }
  
     private static void generateNCR( long z, long p )
     {
      ncr = new long[(int)z+1][(int)z+1];
      ncr[0][0] = 1;
      for(int i=1; i<=z; i++)
      {
       ncr[i][0] = 1;
       ncr[0][i] = 0;
      }
         for(int i=1; i<=z; i++)
         {
             for(int j=1; j<=z; j++)
             {
                 ncr[i][j] = (ncr[i-1][j] + ncr[i-1][j-1]) % p;
             }
         }
     }

     private static long lucas(long n, long k, long p) 
     {
     
         long ans = 1;
         
         while(n>0)
         {
             int N = (int)(n%p);
             int K = (int)(k%p);
             if(K>N)
                 return 0;
             ans *= (ncr[N][K] );
             n /= p;
             k /= p;
         }
         return ans % p;
  
    }
 }
 
 
 

 static int[] cal(int z){
  // 0=not take
  // 1=take;
  int[] ans = new int[2];
  ans[0]=0;
  ans[1]=val[z];
  
  for(int x:next[z]){
   int temp[] = cal(x);
   ans[1]+=temp[0];
   ans[0]+=temp[1];
  }
  ans[1]=Math.max(ans[0], ans[1]);
  ac[z]=false;
  return ans;
 }
 

 
 
 
 
 static class SegTree
 {
  static int seg[];
  static int dp[];
  int n;
  int d;
  public SegTree(int[] arr,int defaul)
  {
   this.dp=arr;
   n=dp.length;
   seg=new int[4*arr.length];
   build(1,0,n);
   this.d=defaul;
  }

  public void build(int id,int l,int r){
   if(r-l<2){
    seg[id]=dp[l];
    return;
   }
   int mid=(l+r)/2;
   build(2*id,l,mid);
   build(2*id+1,mid,r);
   seg[id]=merge(seg[2*id],seg[2*id+1]);
  }

  public int query(int l,int r)
  {
   return givemin(1,l,r,0,n);
  }

  public  int givemin(int id,int x,int y,int l,int r){
   if(x<=l && y>=r){
    return seg[id];
   }
   if(y<=l || r<=x){
    return d;
   }
   int mid=(l+r)/2;
   return merge(givemin(2*id, x, y, l, mid),givemin(2*id+1, x, y, mid, r));
  }

  public int upd(int id,int value)
  {
   return update(1,id,value,0,n);
  }
  public  int update(int id,int p,int value,int l,int r){
   if(r-l<2){
    dp[p]=value;
    seg[id]=value;
    return value;
   }
   int mid=(l+r)/2;
   if(p<mid){
    update(2*id, p, value, l, mid);
    seg[id]=merge(seg[2*id], seg[2*id+1]);
    return seg[id];
   }
   else{
    update(2*id+1, p, value, mid,r);
    seg[id]=merge(seg[2*id+1], seg[2*id]);
    return seg[id];

   }
  }

  public int merge(int l,int r)
  {

   //set initial value
   return l+r;
  }
 }


 static class UF {

  private int[] parent;  // parent[i] = parent of i
  private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
  private int count;   
  int[] seg;
  int max;
  // number of components
  public UF(int N) {
   if (N < 0) throw new IllegalArgumentException();
   count = N;
   parent = new int[N];
   rank = new byte[N];
   seg=new int[N];
   for (int i = 0; i < N; i++) {
    parent[i] = i;
    rank[i] = 0;
    seg[i]=1;
   }
   max=1;
  }

  public int find(int p) {
   if (p < 0 || p >= parent.length) throw new IndexOutOfBoundsException();
   while (p != parent[p]) {
    parent[p] = parent[parent[p]];    // path compression by halving
    p = parent[p];
   }
   return p;
  }
  public int count() {
   return count;
  }

  public boolean connected(int p, int q) {
   return find(p) == find(q);
  }

  public boolean union(int p, int q) {
   //    System.out.println("uf "+p+" "+q);
   int rootP = find(p);
   int rootQ = find(q);
   if (rootP == rootQ) return false;

   // make root of smaller rank point to root of larger rank
   if      (rank[rootP] < rank[rootQ])
   {
    parent[rootP] = rootQ;
    seg[rootQ]+=seg[rootP];
    max=Math.max(seg[rootQ],max);
   }
   else if (rank[rootP] > rank[rootQ])
   {
    parent[rootQ] = rootP;
    seg[rootP]+=seg[rootQ];
    max=Math.max(seg[rootP],max);
   }
   else {
    parent[rootQ] = rootP;
    rank[rootP]++;
    seg[rootP]+=seg[rootQ];
    max=Math.max(seg[rootP],max);
   }
   count--;
   return true;
  }

 }



 static class SparseTable
 {
  int[][] st;
  public SparseTable(int[] h){
   int n=h.length;
   int MAX_LOG=(int)Math.ceil(Math.log(n)/Math.log(2))+1;
   st=new int[n][MAX_LOG];
   for (int i = 0; i < n; i++)
    st[i][0] = h[i];
   for (int j = 1; (1 << j) <= n; j++)
   {
    for (int i = 0;( i + (1 << j) - 1 )< n; i++)
    {
     st[i][j]=Math.max(st[i][j-1],st[i+(1<<(j-1))][j-1]);
    }
   }
  }
  public int query(int l,int r)
  {
   int x=(int )Math.floor((Math.log(r-l+1)/Math.log(2)));
   int ans=Math.max(st[l][x],st[r-(1<<x)+1][x]);
   return ans;
  }

 }


 
 

 

 
 

 
 

 
 public static class FasterScanner {
  private byte[] buf = new byte[1024];
  private int curChar;
  private int snumChars;

  public int read() {
   if (snumChars == -1)
    throw new InputMismatchException();
   if (curChar >= snumChars) {
    curChar = 0;
    try {
     snumChars = System.in.read(buf);
    } catch (IOException e) {
     throw new InputMismatchException();
    }
    if (snumChars <= 0)
     return -1;
   }
   return buf[curChar++];
  }

  public String nextLine() {
   int c = read();
   while (isSpaceChar(c))
    c = read();
   StringBuilder res = new StringBuilder();
   do {
    res.appendCodePoint(c);
    c = read();
   } while (!isEndOfLine(c));
   return res.toString();
  }

  public String nextString() {
   int c = read();
   while (isSpaceChar(c))
    c = read();
   StringBuilder res = new StringBuilder();
   do {
    res.appendCodePoint(c);
    c = read();
   } while (!isSpaceChar(c));
   return res.toString();
  }

  public long nextLong() {
   int c = read();
   while (isSpaceChar(c))
    c = read();
   int sgn = 1;
   if (c == '-') {
    sgn = -1;
    c = read();
   }
   long res = 0;
   do {
    if (c < '0' || c > '9')
     throw new InputMismatchException();
    res *= 10;
    res += c - '0';
    c = read();
   } while (!isSpaceChar(c));
   return res * sgn;
  }

  public int nextInt() {
   int c = read();
   while (isSpaceChar(c))
    c = read();
   int sgn = 1;
   if (c == '-') {
    sgn = -1;
    c = read();
   }
   int res = 0;
   do {
    if (c < '0' || c > '9')
     throw new InputMismatchException();
    res *= 10;
    res += c - '0';
    c = read();
   } while (!isSpaceChar(c));
   return res * sgn;
  }

  public int[] nextIntArray(int n) {
   int[] arr = new int[n];
   for (int i = 0; i < n; i++) {
    arr[i] = nextInt();
   }
   return arr;
  }

  public long[] nextLongArray(int n) {
   long[] arr = new long[n];
   for (int i = 0; i < n; i++) {
    arr[i] = nextLong();
   }
   return arr;
  }

  private boolean isSpaceChar(int c) {
   return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
  }

  private boolean isEndOfLine(int c) {
   return c == '\n' || c == '\r' || c == -1;
  }
 }
}