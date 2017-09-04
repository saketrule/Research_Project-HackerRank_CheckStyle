import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Set;

public class Main {
 static long mod=(long)(1e9+7);
 static int[] prime;
 static int[] div;
 public static void main(String args[]) throws IOException{
  new Thread(null,new Runnable(){
   public void run(){
    new Main().run();
   }
  },"1",1<<23).start();

 }

 protected void run() {
  // TODO Auto-generated method stub
  InputReader in   = new InputReader(System.in);
  OutputWriter out = new OutputWriter(System.out);
  int n1=in.readInt();
  int n=2*n1-1;
  int a[]=new int[n];
  int ans[]=new int[n];
  Arrays.fill(ans, Integer.MAX_VALUE);
  HashSet<Integer> hs=new HashSet();
  HashMap<Integer,Integer> hm=new HashMap();
  for(int i=0;i<n;i++){
   a[i]=in.readInt();
   hs.add(a[i]);
   if(!hm.containsKey(a[i]))
    hm.put(a[i], 0);
   hm.put(a[i], hm.get(a[i])+1);
  }
  if(hs.size()!=n1){
   out.printLine("NO");
  }
  else{
   /* ArrayList<Integer> al=new ArrayList();
   hs=new HashSet();
   for(int i=0;i<n;i++){
    if(!hs.contains(a[i])){
     hs.add(a[i]);
     al.add(a[i]);
    }
   }
   Collections.sort(al);
   build(ans,al,0,0,n1-1);
   for(int i=0;i<n;i++){
    int x=0;
    if(hm.containsKey(ans[i])){
     x=hm.get(ans[i]);
     if(x==1)
      hm.remove(ans[i]);
     else
      hm.put(ans[i], x-1);
    }
    else{
     break;
    }
   }

   if(hm.size()==0){
    out.printLine("YES");
    for(int i=0;i<n;i++)
     out.print(ans[i]+" ");
    out.printLine();
   }
   else
    out.printLine("NO");*/

   ArrayList<Integer> al[]=new ArrayList[n1];
   for(int i=0;i<n1;i++)
    al[i]=new ArrayList();
   int len=Integer.numberOfTrailingZeros(n1)+1;
   //System.out.println(len);
   int x=1;
   int z=0;
   int y=0;

   Set<java.util.Map.Entry<Integer, Integer>> mapEntries = hm.entrySet();
   ArrayList<java.util.Map.Entry<Integer, Integer>> list=new ArrayList<java.util.Map.Entry<Integer, Integer>>(mapEntries);
  // System.out.println(mapEntries);
   Collections.sort(list, new Comparator<java.util.Map.Entry<Integer, Integer>>(){

    @Override
    public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
     // TODO Auto-generated method stub
     return o2.getValue().compareTo(o1.getValue());
    }
    
   });
  // System.out.println(list);
   int real=len;
   for(java.util.Map.Entry<Integer, Integer> entry:list){
    //System.out.println(entry+" "+len+" "+x+" "+z);
    if(entry.getValue()==len){
     int w=entry.getKey();
     if(!al[real-len].contains(w))
      al[real-len].add(w);
     z++;
     if(x==z){
      len--;
      z=0;
      x=(int) Math.pow(2, y);
      y++;
     }
    }
    else{
    // System.out.println(entry+" "+len+" "+x+" "+z);
     out.printLine("NO");
     out.close();
     System.exit(0);
    }
   }

   x=1;
   z=y=0;
   for(int i=0;i<n1;i++){
    Collections.sort(al[i]);
   }
   
   int level=0;
   for(;level<=real-1;level++){
    for(int j=(int) Math.pow(2, level)-1;j<Math.pow(2, level+1)-1;j++){
     if(ans[j]==Integer.MAX_VALUE){
      ans[j]=al[level].get(0);
      al[level].remove(0);
      int m=2*j+1;
      while(m<n){
       ans[m]=ans[j];
       m=2*m+1;
      }
     }
    }
   }
   
   out.printLine("YES");
   for(int i=0;i<n;i++)
    out.print(ans[i]+" ");
   out.printLine();
  }
  out.flush();
  out.close();

 }

 private void build(int[] ans, ArrayList<Integer> al, int index, int start, int end) {
  if(start==end){
   ans[index]=al.get(start);
   return;
  }
  int mid= start+(end-start)/2;
  build(ans,al,2*index+1,start,mid);
  build(ans,al,2*index+2,mid+1,end);
  ans[index]=Math.min(ans[2*index+1], ans[2*index+2]);
 }

}


class InputReader {

 private InputStream stream;
 private byte[] buf = new byte[1024];
 private int curChar;
 private int numChars;
 private SpaceCharFilter filter;

 public InputReader(InputStream stream) {
  this.stream = stream;
 }

 public int read() {
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

 public int readInt() {
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

 public String readString() {
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

 public boolean isSpaceChar(int c) {
  if (filter != null)
   return filter.isSpaceChar(c);
  return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
 }

 public String next() {
  return readString();
 }

 public interface SpaceCharFilter {
  public boolean isSpaceChar(int ch);
 }
}

class OutputWriter {
 private final PrintWriter writer;

 public OutputWriter(OutputStream outputStream) {
  writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
 }

 public OutputWriter(Writer writer) {
  this.writer = new PrintWriter(writer);
 }

 public void print(Object...objects) {
  for (int i = 0; i < objects.length; i++) {
   if (i != 0)
    writer.print(' ');
   writer.print(objects[i]);
  }
 }

 public void printLine(Object...objects) {
  print(objects);
  writer.println();
 }

 public void close() {
  writer.close();
 }

 public void flush() {
  writer.flush();
 }

}

class IOUtils {

 public static int[] readIntArray(InputReader in, int size) {
  int[] array = new int[size];
  for (int i = 0; i < size; i++)
   array[i] = in.readInt();
  return array;
 }

} 