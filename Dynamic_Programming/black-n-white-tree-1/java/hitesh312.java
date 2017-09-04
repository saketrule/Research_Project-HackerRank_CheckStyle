import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class bwtree {
 static List graph[];
 static int v,e;
 static int via[];
 static int color[];
 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  //System.out.println("enter number of nodes and edges :");
  v = sc.nextInt();
  e = sc.nextInt();
  via = new int[v+1];
  color = new int[v+1];
  graph = new LinkedList[v+1];
  StringBuffer sb = new StringBuffer();
  for(int i=0;i<=v;i++){
   graph[i] = new LinkedList<Integer>();
   via[i] = -1;
   color[i] =  0;
  }
  //System.out.println("enter the edges : ");
  for(int i=0;i<e;i++){
   int t1,t2;
   t1 = sc.nextInt();
   t2 = sc.nextInt();
   graph[t1].add(t2);
   graph[t2].add(t1);
  }
  int s = 1;
  int v1=1,v2=0;
  int c1=0,c2=0;
  via[s] = s;
  color[s]=1;
  Queue<Integer> q = new LinkedList<Integer>();
  q.add(s);
  while(!q.isEmpty()){
   s = q.remove();
   if(color[s]==1)c1++;
   else c2++;
   Iterator<Integer> it = graph[s].iterator();
   while(it.hasNext()){
    int tmp = it.next();
    if(color[tmp]==0){
     q.add(tmp);
     via[tmp] = s;
     if(color[s]==1){
      color[tmp]=2;
      if(v2==0)v2=tmp;
     }
     else color[tmp]=1;
    }
   }
  }
  int cnt=0;
  for(int i=1;i<=v;i++){
   if(color[i]==0){
    cnt++;
    s=i;
    via[s] = s;
    color[s]=1;
    int v3=i,v4=0;
    int c3=0,c4=0;
    q.add(i);
    while(!q.isEmpty()){
     s = q.remove();
     if(color[s]==1)c3++;
     else c4++;
     Iterator<Integer> it = graph[s].iterator();
     while(it.hasNext()){
      int tmp = it.next();
      if(color[tmp]==0){
       q.add(tmp);
       via[tmp] = s;
       if(color[s]==1){
        color[tmp]=2;
        if(v4==0)v4=tmp;
       }
       else color[tmp]=1;
      }
     }
    }
    if(c1>=c2 && c3>=c4){
     c1 = c1+c4;
     c2 = c2+c3;
     if(v1>0&&v4>0)sb.append(v1+" "+v4+"\n");
     else sb.append(v2+" "+v3+"\n");
    }
    else if(c1>=c2 && c3<c4){
     c1 = c1+c3;
     c2 = c2+c4;
     if(v1>0&&v3>0)sb.append(v1+" "+v3+"\n");
     else System.out.println(v2+" "+v4+"\n");
    }
    else if(c1<c2 && c3>=c4){
     c1 = c1+c3;
     c2 = c2+c4;
     if(v1>0&&v3>0)sb.append(v1+" "+v3+"\n");
     else System.out.println(v2+" "+v4+"\n");
    }
    else if(c1<c2 && c3<c4){
     c1 = c1+c4;
     c2 = c2+c3;
     if(v1>0&&v4>0)sb.append(v1+" "+v4+"\n");
     else sb.append(v2+" "+v3+"\n");
    }
   }
   
  }
  System.out.println(abs(c1-c2)+" "+cnt);
  System.out.println(sb);
 }
 private static int abs(int i) {
  if(i<0)return -i;
  return i;
 }
}