import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
//import Queue.node;
class TestClass {
 private static InputStream stream;
 private static byte[] buf = new byte[1024];
 private static int curChar;
 private static int numChars;
 private static SpaceCharFilter filter;
 private static PrintWriter pw;
 static int di = 0,gi = 0;
 public static class Heap {
  long arr[][];
  int num;
  Heap(int a){
   arr = new long[a][3];
   num = 0;
  }
  public void swap(long arr[][],int i,int j){
   long t = arr[i][0];
   arr[i][0] = arr[j][0];
   arr[j][0] = t;
   t = arr[i][1];
   arr[i][1] = arr[j][1];
   arr[j][1] = t;
   t = arr[i][2];
   arr[i][2] = arr[j][2];
   arr[j][2] = t;
  }
  public void Minheap(int a){
   while(a>0&&arr[(a-1)/2][0]>arr[a][0]){
    swap(arr,(a-1)/2,a);
    a = (a-1)/2;
   }
  }
  public void Minheapify(int a){
   int largest = a;
   if(2*a+1<num&&arr[largest][0]>arr[2*a+1][0])
    largest = 2*a+1;
   if(2*a+2<num&&arr[largest][0]>arr[2*a+2][0])
    largest = 2*a+2;
   if(largest!=a){
    swap(arr,a,largest);
    Minheapify(largest);
   }
  }
  public void insert(long a,int b,int c){
   arr[num++][0] = a;
   arr[num-1][1] = b;
   arr[num-1][2] = c;
   Minheap(num-1);
  }
  public void deleteAtIndex(int a){
   long pi = arr[a][0];
   di = (int)arr[a][1];
   gi = (int)arr[a][2];
   arr[a][0] = arr[num-1][0];
   arr[a][1] = arr[num-1][1];
   arr[a][2] = arr[num-1][2];
   num--;
   if(arr[a][0]>pi)
    Minheapify(a);
   else
    Minheap(a);
  }
  public long getMin(){
   long pi = arr[0][0];
   di = (int)arr[0][1];
   gi = (int)arr[0][2];
   deleteAtIndex(0);
   return pi;
  }
  public void deleteAtValue(int a){
   for(int i=0;i<num;i++){
    if(arr[i][0]==a){
     deleteAtIndex(i);
     break;
    }
   }
  }
 }
 public static class Queue{
  private class node{
   int val;
   node next;
   node(int a){
    val = a;
    next = null;
   }
  }
  node head,tail;
  Queue(){
   head = null;
   tail = null;
  }
  public void EnQueue(int a){
   if(head==null){
    node p = new node(a);
    head = p;
    tail = p;
   }
   else{
    node p = new node(a);
    tail.next = p;
    tail = p;
   }
  }
  public int DeQueue(){
   int a = head.val;
   head = head.next;
   return a;
  }
  public boolean isEmpty(){
   return head==null;
  }
 }
 public static void Merge(int a[],int p,int r){
        if(p<r){
            int q = (p+r)/2;
            Merge(a,p,q);
            Merge(a,q+1,r);
            Merge_Array(a,p,q,r);
        }
    }
    public static void Merge_Array(int a[],int p,int q,int r){
        int b[] = new int[q-p+1];
        int c[] = new int[r-q];
        for(int i=0;i<b.length;i++)
            b[i] = a[p+i];
        for(int i=0;i<c.length;i++)
            c[i] = a[q+i+1];
        int i = 0,j = 0;
        for(int k=p;k<=r;k++){
            if(i==b.length){
                a[k] = c[j];
                j++;
            }
            else if(j==c.length){
                a[k] = b[i];
                i++;
            }
            else if(b[i]<c[j]){
                a[k] = b[i];
                i++;
            }
            else{
                a[k] = c[j];
                j++;
            }
        }
    }
    public static void Merge(int a[][],int p,int r){
        if(p<r){
            int q = (p+r)/2;
            Merge(a,p,q);
            Merge(a,q+1,r);
            Merge_Array(a,p,q,r);
        }
    }
    public static void Merge_Array(int a[][],int p,int q,int r){
        int b[][] = new int[q-p+1][2];
        int c[][] = new int[r-q][2];
        for(int i=0;i<b.length;i++){
            b[i][0] = a[p+i][0];
            b[i][1] = a[p+i][1];
        }
        for(int i=0;i<c.length;i++){
            c[i][0] = a[q+i+1][0];
            c[i][1] = a[q+i+1][1];
        }
        int i = 0,j = 0;
        for(int k=p;k<=r;k++){
            if(i==b.length){
                a[k][0] = c[j][0];
                a[k][1] = c[j][1];
                j++;
            }
            else if(j==c.length){
                a[k][0] = b[i][0];
                a[k][1] = b[i][1];
                i++;
            }
            else if(b[i][0]<c[j][0]||(b[i][0]==c[j][0]&&b[i][1]<c[j][0])){
                a[k][0] = b[i][0];
                a[k][1] = b[i][1];
                i++;
            }
            else{
                a[k][0] = c[j][0];
                a[k][1] = c[j][1];
                j++;
            }
        }
    }
    public static int pow(int x,int y,long m){
     if(y==0)
      return 1;
     int k = pow(x,y/2,m);
     if(y%2==0)
      return (int)((k*(long)k)%m);
     else
      return (int)((((k*(long)k)%m)*x)%m);
    }
    static int Inversex = 0,Inversey = 0;
    public static void InverseModulo(int a,int m){
     if(m==0){
      Inversex = 1;
      Inversey = 0;
     }
     else{
      InverseModulo(m,a%m);
      int temp = Inversex;
      Inversex = Inversey;
      Inversey = temp - (a/m)*Inversey;
     }
    }
    static long mod1 = 1000000007;
    static long mod2 = 1000000009;
 public static long gcd(long a,long b){
  if(a%b==0)
   return b;
  return gcd(b,a%b);
 }
 public static boolean isPrime(long a){
  if(a==1)
   return false;
  else if(a==2||a==3)
   return true;
  for(long i=2;i<=Math.sqrt(a);i++)
   if(a%i==0)
    return true;
  return false;
 }
 public static double distance(int a,int b,int x,int y){
  return Math.sqrt(((long)(a-x)*(long)(a-x))+((long)(b-y)*(long)(b-y)));
 }
 public static void Hash(String a,long b[],long mod){
  for(int i=0;i<a.length();i++)
   b[i+1] = ((26*(long)b[i] + a.charAt(i)+1-'a')%(int)mod);
 }
 public static long getHash(long b[],int a,int c,long q,long mod){
  long nm = b[c];
  long p = b[a-1];
  p = ((p*(long)q)%mod);
  return (((nm+mod)-p)%mod);
 }
 public static class pair{
  long u,v;
 }
 public static int getPrime(int n){
  int i = 2;
  for(i=2;i<=Math.sqrt(n);i++)
   if(n%i==0)
    return n/i;
  return 1;
 }
 private static void soln() {
  int dp[] = new int[1000001];
  dp[2] = 1;
  dp[3] = 1;
  for(int i=4;i<1000001;i++)
   dp[i] = dp[getPrime(i)]+1;
  int t = nextInt();
  while(t-->0){
   int n = nextInt();
   int max = 0;
   while(n-->0){
    max^=dp[nextInt()];
   }
   if(max==0)
    pw.println("2");
   else
    pw.println("1");
  }
 }
 public static void main(String[] args) {
  InputReader(System.in);
  pw = new PrintWriter(System.out);
  soln();
  pw.close();
 }
 // To Get Input
 // Some Buffer Methods
 public static void InputReader(InputStream stream1) {
  stream = stream1;
 }
 private static boolean isWhitespace(int c) {
  return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
 }
 private static boolean isEndOfLine(int c) {
  return c == '\n' || c == '\r' || c == -1;
 }
 private static int read() {
  if (numChars == -1)
   throw new InputMismatchException();
  if (curChar >= numChars) {
   curChar = 0;
   try {
    numChars = stream.read(buf);
   } catch (IOException e) {
    throw new InputMismatchException();
   }
   if (numChars <= 0)
    return -1;
  }
  return buf[curChar++];
 }
 private static int nextInt() {
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
 private static long nextLong() {
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
 private static String nextToken() {
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
 private static String nextLine() {
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
 private static int[] nextIntArray(int n) {
  int[] arr = new int[n];
  for (int i = 0; i < n; i++)
   arr[i] = nextInt();
  return arr;
 }
 private static long[] nextLongArray(int n) {
  long[] arr = new long[n];
  for (int i = 0; i < n; i++)
   arr[i] = nextLong();
  return arr;
 }
 private static void pArray(int[] arr) {
  for (int i = 0; i < arr.length; i++)
   System.out.print(arr[i] + " ");
  System.out.println();
  return;
 }
 private static void pArray(long[] arr) {
  for (int i = 0; i < arr.length; i++)
   System.out.print(arr[i] + " ");
  System.out.println();
  return;
 }
 private static boolean isSpaceChar(int c) {
  if (filter != null)
   return filter.isSpaceChar(c);
  return isWhitespace(c);
 }
 private interface SpaceCharFilter {
  public boolean isSpaceChar(int ch);
 }
}