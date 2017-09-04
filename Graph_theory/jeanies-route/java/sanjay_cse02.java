import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
public static void main(String[] args) 
{
Scanner object=new Scanner(System.in);
int nodes=object.nextInt();
int k1=object.nextInt();
    

int i=0,j=0,k=0;
int max=2000000000;    
    
//System.out.println("nodes= "+nodes+"  edges="+edges);    
int [][]array=new int[3005][3005];
int []visited=new int[3005];
    
int edges=nodes-1;
for(i=1;i<=k1;i++)
{    
int a=object.nextInt();
}
    
    
    
for(i=1;i<=edges;i++)
{
int start=object.nextInt();
int end=object.nextInt();
int val=object.nextInt();
array[start][end]=val;
array[end][start]=val;
}
    
    /*
for(i=1;i<=nodes;i++)
{
for(j=1;j<=nodes;j++)
{
System.out.print(array[i][j]+" ");
}
System.out.print("\n");
}*/
    
   
for(i=0;i<=nodes;i++)
{
for(j=0;j<=nodes;j++)
{
if(array[i][j]==0)
array[i][j]=max;
}
}
    
    /*
for(i=1;i<=nodes;i++)
{
for(j=1;j<=nodes;j++)
{
System.out.print(array[i][j]+" ");
}
System.out.print("\n");
}    */
 
    
    
visited[1]=1;
int ne=1,a=0,b=0,u=0,v=0;
int min=0,mincost=0;
int n=nodes;    
    
while(ne < n)
 
 {
 
  for(i=1,min=max;i<=n;i++)
 
  for(j=1;j<=n;j++)
 
  if(array[i][j]< min)
 
  if(visited[i]!=0)
 
  {
 
   min=array[i][j];
 
   a=u=i;
 
   b=v=j;
 
  }
 
  if(visited[u]==0 || visited[v]==0)
 
  {
 
   //printf("\n Edge %d:(%d %d) cost:%d",ne++,a,b,min);
 ne++;
   mincost+=min;
 
   visited[b]=1;
 
  }
 
  array[a][b]=array[b][a]=max;
 
 }



    
System.out.println(mincost);
}}