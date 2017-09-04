import java.util.Scanner;

public class Byte {

 public static class box{
  int val;
  int wt;
  box next;
 }
 
 static boolean check[];
 static boolean visited[];
 static int minsum=1000;
 
 public static void dfs(box a[],int n,int k,int index,int nodesum,int wtsum){
  
  if((wtsum!=0)&&(wtsum>=minsum)){
   return;
  }
  
  if(nodesum==k){
   if(minsum>wtsum){
    minsum=wtsum;
   }
   return;
  }
  
  visited[index]=true;
  box b = a[index].next;
  while(b!=null){
   if((((check[b.val]==true)&&(visited[b.val]==false))||check[b.val]==false)){
    dfs(a,n,k,b.val,nodeINC(nodesum,b.val),wtsum+b.wt);
   }
   b=b.next;
  }
  visited[index]=false;
  
 }
 
 public static int nodeINC(int nodesum,int index){
  
  if(check[index]==true){
   return nodesum+1;
  }
  
  return nodesum;
 }
 
 public static void main(String args[]){
  Scanner sc =  new Scanner(System.in);
  int n = sc.nextInt();
  int k = sc.nextInt();
  
  check = new boolean[n+1];
  visited = new boolean[n+1];
  int visitingNodes[] = new int[k];
  //node to visit
  for(int i=0;i<k;++i){
   int index = sc.nextInt();
   visitingNodes[i]=index;
   check[index]=true;
  }
  
  //graph-building
  box a[] = new box[n+1];
  for(int i=1;i<=n;++i){
   a[i]=new box();
   a[i].val=i;
  }
  
  for(int i=0;i<n-1;++i){
   int st = sc.nextInt();
   int en = sc.nextInt();
   int wt = sc.nextInt();
   //st->en
   box b=a[st];
   while(b.next!=null){
    b=b.next;
   }
   box nb = new box();
   nb.val=en;
   nb.wt=wt;
   
   b.next=nb;
   // en->st
   b=a[en];
   while(b.next!=null){
    b=b.next;
   }
   box nb1 = new box();
   nb1.val=st;
   nb1.wt=wt;
   
   b.next=nb1;
  }
  //graph completed
 
  /*for(int i=1;i<=n;++i){
   box h = a[i];
   System.out.println();
   while(h!=null){
    System.out.print("|"+h.val+"|"+h.wt+"|"+"-->");
    h=h.next;
   }
  }
  */
  
  //step 1: to start at nodes to be visited only
  //step 2: keep visiting until all nodes are visited
  for(int i=0;i<k;++i){
   dfs(a,n,k,visitingNodes[i],1,0);
  }
  
  System.out.println(minsum);
  
  
 }
}