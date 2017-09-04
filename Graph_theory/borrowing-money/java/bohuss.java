import java.io.*;
import java.math.BigInteger;
import java.util.*;

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
  //readNextLine();
  int T=1;//NextInt();
  StringBuilder sb = new StringBuilder();
  for(int t=1; t<=T; t++)
  {
   readNextLine();
   int N=NextInt();
   int Q=NextInt();
   Graph g = new Graph(N);
   readNextLine();
   for(int i=0; i<N; i++)
   {
    int c = NextInt();
    g.nodes[i].num = c;
   }
   
   for(int i=0; i<Q; i++)
   {
    readNextLine();
    int a = NextInt()-1;
    int b = NextInt()-1;
    
    g.addEdge(b, a);
    
   }
   long max = 0, cnt=0;
   long mask = 0;
   while(mask<(1<<N))
   {
    Sln base = new Sln(N, mask);
    long now = g.count(base);
    if(now > max){
     max = now;
     cnt=1;
    }
    else if(max == now)cnt++;
    mask++;
   }
   
   
   sb.append(max+" "+cnt);
  }
  System.out.print(sb);
  
  closeInput();  
 }
 
 private class Sln {
  boolean [] p;
  int N;
  Sln(int N){
   this.N = N;
   p = new boolean[N];
  }
  Sln(int N, long mask){
   this.N = N;
   p = new boolean[N];
   for(int i=0; i<N; i++)
    if((mask&(1<<i))>0)p[i] = true;
  }
 }

  private class Graph {
      Node [] nodes;
      int N;
      Graph(int N) {
       this.N = N;
       nodes = new Node[N];
       for(int i=0; i<N; i++)
        nodes[i] = new Node(i);
      }
      

   public void addEdge(int f, int t)
      {
       nodes[f].addEdge(nodes[t]);
       nodes[t].addEdge(nodes[f]);
      }
      
      
      long count(Sln sln){
       long ret = 0;
       if(!ok(sln))return -1;
       for(int i=0; i<N; i++)
        if(sln.p[i])ret+=nodes[i].num;
       return ret;
      }


   private boolean ok(Sln sln) {
    Set <Node> seen = new HashSet<Node>();
    for(int i=0 ;i<N; i++)
    {
     if(!sln.p[i])continue;
     if(seen.contains(nodes[i]))return false;
     for(Node x:nodes[i].children)
      seen.add(x);
    }
    return true;
   }
      
      
      
     }
     
     private class Node {
   HashSet <Node> children = new HashSet<Node>();
   int id;
   int num = 0;
   boolean seen = false;
   Node (int id){
    this.id = id;
   }
   public void addEdge(Node d ) {
    children.add(d);
   }
   int count(){
    for(Node x:children)
     num+=x.count();
    return num;
   }
  }
     
     
     private class Edge {
      Node to;
      int c;
      Edge(Node to, int c){
       this.to = to;
       this.c = c;
      }
     }

}