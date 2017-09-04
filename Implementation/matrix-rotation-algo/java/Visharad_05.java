import java.io.*;
public class Solution
{
 public static void main(String args[])throws IOException
 {
  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  String input[]=br.readLine().split(" ");
  int m=Integer.parseInt(input[0]);
  int n=Integer.parseInt(input[1]);
  int r=Integer.parseInt(input[2]);
  
  int a[][]=new int[m][n];
  for(int i=0;i<m;i++)
  {
   String input2[]=br.readLine().split(" ");
   for(int j=0;j<n;j++)
   {
    a[i][j]=Integer.parseInt(input2[j]);
   }
  }
  rotateMatrix(a,m,n,r);
 }
 public static void rotateMatrix(int a[][],int m,int n,int r)
 {
  int i;
  int k=0,l=0;
  int K=k,L=l;
  int x=m,y=n;
  int M=m,N=n;
  int w;
  int Mm=m,Nn=n;
  int ans[][]=new int[M][N];
  int noOfElements=2*(x+y-2);
  while(k<m && l<n)
  {
   K=k;L=l;
   M=m;N=n;
   int dim[]=new int[noOfElements];
   w=0;
   for(i=l;i<n;i++)
    dim[w++]=a[k][i];
   k++;
   
   for(i=k;i<m;i++)
    dim[w++]=a[i][n-1];
   n--;
   
   if(k<m)
         {
             for (i = n-1; i >= l; --i)
             {
                 dim[w++]=a[m-1][i];
             }
             m--;
         }

         if (l < n)
         {
             for (i = m-1; i >= k; --i)
             {
                 dim[w++]=a[i][l];
             }
             l++;    
         }
         
         /* Here my dimension matrix is ready
          * Now process it
          */
         //for(i=0;i<noOfElements;i++) System.out.print(dim[i]+" ");
         //System.out.println();
         
         int noOfRotations=r%noOfElements;
         int newDim[]=new int[noOfElements];
         int q=0;
         for(i=noOfRotations;i<noOfElements;i++) newDim[q++]=dim[i];
         for(i=0;i<noOfRotations;i++) newDim[q++]=dim[i];
         
         //for(i=0;i<noOfElements;i++) System.out.print(newDim[i]+" ");
         //System.out.println();
         
         /* New dimension is now ready
          * Put it back in the new matrix
          */

         w=0;
         
         for(i=L;i<N;i++)
    ans[K][i]=newDim[w++];
   K++;
   
   for(i=K;i<M;i++)
    ans[i][N-1]=newDim[w++];
   N--;
   
   if(K<M)
         {
             for (i = N-1; i >= L; --i)
             {
                 ans[M-1][i]=newDim[w++];
             }
             M--;
         }

         if (L < N)
         {
             for (i = M-1; i >= K; --i)
             {
                 ans[i][L]=newDim[w++];
             }
             L++;    
         }
         
         x=x-2;
         y=y-2;
         noOfElements=2*(x+y-2);
  }
  printMatrix(ans,Mm,Nn);
 }
 public static void printMatrix(int ans[][],int M,int N)
 {
  for(int i=0;i<M;i++)
  {
   for(int j=0;j<N;j++) System.out.print(ans[i][j]+" ");
   System.out.println();
  }
  
 }
}