import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class A
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
   inputParser = new StringTokenizer(line, " ,\t");
   //System.err.println("Input: " + line);
  } catch (IOException e) {
   System.err.println("Unexpected IO ERROR: " + e);
  } 
  catch (NullPointerException e)
  {
   line=null;
   
  }
  
 }
    
    long NextLong()
    {
            String n = inputParser.nextToken();
            
            long val = Long.parseLong(n);
            
             return val;
    }
    
    int NextInt()
    {
            String n = inputParser.nextToken();
            int val = Integer.parseInt(n);
            
            //System.out.println("I read this number: " + val);
            return val;
    }
    
    double NextDouble()
    {
            String n = inputParser.nextToken();
            double val = Double.parseDouble(n);
            
            //System.out.println("I read this number: " + val);
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
    
    
    public static void main(String [] argv)
    {
            //String filePath="circles.in";
            String filePath=null;
            if(argv.length>0)filePath=argv[0];
            new A(filePath);
            
    }
    
    public A(String inputFile)
    {
     openInput(inputFile);
  readNextLine();
  int T=NextInt();
  StringBuilder sb = new StringBuilder();
  for(int t=1; t<=T; t++)
  {
   readNextLine();
   int N=NextInt();
   int M=NextInt();
   
   HashMap <Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();
   
   for(int m=0; m<M; m++)
   {
    readNextLine();
    int a = NextInt();
    int b = NextInt();
    if(!graph.containsKey(a))graph.put(a, new HashSet<Integer>());
    if(!graph.containsKey(b))graph.put(b, new HashSet<Integer>());
    graph.get(a).add(b);
    graph.get(b).add(a);
   } 
   readNextLine();
   int S = NextInt();
   int MAX=100;
   HashSet <Integer> [] res = new HashSet[MAX];
   for(int i=1; i<MAX; i++)
    res[i] = new HashSet<Integer>();
   /*for(int i=1; i<=N; i++)
   {
    if(i==S)continue;
    if(graph.containsKey(i)&&graph.get(i).contains(S))
    two.add(i);
    else one.add(i);
   }*/
   if(!graph.containsKey(S))graph.put(S, new HashSet<Integer>());
   res[2].addAll(graph.get(S));
   for(int x:res[2])
   {
    boolean ok=false;
    for(int j=N; j>=1; j--)
    {
     if(!graph.get(S).contains(j)&&!graph.get(x).contains(j))
     {
      ok=true;
      break;
     }
    }
    if(!ok)
    {
     res[3].add(x);
    }
    
   }
   res[2].removeAll(res[3]);
   for(int d=3; d<MAX-1; d++)
   {
    if(res[d].isEmpty())break;
    for(int x:res[d])
    {
     boolean ok=false;
     for(int j:res[d-1])
     {
      if(!graph.get(x).contains(j))
      {
       ok=true;
       break;
      }
     }
     if(!ok)
     {
      res[d+1].add(x);
     }
    }
    res[d].removeAll(res[d+1]);
   }/*
   for(int x:four)
   {
    boolean ok=false;
    for(int j:three)
    {
     if(!graph.get(x).contains(j))
     {
      ok=true;
      break;
     }
    }
    if(!ok)
    {
     five.add(x);
    }
   }
   four.removeAll(five);
   for(int x:five)
   {
    boolean ok=false;
    for(int j:four)
    {
     if(!graph.get(x).contains(j))
     {
      ok=true;
      break;
     }
    }
    if(!ok)
    {
     six.add(x);
    }
   }
   five.removeAll(six);
   for(int x:six)
   {
    boolean ok=false;
    for(int j:five)
    {
     if(!graph.get(x).contains(j))
     {
      ok=true;
      break;
     }
    }
    if(!ok)
    {
     seven.add(x);
    }
   }
   six.removeAll(seven);*/
   int [] p = new int[N+1];
   
   for(int i=1; i<=N; i++)
   {
    for(int j=1; j<MAX; j++)
     if(res[j].contains(i))
     {
      p[i] = j-1;
      break;
     }
   }
   for(int i=1; i<=N; i++)
   {
    if(i==S)continue;
    
    else sb.append((p[i]+1)+" ");
   }
   sb.append("\n");
  }
  System.out.print(sb);
  
  closeInput();  
 }
    
}