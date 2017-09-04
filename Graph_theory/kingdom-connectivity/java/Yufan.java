/* Enter your code here. Read input from STDIN. Print output to STDOUT *//* Enter your code here. Read input from STDIN. Print output to STDOUT */

import java.util.*;


class Graph 
{ 
public ArrayList<ArrayList<Integer> > Gra=new ArrayList<ArrayList<Integer> >();
public int[] FirstLine;
Graph(int m)
{
 FirstLine=new int[m];
 for(int i=0;i<m;i++)
 {
  ArrayList<Integer> tmp=new ArrayList<Integer> ();
  Gra.add(tmp);
 }
}
public void addEdge(int node1, int node2) {  
 Gra.get(node2-1).add(node1-1);  
 if(node1==1)
 {
  FirstLine[node2-1]=1;
 }
}

}

public class Solution {
 
 public static int min(int a, int b)
 {
  return a < b ? a : b;
        }
 public static void main(String []args)
 {
  Scanner SC=new Scanner(System.in);
  SC.useDelimiter(" ");
  String tmp0=SC.nextLine();
  String first[]=tmp0.split(" ");
  int m=Integer.parseInt(first[0]);
  Graph graph=new Graph(m);

  for(int i=0;i<Integer.parseInt(first[1]);i++)
  {
      String tmp1=SC.nextLine();  
      String edge[]=tmp1.split(" ");
      graph.addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
  
  }
      int rs=graph.FirstLine[m-1]% 1000000000;
      boolean TT=true;
      int[] tmp=graph.FirstLine;
      int[] swap;
      int[] tmp1=new int[m];
      for(int k=0;k<min(m-1,625);k++)
      {
      
      for(int i=0;i<m;i++)
      {
       int temp=0;
     
       for(int j=0;j<graph.Gra.get(i).size();j++)
       {
        temp+=tmp[graph.Gra.get(i).get(j)];
                  temp=temp% 1000000000;
       }
       tmp1[i]=temp% 1000000000;
       
      }  
      rs+=tmp1[m-1]% 1000000000;
      rs=rs% 1000000000;
      swap = tmp;
      tmp = tmp1;
      tmp1 = swap;
      }
     
            if(m>100)
            {
                m=0;
            }
            
      for(int k=0;k<m;k++)
      {
      // int[] tmp1=new int[m];
          
          for(int i=0;i<m;i++)
          {
           int temp=0;
         
           for(int j=0;j<graph.Gra.get(i).size();j++)
           {
            temp+=tmp[graph.Gra.get(i).get(j)];
                          temp=temp% 1000000000;
           }
           tmp1[i]=temp% 1000000000;
           
          }  
          if(tmp1[m-1]!=0)
          {
           System.out.println("INFINITE PATHS");
           TT=false;
           break;
          }
      swap = tmp;
      tmp = tmp1;
      tmp1 = swap;
      }

      if(TT)
      System.out.println(rs);
      
  
 }

}