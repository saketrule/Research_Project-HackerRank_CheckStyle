/*
 * Code Author: Akshay Miterani
 * DA-IICT
 */
import java.io.*;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class MainA {

 static double eps=(double)1e-6;
 static long mod=(long)1e9+7;
 static final long INF = Long.MAX_VALUE / 100;
 public static void main(String args[]) throws FileNotFoundException{
  InputReader in = new InputReader(System.in);
  OutputStream outputStream = System.out;
  PrintWriter out = new PrintWriter(outputStream);
  //----------------My Code------------------
  int size=100005;
  boolean s[]=new boolean[size];
  for(int i=2;i<size;i++){
   if(!s[i]){
    for(int j=2;j*i<size;j++){
     s[i*j]=true;
    }
   }
  }
  long pre[]=new long[size];
  for(int i=2;i<size;i++){
   pre[i]=pre[i-1];
   if(!s[i])
    pre[i]++;
  }
  int t=in.nextInt();
  while(t-->0){
   int n=in.nextInt();
   out.println(pre[n]%2==0?"Bob":"Alice");
  }
  out.close();
  //----------------The End------------------

 }
 static class Pair implements Comparable<Pair>{
  int x;
  int y;

  Pair(int xx,int yy){
   x=xx;
   y=yy;
  }
  @Override
  public int compareTo(Pair o) {
   if(Long.compare(this.x, o.x)!=0)
    return Long.compare(this.x, o.x);
   else
    return Long.compare(this.y, o.y);
  }
 }
 public static void debug(Object... o) {
  System.out.println(Arrays.deepToString(o));
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