import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int matRow = m;
        int n = in.nextInt();
        int matCol = n;
        int rot = in.nextInt();
        int[][] mat = new int[m][n];
        
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                mat[i][j] = in.nextInt();
                //System.out.print(mat[i][j]+" ");
            }
            //System.out.println("");
        }
        
        int stRowIndex = 0;
        int endRowIndex = m-1;
        int stColIndex = 0;
        int endColIndex = n-1;
        
        while(true){
            mat = rotateSubMat(mat,stRowIndex,endRowIndex,stColIndex,endColIndex,rot, matRow, matCol);
            stRowIndex++;
            endRowIndex--;
            stColIndex++;
            endColIndex--;
            
            if ( (stRowIndex >= endRowIndex) || (stColIndex >= endColIndex) ) {
                break;
            }
        }
        
        //System.out.println("");
        for (int i=0; i<matRow; i++){
            for (int j=0; j<matCol; j++){
                System.out.print(mat[i][j]+" ");
            }
            System.out.println("");
        }
        
    }
    
    static int[][] rotateSubMat(int[][] mat, int i, int m, int j, int n, int rot, int matRow, int matCol){
        //System.out.println("stRow="+i+" endRow="+m+" stCol="+j+" endCol="+n);
        int arrLen = (2*(m-i+1)) + (2*(n-j+1)) - 4;
        //System.out.println("arrLen="+arrLen);
        int[] arr = new int[arrLen];
        int itr=0;
        int p,q;
           
            p=i;
            for( q=j; q<=n; q++){
                arr[itr] = mat[p][q];
                itr++;
            }
            
            q=n;
            for( p=i+1; p<=m; p++){
                arr[itr] = mat[p][q];
                itr++;
            }
        
            p=m;
            for(q=n-1; q>=j; q--){
                arr[itr] = mat[p][q];
                itr++;
            }
        
            q=j;
            for(p=m-1; p>i; p--){
                arr[itr] = mat[p][q];
                itr++;
            }
        
        int[] rotArr = new int[arrLen];
        int pos;
        rot = rot % arrLen;
        
        for (int x=0; x<arrLen; x++){
            //System.out.print(arr[x]+" ");
            //System.out.println("x="+x+" rot="+rot+" arrlen="+arrLen);
            int temp = x - rot;
            pos = (temp % arrLen + arrLen)%arrLen;
            //System.out.println(" pos="+pos);
            rotArr[pos] = arr[x];
        }
        
        //System.out.println("");
        for (int x=0; x<arrLen; x++){
            //System.out.print(rotArr[x]+" ");
        }
        
        itr=0;
        p=i;
            for( q=j; q<=n; q++){
                mat[p][q] = rotArr[itr];
                itr++;
            }
            
            q=n;
            for( p=i+1; p<=m; p++){
                mat[p][q] = rotArr[itr];
                itr++;
            }
        
            p=m;
            for(q=n-1; q>=j; q--){
                mat[p][q] = rotArr[itr];
                itr++;
            }
        
            q=j;
            for(p=m-1; p>i; p--){
                mat[p][q] = rotArr[itr];
                itr++;
            }
        
        //System.out.println("");
        for (i=0; i<matRow; i++){
            for (j=0; j<matCol; j++){
                //System.out.print(mat[i][j]+" ");
            }
            //System.out.println("");
        }
        
        return mat;
    }
}