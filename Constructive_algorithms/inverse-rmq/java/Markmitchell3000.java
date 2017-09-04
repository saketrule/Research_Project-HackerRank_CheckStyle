import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] segTree = new int [(2*n)-1];
        int[] leafElems = new int [n];
        for(int i=0; i<((2*n)-1);i++){
            segTree[i]= scan.nextInt();
        }
        Arrays.sort(segTree);
        leafElems[0] = segTree[0];
        int k = 1;
        for(int j=0; j<((2*n)-1);j++){
            if((j+1)<((2*n)-1)){
                if(segTree[j]!=segTree[j+1]){
                    leafElems[k]=segTree[j+1];
                    k++;
                }
            }
        }
        if (k!=n){
            System.out.println("NO");
        }
        else{
            bldSeg(leafElems, segTree, n);
        }
    }
    
    public static void bldSeg(int[] ns, int[] oldSeg, int n){
        int[] newSeg = new int [(2*n)-1];
        int[] tstNewSeg = new int [(2*n)-1];
        for(int i =0; i<n; i++){
            newSeg[n-1+i] = ns[i];
            tstNewSeg[n-1+i] = ns[i];
            int tempN = n/2;
            int tempI = i;
            while(tempN>0){
              if(tempI%2==0){
                newSeg[tempN-1+(i/2)]=ns[i];
                tstNewSeg[tempN-1+(i/2)]=ns[i];
                tempI /= 2;
                tempN /= 2;
              }
              else{
                  tempN=0;
              }
            }   
        }
        Arrays.sort(tstNewSeg);
        for(int l =0; l<((2*n)-1);l++){
            if(tstNewSeg[l]!=oldSeg[l]){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
        String output =""+newSeg[0];
        for(int m =1; m<((2*n)-1);m++){
            output += " "+newSeg[m];
        }
        System.out.println(output);
        
    }
}