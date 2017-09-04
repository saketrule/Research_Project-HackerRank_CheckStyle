import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


class node {
 private int vertex;
 private int cost;
 private node next;
 
 public int getCost()
 {
  return cost;
 }
 public void setCost(int val)
 {
  cost=val;
 }
 public node getNext()
 {
  return next;
 }
 public void setNext(node obj)
 {
  next=obj;
 }
 public void putData(int n)
 {
  vertex=n;
 }
 public void showData()
 {
  System.out.print(vertex+" ");
 }
 public int getData()
 {
  return vertex;
 }
 
}

class list {
 private int size;
 private node head;
    private node tail;
 
 public list()
 {
  head=null;
  tail=null;
  size=0;
 }
 
 public int getSize()
 {
  return size;
 }
  public void sethead(node obj)
    {
     head=obj;
     if(head==null)
      settail(null);
    }
 public node gethead()
 {
  return head;
 }
 public void settail(node obj)
    {
  tail=obj;
    }
 public node gettail()
 {
  return tail;
 }
 
 public void addEl(int v,int value) //TC O(OutDegree(u))
 {
  node curr=head,prev=null;
  while(curr!=null)             //BEFORE ADDING THE EDGE WE MUST CHECK IT IS ALREADY PRESENT OR NOT
  {
   if(curr.getData()==v){
    //System.out.println("Edge alreday present in the Graph");
    return;
   }
   else if(curr.getData()>v)
       break;
   else
   {
    prev=curr;
    curr=curr.getNext();
   }
  }
  node newborn=new node();
  newborn.putData(v);
  newborn.setCost(value);
  if(head==null)         //TC O(1) ADDING THE NEW EDGE AT THE END OF THE REQIRED LIST AND CHAGING THE TAIL POINTER
  {
   head=newborn;
   tail=newborn;
   //System.out.println("NEW HEAD");
  }
  else if(prev==null)
  {
      newborn.setNext(head);
      head=newborn;
      //System.out.println("HEAD RESET");
  }
  else if(prev==tail)
     {
   tail.setNext(newborn);
      tail=newborn; 
      //System.out.println("NEW TAIL");
     }
  else
  {
   newborn.setNext(curr);
   prev.setNext(newborn);
   //System.out.println("MIDDLE ADJUSTMENT");
  }
     size++;
 }

 public void deleteEl(int v)            //TC O(OutDegree(u)) SINCE WE HAVE TO TRANSVERSE THROUGH THE ENTIRE LIST AND CHECK IF THE EDGE IS PRESENT
 {
  node curr=head,prev=null;
  while(curr!=null)
  {
   if(curr.getData()==v)
   {
    if(prev==null)
     sethead(curr.getNext());
    else
     prev.setNext(curr.getNext());
    size--;
    break;
   }
   else
   {
    prev=curr;
    curr=curr.getNext();
      }
  }
  if(prev!=null&&prev.getNext()==null)
   tail=prev;
 }
 
 public void clear()
 {
  head=null;
  tail=null;
  size=0;
 }
}

class adjL {
   private list arr[];
   private int vertices;
   private char type;
   private int visited[];
   adjL(int n,char ch)
   {
    arr=new list[n];
    vertices=n;
    type=ch;
    adjL_init(0);
   }
   public void adjL_init(int start)            //TC O(vertices-start) i.e Linear time
   {
    for(int i=start;i<vertices;i++)
     arr[i]=new list();   
   }
   
   public void addEdge(int u,int v,int value)   //TC O(OutDegree(u)+OutDegree(v)) FOR SEARCHING AND ADDING A NEW EDGE
   {
    if(type=='U')
       {
     arr[u].addEl(v,value);
     arr[v].addEl(u,value);
       }
    else
     arr[u].addEl(v,value);
   }
    
   public void hungarian_init(int client,int house)
    {
     int mcbm=0;
     int[] match=new int[client+house];
     Arrays.fill(match,-1);
     for(int i=0;i<client;i++)          //WORST CASE O(V/2)=O(V)
     {
      visited=new int[client];
      mcbm+=hungarian(i,match);       //WORST CASE TC O(E)
     }
     System.out.println(mcbm);
    }
    
    public int hungarian(int start,int[] match)     //TC O(E) IT RUNS ALL THE DGES IN THE WORST CASE
    {
     if(visited[start]==1)
      return 0;
     visited[start]=1;
     node curr=arr[start].gethead();
     while(curr!=null)
     {
      if(match[curr.getData()]==-1||hungarian(match[curr.getData()],match)==1) 
      {
       match[curr.getData()]=start;
       return 1;
      }
      curr=curr.getNext();
     }
     return 0;
    } 

}

public class Solution {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int[][] client =new int[2][n];
        int[][] house=new int[2][m];
        adjL g=new adjL(n+m,'U');
        for(int i=0;i<n;i++)
        {
            client[0][i]=in.nextInt();
            client[1][i]=in.nextInt();
        }
        for(int i=0;i<m;i++)
        {
            house[0][i]=in.nextInt();
            house[1][i]=in.nextInt();
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(client[0][i]<house[0][j]&&house[1][j]<=client[1][i])
                   g.addEdge(i,n+j,1);
                else
                   continue;
            }
        }
        g.hungarian_init(n,m);
        in.close();
    }
}