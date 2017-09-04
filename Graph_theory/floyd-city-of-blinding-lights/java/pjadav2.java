import java.util.Scanner;

public class DIJkstra {
 
 public static Vertice heap[];
 public static int size=0;
 public static int location[];
 public static int distance [][];
 
 public static final int INFY = Integer.MAX_VALUE;
 
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     int T = 1;//sc.nextInt();
     for(int test_case =0;test_case<T;test_case++){
      int N = sc.nextInt();
         heap = new Vertice[N+1];
         location = new int[N+1];
         distance = new int[N+1][N+1];
         boolean visited[]= new boolean[N+1];
         int adj[][]= new int [N+1][N+1];
         int M = sc.nextInt();
         int x,y, value;
         for(int m =0;m<M;m++){
          x = sc.nextInt();
          y= sc.nextInt();
          value = sc.nextInt();
          /*if(adj[x][y]!=0){
           value = Math.min(adj[x][y], value);
          }*/
          adj[x][y]=value;
          //adj[y][x]=value;
         }
         
         int Q = sc.nextInt();
         for (int q =1;q<=N;q++){
          
          
          /*int startingNode = sc.nextInt();
          int destination =sc.nextInt();*/
          
          for ( int i =1 ; i <= N ; i++){
           insert(i,INFY);
           distance[q][i]=INFY;
           visited[i]=false;
          }
          decreasePri(q, 0);
          distance[q][q]=0;
         
          while(size!=0){
           Vertice proc = deleteMin();
           int v = proc.value;
           visited[v]=true;
           for( int i =1 ; i <= N ; i++){
            if(!visited[i] && adj[v][i]!=0 && distance[q][v]!=INFY){
             if(distance[q][i]>distance[q][v]+adj[v][i]){
              distance[q][i]=distance[q][v]+adj[v][i];
              decreasePri(i, distance[q][i]);
             }
            }
           }
          }
         } 
         for (int i =0 ;i<Q;i++){
          int q=sc.nextInt();
          int destination=sc.nextInt();
          if(distance[q][destination]==INFY){
        System.out.println("-1 ");
       }else{
        System.out.println(distance[q][destination]+" ");
       }
         } 
          
         
     }
     
     
 }
 
 public static class Vertice {
  int value;
  int priority;
 // int location;
 }
 
 public static boolean hasLeftChid(int position){
  if(position*2 <= size)
   return true;
  return false;
 }
 
 public static boolean hasRightchid(int position){
  if(position*2+1 <= size)
   return true;
  return false;
 }
 
 public static boolean hasParent(int position){
  if(position == 1)
   return false;
  return true;
 }
 
 public static int getLeft(int position){
  position *= 2;
  return position;
  //return heap[position];
 }
 
 public static int getRight(int position){
  position *= 2;
  position++;
  return position;
  //return heap[position];
 }
 
 public static int getParent(int position){
  return position/2;
 }
 
 public static void insert(int value,int pri){
  size++;
  Vertice e = new Vertice();
  e.value=value;
  e.priority=pri;
  location[e.value]=size;
  heap[size]=e;
  
  bubbleup(size);
 }
 
 public static Vertice deleteMin(){
  Vertice returnValue = heap[1];
  heap[1]=heap[size];
  heap[size]=null;
  size--;
  if(size != 0){
   int changedIndex = heap[1].value;
   location[changedIndex]=1;
  }
  bubbleDown(1);
  return returnValue;
 }
 
 public static void bubbleDown(int pos){
  while(pos<=size){
   Vertice current = heap[pos];
   Vertice min=current;
   Vertice leftChild, rightChild;
   if(hasLeftChid(pos)){
    leftChild = heap[2*pos];
    min =findmin(current,leftChild);
     if(hasRightchid(pos)){
      rightChild = heap[pos*2+1];
      min = findmin(min,rightChild);
     }
     
     if(min!=current){
      if(min==leftChild){
       swap(pos,getLeft(pos));
       pos=getLeft(pos);
      }else{
       swap(pos,getRight(pos));
       pos=getRight(pos);
      }
      
     }else{
      break;
     }
   }else{
     break;
   }
  }
 }

 
 public static void bubbleup(int position){
  while(position>1){
   Vertice current = heap[position];
   if(hasParent(position)){
    int parentPos = getParent(position);
    Vertice parent = heap[parentPos];
    if(current.priority<parent.priority){
     swap(position,parentPos);
     position= position/2;
    }else{
     break;
    }
   }
   
  }
 }

 private static void decreasePri(int value ,int pri){
  int position = location[value];
  heap[position].priority=pri;
  Vertice current = heap[position];
  if(hasParent(position)){
   Vertice parent = heap[getParent(position)];
   if(current.priority<parent.priority){
    bubbleup(position);
   }else{
    bubbleDown(position);
   }
  }else{
   bubbleDown(position);
  }
  
 }
 
 private static void swap(int location1, int location2) {
  // TODO Auto-generated method stub
   Vertice temp;
   temp = heap[location2];
   heap[location2] = heap[location1];
   int change = heap[location1].value;
   heap[location1] = temp;
   location[temp.value]=location1;
   location[change]=location2;
 }

 private static Vertice findmin(Vertice min, Vertice Vertice) {
  // TODO Auto-generated method stub
  if(min.priority>Vertice.priority){
   return Vertice;
  }
  return min;
 }
}