import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    class IndexPointers {
        int idx;
        int jdx; 
    }
    
    public IndexPointers calIndex(int m, int n, int i, int j, int r) {
        int min = m>n?n:m;
  int minIdx = i>j?j:i;
        int minIdx1 = (m-1-i)>(n-1-j)?(n-1-j):(m-1-i);
        minIdx = minIdx > minIdx1?minIdx1:minIdx;
        
        
  int factor = (n-minIdx*2)*2 + (m-2-minIdx*2)*2;
  r=r%factor;
        while(r > 0) {
            if(i<min/2 && j<min/2) {
                if(j>i) {
                    if((j-i) >= r) {
                        j = j-r;
                        r=0;
                    }
                    else {
                        r= r-(j-i);
                        j=i;
                    }
                }
                else if(i>=j) {
                    if(m-i-j-1 >= r) {
                        i+=r;
                        r=0;
                    }
                    else {
                        r=r-(m-i-j-1);
                        i+=(m-i-j-1);
                    }
                }
            }
            else if (i<min/2 && j>=min/2) {
    if(i>n-j-1) {
     if(i-(n-j-1) >=r) {
      i-=r;
      r=0;
     }
     else {
      r-= i-(n-j-1);
      i-= i-(n-j-1);
     }
    }
    else if(i<=n-j-1) {
                    if((j-i) >= r) {
                        j = j-r;
                        r=0;
                    }
                    else {
                        r= r-(j-i);
                        j=i;
                    }
                }
      }
            else if (i>=min/2 && j<min/2) {
    if(i >= m-j-1) {
     if(n-j-(m-i-1) -1 >= r) {
      j+=r;
      r=0;
     }
     else {
      r-=n-j-(m-i-1) -1;
      j+=n-j-(m-i-1) -1;
     }
    }
    else if(i < m-j-1) {
     if(m-j-1-i>=r) {
      i+=r;
      r=0;
     }
     else {
      r-=m-j-1-i;
      i+=m-j-1-i;
     }
    }
      }
      else if (i>=min/2 && j>=min/2) {
    if(j < n-1-(m-i-1)) {
     if(n-j-1-(m-i-1) >= r){
      j+=r;
      r=0;
        }
     else {
      r=r-(n-j-1-(m-i-1));
      j+=n-j-1-(m-i-1);
         
     }
    }
    else if(j >= n-1-(m-i-1)) {
                   if((i-(n-j-1)) >= r) {
                       i-= r;
                       r=0;
                   }
                   else {
                       r = r-(i-(n-j-1));
                       i-= i-(n-j-1);
                   }
               }
            }
        }
        IndexPointers idxPtr = new IndexPointers();
        idxPtr.idx = i;
        idxPtr.jdx = j;
        return idxPtr;
    }
    
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int rotCount = in.nextInt();
        int[][] inArr = new int[m][n];
        int[][] outArr = new int[m][n];
        Solution sln = new Solution();
        for(int i =0; i<m; i++) {
            for(int j=0; j<n; j++) {
                inArr[i][j] = in.nextInt();
                IndexPointers idxPtr = sln.calIndex(m, n, i, j, rotCount);
                
                //System.out.println("i = " + i);
                //System.out.println("j = " + j);
                
                outArr[idxPtr.idx][idxPtr.jdx] = inArr[i][j];
                
                
                //System.out.println("idx = " + idxPtr.idx);
                //System.out.println("jdx = " + idxPtr.jdx);
            }
        }
       
        for(int i =0; i<m; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(outArr[i][j] + " ");
            }
            System.out.println();
        }  
       
        
    }
}