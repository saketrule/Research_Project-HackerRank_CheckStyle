import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int N = in.nextInt();
        int R = in.nextInt();
        long[][] matrix = new long[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                matrix[i][j] = in.nextLong();
            }
        }
        
        int layer = M>N?(N/2):(M/2);
        for(int i=0;i<layer;i++){
            int k=0;
            long[] layerArray = new long[(M-2*i)*(N-2*i)];
            for(int j=i;j<M-i;j++){
                layerArray[k] = matrix[j][i];
                k++;
            }
            for(int j=i+1;j<N-i;j++){
                layerArray[k] = matrix[M-i-1][j];
                k++;
            }
            for(int j=M-i-2;j>=i;j--){
                layerArray[k] = matrix[j][N-i-1];
                k++;
            }
            for(int j=N-i-2;j>i;j--){
                layerArray[k] = matrix[i][j];
                k++;
            }
            long[] rotatedlayerArray = new long[k];
            println("R = "+R);
            println("k = "+k);
            int newR = R;
            if(R > k) 
                newR=newR%k;
            println("new R = "+newR);
            for(int x=0; x < newR; x++){
                println("k = "+k+", R="+newR+", x="+x);
                rotatedlayerArray[x] = layerArray[k-newR+x];
            }
 
            int y=0;
            for(int x=newR; x<k; x++){
                rotatedlayerArray[x] = layerArray[y];
                y++;
            }
            k=0;
            for(int j=i;j<M-i;j++){
                matrix[j][i] = rotatedlayerArray[k];
                k++;
            }
            for(int j=i+1;j<N-i;j++){
                matrix[M-i-1][j] = rotatedlayerArray[k];
                k++;
            }
            for(int j=M-i-2;j>=i;j--){
                matrix[j][N-i-1] = rotatedlayerArray[k];
                k++;
            }
            for(int j=N-i-2;j>i;j--){   
                matrix[i][j] = rotatedlayerArray[k];
                k++;
            }
        }
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println("");
        }
    }
   
    
    private static void print(Object msg){
        System.err.print(msg);
    }
    
    private static void println(Object msg){
        System.err.println(msg);
    }
}