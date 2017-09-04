import java.util.*;
import java.io.*;
import java.math.*;
 public class Main {
  public static long mod= (long) (1e9 +7);
  public static void main(String args[])
  {
   InputReader s= new InputReader(System.in);
   OutputStream outputStream= System.out;
   PrintWriter out= new PrintWriter(outputStream);
   int g= s.nextInt();
   while(g-->0){
    int n= s.nextInt();
    int a[]= new int[n];
    for(int i=0;i<n;i++){
     a[i]= s.nextInt();
    }
    int flag=0;
    for(int i=0;i<n;i++){
     if(a[i]!=1){
      flag=1;
     }
    }
    if(flag==1){
     int x=0;
     for(int i=0;i<n;i++){
      x^=a[i];
     }
     if(x==0) out.println("Second");
     else out.println("First");
    }
    else{
     int x=0;
     for(int i=0;i<n;i++){
      x^=a[i];
     }
     if(x==1) out.println("Second");
     else out.println("First");
    }
   }
   out.close();
  }
  
  static long combinations(long n,long r){  // O(r)
   if(n==r || r==0) return 1;
   r= Math.min(r, n-r);
   long ans=n;  // nC1=n;
   for(int i=1;i<r;i++){
    ans= (ans%mod)*((n-i)%mod)%mod;
    ans= (long) (((ans%mod)* (Math.pow((i+1), -1)%mod))%mod);
   }
   return ans%mod;
   
  }
  static long combinations1(long n,long r){   // O(r)
   if(n==r || r==0) return 1;
   r= Math.min(r, n-r);
   long ans=n;  // nC1=n;
   for(int i=1;i<r;i++){
    ans= ans*(n-i);
    ans= ans/(i+1);
   }
   return ans%mod;
   
  }
  static void catalan_numbers(int n) {
   long catalan[]= new long[n+1];
      catalan[1] = 1;
      for (int i = 2; i <= n; i++) {
          for (int j = 1; j <= i - 1; j++) {
              catalan[i] = catalan[i] + ((catalan[j]) * catalan[i - j]);
          }
      }
  }
  
  static ArrayList<Integer> primeFactors(int n)   // O(sqrt(n))
  {
      // Print the number of 2s that divide n
   ArrayList<Integer> arr= new ArrayList<>();
      while (n%2 == 0)
      {
          arr.add(2);
          n = n/2;
      }
   
      // n must be odd at this point.  So we can skip one element (Note i = i +2)
      for (int i = 3; i <= Math.sqrt(n); i = i+2)
      {
          // While i divides n, print i and divide n
          while (n%i == 0)
          {
              arr.add(i);
              n = n/i;
          }
      }
      // This condition is to handle the case when n is a prime number
      // greater than 2
      if (n > 2)
          arr.add(n);
      
      return arr;
  }
 
  public static long expo(long a, long b){
   if (b==0)
          return 1%mod;
      if (b==1)
          return a%mod;
      if (b==2)
          return ((a%mod)*(a%mod))%mod;
 
      if (b%2==0){
              return expo(expo(a%mod,(b%mod)/2)%mod,2%mod)%mod;
      }
      else{
          return (a%mod)*(expo(expo(a%mod,(b-1)%mod/2)%mod,2%mod)%mod)%mod;
      }
  }
  static class Pair implements Comparable<Pair>
  {
   long f;
   String s;
   Pair(long ii, String cc)
   {
    f=ii;
    s=cc;
   }
   
   public int compareTo(Pair o) 
   {
    return Long.compare(this.f, o.f);
   }
   
  }
  
  public static int[] sieve(int N){    // O(n*log(logn))
   int arr[]= new int[N+1];
   for(int i=2;i<Math.sqrt(N);i++){
    if(arr[i]==0){
     for(int j= i*i;j<= N;j= j+i){
      arr[j]=1;
     }
    }
   }
   return arr;
   // All the i for which arr[i]==0 are prime numbers.
  }
  static long gcd(long a,long b){       // O(logn)
   if(b==0) return a;
   return gcd(b,a%b);
  }
  static class InputReader {
 
      public BufferedReader reader;
      public StringTokenizer tokenizer;
  
      public InputReader(InputStream inputstream) {
        reader = new BufferedReader(new InputStreamReader(inputstream));
        tokenizer = null;
      }
      
      public String nextLine(){
       String fullLine=null;
       while (tokenizer == null || !tokenizer.hasMoreTokens()) {
              try {
                fullLine=reader.readLine();
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              return fullLine;
            }
            return fullLine;
      }
   public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
          try {
            tokenizer = new StringTokenizer(reader.readLine());
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
        return tokenizer.nextToken();
      }
   public long nextLong() {
         return Long.parseLong(next());
       }
      public int nextInt() {
        return Integer.parseInt(next());
      }
    }
 }