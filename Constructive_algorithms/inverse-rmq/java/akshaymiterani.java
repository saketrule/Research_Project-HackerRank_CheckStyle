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
 public static void main(String args[]){
  InputReader in = new InputReader(System.in);
  OutputStream outputStream = System.out;
  PrintWriter out = new PrintWriter(outputStream);
  //----------My Code----------
  int n=in.nextInt();
  long a[]=new long[2*n-1];
  TreeMap<Long,Integer> h=new TreeMap<>();
  for(int i=0;i<2*n-1;i++){
   a[i]=in.nextLong();
   if(h.containsKey(a[i]))
    h.put(a[i], h.get(a[i])+1);
   else
    h.put(a[i],1);
  }
  int lg=0;
  int nn=n;
  while(nn>0){
   nn/=2;
   lg++;
  }
  ArrayList<TreeSet<Long>> arr=new ArrayList<TreeSet<Long>>();
  for(int i=0;i<20;i++){
   arr.add(new TreeSet<Long>());
  }
  for(long x:h.keySet()){
   for(int i=0;i<20;i++){
    if(h.get(x)>=i){
     arr.get(i).add(x);
    }
   }
  }
  long ans[]=new long[2*n];
  Arrays.fill(ans, Integer.MIN_VALUE);
  int hona=1;
  for(int top=lg;top>=1;top--){
   if(arr.get(top).size()!=hona){
    System.out.println("NO");
    return;
   }
   hona*=2;
  }
  hona=1;
  for(int top=lg;top>=1;top--){
   for(int i=hona;i<hona*2;i++){
    if(ans[i]==Integer.MIN_VALUE){
     if(ans[i^1]==Integer.MIN_VALUE)
      ans[i]=arr.get(top).pollFirst();
     else{
      ans[i]=arr.get(top).ceiling(ans[i^1]);
      arr.get(top).remove(ans[i]);
     }
    }
    else{
     
    }
    if(i*2<2*n){
     ans[i*2]=ans[i];
     arr.get(top-1).remove(ans[i]);
    }
   }
   hona*=2;
  }
  out.println("YES");
  for(int i=1;i<2*n;i++){
   out.print(ans[i]+" ");
  }
  out.close();
  //---------------The End------------------

 }
 static class Pair implements Comparable<Pair>{
  long x;
  long y;
  int row;
  Pair(long xx,long yy,int r){
   x=xx;
   y=yy;
   row=r;
  }
  @Override
  public int compareTo(Pair o) {
   if(Long.compare(this.row, o.row)!=0){
    return Long.compare(this.row, o.row);
   }
   else{
    if(Long.compare(this.x, o.x)!=0)
     return Long.compare(this.x, o.x);
    else
     return Long.compare(this.y, o.y);
   }
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