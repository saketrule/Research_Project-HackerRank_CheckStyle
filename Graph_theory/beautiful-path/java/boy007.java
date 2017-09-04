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
  while(curr!=null)            
  {
   if(curr.getData()>=v)
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
    if(type=='U'&&u!=v)
       {
     arr[u].addEl(v,value);
     arr[v].addEl(u,value);
       }
    else
     arr[u].addEl(v,value);
   }
    
     public int Spfa_init(int start,int end)           
     {
    int shortest[]=new int[vertices];
    Arrays.fill(shortest,Integer.MAX_VALUE);
    shortest[start]=0;
    Spfa(start,shortest);
         return shortest[end];
     }
   public void Spfa(int start,int[] shortest)
   {
    int present[]=new int[vertices];      
    Arrays.fill(present, -1);
    Queue<Integer> q=new LinkedList<Integer>();
    present[start]=1;
    q.add(start);
    while(!q.isEmpty())                         
    {
     int vertex=q.poll();
     present[vertex]=-1;
     node curr=arr[vertex].gethead();
     while(curr!=null)                        //TC O(OutDegree(start))
     {
               int bit=shortest[vertex]|curr.getCost();
      if(bit<shortest[curr.getData()])
      {
       shortest[curr.getData()]=bit;
       if(present[curr.getData()]==-1)
       {
       q.add(curr.getData());
       present[curr.getData()]=1;
       }
      }
       curr=curr.getNext(); 
     }
    }
  
   }
}

public class Solution {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        adjL g=new adjL(n,'U');
        for(int i=0;i<m;i++)
        {
            int u=in.nextInt();
            int v=in.nextInt();
            int r=in.nextInt();
            g.addEdge(u-1,v-1,r);
        }
        int start=in.nextInt();
        int end=in.nextInt();
        int min=g.Spfa_init(start-1,end-1);
        if(min==Integer.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(min);
        in.close();
    }
}