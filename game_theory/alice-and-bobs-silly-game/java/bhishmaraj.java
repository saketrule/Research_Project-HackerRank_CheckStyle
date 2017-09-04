import java.util.*;
import java.io.*;
public class SillyGame
{
 
 
 /************************ SOLUTION STARTS HERE ***********************/
 
 
 public static boolean[] isPrimeArray(int N) // Sieve of Erathanoses
 {
  boolean num[] = new boolean[N + 1];
  Arrays.fill(num, true);
  num[1] = num[0]=  false;
  for (int i = 2; i * i <= N; i++)
   if (num[i])  // i is prime
    for (int j = i * i; j <= N; j += i)
     num[j] = false;
  
   
  return num;
 }
 
 
 private static void solve(FastScanner s1, PrintWriter out){
  
  int MAX = (int)(1e5);
  boolean isPrime[] = isPrimeArray(MAX);
  int prefixSum[] = new int[MAX + 1];
  for(int i=1;i<=MAX;i++)
   prefixSum[i] += (prefixSum[i - 1] + (isPrime[i] ? 1 : 0));
  
  int Q = s1.nextInt();
  while(Q-->0)
   out.println(prefixSum[s1.nextInt()] % 2 == 1 ? "Alice" : "Bob");
 }
 
 
 
 /************************ SOLUTION ENDS HERE ************************/
 
 
 
 
 
 /************************ TEMPLATE STARTS HERE *********************/
 
 public static void main(String []args) throws IOException {
  FastScanner in  = new FastScanner(System.in);
  PrintWriter out = 
    new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
  solve(in, out);
  in.close();
  out.close();
 }    
 
 static class FastScanner{
  BufferedReader reader;
  StringTokenizer st;
  FastScanner(InputStream stream){reader=new BufferedReader(new InputStreamReader(stream));st=null;} 
  String next()
  {while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}      
  st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
  String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}         
  int    nextInt()   {return Integer.parseInt(next());}
  long   nextLong()  {return Long.parseLong(next());}  
  double nextDouble(){return Double.parseDouble(next());}
  char   nextChar()  {return next().charAt(0);}
  int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
  long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;} 
  int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}      
  long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}      
  void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}    
 }
 
 /************************ TEMPLATE ENDS HERE ************************/
}