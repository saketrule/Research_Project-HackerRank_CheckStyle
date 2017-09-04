import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Solution {
 public static HashMap<Integer,ArrayList<AdjList>> adj;
 public static Edge edge[];
 public static HashMap<Integer, Boolean> hMap=new HashMap<>();
 public static int n,k;
 public static int soln;
 
 public static int path[]=new int[10];
 public static int p=0;
 
 public static void main(String[] args) {
  Scanner sc=new Scanner(System.in);
  n=sc.nextInt();
  k=sc.nextInt();
  int kk[]=new int[k];
  for(int i=0;i<k;i++){
   int x=sc.nextInt();
   kk[i]=x-1;
   hMap.put(x-1, false);
  }
  adj=new HashMap<>();
  for(int i=0;i<n;i++){
   adj.put(i, new ArrayList<AdjList>());
  }
  edge=new Edge[n-1];
  for(int i=0;i<n-1;i++){
   int a=sc.nextInt(),b=sc.nextInt(),c=sc.nextInt();
   edge[i]=new Edge(i,c);
   adj.get(a-1).add(new AdjList(b-1,i));
   adj.get(b-1).add(new AdjList(a-1,i));
  }
  sc.close();
  
  soln=Integer.MAX_VALUE;
  for(int ii:kk){
   p=0;
   cost(ii,k-1,0);
   //System.out.println("Soln for Node: "+ii+" "+soln);
  }
  
  System.out.println(soln);
 }
 public static void printEdge(){
  System.out.println("Edges:");
  for(int i=0;i<n-1;i++){
   System.out.println(edge[i].id+" "+edge[i].wt+" "+edge[i].cc);
  }
  System.out.println("Edges Over!");
 }
 public static void printPath(){
  System.out.println("Path:");
  for(int i=0;i<p;i++){
   System.out.print(path[i]+" ");
  }
  System.out.println("");
 }
 
 public static int cost(int v,int k,int ans){
  if(ans>soln)return Integer.MAX_VALUE;
  path[p++]=v;
  if(k==0){
   soln=Math.min(ans, soln);
   //printEdge();
   //printPath();
   //System.out.println(ans);
   return ans;
  }
  boolean f=false;
  for(AdjList aa:adj.get(v)){
   if(edge[aa.id].cc<2){
    f=true;
    edge[aa.id].cc++;
    int s1;
    if(hMap.containsKey(aa.v) && !hMap.get(aa.v)){
     hMap.put(aa.id, true);
     s1=cost(aa.v,k-1,ans+edge[aa.id].wt);
     hMap.put(aa.id, false);
    }else{
     s1=cost(aa.v,k,ans+edge[aa.id].wt);
    }
    edge[aa.id].cc--;
   }
  }p--;
  if(!f)return Integer.MAX_VALUE;
  
  return Integer.MAX_VALUE;
 }

}
class Edge{
 int id,wt,cc=0;

 public Edge(int id, int wt) {
  this.id = id;
  this.wt = wt;
 }
 
}
class AdjList{
 int v,id;

 public AdjList(int v,int id) {
  this.id=id;
  this.v = v;
 } 
}