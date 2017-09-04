import java.io.*;
import java.util.*;
import java.math.*;
public class Solution {
 public static void main(String args[]) throws Exception {
  Scanner sc = new Scanner(System.in);
  int N = sc.nextInt();
  int K = sc.nextInt();
  int step=K/10;
  int P[] = new int[N+16];
  long maxSum[] = new long[N+16];
  int maxSumEle[] = new int[N+16];
  long max[] = new long[N+16];
  int maxCount[] = new int[N+16];
  int ii=0;
  for(int i=0; i < N; ii++,i++) {
   P[ii] = sc.nextInt();
   if(P[ii]==0&&(ii==0||P[ii-1]==0))
    ii--;
  }
  if(ii>0&&P[ii-1]==0)
   ii--;
  N=ii;
  int mycount=0;
  int n=N-1;
  for(int i=0;n>=0&&(i<K||(i==K&&P[n]==0));i++,n--) {
   max[n]=P[n]+max[n+1];
   if(P[n]==0)
    i=-1;
   maxCount[n]=i+1;
   //System.out.println(n+"   "+P[n]+" "+max[n]+" "+maxCount[n]);
  }
  int NN=N;
  NN=n+1;
  long currSum=0;
  long currMax=-1;
  int currMaxEle=-1;
  int end=K+3<N-NN+3?K+3:N-NN+3;
  for(int i=0;i<end;i++) {
   if(currSum+max[NN+i+1]>=currMax) {
    if(currSum+max[NN+i+1]>currMax||currMaxEle>i+1)
     currMaxEle=i+1;
    currMax=currSum+max[NN+i+1];
   }
   if(currSum+max[NN+i+2]>=currMax) {
    if(currSum+max[NN+i+2]>currMax||currMaxEle>i+2)
     currMaxEle=i+2;
    currMax=currSum+max[NN+i+2];
   }
   maxSum[i]=currMax;
   //System.out.println(N+" "+K+" "+P[i+NN]+" "+i+" "+maxSum[i]+" "+maxSumEle[i]);
   maxSumEle[i]=currMaxEle;
   currSum+=P[i+NN];
  }
  //System.out.println(i+"            "+n+" "+P[n]+" "+max[n]);
  int mid=-1;
  if(step>=1)
   mid=NN-step;
  for(;n>=0;n--) {
   long res=max[n+1];
   int resCount=0;
   currSum=0;
   int i=0;
   end=K<NN-n?K:NN-n;
   for(i=0;i<end;i++) {
    currSum+=P[n+i];
    if(res<=max[n+i+2]+currSum) {
     if(res<max[n+i+2]+currSum||resCount>i+1)
      resCount=i+1;
     res=max[n+i+2]+currSum;
    }
    //System.out.println(i+" "+n+" "+P[n]+" "+res+" "+resCount);
   }
    
   if((i+n)<N) {
    if(i<K) {
     long currSum2=currSum+maxSum[K-i];
     int currSumEle=i-1+maxSumEle[K-i];
     if(res<=currSum2) {
      if(res<currSum2||resCount>currSumEle)
       resCount=currSumEle;
      res=currSum2;
     }
     //System.out.println(i+" "+n+" "+P[n]+" "+res+" "+resCount);
    } else {
     i--;
     if(i>=0&&res<=max[n+i+3]+currSum) {
      if(res<max[n+i+3]+currSum||resCount>i+1)
       resCount=i+1;
      res=max[n+i+3]+currSum;
     }
    }
   }
   if(maxCount[n+1]<K&&res<=max[n+1]+P[n]) {
    if(res<max[n+1]+P[n]||resCount>maxCount[n+1]+1)
     resCount=maxCount[n+1]+1;
    res=max[n+1]+P[n];
   }
   max[n]=res;
   maxCount[n]=resCount;
   if(n==mid) {
    NN=n+1;
    currSum=0;
    currMax=-1;
    currMaxEle=-1;
    end=K+3<N-NN+3?K+3:N-NN+3;
    for(i=0;i<end;i++) {
     if(currSum+max[NN+i+1]>=currMax) {
      if(currSum+max[NN+i+1]>currMax||currMaxEle>i+1)
       currMaxEle=i+1;
      currMax=currSum+max[NN+i+1];
     }
     if(currSum+max[NN+i+2]>=currMax) {
      if(currSum+max[NN+i+2]>currMax||currMaxEle>i+2)
       currMaxEle=i+2;
      currMax=currSum+max[NN+i+2];
     }
     maxSum[i]=currMax;
     //System.out.println(N+" "+K+" "+P[i+NN]+" "+i+" "+maxSum[i]+" "+maxSumEle[i]);
     maxSumEle[i]=currMaxEle;
     currSum+=P[i+NN];
    }
    //if(mid>1000)
     mid-=step;//mid*9/10;
    //else
    // mid=-1;
    if(mid<=0)
     mid=-1;
    mycount++;   
   }
   //System.out.println(n+" "+P[n]+" "+res+" "+resCount);
  } 
  System.out.println(max[0]);//+" "+mycount);
 }
}
/*
time java Solution < myin10
99915808645418

real 0m26.662s
user 0m32.146s
sys 0m17.673s
*/