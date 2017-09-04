import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution
{
 public static HashSet<node> looked = new HashSet<node>();
 public static HashSet<HashSet<node>>rekt = new HashSet<HashSet<node>>();
 public static ArrayList<HashSet<node>> sets = new ArrayList<HashSet<node>>();
 public static int k=0;
 public static int maxNodes (HashSet<node>on,int current,ArrayList<node> l)
 {
  if(l.isEmpty())
   return current;
  for(node ele :on)
   for(node e2: getNodes(ele))
    l.remove(e2);
  HashSet<node>on2=new HashSet<node>();
  on2.addAll(on);
  on2.add(l.remove(0));
  
  return Math.max(maxNodes(on2,current+1,l ),maxNodes(on,current,l));
 }
 public static HashSet<node> getNodes(node no)
 {

  HashSet<node> rSet = new HashSet<node>();
  rSet.add(no);
  looked.add(no);
  rekt.add(sets.get(no.s));
  no.s=k;
  for (edge ed : no.edges)
  {
   if (!looked.contains(ed.n))
    rSet.addAll(getNodes(ed.n));
  }
  return rSet;

 }

 public static void main(String[] args) throws IOException
 {
  /*
   * Enter your code here. Read input from STDIN. Print output to STDOUT.
   * Your class should be named Solution.
   */
  BufferedReader in = new BufferedReader(
    new InputStreamReader(System.in));
  int q = Integer.parseInt(in.readLine());
  int e = 0;
  
  for (int i = 0; i < q; i++)
  {
   String[] tokens = in.readLine().split(" ");
   if (tokens[0].equals("A"))
   {
    int size = Integer.parseInt(tokens[1]);
    HashSet<node> thisSet = new HashSet<node>();
    for (int a = 0; a < size; a++)
    {
     thisSet.add(new node(e + a,k));
    }
    sets.add(thisSet);
    k++;
    e += size;

   }
   else if (tokens[0].equals("B"))
   {
    int x = Integer.parseInt(tokens[1]) - 1;
    int y = Integer.parseInt(tokens[2]) - 1;
    for (node ele : sets.get(x))
    {
     for (node e2 : sets.get(y))
     {

      ele.edges.add(new edge(e2));
      e2.edges.add(new edge(ele));
     }
    }
   }
   else
   {
    HashSet<node> thisSet = new HashSet<node>();
    k++;
    int x = Integer.parseInt(tokens[1]) - 1;
   
    for (node no : sets.get(x))
    {
     thisSet.addAll(getNodes(no));
    }
    looked.clear();
    sets.add(thisSet);
   }
  }
  ArrayList<node>ls = new ArrayList<node>();
  for(HashSet<node>s:sets)
   for(node ele : s)
    ls.add(ele);
  System.out.println(maxNodes(new HashSet<node>(),0,ls));
 }
}

class node
{
 int s;
 int n;
 ArrayList<edge> edges;

 node(int n,int s)
 {
  this.n = n;
  edges = new ArrayList<edge>();
 }

 public boolean equals(Object o)
 {
  if (o instanceof node)
  {
   node other = (node) o;
   return n == other.n;
  }
  return false;
 }
}

class edge
{

 node n;

 edge(node n)
 {
  this.n = n;
 }

}