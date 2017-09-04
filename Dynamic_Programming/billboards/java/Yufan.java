/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.util.*;



public class Solution {
 
 
 public static void main(String []args)
 {
  
  
  long sum=0;
  //int deleteIndex=0;
    // int checkIndex=0;
     long tmp=0;
  //ArrayList<Integer> all= new ArrayList<Integer>();
     Scanner SC=new Scanner(System.in);
     SC.useDelimiter(" ");
  String tmp0=SC.nextLine();
  String first[]=tmp0.split(" ");   
     int N=Integer.parseInt(first[0]);
  int K=Integer.parseInt(first[1]);
  long []AllNum=new long[N];
  
  for(int i=0;i<N;i++)
  {
   String tmp1=SC.nextLine(); 
   AllNum[i]=Integer.parseInt(tmp1);     
  }
  
  
  long []SubSum=new long[N];
  //int []equalSet=new int[K];
  
  for(int i=0;i<=K;i++)
  {
   sum+=AllNum[i]; 
  }
  
  for(int i=0;i<=K;i++)
  {
   SubSum[i]=sum-AllNum[i];
  }
            
  if(N<70000)
                {
  for(int i=K+1;i<N;i++)
  {
                    SubSum[i]=SubSum[i-K-1];
   for(int j=i-K;j<=i-1;j++)
   {   
    
     if(SubSum[i]<SubSum[j])
     {
      SubSum[i]=SubSum[j];
     }
     SubSum[j]+=AllNum[i]; 
    
   }
  }
  }
            
            else
            {
                int index=0;
                for(int i=K+1;i<N;)
  {
                    if (i-index-K-1<0) {
                   int gap = K+1-i+index;
                   long  jump = 0;
                   for (int j = 0; j < gap; j++) {
                       if (j+i >=N) break;
                       jump += AllNum[j+i];
                   }
                   for (int j = i+gap; j >= i; j--) {
                       if (j >= N) continue;
                       SubSum[j] += SubSum[index] + jump - AllNum[j];
                   }
                   for (int j = i-K; j<i; j++) {
                       SubSum[j] += jump;
                   }
                   i += gap;
               }else {
                   SubSum[i] = SubSum[index];
                   for(int j = i-K; j < i; j++) {
                       if (SubSum[i] < SubSum[j]){
                           SubSum[i] = SubSum[j];
                           index = j;
                       }
                       SubSum[j] += AllNum[i];
                   }
                   i++;
                }
  }
                
                
            }
  for(int i=0;i<SubSum.length;i++)
  {
   
   if(tmp<SubSum[i])
   {
    tmp=SubSum[i];
   }
   
  }
  
  System.out.print(tmp);
  

   
  
 }

}