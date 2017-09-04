import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
//Day1: A Chessboard Game
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int[][] a = new int[15][15];
        
        int i,j,x,y;
        for(i=0;i<15;i++){
            Arrays.fill(a[i],2);
        }
        
        a[0][2]=1;
        a[2][0]=1;
        a[0][3]=1;
        a[1][2]=1;
        a[2][1]=1;
        a[3][0]=1;
        //recursively compute
        for(i=4;i<=28;i++) {
            for(j=0;j<=i;j++){
                if(j>=0 && i-j>=0 && j<=14 && i-j<=14){
                    if(j-2>=0 && i-j+1>=0 && j-2<=14 && i-j+1<=14 && a[j-2][i-j+1] == 2 ||
                       j-2>=0 && i-j-1>=0 && j-2<=14 && i-j-1<=14 && a[j-2][i-j-1] == 2 ||
                       j+1>=0 && i-j-2>=0 && j+1<=14 && i-j-2<=14 && a[j+1][i-j-2] == 2 ||
                       j-1>=0 && i-j-2>=0 && j-1<=14 && i-j-2<=14 && a[j-1][i-j-2] == 2) 
                        a[j][i-j]=1;
                }
            }
        }
        
        for(int tt =0;tt<t;tt++){
            x = in.nextInt();
            y = in.nextInt();
            if (a[x-1][y-1]==1) System.out.println("First");
            else System.out.println("Second");
        }
    }
}