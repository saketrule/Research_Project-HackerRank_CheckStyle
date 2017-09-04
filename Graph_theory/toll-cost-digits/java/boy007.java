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
                curr.setCost(value<curr.getCost()?value:curr.getCost());
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
     arr[v].addEl(u,1000-value);
       }
    else
     arr[u].addEl(v,value);
   }

   public int[] printAllpaths_init()
   {
       int[] digit=new int[10];
       for(int i=0;i<vertices;i++)
       {
      visited=new int[vertices];
      printAllpaths(i,digit,0);
       }
       return digit;
   }
   public void printAllpaths(int source,int[] digit,int cost)    //USING DFS SPANNING TREE HERE
   {
    visited[source]=-1;                     //EXPLORING
    node curr=arr[source].gethead();
    while(curr!=null)
    {
     if(visited[curr.getData()]==0||visited[curr.getData()]==1)     //UNVISITED TREE EDGE OR FORWARD FINSIHED EDGE
     { 
      cost+=curr.getCost();
               digit[cost%10]++;
      printAllpaths(curr.getData(),digit,cost);
      cost-=curr.getCost();
     }
     curr=curr.getNext();
    }
    visited[source]=1;                                                 //FINISHED OR VISITED OR DONE EXPLORING
   }
    
}

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        adjL graph=new adjL(n,'U');
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            graph.addEdge(x-1,y-1,r);
        }
        int[] digit=graph.printAllpaths_init();
        for(int i=0;i<digit.length;i++)
            System.out.println(digit[i]);
        in.close();
    }
}