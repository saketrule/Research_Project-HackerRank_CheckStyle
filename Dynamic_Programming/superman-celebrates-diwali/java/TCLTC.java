import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in=new Scanner(System.in);
        int N=in.nextInt();
        int H=in.nextInt();
        int delta=in.nextInt();
        int[][] M=new int[H+1][N];
        int[][] Opt=new int[H+1][N];
        int[] maxF=new int[H+1];
        //transcibe
        for (int n0=0;n0<N;n0++){
            int u=in.nextInt();
            for (int i=0;i<u;i++){
                int nextFloor=in.nextInt();
                M[nextFloor][n0]=M[nextFloor][n0]+1;
            }
        }
        //initialize Opt, i=H, with M data
        for (int i=0;i<N;i++){
            Opt[H][i]=M[H][i];
        }
        int maximum=0;
        for (int j=0;j<N;j++){
            if (Opt[H][j]>maximum){maximum=Opt[H][j];}
        }
        maxF[H]=maximum;
        //recurrence Relation
        for (int i=H-1;i>0;i--){
            for (int j=0;j<N;j++){
                //each entry compute Opt
                int changeFloorOpt=0;
                if (i+delta<=H){
                     int i0=i+delta;
                     changeFloorOpt=maxF[i0];   
                }
                
                Opt[i][j]=M[i][j]+Math.max(Opt[i+1][j],changeFloorOpt);
            }
            //calculateMaxF
            maximum=0;
            for (int j=0;j<N;j++){
                if (Opt[i][j]>maximum) maximum=Opt[i][j];
            }
            maxF[i]=maximum;
        }
        
        //sweep the row one (i=1)
        int output=0;
        for (int j=0;j<N;j++){
            if (Opt[1][j]>output)output=Opt[1][j];
        }
        System.out.println(output);
        
    }
}