import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.reflect.Array;
//class MyEx extends Exception {MyEx(){}}
class Node {
    int id;
    int index;
    int lowlink;
    Node caller;
    int vindex;
    LinkedList<Node> nodeVector;
    Node(int id) {
  this.id=id;
  index=-1;
  nodeVector=new LinkedList<Node>();
 }
};

public class Solution {
 static Node conn[]=null;
 static LinkedList<Integer> revconn[]=null;
 static boolean onQueue[]=null;
 static boolean onStack[]=null;
 static boolean fromCapital[]=null;
 static boolean cycle[]=null;
 static int count[]=null;
 static int N=0;
 static int M=0;
 public static void main(String args[]) throws Exception {
  Scanner sc = new Scanner(System.in);
  N = sc.nextInt();
  M = sc.nextInt();
  conn = new Node[N];
  revconn = new LinkedList[N];
  onQueue = new boolean[N];
  onStack = new boolean[N];
  fromCapital = new boolean[N];
  cycle = new boolean[N];
  count = new int[N];
  for(int t = 0; t < N; t++) {
   conn[t]=new Node(t);
   revconn[t]=new LinkedList<Integer>();
  }
  for(int t = 0; t < M; t++) {
   int x=sc.nextInt()-1;
   int y=sc.nextInt()-1;
   conn[x].nodeVector.add(conn[y]);
   revconn[y].add(x);
  }
  doTarjan();
  if(doBackDfs())
   System.out.println(count[0]);
  else
   System.out.println("INFINITE PATHS");
 }
 static int index=0;
 static Stack<Node> tarStack = null;
 public static final void doTarjan(){
  tarStack = new Stack<Node>();
  fromCapital[0]=true;
  doTar(conn[0]);
 }
 public static final void doTar(Node u){
  u.index=index;
  u.lowlink=index;
  index++;
  u.vindex=0;
  tarStack.push(u);
  u.caller=null;
  onStack[u.id]=true;
  Node last=u;
  while(true) {
   if(last.vindex < last.nodeVector.size()) {
    Node w = last.nodeVector.get(last.vindex);
    last.vindex++;
    if(w.index == -1) {
     w.caller = last;
     w.vindex = 0;
     w.index = index;
     w.lowlink = index;
     index++;
     tarStack.push(w);
     onStack[w.id] = true;
     last = w;
    } else if(onStack[w.id] == true) {
     last.lowlink = min(last.lowlink, w.index);
    }
   } else {
    if(last.lowlink == last.index) {
     Node top = tarStack.pop();
     onStack[top.id] = false;
     fromCapital[top.id]=true;
     //System.out.println("++"+(top.id+1));
     if(top.id != last.id) {
      //System.out.println("---"+(top.id+1));
      cycle[top.id]=true;
      while(top.id != last.id) {
       top = tarStack.pop();
       //System.out.println("---"+(top.id+1));
       onStack[top.id] = false;
       fromCapital[top.id]=cycle[top.id]=true;
      }
     }
    }
    Node newLast = last.caller;
    if(newLast != null) {
     newLast.lowlink = min(newLast.lowlink, last.lowlink);
     last = newLast;
    } else {
     break;
    }
   }
  }
 }
 static final int min(int i,int j) {
  return i>j?j:i;
 }
 static final boolean doBackDfs(){
  if(!fromCapital[N-1])
   return true;
  if(cycle[N-1]||cycle[0])
   return false;
  LinkedList<Integer> q = new LinkedList<Integer>();
  q.offer(N-1);
  onQueue[N-1]=true;
  count[N-1]=1;
  while(!q.isEmpty()) {
   int curr=q.poll();
   onQueue[curr]=false;
   int sum=0;
   for(int i:revconn[curr]) {
    if(cycle[i])
     return false;
    if(!fromCapital[i])
     continue;
    if(!onQueue[i]) {
     onQueue[i]=true;
     q.offer(i);
    }
   }
   for(Node n : conn[curr].nodeVector) {
    sum=(sum+count[n.id])%(1000000000);//=count[curr];
   }
   if(curr!=N-1)
    count[curr]=sum;
  }
  return true;
 }
}