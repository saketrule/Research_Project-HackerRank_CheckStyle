import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
     static int adj[][];
     static int V;
     static int status[];
     static int predecessor[];
     static int length[];
     static  int TEMP;
     static int PERM;
     static int NIL;
     static int infinity;    
    Solution(int v){
     adj=new int[v+1][v+1];
        for(int i=1;i<v+1;i++)
      for(int j=1;j<v+1;j++){
       adj[i][j]=-2;
      }
        TEMP=0;
        PERM=1;
        NIL=-1;
        infinity=999999;
        V=v;
    }
    
    static void addEdge(int a,int b,int wt){
     
      adj[a][b]=wt;
            adj[b][a]=wt;
     
     
     }
     
     
        
    
    
    static void shortest(int s){
        int current;
        predecessor=new int[V+1];
        length=new int[V+1];
        status=new int[V+1];
       
        for(int i=0;i<V+1;i++){
            predecessor[i]=NIL;
            length[i]=infinity;
            status[i]=TEMP;
        }
        length[s]=0;
        
        while(true){
            current=min_temp();
            if(current==NIL)
                return ;
            status[current]=PERM;
            
            for(int i=1;i<V+1;i++){
                if(status[i]==TEMP && adj[current][i]!=-2)
                    if(length[current]+adj[current][i]<length[i]){
                        length[i]=length[current]|adj[current][i];
                            predecessor[i]=current;
                }
            }
            
        }
    }
    
    static int findPath(int s,int d){
        int count=0;
        int u;
        int path[]=new int[10000];
        int shortDist=0;
        while(d!=s){
            count++;
            path[count]=d;
            
            u=predecessor[d];
            shortDist=shortDist|adj[u][d];
            d=u;
        }
        count++;
        path[count]=s;
        return shortDist;
      }
    
    
    static int min_temp(){
        int min=infinity;
        int k=NIL;
        for(int i=1;i<V+1;i++){
         if(status[i]!=PERM && length[i]<min){
             min=length[i];
             k=i;
         }
       }
        return k;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
     BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
       // Scanner sc=new Scanner(System.in);
     //DataInputStream dis = new DataInputStream(System.in);
        //int q=Integer.parseInt(inp.readLine());
        
         String[] s1=inp.readLine().split(" ");
         int vertices=Integer.parseInt(s1[0]);
            long edges=Long.parseLong(s1[1]);
            Solution obj=new Solution(vertices);
            for(int j=0;j<edges;j++){
             String[] s2=inp.readLine().split(" ");
             int a=Integer.parseInt(s2[0]);
                int b=Integer.parseInt(s2[1]);
                int wt=Integer.parseInt(s2[2]);
                if(adj[a][b]==-2 || wt<adj[a][b])
                addEdge(a,b,wt);
               
               }
            String[] s3=inp.readLine().split(" ");
            int source=Integer.parseInt(s3[0]);
            int destination=Integer.parseInt(s3[1]);
            //int realSource=source-1;
            shortest(source);
            int realPath[] = null;
            //System.out.println("vertices: "+vertices);
            //System.out.println("edges: "+edges);
            //System.out.print("source: "+source);
            if(length[destination]==infinity)
             System.out.print("-1 ");
            else{
             System.out.print(findPath(source,destination));
             //realPath=findPath(source,destination);
            }
//            int res=0;
//              for(int i=1;i<4;i++){
//               //System.out.println(realPath[i]);
//               res=res|adj[realPath[i]][realPath[i+1]];
//               
//              }
//              System.out.println(res);
              
           
            
        
       
    }
}