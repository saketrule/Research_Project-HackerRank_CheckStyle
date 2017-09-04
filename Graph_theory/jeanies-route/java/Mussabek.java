import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
 
 String src = null;
 
 int maxN = 100000;
 
 Set<Integer> letterCity;
 
 int[][] adj; //adjacency matrix
 int n;
 int k;
 
 public int calc() throws Exception
 {
  
   
  BufferedReader br = null;
  
  if (src == null)
   br = new BufferedReader(new InputStreamReader(System.in));
  else
   br = new BufferedReader(new FileReader(src));
  
  String[] vals = br.readLine().split(" ");
  
  n = Integer.parseInt(vals[0]);
  k = Integer.parseInt(vals[1]);
  
  letterCity = new HashSet<Integer>();
  
  vals = br.readLine().split(" ");
  
  for (int i = 0; i < k; i++)
  {
   int idx = Integer.parseInt(vals[i]) - 1;
   
   letterCity.add(idx);
  }
  
  adj = new int[n][n];
  for (int i = 0; i < n; i++ )
   Arrays.fill(adj[i], -1);
  
  
   
  for (int i = 0; i < n-1; i++)
  {
   vals = br.readLine().split(" ");
   
   int u = Integer.parseInt(vals[0]) - 1;  // from
   int v = Integer.parseInt(vals[1]) - 1;  // to   
   int d = Integer.parseInt(vals[2]);   // dist
   
   adj[u][v] = d;
   adj[v][u] = d;
   
   
  }
  
  int id0 = (int) letterCity.toArray()[0];
  
  id0 = findFarthest(id0);
  int li = findFarthest(id0);
  
  //System.out.println( "start=" + id0 + ", end=" + li);
  
  int sum = sumDistances(id0); // * 2;
  
  
  
  
  sum *= 2;
  
  
  int backWay = dfs(id0, li);
  //System.out.println("backway cost (from "+id0+", to "+li+") == "+backWay);
  sum -= backWay; //dfs(id0, li);
  
  //System.out.println("sum == "+sum);

  return sum;
 }
 
 public int findFarthest(int idx)
 {
  Set<Integer> visited = new HashSet<Integer>();
  int[] dst = new int[n];
  
  Set<Integer> letters = new HashSet<Integer>();
  
  Arrays.fill(dst, 0);
  
  Queue<Integer> q = new LinkedList<Integer>();
    
  q.add(idx);
  letters.add(idx);
  
  int farthest = idx;
  
  while(true)
  {
   int tmpId = q.poll();
   int tmpDst = dst[tmpId]; 
   
   //if (tmpId == dstId)
   // break;
   
   for (int i = 0; i < n; i++)
   {
    if (adj[tmpId][i] > 0 && !visited.contains(i))
    {
     visited.add(i);
     q.add(i);
     dst[i] = tmpDst + adj[tmpId][i];
    
     
     if (letterCity.contains(i))
     {
      if (!letters.contains(i))
      {
       letters.add(i);
       
       if (dst[i] > dst[farthest])
         farthest = i;
      }
     }
    }
   }

   if (letters.size() == letterCity.size())
    break;
  }
  
  return farthest;
 }
 
 
 public int sumDistances(int idx)
 {
  Set<Integer> visited = new HashSet<Integer>();
  int[] dst = new int[n];

  Set<Integer> letters = new HashSet<Integer>();

  Arrays.fill(dst, 0);
  
  Queue<Integer> q = new LinkedList<Integer>();
  
  int[] parent = new int[n];
  
  Arrays.fill(parent, -1);
  
  parent[idx] = idx;
  
    
  q.add(idx);
  letters.add(idx);
  visited.add(idx);
  
  int sum = 0;
  
  while(true)
  {
   int tmpId = q.poll();
   int tmpDst = dst[tmpId]; 
      
   for (int i = 0; i < n; i++)
   {
    if (adj[tmpId][i] > 0 && !visited.contains(i))
    {
     visited.add(i);
     q.add(i);
     dst[i] = tmpDst + adj[tmpId][i];
     
     parent[i] = tmpId;

     if (letterCity.contains(i))
     {
      if (!letters.contains(i))
      {
       letters.add(i);
       
       //sum += adj[tmpId][i];

      }
     }
    }
   }
   
   if (letters.size() == letterCity.size())
    break;
   
   
  }
  
  Iterator cit = letterCity.iterator();
  
  while (cit.hasNext())
  {
   int lidx = (Integer) cit.next();
   
   
   while(parent[lidx] != lidx )
   {
    
    //System.out.println("from "+lidx+" to "+parent[lidx]);
    sum += adj[lidx][parent[lidx]];
    
    int save = parent[lidx];
    
    parent[lidx] = lidx;
    lidx = save;
    
    //lidx = parent[lidx];
    
    
   }
  }
  
  return sum;
 }
 
 
 public int dfs(int srcId, int dstId)
 {
  Set<Integer> visited = new HashSet<Integer>();
  int[] dst = new int[n];
  
  Arrays.fill(dst, 0);
  
  Queue<Integer> q = new LinkedList<Integer>();
  
  
  q.add(srcId);
  
  while(true)
  {
   int tmpId = q.poll();
   int tmpDst = dst[tmpId]; 
   
   //if (tmpId == dstId)
   // break;
   
   for (int i = 0; i < n; i++)
   {
    if (adj[tmpId][i] > 0 && !visited.contains(i))
    {
     visited.add(i);
     q.add(i);
     dst[i] = tmpDst + adj[tmpId][i];
    
     if (i == dstId)
     {
      break;
      
     }
    }
   }
   
   if (visited.contains(dstId))
    break;
   
   
  }
  
  return dst[dstId];
      
 }

 public static void main(String[] args) throws Exception{

  Solution sol = new Solution();
  System.out.println(sol.calc());
  

 }

}