import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class BlackWhiteTree
{   
   short[] color; 
   int N,M; 
   int edges;
   int D; 
   List networkdetails;
   List vertices;  
   Map shadecountlist;
   Map adjcount;
   List counts;
   List blackcount,whitecount;  
   Queue nodes; 
   BlackWhiteTree()
   {}
   void readInput() throws IOException
   {
     String line;  
     String[] inps;  
     BufferedReader inp=new BufferedReader(new InputStreamReader(System.in));
     line=inp.readLine();
     inps=line.split(" ");  
     N=Integer.parseInt(inps[0]);  
     M=Integer.parseInt(inps[1]);    
     vertices=new ArrayList(N+1);
     networkdetails=new ArrayList();  
     color=new short[N+1];  
     vertices.add(new ArrayList());  
     for(int i=1;i<=N;i++)  
     {
      vertices.add(new ArrayList());   
      color[i]=-1;   
     }          
     for(int i=0;i<M;i++)
     {
        line=inp.readLine();
        inps=line.split(" ");
        int vert1,vert2;
        vert1=Integer.parseInt(inps[0]);  
        vert2=Integer.parseInt(inps[1]); 
        if(vert1>vert2)
        {
          int temp=vert1;
          vert1=vert2;
          vert2=temp;  
        }   
        List adj=(List)vertices.get(vert1);         
        adj.add(vert2); 
        vertices.set(vert1,adj); 
     }
     edges=0;
     D=0;  
   }
   int getNextRootNode(int start)
   {
     for(int itr=start;itr<=N;itr++)
      if(color[itr]==-1)
       return itr;
     return N+1;    
   }
   int setShadeColor(int node,short shade)
   {
      int count=0; 
      List adjnodes=(List)vertices.get(node);
      int len=adjnodes.size();
      for(int i=0;i<len;i++)
      {
        int adjnode=(int)adjnodes.get(i);
         if(color[adjnode]==-1) 
         {
           color[adjnode]=shade;  
           count++;  
         }
        nodes.add(adjnode);  
      }    
      return count; 
   }
   int[] getShadeCounts(int root)
   {
     int blackcount=0,whitecount=0;  
     nodes=new LinkedList();
     nodes.add(root);
     while(nodes.size()>0)
     {
       int node=(int)nodes.remove();
       if(node==root)
       { 
        blackcount+=1;
        color[node]=1;   
        whitecount+=setShadeColor(node,(short)2);   
       }
       else
        if(color[node]==1)
         whitecount+=setShadeColor(node,(short)2);
        else   
         blackcount+=setShadeColor(node,(short)1);                 
     }
     return new int[]{blackcount,whitecount};  
   }
   void genMap(int count1,int count2)
   {
     List temp=(List)shadecountlist.get(count1);
     if(temp==null)
     {
       temp=new ArrayList();
       temp.add(count2); 
       shadecountlist.put(count1,temp);  
     }
     else
     {
       temp.add(count2);   
       Object cnt=adjcount.get(count1);
       if(cnt==null)       
        adjcount.put(count1,2);           
       else       
        adjcount.put(count1,(int)cnt+1);          
     }      
     temp=(List)shadecountlist.get(count2);
     if(temp==null)
     {
       temp=new ArrayList();
       temp.add(count1); 
       shadecountlist.put(count2,temp);  
     }
     else
     {
       temp.add(count1);   
       Object cnt=adjcount.get(count2);
       if(cnt==null)       
        adjcount.put(count2,2);           
       else       
        adjcount.put(count2,(int)cnt+1);          
     }      
   }
   void getSubNetworks()
   {
      int root;      
      root=1; 
      shadecountlist=new TreeMap();
      adjcount=new TreeMap();
      counts=new ArrayList(); 
      while(root<=N) 
      {
        edges++;  
        int[] count=getShadeCounts(root);  
        networkdetails.add(root);         
        genMap(count[0],count[1]);          
        counts.add(count[0]);  
        counts.add(count[1]);  
        root=getNextRootNode(root);  
      }        
   }
   boolean canAdd(int cnt1)
   {     
     List vertlist=(List)shadecountlist.get(cnt1);
     int length=vertlist.size();  
     for(int i=0;i<length;i++)
     {
       int node= (int)vertlist.get(i);
       if(blackcount.contains(node))
       {
        Object count=adjcount.get(node);  
        if(count!=null)
        {
          int cnt=(int)count;
          if(cnt>1)          
           adjcount.put(node,cnt-1);                         
          else  
           return false;       
        }
        else   
         return false;   
       }    
     }    
     return true; 
   }    
   void getMinimumDValue()
   {
      boolean smallflag=true;       
      blackcount=new ArrayList();
      whitecount=new ArrayList(); 
      Object[] sortedcount1=counts.toArray();
      counts.clear(); 
      int[] sortedcount=new int[sortedcount1.length];       
      for(int i=0;i<sortedcount1.length;i++)
       sortedcount[i]=(int)sortedcount1[i]; 
      sortedcount1=null; 
      Arrays.sort(sortedcount);
      int length=sortedcount.length;
      for(int itr=0;itr<length;itr+=2)
      {
        int blkcnt=sortedcount[itr];
        int whtcnt=sortedcount[itr+1];    
        if(!smallflag) 
        {
          int temp=blkcnt;
          blkcnt=whtcnt;
          whtcnt=temp;   
        }
        if(canAdd(blkcnt))  
        {
          blackcount.add(blkcnt);
          whitecount.add(whtcnt);    
        }  
        else
        {
          blackcount.add(whtcnt);
          whitecount.add(blkcnt);   
        }
        smallflag=!smallflag;  
      }
      length=blackcount.size();
      int cnt1=0,cnt2=0; 
      for(int i=0;i<length;i++)
      {
        cnt1+=(int)blackcount.get(i);  
        cnt2+=(int)whitecount.get(i);    
      }
      D=Math.abs(cnt1-cnt2); 
   }
   void printOutput()
   {
     System.out.format("%d %d\n",D,edges-1);  
     int length=networkdetails.size()-1;  
     for(int i=0;i<length;i++)
     {
       int root1=(int)networkdetails.get(i);
       int root2=(int)networkdetails.get(i+1);   
       List children=(List)vertices.get(root1);
       if(children.size()!=0)       
        System.out.format("%d %d\n",(int)children.get(0),root2);            
       else
       {
        children=(List)vertices.get(root2);   
        System.out.format("%d %d\n",(int)children.get(0),root1);  
       }         
     }    
   }
   void getOutput()
   {
      getSubNetworks(); 
      getMinimumDValue();
      printOutput(); 
   }
}

public class Solution {

    public static void main(String[] args) throws IOException {
        BlackWhiteTree ques=new BlackWhiteTree();
        ques.readInput();
        ques.getOutput();
    }
}