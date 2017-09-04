import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
class edge{
 public int u,v,w;
 edge(int u,int v,int w){
  this.u = u;
  this.v = v;
  this.w = w;
 }
 int get_other(int x){
  if(x==u)return v;
  return u;
 }
}
class q_node{
 int vertex,time;
 List<Integer> shops;
 q_node(int v,int t, List<Integer> l){
  vertex = v;
  time = t;
  shops = l;
 }
}
public class shoping {

 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int n = sc.nextInt();
  int m = sc.nextInt();
  int k = sc.nextInt();
  List<Integer> shop[] = new List[n];
  List<edge> graph[] = new List[n];
  for(int i=0;i<n;i++){
   int t = sc.nextInt();
   shop[i] = new LinkedList<Integer>();
   graph[i] = new LinkedList<edge>();
   while(t-- != 0){
    int tmp = sc.nextInt();
    shop[i].add(tmp);
   }
  }
  edge el[] = new edge[m];
  for(int i=0;i<m;i++){
   int u = sc.nextInt();
   u--;
   int v = sc.nextInt();
   v--;
   int w = sc.nextInt();   
   el[i] = new edge(u,v,w);
   graph[u].add(el[i]);
   graph[v].add(el[i]);
  }
  List<q_node> flist = new LinkedList<q_node>();
  Queue<q_node> q = new LinkedList<q_node>();
  q.add(new q_node(0,0,shop[0]));
  int ans = Integer.MAX_VALUE;
  while(!q.isEmpty()){
   q_node nd = q.remove();
   int s = nd.vertex;
   Iterator<edge> it = graph[s].iterator();
   while(it.hasNext()){
    edge e = it.next();
    int ov = e.get_other(s);
    if(nd.time+e.w >= ans)continue;
    List<Integer> tmp = new LinkedList<Integer>();
    tmp.addAll(nd.shops);
    tmp.removeAll(shop[ov]);
    tmp.addAll(shop[ov]);
    q_node newNode = new q_node(ov,nd.time+e.w,tmp);
    q.add(newNode);
    if(ov==n-1){
     if(tmp.size()==k){
      if(nd.time+e.w < ans)ans = nd.time+e.w;
     }
     else{
      Iterator<q_node> it1 = flist.iterator();
      while(it1.hasNext()){
       q_node q1 = it1.next();
       List<Integer> tmp2 = new LinkedList<Integer>();
       tmp2.addAll(q1.shops);
       tmp2.removeAll(tmp);
       if(tmp.size()+tmp2.size()==k){
        if(nd.time+e.w >= q1.time && nd.time+e.w < ans)ans = nd.time+e.w;
        else if(nd.time+e.w < q1.time && q1.time < ans)ans = q1.time;
       }
      }
      flist.add(newNode);
     }
    }
   }
  }
  System.out.println(ans);
 }
}