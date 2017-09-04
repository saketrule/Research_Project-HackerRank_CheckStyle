import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String args[]) throws NumberFormatException,IOException {
  
  Stdin in = new Stdin();
  PrintWriter out = new PrintWriter(System.out);
  int t=in.readInt();
  int n,m,x,y,s;
  HashSet<Integer>map[];
  int dis[];
  boolean isFirst;
  for(int i=0;i<t;i++){
   n=in.readInt();
   m=in.readInt();
   map=new HashSet[n];
   for(int j=0;j<map.length;j++)
    map[j]=new HashSet<Integer>();
   for(int j=0;j<m;j++){
    x=in.readInt()-1;
    y=in.readInt()-1;
    map[x].add(y);
    map[y].add(x);
   }
   s=in.readInt()-1;
   dis=allDistance(map,s);
   isFirst=true;
   for(int d:dis){
    if(d>0){
     if(isFirst){
      out.print(d);
      isFirst=false;
     }
     else
      out.print(" "+d);
    }
   }
   out.println();
  }
  out.flush();
  out.close();

 }
 private static int[]allDistance(HashSet<Integer>map[],int s){
  boolean visited[]=new boolean[map.length];
  int cur;
  int dis[]=new int[map.length];
  Arrays.fill(dis,2*map.length);
  dis[s]=0;
  LinkedList<Integer>queue=new LinkedList<Integer>();
  HashSet<Integer>left=new HashSet<Integer>();
  int remove[]=new int[map.length];
  int index;
  for(int i=0;i<map.length;i++)
   left.add(i);
  left.remove(s);
  queue.add(s); 
  while(!queue.isEmpty()&&!left.isEmpty()){
   cur=queue.pollFirst();
   if(visited[cur])
    continue;
   index=0;
   for(int i:left){
    if(!map[cur].contains(i)){
     dis[i]=min(dis[i],dis[cur]+1);
     queue.add(i);
     remove[index++]=i;
    }
   }
   for(int i=0;i<index;i++){
    left.remove(remove[i]);
   }
   visited[cur]=true;
  }
  return dis; 
 }
 private static int min(int a,int b){
  return a<b?a:b;
 }
 private static class Stdin {
  InputStreamReader read;
  BufferedReader br;

  StringTokenizer st = new StringTokenizer("");

  private Stdin() {
   read = new InputStreamReader(System.in);
   br = new BufferedReader(read);

  }

  private String readNext() throws IOException {

   while (!st.hasMoreTokens())
    st = new StringTokenizer(br.readLine());
   return st.nextToken();
  }

  private int readInt() throws IOException, NumberFormatException {

   return Integer.parseInt(readNext());

  }

  private long readLong() throws IOException, NumberFormatException {

   return Long.parseLong(readNext());

  }
  private double readDouble() throws IOException, NumberFormatException {

   return Double.parseDouble(readNext());

  }
 }
}