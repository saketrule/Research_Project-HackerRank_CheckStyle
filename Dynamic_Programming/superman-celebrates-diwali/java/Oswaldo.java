import java.io.*;
import java.util.*;
import java.math.*;

class Solution {
    

    public static void main(String[] args) throws Exception {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

          String splitLine[]=br.readLine().split(" ");
          int N=Integer.parseInt(splitLine[0]);
          int H=Integer.parseInt(splitLine[1]);
          int I=Integer.parseInt(splitLine[2]);
      
      int Persons[][]=new int[N][H];
      for(int i=0;i<N;++i) {
   splitLine=br.readLine().split(" ");
   int numPersons=Integer.parseInt(splitLine[0]);
   for(int j=1;j<splitLine.length;++j)
       Persons[i][Integer.parseInt(splitLine[j])-1]++;
      }
      /*
      System.out.println("The Persons Array:");
      for(int i=0;i<N;++i)
   for(int j=0;j<H;++j)
       System.out.println("Persons "+i+" "+j+"="+Persons[i][j]);
      */

      int DP[][]=new int[N][H];

      int maxArray[]=new int[H];

      int max=Integer.MIN_VALUE;
      for(int i=0;i<N;++i) {
   DP[i][0]=Persons[i][0];
   max=Math.max(DP[i][0],max);
      }
      maxArray[0]=max;

      for(int j=1;j<H;++j) {
   max=Integer.MIN_VALUE;
   for(int i=0;i<N;++i) {
       DP[i][j]=Persons[i][j];
       if(j-I>=0)
    DP[i][j]+=Math.max(DP[i][j-1],maxArray[j-I]);
       else
    DP[i][j]+=DP[i][j-1];
       max=Math.max(max,DP[i][j]);
       /*
       for(int k=0;k<N;++k) {
    if(k==i || j-I<0)
        continue;
    DP[i][j]=Math.max(DP[i][j],Persons[i][j]+DP[k][j-I]);
       }
*/
   }
   maxArray[j]=max;
      }
      /*
      System.out.println("The DP matrix is:");
      for(int i=0;i<N;++i)
   for(int j=0;j<H;++j)
       System.out.println("DP "+i+" "+j+"="+DP[i][j]);
      */

      /*
      int maxRes=Integer.MIN_VALUE;
      for(int i=0;i<N;++i)
   maxRes=Math.max(DP[i][H-1],maxRes);
      System.out.println(maxRes);
      */
      System.out.println(max);
 }
}