import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.StringTokenizer;

public class C
{
 String line;
 StringTokenizer inputParser;
 BufferedReader is;
 FileInputStream fstream;
 DataInputStream in;
 String FInput="";
 
 
 void openInput(String file)
 {

  if(file==null)is = new BufferedReader(new InputStreamReader(System.in));//stdin
  else
  {
   try{
  
    
   fstream = new FileInputStream(file);
   in = new DataInputStream(fstream);
   is = new BufferedReader(new InputStreamReader(in));
   }catch(Exception e)
   {
    System.err.println(e);
   }
  }

 }
 
 void readNextLine()
 {
  try {
   line = is.readLine();
   inputParser = new StringTokenizer(line, " ");
   //System.err.println("Input: " + line);
  } catch (IOException e) {
   System.err.println("Unexpected IO ERROR: " + e);
  } 
  
 }
 
 int NextInt()
 {
  String n = inputParser.nextToken();
  int val = Integer.parseInt(n);
  
  //System.out.println("I read this number: " + val);
  return val;
 }
 
 private double NextDouble() {
        String n = inputParser.nextToken();
        double val = Double.parseDouble(n);
        return val;
 }
 
 String NextString()
 {
  String n = inputParser.nextToken();
  return n;
 }
 
 void closeInput()
 {
  try {
   is.close();
  } catch (IOException e) {
   System.err.println("Unexpected IO ERROR: " + e);
  }
   
 }
 
 public void readFInput()
 {
  for(;;)
  {
   try
   {
    readNextLine();
    FInput+=line+" ";
   }
   catch(Exception e)
   {
    break;
   }
  }
  inputParser = new StringTokenizer(FInput, " ");
 }
 
 long NextLong()
    {
            String n = inputParser.nextToken();
            
            long val = Long.parseLong(n);
            
            return val;
    }
 
 public static void main(String [] argv)
 {
  //String filePath="input.txt";
        String filePath=null;
        if(argv.length>0)filePath=argv[0];
  new C(filePath);
 }
 
 public C(String inputFile)
 {
  openInput(inputFile);
  StringBuilder sb = new StringBuilder();
  readNextLine();
  int N= NextInt();
  int M= NextInt();
  Graph g = new Graph(N);
  for(int i=0; i<M; i++)
  {
   readNextLine();
   int u=NextInt()-1;
   int v=NextInt()-1;
   int w=NextInt();
   
   g.p[u].ngh.add(new Edge(i+1, v, w));
   g.p[v].ngh.add(new Edge(i+1, u, w));
  }
  readNextLine();
  int start=NextInt()-1;
  int end=NextInt()-1;
  
  sb.append(g.getRes(start, end));
     System.out.println(sb);
  closeInput(); 
 }
 
 private class Edge
 {
  int to;
  int id;
  int w;
  
  Edge(int id, int to, int w)
  {
   this.id=id;
   this.to=to;
   this.w=w;
  }
 }
 
 private class Graph
 {
  Node [] p;
  Graph(int N)
  {
   p = new Node[N];
   for(int i=0; i<N; i++)
   {
    p[i] = new Node(i);
    
   }
  }
  
  int ret = 1000000000;
  int endId=-1;
  HashSet <Integer> seen = new HashSet<Integer>();
  void fill(int x, int val)
  {
   if(seen.contains(x*1000000+val))return;
   seen.add(x*1000000+val);
   p[x].min = val;
   //System.err.println(x+" "+val);
   if(x==endId)
   {
    ret = Math.min(ret, val);
    return;
   }
   for(Edge e:p[x].ngh)
    fill(e.to, val|e.w);
  }
  
  public int getRes(int start, int end) {
   endId=end;
   fill(start,0);
   
   return ret==1000000000?-1:ret;
  }
 }
 
 private class Node implements Comparable<Node>
 {
  ArrayList <Edge> ngh = new ArrayList<Edge>();
  long len=Long.MAX_VALUE;
  Edge from = null;
  int id;
  int min=1000000000;
  
  Node (int id)
  {
   this.id=id;
  }
  public int compareTo(Node d)
  {
   if(d.len>len)return -1;
   if(d.len<len)return 1;
   return 0;
  }
 }
 
}